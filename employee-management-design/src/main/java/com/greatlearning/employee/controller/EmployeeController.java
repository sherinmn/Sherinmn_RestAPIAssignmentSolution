package com.greatlearning.employee.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatlearning.employee.entity.Employee;
import com.greatlearning.employee.entity.Role;
import com.greatlearning.employee.entity.User;
import com.greatlearning.employee.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

/*@Slf4j*/
@Controller
@RequestMapping("/")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/*
	 * @Autowired public EmployeeController(EmployeeService theEmployeeService) {
	 * employeeService = theEmployeeService; }
	 */

	@PostMapping("/user")
	public User saveUser(@RequestBody User user) {
		return employeeService.saveUser(user);
	}

	@PostMapping("/role")
	public Role saveRole(@RequestBody Role role) {
		return employeeService.saveRole(role);
	}

	@RequestMapping("/list")
	public String listEmployees(Model theModel) {
		/*
		 * Authentication authentication =
		 * SecurityContextHolder.getContext().getAuthentication(); Collection<? extends
		 * GrantedAuthority> currentPrincipalName = authentication.getAuthorities();
		 * System.out.println(currentPrincipalName);
		 */
		List<Employee> employees = employeeService.findAll();
		theModel.addAttribute("Employee", employees);
		return "list-employees";
		/* return "redirect:/list"; */
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Employee employee = new Employee();
		theModel.addAttribute("Employee", employee);
		return "employee-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("EmployeeId") int id, Model theModel) {
		Employee employee = employeeService.findById(id);
		theModel.addAttribute("Employee", employee);
		return "employee-form";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("EmployeeId") int id) {
		Employee employee = employeeService.findById(id);
		employeeService.deleteById(id);
		return "redirect:/list";

	}

	@PostMapping("/save")
	public String save(@RequestParam("id") int id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("email") String email) {
		System.out.println("Saving employee with Id: " + id);
		Employee employee;
		if (id != 0) {
			employee = employeeService.findById(id);
			employee.setFirstName(firstName);
			employee.setLastName(lastName);
			employee.setEmail(email);
		} else {
			employee = new Employee(firstName, lastName, email);
		}
		employeeService.save(employee);

		return "redirect:/list";

	}

	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() + ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", "You do not have permission to access this page!");
		}

		model.setViewName("403");
		return model;

	}
	
	@RequestMapping(value = "/logout")
	public ModelAndView logout(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() + ", you have successfully logged out");
		} else {
			model.addObject("msg", "you have successfully logged out!");
		}

		model.setViewName("403");
		return model;

	}

	@GetMapping("/search")
	public String search(@RequestParam("name") String name, Model theModel) {
		if (name.trim().isEmpty())
			return "redirect:/list";
		else {
			List<Employee> theEmployees = employeeService.searchByFirstName(name);
			theModel.addAttribute("Employee", theEmployees);
			return "list-employees";
		}
	}

	@GetMapping("/sort")
	public String sortByFirstName(Model theModel) {
		//List<Employee> theEmployees = employeeService.sortByFirstNameAsc();
		theModel.addAttribute("Employee", employeeService.sortByFirstNameAsc());
		return "list-employees";
	}

}
