/**
 * Kelas ItemPesanan - merepresentasikan satu baris dalam pesanan pelanggan.
 * Menyimpan referensi ke MenuItem beserta jumlah yang dipesan.
 */
public class ItemPesanan {

    private MenuItem menuItem; // Item yang dipesan (bisa Makanan, Minuman, atau Diskon)
    private int jumlah;        // Banyaknya item yang dipesan

    /**
     * Konstruktor ItemPesanan.
     * @param menuItem Item yang dipesan
     * @param jumlah   Jumlah yang dipesan (harus > 0)
     */
    public ItemPesanan(MenuItem menuItem, int jumlah) {
        if (jumlah <= 0)
            throw new IllegalArgumentException("Jumlah pesanan harus lebih dari 0.");
        this.menuItem = menuItem;
        this.jumlah = jumlah;
    }

    // Getter
    public MenuItem getMenuItem() { return menuItem; }
    public int getJumlah() { return jumlah; }

    /**
     * Menghitung subtotal item ini.
     * Jika item adalah Diskon, gunakan harga setelah diskon.
     * Menggunakan instanceof untuk cek tipe (Polimorfisme).
     *
     * @return subtotal = harga (efektif) x jumlah
     */
    public double getSubtotal() {
        if (menuItem instanceof Diskon) {
            // Jika item adalah Diskon, pakai harga setelah diskon
            return ((Diskon) menuItem).getHargaSetelahDiskon() * jumlah;
        }
        return menuItem.getHarga() * jumlah;
    }
}
