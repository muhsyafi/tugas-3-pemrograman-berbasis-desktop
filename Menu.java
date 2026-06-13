import java.util.ArrayList;

/**
 * Kelas Menu - mengelola seluruh daftar item menu restoran.
 * Menggunakan ArrayList untuk menyimpan objek-objek MenuItem (Abstraksi + Polimorfisme).
 */
public class Menu {

    // ArrayList untuk menyimpan semua item menu (Makanan, Minuman, Diskon)
    private ArrayList<MenuItem> daftarMenu;

    /** Konstruktor Menu - inisialisasi ArrayList kosong */
    public Menu() {
        daftarMenu = new ArrayList<>();
    }

    /**
     * Menambahkan item baru ke dalam menu.
     * @param item Objek MenuItem yang akan ditambahkan
     */
    public void tambahItem(MenuItem item) {
        if (item == null) throw new IllegalArgumentException("Item tidak boleh null.");
        daftarMenu.add(item);
        System.out.println("  [OK] Item '" + item.getNama() + "' berhasil ditambahkan.");
    }

    /**
     * Mengambil satu item berdasarkan nomor urut (dimulai dari 1).
     * Melempar MenuItemNotFoundException jika nomor di luar rentang (Exception Handling).
     *
     * @param  nomor Nomor item (1 s.d. jumlah item)
     * @return Objek MenuItem yang ditemukan
     * @throws MenuItemNotFoundException jika nomor tidak valid
     */
    public MenuItem getItem(int nomor) throws MenuItemNotFoundException {
        if (nomor < 1 || nomor > daftarMenu.size()) {
            throw new MenuItemNotFoundException(nomor);
        }
        return daftarMenu.get(nomor - 1);
    }

    /**
     * Menampilkan seluruh isi menu ke konsol.
     * Menggunakan perulangan dan memanggil tampilMenu() secara polimorfik.
     */
    public void tampilkanMenu() {
        if (daftarMenu.isEmpty()) {
            System.out.println("  [ Menu masih kosong. Silakan tambahkan item terlebih dahulu. ]");
            return;
        }

        String header = "  No.  Tipe       Nama                     Info               Harga";
        String garis  = "  " + "-".repeat(70);

        System.out.println(header);
        System.out.println(garis);

        // Struktur pengulangan untuk menampilkan setiap item menu
        for (int i = 0; i < daftarMenu.size(); i++) {
            System.out.printf("  %2d.  ", i + 1);         // Cetak nomor
            daftarMenu.get(i).tampilMenu();               // Polimorfisme: memanggil tampilMenu() subkelas
        }

        System.out.println(garis);
        System.out.println("  Keterangan: [Makanan]=Makanan | [Minuman]=Minuman | [Diskon]=Item Diskon");
    }

    // ── Getter ────────────────────────────────────────────────────────────────

    /** @return Seluruh daftar menu (referensi ArrayList) */
    public ArrayList<MenuItem> getDaftarMenu() { return daftarMenu; }

    /** @return Jumlah item yang ada di menu */
    public int getJumlahItem() { return daftarMenu.size(); }

    /** @return true jika menu kosong */
    public boolean isEmpty() { return daftarMenu.isEmpty(); }
}
