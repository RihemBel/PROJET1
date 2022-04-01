package com.example.ecommerce.Web.rest;


import com.example.ecommerce.Service.LivraisonService;
import com.example.ecommerce.Utility.HeaderUtil;
import com.example.ecommerce.Utility.ResponseUtil;
import com.example.ecommerce.Web.rest.errors.BadRequestAlertException;
import com.example.ecommerce.entities.Livraison;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
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
public class LivraisonController {

    private final Logger log = LoggerFactory.getLogger(LivraisonController.class);

    private static final String ENTITY_NAME = "mywaybaseLivraison";

    private String applicationName;

    private final LivraisonService livraisonService;

    public LivraisonController(LivraisonService livraisonService) {
        this.livraisonService = livraisonService;
    }

    /**
     * {@code POST  /livraisons} : Create a new livraison.
     *
     * @param livraison the livraison to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new livraison, or with status {@code 400 (Bad Request)} if the livraison has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/livraisons")
    public ResponseEntity<Livraison> createLivraison(@RequestBody Livraison livraison) throws URISyntaxException {
        log.debug("REST request to save Livraison : {}", livraison);
        if (livraison.getId() != null) {
            throw new BadRequestAlertException("A new livraison cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Livraison result = livraisonService.saveLivraison(livraison);
        return ResponseEntity.created(new URI("/api/livraisons/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /livraisons} : Updates an existing livraison.
     *
     * @param livraison the livraison to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated livraison,
     * or with status {@code 400 (Bad Request)} if the livraison is not valid,
     * or with status {@code 500 (Internal Server Error)} if the livraison couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/livraisons")
    public ResponseEntity<Livraison> updateLivrasion(@RequestBody Livraison livraison) throws URISyntaxException {
        log.debug("REST request to update livraison : {}", livraison);
        if (livraison.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Livraison result = livraisonService.saveLivraison(livraison);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, livraison.getId().toString()))
                .body(result);
    }

    /**
     * {@code GET  /livraisons} : get all the livraisons.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of livraisons in body.
     */
    @GetMapping("/livraisons")
    public ResponseEntity<List<Livraison>> getAllLivraisons(Pageable pageable) {
        log.debug("REST request to get a page of livraisons");
        Page<Livraison> result = livraisonService.findAll(pageable);
        return new ResponseEntity(result, HttpStatus.OK);

    }

    /**
     * {@code GET  /livraisons/:id} : get the "id" livraison.
     *
     * @param id the id of the v to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the livraison, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/livraisons/{id}")
    public ResponseEntity<Livraison> getLivraison(@PathVariable UUID id) {
        log.debug("REST request to get livraison : {}", id);
        Optional<Livraison> livraison = livraisonService.findOne(id);
        return ResponseUtil.wrapOrNotFound(livraison);
    }

    /**
     * {@code DELETE  /livraisons/:id} : delete the "id" livraison.
     *
     * @param id the id of the livraison to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/livraisons/{id}")
    public ResponseEntity<Void> deleteLivraison(@PathVariable UUID id) {
        log.debug("REST request to delete livraison : {}", id);
        livraisonService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
