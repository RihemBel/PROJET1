package com.example.ecommerce.Web.rest;

import com.example.ecommerce.Service.CategoryService;
import com.example.ecommerce.Service.SubCategoryService;
import com.example.ecommerce.Utility.HeaderUtil;
import com.example.ecommerce.Utility.ResponseUtil;
import com.example.ecommerce.Web.rest.errors.BadRequestAlertException;
import com.example.ecommerce.entities.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
@RequestMapping( "/api")
public class CategoryController {

    private final Logger log = LoggerFactory.getLogger(CategoryController.class);

    private static final String ENTITY_NAME = "mywaybaseCategory";

    private String applicationName;

    private final CategoryService categoryService;

    private final SubCategoryService subCategoryService;

    public CategoryController(CategoryService categoryService, SubCategoryService subCategoryService) {
        this.categoryService = categoryService;
        this.subCategoryService = subCategoryService;
    }

    /**
     * {@code POST  /categories} : Create a new category.
     *
     * @param categoryJson the category to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new category, or with status {@code 400 (Bad Request)} if the category has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@RequestParam(value = "files", required = false) MultipartFile files, @RequestParam("category") String categoryJson) throws URISyntaxException, IOException, ExecutionException, InterruptedException {
        Gson g = new Gson();
        Category category = g.fromJson(categoryJson, Category.class);

        log.debug("REST request to save Category : {}", category);
        if (category.getId() != null) {
            throw new BadRequestAlertException("A new category cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Category result = categoryService.save(files, category);

        return ResponseEntity.created(new URI("/api/categories/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }
        /**
         * {@code PUT  /categories} : Updates an existing category.
         *
         * @param categoryJson the category to update.
         * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated category,
         * or with status {@code 400 (Bad Request)} if the category is not valid,
         * or with status {@code 500 (Internal Server Error)} if the category couldn't be updated.
         * @throws URISyntaxException if the Location URI syntax is incorrect.
         */


        @PutMapping("/categories")
        public ResponseEntity<Category> updateCategory(@RequestParam(value = "files", required = false) MultipartFile files, @RequestParam("category") String categoryJson) throws URISyntaxException, IOException, ExecutionException, InterruptedException {

            Gson g = new Gson();
            Category category = g.fromJson(categoryJson , Category.class);

            log.debug("REST request to update Category : {}", category);
            if (category.getId() == null) {
                throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
            }
            Category result = categoryService.save(files, category);
            return ResponseEntity.ok()
                    .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, category.getId().toString()))
                    .body(result);
    }

        /**
         * {@code GET  /categories} : get all the articleCategories.
         * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of categories in body.
         */
        @GetMapping("/categories")
        public ResponseEntity<List<Category>> getAllCategories() {
            log.debug("REST request to get a page of Categories");
            List<Category> page = categoryService.findAll();

            return new ResponseEntity(page, HttpStatus.OK);
        }

    /**
     * {@code GET  /categories/:id} : get the "id" category.
     *
     * @param id the id of the category to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the category, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/article-categories/{id}")
    public ResponseEntity<Category> getArticleCategory(@PathVariable UUID id) {
        log.debug("REST request to get ArticleCategory : {}", id);
        Optional<Category> category = categoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(category);
    }
    /**
     * {@code DELETE  /categories/:id} : delete the "id" category.
     *
     * @param id the id of the category to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) throws ExecutionException, InterruptedException, IOException {
        log.debug("REST request to delete Category : {}", id);
        categoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    //chek if name of Category exist

    @GetMapping("/categories/checkName/{name}")
    public ResponseEntity<Boolean> findByName(@PathVariable String name) {
        log.debug("REST request to get Category exist : {}", name);
        Boolean result =  categoryService.findByName(name);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}