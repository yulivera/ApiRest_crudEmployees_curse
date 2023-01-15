package com.sprintBootrestApi.restCrud.repository;

import com.sprintBootrestApi.restCrud.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
