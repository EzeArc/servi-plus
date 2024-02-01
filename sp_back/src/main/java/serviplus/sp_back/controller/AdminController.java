package serviplus.sp_back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import serviplus.sp_back.entity.Admin;
import serviplus.sp_back.entity.Category;
import serviplus.sp_back.entity.Client;
import serviplus.sp_back.entity.Job;
import serviplus.sp_back.entity.Provider;
import serviplus.sp_back.service.AdminServiceImpl;
import serviplus.sp_back.service.CategoryServiceImpl;
import serviplus.sp_back.service.ClientServiceImpl;
import serviplus.sp_back.service.JobServiceImpl;
import serviplus.sp_back.service.ProviderServiceImpl;

@RestController
@RequestMapping("/admin")
@CrossOrigin(value = "http://localhost:4321/admin")
public class AdminController {
    @Autowired
    private ProviderServiceImpl providerServiceImpl;
    @Autowired
    private ClientServiceImpl clientServiceImpl;
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;
    @Autowired
    private AdminServiceImpl adminServiceImpl;
    @Autowired
    private JobServiceImpl jobServiceImpl;

    @GetMapping("/listClients")
    public List<Client> listAllClient() {
        return clientServiceImpl.listAllClient();
    }

    @GetMapping("/listProviders")
    public List<Provider> listAllProvider() {
        return providerServiceImpl.listAllProvider();
    }

    @GetMapping("/listJobs")
    public List<Job> listAllJob() {
        return jobServiceImpl.listAllJob();
    }

    @PostMapping("/category")
    public Category createCategory(@RequestBody Category category) {
        return categoryServiceImpl.createCategory(category);
    }

    @PutMapping("/category/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category categoryReceived) {
        try {
            Category categoryDB = categoryServiceImpl.getCategory(id);
            if (categoryDB == null) {
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Category not found with id: " + id);
            }
            Category updatedCategory = categoryServiceImpl.updateCategory(categoryDB, categoryReceived);
            return updatedCategory;
        } catch (Exception e) {
            throw new RuntimeException("Error updating category: " + e.getMessage(), e);
        }

    }

    @PatchMapping("/category/{id}")
    public Category deleteCategory(@PathVariable Long id) {
        return categoryServiceImpl.deleteCategory(id);
    }

    @PatchMapping("/provider/{id}")
    public Provider deleteProvider(@PathVariable Long id) {
        return providerServiceImpl.updateProviderStatus(id);
    }

    @PatchMapping("/client/{id}")
    public Client deleteClient(@PathVariable Long id) {
        return clientServiceImpl.updateClientStatus(id);
    }

    @PatchMapping("/admin/{id}")
    public Admin deleteAdmin(@PathVariable Long id) {
        return adminServiceImpl.updateAdminStatus(id);
    }

    @PatchMapping("/jobStatus/{id}")
    public Job updateJobStatus(@PathVariable Long id) {
        return jobServiceImpl.updateJobStatus(id);
    }

    @PatchMapping("/job/{id}")
    public Job deleteJob(@PathVariable Long id) {
        return jobServiceImpl.deleteJob(id);
    }

    @PatchMapping("/changeRole/{id}")
    public Client changeRoletoAdmin(@PathVariable Long id) {
        return adminServiceImpl.changeRoleToAdmin(id);
    }
}
