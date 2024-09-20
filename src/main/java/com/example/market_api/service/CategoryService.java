package com.example.market_api.service;

import com.example.market_api.data.Category.CategoryView;
import com.example.market_api.data.Category.CreateCategory;
import com.example.market_api.data.Category.EditCategory;
import com.example.market_api.data.Product.EditProduct;
import com.example.market_api.data.Product.ProductView;
import com.example.market_api.entity.Category;
import com.example.market_api.entity.Product;
import com.example.market_api.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;

    public Category createCategory(CreateCategory createCategory) {
        String methodName = "createCategory";
        log.info("Execute method->"+methodName);
        Category category = new Category();
        try{
           category.setName(createCategory.getName());
           category.setDescription(createCategory.getDescription());

           return categoryRepository.save(category);

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category updateCategory(Long id, EditCategory editCategory) {
        if (categoryRepository.existsById(id)) {
            Category category = new Category();
            try {
                category.setId(id);
                category.setDescription(editCategory.getDescription());
                category.setName(editCategory.getName());

                return categoryRepository.save(category);
            } catch (Exception e) {
                log.info("not fount with id " + id);
                throw new RuntimeException(e.getLocalizedMessage());
            }
        }
        return null;
    }

    public boolean deleteCategory(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public Page<CategoryView> getAllCategorys(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        return categoryPage.map(category -> new CategoryView(
                category.getId(),
                category.getName(),
                category.getDescription()
        ));
    }
}
