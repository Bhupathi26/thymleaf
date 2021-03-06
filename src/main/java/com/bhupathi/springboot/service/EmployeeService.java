package com.bhupathi.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.bhupathi.springboot.model.Employee;
//Code is added
public interface EmployeeService {
	List<Employee>getAllEmployees();
	void saveEmployee(Employee employee);
	Employee getEmployeeById(long id);
	void deleteEmployeeById(long id);
	Page<Employee>findPaginated(int pageNo,int pageSize,String sortFied,String sortDirection);
	

}
