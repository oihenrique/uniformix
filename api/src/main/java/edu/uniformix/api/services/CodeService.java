package edu.uniformix.api.services;

import edu.uniformix.api.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CodeService {
    @Autowired
    private static SupplierRepository supplierRepository;

    public static String generateCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int digit = random.nextInt(10);
            code.append(digit);
        }

        return code.toString();
    }

    public static boolean validateSupplyCode(String code) {
        try {
            return supplierRepository.existsByCode(code);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean validateBatchCode(String code) {
        try {
            return supplierRepository.existsByCode(code);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }
}
