package serviplus.sp_back.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import serviplus.sp_back.entity.Client;

public interface IClientService {

    public Client getClient(Long id);

    public List<Client> listAllClient();
    public Long countBy();

    public Client createClient(Client client);

    public Client updateClient(Client client);

    public Client deleteClient(Long id);

    public UserDetails loadUserByMail(String mail);

}
