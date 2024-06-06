package com.team7.recipeasy.recipe.ingredients;


import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="ingredients")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ingredient_id;

    @Nonnull
    private String ingredientName;

    @Nonnull
    private String ingredientMeasurment;

    @Nonnull
    @Column(precision = 2)
    private Double ingredientQuantity;

    /**
     * Used to create a new recipe
     * @param ingredientName
     * @param ingredientMeasurment
     * @param ingredientQuantity
     */
    public Ingredient( @Nonnull String ingredientName, @Nonnull String ingredientMeasurment, Double ingredientQuantity) {
        this.ingredientName = ingredientName;
        this.ingredientMeasurment = ingredientMeasurment;
        this.ingredientQuantity = ingredientQuantity;
    }
}
