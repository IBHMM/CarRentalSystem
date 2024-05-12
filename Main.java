import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CarRentalSystem carRentalSystem = new CarRentalSystem();
        int adminpassword = 0;

        System.out.println("Welcome to the Car Rental System!");
        System.out.println("Please sign up to continue.");
        System.out.println("Who are you");
        System.out.println("1. Admin");
        System.out.println("2. Customer");
        int type = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        if (type == 1) {
            System.out.println("Enter admin password : ");
            adminpassword = scanner.nextInt();
            scanner.nextLine();
        }
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter your gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter your address: ");
        String address = scanner.nextLine();

        try {
            carRentalSystem.signUp(username, password, name, surname, gender, age, address, adminpassword);
            System.out.println("Sign up successful!");
        } catch (IllegalArgumentException e) {
            System.out.println("Sign up failed: " + e.getMessage());
            return;
        }

        System.out.println(carRentalSystem.isAdmin());
        System.out.println("\nAvailable Actions:");
        System.out.println("1. List all available sedans");
        System.out.println("2. List all available SUVs");
        System.out.println("3. List all available trucks");
        System.out.println("4. List cars by price range");
        System.out.println("5. Rent a car");
        System.out.println("6. Show profile information");
        System.out.println("7. Edit profile");
        if (carRentalSystem.isAdmin()) {
            System.out.println("Admin Methods : ");
            System.out.println("8. Add a Vehicle");
            System.out.println("9. Remove a Vehicle");
            System.out.println("10. List all rented Vehicles");
            System.out.println("11. List info of specific user");
        }

        System.out.println("12. Exit");

        // Handle user actions
        while (true) {
            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    carRentalSystem.listSedans();
                    break;
                case 2:
                    carRentalSystem.listSUVs();
                    break;
                case 3:
                    carRentalSystem.listTrucks();
                    break;
                case 4:
                    System.out.print("Enter minimum price: ");
                    double minPrice = scanner.nextDouble();
                    System.out.print("Enter maximum price: ");
                    double maxPrice = scanner.nextDouble();
                    carRentalSystem.listCarsByPriceRange(minPrice, maxPrice);
                    break;
                case 5:
                    System.out.print("Enter the registration number of the vehicle you want to rent: ");
                    String registrationNumber = scanner.nextLine();
                    System.out.print("Enter the number of days you want to rent: ");
                    int numberOfDays = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    try {
                        double bill = carRentalSystem.rentCar(username, registrationNumber, numberOfDays);
                        System.out.println("Total bill: $" + bill);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Renting failed: " + e.getMessage());
                    }
                    break;
                case 6:
                    carRentalSystem.showProfileInfo(username);
                    break;
                case 7:
                    System.out.println("Enter new profile information:");
                    System.out.print("Password: ");
                    password = scanner.nextLine();
                    System.out.print("Name: ");
                    name = scanner.nextLine();
                    System.out.print("Surname: ");
                    surname = scanner.nextLine();
                    System.out.print("Gender: ");
                    gender = scanner.nextLine();
                    System.out.print("Age: ");
                    age = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Address: ");
                    address = scanner.nextLine();
                    carRentalSystem.editProfile(username, password, name, surname, gender, age, address);
                    break;
                case 8:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }
}
