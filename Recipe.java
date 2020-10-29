package com.example.pantryapp.pantry;

import android.media.Image;

import com.example.pantryapp.pantry.options.Units;

import java.util.ArrayList;

public class Recipe {
    String name;
    ArrayList<Ingredient> recipe;
    Image image;

    public Recipe(String name) {
        this.name = name;
        recipe = new ArrayList<>();
    }

    public Recipe(String name, Image image) {
        this.name = name;
        this.image = image;
        recipe = new ArrayList<>();
    }

    public Recipe(String name, ArrayList<Ingredient> recipe) {
        this.name = name;
        this.recipe = recipe;
    }

    public Recipe(String name, Image image, ArrayList<Ingredient> recipe) {
        this.name = name;
        this.image = image;
        this. recipe = recipe;
    }

//    public void addIngredient(Ingredient ingredient) {
//        recipeList.add(ingredient);
//    }

    public void addIngredient(String name, double quantity, Units units) {
        recipe.add(new Ingredient(name, quantity, units));
    }

    // To be ran whenever opening a recipe
    public void checkStock(ArrayList<Ingredient> recipeList, ArrayList<Ingredient> inventory) {
        for(Ingredient x : recipeList) {
            for(Ingredient y : inventory) {
                if(x.getName().equals(y.getName()))
                    x.setInStock(Units.convert(x, y) >= y.getQuantity());
            }
        }
    }

    public void removeIngredient(Ingredient ingredient) {
        recipe.remove(ingredient);
    }

    public void removeIngredient(String name) {
        for(Ingredient x : recipe)
            if(x.getName() == name)
                recipe.remove(x);
    }

    public ArrayList<Ingredient> getRecipe() {
        return recipe;
    }

    public void setRecipe(ArrayList<Ingredient> recipe) {
        this.recipe = recipe;
    }

    public String getName() {
        return this.name;
    }
    public void setImage(Image image) {
        this.image = image;
    }

    public void removeImage() {
        this.image = null;
    }
}
