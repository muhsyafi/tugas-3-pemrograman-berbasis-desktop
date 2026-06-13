# Sistem Manajemen Restoran Nusantara Rasa

**Tugas Praktik 3 — Pemrograman Berbasis Objek**  
Universitas Terbuka — Program Studi Sistem Informasi  
**Nama:** Muh. Syafiudin | **NIM:** 054904763

---

## Deskripsi

Aplikasi manajemen restoran berbasis CLI (Command Line Interface) yang dibangun menggunakan Java murni. Aplikasi ini mendemonstrasikan konsep-konsep OOP seperti inheritance, polimorfisme, enkapsulasi, abstraksi, serta penanganan exception dan operasi I/O file.

---

## Struktur Kelas

```
MenuItem (abstract)
├── Makanan
├── Minuman
└── Diskon

Menu              → menyimpan daftar MenuItem (ArrayList)
ItemPesanan       → pasangan MenuItem + jumlah
Pesanan           → daftar ItemPesanan milik satu pelanggan
FileManager       → utilitas baca/tulis file (menu.txt & struk)
MenuItemNotFoundException → custom exception
RestauranApp      → entry point & menu utama
```

---

## Cara Menjalankan

### Prasyarat
- Java JDK 8 atau lebih baru sudah terinstal

### Langkah-langkah

**1. Kompilasi semua file Java**

```bash
javac *.java
```

**2. Jalankan aplikasi**

```bash
java RestauranApp
```

---

## Panduan Penggunaan (Step by Step)

### Step 1 — Tampilan Awal

Saat pertama dijalankan, program mencoba memuat data menu dari file `menu.txt`.  
Jika file belum ada, program memulai dengan menu kosong.

```
============================================================
       Sistem Manajemen RESTORAN NUSANTARA RASA
       Tugas Praktik 3 - Pemrograman Berbasis Objek
       Universitas Terbuka - Program Studi Sistem Informasi
============================================================
  Memuat data menu dari file...
  [INFO] File 'menu.txt' belum ada. Memulai dengan menu kosong.
```

---

### Step 2 — Menu Utama

Setelah loading, tampil menu utama dengan 6 pilihan:

```
============================================================
                    MENU UTAMA
              RESTORAN NUSANTARA RASA
============================================================
  1. Tambah Item Menu  (Makanan / Minuman / Diskon)
  2. Tampilkan Menu Restoran
  3. Proses Pesanan Pelanggan
  4. Simpan Menu ke File
  5. Lihat Daftar Struk Tersimpan
  6. Keluar
  ------------------------------------------------------------
  Pilihan Anda [1-6]:
```

---

### Step 3 — Tambah Item Menu (Pilihan 1)

Pilih `1` untuk menambahkan item baru ke menu restoran.  
Tersedia tiga tipe item: **Makanan**, **Minuman**, atau **Item Diskon**.

**Contoh menambah Makanan:**

```
  Pilih tipe item:
  1. Makanan
  2. Minuman
  3. Item Diskon
  0. Batal
  Tipe [0-3]: 1
  Nama item      : Nasi Goreng
  Harga (Rp)    : 10000
  Jenis makanan  : Makanan Berat
  [OK] Item 'Nasi Goreng' berhasil ditambahkan.
```

**Contoh menambah Minuman:**

```
  Tipe [0-3]: 2
  Nama item      : Es Teh
  Harga (Rp)    : 4000
  Jenis minuman  : Es
  [OK] Item 'Es Teh' berhasil ditambahkan.
```

---

### Step 4 — Tampilkan Menu Restoran (Pilihan 2)

Pilih `2` untuk melihat seluruh item yang sudah ditambahkan ke menu.

```
============================================================
            DAFTAR MENU RESTORAN NUSANTARA RASA
  ------------------------------------------------------------
  No.  Tipe       Nama           Info            Harga
  ------------------------------------------------------------
  1.  [Makanan]  Nasi Goreng   | Makanan Berat  | Rp 10.000
  2.  [Minuman]  Es Teh        | Es             | Rp  4.000
  ------------------------------------------------------------
  Keterangan: [Makanan]=Makanan | [Minuman]=Minuman | [Diskon]=Item Diskon
```

---

### Step 5 — Proses Pesanan Pelanggan (Pilihan 3)

Pilih `3` untuk memproses pesanan pelanggan.

**a. Masukkan nama pelanggan:**

```
  Nama pelanggan : Muh. Syafiudin
```

**b. Pilih item dari daftar menu yang tersedia:**

```
  Daftar Menu Tersedia:
  No.  Tipe       Nama           Info            Harga
  1.  [Makanan]  Nasi Goreng   | Makanan Berat  | Rp 10.000
  2.  [Minuman]  Es Teh        | Es             | Rp  4.000
  0. Selesai memesan
  Pilih nomor item [0=selesai]: 1
  Item dipilih : Nasi Goreng
  Jumlah       : 2
  [OK] Nasi Goreng x2 berhasil ditambahkan ke pesanan.
```

**c. Lanjutkan memilih atau ketik `0` untuk selesai memesan.**

**d. Struk otomatis ditampilkan, lalu ada pilihan untuk menyimpannya ke file.**

---

### Step 6 — Simpan Menu ke File (Pilihan 4)

Pilih `4` untuk menyimpan seluruh item menu ke file `menu.txt`.  
Data ini akan dimuat kembali otomatis saat program dijalankan lagi.

---

### Step 7 — Lihat Daftar Struk Tersimpan (Pilihan 5)

Pilih `5` untuk menampilkan daftar file struk yang sudah pernah disimpan.  
File struk tersimpan di folder `struk/` dengan format nama `struk_<nama>_<tanggal>.txt`.

---

### Step 8 — Keluar (Pilihan 6)

Pilih `6` untuk keluar dari aplikasi.

```
  Terima kasih telah menggunakan sistem ini.
  Sampai jumpa!
```

---

## Konsep OOP yang Diimplementasikan

| Konsep | Implementasi |
|---|---|
| **Abstraksi** | Kelas `MenuItem` adalah abstract class dengan method abstract `getInfo()` |
| **Inheritance** | `Makanan`, `Minuman`, `Diskon` mewarisi `MenuItem` |
| **Polimorfisme** | Method `tampilkanMenu()` memanggil `getInfo()` yang berbeda tiap subkelas |
| **Enkapsulasi** | Semua atribut bersifat `private`, diakses lewat getter/setter |
| **Exception Handling** | `MenuItemNotFoundException` (custom), `NumberFormatException`, `IOException` |
| **Struktur Keputusan** | `switch` pada menu utama dan tipe item, `if-else` pada validasi input |
| **Struktur Pengulangan** | `while` pada loop menu utama dan loop pemesanan |
| **Operasi File** | `FileManager` untuk baca/tulis `menu.txt` dan file struk |
| **Array/ArrayList** | `Menu` menggunakan `ArrayList<MenuItem>`, `Pesanan` menggunakan `ArrayList<ItemPesanan>` |

---

## Lisensi

Proyek ini dibuat untuk keperluan akademik Tugas Praktik 3 — Pemrograman Berbasis Desktop,  
Universitas Terbuka, Program Studi Sistem Informasi.
