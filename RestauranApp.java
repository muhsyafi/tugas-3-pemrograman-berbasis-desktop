import java.util.Scanner;
import java.io.IOException;

/**
 * Kelas utama aplikasi Manajemen Restoran.
 * Berisi menu utama dan alur interaksi pengguna.
 *
 * Mengimplementasikan:
 * - Struktur keputusan (switch, if-else)
 * - Struktur pengulangan (while, for)
 * - Exception Handling (try-catch)
 * - I/O File (lewat FileManager)
 * - Array / ArrayList (lewat Menu dan Pesanan)
 * - OOP: Abstraksi, Inheritance, Enkapsulasi, Polimorfisme
 *
 * @author Muhsyafi - Universitas Terbuka Semarang
 *         NIM     : 054904763
 *         Prodi   : Sistem Informasi
 */
public class RestauranApp {

    private static final Menu    menu    = new Menu();
    private static final Scanner scanner = new Scanner(System.in);

    private static final String NAMA_RESTORAN = "RESTORAN NUSANTARA RASA";
    private static final String GARIS_TEBAL   = "  " + "=".repeat(60);
    private static final String GARIS_TIPIS   = "  " + "-".repeat(60);

    // ─────────────────────────────────────────────────────────────────────────
    // ENTRY POINT
    // ─────────────────────────────────────────────────────────────────────────

    public static void main(String[] args) {
        tampilkanHeader();

        // Muat menu dari file saat program pertama kali dijalankan (Operasi File)
        System.out.println("  Memuat data menu dari file...");
        try {
            FileManager.muatMenu(menu);
        } catch (IOException e) {
            System.out.println("  [!] Gagal memuat menu: " + e.getMessage());
        }

        // ── Loop menu utama (Struktur Pengulangan) ─────────────────────────
        boolean jalan = true;
        while (jalan) {
            tampilkanMenuUtama();
            int pilihan = bacaInt("  Pilihan Anda [1-6]: ");

            // Struktur keputusan - memilih fitur
            switch (pilihan) {
                case 1:
                    tambahItemMenu();
                    break;
                case 2:
                    tampilkanMenuRestoran();
                    break;
                case 3:
                    prosesPesanan();
                    break;
                case 4:
                    simpanMenuKeFile();
                    break;
                case 5:
                    lihatDaftarStruk();
                    break;
                case 6:
                    System.out.println("\n" + GARIS_TEBAL);
                    System.out.println("  Terima kasih telah menggunakan sistem ini.");
                    System.out.println("  Sampai jumpa!");
                    System.out.println(GARIS_TEBAL);
                    jalan = false;
                    break;
                default:
                    System.out.println("\n  [!] Pilihan tidak valid. Masukkan angka 1-6.");
            }
        }

        scanner.close();
    }

    // ─────────────────────────────────────────────────────────────────────────
    // TAMPILAN HEADER DAN MENU UTAMA
    // ─────────────────────────────────────────────────────────────────────────

    private static void tampilkanHeader() {
        System.out.println("\n" + GARIS_TEBAL);
        System.out.println("       Sistem Manajemen " + NAMA_RESTORAN);
        System.out.println("       Tugas Praktik 3 - Pemrograman Berbasis Objek");
        System.out.println("       Universitas Terbuka - Program Studi Sistem Informasi");
        System.out.println(GARIS_TEBAL);
    }

    private static void tampilkanMenuUtama() {
        System.out.println("\n" + GARIS_TEBAL);
        System.out.println("                    MENU UTAMA");
        System.out.println("              " + NAMA_RESTORAN);
        System.out.println(GARIS_TEBAL);
        System.out.println("  1. Tambah Item Menu  (Makanan / Minuman / Diskon)");
        System.out.println("  2. Tampilkan Menu Restoran");
        System.out.println("  3. Proses Pesanan Pelanggan");
        System.out.println("  4. Simpan Menu ke File");
        System.out.println("  5. Lihat Daftar Struk Tersimpan");
        System.out.println("  6. Keluar");
        System.out.println(GARIS_TIPIS);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // FITUR 1: TAMBAH ITEM MENU
    // ─────────────────────────────────────────────────────────────────────────

    private static void tambahItemMenu() {
        System.out.println("\n" + GARIS_TEBAL);
        System.out.println("              TAMBAH ITEM MENU BARU");
        System.out.println(GARIS_TIPIS);
        System.out.println("  Pilih tipe item:");
        System.out.println("  1. Makanan");
        System.out.println("  2. Minuman");
        System.out.println("  3. Item Diskon");
        System.out.println("  0. Batal");

        int tipe = bacaInt("  Tipe [0-3]: ");

        // Struktur keputusan
        if (tipe == 0) { System.out.println("  Dibatalkan."); return; }
        if (tipe < 1 || tipe > 3) { System.out.println("  [!] Tipe tidak valid."); return; }

        System.out.print("  Nama item      : ");
        String nama = scanner.nextLine().trim();
        if (nama.isEmpty()) { System.out.println("  [!] Nama tidak boleh kosong."); return; }

        double harga = bacaDouble("  Harga (Rp)    : ");
        if (harga < 0) { System.out.println("  [!] Harga tidak boleh negatif."); return; }

        // Exception handling saat membuat objek
        try {
            switch (tipe) {
                case 1: // Makanan (Inheritance dari MenuItem)
                    System.out.print("  Jenis makanan  : ");
                    String jenisMakanan = scanner.nextLine().trim();
                    menu.tambahItem(new Makanan(nama, harga, jenisMakanan));
                    break;

                case 2: // Minuman (Inheritance dari MenuItem)
                    System.out.print("  Jenis minuman  : ");
                    String jenisMinuman = scanner.nextLine().trim();
                    menu.tambahItem(new Minuman(nama, harga, jenisMinuman));
                    break;

                case 3: // Diskon (Inheritance dari MenuItem)
                    double persen = bacaDouble("  Diskon (0-100%): ");
                    menu.tambahItem(new Diskon(nama, harga, persen));
                    break;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("  [!] Input tidak valid: " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // FITUR 2: TAMPILKAN MENU RESTORAN
    // ─────────────────────────────────────────────────────────────────────────

    private static void tampilkanMenuRestoran() {
        System.out.println("\n" + GARIS_TEBAL);
        System.out.println("            DAFTAR MENU " + NAMA_RESTORAN);
        System.out.println(GARIS_TIPIS);
        menu.tampilkanMenu(); // Polimorfisme: tampilMenu() berbeda untuk setiap subkelas
    }

    // ─────────────────────────────────────────────────────────────────────────
    // FITUR 3: PROSES PESANAN PELANGGAN
    // ─────────────────────────────────────────────────────────────────────────

    private static void prosesPesanan() {
        System.out.println("\n" + GARIS_TEBAL);
        System.out.println("            PROSES PESANAN PELANGGAN");
        System.out.println(GARIS_TIPIS);

        // Validasi: menu tidak boleh kosong
        if (menu.isEmpty()) {
            System.out.println("  [!] Menu masih kosong. Silakan tambahkan item terlebih dahulu.");
            return;
        }

        System.out.print("  Nama pelanggan : ");
        String namaPelanggan = scanner.nextLine().trim();
        if (namaPelanggan.isEmpty()) {
            System.out.println("  [!] Nama pelanggan tidak boleh kosong.");
            return;
        }

        // Buat objek Pesanan baru
        Pesanan pesanan;
        try {
            pesanan = new Pesanan(namaPelanggan);
        } catch (IllegalArgumentException e) {
            System.out.println("  [!] " + e.getMessage());
            return;
        }

        // ── Loop pemesanan (Struktur Pengulangan) ──────────────────────────
        boolean pesanLagi = true;
        while (pesanLagi) {
            System.out.println("\n" + GARIS_TIPIS);
            System.out.println("  Daftar Menu Tersedia:");
            menu.tampilkanMenu();
            System.out.println("  0. Selesai memesan");

            int nomor = bacaInt("  Pilih nomor item [0=selesai]: ");

            // Struktur keputusan
            if (nomor == 0) {
                pesanLagi = false;
                continue;
            }

            // Exception handling: item tidak ditemukan
            try {
                MenuItem item = menu.getItem(nomor); // Throws MenuItemNotFoundException
                System.out.println("  Item dipilih : " + item.getNama());
                int jumlah = bacaInt("  Jumlah       : ");

                if (jumlah <= 0) {
                    System.out.println("  [!] Jumlah harus lebih dari 0.");
                    continue;
                }

                pesanan.tambahItem(item, jumlah);
                System.out.printf("  [OK] %s x%d berhasil ditambahkan ke pesanan.%n",
                        item.getNama(), jumlah);

            } catch (MenuItemNotFoundException e) {
                // Tangkap exception kustom
                System.out.println("  [!] " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("  [!] " + e.getMessage());
            }
        }

        // Validasi pesanan tidak kosong
        if (pesanan.isEmpty()) {
            System.out.println("\n  [ Tidak ada item yang dipesan. Pesanan dibatalkan. ]");
            return;
        }

        // Tampilkan struk (termasuk total dengan diskon)
        System.out.println("\n" + GARIS_TIPIS);
        pesanan.tampilkanStruk();

        // Tawaran simpan struk ke file (I/O)
        System.out.print("\n  Simpan struk ke file? (y/n): ");
        String jawab = scanner.nextLine().trim().toLowerCase();
        if (jawab.equals("y") || jawab.equals("ya")) {
            try {
                FileManager.simpanStruk(pesanan);
            } catch (IOException e) {
                System.out.println("  [!] Gagal menyimpan struk: " + e.getMessage());
            }
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // FITUR 4: SIMPAN MENU KE FILE
    // ─────────────────────────────────────────────────────────────────────────

    private static void simpanMenuKeFile() {
        System.out.println("\n" + GARIS_TIPIS);
        try {
            FileManager.simpanMenu(menu); // Operasi tulis ke file
        } catch (IOException e) {
            System.out.println("  [!] Gagal menyimpan menu: " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // FITUR 5: LIHAT DAFTAR STRUK
    // ─────────────────────────────────────────────────────────────────────────

    private static void lihatDaftarStruk() {
        System.out.println("\n" + GARIS_TEBAL);
        System.out.println("              DAFTAR STRUK TERSIMPAN");
        System.out.println(GARIS_TIPIS);
        FileManager.tampilkanDaftarStruk(); // Operasi baca direktori file
    }

    // ─────────────────────────────────────────────────────────────────────────
    // UTILITAS INPUT
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Membaca input integer dari pengguna dengan validasi.
     * Menggunakan pengulangan hingga input valid (Exception Handling).
     */
    private static int bacaInt(String prompt) {
        while (true) { // Pengulangan dengan kondisi
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  [!] Masukkan angka bulat yang valid.");
            }
        }
    }

    /**
     * Membaca input double dari pengguna dengan validasi.
     * Menggunakan pengulangan hingga input valid (Exception Handling).
     */
    private static double bacaDouble(String prompt) {
        while (true) { // Pengulangan dengan kondisi
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  [!] Masukkan angka yang valid.");
            }
        }
    }
}
