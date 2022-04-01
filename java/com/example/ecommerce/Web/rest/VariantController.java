package com.example.ecommerce.Web.rest;


import com.example.ecommerce.Service.VariantService;
import com.example.ecommerce.Utility.HeaderUtil;
import com.example.ecommerce.Utility.ResponseUtil;
import com.example.ecommerce.Web.rest.errors.BadRequestAlertException;
import com.example.ecommerce.entities.Category;
import com.example.ecommerce.entities.Variant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class VariantController {

    private final Logger log = LoggerFactory.getLogger(VariantController.class);

    private static final String ENTITY_NAME = "mywaybaseVariant";

    private String applicationName;

    private final VariantService variantService;

    public VariantController(VariantService variantService) {
        this.variantService = variantService;
    }

    /**
     * {@code POST  /variants} : Create a new variant.
     *
     * @param variant the variant to create.
     * @return the {@link org.springframework.http.ResponseEntity} with status {@code 201 (Created)} and with body the new variant, or with status {@code 400 (Bad Request)} if the variant has already an ID.
     * @throws java.net.URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/variants")
    public ResponseEntity<Variant> createVariant(@Valid @RequestBody Variant variant) throws URISyntaxException {
        log.debug("REST request to save Variant : {}", variant);
        if (variant.getName() == null) {
            throw new BadRequestAlertException("A new variant must already have an ID", ENTITY_NAME, "idexists");
        }
        Variant result = variantService.save(variant);


        return  new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * {@code PUT  /variants} : Updates an existing variant.
     *
     * @param variant the variant to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated variant,
     * or with status {@code 400 (Bad Request)} if the variant is not valid,
     * or with status {@code 500 (Internal Server Error)} if the variant couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/variants")
    public ResponseEntity<Variant> updateVariant(@Valid @RequestBody Variant variant) throws URISyntaxException {
        log.debug("REST request to update Variant : {}", variant);
        if (variant.getName() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Variant result = variantService.save(variant);


        return  new ResponseEntity<>(result, HttpStatus.OK);
    }
    /**
     * {@code GET  /variants} : get all the variants.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of variant in body.
     */

    @GetMapping("/variants")
    public ResponseEntity<List<Variant>> getAllVariants(Pageable pageable) {
        log.debug("REST request to get a page of Variants");
        Page<Variant> page = variantService.findAll(pageable);

        return new ResponseEntity(page, HttpStatus.OK);
    }

    /**
     * {@code GET  /variants/:id} : get the "id" variant.
     *
     * @param id the id of the variant to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the variant, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/variants/{id}")
    public ResponseEntity<Variant> getVariant(@PathVariable String id) {
        log.debug("REST request to get Variant : {}", id);
        Optional<Variant> variant = variantService.findOne(id);
        return ResponseUtil.wrapOrNotFound(variant);
    }
    /**
     * {@code DELETE  /variants/:id} : delete the "id" variant.
     *
     * @param id the id of the variant to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/variants/{id}")
    public ResponseEntity<Void> deleteVariant(@PathVariable String id) {
        log.debug("REST request to delete Variant : {}", id);
        variantService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

}
