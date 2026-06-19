package com.ganesh.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ganesh.entity.Products;


@Controller
public class WelcomeController {
	
	
	@GetMapping({"/welcome","/home"})
	public String welcome(Model model) {
		
		model.addAttribute("msg", "Good Morning");
		model.addAttribute("names", List.of("Ganesh", "Ram", "Shyam", "Hari"));
		
		return "welcome";
	}
	
	
	@GetMapping("/product-form")
	public String productForm(Model model) {
		
		Products product=new Products();
		product.setName("Ganesh");
		
		model.addAttribute("product", product);
		
		
		return "product-form";
	}

}
