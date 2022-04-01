package com.example.ecommerce.Service;

import com.example.ecommerce.entities.VariantValue;
import com.example.ecommerce.repositories.VariantValueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class VariantValueService {
    private final Logger log = LoggerFactory.getLogger(VariantValueService.class);

    private final VariantValueRepository variantValueRepository;

    public VariantValueService(VariantValueRepository variantValueRepository) {
        this.variantValueRepository = variantValueRepository;
    }

    /**
     * Save a variantValue.
     *
     * @param variantValue the entity to save.
     * @return the persisted entity.
     */
    public VariantValue save(VariantValue variantValue) {
        log.debug("Request to save VariantValue : {}", variantValue);
        return variantValueRepository.save(variantValue);
    }

    /**
     * Get all the variantValues.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<VariantValue> findAll(Pageable pageable) {
        log.debug("Request to get all VariantValues");
        return variantValueRepository.findAll(pageable);
    }


    /**
     * Get one variantValue by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<VariantValue> findOne(String id) {
        log.debug("Request to get VariantValue : {}", id);
        return variantValueRepository.findById(id);
    }

    /**
     * Delete the variantValue by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete VariantValue : {}", id);
        variantValueRepository.deleteById(id);
    }


}
