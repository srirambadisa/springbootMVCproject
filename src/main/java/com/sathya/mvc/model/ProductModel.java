package com.sathya.mvc.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel 
{
	@NotBlank(message = "Product name cannot be blank")
    @Size(min = 5, max = 55, message = "Product name must be between 5 and 55 characters")
	private String name;
	
	@Positive(message = "Price must be greater than zero")
	private double price;
	
	@Min(value = 1, message = "Quantity must be at least 1")
	private int quantity;
	
	private String brand;
	private String madeIn;
}
