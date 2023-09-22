package dev.naman.productservice.controllers;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.dtos.ProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.services.ProductService;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
//    @Autowired
    // field injection
    private ProductService productService;
    // ....;
    // ...;



    // constructor injection
//    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
//

    // setter injection
//    @Autowired
//    public void setProductService(ProductService productService) {
//        this.productService = productService;
//    }

    // GET /products {}
    @GetMapping
    public List<GenericProductDto> getAllProducts() throws NotFoundException {
        return productService.getAllProducts();
    }

    @GetMapping("{uuid}")
    public GenericProductDto getProductByUuid(@PathVariable("uuid")UUID uuid) throws NotFoundException {
        return productService.getProductById(uuid);
    }


    @DeleteMapping("{uuid}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("uuid") UUID id) {
        return new ResponseEntity<>(
                productService.deleteProduct(id),
                HttpStatus.OK
        );
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product) {
//        System.out.println(product.name);
        return productService.createProduct(product);
    }

    @PutMapping("{uuid}")
    public GenericProductDto updateProductById(@PathVariable("uuid") UUID id, @RequestBody GenericProductDto genericProductDto) {

        return productService.updateProduct(id, genericProductDto);
    }
}
