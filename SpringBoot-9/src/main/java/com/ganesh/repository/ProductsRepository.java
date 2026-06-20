package com.ganesh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ganesh.entity.Products;

public interface ProductsRepository extends JpaRepository<Products, Integer>{

}
