package serviplus.sp_back.controller;

import serviplus.sp_back.entity.Client;
import serviplus.sp_back.service.ClientServiceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")
@CrossOrigin(value = "http://localhost:4321/user")
public class ClientController {

    @Autowired
    private ClientServiceImpl clientServiceImpl;


    @PutMapping("/client/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client clientReceived) {
        // Verifica si el cliente existe antes de intentar actualizar
        Client clientDB = clientServiceImpl.getClient(id);
    
        if (clientDB == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Client not found with id: " + id);
        }
    
        // Llamada al servicio para realizar la actualización y obtener el cliente actualizado
        Client updatedClient = clientServiceImpl.updateClient(clientDB, clientReceived);
    
        // Puedes retornar el cliente actualizado si es necesario
        return updatedClient;
    }
    
    @GetMapping("/listClients")
    public List<Client> listAllClient() {
        // Puedes agregar lógica adicional aquí para manejar usuarios autenticados si es necesario
        return clientServiceImpl.listAllClient();
    }
}