package com.example.pantryapp.pantry;

import com.example.pantryapp.pantry.options.*;

public class Ingredient {

    String name;
    double quantity;
    Units units;
    Classification type;
    SubClassP subType;
    double cost;
    boolean inStock;

    // Inventory Ingredient
    // Full object, quantity denotes amount owned
    public Ingredient(String name, double quantity, Units units, Classification type, SubClassP subType, double cost) {
        this.name = name;
        this.quantity = quantity;
        this.units = units;
        this.type = type;
        if(this.type == Classification.PROTEIN)
            this.subType = subType;
        else
            this.subType = null;
        this.cost = cost;
        this.inStock = false;
    }

    // Recipe Ingredient
    // Slimmed down, quantity denotes amount required, not necessarily owned
    public Ingredient(String name, double quantity, Units units) {
        this.name = name;
        this.quantity = quantity;
        this.units = units;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void reduceQuantity(double quantity) {
        this.quantity -= quantity;
    }

    public Units getUnits() {
        return units;
    }

    public void setUnits(Units units) {
        this.units = units;
    }

    public Classification getType() {
        return type;
    }

    public void setType(Classification type) {
        this.type = type;
    }

    public SubClassP getSubType() {
        return subType;
    }

    public void setSubType(SubClassP subType) {
        this.subType = subType;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }
}
