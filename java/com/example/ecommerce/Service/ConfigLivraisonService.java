package com.example.ecommerce.Service;

import com.example.ecommerce.entities.ConfigLivraison;
import com.example.ecommerce.entities.Livraison;
import com.example.ecommerce.repositories.ConfigLivraisonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ConfigLivraisonService {

    private final Logger log = LoggerFactory.getLogger(PanierService.class);
    private final ConfigLivraisonRepository configLivraisonRepository;

    public ConfigLivraisonService(ConfigLivraisonRepository configLivraisonRepository) {
        this.configLivraisonRepository = configLivraisonRepository;
    }


    /**
     * Save a configlivraison.
     *
     * @param configLivraison the entity to save.
     * @return the persisted entity.
     */
    public ConfigLivraison saveConfigLivraison(ConfigLivraison configLivraison){

        return configLivraisonRepository.save(configLivraison);
    }

    /**
     * Delete the configlivraison by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) {
        log.debug("Request to delete configLivraison : {}", id);
        configLivraisonRepository.deleteById(id);
    }

    /**
     * Get one configlivraison by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ConfigLivraison> findOne(UUID id) {
        log.debug("Request to get Configlivraison : {}", id);
        return configLivraisonRepository.findById(id);
    }

    /**
     * Get all the configlivraisons.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ConfigLivraison> findAll(Pageable pageable) {
        log.debug("Request to get all ConfigLivraisons");
        return configLivraisonRepository.findAll(pageable);
    }

    /**
     * Get all configlivraisons by name.
     *
     * @param name the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public List<ConfigLivraison> findOne(String name) {
        log.debug("Request to get all ConfigLivraisons by name : {}", name);
        return (List<ConfigLivraison>) configLivraisonRepository.findByName(name);
    }
}
