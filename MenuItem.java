/**
 * Kelas abstrak MenuItem - dasar dari semua item menu restoran.
 * Mengimplementasikan konsep ABSTRAKSI dan ENKAPSULASI.
 *
 * @author Mahasiswa Universitas Terbuka
 */
public abstract class MenuItem {

    // Atribut private untuk enkapsulasi (informasi tidak bisa diakses langsung dari luar)
    private String nama;
    private double harga;
    private String kategori;

    /**
     * Konstruktor MenuItem.
     * @param nama     Nama item menu
     * @param harga    Harga item menu
     * @param kategori Kategori item menu (Makanan/Minuman/Diskon)
     */
    public MenuItem(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }

    // ── Getter & Setter (Enkapsulasi) ──────────────────────────────────────────

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public double getHarga() { return harga; }
    public void setHarga(double harga) {
        if (harga < 0) throw new IllegalArgumentException("Harga tidak boleh negatif.");
        this.harga = harga;
    }

    public String getKategori() { return kategori; }
    public void setKategori(String kategori) { this.kategori = kategori; }

    // ── Metode Abstrak (Abstraksi + Polimorfisme) ──────────────────────────────

    /**
     * Menampilkan informasi item menu ke konsol.
     * Setiap subkelas WAJIB mengimplementasikan metode ini (Polimorfisme).
     */
    public abstract void tampilMenu();

    /**
     * Mengubah objek menjadi baris teks untuk disimpan ke file.
     */
    public abstract String toFileString();
}
