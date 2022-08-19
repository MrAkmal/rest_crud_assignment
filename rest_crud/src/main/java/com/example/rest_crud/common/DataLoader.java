package com.example.rest_crud.common;

import com.example.rest_crud.dao.FinancialYearRepository;
import com.example.rest_crud.entity.FinancialYear;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {



    @Value("${spring.sql.init.mode}")
    String initMode;

    private FinancialYearRepository financialYearRepository;

    @Autowired
    public DataLoader(FinancialYearRepository financialYearRepository) {
        this.financialYearRepository = financialYearRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (initMode.equals("always")) {

            FinancialYear financialYear = new FinancialYear(2022, true);
            FinancialYear financialYear1 = new FinancialYear(2023, false);
            FinancialYear financialYear2 = new FinancialYear(2016, false);

            financialYearRepository.save(financialYear);
            financialYearRepository.save(financialYear1);
            financialYearRepository.save(financialYear2);
        }
    }
}

