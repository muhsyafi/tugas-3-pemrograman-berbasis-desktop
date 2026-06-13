/**
 * Kelas Diskon - subkelas dari MenuItem untuk item dengan diskon.
 * Mengimplementasikan konsep INHERITANCE dari kelas abstrak MenuItem.
 */
public class Diskon extends MenuItem {

    // Atribut tambahan khusus diskon
    private double persentaseDiskon; // 0.0 - 100.0

    /**
     * Konstruktor Diskon.
     * @param nama             Nama item berDiskon
     * @param harga            Harga asli sebelum diskon
     * @param persentaseDiskon Besar diskon dalam persen (0-100)
     */
    public Diskon(String nama, double harga, double persentaseDiskon) {
        super(nama, harga, "Diskon");
        setPersentaseDiskon(persentaseDiskon); // Gunakan setter agar ada validasi
    }

    // Getter & Setter
    public double getPersentaseDiskon() { return persentaseDiskon; }
    public void setPersentaseDiskon(double persentaseDiskon) {
        if (persentaseDiskon < 0 || persentaseDiskon > 100)
            throw new IllegalArgumentException("Persentase diskon harus antara 0 hingga 100.");
        this.persentaseDiskon = persentaseDiskon;
    }

    /**
     * Menghitung harga setelah diskon diterapkan.
     * @return harga setelah dikurangi diskon
     */
    public double getHargaSetelahDiskon() {
        return getHarga() * (1.0 - persentaseDiskon / 100.0);
    }

    /**
     * Implementasi metode tampilMenu() dari kelas induk (Polimorfisme).
     * Menampilkan informasi diskon termasuk harga sebelum dan sesudah diskon.
     */
    @Override
    public void tampilMenu() {
        System.out.printf("[Diskon]  %-22s | Diskon: %5.1f%% | Rp %,.0f --> Rp %,.0f%n",
                getNama(), persentaseDiskon, getHarga(), getHargaSetelahDiskon());
    }

    /**
     * Format data untuk disimpan ke file.
     * Format: DISKON|nama|harga|persentaseDiskon
     */
    @Override
    public String toFileString() {
        return "DISKON|" + getNama() + "|" + getHarga() + "|" + persentaseDiskon;
    }
}
