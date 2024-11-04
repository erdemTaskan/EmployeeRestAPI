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


    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployeeList(@RequestParam(required = false) String name){

        return new ResponseEntity<>(employeeService.getEmployee(name),OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable int id){

            return new ResponseEntity<>(getEmployeebyId(id),OK);

    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee newEmployee){



        return new ResponseEntity<>(employeeService.createEmployee(newEmployee),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> getEmployee(@PathVariable int id , @RequestBody Employee newEmployee){
            employeeService.updateEmployee(id,newEmployee);

            return new ResponseEntity<>(OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(OK);
    }

    private Employee getEmployeebyId(int id){
      return employeeService.getEmployeeById(id);


    }
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> HandleEmployeeNotFoundException(EmployeeNotFoundException ex){
            return new ResponseEntity<>(ex.getMessage(),NOT_FOUND);
    }


}
