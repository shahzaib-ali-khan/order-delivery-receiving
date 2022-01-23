package com.gorillas.order.delivery.receiving.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity

public class Delivery {
	
	@Id
    private String deliveryId;
    private String product;
    private String supplier;
    private int quantity;
    private String expectedDate;
    private String expectedWarehouse;
    
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isReceived;
    
	public String getDeliveryId() {
		return deliveryId;
	}
	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getExpectedDate() {
		return expectedDate;
	}
	public void setExpectedDate(String expectedDate) {
		this.expectedDate = expectedDate;
	}
	public String getExpectedWarehouse() {
		return expectedWarehouse;
	}
	public void setExpectedWarehouse(String expectedWarehouse) {
		this.expectedWarehouse = expectedWarehouse;
	}
	public boolean getIsReceived() {
		return isReceived;
	}
	public void setIsReceived(boolean isReceived) {
		this.isReceived = isReceived;
	}
}
