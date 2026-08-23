package com.lumileaf.lumi.service;

import com.lumileaf.lumi.model.Supplier;
import com.lumileaf.lumi.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> getSuppliersBySection(String section) {
        return supplierRepository.findBySection(section);
    }
}