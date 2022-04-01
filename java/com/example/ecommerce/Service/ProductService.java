package com.example.ecommerce.Service;

import com.example.ecommerce.Web.rest.VM.ProductVariantsVariantValues;
import com.example.ecommerce.Web.rest.VM.ProductWithHisItems;
import com.example.ecommerce.entities.*;
import com.example.ecommerce.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class ProductService {

    private final Logger log = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    private final ItemRepository itemRepository;

    private final ImageService imageService;

    private final ImageRepository imageRepository;

    private final PivvRepository pivvRepository;

    private final VariantRepository variantRepository;

    private final VariantValueRepository variantValueRepository;


    private final MessageSource messageSource;

    private final DataSource dataSource;

    public ProductService(ProductRepository productRepository, ItemRepository itemRepository, ImageService imageService, ImageRepository imageRepository, PivvRepository pivvRepository, VariantRepository variantRepository, VariantValueRepository variantValueRepository, MessageSource messageSource, DataSource dataSource) {
        this.productRepository = productRepository;
        this.itemRepository = itemRepository;
        this.imageService = imageService;
        this.imageRepository = imageRepository;
        this.pivvRepository = pivvRepository;
        this.variantRepository = variantRepository;
        this.variantValueRepository = variantValueRepository;
        this.messageSource = messageSource;
        this.dataSource = dataSource;
    }


    /**
     * Get all the articles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Product> findAll(Pageable pageable) {

        log.debug("Request to get all Products");
        return productRepository.findAll(pageable);
    }

    /**
     * Get items of a product.
     */
    @Transactional(readOnly = true)
    public ProductWithHisItems getAllItems_ofArticle(UUID productId) {
        Optional<Product> product = this.findOne(productId);

        List<Item> itemList = pivvRepository.getAllItems_ofProduct(productId);
        ProductWithHisItems productWithHisItems = new ProductWithHisItems(product.get(), itemList);
        return productWithHisItems;
    }

    /**
     * Get one product by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Product> findOne(UUID id) {
        log.debug("Request to get Product : {}", id);
        return productRepository.findById(id);
    }

    /**
     * Get all the products without pagination.
     */
    @Transactional(readOnly = true)
    public ProductWithHisItems findOneWithHisItems(UUID id) {

        log.debug("Request to get Product with his items : {}", id);
        Optional<Product> product = this.findOne(id);
        List<ProductWithHisItems> productWithHisItemsList = new ArrayList<>();
        List<Item> itemList = pivvRepository.getAllItems_ofProduct(product.get().getId());
        ProductWithHisItems articleWithHisItems = new ProductWithHisItems(product.get(), itemList);
        return articleWithHisItems;

    }


    /**
     * Delete the product by id.
     *
     * @param id the id of the entity.
     */
    public void delete(UUID id) throws IOException {
        log.debug("Request to delete Product : {}", id);
        Optional<Product> product = this.findOne(id);
        List<Item>itemList = pivvRepository.getAllItems_ofProduct(product.get().getId());
        for(Item item: itemList){
            Set<Image> imageItemSet = item.getImage();
            if(imageItemSet.size() != 0) {
                for (Image imageItem : imageItemSet) {
                    imageService.delete(imageItem.getId());

                }
            }
            itemRepository.deleteById(item.getId());

        }
        productRepository.deleteById(id);

    }

    /**
     * Get one article by id with his variants and their variantValues.
     *
     * @param productId the id of the entity.
     * @return the entity.
    //     */
    @Transactional(readOnly = true)
    public List<ProductVariantsVariantValues> eshop_findProductVariantsVariantValues(UUID productId) {

        log.debug("Request to get Product with his variants and variant values: {}", productId);
        List<String> variantOfProduct = pivvRepository.getAllVariants_ofProduct(productId);
        List<ProductVariantsVariantValues>productVariantsVariantValuesList = new ArrayList<>();
        for(String variantName: variantOfProduct){
            List<String> variantValueNames = pivvRepository.getAllVariantValues_ofVariant(productId, variantName);

            ProductVariantsVariantValues productVariantsVariantValues =new ProductVariantsVariantValues(variantName, variantValueNames);
            productVariantsVariantValuesList.add(productVariantsVariantValues);
        }

        return productVariantsVariantValuesList;
    }

    //verify if name of article exist

   public Boolean findByName(String name){
       Product product = productRepository.findByName(name);
       if(product == null){
          return false;
        }else
          return true;
   }


    /**
     * Save a product.
     *
     * @return the persisted entity.
     */
    public Product save(MultipartFile files, Product product) {
        return productRepository.save(product);

    }
    /**
     * Get all the products with items pagination.
     */
    @Transactional(readOnly = true)
    public Page<ProductWithHisItems> findAllWithItems(Pageable pageable, Sort sort) {

        log.debug("Request to get all Products");
        List<Product> productList = productRepository.findAll(sort);
        List<ProductWithHisItems> productWithHisItemsList = new ArrayList<>();
        for (Product product : productList) {
            List<Item> itemList = pivvRepository.getAllItems_ofProduct(product.getId());
           ProductWithHisItems productWithHisItems = new ProductWithHisItems(product, itemList);
            productWithHisItemsList.add(productWithHisItems);
        }


        int start = Math.min((int)pageable.getOffset(), productWithHisItemsList.size());
        int end = Math.min((start + pageable.getPageSize()), productWithHisItemsList.size());

        final Page<ProductWithHisItems> page = new PageImpl(productWithHisItemsList.subList(start,end), pageable, productWithHisItemsList.size());
        return  page;
    }

}
