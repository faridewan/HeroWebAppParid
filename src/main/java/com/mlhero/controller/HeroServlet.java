package com.mlhero.controller;

import com.mlhero.dao.HeroDAO;
import com.mlhero.model.Hero;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

/**
 * Servlet utama yang mengatur routing dan aksi user.
 * Menangani permintaan dari browser seperti /new, /insert, /edit, /update, /delete.
 */
@WebServlet("/")
public class HeroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private HeroDAO heroDAO;

    // Inisialisasi DAO saat servlet di-load pertama kali
    public void init() {
        heroDAO = new HeroDAO();
    }

    // Method POST diarahkan ke GET supaya bisa reuse logic
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    // Method utama untuk menangani semua request GET
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath(); // contoh: "/insert"

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertHero(request, response);
                    break;
                case "/delete":
                    deleteHero(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateHero(request, response);
                    break;
                default:
                    listHero(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    /**
     * Menampilkan daftar hero
     */
    private void listHero(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Hero> listHero = heroDAO.selectAllHeroes();
        request.setAttribute("listHero", listHero);
        RequestDispatcher dispatcher = request.getRequestDispatcher("hero-list.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Menampilkan form tambah hero
     */
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("hero-form.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Menampilkan form edit berdasarkan ID
     */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Hero existingHero = heroDAO.selectHero(id);
        request.setAttribute("hero", existingHero);
        RequestDispatcher dispatcher = request.getRequestDispatcher("hero-form.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Menambahkan data hero baru
     */
    private void insertHero(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String nama = request.getParameter("namaHero");
        String kategori = request.getParameter("kategori");
        String gender = request.getParameter("gender");

        Hero newHero = new Hero(nama, kategori, gender);
        heroDAO.insertHero(newHero);
        response.sendRedirect("list");
    }

    /**
     * Mengupdate data hero
     */
    private void updateHero(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("idHero"));
        String nama = request.getParameter("namaHero");
        String kategori = request.getParameter("kategori");
        String gender = request.getParameter("gender");

        Hero hero = new Hero(id, nama, kategori, gender);
        heroDAO.updateHero(hero);
        response.sendRedirect("list");
    }

    /**
     * Menghapus data hero berdasarkan ID
     */
    private void deleteHero(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        heroDAO.deleteHero(id);
        response.sendRedirect("list");
    }
}
