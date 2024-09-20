package com.example.market_api.controller;

import com.amazonaws.cache.Cache;
import com.example.market_api.common.ResponseObject;
import com.example.market_api.data.Category.CategoryView;
import com.example.market_api.data.Category.CreateCategory;
import com.example.market_api.data.Category.EditCategory;
import com.example.market_api.entity.Category;
import com.example.market_api.service.CategoryService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Log4j2
@RequestMapping("${base.url}/category")
@Controller
@RestController
@AllArgsConstructor

public class CategoryController {
    private final CategoryService categoryService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
            @ApiResponse(responseCode = "404", description = "Clients not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseObject<Category>> addCategory(@RequestBody CreateCategory createCategory) {
        ResponseObject<Category> responseObject = new ResponseObject<>();
        Category createdCategory = categoryService.createCategory(createCategory);
        responseObject.setData(createdCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseObject);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
            @ApiResponse(responseCode = "404", description = "Clients not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
    })
    @GetMapping("getById/{id}")
    public ResponseEntity<ResponseObject<Category>> getCategoryById(@PathVariable Long id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent()) {
            ResponseObject<Category> responseObject = new ResponseObject<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), category.get());
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        } else {
            ResponseObject<Category> responseObject = new ResponseObject<>(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObject);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
            @ApiResponse(responseCode = "404", description = "Clients not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
    })
    @PutMapping("updateCategory/{id}")
    public ResponseEntity<ResponseObject<Category>> editCategory(@PathVariable Long id, @RequestBody EditCategory editCategory) {
        Category updateCategory = categoryService.updateCategory(id, editCategory);
        if (updateCategory != null) {
            ResponseObject<Category> responseObject = new ResponseObject<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), updateCategory);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        } else {
            ResponseObject<Category> responseObject = new ResponseObject<>(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObject);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
            @ApiResponse(responseCode = "404", description = "Clients not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject<Void>> deleteCategory(@PathVariable Long id) {
        if (categoryService.deleteCategory(id)) {
            ResponseObject<Void> responseObject = new ResponseObject<>(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.getReasonPhrase(), null);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseObject);
        } else {
            ResponseObject<Void> responseObject = new ResponseObject<>(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObject);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
            @ApiResponse(responseCode = "404", description = "Clients not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
    })
    @GetMapping
    public ResponseEntity<ResponseObject<Page<CategoryView>>> getAllCategorys(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<CategoryView> categoryViews = categoryService.getAllCategorys(page, size);
        ResponseObject<Page<CategoryView>> responseObject = new ResponseObject<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), categoryViews);
        return ResponseEntity.ok(responseObject);
    }

}

