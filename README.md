# SOFT2412_Assignment

## Project Specification

### Overview
This project is a simple application that simulates a menu ordering system. It allows users to view items from a menu, add them to an order, and view their total order. Additionally, there are functionalities for admin users to manage the menu items.

### Key Classes and Functionalities:

1. **Item Class**:
    - Represents an individual menu item.
    - Attributes: `name`, `description`, and `price`.
    - Methods: `get_name()`, `get_description()`, `get_price()`, and `toString()`.

2. **Menu Class**:
    - Represents the entire menu.
    - Attributes: `menu` (a list of items) and `category`.
    - Methods: `get_menu(int index)` and `get_category()`.

3. **User Class**:
    - Represents a user of the system.
    - Attributes: `order_list` (a list of orders) and `total` (total price of all orders).
    - Methods: `add_order(Item item, int amount)`, `change_quantity(int item_number, int amount)`, `get_order_list()`, and `get_total()`.

4. **Order Class**:
    - Represents an individual order.
    - Attributes: `item` (the item being ordered) and `amount` (quantity of the item).
    - Methods: `get_item()`, `get_amount()`, and `set_amount(int new_amount)`.

5. **DatabaseOperator Class**:
    - Manages interactions with the database.
    - Attributes: `jsonObject` (the JSON representation of the database) and `testFilePath` (path to the database file).
    - Methods: `loadJSON()`, `addAdmin()`, `adminlogin()`, `addItem()`, `removeItem()`, `modifyItem()`, `printAllItems()`, `printItems()`, `printItem()`, `printAllOrders()`, and `printOrder()`.

## How to Run the Program
To run the program, please use the `gradle run` command in the terminal. Upon running, you will see the the following options in terminal as Text UI:

- Hello and welcome!

- Admin login
- Customer login
- Quit the App


## How to Test the Program
To test the program, please use the `gradle test jacocoTestReport` command after building the project. This will generate a report for the test coverage. The jacoco test report will be saved at the folder: `build/reports/jacoco/test/html`, which is named `jacoco-sessions.html`. Please open the HTML file in your browser.

## Expectations When Running and Testing
- After running the program, you should see the aforementioned options in the terminal.
- After testing the program, the jacoco test report will display the test coverage of the codebase.

## Instructions on How to Contribute/Collaborate on the GitHub Repository
1. **Fork the Repository**: Click on the 'Fork' button at the top right corner of the repository page to create a copy of the repository in your GitHub account.
2. **Clone the Repository**: Once you have forked the repository, clone it to your local machine using the `git clone` command.
3. **Create a New Branch**: Before making any changes, create a new branch using the `git checkout -b branch-name` command.
4. **Make Changes**: Make the necessary changes in your local copy of the repository.
5. **Commit Changes**: Commit the changes you made using the `git commit -m "commit message"` command.
6. **Push Changes**: Push the changes to your forked repository using the `git push origin branch-name` command.
7. **Submit a Pull Request**: Go to the 'Pull requests' tab of the original repository and click on the 'New pull request' button. Compare the changes and submit your pull request.

