<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mlhero.model.Hero" %>
<%@ page import="java.util.List" %>

<!--
  Halaman untuk menampilkan semua data hero dari database.
  Ditampilkan dalam bentuk tabel.
-->
<html>
<head>
    <title>Daftar Hero Mobile Legend</title>
    <style>
    body {
        font-family: Arial, sans-serif;
        margin: 40px;
        background-color: #f6f6f6;
        color: #333;
    }

    h1 {
        color: #1e88e5;
    }

    a {
        text-decoration: none;
        color: #1976d2;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        background-color: #fff;
        box-shadow: 0 2px 5px rgba(0,0,0,0.1);
    }

    th, td {
        border: 1px solid #ddd;
        padding: 10px;
        text-align: left;
    }

    th {
        background-color: #e3f2fd;
    }

    form {
        background-color: #fff;
        padding: 20px;
        width: 400px;
        box-shadow: 0 2px 5px rgba(0,0,0,0.1);
    }

    input[type="text"], select, input[type="number"] {
        width: 100%;
        padding: 8px;
        margin-top: 5px;
        margin-bottom: 15px;
    }

    input[type="submit"] {
        padding: 10px 20px;
        background-color: #1e88e5;
        border: none;
        color: white;
        cursor: pointer;
    }

    </style>
</head>
<body>
<h1>Daftar Hero Mobile Legend</h1>

<!-- Tombol menuju form tambah -->
<a href="new">Tambah Hero Baru</a>
<br/><br/>

<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>Nama</th>
        <th>Kategori</th>
        <th>Gender</th>
        <th>Aksi</th>
    </tr>

    <%
        // Ambil listHero dari request attribute
        List<Hero> listHero = (List<Hero>) request.getAttribute("listHero");
        if (listHero != null) {
            for (Hero hero : listHero) {
    %>
    <tr>
        <td><%= hero.getIdHero() %></td>
        <td><%= hero.getNamaHero() %></td>
        <td><%= hero.getKategori() %></td>
        <td><%= hero.getGender() %></td>
        <td>
            <a href="edit?id=<%= hero.getIdHero() %>">Edit</a> |
            <a href="delete?id=<%= hero.getIdHero() %>" onclick="return confirm('Yakin hapus hero ini?')">Hapus</a>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>

</body>
</html>
