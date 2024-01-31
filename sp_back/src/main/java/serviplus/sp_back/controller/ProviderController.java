package serviplus.sp_back.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import jakarta.persistence.EntityNotFoundException;
import serviplus.sp_back.entity.Provider;
import serviplus.sp_back.service.ProviderServiceImpl;

@RestController
@RequestMapping("/user")
@CrossOrigin(value = "http://localhost:4321/user")
public class ProviderController {

    @Autowired
    private ProviderServiceImpl providerServiceImpl;

    @PutMapping("/provider/{id}")
    public Provider updateprovider(@PathVariable Long id, @RequestBody Provider providerReceived) {
        Provider providerDB = providerServiceImpl.getProvider(id);

        if (providerDB == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Provider not found with id: " + id);
        }
        Provider updatedProvider = providerServiceImpl.updateProvider(providerDB, providerReceived);
        return updatedProvider;
    }

    @GetMapping("/listProviders")
    public List<Provider> listAllProvider() {
        return providerServiceImpl.listAllProvider();
    }

    @DeleteMapping("/provider/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProvider(@PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        try {
            providerServiceImpl.deleteProvider(id);
            Map<String, Boolean> response = new HashMap<>();
            response.put("delete", true);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
