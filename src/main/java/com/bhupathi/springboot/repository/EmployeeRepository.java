package com.bhupathi.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhupathi.springboot.model.Employee;
//Code is added
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
