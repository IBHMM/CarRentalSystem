import java.util.*;

class CarRentalSystem {
    private Map<String, User> users;
    private List<Vehicle> vehicles;
    private Boolean isadmin = false;

    public CarRentalSystem() {
        users = new HashMap<>();
        vehicles = new ArrayList<>();
        this.CreateMonkVehicles();

    }

    public boolean isAdmin() {
        return this.isadmin;
    }

    public void showProfileInfo(String username) {
        Scanner scanner = new Scanner(System.in);
        User user = users.get(username);
        
        if (user != null) {
            System.out.print("Enter your current password: ");
            String currentPassword = scanner.nextLine();

            if (user.getPassword().equals(currentPassword)) {
                System.out.println("Username: " + user.getUsername());
                System.out.println("Name: " + user.getName());
                System.out.println("Surname: " + user.getSurname());
                System.out.println("Gender: " + user.getGender());
                System.out.println("Age: " + user.getAge());
                System.out.println("Address: " + user.getAddress());
            }
        } else {
            System.out.println("User not found.");
        }
    }

    public void editProfile(User user) {
        Scanner scanner = new Scanner(System.in);
        
        if (user != null) {
            System.out.print("Enter your current password: ");
            String currentPassword = scanner.nextLine();
            
            if (user.getPassword().equals(currentPassword)) {
                user.setPassword(user.getPassword());
                user.setName(user.getName());
                user.setSurname(user.getSurname());
                user.setGender(user.getGender());
                user.setAge(user.getAge());
                user.setAddress(user.getAddress());
                System.out.println("Profile updated successfully.");
            } else {
                System.out.println("Invalid password. Profile update failed.");
            }
        } else {
            System.out.println("User not found.");
        }
    }
    

    public boolean signUp(String username, String password, String name, String surname, String gender, int age, String address, int adminpassword, int type) {
        if (age < 18) {
            throw new IllegalArgumentException("User must be at least 18 years old.");
        }
        User newuser = new User(username, password, name, surname, gender, age, address, adminpassword);
        this.isadmin = newuser.isAdmin();
        if (type == 1 && !this.isAdmin()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Admin password incorrect");
            System.out.print("Do you want to continue as customer (yes/no) : ");
            String answer = scanner.nextLine();
            if (answer == "yes") {
                users.put(username, newuser);
                scanner.close();
                return true;
            }else {
                scanner.close();
                return false;
            }
        }else {
            users.put(username, newuser);
            System.out.println("User successfully entered");
        }
        return true;
    }

    public void listSedans() {
        System.out.println("Sedans:");
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getType() == VehicleType.SEDAN) {
                System.out.println(vehicle);
            }
        }
    }

    public void listSUVs() {
        System.out.println("SUVs:");
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getType() == VehicleType.SUV) {
                System.out.println(vehicle);
            }
        }
    }

    public void listTrucks() {
        System.out.println("Trucks:");
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getType() == VehicleType.TRUCK) {
                System.out.println(vehicle);
            }
        }
    }

    public void listCarsByPriceRange(double minPrice, double maxPrice) {
        System.out.println("Cars within price range $" + minPrice + " - $" + maxPrice + ":");
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getRentPrice() >= minPrice && vehicle.getRentPrice() <= maxPrice) {
                System.out.println(vehicle);
            }
        }
    }

    public double rentCar(String username, String registrationNumber, int numberOfDays) {
        User user = users.get(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found.");
        }

        Vehicle vehicleToRent = null;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getRegistrationNumber().equals(registrationNumber)) {
                vehicleToRent = vehicle;
                break;
            }
        }

        if (vehicleToRent == null) {
            throw new IllegalArgumentException("Vehicle not found.");
        }

        double bill = vehicleToRent.getRentPrice() * numberOfDays;
        return bill;
    }

    public void removeVehicle(String registrationNumber) {
        if (!this.isAdmin()) {
            System.out.println("Access denied. Admin privileges required.");
            return;
        }
        Iterator<Vehicle> iterator = vehicles.iterator();
        while (iterator.hasNext()) {
            Vehicle vehicle = iterator.next();
            if (vehicle.getRegistrationNumber().equals(registrationNumber)) {
                System.out.println(vehicle.toString() + "removed successfully.");
                iterator.remove();
                return;
            }
        }
        System.out.println("Vehicle not found.");
    }

    public void listAllRentedVehicles() {
        if (!this.isAdmin()) {
            System.out.println("Access denied. Admin privileges required.");
            return;
        }
        System.out.println("List of all rented vehicles:");
    
        for (User user : users.values()) {
            System.out.println("User: " + user.getName() + " " + user.getSurname());
            List<Vehicle> rentedVehicles = user.getRentedVehicles();
            if (rentedVehicles.isEmpty()) {
                System.out.println("No rented vehicles for this user.");
            } else {
                for (Vehicle vehicle : rentedVehicles) {
                    System.out.println("\t" + vehicle.toString());
                }
            }
        }
    }
    

    public void listAllUsers() {
        if (!this.isAdmin()) {
            System.out.println("Access denied. Admin privileges required.");
            return;
        }
        System.out.println("List of all users:");
        for (User user : users.values()) {
            System.out.println(user);
        }
    }

    public void addVehicle(Vehicle vehicle) {
        if (!this.isAdmin()) {
            System.out.println("Access denied. Admin privileges required.");
            return;
        }
        vehicles.add(vehicle);
    }

    public void addUser(User user) {
        if (!this.isAdmin()) {
            System.out.println("Access denied. Admin privileges required.");
            return;
        }else {
            this.signUp(user.getUsername(), user.getPassword(), user.getName(), user.getSurname(), user.getGender(), user.getAge(), user.getAddress(), 0000, 2);
            System.out.println("User Successfully Added");
        }
    }

    public User TakeUserInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Surname: ");
        String username = scanner.nextLine();
        System.out.print("Username: ");
        String surname = scanner.nextLine();
        System.out.print("Gender: ");
        String gender = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();

        System.out.print("Address: ");
        String address = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        return  new User(username, password, name, surname, gender, age, address, age);
    } 
    

    public void CreateMonkVehicles() {
        vehicles.add(new Vehicle("ABC123", "Toyota", "Camry", VehicleType.SEDAN, 50.0));
        vehicles.add(new Vehicle("XYZ456", "Honda", "Accord", VehicleType.SEDAN, 60.0));
        vehicles.add(new Vehicle("DEF789", "Ford", "Explorer", VehicleType.SUV, 80.0));
        vehicles.add(new Vehicle("GHI101", "Chevrolet", "Cruze", VehicleType.SEDAN, 55.0));
        vehicles.add(new Vehicle("JKL202", "Nissan", "Altima", VehicleType.SEDAN, 52.0));
        vehicles.add(new Vehicle("MNO303", "Toyota", "RAV4", VehicleType.SUV, 75.0));
        vehicles.add(new Vehicle("PQR404", "Hyundai", "Sonata", VehicleType.SEDAN, 58.0));
        vehicles.add(new Vehicle("STU505", "Jeep", "Wrangler", VehicleType.SUV, 90.0));
        vehicles.add(new Vehicle("VWX606", "Kia", "Sportage", VehicleType.SUV, 70.0));
        vehicles.add(new Vehicle("YZA707", "Subaru", "Outback", VehicleType.SUV, 85.0));
        vehicles.add(new Vehicle("BCB808", "Mazda", "CX-5", VehicleType.SUV, 78.0));
        vehicles.add(new Vehicle("CDE909", "Volkswagen", "Jetta", VehicleType.SEDAN, 57.0));
        vehicles.add(new Vehicle("FGF010", "Ford", "F-150", VehicleType.TRUCK, 100.0));
        vehicles.add(new Vehicle("HIH111", "Toyota", "Tacoma", VehicleType.TRUCK, 95.0));
        vehicles.add(new Vehicle("IJI212", "Chevrolet", "Silverado", VehicleType.TRUCK, 105.0));
        vehicles.add(new Vehicle("JKJ313", "Ram", "1500", VehicleType.TRUCK, 98.0));
        vehicles.add(new Vehicle("KAK414", "GMC", "Sierra", VehicleType.TRUCK, 102.0));
        vehicles.add(new Vehicle("LML515", "Ford", "Escape", VehicleType.SUV, 73.0));
        vehicles.add(new Vehicle("MNM616", "Honda", "Pilot", VehicleType.SUV, 82.0));
        vehicles.add(new Vehicle("NON717", "Nissan", "Rogue", VehicleType.SUV, 68.0));
    }   

    public void CreateMonkUsers() {
        
    }
}