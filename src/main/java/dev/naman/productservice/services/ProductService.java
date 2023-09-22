package dev.naman.productservice.services;

import dev.naman.productservice.dtos.ProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.dtos.GenericProductDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    GenericProductDto createProduct(GenericProductDto product);

    GenericProductDto getProductById(UUID id) throws NotFoundException;

    List<GenericProductDto> getAllProducts() throws NotFoundException;

   GenericProductDto deleteProduct(UUID id);
   GenericProductDto updateProduct(UUID id, GenericProductDto product);

}
