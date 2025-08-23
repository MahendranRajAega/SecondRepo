package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.EmployeeDetails;
import com.example.demo.service.EmpService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmpService empService;
	
	// GET all employee
	
	@GetMapping("/emps")
	public ResponseEntity<List<EmployeeDetails>> getAllEmpployees(){
		
		return ResponseEntity.ok(empService.getEmployeeDetails());
	}
	
	// GET employee based on ID
	
	@GetMapping("/emps/{id}")
	public ResponseEntity<String> getEmpById(@PathVariable Long id){
		
		return empService.getEmployeeById(id)
				.map(emp->ResponseEntity.ok(emp.toString()))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Employee not foun with ID="+id));
	}

	//create employee
	@PostMapping("/saveEmps")
	public ResponseEntity<String> createUser(@RequestBody EmployeeDetails employees){
		empService.saveEmployee(employees);
		return ResponseEntity.ok("Employee records saved successfully ");
	}
	
	//Update Emp
	
	@PutMapping("/updateEmp/{id}")
	 public ResponseEntity<String> updateEmployee(
	            @PathVariable Long id,
	            @RequestBody EmployeeDetails empDetails) {
	        return empService.updateEmployees(id, empDetails)
	                .map(emp->ResponseEntity.ok(emp.toString()))
	                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
							.body("Employee not foun with ID="+id));
	    }
	
	
	
	 @DeleteMapping("deleteEmp/{id}")
	 public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		 boolean deleted = empService.deleteEmp(id);
		    if (deleted) {
		        return ResponseEntity.ok("Employee deleted successfully with ID: " + id);
		    } else {
		        return ResponseEntity.status(HttpStatus.NOT_FOUND)
		                             .body("Employee not found with ID: " + id);
		    }
	    }
	
}
