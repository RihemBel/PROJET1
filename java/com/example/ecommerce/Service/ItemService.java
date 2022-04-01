package com.example.ecommerce.Service;

import com.example.ecommerce.entities.Category;
import com.example.ecommerce.entities.Image;
import com.example.ecommerce.entities.Item;
import com.example.ecommerce.entities.Product;
import com.example.ecommerce.repositories.BlogRepository;
import com.example.ecommerce.repositories.ItemRepository;
import com.example.ecommerce.repositories.PivvRepository;
import com.example.ecommerce.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class ItemService {


    private final Logger log = LoggerFactory.getLogger(ItemService.class);

    private final ItemRepository itemRepository;

    private final PivvRepository pivvRepository;

    private final ProductService productService;

    private final BlogRepository blogRepository;

    private final ImageService imageService;

    private final ProductRepository productRepository;

    public ItemService(ItemRepository itemRepository, PivvRepository pivvRepository, ProductService productService, BlogRepository blogRepository, ImageService imageService, ProductRepository productRepository) {
        this.itemRepository = itemRepository;
        this.pivvRepository = pivvRepository;
        this.productService = productService;
        this.blogRepository = blogRepository;
        this.imageService = imageService;
        this.productRepository = productRepository;
    }

    /**
     * Save an item.
     *
     * @return the persisted entity.
     */
    public Item save(Item item) {
        //   item.setIsDeleted(false);
        return itemRepository.save(item);

    }
    /**
     * Get one item by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Item> findOne(UUID id) {
        log.debug("Request to get Item : {}", id);
        return itemRepository.findById(id);
    }



    /**
     * Delete the item by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) throws IOException {
        log.debug("Request to delete Item : {}", id);
        Product product = pivvRepository.getProduct_ofItem(id);
        Optional<Item> item = this.findOne(id);
        Set<Image> imageItemSet = item.get().getImage();
        if(imageItemSet.size() != 0) {
            for (Image imageItem : imageItemSet) {
                imageService.delete(imageItem.getId());

            }
        }
        itemRepository.deleteById(id);

        List<Item> itemList = pivvRepository.getAllItems_ofProduct(product.getId());
        if(itemList.size() == 0){
            productService.delete(product.getId());
        }

    }
    /**
     * Get all the items.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Item> findAll() {
        log.debug("Request to get all items");
        return itemRepository.findAll();

    }
    //used to delete item before update product

    public void deleteToUpdateProduct(UUID id) throws IOException {
        log.debug("Request to delete Item : {}", id);
        Product product = pivvRepository.getProduct_ofItem(id);
        Optional<Item> item = this.findOne(id);
        Set<Image>imageItemSet = item.get().getImage();
        if(imageItemSet.size() != 0) {
            for (Image imageItem : imageItemSet) {
                imageService.delete(imageItem.getId());

            }
        }
        itemRepository.deleteById(id);

    }

}
