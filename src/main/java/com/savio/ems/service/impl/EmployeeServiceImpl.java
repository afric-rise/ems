package com.savio.ems.service.impl;

import com.savio.ems.dto.EmployeeDto;
import com.savio.ems.entity.Employee;
import com.savio.ems.exceptions.ResourceNotFoundException;
import com.savio.ems.mapper.EmployeeMapper;
import com.savio.ems.repository.EmployeeRepository;
import com.savio.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        try {
            Employee savedEmployee = employeeRepository.save(employee);
            return EmployeeMapper.mapToEmployeeDto(savedEmployee);
        } catch (DataIntegrityViolationException ex){
            throw new ResourceNotFoundException("Something went wrong");
        }
    }

    @Override
    public Page<EmployeeDto> getAllEmployees(Pageable pageable) {
         Page<Employee> employees = employeeRepository.findAll(pageable);
         return employees.map(EmployeeMapper::mapToEmployeeDto);
    }

    @Override
    public EmployeeDto getEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Employee with specified Id does not exist")
                );
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Employee with specified Id does not exist")
                );
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setSalary(employeeDto.getSalary());

        Employee updatedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
    }

    @Override
    public String deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Employee with specified Id does not exist")
                );
        employeeRepository.delete(employee);

        return "Employee is deleted successfully";
    }
}
