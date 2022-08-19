package com.example.rest_crud.dao;

import com.example.rest_crud.entity.FinancialYear;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialYearRepository extends JpaRepository<FinancialYear, Integer> {
}
