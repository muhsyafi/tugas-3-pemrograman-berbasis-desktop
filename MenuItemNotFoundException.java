/**
 * Exception kustom yang dilempar ketika item menu tidak ditemukan.
 * Mengimplementasikan konsep EXCEPTION HANDLING.
 */
public class MenuItemNotFoundException extends Exception {

    private int nomorItem;

    /**
     * Konstruktor dengan nomor item yang tidak ditemukan.
     * @param nomor Nomor item yang dicari
     */
    public MenuItemNotFoundException(int nomor) {
        super("Item nomor " + nomor + " tidak ditemukan dalam daftar menu.");
        this.nomorItem = nomor;
    }

    /**
     * Konstruktor dengan pesan kustom.
     * @param pesan Pesan error kustom
     */
    public MenuItemNotFoundException(String pesan) {
        super(pesan);
        this.nomorItem = -1;
    }

    /** @return Nomor item yang menyebabkan exception */
    public int getNomorItem() { return nomorItem; }
}
