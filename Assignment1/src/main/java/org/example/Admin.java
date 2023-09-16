package org.example;
import java.io.IOException;
import java.util.Scanner;

public class Admin{
    private static final String FILE_PATH = "src/main/resources/Database.json";
    private static DatabaseOperator dataset = new DatabaseOperator(FILE_PATH);
    private static final Scanner s = new Scanner(System.in);
    private String name;
    private String description;
    private String type;
    private double price;
    private String option;


    public Admin(){
        try {
            dataset.loadJSON();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    public void addFood(){

        System.out.println("Please input the type:");
        type = s.nextLine();
        System.out.println("Please input the name:");
        name = s.nextLine();
        System.out.println("Please input the description:");
        description = s.nextLine();
        System.out.println("Please input the price:");
        price = s.nextDouble();
        s.nextLine();
        System.out.println("Type:"+type+" name:"+name+" description:"+description+" Price:"+price);
        System.out.println("Do you want to add?(y/n):");
        option = s.nextLine();
        System.out.println(option);
        if(option.equals("y")){
            dataset.addItem(type,name,description,price);

        }

    }
    public void updateMenu(){
        dataset.printAllItems();
        System.out.println("Which one do you want to Modify?(input the name):");
        name = s.nextLine();
        System.out.println("Please input the new type:");
        type = s.nextLine();
        System.out.println("Please input the new name:");
        String newname = s.nextLine();
        System.out.println("Please input the new description:");
        description = s.nextLine();
        System.out.println("Please input the new price:");
        price = s.nextDouble();
        s.nextLine();
        dataset.modifyItem(name,newname,description,price);
    }
    public void deleteFood(){
        dataset.printAllItems();
        System.out.println("Which one do you want to delete?(input the name):");
        name = s.nextLine();
        dataset.removeItem(name);
    }
    public boolean register(){
        System.out.println("Please input your Username:");
        String username = s.nextLine();
        System.out.println("Please input your password:");
        String password = s.nextLine();
        boolean success = dataset.addAdmin(username,password);
        return success;
    }
    public boolean admin_login(String username,String password){
        return dataset.adminlogin(username,password);
    }
}