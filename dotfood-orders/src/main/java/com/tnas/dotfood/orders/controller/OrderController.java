package com.tnas.dotfood.orders.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.tnas.dotfood.orders.dto.OrderDto;
import com.tnas.dotfood.orders.dto.StatusDto;
import com.tnas.dotfood.orders.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

        @Autowired
        private OrderService service;

        @GetMapping()
        public List<OrderDto> getAll() {
            return service.getAll();
        }

        @GetMapping("/{id}")
        public ResponseEntity<OrderDto> getById(@PathVariable @NotNull Long id) {
            return  ResponseEntity.ok(service.getById(id));
        }

        @PostMapping()
        public ResponseEntity<OrderDto> makeOrder(@RequestBody @Valid OrderDto dto, 
        		UriComponentsBuilder uriBuilder) {
        	
            var sentOrder = service.createOrder(dto);

            return ResponseEntity
            		.created(uriBuilder.path("/orders/{id}")
            		.buildAndExpand(sentOrder.getId()).toUri()).body(sentOrder);
        }

        @PutMapping("/{id}/status")
        public ResponseEntity<OrderDto> updateStatus(@PathVariable Long id, @RequestBody StatusDto status){
            return ResponseEntity.ok(service.updateStatus(id, status));
        }

        @PutMapping("/{id}/paid")
        public ResponseEntity<Void> approvePayment(@PathVariable @NotNull Long id) {
            service.approveOrderPayment(id);
            return ResponseEntity.ok().build();
        }
}
