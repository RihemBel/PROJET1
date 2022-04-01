package com.example.ecommerce.Service;

import com.example.ecommerce.entities.AdressLivraison;
import com.example.ecommerce.entities.ConfigLivraison;
import com.example.ecommerce.repositories.AdressLivraisonRepository;
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
public class AdressLivraisonService {
    private final Logger log = LoggerFactory.getLogger(PanierService.class);
    private final AdressLivraisonRepository adressLivraisonRepository;


    public AdressLivraisonService(AdressLivraisonRepository adressLivraisonRepository) {
        this.adressLivraisonRepository = adressLivraisonRepository;
    }

    /**
     * Save an adresslivraison.
     *
     * @param adressLivraison the entity to save.
     * @return the persisted entity.
     */
    public AdressLivraison saveAdressLivraison(AdressLivraison adressLivraison){

        return adressLivraisonRepository.save(adressLivraison);
    }

    /**
     * Delete the adresslivraison by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) {
        log.debug("Request to delete adressLivraison : {}", id);
        adressLivraisonRepository.deleteById(id);
    }

    /**
     * Get one adresslivraison by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AdressLivraison> findOne(UUID id) {
        log.debug("Request to get Adresslivraison : {}", id);
        return adressLivraisonRepository.findById(id);
    }
    /**
     * Get all the adresslivraisons.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<AdressLivraison> findAll(Pageable pageable) {
        log.debug("Request to get all AdressLivraisons");
        return adressLivraisonRepository.findAll(pageable);
    }
}
