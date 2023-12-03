import java.util.ArrayList;

class Bank {
    private ArrayList<Customer> customers;
    private ArrayList<Admin> admins;

    public Bank() {
        this.customers = new ArrayList<>();
        this.admins = new ArrayList<>();
    }

    // Mendapatkan daftar semua customer dalam bank
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    // Menambahkan pelanggan baru ke dalam bank
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    // Menambahkan admin baru ke dalam bank
    public void addAdmin(Admin admin) {
        admins.add(admin);
    }

    // Mencari pelanggan berdasarkan username
    public Customer findCustomerByUsername(String username) {
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username)) {
                return customer;
            }
        }
        return null; // Return null jika tidak ditemukan
    }

     // Metode untuk mencari admin berdasarkan username
    public Admin findAdminByUsername(String username) {
        for (Admin admin : admins) {
            if (admin.getUsername().equals(username)) {
                return admin;
            }
        }
        return null; // Return null jika admin tidak ditemukan
    }
}