package com.firstproject.employee.service;


import com.firstproject.employee.exception.EmployeeNotFoundException;
import com.firstproject.employee.model.Employee;
import com.firstproject.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;


    public List<Employee> getEmployee(String name){
        if (name == null){
            return employeeRepository.findAll();
        }
        else {
            return employeeRepository.findAllByName(name);
        }

    }


    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee newEmployee) {

        return employeeRepository.save(newEmployee);
    }

    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    public Employee getEmployeeById(int id) {
      return employeeRepository.findById(id)
        .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id "+id)); //Bulamazsa hata fırlatıyo
    }

    public void updateEmployee(int id, Employee newEmployee) {
        Employee oldEmployee= getEmployeeById(id);
        oldEmployee.setName(newEmployee.getName());
        oldEmployee.setDepartment(newEmployee.getDepartment());
        oldEmployee.setSalary(newEmployee.getSalary());
        employeeRepository.save(oldEmployee);
    }

   /* public Optional<Employee> findEmployeeById(Integer employeeId){
        return employeeRepository.findById(employeeId);
    }*/
}
