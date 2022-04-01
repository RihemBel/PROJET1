package com.example.ecommerce.Web.rest;


import com.example.ecommerce.Service.LignePanierService;
import com.example.ecommerce.Utility.HeaderUtil;
import com.example.ecommerce.Utility.ResponseUtil;
import com.example.ecommerce.entities.LignePanier;
import com.example.ecommerce.entities.Panier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class LignePanierController {

    private final Logger log = LoggerFactory.getLogger(LignePanierController.class);

    private static final String ENTITY_NAME = "mywaybaseLignePanier";

    private String applicationName;

    private final LignePanierService lignePanierService;

    public LignePanierController(LignePanierService lignePanierService) {
        this.lignePanierService = lignePanierService;
    }


    /**
     * {@code POST  /lignePanier} : Create a new lignePanier.
     */
    @PostMapping("/lignePanier")
    public void createLignePanier(@RequestBody LignePanier lp) {

        lignePanierService.save(lp);
    }

    /**
     * {@code GET  /lignePanier} : get all the lignePanier.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of variantValue in body.
     */
    @GetMapping("/lignePanier")
    public ResponseEntity<List<LignePanier>> getAlllignePanier(Pageable pageable) {
        log.debug("REST request to get a page of lignePanier");
        Page<LignePanier> page = (Page<LignePanier>) lignePanierService.findAll(pageable);

        return new ResponseEntity(page, HttpStatus.OK);
    }

    /**
     * {@code GET  /lignePanier/:id} : get the "id" lignePanier.
     *
     * @param id the id of the variant to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the lignePanier, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/lignePanier/{id}")
    public ResponseEntity<LignePanier> getLignePanier(@PathVariable UUID id) {
        log.debug("REST request to get Panier : {}", id);
        Optional<LignePanier> lignePanier = lignePanierService.findOne(id);
        return ResponseUtil.wrapOrNotFound(lignePanier);
    }


    /**
     * {@code DELETE  /lignePanier/:id} : delete the "id" lignePanier.
     *
     * @param id the id of the lignePanier to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/lignePanier/{id}")
    public ResponseEntity<Void> deleteLignePanier(@PathVariable UUID id) {
        log.debug("REST request to delete LignePanier : {}", id);
        lignePanierService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }


}
