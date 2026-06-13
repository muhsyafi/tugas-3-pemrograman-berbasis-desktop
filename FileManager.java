import java.io.*;
import java.util.ArrayList;

/**
 * Kelas FileManager - menangani seluruh operasi Input/Output file.
 * Menyimpan dan memuat data menu (menu.txt) serta menyimpan struk pesanan.
 *
 * Mengimplementasikan konsep OPERASI FILE dan I/O.
 */
public class FileManager {

    /** Nama file untuk menyimpan data menu */
    private static final String NAMA_FILE_MENU = "menu.txt";

    /** Direktori untuk menyimpan file-file struk */
    private static final String DIR_STRUK = "struk";

    // ─────────────────────────────────────────────────────────────────────────
    // Operasi File Menu
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Menyimpan seluruh daftar menu ke file teks (menu.txt).
     * Format tiap baris: TIPE|NAMA|HARGA|KETERANGAN
     *
     * @param menu Objek Menu yang akan disimpan
     * @throws IOException jika terjadi kesalahan penulisan file
     */
    public static void simpanMenu(Menu menu) throws IOException {
        // Menggunakan try-with-resources agar PrintWriter otomatis ditutup
        try (PrintWriter pw = new PrintWriter(new FileWriter(NAMA_FILE_MENU))) {
            pw.println("# ============================================");
            pw.println("# File Data Menu - Restoran Nusantara Rasa");
            pw.println("# Format: TIPE|NAMA|HARGA|KETERANGAN");
            pw.println("# ============================================");

            // Pengulangan untuk menulis setiap item ke file
            for (MenuItem item : menu.getDaftarMenu()) {
                pw.println(item.toFileString());
            }
        }
        System.out.println("  [OK] Menu (" + menu.getJumlahItem()
                + " item) berhasil disimpan ke '" + NAMA_FILE_MENU + "'.");
    }

    /**
     * Memuat daftar menu dari file teks (menu.txt).
     * Jika file tidak ada, menu dibiarkan kosong.
     *
     * @param menu Objek Menu yang akan diisi dengan data dari file
     * @throws IOException jika terjadi kesalahan pembacaan file
     */
    public static void muatMenu(Menu menu) throws IOException {
        File file = new File(NAMA_FILE_MENU);

        // Struktur keputusan: cek apakah file ada
        if (!file.exists()) {
            System.out.println("  [INFO] File '" + NAMA_FILE_MENU
                    + "' belum ada. Memulai dengan menu kosong.");
            return;
        }

        int berhasil = 0;
        int gagal    = 0;

        // Menggunakan try-with-resources untuk membaca file baris per baris
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String baris;
            int noBaris = 0;

            while ((baris = br.readLine()) != null) {
                noBaris++;
                baris = baris.trim();

                // Lewati baris kosong dan komentar
                if (baris.isEmpty() || baris.startsWith("#")) continue;

                // Pisahkan berdasarkan karakter '|'
                String[] bagian = baris.split("\\|", 4);
                if (bagian.length < 4) {
                    System.out.printf("  [!] Baris %d tidak valid, dilewati.%n", noBaris);
                    gagal++;
                    continue;
                }

                // Exception handling saat parsing data dari file
                try {
                    String tipe      = bagian[0].trim().toUpperCase();
                    String nama      = bagian[1].trim();
                    double harga     = Double.parseDouble(bagian[2].trim());
                    String keterangan = bagian[3].trim();

                    // Struktur keputusan: tentukan tipe item
                    switch (tipe) {
                        case "MAKANAN":
                            menu.getDaftarMenu().add(new Makanan(nama, harga, keterangan));
                            berhasil++;
                            break;
                        case "MINUMAN":
                            menu.getDaftarMenu().add(new Minuman(nama, harga, keterangan));
                            berhasil++;
                            break;
                        case "DISKON":
                            double persen = Double.parseDouble(keterangan);
                            menu.getDaftarMenu().add(new Diskon(nama, harga, persen));
                            berhasil++;
                            break;
                        default:
                            System.out.printf("  [!] Tipe '%s' tidak dikenal (baris %d).%n",
                                    tipe, noBaris);
                            gagal++;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.printf("  [!] Baris %d gagal diparse: %s%n", noBaris, e.getMessage());
                    gagal++;
                }
            }
        }

        System.out.printf("  [OK] Menu dimuat: %d item berhasil, %d item gagal.%n", berhasil, gagal);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Operasi File Struk
    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Menyimpan struk pesanan ke file teks di folder 'struk/'.
     * Nama file: struk_[namaPelanggan]_[timestamp].txt
     *
     * @param pesanan Objek Pesanan yang struknnya akan disimpan
     * @throws IOException jika terjadi kesalahan penulisan file
     */
    public static void simpanStruk(Pesanan pesanan) throws IOException {
        // Buat direktori 'struk' jika belum ada
        File dir = new File(DIR_STRUK);
        if (!dir.exists()) dir.mkdirs();

        // Buat nama file yang aman (tidak mengandung karakter spesial)
        String timestamp = pesanan.getWaktuPesan()
                .format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String namaAman = pesanan.getNamaPelanggan().replaceAll("[^a-zA-Z0-9]", "_");
        String namaFile = DIR_STRUK + File.separator
                + "struk_" + namaAman + "_" + timestamp + ".txt";

        try (PrintWriter pw = new PrintWriter(new FileWriter(namaFile))) {
            pw.println(pesanan.getStrukString());
        }

        System.out.println("  [OK] Struk disimpan ke '" + namaFile + "'.");
    }

    /**
     * Menampilkan daftar semua file struk yang sudah tersimpan.
     */
    public static void tampilkanDaftarStruk() {
        File dir = new File(DIR_STRUK);

        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("  [INFO] Belum ada struk yang tersimpan.");
            return;
        }

        // Filter hanya file .txt
        File[] daftarFile = dir.listFiles((d, name) -> name.endsWith(".txt"));

        if (daftarFile == null || daftarFile.length == 0) {
            System.out.println("  [INFO] Belum ada struk yang tersimpan.");
            return;
        }

        System.out.println("  Daftar struk tersimpan (" + daftarFile.length + " file):");
        // Pengulangan untuk menampilkan setiap file struk
        for (int i = 0; i < daftarFile.length; i++) {
            System.out.printf("  %2d. %s%n", i + 1, daftarFile[i].getName());
        }
    }
}
