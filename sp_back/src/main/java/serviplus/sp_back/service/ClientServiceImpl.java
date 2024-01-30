package serviplus.sp_back.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;
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
    public Client createClient(Client client) {
        client.setState(false);
        return clientRepository.save(client);
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

    @Override
    public UserDetails loadUserByMail(String mail) throws UsernameNotFoundException {
        Client clientDB = clientRepository.findByMail(mail).orElse(null);
        if (clientDB == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con email: " + mail);
        }
        List<GrantedAuthority> permission = new ArrayList<>();
        GrantedAuthority clientPermission = new SimpleGrantedAuthority("ROLE_" + clientDB.getRol().toString());
        permission.add(clientPermission);
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        session.setAttribute("clientSession", clientDB);
        return new User(clientDB.getMail(), clientDB.getPassword(), permission);
    }
}
