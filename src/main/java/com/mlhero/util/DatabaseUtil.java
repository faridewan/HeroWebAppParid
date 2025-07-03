package com.mlhero.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class untuk membuat koneksi ke database MySQL.
 * Digunakan oleh DAO agar tidak menulis ulang koneksi di setiap operasi.
 */
public class DatabaseUtil {
    // Konfigurasi koneksi ke database
    private static final String URL = "jdbc:mysql://localhost:3306/ml_db";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // gapunya password

    /**
     * Method untuk mendapatkan objek Connection.
     * @return koneksi aktif ke database
     * @throws SQLException jika gagal terhubung
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Memuat driver JDBC secara eksplisit
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Mengembalikan koneksi aktif
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
