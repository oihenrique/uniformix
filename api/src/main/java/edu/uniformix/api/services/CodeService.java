package edu.uniformix.api.services;

import edu.uniformix.api.repositories.BatchRepository;
import edu.uniformix.api.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CodeService {
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private BatchRepository batchRepository;

    public String generateCode(char initialCodeCharacter) {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        code.append(initialCodeCharacter);

        for (int i = 0; i < 5; i++) {
            int digit = random.nextInt(10);
            code.append(digit);
        }

        return code.toString();
    }

    public Boolean validateSupplyCode(String code) {
        try {
            return supplierRepository.existsByCode(code);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean validateBatchCode(String code) {
        try {
            return batchRepository.existsByCode(code);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }
}
