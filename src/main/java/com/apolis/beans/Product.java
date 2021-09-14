package com.apolis.beans;

import org.springframework.data.elasticsearch.annotations.Document;

import io.swagger.annotations.ApiModelProperty;

@Document(indexName = "product_it")
public class Product {

	@ApiModelProperty(notes = "Product Id", name = "id", required = false)
	private String id;
	@ApiModelProperty(notes = "Product Name", name = "productName", required = true)
	private String productName;
	@ApiModelProperty(notes = "Product Price", name = "productPrice", required = true)
	private String productPrice;
	@ApiModelProperty(notes = "Product Description", name = "productDesc", required = true)
	private String productDesc;
	@ApiModelProperty(notes = "Product Total Quantity", name = "productQty", required = true)
	private String productQty;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getProductQty() {
		return productQty;
	}
	public void setProductQty(String productQty) {
		this.productQty = productQty;
	}
}
