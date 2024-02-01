package serviplus.sp_back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import serviplus.sp_back.entity.Category;
import serviplus.sp_back.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Category> listAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> listAllCategoryActive() {
        return categoryRepository.findByStatus(false);
    }

    @Override
    @Transactional
    public Category createCategory(Category categoryReceived) {
        Category categoryDB = new Category();
        categoryDB.setName(categoryReceived.getName());
        categoryDB.setImage(categoryReceived.getImage());
        categoryDB.setStatus(false);
        return categoryRepository.save(categoryDB);
    }

    @Override
    @Transactional
    public Category updateCategory(Category categoryDB, Category categoryReceived) {
        try {
            if (categoryDB == null) {
                throw new Exception("Category not found: " + categoryDB);
            }
            categoryDB.setName(categoryReceived.getName());
            categoryDB.setImage(categoryReceived.getImage());
            return categoryRepository.save(categoryDB);
        } catch (Exception e) {
            throw new RuntimeException("Error updating category", e);
        }

    }

    @Override
    @Transactional
    public Category deleteCategory(Long id) {
        try {
            Category categoryDB = getCategory(id);
            if (categoryDB == null) {
                throw new Exception("Category not found with id: " + id);
            }
            categoryDB.setStatus(true);
            return categoryRepository.save(categoryDB);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting category", e);
        }

    }

}
