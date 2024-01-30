package serviplus.sp_back.controller;

import serviplus.sp_back.entity.Client;
import serviplus.sp_back.service.ClientServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")
@CrossOrigin(value = "http://localhost:4321/user")
public class ClientController {

    @Autowired
    private ClientServiceImpl clientServiceImpl;

    @GetMapping("/auth")
    public Client getClientAuthorization(@RequestParam String param) {
        return new Client();
    }

    @PostMapping("register")
    public Client registerClient(@RequestBody Client client) {
        return clientServiceImpl.createClient(client);
    }

    @PutMapping("/client/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Integer id, @RequestBody Client clientRecibed)
            throws HttpClientErrorException {
        Client clientDB = clientServiceImpl.getClient(clientRecibed.getId());
        if (clientDB == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Client not found with id: " + id);
        }
        return ResponseEntity.ok(clientServiceImpl.updateClient(clientDB));
    }

    @GetMapping("/list-all-clients")
    public List<Client> listAllClient() {
        return clientServiceImpl.listAllClient();
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteClient(@PathVariable Long id) {
        try {
            Client clientDB = clientServiceImpl.getClient(id);
    
            if (clientDB == null) {
                throw new Exception("Client not found with id: " + id);
            }
    
            clientServiceImpl.deleteClient(clientDB.getId());
            Map<String, Boolean> response = new HashMap<>();
            response.put("delete", true);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    

    

}