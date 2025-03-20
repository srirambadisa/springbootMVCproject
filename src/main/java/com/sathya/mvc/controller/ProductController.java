package com.sathya.mvc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sathya.mvc.entity.ProductEntity;
import com.sathya.mvc.model.ProductModel;
import com.sathya.mvc.service.ProductService;

@Controller
public class ProductController 
{
	@Autowired
	ProductService productService;

	@GetMapping("/wish") 
	public String greet() 
	{ 
		return "file1"; 
	}

	@GetMapping("/emptyform") 
	public String getProduct() 
	{ 
		return "ProductForm";
	}

	@GetMapping("/defaultform") 
	public String getProductForm(Model model) {
		ProductEntity product=new ProductEntity(); 
		product.setBrand("APPLE");
		product.setMadeIn("India");

		model.addAttribute("product",product); 
		return "defaultForm";

	}
	@PostMapping("/saveproduct")
	public String saveProductForm(@ModelAttribute ProductModel productModel)
	{
		productService.saveProductData(productModel);
		return "success";
	}

	@GetMapping("/viewProducts")
	public String viewProducts(Model model)
	{
		List<ProductEntity> products=productService.viewProducts();
		model.addAttribute("products", products);
		return "viewProducts";
	}
	
	@GetMapping("/viewOne/{id}")
	public String viewOne(@PathVariable long id,Model model)
	{
		Optional<ProductEntity> optionalProduct=productService.viewOne(id);
		if(optionalProduct.isPresent())
		{
			ProductEntity product=optionalProduct.get();
			model.addAttribute("product", product);
		}
		else
		{
			model.addAttribute("msg", "Error: no product found with "+id);
		}
		
		return "viewOne";
	}
	
	@GetMapping("/deleteOne/{id}")
	public String deleteOne(@PathVariable("id") long id)
	{
		productService.deleteOne(id);
		return "redirect:/viewProducts";
	}
	
	@GetMapping("/editOne/{id}")
	public String editOne(@PathVariable long id,Model model)
	{
		ProductModel productModel=productService.editOne(id);
		model.addAttribute("productModel", productModel);
		return "editOne";
	}
	

}
