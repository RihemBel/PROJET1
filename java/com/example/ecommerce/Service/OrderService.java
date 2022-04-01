package com.example.ecommerce.Service;

import com.example.ecommerce.entities.Order;
import com.example.ecommerce.entities.Panier;
import com.example.ecommerce.repositories.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class OrderService {

    private final Logger log = LoggerFactory.getLogger(PanierService.class);

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {

        this.orderRepository = orderRepository;
    }


    /**
     * Get all the orders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Order> findAll(Pageable pageable) {
        log.debug("Request to get all Orders");
        return orderRepository.findAll();
    }

   /**
           * Get one order by id.
     *
             * @param id the id of the entity.
            * @return the entity.
            */
    @Transactional(readOnly = true)
    public Optional<Order> findOne(UUID id) {
        log.debug("Request to get Order : {}", id);
        return orderRepository.findById(id);
    }

    /**
     * Save an order.
     *
     * @param order the entity to save.
     * @return the persisted entity.
     */
    public Order saveOrder(Order order){

        return orderRepository.save(order);
    }
    /**
     * Delete the order by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) {
        log.debug("Request to delete Order : {}", id);
        orderRepository.deleteById(id);
    }
}
