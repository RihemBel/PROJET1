package com.example.ecommerce.Service;

import com.example.ecommerce.entities.Variant;
import com.example.ecommerce.repositories.VariantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class VariantService {

    private final Logger log = LoggerFactory.getLogger(VariantService.class);

    private final VariantRepository variantRepository;

    public VariantService(VariantRepository variantRepository) {
        this.variantRepository = variantRepository;}

    /**
     * Save a variant.
     *
     * @param variant the entity to save.
     * @return the persisted entity.
     */
    public Variant save(Variant variant) {
        log.debug("Request to save Variant : {}", variant);
        return variantRepository.save(variant);
    }

    /**
     * Get all the variants.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Variant> findAll(Pageable pageable) {
        log.debug("Request to get all Variants");
        return variantRepository.findAll(pageable);
    }

    /**
     * Get one variant by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Variant> findOne(String id) {
        log.debug("Request to get Variant : {}", id);
        return variantRepository.findById(id);
    }

    /**
     * Delete the variant by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Variant : {}", id);
        variantRepository.deleteById(id);
    }

}
