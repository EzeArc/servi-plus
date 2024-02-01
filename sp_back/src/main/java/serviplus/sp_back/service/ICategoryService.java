package serviplus.sp_back.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import serviplus.sp_back.entity.Category;

public interface ICategoryService {

    public Category getCategory(Long id);

    public List<Category> listAllCategory();
    
    public List<Category> listAllCategoryActive();

    public Category createCategory(Category categoryReceived, MultipartFile imageReceived);

    public Category updateCategory(Category categoryReceived, MultipartFile imageReceived, Long idImage);

    public Category deleteCategory(Long id);
}
