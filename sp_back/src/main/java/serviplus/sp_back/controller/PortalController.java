package serviplus.sp_back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import serviplus.sp_back.entity.Category;
import serviplus.sp_back.service.CategoryServiceImpl;

@RestController
@RequestMapping("/sevi-plus")
@CrossOrigin(value = "http://localhost:4321")
public class PortalController {
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @GetMapping("/categories")
    public List<Category> getCategories() {
        var categories = categoryServiceImpl.listAllCategory();
        return categories;
    }
}
