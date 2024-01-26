package serviplus.sp_back.service;

import java.util.List;

import serviplus.sp_back.entity.Category;

public interface ICategoryService {

    public Category getCategory(Long id);

    public List<Category> listAllCategory();

    public Category createCategory(Category category);

    public Category updateCategory(Category category);

    public Category deleteCategory(Long id);
}
