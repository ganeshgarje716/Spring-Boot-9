package com.ganesh.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ganesh.entity.Products;
import com.ganesh.repository.ProductsRepository;


@Controller
public class WelcomeController {
	
	@Autowired
	ProductsRepository productsRepository;
	
	
	@GetMapping({"/welcome","/home"})
	public String welcome(Model model) {
		
		model.addAttribute("msg", "Good Morning");
		model.addAttribute("names", List.of("Ganesh", "Ram", "Shyam", "Hari"));
		
		return "welcome";
	}
	
	
	@GetMapping("/product-form")
	public String productForm(Model model) {
		
		Products product=new Products();
		
		model.addAttribute("product", product);
		
		return "product-form";
	}
	
	
	@PostMapping({"/save-product","/update-product"})
	public String saveProduct(Products product,
	        @RequestParam("image") MultipartFile file,
	        Model model) throws Exception {

	    String uploadPath ="C:\\Users\\Ganesh\\OneDrive\\Desktop\\uploadProducts\\";

	    File folder = new File(uploadPath);

	    if (!folder.exists()) {
	        folder.mkdirs();
	    }

	    if (!file.isEmpty()) {

	        String fileName =System.currentTimeMillis() + "_" +file.getOriginalFilename();

	        product.setImageName(fileName);

	        file.transferTo(new File(folder, fileName));
	    }

	    productsRepository.save(product);

	    model.addAttribute("product", product);

	    return "redirect:/all-products";
	}
	
	
	@GetMapping("/all-products")
	public String allProducts(Model model) {
		
		model.addAttribute("products", productsRepository.findAll());
		
		return "all-products";
	}
	
	
	@GetMapping("/all-products/{id}")
	public String deleteProduct(@PathVariable Integer id) {
		
		productsRepository.deleteById(id);
		
		return "redirect:/all-products";
	}
	
	
	@GetMapping("/update-product/{id}")
	public String updateProduct(@PathVariable Integer id, Model model) {
		
		model.addAttribute("product", productsRepository.findById(id).orElse(null));
		
		return "/update-product";
	}
	
	
	
	
	
	
	

}
