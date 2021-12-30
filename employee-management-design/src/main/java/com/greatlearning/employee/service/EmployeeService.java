package com.greatlearning.employee.service;

import org.springframework.stereotype.Service;

import com.greatlearning.employee.entity.Employee;
import com.greatlearning.employee.entity.Role;
import com.greatlearning.employee.entity.User;

import java.util.List;

@Service
public interface EmployeeService {
	
	public List<Employee> findAll();

    public Employee findById(int theId);

    public void save(Employee theEmployee);

    public void deleteById(int theId);

    public List<Employee> searchByFirstName(String firstName);

    public List<Employee> sortByFirstNameAsc();

    public User saveUser(User user);

    public Role saveRole(Role role);

}
