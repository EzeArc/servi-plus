package serviplus.sp_back.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import serviplus.sp_back.entity.Client;
import serviplus.sp_back.repository.ClientRepository;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {

    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client getClient(Long id) {

        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public List<Client> listAllClient() {
        return clientRepository.findAll();
    }

    @Override
    @Transactional
    public Client updateClient(Client clientDB, Client clientReceived) {
        // Verifica nuevamente si el cliente existe, aunque debería haberse comprobado
        // en el controlador
        if (clientDB == null) {
            return null; // O lanza una excepción si lo prefieres
        }

        // Actualiza los campos del cliente de la base de datos con los datos recibidos
        clientDB.setName(clientReceived.getName());
        clientDB.setMail(clientReceived.getMail());
        clientDB.setAddres(clientReceived.getAddres());
        clientDB.setPhone(clientReceived.getPhone());
        clientDB.setImage(clientReceived.getImage());
        clientDB.setPassword(passwordEncoder.encode(clientReceived.getPassword()));

        // Guarda el cliente actualizado en la base de datos
        return clientRepository.save(clientDB);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public Client deleteClient(Long id) {
        Client ClientDB = getClient(id);
        if (ClientDB == null) {
            return null;
        }
        ClientDB.setState(true);
        return clientRepository.save(ClientDB);
    }

    @Override
    public Long countBy() {
        return clientRepository.countBy();
    }

}
