package com.sprintBootrestApi.restCrud.service.impl;

import com.sprintBootrestApi.restCrud.exception.ResourceNotFoundException;
import com.sprintBootrestApi.restCrud.model.Employee;
import com.sprintBootrestApi.restCrud.repository.EmployeeRepository;
import com.sprintBootrestApi.restCrud.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {

//        Optional<Employee> employee = employeeRepository.findById(id);
//        if(employee.isPresent()){
//            return employee.get();
//        }else{
//            throw new ResourceNotFoundException("Employee","id",id);
//        }

        return employeeRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Employee","id",id));

    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        // we need to checke employee with given id is exist in DB or not
        Employee exitingEmployee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee","id",id));
        exitingEmployee.setFirstName(employee.getFirstName());
        exitingEmployee.setLastName(employee.getLastName());
        exitingEmployee.setEmail(employee.getEmail());
        //save existing employee to DB
        employeeRepository.save(exitingEmployee);
        return exitingEmployee;
    }

    @Override
    public void deleteEmployee(long id) {

        //check whether a employee exist in a DB or not
        employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee","Id",id));
        employeeRepository.deleteById(id);
    }


}
