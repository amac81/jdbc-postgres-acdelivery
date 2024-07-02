package app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import db.DB;
import entities.Order;
import entities.OrderStatus;
import entities.Product;

public class Program {

	public static void main(String[] args) throws SQLException {
		
		Connection conn = DB.getConnection();
	
		Statement st = conn.createStatement();
			
		ResultSet rs = st.executeQuery("SELECT * FROM tb_order "
				+ "INNER JOIN tb_order_product ON tb_order.id = tb_order_product.order_id "
				+ "INNER JOIN tb_product ON tb_product.id = tb_order_product.product_id");
		
		
		Map<Long, Order> ordersMap = new HashMap <> ();
		Map<Long, Product> productsMap = new HashMap <> ();
		
		while (rs.next()) {
			Long orderId = rs.getLong("order_id");
			
			if(ordersMap.get(orderId) == null) {
				Order order = instatiateOrder(rs); 
				ordersMap.put(orderId, order);
			}
					
			Long productId = rs.getLong("product_id");
			
			if(productsMap.get(productId) == null) {
				Product product = instatiateProduct(rs); 
				productsMap.put(productId, product);
			}
			
			ordersMap.get(orderId).getProducts().add(productsMap.get(productId));
		}
		
		
		for(Long orderId : ordersMap.keySet()) {
			System.out.println(ordersMap.get(orderId));
			
		}
	}
	
	private static Product instatiateProduct(ResultSet rs) throws SQLException {
		Product p = new Product();
		p.setId(rs.getLong("product_id"));
		p.setName(rs.getString("name"));
		p.setDescription(rs.getString("description"));
		p.setImageUri(rs.getString("image_uri"));
		p.setPrice(rs.getDouble("price"));
		
		return p;		
	}
	
	private static Order instatiateOrder(ResultSet rs) throws SQLException {
		Order o = new Order();
		o.setId(rs.getLong("order_id"));
		o.setLatitude(rs.getDouble("latitude"));
		o.setLongitude(rs.getDouble("longitude"));
		o.setMoment(rs.getTimestamp("moment").toInstant());
		o.setStatus(OrderStatus.values()[rs.getInt("status")]);
		
		return o;		
	}
}
