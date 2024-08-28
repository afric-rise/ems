package com.savio.ems.service;

import com.savio.ems.dto.EmployeeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    Page<EmployeeDto> getAllEmployees(Pageable pageable);
    EmployeeDto getEmployee(Long id);
    EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto);
    String deleteEmployee(Long id);
}
