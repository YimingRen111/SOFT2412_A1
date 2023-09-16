package org.menu;

public class Order {
    private Item item;
    private int amount;
    public Order(Item item, int amount){
        this.item = item;
        this.amount = amount;
    }

    public Item get_item(){
        return item;
    }

    public int get_amount(){
        return amount;
    }

    public void set_amount(int new_amount){
        this.amount = new_amount;
    }


}
