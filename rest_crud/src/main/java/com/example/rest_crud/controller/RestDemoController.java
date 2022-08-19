package com.example.rest_crud.controller;

import com.example.rest_crud.entity.FinancialYear;
import com.example.rest_crud.payload.ApiResponse;
import com.example.rest_crud.service.FinancialYearServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/financial_years")
public class RestDemoController {

    private final FinancialYearServiceImpl financialYearService;

    @Autowired
    public RestDemoController(FinancialYearServiceImpl financialYearService) {
        this.financialYearService = financialYearService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<FinancialYear>>> getAll() {
        List<FinancialYear> all = financialYearService.findAll();
        return new ResponseEntity<>(new ApiResponse<>(all, "success", HttpStatus.OK.value()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<FinancialYear>> findById(@PathVariable Integer id) {
        FinancialYear byId = financialYearService.findById(id);
        return new ResponseEntity<>(new ApiResponse<>(byId, "success", HttpStatus.OK.value()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<FinancialYear>> save(@RequestBody FinancialYear financialYear) {
        financialYear.setId(0);
        FinancialYear save = financialYearService.save(financialYear);
        return new ResponseEntity<>(new ApiResponse<>(save, "success", HttpStatus.ACCEPTED.value()), HttpStatus.ACCEPTED);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<FinancialYear>> update(@RequestBody FinancialYear financialYear) {
        FinancialYear save = financialYearService.save(financialYear);
        return new ResponseEntity<>(new ApiResponse<>(save, "success", HttpStatus.ACCEPTED.value()), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        financialYearService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
