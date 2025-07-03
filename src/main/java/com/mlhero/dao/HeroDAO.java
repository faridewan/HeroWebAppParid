package com.mlhero.dao;

import com.mlhero.model.Hero;
import com.mlhero.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class untuk mengelola operasi CRUD pada tabel tm_hero.
 * Menyimpan semua logika SQL seperti insert, update, delete, dan select.
 */
public class HeroDAO {

    // SQL Query dasar
    private static final String INSERT_HERO_SQL = "INSERT INTO tm_hero (nama_hero, kategori, gender) VALUES (?, ?, ?)";
    private static final String SELECT_HERO_BY_ID = "SELECT * FROM tm_hero WHERE id_hero = ?";
    private static final String SELECT_ALL_HEROES = "SELECT * FROM tm_hero";
    private static final String DELETE_HERO_SQL = "DELETE FROM tm_hero WHERE id_hero = ?";
    private static final String UPDATE_HERO_SQL = "UPDATE tm_hero SET nama_hero = ?, kategori = ?, gender = ? WHERE id_hero = ?";

    /**
     * Menambahkan data hero ke database.
     */
    public void insertHero(Hero hero) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_HERO_SQL)) {

            statement.setString(1, hero.getNamaHero());
            statement.setString(2, hero.getKategori());
            statement.setString(3, hero.getGender());
            statement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    /**
     * Mengambil 1 hero berdasarkan ID.
     */
    public Hero selectHero(int id) {
        Hero hero = null;
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_HERO_BY_ID)) {

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                hero = new Hero(
                        rs.getInt("id_hero"),
                        rs.getString("nama_hero"),
                        rs.getString("kategori"),
                        rs.getString("gender")
                );
            }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return hero;
    }

    /**
     * Mengambil semua data hero dari database.
     */
    public List<Hero> selectAllHeroes() {
        List<Hero> heroes = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_HEROES)) {

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Hero hero = new Hero(
                        rs.getInt("id_hero"),
                        rs.getString("nama_hero"),
                        rs.getString("kategori"),
                        rs.getString("gender")
                );
                heroes.add(hero);
            }

        } catch (SQLException e) {
            printSQLException(e);
        }

        return heroes;
    }

    /**
     * Menghapus data hero berdasarkan ID.
     */
    public boolean deleteHero(int id) {
        boolean rowDeleted = false;

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_HERO_SQL)) {

            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;

        } catch (SQLException e) {
            printSQLException(e);
        }

        return rowDeleted;
    }

    /**
     * Mengupdate data hero berdasarkan ID.
     */
    public boolean updateHero(Hero hero) {
        boolean rowUpdated = false;

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_HERO_SQL)) {

            statement.setString(1, hero.getNamaHero());
            statement.setString(2, hero.getKategori());
            statement.setString(3, hero.getGender());
            statement.setInt(4, hero.getIdHero());

            rowUpdated = statement.executeUpdate() > 0;

        } catch (SQLException e) {
            printSQLException(e);
        }

        return rowUpdated;
    }

    // Menampilkan detail error SQL
    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            e.printStackTrace(System.err);
        }
    }
}
