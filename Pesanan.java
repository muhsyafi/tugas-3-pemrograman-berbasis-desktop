import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Kelas Pesanan - mencatat pesanan satu pelanggan.
 * Menyimpan daftar ItemPesanan dalam ArrayList dan menghitung total biaya.
 */
public class Pesanan {

    private String namaPelanggan;
    private ArrayList<ItemPesanan> daftarPesanan;
    private LocalDateTime waktuPesan;

    private static final DateTimeFormatter FORMAT_WAKTU =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    /**
     * Konstruktor Pesanan.
     * @param namaPelanggan Nama pelanggan yang memesan
     */
    public Pesanan(String namaPelanggan) {
        if (namaPelanggan == null || namaPelanggan.trim().isEmpty())
            throw new IllegalArgumentException("Nama pelanggan tidak boleh kosong.");
        this.namaPelanggan = namaPelanggan.trim();
        this.daftarPesanan = new ArrayList<>();
        this.waktuPesan = LocalDateTime.now();
    }

    /**
     * Menambahkan item ke daftar pesanan.
     * @param item   Item yang dipesan
     * @param jumlah Jumlah yang dipesan
     */
    public void tambahItem(MenuItem item, int jumlah) {
        daftarPesanan.add(new ItemPesanan(item, jumlah));
    }

    /**
     * Menghitung total biaya seluruh pesanan.
     * Diskon sudah diperhitungkan melalui getSubtotal() di ItemPesanan.
     * @return total pembayaran
     */
    public double hitungTotal() {
        double total = 0;
        // Struktur pengulangan untuk menjumlahkan subtotal setiap item
        for (ItemPesanan ip : daftarPesanan) {
            total += ip.getSubtotal();
        }
        return total;
    }

    /**
     * Menampilkan struk pesanan ke konsol.
     */
    public void tampilkanStruk() {
        System.out.println(getStrukString());
    }

    /**
     * Menghasilkan teks struk pesanan (digunakan untuk tampil dan simpan file).
     * @return String berisi struk lengkap
     */
    public String getStrukString() {
        final int LEBAR = 55;
        final String GARIS_TEBAL = "  +" + "=".repeat(LEBAR) + "+";
        final String GARIS_TIPIS = "  +" + "-".repeat(LEBAR) + "+";

        StringBuilder sb = new StringBuilder();

        // Header struk
        sb.append(GARIS_TEBAL).append("\n");
        sb.append(barisTengah("RESTORAN NUSANTARA RASA", LEBAR)).append("\n");
        sb.append(barisTengah("STRUK PESANAN PELANGGAN", LEBAR)).append("\n");
        sb.append(GARIS_TEBAL).append("\n");

        // Informasi pelanggan
        sb.append(barisTeks("Pelanggan : " + namaPelanggan, LEBAR)).append("\n");
        sb.append(barisTeks("Waktu     : " + waktuPesan.format(FORMAT_WAKTU), LEBAR)).append("\n");
        sb.append(GARIS_TIPIS).append("\n");

        // Header kolom
        sb.append(String.format("  | %-26s %5s %19s |\n", "Item", "Qty", "Subtotal"));
        sb.append(GARIS_TIPIS).append("\n");

        // Daftar item pesanan (pengulangan)
        for (ItemPesanan ip : daftarPesanan) {
            String nama = ip.getMenuItem().getNama();
            if (nama.length() > 26) nama = nama.substring(0, 23) + "...";
            sb.append(String.format("  | %-26s %5d %19s |\n",
                    nama,
                    ip.getJumlah(),
                    formatRp(ip.getSubtotal())));
        }

        // Total
        sb.append(GARIS_TIPIS).append("\n");
        sb.append(String.format("  | %-32s %22s |\n", "TOTAL PEMBAYARAN", formatRp(hitungTotal())));
        sb.append(GARIS_TEBAL).append("\n");
        sb.append("  Terima kasih telah berkunjung! Selamat menikmati.\n");

        return sb.toString();
    }

    // ── Helper ────────────────────────────────────────────────────────────────

    /** Membuat baris teks rata tengah dalam kotak struk */
    private String barisTengah(String teks, int lebar) {
        int pad = (lebar - teks.length()) / 2;
        String kiri = " ".repeat(Math.max(0, pad));
        String kanan = " ".repeat(Math.max(0, lebar - pad - teks.length()));
        return "  |" + kiri + teks + kanan + "|";
    }

    /** Membuat baris teks rata kiri dalam kotak struk */
    private String barisTeks(String teks, int lebar) {
        if (teks.length() > lebar - 1) teks = teks.substring(0, lebar - 4) + "...";
        return String.format("  | %-" + (lebar) + "s|", teks);
    }

    /** Format angka ke format Rupiah */
    private String formatRp(double nilai) {
        return String.format("Rp %,.0f", nilai);
    }

    // ── Getter ────────────────────────────────────────────────────────────────

    public String getNamaPelanggan() { return namaPelanggan; }
    public ArrayList<ItemPesanan> getDaftarPesanan() { return daftarPesanan; }
    public LocalDateTime getWaktuPesan() { return waktuPesan; }
    public boolean isEmpty() { return daftarPesanan.isEmpty(); }
}
