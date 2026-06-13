/**
 * Kelas Minuman - subkelas dari MenuItem untuk item minuman.
 * Mengimplementasikan konsep INHERITANCE dari kelas abstrak MenuItem.
 */
public class Minuman extends MenuItem {

    // Atribut tambahan khusus minuman
    private String jenisMinuman;

    /**
     * Konstruktor Minuman.
     * @param nama         Nama minuman
     * @param harga        Harga minuman
     * @param jenisMinuman Jenis minuman (mis. Panas, Dingin, Jus)
     */
    public Minuman(String nama, double harga, String jenisMinuman) {
        super(nama, harga, "Minuman"); // Memanggil konstruktor kelas induk
        this.jenisMinuman = jenisMinuman;
    }

    // Getter & Setter
    public String getJenisMinuman() { return jenisMinuman; }
    public void setJenisMinuman(String jenisMinuman) { this.jenisMinuman = jenisMinuman; }

    /**
     * Implementasi metode tampilMenu() dari kelas induk (Polimorfisme).
     * Menampilkan informasi khusus minuman.
     */
    @Override
    public void tampilMenu() {
        System.out.printf("[Minuman] %-22s | %-16s | Rp %,.0f%n",
                getNama(), jenisMinuman, getHarga());
    }

    /**
     * Format data untuk disimpan ke file.
     * Format: MINUMAN|nama|harga|jenisMinuman
     */
    @Override
    public String toFileString() {
        return "MINUMAN|" + getNama() + "|" + getHarga() + "|" + jenisMinuman;
    }
}
