public class Withdraw implements Transaksi {
    private Customer customer; // Pelanggan yang melakukan penarikan
    private double amount; // Jumlah uang yang ditarik

    public Withdraw(Customer customer, double amount) {
        this.customer = customer;
        this.amount = amount;
    }

    @Override
    public void execute() {
        if (customer.getAccount().getBalance() >= amount) { // Jika saldo mencukupi
            customer.getAccount().withdraw(amount); // Melakukan penarikan
            customer.getAccount().getRiwayatTransaksi().addTransaksi(this); // Menambahkan transaksi ke riwayat transaksi pelanggan
            System.out.println("Penarikan: " + amount + " oleh " + customer.getUsername()); // Mencetak informasi penarikan
        } else {
            System.out.println("Saldo tidak mencukupi untuk melakukan penarikan."); // Jika saldo tidak mencukupi
        }
    }

    @Override
    public String toString() {
        return "Penarikan: " + amount + " oleh " + customer.getUsername(); // Representasi string dari objek Withdraw
    }
}