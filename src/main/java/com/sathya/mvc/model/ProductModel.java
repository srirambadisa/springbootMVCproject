package com.sathya.mvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel 
{
	private String name;
	private double price;
	private int quantity;
	private String brand;
	private String madeIn;
}
