package com.example.pantryapp.ui.recipebook;

import com.example.pantryapp.pantry.Recipe;

public class RecipeModel {

    private final long Id;
    private final Recipe recipe;

    public RecipeModel(long Id, Recipe recipe) {
        this.Id = Id;
        this.recipe = recipe;
    }

    public long getId() {
        return this.Id;
    }

    public Recipe getRecipe() {
        return this.recipe;
    }
}

