package serviplus.sp_back.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import serviplus.sp_back.entity.Category;
import serviplus.sp_back.entity.CategoryDTO;
import serviplus.sp_back.entity.Image;
import serviplus.sp_back.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ImageServiceImpl imageServiceImpl;

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
    public Category createCategory(Category categoryReceived, MultipartFile file) {
        try {
            if (file != null && !file.isEmpty()) {
                Category categoryDB = new Category();
                categoryDB.setName(categoryReceived.getName());
                Image imageCategory = imageServiceImpl.saveImage(file);
                categoryDB.setImage(imageCategory);
                categoryDB.setStatus(false);
                return categoryRepository.save(categoryDB);
            } else {
                throw new IllegalArgumentException("La imagen recibida es nula o está vacía.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la imagen durante la creación de la categoría", e);
        }
    }

    @Override
    @Transactional
    public Category updateCategory(Category categoryReceived, MultipartFile imageReceived, Long idImage) {
        try {
            Optional<Category> optionalCategory = categoryRepository.findById(categoryReceived.getId());

            if (optionalCategory.isPresent()) {
                Category categoryUpdated = optionalCategory.get();
                categoryUpdated.setName(categoryReceived.getName());

                Image imageUpdated = imageServiceImpl.updateImage(imageReceived, idImage);
                categoryUpdated.setImage(imageUpdated);

                return categoryRepository.save(categoryUpdated);
            } else {
                throw new IllegalArgumentException("No se encontró la categoría con ID: " + categoryReceived.getId());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la categoría", e);
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

    public List<CategoryDTO> getAllCategoriesWithImagesDTO() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private CategoryDTO mapToDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());

        if (category.getImage() != null) {
            dto.setNameImage(category.getImage().getName());
            dto.setMime(category.getImage().getMime());
            dto.setContent(category.getImage().getContent());
        }

        return dto;
    }

}
