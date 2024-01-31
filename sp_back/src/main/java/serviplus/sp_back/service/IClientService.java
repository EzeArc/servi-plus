package serviplus.sp_back.service;

import java.util.List;

import serviplus.sp_back.entity.Client;

public interface IClientService {

    public Client getClient(Long id);

    public List<Client> listAllClient();
    public Long countBy();

    public Client updateClient(Client clientDB, Client clientReceived);

    public Client updateClientStatus(Long id);

}
