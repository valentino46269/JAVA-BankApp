import java.util.ArrayList;

// Class untuk menyimpan informasi transaksi
class RiwayatTransaksi {
    private ArrayList<Transaksi> transaksi;

    public RiwayatTransaksi() {
        this.transaksi = new ArrayList<>();
    }

    public void addTransaksi(Transaksi transaksi) {
        this.transaksi.add(transaksi);
    }

    public void showRiwayatTransaksi() {
        for (Transaksi transaksi : transaksi) {
            System.out.println(transaksi.toString());
        }
    }

    public ArrayList<Transaksi> getTransaksis() {
        return transaksi;
    }
}