package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.EmployeeDetails;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmpService {
	
	@Autowired
	private EmployeeRepository empRepo;
	
	public List<EmployeeDetails> getEmployeeDetails(){
		return empRepo.findAll() ;
	}
	
	public Optional<EmployeeDetails> getEmployeeById(Long id){
		
		return empRepo.findById(id);
	}
	
	public EmployeeDetails saveEmployee(EmployeeDetails emp) {
		
		return empRepo.save(emp);
		
	}
	
	public Optional<EmployeeDetails> updateEmployees(Long id, EmployeeDetails empDetails) {
        return empRepo.findById(id).map(emp -> {
            emp.setEmpName(empDetails.getEmpName());
            emp.setEmpAddress(empDetails.getEmpAddress());
            emp.setEmpSalary(empDetails.getEmpSalary());
            return empRepo.save(emp);
        });
    }

	public boolean deleteEmp(Long id) {
        return empRepo.findById(id).map(emp -> {
        	empRepo.delete(emp);
            return true;
        }).orElse(false);
	}

}
