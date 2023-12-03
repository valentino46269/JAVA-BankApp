class Deposit implements Transaksi {
    private Customer customer; // Informasi pelanggan
    private double amount; // Jumlah deposit

    public Deposit(Customer customer, double amount) {
        this.customer = customer;
        this.amount = amount;
    }

    @Override
    public void execute() {
        customer.getAccount().deposit(amount); // Melakukan deposit ke akun pelanggan
        customer.getAccount().getRiwayatTransaksi().addTransaksi(this); // Menambahkan transaksi ke riwayat transaksi pelanggan
        System.out.println("Deposit: " + amount + " Ke " + customer.getUsername()); // Mencetak informasi deposit
    }

    @Override
    public String toString() {
        return "Deposit: " + amount + " Dari " + customer.getUsername(); // Representasi string dari objek Deposit
    }
}