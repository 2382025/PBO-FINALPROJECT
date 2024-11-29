package LawFirmApp.entities;

public class Klien{
    private String nama;
    private String jenisKasus;
    private String tanggalRegistrasi;
    private String status;
    private String pengacara;
    private String jumlahTagihan;

    public Klien(String nama, String jenisKasus, String tanggalRegistrasi, String status, String pengacara, String jumlahTagihan) {
        this.nama = nama;
        this.jenisKasus = jenisKasus;
        this.tanggalRegistrasi = tanggalRegistrasi;
        this.status = status;
        this.pengacara = pengacara;
        this.jumlahTagihan = jumlahTagihan;
    }

    public Klien() {

    }


    // Getter dan Setter
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenisKasus() {
        return jenisKasus;
    }

    public void setJenisKasus(String jenisKasus) {
        this.jenisKasus = jenisKasus;
    }

    public String getTanggalRegistrasi() {
        return tanggalRegistrasi;
    }

    public void setTanggalRegistrasi(String tanggalRegistrasi) {
        this.tanggalRegistrasi = tanggalRegistrasi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPengacara() {
        return pengacara;
    }

    public void setPengacara(String pengacara) {
        this.pengacara = pengacara;
    }

    public String getJumlahTagihan() {
        return jumlahTagihan;
    }

    public void setJumlahTagihan(String jumlahTagihan) {
        this.jumlahTagihan = jumlahTagihan;
    }
}