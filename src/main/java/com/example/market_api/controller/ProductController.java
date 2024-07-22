package com.example.market_api.controller;

import com.example.market_api.common.ResponseObject;
import com.example.market_api.data.Product.CreateProduct;
import com.example.market_api.data.Product.EditProduct;
import com.example.market_api.data.Product.ProductView;
import com.example.market_api.entity.Product;
import com.example.market_api.service.ProductService;
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
@RequestMapping("${base.url}/product")
@Controller
@RestController
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
            @ApiResponse(responseCode = "404", description = "Clients not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseObject<Product>> addProduct(@RequestBody CreateProduct createProduct) {
        ResponseObject<Product> responseObject = new ResponseObject<>();
        Product createdProduct= productService.createProduct(createProduct);
        responseObject.setData(createdProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseObject);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
            @ApiResponse(responseCode = "404", description = "Clients not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
    })
    @GetMapping("getById/{id}")
    public ResponseEntity<ResponseObject<Product>> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            ResponseObject<Product> responseObject = new ResponseObject<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), product.get());
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        } else {
            ResponseObject<Product> responseObject = new ResponseObject<>(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObject);
        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
            @ApiResponse(responseCode = "404", description = "Clients not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
    })
    @PutMapping("updateProduct/{id}")
    public ResponseEntity<ResponseObject<Product>> editProduct(@PathVariable Long id, @RequestBody EditProduct editProduct) {
        Product updateProduct=productService.updateProduct(id,editProduct);
        if(updateProduct!=null){
            ResponseObject<Product> responseObject = new ResponseObject<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), updateProduct);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        } else {
            ResponseObject<Product> responseObject = new ResponseObject<>(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), null);
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
    public ResponseEntity<ResponseObject<Void>> deleteProduct(@PathVariable Long id) {
        if (productService.deleteProduct(id)) {
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
    public ResponseEntity<ResponseObject<Page<ProductView>>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<ProductView> productViews = productService.getAllProducts(page, size);
        ResponseObject<Page<ProductView>> responseObject = new ResponseObject<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), productViews);
        return ResponseEntity.ok(responseObject);
    }

}
