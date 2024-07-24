package com.example.market_api.controller;

import com.example.market_api.common.ResponseObject;
import com.example.market_api.data.Supplier.CreateSupplier;
import com.example.market_api.data.Supplier.EditSupplier;
import com.example.market_api.data.Supplier.SupplierView;
import com.example.market_api.entity.Supplier;
import com.example.market_api.service.SupplierService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RestController
@RequestMapping("/supplier")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
            @ApiResponse(responseCode = "404", description = "Clients not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseObject<Supplier>> addSupplier(@RequestBody CreateSupplier createSupplier) {
        ResponseObject<Supplier> responseObject = new ResponseObject<>();
        Supplier createdSuplier = supplierService.createSupplier(createSupplier);
        responseObject.setData(createdSuplier);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseObject);
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<ResponseObject<Supplier>> getSupplierById(@PathVariable Long id) {
        Optional<Supplier> supplier= supplierService.getSupplierById(id);
        if (supplier.isPresent()) {
            ResponseObject<Supplier> responseObject = new ResponseObject<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), supplier.get());
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        } else {
            ResponseObject<Supplier> responseObject = new ResponseObject<>(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObject);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid data supplied"),
            @ApiResponse(responseCode = "404", description = "Clients not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
    })
    @PutMapping("updateSupplier/{id}")
    public ResponseEntity<ResponseObject<Supplier>> editProduct(@PathVariable Long id, @RequestBody EditSupplier editSupplier) {
        Supplier updateSpplier = supplierService.updateSpplier(id,editSupplier );
        if (updateSpplier != null) {
            ResponseObject<Supplier> responseObject = new ResponseObject<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), updateSpplier);
            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
        } else {
            ResponseObject<Supplier> responseObject = new ResponseObject<>(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), null);
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
    public ResponseEntity<ResponseObject<Void>> deleteSupplier(@PathVariable Long id) {
        if (supplierService.deleteSupplier(id)) {
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
    public ResponseEntity<ResponseObject<Page<SupplierView>>> getAllSuppliers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<SupplierView> suppliersviews = supplierService.getAllSuppliers(page, size);
        ResponseObject<Page<SupplierView>> responseObject = new ResponseObject<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), suppliersviews);
        return ResponseEntity.ok(responseObject);
    }


}
