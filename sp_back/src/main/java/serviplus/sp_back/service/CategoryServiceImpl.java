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
    public Category createCategory(Category category) {
        category.setStatus(false);
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public Category updateCategory(Category category) {
        Category categoryDB = getCategory(category.getId());
        if (categoryDB == null) {
            return null;
        }
        categoryDB.setName(category.getName());
        categoryDB.setImage(category.getImage());
        return categoryRepository.save(categoryDB);
    }

    @Override
    @Transactional
    public Category deleteCategory(Long id) {
        Category categoryDB = getCategory(id);
        if (categoryDB == null) {
            return null;
        }
        categoryDB.setStatus(true);
        return categoryRepository.save(categoryDB);
    }

}
