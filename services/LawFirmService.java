package services;

import entities.Klien;

public interface LawFirmService {
    // Mengambil daftar klien
    void showDaftarKlien();

    // Menambah klien baru
    void addKlien(String nama, String jenisKasus, String tanggalRegistrasi, String status, String pengacara, String jumlahTagihan);

    // Menghapus klien berdasarkan nomor
    Boolean removeKlien(Integer number);

    // Mengedit informasi klien berdasarkan nomor
    Boolean editKlien(Integer number, String namaBaru, String jenisKasusBaru, String tanggalBaru, String pengacaraBaru, String tagihanBaru);

    // Mencari klien berdasarkan nama
    void searchKlien(String nama);

    // Menampilkan statistik kasus (jumlah kasus aktif dan selesai)
    void showStatistikKasus();

    // Menampilkan daftar tagihan
    void showDaftarTagihan();

    // Mengubah status kasus klien
    Boolean ubahStatusKasus(Integer number, String statusBaru);
}
