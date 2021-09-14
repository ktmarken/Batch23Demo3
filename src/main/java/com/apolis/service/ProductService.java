package com.apolis.service;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.apolis.beans.Product;
import com.apolis.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ElasticsearchRestTemplate elasticsearchRestTemplate;
	
	public Product create(Product product) {
		return productRepository.save(product);
	}

	public Iterable<Product> findAllProducts() {
		return productRepository.findAll();
	}
	
	public Iterable<Product> findProductByName(String name) {
		return productRepository.findByProductName(name);
	}
	
	public List<Product> findProductByDesc(String desc) {
		// search() is deprecated
//		return productRepository.search(QueryBuilders.matchQuery("productDesc", desc));
		
		NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchQuery("productDesc", desc)).build();
		
		SearchHits<Product> products = elasticsearchRestTemplate.search(searchQuery, Product.class, IndexCoordinates.of("product_it"));
		
		List<Product> productList = new ArrayList<Product>();
		
		for(SearchHit<Product> p : products) {
			productList.add(p.getContent());
		}
		
		return productList;
	}
	
//	public List<Product> findProductByPrice(String price) {
//		
//		NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchQuery("productDesc", desc)).build();
//		
//		SearchHits<Product> products = elasticsearchRestTemplate.search(searchQuery, Product.class, IndexCoordinates.of("product_it"));
//		
//		return products;
//	}
//	
//	public List<Product> findByProductTQty(String item) {
//		
//		NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.rangeQuery("productTQty").gte(item)).build();
//		
//		List<Product> products = elasticsearchRestTemplate.queryForList(searchQuery, Product.class, IndexCoordinates.of("product_it"));
//		
//		return products;
//	}
//	
//	
//	public List<Product> findByProductDetail(String itemName, String itemQty) {
//		
//		QueryBuilder query = QueryBuilders.boolQuery().must(QueryBuilders.rangeQuery("productTQty").gte(itemQty))
//													  .should(QueryBuilders.matchQuery("productDesc", itemName));
//													//.mustNot(QueryBuilders.matchQuery("productDesc", itemName));
//		
//		NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(query).build();
//		
//		List<Product> products = elasticsearchRestTemplate.queryForList(searchQuery, Product.class, IndexCoordinates.of("product_it"));
//		
//		return products;
//	}
	
	public void delete(Product product) {
		productRepository.delete(product);
	}
	
}
