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
    private int ingredientId;

    @Nonnull
    private String ingredientName;

    @Nonnull
    private String ingredientAmount;

    /**
     * Used to create a new recipe
     * @param ingredientName
     * @param ingredientAmount
     */
    public Ingredient( @Nonnull String ingredientName, @Nonnull String ingredientAmount) {
        this.ingredientName = ingredientName;
        this.ingredientAmount = ingredientAmount;

    }
}
