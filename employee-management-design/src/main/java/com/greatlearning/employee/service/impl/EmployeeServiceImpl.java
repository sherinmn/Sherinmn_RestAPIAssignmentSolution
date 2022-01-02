package com.greatlearning.employee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.greatlearning.employee.entity.Employee;
import com.greatlearning.employee.entity.Role;
import com.greatlearning.employee.entity.User;
import com.greatlearning.employee.repository.EmployeeRepository;
import com.greatlearning.employee.repository.RoleRepository;
import com.greatlearning.employee.repository.UserRepository;
import com.greatlearning.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	BCryptPasswordEncoder bcryptEncoder;

	@Override
	public List<Employee> findAll() {

		return employeeRepository.findAll();
	}

	@Override
	public Employee findById(int theId) {
		return employeeRepository.getById(theId);
	}

	@Override
	public void save(Employee theEmployee) {

		employeeRepository.save(theEmployee);
	}

	@Override
	public void deleteById(int theId) {

		employeeRepository.deleteById(theId);
	}

	@Override
	public List<Employee> searchByFirstName(String firstName) {
		return employeeRepository.findByFirstNameContainsAllIgnoreCase(firstName);
	}

	@Override
	public List<Employee> sortByFirstNameAsc() {
		return employeeRepository.findAllByOrderByFirstNameAsc();
	}

	@Override
	public User saveUser(User user) {

		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public Role saveRole(Role role) {

		return roleRepository.save(role);
	}

}
