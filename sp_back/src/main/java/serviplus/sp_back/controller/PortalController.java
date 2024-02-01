package serviplus.sp_back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import serviplus.sp_back.entity.Category;
import serviplus.sp_back.service.CategoryServiceImpl;
import serviplus.sp_back.service.ClientServiceImpl;
import serviplus.sp_back.service.JobServiceImpl;
import serviplus.sp_back.service.ProviderServiceImpl;

@RestController
@RequestMapping("/sevi-plus")
@CrossOrigin(value = "http://localhost:4321")
public class PortalController {
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;
    @Autowired
    private ClientServiceImpl clientServiceImpl;
    @Autowired
    private ProviderServiceImpl providerServiceImpl;
    @Autowired
    private JobServiceImpl jobServiceImpl;

    @GetMapping("/categories")
    public List<Category> getCategories() {
       return categoryServiceImpl.listAllCategoryActive();
    }

    @GetMapping("/totalUsers")
    public Long getCountTotalClients() {
       return clientServiceImpl.countBy();
    }

    @GetMapping("/totalProviders")
    public Long getCountTotalProviders() {
       return providerServiceImpl.countBy();
    }

    @GetMapping("/totalJobs")
    public Long getCountTotalJobs() {
       return jobServiceImpl.countByAllJob();
    }
}
