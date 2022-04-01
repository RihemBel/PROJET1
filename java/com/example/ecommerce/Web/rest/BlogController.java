package com.example.ecommerce.Web.rest;


import com.example.ecommerce.Service.BlogService;
import com.example.ecommerce.Utility.HeaderUtil;
import com.example.ecommerce.Utility.ResponseUtil;
import com.example.ecommerce.Web.rest.errors.BadRequestAlertException;
import com.example.ecommerce.entities.Blog;
import com.example.ecommerce.entities.ConfigLivraison;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class BlogController {

    private final Logger log = LoggerFactory.getLogger(BlogController.class);

    private static final String ENTITY_NAME = "mywaybaseBlog";

    private String applicationName;

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    /**
     * {@code POST  /blog} : Create a new blog.
     *
     * @param blog the configLivraison to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new blog, or with status {@code 400 (Bad Request)} if the blog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/blog")
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog) throws URISyntaxException {
        log.debug("REST request to save blog : {}", blog);
        if (blog.getId() != null) {
            throw new BadRequestAlertException("A new blog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Blog result = blogService.save(blog);
        return ResponseEntity.created(new URI("/api/blog/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /blog} : Updates an existing blog.
     *
     * @param blog the configLivraison to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated blog,
     * or with status {@code 400 (Bad Request)} if the blog is not valid,
     * or with status {@code 500 (Internal Server Error)} if the blog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/blog")
    public ResponseEntity<Blog> updateBlog(@RequestBody Blog blog) throws URISyntaxException {
        log.debug("REST request to update blog : {}", blog);
        if (blog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Blog result = blogService.save(blog);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, blog.getId().toString()))
                .body(result);
    }

    /**
     * {@code GET  /blog} : get all the blog.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of blog in body.
     */
    @GetMapping("/blog")
    public ResponseEntity<List<Blog>> getAllBlog(Pageable pageable) {
        log.debug("REST request to get a page of blog");
        Page<Blog> result = blogService.findAll(pageable);
        return new ResponseEntity(result, HttpStatus.OK);

    }

    /**
     * {@code GET  /blog/:id} : get the "id" blog.
     *
     * @param id the id of the v to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the blog, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/blog/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable UUID id) {
        log.debug("REST request to get blog : {}", id);
        Optional<Blog> blog = blogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(blog);
    }

    /**
     * {@code DELETE  /blog/:id} : delete the "id" blog.
     *
     * @param id the id of the blog to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/blog/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable UUID id) throws IOException {
        log.debug("REST request to delete blog : {}", id);
        blogService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

}
