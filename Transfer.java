class Transfer implements Transaksi {
    private Customer sourceCustomer; // Pelanggan sumber dana
    private Customer destinationCustomer; // Pelanggan tujuan dana
    private double amount; // Jumlah transfer

    public Transfer(Customer sourceCustomer, Customer destinationCustomer, double amount) {
        this.sourceCustomer = sourceCustomer;
        this.destinationCustomer = destinationCustomer;
        this.amount = amount;
    }

    @Override
    public void execute() {
        sourceCustomer.getAccount().withdraw(amount); // Mengurangi saldo dari pelanggan sumber dana
        destinationCustomer.getAccount().deposit(amount); // Menambahkan saldo ke pelanggan tujuan dana
        sourceCustomer.getAccount().getRiwayatTransaksi().addTransaksi(this); // Menambahkan transaksi ke riwayat transaksi pelanggan sumber dana
        destinationCustomer.getAccount().getRiwayatTransaksi().addTransaksi(this); // Menambahkan transaksi ke riwayat transaksi pelanggan tujuan dana
        System.out.println("Transfer: " + amount + " dari " + sourceCustomer.getUsername() + " ke " + destinationCustomer.getUsername()); // Mencetak informasi transfer
    }

    @Override
    public String toString() {
        return "Transfer: " + amount + " dari " + sourceCustomer.getUsername() + " ke " + destinationCustomer.getUsername(); // Representasi string dari objek Transfer
    }

    // Method get untuk mengambil nama tujuan transfer pengguna (destinationCustomer)
    public Customer getDestinationCustomer() {
        return destinationCustomer;
    }
}