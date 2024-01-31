package serviplus.sp_back.controller;

import serviplus.sp_back.entity.Client;
import serviplus.sp_back.service.ClientServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import jakarta.persistence.EntityNotFoundException;

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
    public ResponseEntity<Client> updateClient(@PathVariable Integer id, @RequestBody Client clientReceived,
                                               @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            // El usuario no está autenticado
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Client clientDB = clientServiceImpl.getClient(clientReceived.getId());
        if (clientDB == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Client not found with id: " + id);
        }

        // Puedes acceder a userDetails.getUsername() para obtener el nombre de usuario del usuario autenticado
        // Realiza la lógica de actualización aquí
        return ResponseEntity.ok(clientServiceImpl.updateClient(clientDB));
    }

    @GetMapping("/listClients")
    public List<Client> listAllClient() {
        // Puedes agregar lógica adicional aquí para manejar usuarios autenticados si es necesario
        return clientServiceImpl.listAllClient();
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteClient(@PathVariable Long id,
                                                             @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            // El usuario no está autenticado
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        try {
            // Lógica de eliminación de cliente
            clientServiceImpl.deleteClient(id);
            Map<String, Boolean> response = new HashMap<>();
            response.put("delete", true);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            // Manejo de cliente no encontrado
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            // Manejo de otras excepciones
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}