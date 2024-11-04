package com.firstproject.employee.repository;

import com.firstproject.employee.model.Employee;

import com.firstproject.employee.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface EmployeeRepository extends MongoRepository<Employee,Integer> {


    List<Employee> findAllByName(String name);
    
}

