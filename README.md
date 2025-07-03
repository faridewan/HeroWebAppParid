# HeroWebAppParid – UAS PBO Java Web

Aplikasi CRUD berbasis Java Web untuk mendata Hero Mobile Legend. Dibuat untuk memenuhi Ujian Akhir Semester mata kuliah Pemrograman Berorientasi Objek (PBO).

## 📚 Fitur

- Tambah data hero
- Tampilkan daftar hero
- Edit data hero
- Hapus data hero

## 💻 Teknologi yang Digunakan

- Java 23 (Oracle OpenJDK)
- Jakarta Servlet 6
- JSP (Java Server Pages)
- Apache Tomcat 11
- MySQL (phpMyAdmin)
- Maven
- IntelliJ IDEA Community Edition

## 🗄️ Struktur Database

Database: `ml_db`  
Tabel: `tm_hero`

```sql
CREATE DATABASE ml_db;

CREATE TABLE tm_hero (
  id_hero INT AUTO_INCREMENT PRIMARY KEY,
  nama_hero VARCHAR(100),
  kategori ENUM('MAGE','ASSASIN','FIGHTER','TANK','MARKSMAN','SUPPORT'),
  gender ENUM('MALE','FEMALE')
);


📁 Struktur Proyek

HeroWebAppParid/
├── src/
│   └── main/
│       ├── java/com/mlhero/
│       │   ├── controller/HeroServlet.java
│       │   ├── dao/HeroDAO.java
│       │   ├── model/Hero.java
│       │   └── util/DatabaseUtil.java
│       └── webapp/
│           ├── hero-list.jsp
│           └── hero-form.jsp
├── pom.xml
└── README.md

✍️ Kontributor
👤 Nama: Parid Hendrawan
🎓 NIM: SSI202303424