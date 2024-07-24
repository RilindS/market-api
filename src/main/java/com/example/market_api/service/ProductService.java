package com.example.market_api.service;

import com.example.market_api.data.Product.CreateProduct;
import com.example.market_api.data.Product.EditProduct;
import com.example.market_api.data.Product.ProductView;
import com.example.market_api.entity.Category;
import com.example.market_api.entity.Product;
import com.example.market_api.entity.Supplier;
import com.example.market_api.repository.CategoryRepository;
import com.example.market_api.repository.ProductRepository;
import com.example.market_api.repository.SupplierRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@AllArgsConstructor
@Setter
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;

    public Product createProduct(CreateProduct createProduct) {
        String methodName = "createProduct";
        log.info("Execute method->" + methodName);

        Product product = new Product();

        try {
            product.setName(createProduct.getName());
            product.setDescription(createProduct.getDescription());
            product.setPrice(createProduct.getPrice());

            if (createProduct.getSupplierIds() != null) {
                List<Supplier> suppliers = supplierRepository.findAllById(createProduct.getSupplierIds());
                product.setSuppliers(suppliers);
            }

            if(createProduct.getCategoryIds() != null) {
                List<Category> categories = categoryRepository.findAllById(createProduct.getCategoryIds());
                product.setCategories(categories);
            }

        } catch (Exception e) {
            throw new RuntimeException(e.getLocalizedMessage());

        }
        return productRepository.save(product);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product updateProduct(Long id, EditProduct editProduct) {
        if (productRepository.existsById(id)) {
            Product product = new Product();
            try {
                product.setId(id);
                product.setDescription(editProduct.getDescription());
                product.setPrice(editProduct.getPrice());
                product.setName(editProduct.getName());

                return productRepository.save(product);

            } catch (Exception e) {
                log.info("not fount with id " + id);
                throw new RuntimeException(e.getLocalizedMessage());
            }

        }
        return null;
    }

    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public Page<ProductView> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.map(product -> new ProductView(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        ));
    }




}
