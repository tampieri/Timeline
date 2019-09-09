package com.higor.timeline.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Compra implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String timestamp;
	private String transaction_id;
	private double revenue;
	private String store_name;
	private List<Produto> products = new ArrayList<>();

	public Compra() {
	}

	public Compra(String timestamp, String transaction_id, double revenue, String store_name) {
		super();
		this.timestamp = timestamp;
		this.transaction_id = transaction_id;
		this.revenue = revenue;
		this.store_name = store_name;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public double getRevenue() {
		return revenue;
	}

	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public List<Produto> getProducts() {
		return products;
	}

	public void setProducts(List<Produto> products) {
		this.products = products;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((products == null) ? 0 : products.hashCode());
		long temp;
		temp = Double.doubleToLongBits(revenue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((store_name == null) ? 0 : store_name.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((transaction_id == null) ? 0 : transaction_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compra other = (Compra) obj;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		if (Double.doubleToLongBits(revenue) != Double.doubleToLongBits(other.revenue))
			return false;
		if (store_name == null) {
			if (other.store_name != null)
				return false;
		} else if (!store_name.equals(other.store_name))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (transaction_id == null) {
			if (other.transaction_id != null)
				return false;
		} else if (!transaction_id.equals(other.transaction_id))
			return false;
		return true;
	}

}
