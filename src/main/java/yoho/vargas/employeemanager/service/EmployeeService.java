package yoho.vargas.employeemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yoho.vargas.employeemanager.exception.UserNotFoundException;
import yoho.vargas.employeemanager.model.Employee;
import yoho.vargas.employeemanager.repo.EmployeeRepo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee addEmployee(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepo.save(employee);
    }

    public List<Employee> findAllEmployees(){
        return employeeRepo.findAll();
    }

    public Employee updateEmployee(Employee employee){
        Optional<Employee> e = employeeRepo.findById(employee.getId());
        if(e.isPresent()){
            e.get().setName(employee.getName());
            e.get().setEmail(employee.getEmail());
            e.get().setEmployeeCode(employee.getEmployeeCode());
            e.get().setPhone(employee.getPhone());
            e.get().setImageUrl(employee.getImageUrl());
            e.get().setJobTitle(employee.getJobTitle());
            employeeRepo.flush();
            return e.get();
        }
        return e.orElseThrow(() -> new UserNotFoundException("User by id "+ employee.getId()+" not found"));
    }

    public Employee findEmployById(Long id){
        return employeeRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User by id "+id+" not found"));
//        return employeeRepo.findEmployById(id).orElseThrow(() -> new UserNotFoundException("User by id "+id+" not found"));
    }

    public void deleteEmployee(Long id){
        employeeRepo.deleteById(id);
    }
}
