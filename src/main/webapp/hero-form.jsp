<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mlhero.model.Hero" %>

<%
    // Ambil data hero dari request attribute (jika edit)
    Hero hero = (Hero) request.getAttribute("hero");
    boolean isEdit = (hero != null);
%>

<!--
  Form untuk tambah atau edit data hero.
  Jika 'hero' ada, maka ini mode edit. Jika null, maka tambah.
-->
<html>
<head>
    <title><%= isEdit ? "Edit Hero" : "Tambah Hero Baru" %></title>
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
<h1><%= isEdit ? "Edit Hero" : "Tambah Hero Baru" %></h1>

<form action="<%= isEdit ? "update" : "insert" %>" method="post">
    <% if (isEdit) { %>
        <!-- Hidden field untuk ID saat update -->
        <input type="hidden" name="idHero" value="<%= hero.getIdHero() %>"/>
    <% } %>

    Nama Hero:<br/>
    <input type="text" name="namaHero" value="<%= isEdit ? hero.getNamaHero() : "" %>" required/><br/>

    Kategori:<br/>
    <select name="kategori" required>
        <option value="">-- Pilih Kategori --</option>
        <%
            String[] kategoriList = {"MAGE", "ASSASIN", "FIGHTER", "TANK", "MARKSMAN", "SUPPORT"};
            for (String kategori : kategoriList) {
                boolean selected = isEdit && kategori.equalsIgnoreCase(hero.getKategori());
        %>
        <option value="<%= kategori %>" <%= selected ? "selected" : "" %>><%= kategori %></option>
        <%
            }
        %>
    </select><br/>

    Gender:<br/>
    <input type="radio" name="gender" value="MALE" <%= isEdit && "MALE".equalsIgnoreCase(hero.getGender()) ? "checked" : "" %>/> Male
    <input type="radio" name="gender" value="FEMALE" <%= isEdit && "FEMALE".equalsIgnoreCase(hero.getGender()) ? "checked" : "" %>/> Female
    <br/><br/>

    <input type="submit" value="<%= isEdit ? "Update" : "Simpan" %>"/>
</form>

<br/>
<a href="list">Kembali ke Daftar</a>
</body>
</html>
