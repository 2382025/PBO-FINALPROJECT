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

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Klien klien = new Klien();
                klien.setNama(resultSet.getString("nama"));
                klien.setJenisKasus(resultSet.getString("jenis_kasus"));
                klien.setTanggalRegistrasi(resultSet.getDate("tanggal_registrasi").toString());
                klien.setStatus(resultSet.getString("status"));
                klien.setPengacara(resultSet.getString("pengacara"));
                klien.setJumlahTagihan(String.valueOf(resultSet.getBigDecimal("jumlah_tagihan")));
                klienList.add(klien);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching klien data: " + e.getMessage());
        }

        return klienList.toArray(new Klien[0]);
    }

    @Override
    public void showDaftarKlien() {

    }

    @Override
    public void addKlien(String nama, String jenisKasus, String tanggalRegistrasi, String status, String pengacara, String jumlahTagihan) {
        String sqlStatement = "INSERT INTO klien (nama, jenis_kasus, tanggal_registrasi, status, pengacara, jumlah_tagihan) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            preparedStatement.setString(1, nama);
            preparedStatement.setString(2, jenisKasus);
            preparedStatement.setDate(3, java.sql.Date.valueOf(tanggalRegistrasi));
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
        String sqlStatement = "UPDATE klien SET nama = ?, jenis_kasus = ?, tanggal_registrasi = ?, pengacara = ?, jumlah_tagihan = ? WHERE id = ?";

        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            preparedStatement.setString(1, namaBaru);
            preparedStatement.setString(2, jenisKasusBaru);
            preparedStatement.setDate(3, java.sql.Date.valueOf(tanggalBaru));
            preparedStatement.setString(4, pengacaraBaru);
            preparedStatement.setBigDecimal(5, new java.math.BigDecimal(tagihanBaru));
            preparedStatement.setInt(6, number);

            int rowsEffected = preparedStatement.executeUpdate();
            return rowsEffected > 0;
        } catch (SQLException e) {
            System.out.println("Error mengubah data klien: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean removeKlien(Integer number) {
        String sqlStatement = "DELETE FROM klien WHERE id = ?";

        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            preparedStatement.setInt(1, number);

            int rowsEffected = preparedStatement.executeUpdate();
            return rowsEffected > 0;
        } catch (SQLException e) {
            System.out.println("Error menghapus klien: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void searchKlien(String nama) {
        String sqlStatement = "SELECT * FROM klien WHERE nama LIKE ?";

        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            preparedStatement.setString(1, "%" + nama + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    System.out.println("Nama: " + resultSet.getString("nama"));
                    System.out.println("Jenis Kasus: " + resultSet.getString("jenis_kasus"));
                    System.out.println("Tanggal Registrasi: " + resultSet.getDate("tanggal_registrasi"));
                    System.out.println("Status: " + resultSet.getString("status"));
                    System.out.println("Pengacara: " + resultSet.getString("pengacara"));
                    System.out.println("Jumlah Tagihan: " + resultSet.getBigDecimal("jumlah_tagihan"));
                    System.out.println("-------------------");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error mencari klien: " + e.getMessage());
        }
    }

    @Override
    public void showStatistikKasus() {
        String sqlStatement = "SELECT jenis_kasus, COUNT(*) as jumlah, " +
                "AVG(CASE WHEN status = 'Selesai' THEN 1.0 ELSE 0.0 END) * 100 as persentase_selesai " +
                "FROM klien GROUP BY jenis_kasus";

        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("STATISTIK KASUS:");
            while (resultSet.next()) {
                System.out.println("Jenis Kasus: " + resultSet.getString("jenis_kasus"));
                System.out.println("Jumlah Kasus: " + resultSet.getInt("jumlah"));
                System.out.println("Persentase Selesai: " + resultSet.getDouble("persentase_selesai") + "%");
                System.out.println("-------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error menampilkan statistik kasus: " + e.getMessage());
        }
    }

    @Override
    public void showDaftarTagihan() {
        String sqlStatement = "SELECT nama, jenis_kasus, jumlah_tagihan, status " +
                "FROM klien ORDER BY jumlah_tagihan DESC";

        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("DAFTAR TAGIHAN:");
            while (resultSet.next()) {
                System.out.println("Nama: " + resultSet.getString("nama"));
                System.out.println("Jenis Kasus: " + resultSet.getString("jenis_kasus"));
                System.out.println("Jumlah Tagihan: " + resultSet.getBigDecimal("jumlah_tagihan"));
                System.out.println("Status: " + resultSet.getString("status"));
                System.out.println("-------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error menampilkan daftar tagihan: " + e.getMessage());
        }
    }

    @Override
    public boolean ubahStatusKasus(Integer number, String statusBaru) {
        String sqlStatement = "UPDATE klien SET status = ? WHERE id = ?";

        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {

            preparedStatement.setString(1, statusBaru);
            preparedStatement.setInt(2, number);

            int rowsEffected = preparedStatement.executeUpdate();
            return rowsEffected > 0;
        } catch (SQLException e) {
            System.out.println("Error mengubah status kasus: " + e.getMessage());
            return false;
        }
    }
}