package com.example.ecommerce.Web.rest;


import com.example.ecommerce.Service.ItemService;
import com.example.ecommerce.Utility.HeaderUtil;
import com.example.ecommerce.Utility.ResponseUtil;
import com.example.ecommerce.Web.rest.errors.BadRequestAlertException;
import com.example.ecommerce.entities.Category;
import com.example.ecommerce.entities.Item;
import com.example.ecommerce.entities.User;
import com.example.ecommerce.repositories.ItemRepository;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class ItemController {

    private final Logger log = LoggerFactory.getLogger(ItemController.class);

    private static final String ENTITY_NAME = "mywaybaseItem";

    private String applicationName;

    private final ItemService itemService;

    private final ItemRepository itemRepository;


    public ItemController(ItemService itemService, ItemRepository itemRepository) {
        this.itemService = itemService;
        this.itemRepository = itemRepository;
    }

    private static final List<User> USERS = Arrays.asList(
            new User(UUID.fromString("2de63968-ac23-11ec-b909-0242ac120002"), "anna" ,"Smith")
//            new User("Maria", "Jones"),
//            new User("Anna", "Smith")
    );


    /**
     * {@code POST  /items} : Create a new item.
     *
     * @param itemJson the item to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new item, or with status {@code 400 (Bad Request)} if the item has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/items")
    public ResponseEntity<Item> createItem(@RequestParam(value = "files", required = false) MultipartFile files, @RequestParam("item") String itemJson) throws URISyntaxException, IOException, ExecutionException, InterruptedException {
        Gson g = new Gson();
        Item item = g.fromJson(itemJson, Item.class);
        log.debug("REST request to save Item : {}", item);
        if (item.getId() != null) {
            throw new BadRequestAlertException("A new item cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Item result = itemService.save(item);
        return ResponseEntity.created(new URI("/api/items/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }
    /**
            * {@code PUT  /items} : Updates an existing item.
            *
            * @param item the item to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated item,
     * or with status {@code 400 (Bad Request)} if the item is not valid,
            * or with status {@code 500 (Internal Server Error)} if the item couldn't be updated.
            * @throws URISyntaxException if the Location URI syntax is incorrect.
            */

    @PutMapping("/items")
    public ResponseEntity<Item> updateItem(@Valid @RequestBody Item item) throws URISyntaxException, IOException {
        log.debug("REST request to update Item : {}", item);
        if (item.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        Item result = itemService.save(item);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }


    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems(@RequestHeader("Authorization") String authentication) {
        log.debug("REST request to get a List of Items");
        System.out.println("token"+authentication);
        List<Item> result = itemService.findAll();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * {@code GET  /items/:id} : get the "id" item.
     *
     * @param id the id of the item to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the item, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getItem(@PathVariable UUID id) {
        log.debug("REST request to get Item : {}", id);
        Optional<Item> item = itemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(item);
    }

    /**
     * {@code DELETE  /items/:id} : delete the "id" item.
     *
     * @param id the id of the item to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable UUID id) throws IOException {
        log.debug("REST request to delete Item : {}", id);
        itemService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    //used to delete item before update product
    @DeleteMapping("/items/delete/{id}")
    public ResponseEntity<Void> deleteItemToUpdateProduct(@PathVariable UUID id) throws IOException {
        log.debug("REST request to delete Item : {}", id);
        itemService.deleteToUpdateProduct(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
