package dev.naman.productservice.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class Product extends BaseModel {

    private String title;

    private String description;

    private String image;
    //            P : C
    // => L to R: 1 : 1
    // => R to L: m : 1
    // => Ans:    m : 1
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "category")
    private Category category;

//    @Fetch(FetchMode.JOIN)
    private double price;
//    private double price;
}
