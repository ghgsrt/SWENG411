package com.example.pantryapp.pantry;

import java.util.ArrayList;

public class RecipeBook {
    ArrayList<Recipe> recipeBook;

    public RecipeBook() {
        recipeBook = new ArrayList<>();
    }

    public RecipeBook(ArrayList<Recipe> recipeBook) {
        this.recipeBook = recipeBook;
    }

    public ArrayList<Recipe> getRecipeBook() {
        return recipeBook;
    }

    public void setRecipeBook(ArrayList<Recipe> recipeBook) {
        this.recipeBook = recipeBook;
    }

    public void addRecipe(Recipe recipe) {
        recipeBook.add(recipe);
    }

    public void removeRecipe(Recipe recipe) {
        recipeBook.remove(recipe);
    }

    public void removeRecipe(String name) {
        for(Recipe x : recipeBook)
            if(x.getName().equals(name))
                recipeBook.remove(x);
    }

    // Create methods for sorting the recipe book?
    // EX: Vegetarian (parse for no protein ingredients)
    //     Vegan (parse for no protein or dairy ingredients)
    //
    // Maybe add a "Tag" functionality to use for parsing?
    // EX: Vegetarian, Vegan, Breakfast, Gluten-Free, Low-Cal,
    //     Quick, No-Bake, etc.
    // If so, allow user to create tags to use?
    // Include limited set of starter tags?
}
