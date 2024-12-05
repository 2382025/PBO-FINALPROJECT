package repositories;

import config.Database;
import entities.Klien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LawFirmRepositoryDbImpl implements LawFirmRepository {
    private final Database database;

    public LawFirmRepositoryDbImpl(final Database database) {
        this.database = database;
    }

    @Override
    public Klien[] getAll() {
        Connection connection = database.getConnection();
        String sqlStatement = "SELECT * FROM klien";
        List<Klien> klienList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Klien klien = new Klien();
                klien.setNama(resultSet.getString("nama"));
                klien.setJenisKasus(resultSet.getString("jenisKasus"));
                klien.setTanggalRegistrasi(resultSet.getDate("tanggalRegistrasi").toString());
                klien.setStatus(resultSet.getString("status"));
                klien.setPengacara(resultSet.getString("pengacara"));
                klien.setJumlahTagihan(String.valueOf(resultSet.getBigDecimal("jumlahTagihan")));
                klienList.add(klien);
            }
        } catch (SQLException e) {
            System.out.println("Error dalam mengambil data: " + e.getMessage());
        }

        return klienList.toArray(new Klien[0]);
    }

    @Override
    public void showDaftarKlien() {
        String sqlStatement = "SELECT nama, jenisKasus, tanggalRegistrasi, status, pengacara, jumlahTagihan FROM klien";
        List<Klien> daftarKlien = new ArrayList<>();

        try{
            Connection connection = database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Klien klien = new Klien();
                klien.setNama(resultSet.getString("nama"));
                klien.setJenisKasus(resultSet.getString("jenisKasus"));
                klien.setTanggalRegistrasi(resultSet.getDate("tanggalRegistrasi").toString());
                klien.setStatus(resultSet.getString("status"));
                klien.setPengacara(resultSet.getString("pengacara"));
                klien.setJumlahTagihan(resultSet.getBigDecimal("jumlahTagihan").toString());
                daftarKlien.add(klien);
            }
        } catch (SQLException e) {
            System.out.println("Error dalam mengambil data: " + e.getMessage());
            return;
        }

        int nomor = 1;
        System.out.println("DAFTAR KLIEN:");
        for (Klien klien : daftarKlien) {
            System.out.println(nomor + ". | Nama: " + klien.getNama() +
                    " | Kasus: " + klien.getJenisKasus() +
                    " | Pengacara: " + klien.getPengacara() +
                    " | Status: " + klien.getStatus() + " |");
            nomor++;
        }
    }

    @Override
    public void addKlien(String nama, String jenisKasus, String tanggalRegistrasi, String status, String pengacara, String jumlahTagihan) {
        String sqlStatement = "INSERT INTO klien (nama, jenisKasus, tanggalRegistrasi, status, pengacara, jumlahTagihan) VALUES (?, ?, ?, ?, ?, ?)";

        try{
            Connection connection = database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setString(1, nama);
            preparedStatement.setString(2, jenisKasus);
            preparedStatement.setString(3, tanggalRegistrasi);
            preparedStatement.setString(4, status);
            preparedStatement.setString(5, pengacara);
            preparedStatement.setBigDecimal(6, new java.math.BigDecimal(jumlahTagihan));

            int rowsEffected = preparedStatement.executeUpdate();
            if (rowsEffected > 0) {
                System.out.println("Klien berhasil ditambahkan!");
            }
        } catch (SQLException e) {
            System.out.println("Error menambahkan klien: " + e.getMessage());
        }
    }

    @Override
    public boolean editKlien(Integer number, String namaBaru, String jenisKasusBaru, String tanggalBaru, String pengacaraBaru, String tagihanBaru) {
        return false;
    }

    @Override
    public boolean editKlien(Integer number, String namaBaru, String jenisKasusBaru, String tanggalBaru, String statusBaru, String pengacaraBaru, String tagihanBaru) {
        String sqlStatement = "UPDATE klien SET nama = ?, jenisKasus = ?, tanggalRegistrasi = ?, status = ?, pengacara = ?, jumlahTagihan = ? WHERE nomor = ?";

        try{
            Connection connection = database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setString(1, namaBaru);
            preparedStatement.setString(2, jenisKasusBaru);
            preparedStatement.setDate(3, java.sql.Date.valueOf(tanggalBaru));
            preparedStatement.setString(4, statusBaru);
            preparedStatement.setString(5, pengacaraBaru);
            preparedStatement.setBigDecimal(6, new java.math.BigDecimal(tagihanBaru));
            preparedStatement.setInt(7, number);


            int rowsEffected = preparedStatement.executeUpdate();
            if (rowsEffected > 0) {
                System.out.println("Berhasil Mengedit Data Klien!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error Mengedit Data Klien: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean removeKlien(Integer number) {
        String sqlStatement = "DELETE FROM klien WHERE nomor = ?";

        try{
            Connection connection = database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setInt(1, number);

            int rowsEffected = preparedStatement.executeUpdate();
            if (rowsEffected > 0) {
                System.out.println("Berhasil Menghapus Klien!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error Menghapus Klien: " + e.getMessage());
        }
        return false;
    }

    @Override
    public void searchKlien(String nama) {
        String sqlStatement = "SELECT * FROM klien WHERE nama LIKE ?";

        try {
            Connection connection = database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);

            preparedStatement.setString(1, "%" + nama + "%");

            try {
                ResultSet resultSet = preparedStatement.executeQuery();
                boolean found = false;
                while (resultSet.next()) {
                    found = true;
                    System.out.println("_");
                    System.out.println("Nama: " + resultSet.getString("nama"));
                    System.out.println("Jenis Kasus: " + resultSet.getString("jenisKasus"));
                    System.out.println("Tanggal Registrasi: " + resultSet.getDate("tanggalRegistrasi"));
                    System.out.println("Status: " + resultSet.getString("status"));
                    System.out.println("Pengacara: " + resultSet.getString("pengacara"));
                    System.out.println("Jumlah Tagihan: " + resultSet.getBigDecimal("jumlahTagihan"));
                    System.out.println("_");
                }

                if (!found) {
                    System.out.println("Tidak ada klien yang ditemukan dengan nama: " + nama);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            System.out.println("Error mencari klien: " + e.getMessage());
        }
    }

    @Override
    public void showStatistikKasus() {
        String sqlStatement = "SELECT " +
                "SUM(CASE WHEN status = 'Aktif' THEN 1 ELSE 0 END) AS jumlahAktif, " +
                "SUM(CASE WHEN status = 'Selesai' THEN 1 ELSE 0 END) AS jumlahSelesai " +
                "FROM klien";

        try {
            Connection connection = database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("STATISTIK KASUS:");
            if (resultSet.next()) {
                int jumlahAktif = resultSet.getInt("jumlahAktif");
                int jumlahSelesai = resultSet.getInt("jumlahSelesai");

                System.out.println("");
                System.out.println("Aktif: " + jumlahAktif);
                System.out.println("Selesai: " + jumlahSelesai);
                System.out.println("");
            }
        } catch (SQLException e) {
            System.out.println("Error menampilkan statistik kasus: " + e.getMessage());
        }
    }

    @Override
    public void showDaftarTagihan() {
        String sqlStatement = "SELECT nama, jumlahTagihan FROM klien ORDER BY jumlahTagihan DESC";
        List<Klien> daftarTagihan = new ArrayList<>();

        try{
            Connection connection = database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Klien klien = new Klien();
                klien.setNama(resultSet.getString("nama"));
                klien.setJumlahTagihan(resultSet.getBigDecimal("jumlahTagihan").toString());
                daftarTagihan.add(klien);
            }
        } catch (SQLException e) {
            System.out.println("Error menampilkan tagihan: " + e.getMessage());
        }

        System.out.println("DAFTAR TAGIHAN KLIEN:");
        int nomor = 1;
        for (Klien klien : daftarTagihan) {
            System.out.println("");
            System.out.println(nomor +  ". Nama: " + klien.getNama() + " | Tagihan: " + klien.getJumlahTagihan());
            System.out.println("");
            nomor++;
        }
    }

    @Override
    public boolean ubahStatusKasus(Integer number, String statusBaru) {
        return false;
    }

    @Override
    public void showDaftarKlien(String nama, String jenisKasus, String tanggalRegistrasi, String status, String pengacara, String jumlahTagihan) {

    }
}