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
        System.out.print("TYPE : ");
        int type = scanner.nextInt();
        if (type == 1) {
            System.out.print("Enter admin password : ");
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
        scanner.nextLine();
        System.out.print("Enter your address: ");
        String address = scanner.nextLine();

        try {
            boolean result = carRentalSystem.signUp(username, password, name, surname, gender, age, address, adminpassword, type);
            if (result) {
                System.out.println("Sign up successful!");
            }else {
                
                return;
            }
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
        System.out.println("7. Edit profile \n");

        if (carRentalSystem.isAdmin()) {
            System.out.println("Admin Methods : ");
            System.out.println("8.  Add a Vehicle");
            System.out.println("9.  Remove a Vehicle");
            System.out.println("10. Add a User");
            System.out.println("11. Remove a User");
            System.out.println("12. List all rented Vehicles");
            System.out.println("13. List info of specific user \n");
        }

        System.out.println("14. Exit");

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
                    carRentalSystem.editProfile(carRentalSystem.TakeUserInfo());
                    break;
                case 8:
                    System.out.println("Enter new vehicle information:");
                    System.out.print("Make: ");
                    String make = scanner.nextLine();
                    System.out.print("Model: ");
                    String model = scanner.nextLine();
                    System.out.println("Type: ");
                    System.out.println("1. SEDAN");
                    System.out.println("2. SUV");
                    System.out.println("3. TRUCK");
                    System.out.print("Enter option: ");
                    int option = scanner.nextInt();
                    VehicleType typ = null;
                    switch (option) {
                        case 1:
                            typ = VehicleType.SEDAN;
                            break;
                        case 2:
                            typ = VehicleType.SUV;
                            break;
                        case 3:
                            typ = VehicleType.TRUCK;
                            break;
                        default:
                            System.out.println("Invalid option. Vehicle adding failed.");
                            return;
                    }
                    System.out.print("Price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("ID: ");
                    String id = scanner.nextLine();
            
                    if (typ != null && !make.isEmpty() && !model.isEmpty() && price > 0 && !id.isEmpty()) {
                        carRentalSystem.addVehicle(new Vehicle(id, make, model, typ, price));
                        System.out.println("Vehicle added successfully.");
                    } else {
                        System.out.println("Vehicle adding failed. Please provide valid information.");
                    }

                    break;
                case 9: 
                    System.out.println("Enter Registration number : ");
                    String registrationnumber = scanner.nextLine();
                    carRentalSystem.removeVehicle(registrationnumber);
                    break;
                case 10:
                    System.out.println("Enter new user information:");
                    carRentalSystem.addUser(carRentalSystem.TakeUserInfo());
                    break;
                case 14:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
            
        }

    }

}
