package com.example.ecommerce.Web.rest;


import com.example.ecommerce.Service.ConfigLivraisonService;
import com.example.ecommerce.Utility.HeaderUtil;
import com.example.ecommerce.Utility.ResponseUtil;
import com.example.ecommerce.Web.rest.errors.BadRequestAlertException;
import com.example.ecommerce.entities.ConfigLivraison;
import com.example.ecommerce.entities.Livraison;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ConfigLivraisonController {

    private final Logger log = LoggerFactory.getLogger(ConfigLivraisonController.class);

    private static final String ENTITY_NAME = "mywaybaseConfigLivraison";

    private String applicationName;

    private final ConfigLivraisonService configLivraisonService;

    public ConfigLivraisonController(ConfigLivraisonService configLivraisonService) {
        this.configLivraisonService = configLivraisonService;
    }

    /**
     * {@code POST  /configLivraison} : Create a new configLivraison.
     *
     * @param configLivraison the configLivraison to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new configLivraison, or with status {@code 400 (Bad Request)} if the configLivraison has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/configLivraison")
    public ResponseEntity<ConfigLivraison> createConfigLivraison(@RequestBody ConfigLivraison configLivraison) throws URISyntaxException {
        log.debug("REST request to save configLivraison : {}", configLivraison);
        if (configLivraison.getId() != null) {
            throw new BadRequestAlertException("A new configLivraison cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ConfigLivraison result = configLivraisonService.saveConfigLivraison(configLivraison);
        return ResponseEntity.created(new URI("/api/configLivraison/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /configLivraison} : Updates an existing configLivraison.
     *
     * @param configLivraison the configLivraison to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated configLivraison,
     * or with status {@code 400 (Bad Request)} if the configLivraison is not valid,
     * or with status {@code 500 (Internal Server Error)} if the configLivraison couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/configLivraison")
    public ResponseEntity<ConfigLivraison> updateConfigLivraison(@RequestBody ConfigLivraison configLivraison) throws URISyntaxException {
        log.debug("REST request to update configLivraiosn : {}", configLivraison);
        if (configLivraison.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ConfigLivraison result = configLivraisonService.saveConfigLivraison(configLivraison);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, configLivraison.getId().toString()))
                .body(result);
    }

    /**
     * {@code GET  /configLivraison} : get all the configLivraison.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of configLivraison in body.
     */
    @GetMapping("/configLivraison")
    public ResponseEntity<List<ConfigLivraison>> getAllConfigLivraison(Pageable pageable) {
        log.debug("REST request to get a page of configLivraison");
        Page<ConfigLivraison> result = configLivraisonService.findAll(pageable);
        return new ResponseEntity(result, HttpStatus.OK);

    }

    /**
     * {@code GET  /configLivraison/:id} : get the "id" configLivraison.
     *
     * @param id the id of the v to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the configLivraison, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/configLivraison/{id}")
    public ResponseEntity<ConfigLivraison> getConfigLivraison(@PathVariable UUID id) {
        log.debug("REST request to get configLivraison : {}", id);
        Optional<ConfigLivraison> configLivraison = configLivraisonService.findOne(id);
        return ResponseUtil.wrapOrNotFound(configLivraison);
    }

    /**
     * {@code DELETE  /configLivraison/:id} : delete the "id" configLivraison.
     *
     * @param id the id of the configLivraison to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/configLivraison/{id}")
    public ResponseEntity<Void> deleteConfigLivraison(@PathVariable UUID id) {
        log.debug("REST request to delete configLivraison : {}", id);
        configLivraisonService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
