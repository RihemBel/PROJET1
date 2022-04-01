package com.example.ecommerce.Web.rest;


import com.example.ecommerce.Service.PivvService;
import com.example.ecommerce.Utility.HeaderUtil;
import com.example.ecommerce.Utility.ResponseUtil;
import com.example.ecommerce.Web.rest.errors.BadRequestAlertException;
import com.example.ecommerce.entities.Pivv;
import com.example.ecommerce.entities.PivvPk;
import com.example.ecommerce.entities.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PivvController {

    private final Logger log = LoggerFactory.getLogger(PivvController.class);

    private static final String ENTITY_NAME = "mywaybasePvv";

    private String applicationName;

    private final PivvService pivvService;


    public PivvController(PivvService pivvService) {

        this.pivvService = pivvService;
    }

    /**
     * {@code POST  /pivvs} : Create a new pivv.
     *
     * @param pivv the pivv to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pivv, or with status {@code 400 (Bad Request)} if the article has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pivvs")
    public ResponseEntity<Pivv> createPivv(@Valid @RequestBody Pivv pivv) throws URISyntaxException {

        Pivv result = pivvService.save(pivv);
        return ResponseEntity.created(new URI("/api/articles/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }


    @PutMapping("/pivvs")
    public ResponseEntity<Pivv> updatePivv(@Valid @RequestBody Pivv pivv) throws URISyntaxException {
        log.debug("REST request to update Article : {}", pivv);
        if (pivv.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        Pivv result = pivvService.save(pivv);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code GET  /articles} : get all the articles.
     *
     * @param pageable  the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of articles in body.
     */
    @GetMapping("/pivvs")
    public ResponseEntity<List<Pivv>> getAllPivvs(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Pivvs");

        Page<Pivv> result = pivvService.findAll(pageable);

        return new ResponseEntity(result, HttpStatus.OK);
    }

    /**
     * {@code GET  /pivvs/:id} : get the "id" article.
     *
     * @param id the id of the product to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the product, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pivvs/{id}")
    public ResponseEntity<Pivv> getPivv(@PathVariable PivvPk id) {
        log.debug("REST request to get Product : {}", id);
        Optional<Pivv> article = pivvService.findOne(id);
        return ResponseUtil.wrapOrNotFound(article);
    }

    /**
     * {@code DELETE  /products/:id} : delete the "id" product.
     *
     * @param id the id of the article to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pivvs/{id}")
    public ResponseEntity<Void> deletePivv(@PathVariable PivvPk id) {
        log.debug("REST request to delete Pivv : {}", id);
        pivvService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
