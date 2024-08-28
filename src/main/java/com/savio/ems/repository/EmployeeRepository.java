package com.savio.ems.repository;

import com.savio.ems.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Override
    Page<Employee> findAll(Pageable pageable);
}
