package com.bhupathi.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bhupathi.springboot.model.Employee;
import com.bhupathi.springboot.service.EmployeeService;

//Code is added
@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	//display list of employees
	@GetMapping("/")
	public String viewHomePage(Model model)
	{
		
		return findPaginted(1,"firstName","asc",model);
	}
	
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model)
	{
		Employee employee=new Employee();
		model.addAttribute("employee",employee);
		return "new_employee";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee")Employee employee)
	{
		employeeService.saveEmployee(employee);
		return "redirect:/";
		
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id")long id,Model model)
	{
		Employee employee=employeeService.getEmployeeById(id);
		model.addAttribute("employee",employee);
		return "update_employee";
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable(value = "id")long id)
	{
		this.employeeService.deleteEmployeeById(id);
		return "redirect:/";
	}
	
	//Code is added
	@GetMapping("/page/{pageNo}")
	public String findPaginted(@PathVariable(value = "pageNo")int pageNo,
			@RequestParam("sortField")String sortField,
			@RequestParam("sortDir")String sortDir,
			Model model)
	{
		int pageSize=5;
		Page<Employee> page=employeeService.findPaginated(pageNo, pageSize,sortField,sortDir);
		List<Employee> listEmployees=page.getContent();
		model.addAttribute("currentPage",pageNo);
		model.addAttribute("totalPages",page.getTotalPages());
		model.addAttribute("totalItems",page.getTotalElements());
		model.addAttribute("sortField",sortField);
		model.addAttribute("sortDir",sortDir);
		model.addAttribute("reversSortDir",sortDir.equals("asc")?"desc":"asc");
		model.addAttribute("listEmployees",listEmployees);
		return "index";
	}
}
