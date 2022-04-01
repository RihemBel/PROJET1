package com.example.ecommerce.Service;

import com.example.ecommerce.entities.Panier;
import com.example.ecommerce.repositories.PanierRepository;
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
public class PanierService {

    private final Logger log = LoggerFactory.getLogger(PanierService.class);
    private final PanierRepository panierRepository;

    public PanierService(PanierRepository panierRepository) {
        this.panierRepository = panierRepository;
    }


    /**
     * Save a panier.
     *
     * @param panier the entity to save.
     * @return the persisted entity.
     */
    public Panier save(Panier panier) {
        return panierRepository.save(panier);

    }

    /**
     * Get all the paniers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Panier> findAll(Pageable pageable) {
        log.debug("Request to get all Paniers");
        return panierRepository.findAll(pageable);
    }

    /**
     * Get one panier by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Panier> findOne(UUID id) {
        log.debug("Request to get Panier : {}", id);
        return panierRepository.findById(id);
    }

    /**
     * Delete the panier by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) {
        log.debug("Request to delete Panier : {}", id);
        panierRepository.deleteById(id);
    }
    /**
     *
     * @return  total of paniers
     */
    public Integer calculate_total_paniers() {
        Integer total_paniers = panierRepository.findTotalPaniers();
        return total_paniers;
    }

}
