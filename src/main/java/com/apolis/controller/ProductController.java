package com.apolis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apolis.beans.Product;
import com.apolis.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/product")
@Api(value = "ProductController", description = "")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping
	@ApiOperation(value = "Create Product Information", response = Product.class)
	public Product createProduct(@RequestBody Product product) {
		return productService.create(product);
	}
	
	@GetMapping("findAll")
	@ApiOperation(value = "Fetch All Product Information", response = Iterable.class)
	@ApiResponses(
			@ApiResponse(code = 401, message = "Not Accessible")
			)
	public Iterable<Product> findAllProducts() {
		return productService.findAllProducts();
	}
	
	@GetMapping("findProductByName/{name}")
	@ApiOperation(value = "Fetch All Product Information By Product Name", response = Iterable.class)
	public Iterable<Product> findProductByName(@PathVariable String name) {
		return productService.findProductByName(name);
	}
	
	@GetMapping("findProductByDesc/{desc}")
	@ApiOperation(value = "Fetch All Product Information By Product Description Full Text", response = Iterable.class)
	public Iterable<Product> findProductByDesc(@PathVariable String desc) {
		return productService.findProductByDesc(desc);
	}
	
	@DeleteMapping
	public String deleteProduct(@RequestBody Product product) {
		productService.delete(product);
		return "Product deleted successfully.";
	}
}
