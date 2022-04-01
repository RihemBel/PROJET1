package com.example.ecommerce.Service;


import com.example.ecommerce.entities.Image;
import com.example.ecommerce.repositories.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ImageService {

    private final Logger log = LoggerFactory.getLogger(ImageService.class);

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    /**
     * Save an image.
     *
     * @param image the entity to save.
     * @return the persisted entity.
     */
    public Image save(Image image) {
        log.debug("Request to save ImageItem : {}", image);
        return imageRepository.save(image);
    }


    /**
     * Delete the image by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) throws IOException {
        log.debug("Request to delete ImageItem : {}", id);
        imageRepository.deleteById(id);
    }

    /**
     * Get one image by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Image> findOne(Long id) {
        log.debug("Request to get ImageItem : {}", id);
        return imageRepository.findById(id);
    }


    /**
     * Get all the image.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Image> findAll(Pageable pageable) {
        log.debug("Request to get all ImageItems");
        return imageRepository.findAll(pageable);
    }

}
