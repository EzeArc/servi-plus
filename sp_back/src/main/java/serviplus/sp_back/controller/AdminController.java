package serviplus.sp_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import serviplus.sp_back.entity.Client;
import serviplus.sp_back.entity.Provider;
import serviplus.sp_back.service.ClientServiceImpl;
import serviplus.sp_back.service.ProviderServiceImpl;

@RestController
@RequestMapping("/admin")
@CrossOrigin(value = "http://localhost:4321/admin")
public class AdminController {
    @Autowired
    private ProviderServiceImpl providerServiceImpl;
    @Autowired
    private ClientServiceImpl clientServiceImpl;

    @PatchMapping("/provider/{id}")
    public Provider updateProviderStatus(@PathVariable Long id) {
        return providerServiceImpl.updateProviderStatus(id);
    }

    @PatchMapping("/client/{id}")
    public Client deleteClient(@PathVariable Long id) {
        return clientServiceImpl.updateClientStatus(id);
    }
}
