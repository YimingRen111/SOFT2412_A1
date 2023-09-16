package org.example;

//import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.ArrayList; // import the ArrayList class

public class User {
    private final ArrayList<Order> order_list;
    private float total = 0;

    public User(){
        // each user will have an order list
        this.order_list = new ArrayList<Order>();
    }

    public void add_order(Item item, int amount){
        Order order = new Order(item,amount);
        this.order_list.add(order);
        this.total += amount * item.get_price();
    }

    public void change_quantity(int item_number, int amount){
        // search for the item
        Order order = order_list.get(item_number - 1);

        // if the amount is 0, remove the item
        if (amount == 0){
            order_list.remove(order);
            this.total -= order.get_amount() * order.get_item().get_price();
        }
        else if (amount < 0) {
            System.out.println("negative amount?");
        }
        else{
            this.total -= order.get_amount() * order.get_item().get_price();
            order.set_amount(amount);
            this.total += amount * order.get_item().get_price();
        }
    }

    public ArrayList<Order> get_order_list(){
        return this.order_list;
    }

    public float get_total(){
        return this.total;
    }
}