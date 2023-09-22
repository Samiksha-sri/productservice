package dev.naman.productservice.repositories;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository
extends JpaRepository<Product, UUID> {

    @Override
    List<Product> findAll();

    Product findByTitleEquals(String title);

    @Override
    void deleteById(UUID uuid);

    List<Product> findAllByTitleLike(String titleRegex);

    List<Product> readAllByTitleLike(String titleRegex);


   List<Product> findAllByCategoryIn(List<Category> categories);

    //    @Query("select Product  from Product  where Product .category.uuid in :uuids")
//    List<Product> findAllByCategoryIn(List<UUID> uuids);


    @Query(value = CustomQueries.FIND_ALL_BY_TITLE, nativeQuery = true)
    List<Product> findAllByTitle(String naman);

//    @Query("select Product from Product where Product.price.currency = :currency and Product.title = :naman")
//    List<Product> doSomething(String naman, String currency);
}
