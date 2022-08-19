package com.example.rest_crud.service;

import com.example.rest_crud.dao.FinancialYearRepository;
import com.example.rest_crud.entity.FinancialYear;
import com.example.rest_crud.exceptionhandler.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FinancialYearServiceImpl implements CrudService<FinancialYear, Integer> {

    private final FinancialYearRepository financialYearRepository;

    @Autowired
    public FinancialYearServiceImpl(FinancialYearRepository financialYearRepository) {
        this.financialYearRepository = financialYearRepository;
    }

    @Override
    public List<FinancialYear> findAll() {
        return financialYearRepository.findAll();
    }

    @Override
    public FinancialYear save(FinancialYear financialYear) {
        return financialYearRepository.save(financialYear);
    }

    @Override
    public void deleteById(Integer id) {

        if (financialYearRepository.existsById(id)) {
            financialYearRepository.deleteById(id);
        } else {
            throw new CustomException("FinancialYear not found with this id - " + id);
        }
    }

    @Override
    public FinancialYear findById(Integer id) {
        Optional<FinancialYear> optionalFinancialYear = financialYearRepository.findById(id);

        if (optionalFinancialYear.isPresent()) {
            return optionalFinancialYear.get();
        } else {
            throw new CustomException("FinancialYear not found with this id - " + id);
        }
    }
}
