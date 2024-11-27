package services;

import entities.Klien;
import repositories.LawFirmRepository;

public class LawFirmServiceImpl implements LawFirmService {
    private final LawFirmRepository lawFirmRepository;

    public LawFirmServiceImpl(LawFirmRepository lawFirmRepository) {
        this.lawFirmRepository = lawFirmRepository;
    }

    @Override
    public void showDaftarKlien() {
        ////Memanggil Method Repository untuk menampilkan daftar klien
        lawFirmRepository.showDaftarKlien();
    }

    @Override
    public void addKlien(String nama, String jenisKasus, String tanggalRegistrasi, String status, String pengacara, String jumlahTagihan) {
        // Validate input
        if (nama.isBlank() || jenisKasus.isBlank() || tanggalRegistrasi.isBlank() || status.isBlank() || pengacara.isBlank() || jumlahTagihan.isBlank()) {
            System.out.println("Harus diisi!");
            return;
        }

        //Memanggil Method Repository untuk menambah klien
        lawFirmRepository.addKlien(nama, jenisKasus, tanggalRegistrasi, status, pengacara, jumlahTagihan);
    }

    @Override
    public Boolean removeKlien(Integer number) {
        //Memanggil Method Repository untuk menghapus klien
        return lawFirmRepository.removeKlien(number);
    }

    @Override
    public Boolean editKlien(Integer number, String namaBaru, String jenisKasusBaru, String tanggalBaru, String pengacaraBaru, String tagihanBaru) {
        // Validasi input
        if (namaBaru.isBlank() || jenisKasusBaru.isBlank() || tanggalBaru.isBlank() || pengacaraBaru.isBlank() || tagihanBaru.isBlank()) {
            System.out.println("Harus diisi!");
            return false;
        }

        // Call repository method to edit client
        return lawFirmRepository.editKlien(number, namaBaru, jenisKasusBaru, tanggalBaru, pengacaraBaru, tagihanBaru);
    }

    @Override
    public void searchKlien(String nama) {
        //Memanggil Method Repository untuk mencari klien
        lawFirmRepository.searchKlien(nama);
    }

    @Override
    public void showStatistikKasus() {
        // Memanggil repository
        lawFirmRepository.showStatistikKasus();
    }

    @Override
    public void showDaftarTagihan() {
        // Memanggil repository
        lawFirmRepository.showDaftarTagihan();
    }

    @Override
    public Boolean ubahStatusKasus(Integer number, String statusBaru) {
        //Memanggil Method Repository untuk mengubah status klien
        return lawFirmRepository.ubahStatusKasus(number, statusBaru);
    }
}
