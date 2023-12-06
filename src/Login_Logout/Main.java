package Login_Logout;

//import Inventory.Inventory;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login login = new Login();
        Register register = new Register();

            printMenu();
            int choice = scanner.nextInt();
            System.out.println();

            switch (choice) {
                case 1 -> {
//                    register.registerInput(scanner);
                }
                case 2 -> {
                    login.loginInput(scanner);
                }
                case 3 -> {
                    System.out.println("Exiting the program.");
                    return;
                }
                default -> System.out.println("Invalid option. Please choose a valid option.");
            }

        scanner.close();
            System.out.println(register.get_accountId());
//        Inventory inventory = new Inventory();
//        String[] weaponFound = inventory.get_weapon(login.getAccountId(login.getUsername(), login.getPassword()));

//        for (String ex: weaponFound)
//        {
//            System.out.println(ex);
//        }
    }

    private static void printMenu() {
        System.out.println("Choose an option:");
        System.out.println("1. Login_Logout.Register");
        System.out.println("2. Login_Logout.Login");
        System.out.println("3. Exit\n");
        System.out.print("Choice: ");
    }
}
