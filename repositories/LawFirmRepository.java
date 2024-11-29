package repositories;

import entities.Klien;

public interface LawFirmRepository {


    Klien[] getAll();

    void showDaftarKlien();

    void addKlien(String nama, String jenisKasus, String tanggalRegistrasi, String status, String pengacara, String jumlahTagihan);

    boolean editKlien(Integer number, String namaBaru, String jenisKasusBaru, String tanggalBaru, String pengacaraBaru, String tagihanBaru);

    boolean editKlien(Integer number, String namaBaru, String jenisKasusBaru, String tanggalBaru, String statusBaru, String pengacaraBaru, String tagihanBaru);

    boolean removeKlien(Integer number);

    void searchKlien(String nama);

    void showStatistikKasus();

    void showDaftarTagihan();

    boolean ubahStatusKasus(Integer number, String statusBaru);


}
