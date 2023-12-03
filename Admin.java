import java.util.ArrayList;

class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
    }

    public String getPassword() {
        return password;
    }

    // menampilkan daftar seluruh customer di BankApp
    public void viewAllCustomers(Bank bank) {
        ArrayList<Customer> customers = bank.getCustomers();
        System.out.println("=== DATA CUSTOMER ===");
        for (Customer customer : customers) {
            System.out.println("Username: " + customer.getUsername());
        }
    }
}