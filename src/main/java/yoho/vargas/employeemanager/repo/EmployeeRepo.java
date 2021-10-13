package yoho.vargas.employeemanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import yoho.vargas.employeemanager.model.Employee;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    void deleteEmployeById(Long id);

    Optional<Employee> findEmployById(Long id);
}
