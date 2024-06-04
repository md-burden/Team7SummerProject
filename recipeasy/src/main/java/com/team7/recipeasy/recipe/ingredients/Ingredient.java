package com.team7.recipeasy.recipe.ingredients;

import com.team7.recipeasy.recipe.Recipe;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

@Entity
@Table(name="ingredients")
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

    public Ingredient() {
    }

    public Ingredient( @Nonnull String ingredientName, @Nonnull String ingredientMeasurment, Double ingredientQuantity) {
        this.ingredientName = ingredientName;
        this.ingredientMeasurment = ingredientMeasurment;
        this.ingredientQuantity = ingredientQuantity;
    }

    public int getIngredient_id() {
        return ingredient_id;
    }

    public void setIngredient_id(int ingredient_id) {
        this.ingredient_id = ingredient_id;
    }

    @Nonnull
    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(@Nonnull String ingredientName) {
        this.ingredientName = ingredientName;
    }

    @Nonnull
    public String getIngredientMeasurment() {
        return ingredientMeasurment;
    }

    public void setIngredientMeasurment(@Nonnull String ingredientMeasurment) {
        this.ingredientMeasurment = ingredientMeasurment;
    }

    public Double getIngredientQuantity() {
        return ingredientQuantity;
    }

    public void setIngredientQuantity(Double ingredientQuantity) {
        this.ingredientQuantity = ingredientQuantity;
    }
}
