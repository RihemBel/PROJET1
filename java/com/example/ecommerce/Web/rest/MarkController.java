package com.example.ecommerce.Web.rest;


import com.example.ecommerce.Service.MarkService;
import com.example.ecommerce.Utility.HeaderUtil;
import com.example.ecommerce.Utility.ResponseUtil;
import com.example.ecommerce.Web.rest.errors.BadRequestAlertException;
import com.example.ecommerce.entities.Mark;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
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
public class MarkController {

    private final Logger log = LoggerFactory.getLogger(MarkController.class);

    private static final String ENTITY_NAME = "mywaybaseMark";

    private String applicationName;

    private final MarkService markService;

    private final MessageSource messageSource;

    public MarkController(MarkService markService, MessageSource messageSource) {
        this.markService = markService;
        this.messageSource = messageSource;
    }

    /**
     * {@code POST  /marks} : Create a new mark.
     *
     * @param markJson the mark to create.
     * @return the {@link org.springframework.http.ResponseEntity} with status {@code 201 (Created)} and with body the new mark, or with status {@code 400 (Bad Request)} if the mark has already an ID.
     * @throws java.net.URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/marks")
    public ResponseEntity<Mark> createMark(@RequestParam(value = "files", required = false) MultipartFile files, @RequestParam("mark") String markJson) throws URISyntaxException, IOException, ExecutionException, InterruptedException {

        Gson g = new Gson();
        Mark mark = g.fromJson(markJson, Mark.class);

        log.debug("REST request to save Mark : {}", mark);
        if (mark.getId() != null) {
            throw new BadRequestAlertException("A new mark cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Mark result = markService.save(files, mark);

        return ResponseEntity.created(new URI("/api/marks/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);

    }

    /**
     * {@code PUT  /marks} : Updates an existing mark.
     *
     * @param markJson the mark to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mark,
     * or with status {@code 400 (Bad Request)} if the mark is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mark couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/marks")
    public ResponseEntity<Mark> updateMark(@RequestParam(value = "files", required = false) MultipartFile files, @RequestParam("mark") String markJson) throws URISyntaxException, IOException, ExecutionException, InterruptedException {

        Gson g = new Gson();
        Mark mark = g.fromJson(markJson, Mark.class);

        log.debug("REST request to update Mark : {}", mark);
        if (mark.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Mark result = markService.save(files, mark);

        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mark.getId().toString()))
                .body(result);
    }

    /**
     * {@code GET  /marks} : get all the marks.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mark in body.
     */
    @GetMapping("/marks")
    public ResponseEntity<List<Mark>> getAllMarks() {
        log.debug("REST request to get a page of Marks");
        List<Mark> page = markService.findAll();
        return new ResponseEntity<>(page, HttpStatus.OK);
    }
    /**
     * {@code GET  /marks/:id} : get the "id" mark.
     *
     * @param id the id of the mark to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mark, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/marks/{id}")
    public ResponseEntity<Mark> getMark(@PathVariable UUID id) {
        log.debug("REST request to get Mark : {}", id);
        Optional<Mark> mark = markService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mark);
    }

    /**
     * {@code DELETE  /marks/:id} : delete the "id" mark.
     *
     * @param id the id of the mark to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/marks/{id}")
    public ResponseEntity<Void> deleteMark(@PathVariable UUID id) throws ExecutionException, InterruptedException {
        log.debug("REST request to delete Mark : {}", id);
        markService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/marks/checkName/{name}")
    public ResponseEntity<Boolean> findByName(@PathVariable String name) {
        log.debug("REST request to get Mark exist : {}", name);
        Boolean result =  markService.findByName(name);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
