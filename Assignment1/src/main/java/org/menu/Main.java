package org.menu;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static final String PASSWORD = "password";   // Admin password
    private static final Scanner s = new Scanner(System.in);    // Scanner for user input
    private static final Menu menu = new Menu("src/main/resources/menu");   // Menu object

    public static void main(String[] args) {

        if (is_admin()){
            Admin admin = new Admin();
        }
        else{
            System.out.println("Welcome, User!");
            User user = new User();

            while (true){
                String choice = display_options();
                if (choice.equals("1")){
                    add_order(user);
                }
                else if (choice.equals("2")) {
                    change_quantity(user);
                } else if (choice.equals("3")){
                    view_cart(user);
                }
                else if (choice.equals("4")){
                    check_out(user);
                    break;
                }


                else{
                    break;
                }
            }
        }

        s.close();

    }

    public static boolean is_admin(){
        System.out.println("Hi there! Are you an admin (y/n)");
        String input = s.nextLine();

        if (input.equals("y")){
            System.out.println("Password:");
            if (s.nextLine().equals(PASSWORD)){
                System.out.println("Welcome, Admin!");
                System.out.println("-------------------------------");
                return true;
            }
            else{
                System.out.println("Wrong password");
                System.out.println("-------------------------------");
                return is_admin();
            }
        }
        else if (input.equals("n")){
            System.out.println("-------------------------------");
            return false;
        }
        else{
            System.out.println("Please enter 'y' or 'n'");
            System.out.println("-------------------------------");
            return is_admin();
        }
    }

    public static String display_options(){
        System.out.println("How can we help you?");
        System.out.println("1. Order");
        System.out.println("2. Modify Your Order");
        System.out.println("3. View Your Cart");
        System.out.println("4. Checkout");
        System.out.println("5. Cancel Order");

        String choice = s.nextLine();
        try{
            if (Integer.parseInt(choice) > 5 || Integer.parseInt(choice) < 1){
                System.out.println("Invalid Option");
                return display_options();
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid Input");
            System.out.println("-------------------------------");
            return display_options();
        }
        System.out.println("-------------------------------");
        return choice;
    }

    public static String display_category(){
        System.out.println("Which category number would you like to order?");
        ArrayList<String> category = menu.get_category();
        for (int i = 0; i < category.size(); i++){
            System.out.println((i+1) + ". " + category.get(i));
        }
        String choice = s.nextLine();
        // catch invalid input
        try{
            // if the choice is not within the category
            if (Integer.parseInt(choice) > category.size()){
                System.out.println("Invalid Input");
                System.out.println("-------------------------------");
                return display_category();
            }
        }
        // if it's not a number
        catch (NumberFormatException e){
            System.out.println("Invalid Input");
            System.out.println("-------------------------------");
            return display_category();
        }
        System.out.println("-------------------------------");
        return choice;
    }

    public static String display_menu(int index){
        System.out.println("Which item number would you like to order?");
        ArrayList<Item> items = menu.get_menu(index);
        for (int i = 0; i < items.size(); i++){
            System.out.println((i+1) + ". " + items.get(i).toString());
        }
        String choice = s.nextLine();
        // catch invalid input
        try{
            // if the choice is not within the category
            if (Integer.parseInt(choice) > items.size()){
                System.out.println("Invalid Input");
                System.out.println("-------------------------------");
                return display_menu(index);
            }
        }
        // if it's not a number
        catch (NumberFormatException e){
            System.out.println("Invalid Input");
            return display_menu(index);
        }
        System.out.println("-------------------------------");
        return choice;
    }

    public static void add_order(User user){
        int c_index = Integer.parseInt(display_category());                 // category index
        int i_index = Integer.parseInt(display_menu(c_index - 1));    // item index

        System.out.println("Enter the amount you wish to order: ");
        int amount = Integer.parseInt(s.nextLine());
        System.out.println("Item added to your order.");
        System.out.println("-------------------------------");

        user.add_order(menu.get_menu(c_index - 1).get(i_index - 1), amount);
    }

    public static void change_quantity(User user){
        ArrayList<Order> order_list = user.get_order_list();
        System.out.println("Your Cart: ");
        int i = 1;
        for (Order o : order_list){
            System.out.println(i + ": " + o.get_item().get_name() + ": " + o.get_amount());
            i += 1;
        }
        System.out.println("Which item do you wish to change? ");
        int index = Integer.parseInt(s.nextLine());
        System.out.println("How many do you want instead ");
        int amount = Integer.parseInt(s.nextLine());

        user.change_quantity(index, amount);
    }

    public static void view_cart(User user){
        ArrayList<Order> order_list = user.get_order_list();
        System.out.println("Your Cart: ");
        for (Order o : order_list){
            System.out.println(o.get_item().get_name() + ": " + o.get_amount());
        }
        System.out.println("subtotal: $" + user.get_total());
        System.out.println("-------------------------------");
    }

    public static void check_out(User user){
        System.out.println("Your total is: $" + user.get_total());
        System.out.println("Delivery or Pickup? (d/p)");
        String choice = s.nextLine();
        if (choice.equals("d")){
            System.out.println("Your package will be delivered to you.");
        }
        else if (choice.equals("p")){
            System.out.println("Great, see you soon!");
        }
        else{
            System.out.println("Please enter 'd' or 'p'.");
            System.out.println("-------------------------------");
            check_out(user);
        }

    }

}



