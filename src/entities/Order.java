package entities;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Order {
	private Long id;
	private String latitude;
	private String longitude;
	private Instant moment;
	private OrderStatus status;	
	
	private List<Product> products = new ArrayList <Product> ();
	
	public Order () {}
	
	public Order(Long id, String latitude, String longitude, Instant moment, OrderStatus status) {
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.moment = moment;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Double total() {
		Double total = 0.0;
		
		for(Product p : products) {
			total += p.getPrice();
		}
		
		return total;
	}

	public List<Product> getProducts() {
		return products;
	}
	
}
