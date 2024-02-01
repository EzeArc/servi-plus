package serviplus.sp_back.service;

import java.util.List;

import serviplus.sp_back.entity.Category;

public interface ICategoryService {

    public Category getCategory(Long id);

    public List<Category> listAllCategory();
    
    public List<Category> listAllCategoryActive();

    public Category createCategory(Category categoryReceived);

    public Category updateCategory(Category categoryDB, Category categoryReceived);

    public Category deleteCategory(Long id);
}
