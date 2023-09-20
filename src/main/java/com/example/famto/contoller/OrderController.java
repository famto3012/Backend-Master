package com.example.famto.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.famto.entity.Order;
import com.example.famto.exception.ResourceNotFoundException;
import com.example.famto.repository.LoginRepository;
import com.example.famto.repository.OrderRepository;

@RestController
@RequestMapping("/api/orders")

public class OrderController {
	
	@Autowired
	private OrderRepository orderRepository;

	// get all orders
	@GetMapping
	public List<Order> getAllOrders() {
		return this.orderRepository.findAll();
	}

	// get order by id
	@GetMapping("/{newId}")
	public Order getOrderById(@PathVariable (value = "newId") Integer orderId) {
		return this.orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found with newId :" + orderId));
	}

	// create order
	@PostMapping
	public Order createOrder(@RequestBody Order order) {
		return this.orderRepository.save(order);
	}
	
	// update order status
	@PutMapping("/{newId}")
	public Order updateOrder(@RequestBody Order order, @PathVariable ("newId") Integer orderId) {
		 Order existingOrder = this.orderRepository.findById(orderId)
			.orElseThrow(() -> new ResourceNotFoundException("Order not found with newId :" + orderId));
		 existingOrder.setStatus(order.getStatus());
		 return this.orderRepository.save(existingOrder);
	}
	
/*	// delete login by id
	@DeleteMapping("/{id}")
	public ResponseEntity<Login> deleteLogin(@PathVariable ("id") long loginId){
		 Login existingLogin = this.loginRepository.findById(loginId)
					.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + loginId));
		 this.loginRepository.delete(existingLogin);
		 return ResponseEntity.ok().build();
	} */

}
