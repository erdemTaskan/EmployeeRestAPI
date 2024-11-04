package com.firstproject.employee.controller;


import com.firstproject.employee.exception.EmployeeNotFoundException;
import com.firstproject.employee.model.Employee;
import com.firstproject.employee.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/v1/employee")
@AllArgsConstructor
public class EmployeeController {



    private final EmployeeService employeeService;
   // private static final List<Employee> employees= new ArrayList<>();



  /*  public EmployeeController(){
        if (employees.isEmpty()){
            Employee employee1=new Employee(1,"Erdem","IT",30000);
            Employee employee2=new Employee(2,"Elif","Tasarım",45000);
            Employee employee3=new Employee(3,"Eda","Sales",25000);
            Employee employee4=(new Employee(4,"Yuşa","Accounting",23000));
            Employee employee5=new Employee(5,"Fatih","Accounting",23000);




            employees.add(employee1);
            employees.add(employee2);
            employees.add(employee3);
            employees.add(employee4);
            employees.add(employee5);

        }

    } */

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployeeList(@RequestParam(required = false) String name){

        return new ResponseEntity<>(employeeService.getEmployee(name),OK);
    }

    @GetMapping("/{id}") // Employee bulmak için
    public ResponseEntity<Employee> getEmployee(@PathVariable int id){

            return new ResponseEntity<>(getEmployeebyId(id),OK);

    }

    @PostMapping //Eklemek için
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee newEmployee){



        return new ResponseEntity<>(employeeService.createEmployee(newEmployee),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")  //Güncellemek için
    public ResponseEntity<Void> getEmployee(@PathVariable int id , @RequestBody Employee newEmployee){
            employeeService.updateEmployee(id,newEmployee);

            return new ResponseEntity<>(OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(OK);
    }

    private Employee getEmployeebyId(int id){  //Kod tekrarından kaçmak için employee filtrelemek amaçlı
      return employeeService.getEmployeeById(id);


    }
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> HandleEmployeeNotFoundException(EmployeeNotFoundException ex){
            return new ResponseEntity<>(ex.getMessage(),NOT_FOUND);
    }


}
