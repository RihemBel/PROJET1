package com.example.ecommerce.Web.rest;

import com.example.ecommerce.Service.VariantValueService;
import com.example.ecommerce.Utility.HeaderUtil;
import com.example.ecommerce.Utility.ResponseUtil;
import com.example.ecommerce.Web.rest.errors.BadRequestAlertException;
import com.example.ecommerce.entities.Variant;
import com.example.ecommerce.entities.VariantValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class VariantValueController {

    private final Logger log = LoggerFactory.getLogger(VariantValueController.class);

    private static final String ENTITY_NAME = "mywaybaseVariantValue";


    private String applicationName;

    private final VariantValueService variantValueService;

    public VariantValueController(VariantValueService variantValueService) {
        this.variantValueService = variantValueService;
    }

    /**
     * {@code POST  /variantValues} : Create a new variantValue.
     *
     * @param variantValue the variantValue to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new variantValue, or with status {@code 400 (Bad Request)} if the variantValue has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/variant-values")
    public ResponseEntity<VariantValue> createVariantValue(@Valid @RequestBody VariantValue variantValue) throws URISyntaxException {
        log.debug("REST request to save VariantValue : {}", variantValue);
        if (variantValue.getName() == null) {
            throw new BadRequestAlertException("A new variantValue must already have an ID", ENTITY_NAME, "idexists");
        }
        VariantValue result = variantValueService.save(variantValue);


        return  new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * {@code PUT  /variantValues} : Updates an existing variantValue.
     *
     * @param variantValue the variantValue to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated variantValue,
     * or with status {@code 400 (Bad Request)} if the variantValue is not valid,
     * or with status {@code 500 (Internal Server Error)} if the variantValue couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/variant-values")
    public ResponseEntity<VariantValue> updateVariantValue(@Valid @RequestBody VariantValue variantValue) throws URISyntaxException {
        log.debug("REST request to update VariantValue : {}", variantValue);
        if (variantValue.getName() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VariantValue result = variantValueService.save(variantValue);

        return  new ResponseEntity<>(result, HttpStatus.OK);
    }
    /**
     * {@code GET  /variantValues} : get all the variantValues.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of variantValue in body.
     */
    @GetMapping("/variant-values")
    public ResponseEntity<List<VariantValue>> getAllVariantValues(Pageable pageable) {
        log.debug("REST request to get a page of VariantValues");
        Page<VariantValue> page = variantValueService.findAll(pageable);

        return new ResponseEntity(page, HttpStatus.OK);
    }

    /**
     * {@code GET  /variantValues/:id} : get the "id" variantValue.
     *
     * @param id the id of the variantValue to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the variantValue, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/variant-values/{id}")
    public ResponseEntity<VariantValue> getVariantValue(@PathVariable String id) {
        log.debug("REST request to get VariantValue : {}", id);
        Optional<VariantValue> variantValue = variantValueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(variantValue);
    }

    /**
     * {@code DELETE  /variantValues/:id} : delete the "id" variantValue.
     *
     * @param id the id of the variantValue to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/variant-values/{id}")
    public ResponseEntity<Void> deleteVariantValue(@PathVariable String id) {
        log.debug("REST request to delete VariantValue : {}", id);
        variantValueService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
