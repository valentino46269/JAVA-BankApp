class Account {
    private double balance; 
    private RiwayatTransaksi riwayattransaksi;

    public Account() {
        this.balance = 0.0;
        this.riwayattransaksi = new RiwayatTransaksi();
    }

    public double getBalance() { // metode untuk Cek Saldo
        return balance;
    }

    public void deposit(double amount) {  // metode untuk Deposit
        balance += amount;
    }

    public void withdraw(double amount) {  // metode untuk Tarik Saldo
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("SALDO TIDAK CUKUP!");  // menampilkan pesan kesalahan jika saldo tidak cukup
        }
    }

    public RiwayatTransaksi getRiwayatTransaksi() {  // metode untuk melihat riwayat transaksi
        return riwayattransaksi;
    }
}