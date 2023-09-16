package org.example;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class DatabaseOperator {

    private JSONObject jsonObject;

    private final String testFilePath;

    public DatabaseOperator(String testFilePath) {
        this.testFilePath = testFilePath;
    }

    public void loadJSON() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(testFilePath)));
        jsonObject = new JSONObject(content);
    }
    //add admin
    public boolean addAdmin(String username,String password){
        if (jsonObject != null) {
            JSONArray admin_account = jsonObject.getJSONArray("admin_account");
            if (admin_account != null) {
                for (int i = 0; i < admin_account.length(); i++) {
                    if (admin_account.getJSONObject(i).getString("username").equals(username)) {
                        System.out.println("The admin user already exists！");
                        return false;
                    }
                }
                JSONObject admin = new JSONObject();

                admin.put("username", username);
                admin.put("password",password);
                admin_account.put(admin);
                saveJSON();
                System.out.println("Add admin account successfully(Username:" + username);
                return true;
            } else {
                // 处理 Admin 为空的情况
                System.out.println("admin_account is null");
            }
        } else {
            // 处理 jsonObject 为空的情况
            System.out.println("jsonObject is null");
        }
        return false;
    }
    //admin login
    public boolean adminlogin(String username,String password){
        if (jsonObject != null) {
            JSONArray admin_account = jsonObject.getJSONArray("admin_account");
            if (admin_account != null) {
                for (int i = 0; i < admin_account.length(); i++) {
                    if (admin_account.getJSONObject(i).getString("username").equals(username) && admin_account.getJSONObject(i).getString("password").equals(password)) {
                        System.out.println("Login Success！");
                        return true;
                    }
                }

            } else {
                // 处理 menu_items 为空的情况
                System.out.println("admin_account dataset is null");
            }
        } else {
            // 处理 jsonObject 为空的情况
            System.out.println("jsonObject is null");
        }
        return false;
    }
    // add an item to the database
    public void addItem(String type,String name, String description, double price) {
        if (jsonObject != null) {
            JSONArray menuItems = jsonObject.getJSONArray("menu_items");
            if (menuItems != null) {

                JSONObject newItem = new JSONObject();
                int id = menuItems.length() + 1;
                newItem.put("id", id);
                newItem.put("type",type);
                newItem.put("name", name);
                newItem.put("description", description);
                newItem.put("price", price);
                menuItems.put(newItem);
                saveJSON();
                System.out.println("Add successfully (ID:" + id + " type:" + type + " name:" + name + " description" + description + " price:" + price + ")");

            } else {
                // 处理 menu_items 为空的情况
                System.out.println("menu_items is null");
            }
        } else {
            // 处理 jsonObject 为空的情况
            System.out.println("jsonObject is null");
        }
    }

    // remove an item to the database
    public void removeItem(String name) {
        JSONArray menuItems = jsonObject.getJSONArray("menu_items");

        for (int i = 0; i < menuItems.length(); i++) {
            if (menuItems.getJSONObject(i).getString("name").equals(name)) {
                menuItems.remove(i);
                saveJSON();
                System.out.println("Success remove "+ name+".");
                return;
            }
        }
        System.out.println("Can not find "+name+" in the menu.");
        return;
    }

    // modify item information by searching a unique id
    public void modifyItem(String oldname, String newName, String newDescription, double newPrice) {
        JSONArray menuItems = jsonObject.getJSONArray("menu_items");
        for (int i = 0; i < menuItems.length(); i++) {
            JSONObject item = menuItems.getJSONObject(i);
            if (item.getString("name").equals(oldname)) {
                item.put("name", newName);
                item.put("description", newDescription);
                item.put("price", newPrice);
                saveJSON();
                System.out.println("Success modify "+newName+".");
                return;
            }
        }
        System.out.println("Can not find the item, fail to modify.");
        return;

    }

    // print all items in the database
    public void printAllItems() {
        JSONArray menuItems = jsonObject.getJSONArray("menu_items");
        for (int i = 0; i < menuItems.length(); i++) {
            JSONObject item = menuItems.getJSONObject(i);
            System.out.println("Name: " + item.getString("name") + ", Type: " + item.getString("type") +", Description: " + item.getString("description") + ", Price: " + item.getDouble("price"));
        }
    }

    public void printItems(String type) {
        JSONArray menuItems = jsonObject.getJSONArray("menu_items");
        for (int i = 0; i < menuItems.length(); i++) {
            JSONObject item = menuItems.getJSONObject(i);
            if(Objects.equals(item.getString("type"), "type")){
                System.out.println("Name: " + item.getString("name") + ", Type: " + item.getString("type") + ", Description: " + item.getString("description") + ", Price: " + item.getDouble("price"));
            }
        }
    }

    // print the information of an item
    public void printItem(String name) {
        JSONArray menuItems = jsonObject.getJSONArray("menu_items");
        for (int i = 0; i < menuItems.length(); i++) {
            JSONObject item = menuItems.getJSONObject(i);
            if (Objects.equals(item.getString("name"), name)) {
                System.out.println("Name: " + item.getString("name") + ", Description: " + item.getString("description") + ", Price: " + item.getDouble("price"));
                break;
            }
        }
    }

    // print all orders
    public void printAllOrders() {
        JSONArray orderHistory = jsonObject.getJSONArray("order_history");
        for (int i = 0; i < orderHistory.length(); i++) {
            JSONObject order = orderHistory.getJSONObject(i);
            System.out.println("Order ID: " + order.getInt("order_id") + ", Date: " + order.getString("order_date") + ", Total Amount: " + order.getDouble("total_amount"));
        }
    }

    // print a specific order
    public void printOrder(int orderId) {
        JSONArray orderHistory = jsonObject.getJSONArray("order_history");
        for (int i = 0; i < orderHistory.length(); i++) {
            JSONObject order = orderHistory.getJSONObject(i);
            if (order.getInt("order_id") == orderId) {
                System.out.println("Order ID: " + order.getInt("order_id") + ", Date: " + order.getString("order_date") + ", Total Amount: " + order.getDouble("total_amount"));
                JSONArray items = order.getJSONArray("items");
                for (int j = 0; j < items.length(); j++) {
                    JSONObject item = items.getJSONObject(j);
                    System.out.println("  Item ID: " + item.getInt("item_id") + ", Quantity: " + item.getInt("quantity"));
                }
                break;
            }
        }
    }

    private void saveJSON() {
        try (FileWriter file = new FileWriter(testFilePath)) {
            file.write(jsonObject.toString(4));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}