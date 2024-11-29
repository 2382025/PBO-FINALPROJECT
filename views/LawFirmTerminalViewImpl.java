package views;

import services.LawFirmService;
import java.util.Scanner;

public class LawFirmTerminalViewImpl implements LawFirmView {
    private static Scanner scanner = new Scanner(System.in);
    private final LawFirmService lawFirmService;

    public LawFirmTerminalViewImpl(LawFirmService lawFirmService) {
        this.lawFirmService = lawFirmService;
    }

    // Input untuk memudahkan pengambilan data dari pengguna
    private static String input(String info) {
        System.out.print(info + " : ");
        return scanner.nextLine();
    }

    // Menampilkan menu utama
    public void showMainMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("MENU:");
            System.out.println("1. Tampilkan Daftar Klien");
            System.out.println("2. Tambah Klien Baru");
            System.out.println("3. Edit Informasi Klien");
            System.out.println("4. Hapus Klien");
            System.out.println("5. Cari Klien");
            System.out.println("6. Tampilkan Statistik Kasus");
            System.out.println("7. Tampilkan Daftar Tagihan");
            System.out.println("8. Ubah Status Kasus");
            System.out.println("9. Keluar");
            String selectedMenu = input("Pilih");

            switch (selectedMenu) {
                case "1":
                    showDaftarKlien();
                    break;
                case "2":
                    showMenuAddKlien();
                    break;
                case "3":
                    showMenuEditKlien();
                    break;
                case "4":
                    showMenuRemoveKlien();
                    break;
                case "5":
                    showMenuSearchKlien();
                    break;
                case "6":
                    showStatistikKasus();
                    break;
                case "7":
                    showDaftarTagihan();
                    break;
                case "8":
                    showMenuUbahStatusKasus();
                    break;
                case "9":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        }
    }

    // Menampilkan daftar klien
    public void showDaftarKlien() {
        lawFirmService.showDaftarKlien(); // Memanggil Service untuk menampilkan daftar klien
    }

    // Menampilkan menu untuk menambah klien
    public void showMenuAddKlien() {
        System.out.println("MENAMBAH KLIEN:");
        String nama = input("Nama Klien (x jika batal)");
        if (!nama.equals("x")) {
            String jenisKasus = input("Jenis Kasus");
            String tanggalRegistrasi = input("Tanggal Registrasi (dd-MM-yyyy)");
            String status = input("Status Kasus");
            String pengacara = input("Pengacara yang Ditugaskan");
            String jumlahTagihan = input("Jumlah Tagihan");
            lawFirmService.addKlien(nama, jenisKasus, tanggalRegistrasi, status, pengacara, jumlahTagihan); // Call service to add the client
        }
    }

    // Menampilkan menu untuk menghapus klien
    public void showMenuRemoveKlien() {
        System.out.println("MENGHAPUS KLIEN:");
        showDaftarKlien(); // Menampilkan daftar klien sebelum menghapus
        String number = input("Nomor klien yang dihapus (x jika batal)");
        if (!number.equals("x")) {
            boolean success = lawFirmService.removeKlien(Integer.parseInt(number)); // Call service to remove the client
            if (success) {
                System.out.println("Berhasil menghapus klien");
            } else {
                System.out.println("Gagal menghapus klien: " + number);
            }
        }
    }

    // Menampilkan menu untuk mengedit klien
    public void showMenuEditKlien() {
        System.out.println("EDIT KLIEN:");
        showDaftarKlien(); // Menampilkan daftar klien sebelum mengedit
        String selectedKlien = input("Nomor klien yang akan diedit (x jika batal)");
        if (!selectedKlien.equals("x")) {
            System.out.println("UPDATE INFORMASI KLIEN");
            String namaBaru = input("Nama");
            String jenisKasusBaru = input("Jenis Kasus");
            String tanggalBaru = input("Tanggal Registrasi");
            String pengacaraBaru = input("Pengacara");
            String tagihanBaru = input("Jumlah Tagihan");
            boolean isEditKlienSuccess = lawFirmService.editKlien(Integer.parseInt(selectedKlien), namaBaru, jenisKasusBaru, tanggalBaru, pengacaraBaru, tagihanBaru); // Call service to edit the client
            if (isEditKlienSuccess) {
                System.out.println("Berhasil Mengedit Klien");
            } else {
                System.out.println("Gagal Mengedit Klien");
            }
        }
    }

    // Menampilkan menu untuk mencari klien
    public void showMenuSearchKlien() {
        System.out.println("MENCARI KLIEN:");
        String nama = input("Masukkan nama klien");
        lawFirmService.searchKlien(nama); // Memanggil service untuk mencari klien
    }

    // Menampilkan statistik kasus
    public void showStatistikKasus() {
        lawFirmService.showStatistikKasus(); // Memangil service untuk menunjukkan statistik
    }

    // Menampilkan daftar tagihan
    public void showDaftarTagihan() {
        lawFirmService.showDaftarTagihan();  //Memangil service untuk menunjukkan daftar tagihan
    }

    // Menampilkan menu untuk mengubah status kasus
    public void showMenuUbahStatusKasus() {
        System.out.println("MENGUBAH STATUS KASUS:");
        showDaftarKlien(); // Menampilkan daftar klien sebelum mengubah status
        String selectedKlien = input("Nomor klien yang akan diubah statusnya (x jika batal)");
        if (!selectedKlien.equals("x")) {
            String statusBaru = input("Status baru");
            boolean isSuccess = lawFirmService.ubahStatusKasus(Integer.parseInt(selectedKlien), statusBaru); // Call service to update the case status
            if (isSuccess) {
                System.out.println("Berhasil Mengubah Status Kasus");
            } else {
                System.out.println("Gagal Mengubah Status Kasus");
            }
        }
    }

    // Method to run the view
    @Override
    public void run() {
        showMainMenu();
    }
}
