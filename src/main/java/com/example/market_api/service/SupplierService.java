package com.example.market_api.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.example.market_api.data.Product.ProductView;
import com.example.market_api.data.Supplier.CreateSupplier;
import com.example.market_api.data.Supplier.EditSupplier;
import com.example.market_api.data.Supplier.SupplierView;
import com.example.market_api.entity.Product;
import com.example.market_api.entity.Supplier;
import com.example.market_api.exception.BadRequestApiException;
import com.example.market_api.exception.ResourceNotFoundException;
import com.example.market_api.repository.SupplierRepository;
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
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public Supplier createSupplier(CreateSupplier createSupplier) {
        String methodName = "createSupplier";
        log.info("successful execute " + methodName);

        Supplier supplier = new Supplier();

        try {
            supplier.setName(createSupplier.getName());
            supplier.setAddress(createSupplier.getAddress());
            supplier.setPhone(createSupplier.getPhone());
            supplier.setEmail(createSupplier.getEmail());

            return supplierRepository.save(supplier);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Failed to create supplier: " + e.getLocalizedMessage());
        }
    }


    public Optional<Supplier> getSupplierById(Long id) {
        return supplierRepository.findById(id);
    }

    public Supplier updateSpplier(Long id, EditSupplier editSupplier) {
        if (supplierRepository.existsById(id)) {
            Supplier supplier = new Supplier();
            try {
                supplier.setId(id);
                supplier.setName(editSupplier.getName());
                supplier.setAddress(editSupplier.getAddress());
                supplier.setPhone(editSupplier.getPhone());
                supplier.setEmail(editSupplier.getEmail());

                return supplierRepository.save(supplier);


            } catch (Exception e) {
                throw new RuntimeException(e.getLocalizedMessage());
            }
        }
        return null;
    }
    public boolean deleteSupplier(Long id) {
        if (supplierRepository.existsById(id)) {
            supplierRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public Page<SupplierView> getAllSuppliers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Supplier> supplierPage = supplierRepository.findAll(pageable);
        return supplierPage.map(supplier -> new SupplierView(
                supplier.getId(),
                supplier.getName(),
                supplier.getContactInfo(),
                supplier.getAddress(),
                supplier.getPhone(),
                supplier.getEmail()
        ));
    }

}
