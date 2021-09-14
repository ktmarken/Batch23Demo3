package com.apolis.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.apolis.beans.Product;

@Repository
public interface ProductRepository extends ElasticsearchRepository<Product, String>{

	Iterable<Product> findByProductName(String productName);
	Iterable<Product> findByProductDesc(String productDesc);
}
