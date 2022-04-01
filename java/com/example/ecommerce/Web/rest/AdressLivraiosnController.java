package com.example.ecommerce.Web.rest;


import com.example.ecommerce.Service.AdressLivraisonService;
import com.example.ecommerce.Utility.HeaderUtil;
import com.example.ecommerce.Web.rest.errors.BadRequestAlertException;
import com.example.ecommerce.entities.AdressLivraison;
import com.example.ecommerce.entities.ConfigLivraison;
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

@RestController
@RequestMapping("/api")
public class AdressLivraiosnController {

    private final Logger log = LoggerFactory.getLogger(ConfigLivraisonController.class);

    private static final String ENTITY_NAME = "mywaybaseAdressLivraison";

    private String applicationName;

    private final AdressLivraisonService adressLivraisonService;

    public AdressLivraiosnController(AdressLivraisonService adressLivraisonService) {
        this.adressLivraisonService = adressLivraisonService;
    }

    /**
     * {@code POST  /adressLivraison} : Create a new adressLivraison.
     *
     * @param adressLivraison the adressLivraison to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new adressLivraison, or with status {@code 400 (Bad Request)} if the adressLivraison has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adressLivraison")
    public ResponseEntity<AdressLivraison> createAdressLivraison(@RequestBody AdressLivraison adressLivraison) throws URISyntaxException {
        log.debug("REST request to save adressLivraison : {}", adressLivraison);
        if (adressLivraison.getId() != null) {
            throw new BadRequestAlertException("A new adressLivraison cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdressLivraison result = adressLivraisonService.saveAdressLivraison(adressLivraison);
        return ResponseEntity.created(new URI("/api/adressLivraison/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /adressLivraison} : Updates an existing adressLivraison.
     *
     * @param adressLivraison the adressLivraison to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adressLivraison,
     * or with status {@code 400 (Bad Request)} if the adressLivraison is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adressLivraison couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adressLivraison")
    public ResponseEntity<AdressLivraison> updateAdressLivraison(@RequestBody AdressLivraison adressLivraison) throws URISyntaxException {
        log.debug("REST request to update adressLivraison : {}", adressLivraison);
        if (adressLivraison.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdressLivraison result = adressLivraisonService.saveAdressLivraison(adressLivraison);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adressLivraison.getId().toString()))
                .body(result);
    }

    /**
     * {@code GET  /adressLivraison} : get all the adressLivraison.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adressLivraison in body.
     */
    @GetMapping("/adressLivraison")
    public ResponseEntity<List<AdressLivraison>> getAllAdressLivraison(Pageable pageable) {
        log.debug("REST request to get a page of adressLivraison");
        Page<AdressLivraison> result = adressLivraisonService.findAll(pageable);
        return new ResponseEntity(result, HttpStatus.OK);

    }
}
