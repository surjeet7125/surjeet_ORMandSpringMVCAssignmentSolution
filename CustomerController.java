package com.greatLearning.customer.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.greatLearning.customer.service.CustomerService;
import com.greatLearning.customer.entity.Customer;
@Controller
	@RequestMapping("/customer")
	public class CustomerController {
		@Autowired
		private CustomerService customerService;
		// add mapping for "/list"
		@RequestMapping("/list")
		public String listBooks(Model theModel) {

			System.out.println("request recieved");
			// get Books from db
			List<Customer> theCustomer = customerService.findAll();
			// add to the spring model
			theModel.addAttribute("Customer", theCustomer);
			return "list-Customer";
		}
		@RequestMapping("/showFormForAdd")
		public String showFormForAdd(Model theModel) {
			// create model attribute to bind form data
			Customer theCustomer = new Customer();
			theModel.addAttribute("Customer", theCustomer);
			return "Customer-form";
		}
		@RequestMapping("/showFormForUpdate")
		public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
			Customer theCustomer = customerService.findById(theId);

			theModel.addAttribute("Customer", theCustomer);
			// send over to our form
		return "Customer-form";
}
		@PostMapping("/save")
		public String saveCustomer(@RequestParam("id") int id, @RequestParam("firstName") String firstName,
				@RequestParam("lastName") String lastName, @RequestParam("course") String course,
				@RequestParam("email") String country) {

			System.out.println(id);
			Customer theCustomer;
			if (id != 0) {
				theCustomer = customerService.findById(id);
				theCustomer.setFirstName(firstName);
				theCustomer.setLastName(lastName);
				theCustomer.setEmail(email);
		//		theCustomer.setCountry(country);
			} else
				theCustomer = new Customer(firstName, lastName, email);
			// save the Book
			customerService.save(theCustomer);

			// use a redirect to prevent duplicate submissions
			return "redirect:/customer/list";
	}
		@RequestMapping("/delete")
		public String delete(@RequestParam("customerId") int theId) {
			// delete the customer details
			customerService.deleteById(theId);
			// redirect to /customer/list
			return "redirect:/customer/list";
		}
	}