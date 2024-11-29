package repositories;
import entities.Klien;

public class LawFirmRepositoryImpl implements LawFirmRepository {

    private Klien[] daftarKlien = new Klien[10];

    @Override
    public Klien[] getAll() {
        return new Klien[0];
    }

    @Override
    public void showDaftarKlien() {
        int nomor = 1;
        for (Klien klien : daftarKlien) {
            if (klien != null) {
                System.out.println(nomor + ". | Nama: " + klien.getNama() + " | Kasus: " + klien.getJenisKasus() + " | Pengacara: " + klien.getPengacara() + " | Status: " + klien.getStatus() + "|");
            nomor++;
            }
        }
    }

    @Override
    public void addKlien(String nama, String jenisKasus, String tanggalRegistrasi, String status, String pengacara, String jumlahTagihan) {
        resizeArrayIfFull();
        for (int i = 0; i < daftarKlien.length; i++) {
            if (daftarKlien[i] == null) {
                daftarKlien[i] = new Klien(nama, jenisKasus, tanggalRegistrasi, status, pengacara, jumlahTagihan);
                break;
            }
        }
    }

    // Mengecek apakah array penuh
    public boolean isArrayFull() {
        for (Klien klien : daftarKlien) {
            if (klien == null) {
                return false;
            }
        }
        return true;
    }

    // Menambah kapasitas array
    public void resizeArrayToTwoTimesBigger() {
        Klien[] temp = daftarKlien;
        daftarKlien = new Klien[daftarKlien.length * 2];
        System.arraycopy(temp, 0, daftarKlien, 0, temp.length);
    }

    // Mengecek dan memperbesar array jika penuh
    public void resizeArrayIfFull() {
        if (isArrayFull()) {
            resizeArrayToTwoTimesBigger();
        }
    }

    @Override
    public boolean editKlien(Integer number, String namaBaru, String jenisKasusBaru, String tanggalBaru, String pengacaraBaru, String tagihanBaru) {
        if (isSelectedKlienNotValid(number)) {
            return false;
        }
        Klien klien = daftarKlien[number - 1];
        klien.setNama(namaBaru);
        klien.setJenisKasus(jenisKasusBaru);
        klien.setTanggalRegistrasi(tanggalBaru);
        klien.setPengacara(pengacaraBaru);
        klien.setJumlahTagihan(tagihanBaru);
        return true;
    }

    @Override
    public boolean editKlien(Integer number, String namaBaru, String jenisKasusBaru, String tanggalBaru, String statusBaru, String pengacaraBaru, String tagihanBaru) {
        return false;
    }

    @Override
    public boolean removeKlien(Integer number) {
        if (isSelectedKlienNotValid(number)) {
            return false;
        }

        for (int i = number - 1; i < daftarKlien.length - 1; i++) {
            daftarKlien[i] = daftarKlien[i + 1];
        }
        daftarKlien[daftarKlien.length - 1] = null;
        return true;
    }

    @Override
    public void searchKlien(String nama) {
        boolean found = false;
        System.out.println("HASIL PENCARIAN:");
        for (Klien klien : daftarKlien) {
            if (klien != null && klien.getNama().equalsIgnoreCase(nama)) {
                System.out.println("| Nama: " + klien.getNama() + " | Kasus: " + klien.getJenisKasus() + " | Status: " + klien.getStatus() + "|");
                found = true;
            }
        }
        if (!found) {
            System.out.println("Klien dengan nama " + nama + " tidak ditemukan.");
        }
    }

    @Override
    public void showStatistikKasus() {
        int aktif = 0;
        int selesai = 0;
        for (Klien klien : daftarKlien) {
            if (klien != null) {
                if (klien.getStatus().equalsIgnoreCase("Aktif")) {
                    aktif++;
                } else if (klien.getStatus().equalsIgnoreCase("Selesai")) {
                    selesai++;
                }
            }
        }
        System.out.println("STATISTIK KASUS:");
        System.out.println("Kasus Aktif: " + aktif);
        System.out.println("Kasus Selesai: " + selesai);
    }

    @Override
    public void showDaftarTagihan() {
        System.out.println("DAFTAR TAGIHAN:");
        for (Klien klien : daftarKlien) {
            if (klien != null) {
                System.out.println("Klien: " + klien.getNama() + ", Tagihan: Rp. " + klien.getJumlahTagihan());
            }
        }
    }

    @Override
    public boolean ubahStatusKasus(Integer number, String statusBaru) {
        if (isSelectedKlienNotValid(number)) {
            return false;
        }
        Klien klien = daftarKlien[number - 1];
        klien.setStatus(statusBaru);
        return true;
    }

    // Assume this method checks if the selected client is valid
    private boolean isSelectedKlienNotValid(Integer number) {
        return number < 1 || number > daftarKlien.length || daftarKlien[number - 1] == null;
    }
}
