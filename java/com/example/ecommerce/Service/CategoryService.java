package com.example.ecommerce.Service;

import com.example.ecommerce.entities.Category;
import com.example.ecommerce.entities.SubCategory;
import com.example.ecommerce.repositories.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


@Service
@Transactional
public class CategoryService {
    private final Logger log = LoggerFactory.getLogger(CategoryService.class);

    private final CategoryRepository categoryRepository;

    private final MessageSource messageSource;


    private final DataSource dataSource;

    private final NotificationService notificationService;

    public CategoryService(CategoryRepository categoryRepository, MessageSource messageSource, DataSource dataSource, NotificationService notificationService) {
        this.categoryRepository = categoryRepository;
        this.messageSource = messageSource;
        this.dataSource = dataSource;
        this.notificationService = notificationService;
    }

    /**
     * Save a category.
     *
     * @return the persisted entity.
     */
    public Category save(MultipartFile files, Category category) {
        return categoryRepository.save(category);

    }
    /**
     * Get all the articleCategories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Category> findAll(Pageable pageable) {
        log.debug("Request to get all ProductCategories");
        return categoryRepository.findAllByOrderByName(pageable);

    }

    /**
     * Get all the articleCategories.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Category> findAll() {
        log.debug("Request to get all ProductCategories");
        return categoryRepository.findAll();

    }

    /**
     * Get one productCategory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Category> findOne(UUID id) {
        log.debug("Request to get ProductCategory : {}", id);
        return categoryRepository.findById(id);
    }

    //verify if name of articleCategory exist

    public Boolean findByName(String name){

        Category productCategory = categoryRepository.findByName(name);
        if(productCategory == null){
            return false;
        }
        else
            return true;
    }



    public Boolean findByName(UUID categoryId, String name) {

        Optional<Category> category = this.findOne(categoryId);

        Set<SubCategory> subCategorySet = category.get().getSubCategory();

        Boolean result = false;

        if(subCategorySet != null && subCategorySet.size() != 0){
            for (SubCategory subCategory : subCategorySet) {

                if (subCategory.getName().equals(name)) {

                    result = true;
                }
            }
        }

        return result;
    }
    /**
     * Delete the category by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) throws IOException {
        log.debug("Request to delete category : {}", id);
        categoryRepository.deleteById(id);

    }

}