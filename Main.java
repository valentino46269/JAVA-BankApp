import java.util.Scanner;

public class Main {
    private static Bank bank = new Bank(); // Bank sebagai database pelanggan dan admin
    private static Admin admin; // deklarasi variabel admin
    private static Scanner scanner = new Scanner(System.in); // deklarasi variabel scanner

    // Metode Main
    public static void main(String[] args) {
        while (true) {
            // Tampilan menu utama
            System.out.println("\n==== Selamat Datang di Valens Bank ====");
            System.out.println("1. Login Customer");
            System.out.println("2. Login Admin ");
            System.out.println("3. Register");
            System.out.println("0. Exit App");
            System.out.println("=======================================");
            System.out.println("Masukan Pilihan:");
            int choice = scanner.nextInt();
            scanner.nextLine();
            // Pemrosesan pilihan
            switch (choice) {
                case 1:
                    userLogin(bank);
                    break;
                case 2:
                    adminLogin();
                    break;
                case 3:
                    register();
                    break;
                case 0:
                    System.out.println("Terimakasih telah menggunakan Valens Bank! :)");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Tidak ada pilihan tersebut, coba lagi!");
            }
        }
    }

    // Metode untuk Login Customer
    private static void userLogin(Bank bank) {
        System.out.println("Masukan username:");
        String username = scanner.nextLine();
        System.out.println("Masukan password:");
        String password = scanner.nextLine();
        for (Customer customer : bank.getCustomers()) {
            if (customer.getUsername().equals(username) && customer.authenticate(password)) {
                performUserActions(customer);
                return;
            }
        }
        System.out.println("Username atau password salah.");
    }

    // Metode untuk login admin
    private static void adminLogin() {
        System.out.println("Masukkan username admin:");
        String username = scanner.nextLine();
        System.out.println("Masukkan password admin:");
        String password = scanner.nextLine();
        Admin admin = bank.findAdminByUsername(username);
        if (admin != null && admin.getPassword().equals(password)) {
            performAdminActions(admin, bank);
        } else {
            System.out.println("Login gagal. Username atau password admin salah.");
        }
    }

    // Metode untuk registrasi
    private static void register() {
        System.out.println("Masukkan username:");
        String username = scanner.nextLine();
        System.out.println("Masukkan password:");
        String password = scanner.nextLine();
        System.out.println("Pilih jenis registrasi:");
        System.out.println("1. Daftar sebagai Admin");
        System.out.println("2. Daftar sebagai Customer");
        int registrationChoice = scanner.nextInt();
        scanner.nextLine();
        switch (registrationChoice) {
            case 1:
                Admin newAdmin = new Admin(username, password);
                bank.addAdmin(newAdmin);
                System.out.println("Registrasi Admin berhasil!");
                break;
            case 2:
                Customer newCustomer = new Customer(username, password);
                bank.addCustomer(newCustomer);
                System.out.println("Registrasi Customer berhasil!");
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }
    }

    // Metode untuk aksi customer setelah login
    private static void performUserActions(Customer customer) {
        while (true) {
            System.out.println("\nSelamat datang " + customer.getUsername() + "!");
            System.out.println("=== MENU CUSTOMER ===");
            System.out.println("1. Cek Saldo");
            System.out.println("2. Deposit");
            System.out.println("3. Transfer");
            System.out.println("4. Tarik Saldo");
            System.out.println("5. Riwayat Transaksi");
            System.out.println("6. Informasi Akun");
            System.out.println("0. Keluar");
            System.out.println("======================");
            System.out.println("Masukan pilihan:");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Saldo saat ini: " + customer.getAccount().getBalance());
                    break;
                case 2:
                    try {
                        System.out.println("Masukan jumlah deposit:");
                        double depositAmount = scanner.nextDouble();
                        new Deposit(customer, depositAmount).execute();
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Masukkan dalam bentuk angka!");
                        scanner.nextLine();
                    }
                    break;
                case 3:
                    System.out.println("Masukan username tujuan :");
                    String destinationUsername = scanner.nextLine();
                    Customer destinationCustomer = findCustomerByUsername(destinationUsername);
                    if (destinationCustomer != null) {
                        System.out.println("Masukan jumlah transfer :");
                        double transferAmount = scanner.nextDouble();
                        new Transfer(customer, destinationCustomer, transferAmount).execute();
                    } else {
                        System.out.println("Username tujuan tidak ditemukan.");
                    }
                    break;
                case 4:
                    try {
                        System.out.println("Masukan jumlah penarikan:");
                        double withdrawAmount = scanner.nextDouble();
                        new Withdraw(customer, withdrawAmount).execute();
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Masukkan dalam bentuk angka!");
                        scanner.nextLine();
                    }
                    break;
                case 5:
                    customer.getAccount().getRiwayatTransaksi().showRiwayatTransaksi();
                    break;
                case 6:
                    System.out.println("=== Informasi Akun ===");
                    System.out.println("Nama: " + customer.getUsername());
                    System.out.println("Saldo saat ini: " + customer.getAccount().getBalance());
                    break;
                case 0:
                    System.out.println("Keluar...");
                    return;
                default:
                    System.out.println("Tidak ada pilihan tersebut, coba lagi!");
            }
        }
    }

    // Metode untuk aksi admin setelah login
    private static void performAdminActions(Admin admin, Bank bank) {
        while (true) {
            System.out.println("\n===== MENU ADMIN =====");
            System.out.println("1. Lihat semua Customers");
            System.out.println("2. Riwayat Transaksi Customer Tertentu");
            System.out.println("3. Riwayat Transaksi Semua Customer");
            System.out.println("0. Keluar");
            System.out.println("=======================");
            System.out.println("Masukan pilihan:");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    if (bank.getCustomers().isEmpty()) {
                        System.out.println("Belum ada customer.");
                    } else {
                        admin.viewAllCustomers(bank);
                    }
                    break;
                case 2:
                    System.out.println("Masukkan username Customer:");
                    String customerUsername = scanner.nextLine();
                    Customer customer = bank.findCustomerByUsername(customerUsername);
                    if (customer != null) {
                        RiwayatTransaksi riwayatTransaksi = customer.getAccount().getRiwayatTransaksi();
                        if (riwayatTransaksi.getTransaksis().isEmpty()) {
                            System.out.println("Customer tersebut belum melakukan transaksi.");
                        } else {
                            riwayatTransaksi.showRiwayatTransaksi();
                        }
                    } else {
                        System.out.println("Customer dengan username tersebut tidak ditemukan.");
                    }
                    break;
                case 3:
                    System.out.println("\n===== RIWAYAT TRANSAKSI SEMUA CUSTOMER =====");
                    boolean noTransaksi = true;
                    for (Customer c : bank.getCustomers()) {
                        System.out.println("\nRiwayat transaksi untuk Customer: " + c.getUsername());
                        RiwayatTransaksi riwayatTransaksi = c.getAccount().getRiwayatTransaksi();
                        if (!riwayatTransaksi.getTransaksis().isEmpty()) {
                            riwayatTransaksi.showRiwayatTransaksi();
                            noTransaksi = false;
                        }
                    }
                    if (noTransaksi) {
                        System.out.println("Tidak ada transaksi dari semua customer.");
                    }
                    break;
                case 0:
                    System.out.println("Keluar...");
                    return;
                default:
                    System.out.println("Tidak ada pilihan tersebut, coba lagi!");
            }
        }
    }

    // Mencari Customer berdasarkan username dalam daftar pelanggan yang dimiliki oleh Bank.
    private static Customer findCustomerByUsername(String username) {
        for (Customer customer : bank.getCustomers()) {
            if (customer.getUsername().equals(username)) {
                return customer;
            }
        }
        return null;
    }
}