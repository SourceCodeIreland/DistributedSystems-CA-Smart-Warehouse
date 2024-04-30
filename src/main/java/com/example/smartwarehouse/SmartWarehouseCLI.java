import java.util.Scanner;

public class SmartWarehouseCLI {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SmartWarehouseController controller = new SmartWarehouseController();

        System.out.println("Welcome to the Smart Warehouse CLI!");

        boolean exit = false;
        while (!exit) {
            System.out.println("Choose an option:");
            System.out.println("1. View inventory");
            System.out.println("2. Add item to inventory");
            System.out.println("3. Remove item from inventory");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    controller.viewInventory();
                    break;
                case 2:
                    System.out.println("Enter the item name:");
                    String itemName = scanner.nextLine();
                    System.out.println("Enter the item quantity:");
                    int quantity = scanner.nextInt();
                    controller.addItemToInventory(itemName, quantity);
                    break;
                case 3:
                    System.out.println("Enter the item name to remove:");
                    String itemToRemove = scanner.nextLine();
                    controller.removeItemFromInventory(itemToRemove);
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        System.out.println("Thank you for using the Smart Warehouse CLI!");
        scanner.close();
    }
}

class SmartWarehouseController {

    public void viewInventory() {
        // Placeholder code to view inventory
        System.out.println("Inventory viewed.");
    }

    public void addItemToInventory(String itemName, int quantity) {
        // Placeholder code to add item to inventory
        System.out.println("Item '" + itemName + "' added to inventory with quantity " + quantity + ".");
    }

    public void removeItemFromInventory(String itemName) {
        // Placeholder code to remove item from inventory
        System.out.println("Item '" + itemName + "' removed from inventory.");
    }
}
