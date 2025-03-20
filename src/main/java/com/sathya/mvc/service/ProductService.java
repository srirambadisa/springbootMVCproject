package com.sathya.mvc.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathya.mvc.entity.ProductEntity;
import com.sathya.mvc.model.ProductModel;
import com.sathya.mvc.repository.ProductRepository;

@Service
public class ProductService 
{
	@Autowired
	ProductRepository productRepository;
	
	public void saveProductData(ProductModel productModel)
	{
		ProductEntity productEntity=new ProductEntity();
		productEntity.setName(productModel.getName());
		productEntity.setPrice(productModel.getPrice());
		productEntity.setQuantity(productModel.getQuantity());
		productEntity.setBrand(productModel.getBrand());
		
		double price=productModel.getPrice();
		int quantity=productModel.getQuantity();
		
		double totalAmount=price*quantity;
		double tax=totalAmount*18/100;
		
		productEntity.setTotalAmount(totalAmount);
		productEntity.setTaxAmount(tax);
		productEntity.setCreatedAt(LocalDateTime.now());
		productEntity.setCreatedBy(System.getProperty("user.name"));
		productEntity.setMadeIn(productModel.getMadeIn());
		productRepository.save(productEntity);
	}
	
	
	public List<ProductEntity> viewProducts() 
	{
		List<ProductEntity> products=productRepository.findAll();
		return products;
	}
	
	
	public Optional<ProductEntity> viewOne(long id) 
	{
		Optional<ProductEntity> optionalProduct=productRepository.findById(id);
		return optionalProduct;
		
	}
	
	public void deleteOne(long id) 
	{
		productRepository.deleteById(id);
	}


	public ProductModel editOne(long id) 
	{
		ProductEntity productEntity=productRepository.findById(id).get();
		ProductModel productModel=new ProductModel();
		productModel.setName(productEntity.getName());
		productModel.setPrice(productEntity.getPrice());
		productModel.setQuantity(productEntity.getQuantity());
		productModel.setBrand(productEntity.getBrand());
		productModel.setMadeIn(productEntity.getMadeIn());
		
		return productModel;
		
	}



}
