
package org.example;

public class Item {
    private final String name;
    private final String description;
    private final float price;

    public Item(String name, String description, float price){
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String get_name(){
        return this.name;
    }

    public String get_description(){
        return this.description;
    }

    public float get_price(){
        return this.price;
    }

    @Override
    public String toString() {
        return this.name + " - $" + this.price + "\n" + this.description + "\n";
    }
}