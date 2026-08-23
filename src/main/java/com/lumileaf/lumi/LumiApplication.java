package com.lumileaf.lumi;

import com.lumileaf.lumi.model.Supplier;
import com.lumileaf.lumi.repository.SupplierRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class LumiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LumiApplication.class, args);
    }

    @Bean
    CommandLineRunner loadTestSuppliers(SupplierRepository supplierRepository) {
        return args -> {
            if (supplierRepository.count() == 0) {
                supplierRepository.save(new Supplier(null,"Test Amigos Supplier","90080","Amigos","1234567890"));
                supplierRepository.save(new Supplier(null,"Test Estate Supplier 1","90001","Estate","1111111111"));
                // ... (rest of your suppliers)
                System.out.println("Sample Suppliers Loaded Successfully!");
            }
        };
    }
}