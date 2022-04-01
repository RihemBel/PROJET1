package com.example.ecommerce.Service;

import com.example.ecommerce.entities.Mark;
import com.example.ecommerce.repositories.BlogRepository;
import com.example.ecommerce.repositories.MarkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
@Transactional
public class MarkService {

    private final Logger log = LoggerFactory.getLogger(MarkService.class);


    private final MarkRepository markRepository;

    private final MessageSource messageSource;

    private final BlogRepository blogRepository;

    public MarkService(MarkRepository markRepository, MessageSource messageSource, BlogRepository blogRepository) {
        this.markRepository = markRepository;
        this.messageSource = messageSource;
        this.blogRepository = blogRepository;
    }
    /**
     * Save a mark.
     *
     *
     * @param files
     * @param mark the entity to save.
     * @return the persisted entity.
     */
    public Mark save(MultipartFile files, Mark mark) {
        log.debug("Request to save Mark : {}", mark);
        Mark result =  markRepository.save(mark);
        return result;
    }

    /**
     * Get all the marks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Mark> findAll(Pageable pageable) {
        log.debug("Request to get all Marks");
        return markRepository.findAll(pageable);
    }

    /**
     * Get all the marks.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Mark> findAll() {
        log.debug("Request to get all Marks");
        return markRepository.findAll();
    }

    /**
     * Get one mark by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Mark> findOne(UUID id) {
        log.debug("Request to get Mark : {}", id);
        return markRepository.findById(id);
    }

    /**
     * Delete the mark by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) throws ExecutionException, InterruptedException {
        log.debug("Request to delete Mark : {}", id);
        markRepository.deleteById(id);

    }

    //verify if name of mark exist

    public Boolean findByName(String name){

        Mark mark = markRepository.findByName(name);
        if(mark == null){
            return false;
        }

        else
            return true;
    }

}
