package com.ganesh.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Products {
	
	
	@Id
	private Integer id;
	
	private String name;
	
	private Double price;
	
	private Integer quantity;
	
	private String imageName;

}
