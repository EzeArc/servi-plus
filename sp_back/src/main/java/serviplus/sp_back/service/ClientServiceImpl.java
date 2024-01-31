package serviplus.sp_back.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import serviplus.sp_back.entity.Client;
import serviplus.sp_back.repository.ClientRepository;

@Service
public class ClientServiceImpl implements IClientService {

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
    public Client updateClient(Client client) {
        Client clientDB = getClient(client.getId());
        if (clientDB == null) {
            return null;
        }
        clientDB.setName(client.getName());
        clientDB.setMail(client.getMail());
        clientDB.setAddres(client.getAddres());
        clientDB.setPhone(client.getPhone());
        clientDB.setImage(client.getImage());
        clientDB.setPassword(client.getPassword());
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
