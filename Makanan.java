/**
 * Kelas Makanan - subkelas dari MenuItem untuk item makanan.
 * Mengimplementasikan konsep INHERITANCE dari kelas abstrak MenuItem.
 */
public class Makanan extends MenuItem {

    // Atribut tambahan khusus makanan
    private String jenisMakanan;

    /**
     * Konstruktor Makanan.
     * @param nama        Nama makanan
     * @param harga       Harga makanan
     * @param jenisMakanan Jenis makanan (mis. Nasi, Mie, Soto)
     */
    public Makanan(String nama, double harga, String jenisMakanan) {
        super(nama, harga, "Makanan"); // Memanggil konstruktor kelas induk
        this.jenisMakanan = jenisMakanan;
    }

    // Getter & Setter
    public String getJenisMakanan() { return jenisMakanan; }
    public void setJenisMakanan(String jenisMakanan) { this.jenisMakanan = jenisMakanan; }

    /**
     * Implementasi metode tampilMenu() dari kelas induk (Polimorfisme).
     * Menampilkan informasi khusus makanan.
     */
    @Override
    public void tampilMenu() {
        System.out.printf("[Makanan] %-22s | %-16s | Rp %,.0f%n",
                getNama(), jenisMakanan, getHarga());
    }

    /**
     * Format data untuk disimpan ke file.
     * Format: MAKANAN|nama|harga|jenisMakanan
     */
    @Override
    public String toFileString() {
        return "MAKANAN|" + getNama() + "|" + getHarga() + "|" + jenisMakanan;
    }
}
