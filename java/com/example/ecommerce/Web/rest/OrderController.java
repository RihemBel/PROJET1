package com.example.ecommerce.Web.rest;

import com.example.ecommerce.Service.OrderService;
import com.example.ecommerce.Utility.HeaderUtil;
import com.example.ecommerce.Utility.ResponseUtil;
import com.example.ecommerce.Web.rest.errors.BadRequestAlertException;
import com.example.ecommerce.entities.Category;
import com.example.ecommerce.entities.Order;
import com.example.ecommerce.entities.Variant;
import com.example.ecommerce.entities.VariantValue;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final Logger log = LoggerFactory.getLogger(OrderController.class);

    private static final String ENTITY_NAME = "mywaybaseOrder";


    private String applicationName;

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * {@code POST  /orders} : Create a new order.
     */
    @PostMapping("/orders")
    public void createOrder(@RequestBody Order o) {
        orderService.saveOrder(o);
    }

    /**
     * {@code GET  /orders} : get all the orders.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of variantValue in body.
     */
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders(Pageable pageable) {
        log.debug("REST request to get a page of Orders");
        Page<Order> page = (Page<Order>) orderService.findAll(pageable);

        return new ResponseEntity(page, HttpStatus.OK);
    }

    /**
     * {@code GET  /orders/:id} : get the "id" order.
     *
     * @param id the id of the variant to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the order, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable UUID id) {
        log.debug("REST request to get Order : {}", id);
        Optional<Order> order = orderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(order);
    }


    /**
     * {@code DELETE  /orders/:id} : delete the "id" order.
     *
     * @param id the id of the order to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID id) {
        log.debug("REST request to delete Order : {}", id);
        orderService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
