package com.example.ecommerce.Service;

import com.example.ecommerce.entities.SubCategory;
import com.example.ecommerce.repositories.BlogRepository;
import com.example.ecommerce.repositories.SubCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class SubCategoryService {

    private final Logger log = LoggerFactory.getLogger(SubCategoryService.class);

    private final MessageSource messageSource;

    private final SubCategoryRepository subCategoryRepository;

    private final BlogRepository blogRepository;

    public SubCategoryService(MessageSource messageSource, SubCategoryRepository subCategoryRepository, BlogRepository blogRepository) {
        this.messageSource = messageSource;
        this.subCategoryRepository = subCategoryRepository;
        this.blogRepository = blogRepository;
    }

    /**
     * Save a subcategory.
     *
     * @return the persisted entity.
     */
    public SubCategory save(MultipartFile files, SubCategory subCategory) {
        return subCategoryRepository.save(subCategory);

    }

    /**
     * Get all the subCategories.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<SubCategory> findAll() {
        log.debug("Request to get all SubCategories");
        return (List<SubCategory>) subCategoryRepository.findAll();
    }


    /**
     * Get one articleSubCategory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SubCategory> findOne(UUID id) {
        log.debug("Request to get SubCategory : {}", id);
        return subCategoryRepository.findById(id);
    }

    /**
     * Delete the articleSubCategory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) {
        log.debug("Request to delete subCategory : {}", id);
        subCategoryRepository.deleteById(id);

    }
}