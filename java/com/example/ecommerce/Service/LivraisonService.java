package com.example.ecommerce.Service;


import com.example.ecommerce.entities.Livraison;
import com.example.ecommerce.repositories.LivraisonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class LivraisonService {

    private final Logger log = LoggerFactory.getLogger(PanierService.class);
    private final LivraisonRepository livraisonRepository;

    public LivraisonService(LivraisonRepository livraisonRepository) {
        this.livraisonRepository = livraisonRepository;
    }

    /**
     * Save a livraison.
     *
     * @param livraison the entity to save.
     * @return the persisted entity.
     */
    public Livraison saveLivraison(Livraison livraison){

        return livraisonRepository.save(livraison);
    }

    /**
     * Delete the livraison by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) {
        log.debug("Request to delete Livraison : {}", id);
        livraisonRepository.deleteById(id);
    }

    /**
     * Get one livraison by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Livraison> findOne(UUID id) {
        log.debug("Request to get Livraison : {}", id);
        return livraisonRepository.findById(id);
    }

    /**
     * Get all the livraisons.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Livraison> findAll(Pageable pageable) {
        log.debug("Request to get all Livraisons");
        return livraisonRepository.findAll(pageable);
    }

    /**
     * Get all livraisons by name.
     *
     * @param name the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public List<Livraison> findOne(String name) {
        log.debug("Request to get all Livraisons by name : {}", name);
        return (List<Livraison>) livraisonRepository.findByName(name);
    }
}
