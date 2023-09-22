package dev.naman.productservice.services;

import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.repositories.ProductRepository;
import dev.naman.productservice.dtos.GenericProductDto;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Primary
@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public SelfProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public GenericProductDto getProductById(UUID id) throws NotFoundException {
       Product product =  productRepository.findById(id).get();

        return getGenericProductDto(product);
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        Product newProduct = new Product();
        newProduct.setCategory(genericProductDto.getCategory());
        newProduct.setDescription(genericProductDto.getDescription());
        newProduct.setImage(genericProductDto.getImage());
        newProduct.setTitle(genericProductDto.getTitle());
        newProduct.setPrice(genericProductDto.getPrice());
        newProduct.setUuid(UUID.randomUUID());

        productRepository.save(newProduct);

        return getGenericProductDto(newProduct);
    }

    @Override
    public List<GenericProductDto> getAllProducts() throws NotFoundException {
        List<Product> products = productRepository.findAll();
        if(products.isEmpty())
            throw new NotFoundException("No products to show.");
        List<GenericProductDto> genericProductDtos = new ArrayList<>();
        for(Product product : products){

            genericProductDtos.add(getGenericProductDto(product));
        }
        return genericProductDtos;
    }

    @Override
    public GenericProductDto deleteProduct(UUID id) {
        Product product = productRepository.findById(id).get();
        GenericProductDto genericProductDto = getGenericProductDto(product);
        productRepository.deleteById(id);
        return genericProductDto;
    }

    @Override
    public GenericProductDto updateProduct(UUID id, GenericProductDto genericProductDto) {
       Product product = productRepository.findById(id).get();

           product.setCategory(genericProductDto.getCategory());
           product.setDescription(genericProductDto.getDescription());
           product.setImage(genericProductDto.getImage());
           product.setTitle(genericProductDto.getTitle());
           product.setPrice(genericProductDto.getPrice());
           product.setUuid(id);


       productRepository.save(product);
        return getGenericProductDto(product);
    }


    private GenericProductDto getGenericProductDto(Product product){
        GenericProductDto genericProductDto = new GenericProductDto();

        genericProductDto.setId(product.getUuid());
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setCategory(product.getCategory());
        genericProductDto.setPrice(product.getPrice());
        genericProductDto.setImage(product.getImage());

        return genericProductDto;
    }
}
