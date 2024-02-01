package serviplus.sp_back.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import serviplus.sp_back.entity.Job;
import serviplus.sp_back.entity.Provider;
import serviplus.sp_back.service.JobServiceImpl;
import serviplus.sp_back.service.ProviderServiceImpl;

@RestController
@RequestMapping("/user")
@CrossOrigin(value = "http://localhost:4321/user")
public class ProviderController {

    @Autowired
    private JobServiceImpl jobServiceImpl;
    @Autowired
    private ProviderServiceImpl providerServiceImpl;

    @GetMapping("/listJobsToFinish")
    public List<Job> listAllJobToFinish() {
        return jobServiceImpl.listAllJobToFinish();
    }

    @PutMapping("/provider/{id}")
    public Provider updateprovider(@PathVariable Long id, @RequestBody Provider providerReceived) {
        try {
            Provider providerDB = providerServiceImpl.getProvider(id);
            if (providerDB == null) {
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Provider not found with id: " + id);
            }
            Provider updatedProvider = providerServiceImpl.updateProvider(providerDB, providerReceived);
            return updatedProvider;

        } catch (Exception e) {
            throw new RuntimeException("Error updating provider: " + e.getMessage(), e);
        }
    }
}
