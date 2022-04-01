package com.example.ecommerce.Service;

import com.example.ecommerce.entities.Pivv;
import com.example.ecommerce.entities.PivvPk;
import com.example.ecommerce.repositories.PivvRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PivvService {

    private final Logger log = LoggerFactory.getLogger(PivvService.class);

    private final PivvRepository pivvRepository;


    public PivvService(PivvRepository pivvRepository) {
        this.pivvRepository = pivvRepository;
    }
    /**
     * Save a pivv.
     *
     * @param pivv the entity to save.
     * @return the persisted entity.
     */
    public Pivv save(Pivv pivv) {
        log.debug("Request to save Pivv : {}", pivv);
        Pivv result =  pivvRepository.save(pivv);
        return result;
    }

    /**
     * Get all the pivvs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Pivv> findAll(Pageable pageable) {
        log.debug("Request to get all Pivvs");
        return pivvRepository.findAll(pageable);
    }

    /**
     * Get one pivv by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Pivv> findOne(PivvPk id) {
        log.debug("Request to get Pivv : {}", id);
        return pivvRepository.findById(id);
    }

    /**
     * Delete the pivv by id.
     *
     * @param id the id of the entity.
     */
    public void delete(PivvPk id) {
        log.debug("Request to delete Pivv : {}", id);
        pivvRepository.deleteById(id);
    }
}
