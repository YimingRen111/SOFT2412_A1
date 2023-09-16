import org.example.DatabaseOperator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseOperatorTest {

    private DatabaseOperator databaseOperator;

    @BeforeEach
    void setUp() throws IOException {
        String TEST_FILE_PATH = "src/main/resources/TestDatabase.json";
        databaseOperator = new DatabaseOperator(TEST_FILE_PATH);
        databaseOperator.loadJSON();
    }

    @Test
    void testLoadJSON() throws IOException {
        databaseOperator.loadJSON();
        assertNotNull(databaseOperator);  // Check if it has been loaded without any exceptions
    }

    @Test
    void testAddAdminSuccessfully() {
        assertTrue(databaseOperator.addAdmin("admin2", "password456"));
    }

    @Test
    void testAddAdminAlreadyExists() {
        databaseOperator.addAdmin("admin1", "password123");
        assertFalse(databaseOperator.addAdmin("admin1", "password123"));
    }

    @Test
    void testAdminLoginSuccess() {
        databaseOperator.addAdmin("admin1", "password123");
        assertTrue(databaseOperator.adminlogin("admin1", "password123"));
    }

    @Test
    void testAdminLoginFailureDueToPassword() {
        databaseOperator.addAdmin("admin1", "password123");
        assertFalse(databaseOperator.adminlogin("admin1", "wrongpass"));
    }

    @Test
    void testAdminLoginFailureDueToNonExistentUser() {
        assertFalse(databaseOperator.adminlogin("nonexistentuser", "somepass"));
    }

    @Test
    void testAddItem() {
        databaseOperator.addItem("food", "burger", "delicious", 10.5);
        // Assuming we added a getter or some way to validate the addition
    }

    @Test
    void testRemoveItem() {
        databaseOperator.addItem("food", "burger", "delicious", 10.5);
        databaseOperator.removeItem("burger");
        // Assuming we added a getter or some way to validate the removal
    }

    @Test
    void testRemoveItemNotExisting() {
        databaseOperator.removeItem("nonexistentburger");
    }

    @Test
    void testModifyItem() {
        databaseOperator.addItem("food", "burger", "delicious", 10.5);
        databaseOperator.modifyItem("burger", "burger deluxe", "very delicious", 12.5);
    }

    @Test
    void testModifyItemNotExisting() {
        databaseOperator.modifyItem("nonexistentburger", "new name", "new desc", 10.5);
    }

    @Test
    void testPrintAllItems() {
        databaseOperator.printAllItems();
        // This is more of a visual check. Ideally, we'd capture the output and verify.
    }

    @Test
    void testPrintItem() {
        databaseOperator.addItem("food", "burger", "delicious", 10.5);
        databaseOperator.printItem("burger");
        // this is more of a visual check. We'd ideally capture the output and verify.
    }

    @Test
    void testPrintItemNotExisting() {
        databaseOperator.printItem("nonexistentburger");
    }

    @Test
    void testPrintAllOrders() {
        // Assuming some orders are added in setup or another test
        databaseOperator.printAllOrders();
    }

    @Test
    void testPrintOrder() {
        // Add order first, then print
    }

    @Test
    void testPrintOrderNotExisting() {
        databaseOperator.printOrder(999); // Assuming order with id 999 doesn't exist
    }

}

