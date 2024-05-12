import java.util.*;

public class User {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String gender;
    private int age;
    private String address;
    private List<Vehicle> rentedVehicles;
    private final int adminpassword = 9988;
    private Boolean isAdmin = false;

    public User(String username, String password, String name, String surname, String gender, int age, String address, int adminpassword) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        if (age >= 18) {
            this.age = age;
        } else {
            throw new IllegalArgumentException("User must be at least 18 years old.");
        }
        this.address = address;
        this.rentedVehicles = new ArrayList<>();
        this.setAdmin(adminpassword);
    }

    private void setAdmin(int adminpassword) {
        if(adminpassword == this.adminpassword) {
            this.isAdmin = true;
        }
    }

    public User(String username, String password, List<Vehicle> rentedVehicles) {
        this.username = username;
        this.password = password;
        this.rentedVehicles = rentedVehicles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 18) {
            this.age = age;
        } else {
            throw new IllegalArgumentException("User must be at least 18 years old.");
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Vehicle> getRentedVehicles() {
        return rentedVehicles;
    }

    public void setRentedVehicles(List<Vehicle> rentedVehicles) {
        this.rentedVehicles = rentedVehicles;
    }

    public void addRentedVehicle(Vehicle vehicle) {
        rentedVehicles.add(vehicle);
    }

    public void removeRentedVehicle(Vehicle vehicle) {
        rentedVehicles.remove(vehicle);
    }

    public boolean isAdmin() {
        return this.isAdmin;
    }
}
