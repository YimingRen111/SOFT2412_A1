package org.menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private ArrayList<ArrayList<Item>> menu = new ArrayList<ArrayList<Item>>();
    private ArrayList<String> category = new ArrayList<String>();


    public Menu(String path) {
        try {
            File menu_file = new File(path);
            Scanner r = new Scanner(menu_file);
            while (r.hasNextLine()) {
                String line = r.nextLine();
                if (line.startsWith("#")){
                    String name = line.substring(2);
                    String description = r.nextLine().substring(2);
                    float price = Float.parseFloat(r.nextLine().substring(2));
                    Item item = new Item(name, description, price);
                    menu.get(menu.size() - 1).add(item);
                }
                else if (line.isEmpty()) {
                    continue;
                }
                else{
                    category.add(line);
                    ArrayList<Item> items = new ArrayList<Item>();
                    menu.add(items);
                }
            }
            r.close();
        } catch (FileNotFoundException e) {
            System.out.println("our menu is not up yet");
        }


    }

    public ArrayList<Item> get_menu(int index){
        return menu.get(index);
    }

    public ArrayList<String> get_category(){
        return category;
    }





}
