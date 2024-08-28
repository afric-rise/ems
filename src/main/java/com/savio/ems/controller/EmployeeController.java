package com.savio.ems.controller;

import com.savio.ems.dto.EmployeeDto;
import com.savio.ems.exceptions.ResourceNotFoundException;
import com.savio.ems.response.ResponseFormat;
import com.savio.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<ResponseFormat<EmployeeDto>> createEmployee(@Validated @RequestBody EmployeeDto employeeDto) {
        try {
            EmployeeDto employeeDto1 = employeeService.createEmployee(employeeDto);

            ResponseFormat<EmployeeDto> responseFormat = new ResponseFormat<>();
            responseFormat.setCode(HttpStatus.CREATED.value());
            responseFormat.setError(false);
            responseFormat.setMessage("Employee created Successfully");
            responseFormat.setData(employeeDto1);

            return ResponseEntity.status(HttpStatus.CREATED).body(responseFormat);
        } catch (ResourceNotFoundException ex) {

            ResponseFormat<EmployeeDto> responseFormat = new ResponseFormat<>();
            responseFormat.setCode(HttpStatus.BAD_REQUEST.value());
            responseFormat.setError(true);
            responseFormat.setMessage(ex.getMessage());
            responseFormat.setData(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseFormat);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseFormat<Page<EmployeeDto>>> getAllEmployees(Pageable pageable) {
        try{
            Page<EmployeeDto> employeeDtoList = employeeService.getAllEmployees(pageable);

            ResponseFormat<Page<EmployeeDto>> responseFormat = new ResponseFormat<>();
            responseFormat.setCode(HttpStatus.OK.value());
            responseFormat.setError(false);
            responseFormat.setMessage("Successfully retrieved all employees");
            responseFormat.setData(employeeDtoList);

            return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
        } catch (ResourceNotFoundException ex) {

            ResponseFormat<Page<EmployeeDto>> responseFormat = new ResponseFormat<>();
            responseFormat.setCode(HttpStatus.NOT_FOUND.value());
            responseFormat.setError(true);
            responseFormat.setMessage(ex.getMessage());
            responseFormat.setData(null);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseFormat);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseFormat<EmployeeDto>> getEmployee(@PathVariable Long id){
        try{
            EmployeeDto employeeDto = employeeService.getEmployee(id);

            ResponseFormat<EmployeeDto> responseFormat = new ResponseFormat<>();
            responseFormat.setCode(HttpStatus.OK.value());
            responseFormat.setError(false);
            responseFormat.setMessage("Successfully retrieved an employee");
            responseFormat.setData(employeeDto);

            return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
        } catch (ResourceNotFoundException ex) {
            ResponseFormat<EmployeeDto> responseFormat = new ResponseFormat<>();
            responseFormat.setCode(HttpStatus.NOT_FOUND.value());
            responseFormat.setError(true);
            responseFormat.setMessage(ex.getMessage());
            responseFormat.setData(null);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseFormat);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ResponseFormat<EmployeeDto>> updateEmployee(
            @PathVariable Long id,
            @Validated @RequestBody EmployeeDto employeeDto) {
        try{
            EmployeeDto employeeDto1 = employeeService.updateEmployee(id, employeeDto);

            ResponseFormat<EmployeeDto> responseFormat = new ResponseFormat<>();
            responseFormat.setCode(HttpStatus.OK.value());
            responseFormat.setError(false);
            responseFormat.setMessage("Successfully updated employee details");
            responseFormat.setData(employeeDto1);

            return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
        } catch (ResourceNotFoundException ex) {
            ResponseFormat<EmployeeDto> responseFormat = new ResponseFormat<>();
            responseFormat.setCode(HttpStatus.NOT_FOUND.value());
            responseFormat.setError(true);
            responseFormat.setMessage(ex.getMessage());
            responseFormat.setData(null);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseFormat);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseFormat<String>> deleteEmployee(@PathVariable Long id) {
        try{
            String message = employeeService.deleteEmployee(id);

            ResponseFormat<String> responseFormat = new ResponseFormat<>();
            responseFormat.setCode(HttpStatus.OK.value());
            responseFormat.setError(false);
            responseFormat.setMessage(message);
            responseFormat.setData(null);

            return ResponseEntity.status(HttpStatus.OK).body(responseFormat);
        } catch (ResourceNotFoundException ex){
            ResponseFormat<String> responseFormat = new ResponseFormat<>();
            responseFormat.setCode(HttpStatus.NOT_FOUND.value());
            responseFormat.setError(true);
            responseFormat.setMessage(ex.getMessage());
            responseFormat.setData(null);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseFormat);
        }
    }
}
