package com.example.ecommerce.Service;

import com.example.ecommerce.entities.Blog;
import com.example.ecommerce.entities.Image;
import com.example.ecommerce.repositories.BlogRepository;
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
public class BlogService {

    private final Logger log = LoggerFactory.getLogger(BlogService.class);

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {

        this.blogRepository = blogRepository;
    }
    /**
     * Save a blog.
     *
     * @param blog the entity to save.
     * @return the persisted entity.
     */
    public Blog save(Blog blog) {
        log.debug("Request to save Blog : {}", blog);
        return blogRepository.save(blog);
    }

    /**
     * Delete the blog by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) throws IOException {
        log.debug("Request to delete blog : {}", id);
        blogRepository.deleteById(id);
    }

    /**
     * Get one blog by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Blog> findOne(UUID id) {
        log.debug("Request to get blog : {}", id);
        return blogRepository.findById(id);
    }


    /**
     * Get all the blogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Blog> findAll(Pageable pageable) {
        log.debug("Request to get all blogs");
        return blogRepository.findAll(pageable);
    }
}
