package com.example.ecommerce.Service;


import com.example.ecommerce.entities.LignePanier;
import com.example.ecommerce.repositories.LignePanierRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class LignePanierService {
    private final Logger log = LoggerFactory.getLogger(PanierService.class);
    private final LignePanierRepository lignePanierRepository;

    public LignePanierService(LignePanierRepository lignePanierRepository) {
        this.lignePanierRepository = lignePanierRepository;
    }

    /**
     * Save a lignePanier.
     *
     * @param lignePanier the entity to save.
     * @return the persisted entity.
     */
    public LignePanier save(LignePanier lignePanier) {
        return lignePanierRepository.save(lignePanier);

    }

    /**
     * Delete the lignePanier by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) {
        log.debug("Request to delete LignePanier : {}", id);
        lignePanierRepository.deleteById(id);
    }

    /**
     * Get one lignePanier by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<LignePanier> findOne(UUID id) {
        log.debug("Request to get LignePanier : {}", id);
        return lignePanierRepository.findById(id);
    }

    /**
     *
     * @return  total of lignePaniers
     */
    public Integer calculate_total_lignepaniers() {
        Integer total_lignepaniers = lignePanierRepository.findTotalLignePaniers();
        return total_lignepaniers;
    }

    /**
     * Get all the paniers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<LignePanier> findAll(Pageable pageable) {
        log.debug("Request to get all LignePaniers");
        return lignePanierRepository.findAll(pageable);
    }

}
