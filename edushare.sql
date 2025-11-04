-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: edushare
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id_pengguna` int NOT NULL,
  `id_sekolah` int NOT NULL,
  PRIMARY KEY (`id_pengguna`),
  KEY `id_sekolah` (`id_sekolah`),
  CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`id_sekolah`) REFERENCES `sekolah` (`id_sekolah`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK2b60avbubdukmub1agdv10ipu` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,1),(2,1),(3,1),(4,1);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dokumen`
--

DROP TABLE IF EXISTS `dokumen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dokumen` (
  `id_dokumen` int NOT NULL AUTO_INCREMENT,
  `id_sekolah` int NOT NULL,
  `jenis_dokumen` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `nama_file` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `path_file` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `tanggal_upload` datetime NOT NULL,
  `status_verifikasi` enum('menunggu','diterima','ditolak') COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'menunggu',
  `catatan_admin` text COLLATE utf8mb4_general_ci,
  PRIMARY KEY (`id_dokumen`),
  KEY `id_sekolah` (`id_sekolah`),
  CONSTRAINT `dokumen_ibfk_1` FOREIGN KEY (`id_sekolah`) REFERENCES `sekolah` (`id_sekolah`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dokumen`
--

LOCK TABLES `dokumen` WRITE;
/*!40000 ALTER TABLE `dokumen` DISABLE KEYS */;
INSERT INTO `dokumen` VALUES (1,1,'Proposal Donasi','proposal_sd1.pdf','/uploads/sd1/proposal_sd1.pdf','2025-10-01 09:15:00','diterima','Sudah diverifikasi'),(2,2,'Proposal Donasi','proposal_smp2.pdf','/uploads/smp2/proposal_smp2.pdf','2025-10-02 10:00:00','menunggu',NULL),(3,3,'Laporan Keuangan','laporan_sma3.pdf','/uploads/sma3/laporan_sma3.pdf','2025-10-03 11:30:00','diterima','Data lengkap'),(4,4,'Foto Kegiatan','foto_smk1.jpg','/uploads/smk1/foto_smk1.jpg','2025-10-04 08:45:00','menunggu',NULL),(5,5,'Proposal Donasi','proposal_sdfalah.pdf','/uploads/sd5/proposal_sdfalah.pdf','2025-10-05 14:20:00','diterima','Lengkap dan jelas'),(6,6,'Surat Permohonan','permohonan_smp4.pdf','/uploads/smp4/permohonan_smp4.pdf','2025-10-06 09:10:00','menunggu',NULL),(7,7,'Laporan Penggunaan Dana','laporan_sma2.pdf','/uploads/sma2/laporan_sma2.pdf','2025-10-07 10:40:00','ditolak','Kurang tanda tangan kepala sekolah'),(8,8,'Proposal Donasi','proposal_smk3.pdf','/uploads/smk3/proposal_smk3.pdf','2025-10-08 12:00:00','menunggu',NULL),(9,9,'Dokumentasi Renovasi','foto_sd5.jpg','/uploads/sd5/foto_sd5.jpg','2025-10-08 15:00:00','diterima','Dokumentasi bagus'),(10,10,'Proposal Donasi','proposal_smp6.pdf','/uploads/smp6/proposal_smp6.pdf','2025-10-09 13:25:00','menunggu',NULL),(11,11,'Laporan Akhir','laporan_sma1.pdf','/uploads/sma1/laporan_sma1.pdf','2025-10-10 09:00:00','diterima','Sudah sesuai format'),(12,12,'Proposal Donasi','proposal_smk2.pdf','/uploads/smk2/proposal_smk2.pdf','2025-10-11 10:50:00','menunggu',NULL),(13,13,'Laporan Keuangan','laporan_sd7.pdf','/uploads/sd7/laporan_sd7.pdf','2025-10-12 11:10:00','diterima','Verifikasi selesai'),(14,14,'Foto Kegiatan','foto_smp3.jpg','/uploads/smp3/foto_smp3.jpg','2025-10-13 14:00:00','diterima','Kegiatan sesuai laporan'),(15,15,'Proposal Donasi','proposal_sma_katolik.pdf','/uploads/sma15/proposal_sma_katolik.pdf','2025-10-13 15:00:00','menunggu',NULL),(16,16,'Laporan Praktikum','laporan_smkpgri.pdf','/uploads/smkpgri/laporan_smkpgri.pdf','2025-10-14 08:30:00','diterima','Sudah diperiksa'),(17,17,'Proposal Donasi','proposal_sd4.pdf','/uploads/sd4/proposal_sd4.pdf','2025-10-14 10:15:00','menunggu',NULL),(18,18,'Foto Kegiatan','foto_smp1.jpg','/uploads/smp1/foto_smp1.jpg','2025-10-15 09:45:00','diterima','Kegiatan valid'),(19,19,'Laporan Keuangan','laporan_sma5.pdf','/uploads/sma5/laporan_sma5.pdf','2025-10-15 11:30:00','menunggu',NULL),(20,20,'Proposal Donasi','proposal_smk4.pdf','/uploads/smk4/proposal_smk4.pdf','2025-10-16 08:40:00','diterima','Lengkap'),(21,21,'Proposal Donasi','proposal_sd6.pdf','/uploads/sd6/proposal_sd6.pdf','2025-10-16 09:30:00','menunggu',NULL),(22,22,'Laporan Kegiatan','laporan_smp5.pdf','/uploads/smp5/laporan_smp5.pdf','2025-10-17 13:10:00','ditolak','Kurang lampiran foto'),(23,23,'Proposal Donasi','proposal_sma4.pdf','/uploads/sma4/proposal_sma4.pdf','2025-10-17 14:00:00','menunggu',NULL),(24,24,'Foto Peralatan','foto_smk5.jpg','/uploads/smk5/foto_smk5.jpg','2025-10-18 09:20:00','diterima','Sudah diverifikasi'),(25,25,'Proposal Donasi','proposal_sdkristen.pdf','/uploads/sd25/proposal_sdkristen.pdf','2025-10-18 10:10:00','menunggu',NULL),(26,26,'Foto Kegiatan','foto_smp8.jpg','/uploads/smp8/foto_smp8.jpg','2025-10-18 11:15:00','diterima','Foto jelas'),(27,27,'Laporan Kegiatan','laporan_sma6.pdf','/uploads/sma6/laporan_sma6.pdf','2025-10-19 08:50:00','menunggu',NULL),(28,28,'Proposal Donasi','proposal_smk6.pdf','/uploads/smk6/proposal_smk6.pdf','2025-10-19 09:40:00','diterima','Lengkap'),(29,29,'Laporan Renovasi','laporan_sd3.pdf','/uploads/sd3/laporan_sd3.pdf','2025-10-19 10:30:00','menunggu',NULL),(30,30,'Proposal Donasi','proposal_smpislam.pdf','/uploads/smp30/proposal_smpislam.pdf','2025-10-19 11:00:00','diterima','Sudah dicek'),(31,31,'Foto Kegiatan','foto_sma7.jpg','/uploads/sma7/foto_sma7.jpg','2025-10-19 11:45:00','menunggu',NULL),(32,32,'Laporan Keuangan','laporan_smkmu.pdf','/uploads/smkmu/laporan_smkmu.pdf','2025-10-19 13:00:00','diterima','Validasi selesai'),(33,33,'Proposal Donasi','proposal_sd8.pdf','/uploads/sd8/proposal_sd8.pdf','2025-10-19 14:20:00','menunggu',NULL),(34,34,'Foto Kegiatan','foto_smp9.jpg','/uploads/smp9/foto_smp9.jpg','2025-10-19 15:00:00','diterima','Bagus'),(35,35,'Laporan Akhir','laporan_sma8.pdf','/uploads/sma8/laporan_sma8.pdf','2025-10-19 15:30:00','diterima','Verifikasi ok'),(36,36,'Proposal Donasi','proposal_smk7.pdf','/uploads/smk7/proposal_smk7.pdf','2025-10-19 16:00:00','menunggu',NULL),(37,37,'Foto Renovasi','foto_sd9.jpg','/uploads/sd9/foto_sd9.jpg','2025-10-20 08:30:00','diterima','Foto sesuai kondisi'),(38,38,'Proposal Donasi','proposal_smp10.pdf','/uploads/smp10/proposal_smp10.pdf','2025-10-20 09:00:00','menunggu',NULL),(39,39,'Laporan Keuangan','laporan_sma9.pdf','/uploads/sma9/laporan_sma9.pdf','2025-10-20 09:30:00','diterima','Valid'),(40,40,'Proposal Donasi','proposal_smk8.pdf','/uploads/smk8/proposal_smk8.pdf','2025-10-20 10:00:00','menunggu',NULL),(41,40,'Proposal Donasi','Studi Kasus Pertemuan ke-3.pdf','/uploads/sekolah40/Studi Kasus Pertemuan ke-3.pdf','2025-11-03 01:39:16','menunggu',NULL),(42,40,'Proposal Donasi','Surat Kontrak Praktikum_Semester 2_2409116074_Ahmad Dani.pdf','/uploads/sekolah40/Surat Kontrak Praktikum_Semester 2_2409116074_Ahmad Dani.pdf','2025-11-03 02:22:21','menunggu',NULL),(43,40,'Proposal Donasi','Surat Kontrak Praktikum_Semester 2_2409116074_Ahmad Dani.pdf','uploads/sekolah40/Surat Kontrak Praktikum_Semester 2_2409116074_Ahmad Dani.pdf','2025-11-03 02:39:08','menunggu',NULL),(44,40,'Proposal Donasi','Tugas Pertemuan 4.pdf','uploads/sekolah40/Tugas Pertemuan 4.pdf','2025-11-03 02:39:49','menunggu',NULL),(45,43,'Proposal Donasi','Surat Kontrak Praktikum_Semester 2_2409116074_Ahmad Dani.pdf','uploads/sekolah43/Surat Kontrak Praktikum_Semester 2_2409116074_Ahmad Dani.pdf','2025-11-03 07:39:52','menunggu',NULL),(46,43,'Proposal Donasi','Surat Kontrak Praktikum_Semester 2_2409116074_Ahmad Dani.pdf','uploads/sekolah43/Surat Kontrak Praktikum_Semester 2_2409116074_Ahmad Dani.pdf','2025-11-03 10:02:29','menunggu',NULL),(47,43,'Proposal Donasi','proposal_sd1.pdf','uploads/sekolah43/proposal_sd1.pdf','2025-11-03 10:53:19','menunggu',NULL);
/*!40000 ALTER TABLE `dokumen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donasi`
--

DROP TABLE IF EXISTS `donasi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donasi` (
  `id_donasi` int NOT NULL AUTO_INCREMENT,
  `id_pengguna` int NOT NULL,
  `id_kebutuhan` int NOT NULL,
  `tanggal_donasi` date NOT NULL,
  `jumlah_donasi` decimal(15,2) NOT NULL,
  `metode_pembayaran` enum('transfer','cash','qris','lainnya') COLLATE utf8mb4_general_ci NOT NULL,
  `status_donasi` enum('pending','berhasil','gagal','dibatalkan') COLLATE utf8mb4_general_ci NOT NULL,
  `total_donasi` decimal(15,2) DEFAULT NULL,
  PRIMARY KEY (`id_donasi`),
  KEY `id_pengguna` (`id_pengguna`),
  KEY `id_kebutuhan` (`id_kebutuhan`),
  CONSTRAINT `fk_donasi_pengguna` FOREIGN KEY (`id_pengguna`) REFERENCES `donatur` (`id_pengguna`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKev9tle88rm91qhd8vslxl0u7v` FOREIGN KEY (`id_kebutuhan`) REFERENCES `kebutuhan` (`id_kebutuhan`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donasi`
--

LOCK TABLES `donasi` WRITE;
/*!40000 ALTER TABLE `donasi` DISABLE KEYS */;
INSERT INTO `donasi` VALUES (41,5,1,'2025-10-01',1200000.00,'transfer','berhasil',NULL),(42,7,2,'2025-10-02',800000.00,'qris','berhasil',NULL),(43,8,3,'2025-10-02',2000000.00,'cash','berhasil',NULL),(44,10,4,'2025-10-03',750000.00,'transfer','berhasil',NULL),(45,13,5,'2025-10-03',3000000.00,'transfer','berhasil',NULL),(46,14,6,'2025-10-04',5500000.00,'qris','berhasil',NULL),(47,16,7,'2025-10-04',1000000.00,'cash','berhasil',NULL),(48,20,8,'2025-10-05',1500000.00,'transfer','berhasil',NULL),(49,22,9,'2025-10-05',1200000.00,'cash','berhasil',NULL),(50,24,10,'2025-10-06',2250000.00,'qris','berhasil',NULL),(51,27,11,'2025-10-06',4000000.00,'transfer','berhasil',NULL),(52,29,12,'2025-10-07',1700000.00,'cash','berhasil',NULL),(53,31,13,'2025-10-07',900000.00,'transfer','berhasil',NULL),(54,33,14,'2025-10-08',2100000.00,'qris','berhasil',NULL),(55,35,15,'2025-10-08',3500000.00,'transfer','berhasil',NULL),(56,38,16,'2025-10-09',1600000.00,'cash','berhasil',NULL),(57,40,17,'2025-10-09',1400000.00,'transfer','berhasil',NULL),(58,42,18,'2025-10-10',2000000.00,'qris','berhasil',NULL),(59,43,19,'2025-10-10',2800000.00,'transfer','berhasil',NULL),(60,44,20,'2025-10-11',3600000.00,'cash','berhasil',NULL),(61,45,21,'2025-10-11',1900000.00,'transfer','berhasil',NULL),(62,46,22,'2025-10-12',2200000.00,'qris','berhasil',NULL),(63,5,23,'2025-10-12',1250000.00,'cash','berhasil',NULL),(64,7,24,'2025-10-13',1800000.00,'transfer','berhasil',NULL),(65,8,25,'2025-10-13',1950000.00,'qris','berhasil',NULL),(66,10,26,'2025-10-14',2400000.00,'transfer','berhasil',NULL),(67,13,27,'2025-10-14',1600000.00,'cash','berhasil',NULL),(68,14,28,'2025-10-15',3700000.00,'transfer','berhasil',NULL),(69,16,29,'2025-10-15',2100000.00,'qris','berhasil',NULL),(70,20,30,'2025-10-16',2800000.00,'transfer','berhasil',NULL),(71,22,31,'2025-10-16',2500000.00,'qris','berhasil',NULL),(72,24,32,'2025-10-17',2900000.00,'transfer','berhasil',NULL),(73,27,33,'2025-10-17',1850000.00,'cash','berhasil',NULL),(74,29,34,'2025-10-18',1700000.00,'transfer','berhasil',NULL),(75,31,35,'2025-10-18',2350000.00,'qris','berhasil',NULL),(76,33,36,'2025-10-19',3100000.00,'transfer','berhasil',NULL),(77,35,37,'2025-10-19',3200000.00,'cash','berhasil',NULL),(78,38,38,'2025-10-20',1850000.00,'transfer','berhasil',NULL),(79,40,39,'2025-10-20',1950000.00,'qris','berhasil',NULL),(80,42,40,'2025-10-21',3000000.00,'transfer','berhasil',NULL),(81,43,40,'2025-11-03',90000000.00,'transfer','berhasil',NULL),(82,43,40,'2025-11-03',8900000.00,'transfer','gagal',NULL),(83,49,43,'2025-11-03',150000.00,'transfer','berhasil',NULL),(84,49,1,'2025-11-03',1900000.00,'transfer','berhasil',NULL),(85,49,3,'2025-11-03',490000.00,'transfer','berhasil',NULL),(86,49,2,'2025-11-03',1500000.00,'transfer','berhasil',0.00),(87,49,45,'2025-11-03',250000.00,'transfer','pending',0.00),(88,43,45,'2025-11-03',9000000.00,'transfer','berhasil',0.00);
/*!40000 ALTER TABLE `donasi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donatur`
--

DROP TABLE IF EXISTS `donatur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donatur` (
  `id_donatur` int NOT NULL AUTO_INCREMENT,
  `id_pengguna` int NOT NULL,
  `id_sekolah` int DEFAULT NULL,
  `total_donasi` decimal(15,2) NOT NULL,
  PRIMARY KEY (`id_donatur`),
  UNIQUE KEY `id_pengguna` (`id_pengguna`,`id_sekolah`),
  KEY `fk_donatur_sekolah` (`id_sekolah`),
  CONSTRAINT `fk_donatur_pengguna` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_donatur_sekolah` FOREIGN KEY (`id_sekolah`) REFERENCES `sekolah` (`id_sekolah`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donatur`
--

LOCK TABLES `donatur` WRITE;
/*!40000 ALTER TABLE `donatur` DISABLE KEYS */;
INSERT INTO `donatur` VALUES (1,5,1,1200000.00),(2,5,23,1250000.00),(3,7,2,800000.00),(4,7,24,1800000.00),(5,8,3,2000000.00),(6,8,25,1950000.00),(7,10,4,750000.00),(8,10,26,2400000.00),(9,13,5,3000000.00),(10,13,27,1600000.00),(11,14,6,5500000.00),(12,14,28,3700000.00),(13,16,7,1000000.00),(14,16,29,2100000.00),(15,20,8,1500000.00),(16,20,30,2800000.00),(17,22,9,1200000.00),(18,22,31,2500000.00),(19,24,10,2250000.00),(20,24,32,2900000.00),(21,27,11,4000000.00),(22,27,33,1850000.00),(23,29,12,1700000.00),(24,29,34,1700000.00),(25,31,13,900000.00),(26,31,35,2350000.00),(27,33,14,2100000.00),(28,33,36,3100000.00),(29,35,15,3500000.00),(30,35,37,3200000.00),(31,38,16,1600000.00),(32,38,38,1850000.00),(33,40,17,1400000.00),(34,40,39,1950000.00),(35,42,18,2000000.00),(36,42,40,3000000.00),(37,43,19,101800000.00),(38,44,20,3600000.00),(39,45,21,1900000.00),(40,46,22,2200000.00),(41,49,NULL,4040000.00);
/*!40000 ALTER TABLE `donatur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kebutuhan`
--

DROP TABLE IF EXISTS `kebutuhan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kebutuhan` (
  `id_kebutuhan` int NOT NULL AUTO_INCREMENT,
  `id_sekolah` int NOT NULL,
  `nama_alat` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `jumlah` int NOT NULL,
  `prioritas` enum('tinggi','sedang','rendah') COLLATE utf8mb4_general_ci NOT NULL,
  `deskripsi` text COLLATE utf8mb4_general_ci,
  `tanggal_pengajuan` date NOT NULL,
  `status` enum('diajukan','diverifikasi','ditolak','terpenuhi') COLLATE utf8mb4_general_ci NOT NULL,
  `total_kebutuhan` decimal(15,2) DEFAULT NULL,
  `target_donasi` decimal(15,2) DEFAULT NULL,
  `total_terkumpul` decimal(15,2) DEFAULT NULL,
  PRIMARY KEY (`id_kebutuhan`),
  KEY `id_sekolah` (`id_sekolah`),
  CONSTRAINT `FK4sna0cikkmge157gu18wvftf5` FOREIGN KEY (`id_sekolah`) REFERENCES `sekolah` (`id_sekolah`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kebutuhan`
--

LOCK TABLES `kebutuhan` WRITE;
/*!40000 ALTER TABLE `kebutuhan` DISABLE KEYS */;
INSERT INTO `kebutuhan` VALUES (1,1,'Laptop Guru',5,'tinggi','Mendukung pembelajaran digital.','2025-11-01','diverifikasi',5000000.00,3220494.00,3100000.00),(2,2,'Proyektor',2,'sedang','Untuk kegiatan presentasi kelas.','2025-11-01','diverifikasi',3000000.00,2931913.00,2300000.00),(3,3,'Kursi Siswa',40,'tinggi','Mengganti kursi lama.','2025-11-01','diverifikasi',8000000.00,3721909.00,2490000.00),(4,4,'Meja Belajar',35,'tinggi','Untuk ruang kelas baru.','2025-11-01','terpenuhi',7000000.00,6329056.00,1479792.00),(5,5,'Buku Pelajaran',100,'sedang','Buku pelajaran utama siswa.','2025-11-01','diverifikasi',2500000.00,3754885.00,1319853.00),(6,6,'Papan Tulis Digital',1,'tinggi','Pembelajaran interaktif.','2025-11-01','diverifikasi',4000000.00,6381303.00,2388348.00),(7,7,'Printer Sekolah',1,'rendah','Administrasi dan laporan.','2025-11-01','terpenuhi',2000000.00,4450792.00,1547349.00),(8,8,'Peralatan Olahraga',15,'sedang','Kegiatan olahraga siswa.','2025-11-01','diverifikasi',3500000.00,4133333.00,979719.00),(9,9,'Alat Musik',10,'rendah','Untuk ekstrakurikuler musik.','2025-11-01','diverifikasi',1500000.00,5476197.00,956319.00),(10,10,'Komputer Lab',10,'tinggi','Lab komputer baru.','2025-11-01','ditolak',8000000.00,3847739.00,2249958.00),(11,11,'Sound System',1,'sedang','Untuk upacara dan acara sekolah.','2025-11-01','terpenuhi',2500000.00,5821281.00,3134902.00),(12,12,'Peralatan IPA',8,'tinggi','Alat laboratorium IPA.','2025-11-01','diajukan',4000000.00,2974679.00,503416.00),(13,13,'Kipas Angin',6,'rendah','Ventilasi ruang kelas.','2025-11-01','diverifikasi',1800000.00,6127372.00,1033820.00),(14,14,'Proyektor Mini',3,'sedang','Untuk kelompok belajar.','2025-11-01','terpenuhi',2700000.00,6647952.00,3208343.00),(15,15,'Rak Buku',5,'rendah','Koleksi perpustakaan.','2025-11-01','diajukan',1600000.00,3164738.00,1427373.00),(16,16,'Seragam Siswa',50,'sedang','Untuk siswa kurang mampu.','2025-11-01','diverifikasi',5000000.00,2298451.00,59757.00),(17,17,'Whiteboard',10,'sedang','Mengganti papan lama.','2025-11-01','terpenuhi',2500000.00,2187938.00,76066.00),(18,18,'Kabel LAN',20,'rendah','Perluasan jaringan sekolah.','2025-11-01','diverifikasi',1000000.00,2884771.00,1230540.00),(19,19,'Komputer Admin',2,'sedang','Untuk staf tata usaha.','2025-11-01','terpenuhi',6000000.00,2119201.00,1254201.00),(20,20,'Printer Warna',1,'rendah','Percetakan laporan guru.','2025-11-01','diverifikasi',1800000.00,6301875.00,1295936.00),(21,21,'Proyektor Kelas',1,'tinggi','Pembelajaran interaktif.','2025-11-01','diajukan',3000000.00,2662829.00,1013922.00),(22,22,'Alat Kebersihan',15,'rendah','Sapu, pel, ember.','2025-11-01','terpenuhi',1200000.00,5876880.00,3431049.00),(23,23,'Laptop Kepala Sekolah',1,'tinggi','Administrasi kepala sekolah.','2025-11-01','diajukan',7000000.00,4695257.00,2186532.00),(24,24,'Kamera Dokumentasi',1,'rendah','Dokumentasi kegiatan sekolah.','2025-11-01','diverifikasi',3500000.00,3317958.00,1969894.00),(25,25,'Buku Perpustakaan',200,'sedang','Koleksi bacaan baru.','2025-11-01','terpenuhi',4000000.00,2783902.00,1361945.00),(26,26,'AC Ruang Guru',2,'rendah','Kenyamanan ruang guru.','2025-11-01','diajukan',4500000.00,5032521.00,1770683.00),(27,27,'Speaker Portable',1,'sedang','Kegiatan luar ruangan.','2025-11-01','diverifikasi',2200000.00,2562778.00,1235573.00),(28,28,'Jaringan Internet',1,'tinggi','Wi-Fi sekolah.','2025-11-01','terpenuhi',5000000.00,5400103.00,3206009.00),(29,29,'Buku Gambar',50,'rendah','Pelajaran seni rupa.','2025-11-01','diajukan',800000.00,6536943.00,2229647.00),(30,30,'LCD Projector',1,'tinggi','Pembelajaran multimedia.','2025-11-01','diverifikasi',6000000.00,2601005.00,1397652.00),(31,31,'Laptop Siswa',10,'tinggi','Pembelajaran digital.','2025-11-01','terpenuhi',8000000.00,2586604.00,1396533.00),(32,32,'Peralatan Lab Kimia',12,'sedang','Praktikum IPA.','2025-11-01','diajukan',4500000.00,2736427.00,60549.00),(33,33,'Alat Musik Tradisional',6,'rendah','Seni budaya daerah.','2025-11-01','diverifikasi',2200000.00,5712683.00,2063584.00),(34,34,'Printer A3',1,'rendah','Mencetak dokumen besar.','2025-11-01','terpenuhi',2500000.00,5913139.00,379610.00),(35,35,'Buku Tulis',100,'sedang','Alat tulis siswa.','2025-11-01','diajukan',2000000.00,2935486.00,1082310.00),(36,36,'Komputer Multimedia',15,'tinggi','Desain grafis dan editing.','2025-11-01','diverifikasi',9000000.00,4555989.00,1947673.00),(37,37,'Stop Kontak',20,'rendah','Instalasi listrik lab.','2025-11-01','terpenuhi',1000000.00,2144423.00,8925.00),(38,38,'Peralatan Prakarya',25,'sedang','Kewirausahaan siswa.','2025-11-01','diajukan',3500000.00,6740142.00,2909042.00),(39,39,'Papan Informasi Digital',1,'tinggi','Pengumuman sekolah.','2025-11-01','diverifikasi',5000000.00,5762889.00,2091554.00),(40,40,'Drone Edukasi',2,'tinggi','Media pembelajaran STEM.','2025-11-01','diverifikasi',7000000.00,5833622.00,66318.00),(42,40,'Alat Pengeboran',1,'tinggi','Alat untuk praktik jurusan geologi pertambangan','2025-11-03','diverifikasi',NULL,0.00,0.00),(43,40,'Fiber Optic Fushion Splicer',1,'tinggi','Membutuhkan sebuah alat untuk praktik penyambungan kabel fiber optik','2025-11-03','diverifikasi',60000000.00,25000000.00,150000.00),(45,43,'Cairan NaCL',2,'rendah','butuh cairan garam untuk praktikum','2025-11-03','terpenuhi',500000.00,200000.00,9000000.00),(46,43,'ESP Board',2,'sedang','Membutuhkan espboard untuk','2025-11-03','diverifikasi',50000000.00,35000000.00,0.00);
/*!40000 ALTER TABLE `kebutuhan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pengguna`
--

DROP TABLE IF EXISTS `pengguna`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pengguna` (
  `id_pengguna` int NOT NULL AUTO_INCREMENT,
  `nama` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `no_hp` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `alamat` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `jenis_user` varchar(31) COLLATE utf8mb4_general_ci NOT NULL,
  `id_sekolah` int DEFAULT NULL,
  PRIMARY KEY (`id_pengguna`),
  KEY `FK4id57wa3hyp0oltfov2gg0yxo` (`id_sekolah`),
  CONSTRAINT `FK4id57wa3hyp0oltfov2gg0yxo` FOREIGN KEY (`id_sekolah`) REFERENCES `sekolah` (`id_sekolah`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pengguna`
--

LOCK TABLES `pengguna` WRITE;
/*!40000 ALTER TABLE `pengguna` DISABLE KEYS */;
INSERT INTO `pengguna` VALUES (1,'Moreno Ferdinand Farhantino','moreno.farhantino@gmail.com','password123','081212345678','Jl. MT Haryono No.12, Samarinda','Admin',NULL),(2,'Ahmad Dani','ahmad.dani@gmail.com','ahmadpass','081234556677','Jl. Pattimura No.11, Denpasar','Admin',NULL),(3,'Ahmad Sepriza','ahmad.sepriza@gmail.com','sepriza321','081367899012','Jl. Pangeran Antasari No.14, Banjarmasin','Admin',NULL),(4,'Aris Candra Muzaffar','aris.muzaffar@gmail.com','aris123','081223498765','Jl. Imam Bonjol No.8, Samarinda','Admin',NULL),(5,'Rini Wulandari','rini.wulandari@gmail.com','rini123','082234567890','Jl. Ahmad Yani No.8, Balikpapan','Donatur',NULL),(6,'Muhammad Restu Al Hidayat','restu.hidayat@gmail.com','restu321','081345678921','Jl. Merdeka No.9, Surabaya','Sekolah',NULL),(7,'Daffa Syahrandy Husain','daffa.syahrandy@gmail.com','dafpass','085123456789','Jl. S. Parman No.20, Makassar','Donatur',NULL),(8,'Grace Vies Angel','grace.angel@gmail.com','angelgrace','081987654321','Jl. Gatot Subroto No.17, Jakarta','Donatur',NULL),(9,'Nadila Putri','nadila.putri@gmail.com','nadila2024','082178945612','Jl. Diponegoro No.11, Yogyakarta','Sekolah',NULL),(10,'Rizky Wahyu Dina Putri','rizky.putri@gmail.com','rizky123','085678901234','Jl. KH Agus Salim No.23, Malang','Donatur',NULL),(11,'Muhammad Fachri','fachri.muhammad@gmail.com','fachri2025','081234567890','Jl. Letjen Sutoyo No.33, Bandung','Sekolah',NULL),(12,'Indah Maramin Al Inayah','indah.alinayah@gmail.com','indah999','083812345678','Jl. Panjaitan No.7, Banjarmasin','Sekolah',NULL),(13,'Deny Candra Kasuma','deny.kasuma@gmail.com','deny123','085234567890','Jl. Sam Ratulangi No.9, Manado','Donatur',NULL),(14,'Nurhidayah','nurhidayah89@gmail.com','nurhidayah','082176543210','Jl. Jendral Sudirman No.14, Palembang','Donatur',NULL),(15,'Ahmad Samsul Arifin','samsul.arifin@gmail.com','ahmad123','081345678912','Jl. Mangga Besar No.8, Jakarta','Sekolah',NULL),(16,'Elmy Fadillah','elmy.fadillah@gmail.com','elmy321','085123098765','Jl. Hasan Basri No.5, Pontianak','Donatur',NULL),(17,'Muhammad Arifin Alqi. Ab','arifin.alqi@gmail.com','arifinpass','082112223334','Jl. Kalimantan No.4, Balikpapan','Sekolah',NULL),(18,'Nayla Lelyanggraheni Hutomo','nayla.hutomo@gmail.com','nayla456','081889900112','Jl. M. Yamin No.6, Surakarta','Sekolah',NULL),(19,'Maria Claudia Meo','maria.meo@gmail.com','maria321','081245678923','Jl. Kawi No.10, Malang','Sekolah',NULL),(20,'Rabiatul Hikmah','rabiatul.hikmah@gmail.com','hikmah22','081334556677','Jl. Mawar No.2, Samarinda','Donatur',NULL),(21,'Zahra Aurellya Herdiansyah','zahra.herdiansyah@gmail.com','zahra123','081278945612','Jl. Bhayangkara No.25, Tangerang','Sekolah',NULL),(22,'Muhammad Reffi Fadillah','reffi.fadillah@gmail.com','reffi2025','082167849321','Jl. Tendean No.6, Semarang','Donatur',NULL),(23,'Tsabitah Kawiswara','tsabitah.kawiswara@gmail.com','tsabitah123','085678900112','Jl. Ahmad Dahlan No.15, Bandung','Sekolah',NULL),(24,'Indah Putri Lestari','indah.putri@gmail.com','indahputri','081223344556','Jl. HOS Cokroaminoto No.2, Jakarta','Donatur',NULL),(25,'Ghani Mandala Putra','ghani.putra@gmail.com','ghani2025','082112233445','Jl. Slamet Riyadi No.9, Surakarta','Sekolah',NULL),(26,'Nabila Imtiyaz Agustin','nabila.agustin@gmail.com','nabila123','085233344455','Jl. Anggrek No.8, Samarinda','Sekolah',NULL),(27,'Fikri Abiyyu Rahman','fikri.rahman@gmail.com','fikri123','081122334455','Jl. Martadinata No.4, Makassar','Donatur',NULL),(28,'Muhammad Nur Alfian','nur.alfian@gmail.com','alfianpass','082345678912','Jl. Juanda No.10, Palangkaraya','Sekolah',NULL),(29,'Zyrus Alfredo Randan Malinggato','zyrus.malinggato@gmail.com','zyrus999','083812309876','Jl. Trunojoyo No.3, Samarinda','Donatur',NULL),(30,'Syawe Manisha P. Siregar','syawe.siregar@gmail.com','syawe22','082178945611','Jl. Jati No.7, Medan','Sekolah',NULL),(31,'Aliyah Azzah Sekedang','aliyah.sekedang@gmail.com','aliyahpass','085277889900','Jl. Mulawarman No.18, Samarinda','Donatur',NULL),(32,'Muhammad Sadikin Samir','sadikin.samir@gmail.com','sadikin123','081234987654','Jl. Pahlawan No.2, Balikpapan','Sekolah',NULL),(33,'Rizqy','rizqy.rq@gmail.com','rizqy2025','081223345566','Jl. Melati No.4, Palu','Donatur',NULL),(34,'Vebronia Vitania Lusi','vebronia.lusi@gmail.com','vebronia23','081234567111','Jl. Flores No.12, Kupang','Sekolah',NULL),(35,'Ahmad Qomarul Arifin','qomarul.arifin@gmail.com','qomarul99','082166778899','Jl. Basuki Rahmat No.15, Surabaya','Donatur',NULL),(36,'Jen Agresia Misti','jen.misti@gmail.com','jenpass','083812312345','Jl. Kartini No.8, Pontianak','Sekolah',NULL),(37,'Raihan Fariz Novanto','raihan.novanto@gmail.com','raihan456','081277889900','Jl. Pandanaran No.9, Semarang','Sekolah',NULL),(38,'Adella Putri','adella.putri@gmail.com','adella123','082198765432','Jl. Ahmad Yani No.5, Balikpapan','Donatur',NULL),(39,'Zahra Aulia Rahmah','zahra.rahmah@gmail.com','zahra999','081389900112','Jl. Veteran No.1, Yogyakarta','Sekolah',NULL),(40,'Nur Ihsan','nur.ihsan@gmail.com','ihsan123','082176543219','Jl. Pattimura No.10, Samarinda','Donatur',NULL),(42,'Ahmad Dani','ahmaddani@gmail.com','Dhani Ahmad','085156448913','Jalan Padjajaran','Donatur',NULL),(43,'Dwi','dwi@gmail.com','dwisuka','08123456789','Jln Padjajaran','Donatur',NULL),(44,'Ahmad Sepriza','rijaganteng@gmail.com','rija123','08123456789','Jalan Jakarta','Donatur',NULL),(45,'Dinathan Fahrezi','dinatam@gmail.com','dinatan123','08515645678','Jalan Mugirejo','Donatur',NULL),(46,'Adella','adella@gmail.com','adell123','08123456789','Jln Padjajaran','Donatur',NULL),(49,'Ahmad Dhani','dhaniahmad@gmail.com','ahmd123','081234565789','Jalan Padjajaran','Donatur',NULL),(51,'SMKN 1 SENDAWAR','smkn1sdr','smkbisa','08123456789','Jalan Lalang Besar','Sekolah',NULL),(52,'SMP Negeri 6 Bontang','smpn6bontang@sekolah.com','sekolah123','08135200001','Jl. Sutomo No.4','Sekolah',22),(53,'SMA Negeri 1 Samarinda','sman1samarinda@sekolah.com','sekolah123','08135300002','Jl. Bhayangkara No.1','Sekolah',23),(54,'SMK Negeri 2 Balikpapan','smkn2balikpapan@sekolah.com','sekolah123','08135400003','Jl. Pandan Wangi No.18','Sekolah',24),(55,'SD Negeri 7 Tenggarong','sdn7tenggarong@sekolah.com','sekolah123','08135500004','Jl. Anggrek No.10','Sekolah',25),(56,'SMP Negeri 3 Samarinda','smpn3samarinda@sekolah.com','sekolah123','08135600005','Jl. Mulawarman No.13','Sekolah',26),(57,'SMA Katolik Santo Yosef Balikpapan','smayosefbalikpapan@sekolah.com','sekolah123','08135700006','Jl. Merdeka No.9','Sekolah',27),(58,'SMK PGRI 1 Samarinda','smkpgri1samarinda@sekolah.com','sekolah123','08135800007','Jl. P. Suryanata No.3','Sekolah',28),(59,'SD Negeri 4 Bontang','sdn4bontang@sekolah.com','sekolah123','08135900008','Jl. Mawar No.6','Sekolah',29),(60,'SMP Negeri 1 Balikpapan','smpn1balikpapan@sekolah.com','sekolah123','08136000009','Jl. Ahmad Dahlan No.20','Sekolah',30),(61,'SMA Negeri 5 Samarinda','sman5samarinda@sekolah.com','sekolah123','08136100010','Jl. Lambung Mangkurat No.2','Sekolah',31),(62,'SMK Negeri 4 Balikpapan','smkn4balikpapan@sekolah.com','sekolah123','08136200011','Jl. Soekarno Hatta No.24','Sekolah',32),(63,'SD Negeri 6 Tenggarong','sdn6tenggarong@sekolah.com','sekolah123','08136300012','Jl. Flores No.5','Sekolah',33),(64,'SMP Negeri 5 Samarinda','smpn5samarinda@sekolah.com','sekolah123','08136400013','Jl. Wijaya Kusuma No.8','Sekolah',34),(65,'SMA Negeri 4 Balikpapan','sman4balikpapan@sekolah.com','sekolah123','08136500014','Jl. Pelita No.3','Sekolah',35),(66,'SMK Negeri 5 Samarinda','smkn5samarinda@sekolah.com','sekolah123','08136600015','Jl. Kesuma Bangsa No.11','Sekolah',36),(67,'SD Kristen Eben Haezer Balikpapan','sdebenhaezer@sekolah.com','sekolah123','08136700016','Jl. Imam Bonjol No.2','Sekolah',37),(68,'SMP Negeri 8 Samarinda','smpn8samarinda@sekolah.com','sekolah123','08136800017','Jl. Basuki Rahmat No.16','Sekolah',38),(69,'SMA Negeri 7 Samarinda','sman7samarinda@sekolah.com','sekolah123','08136900018','Jl. Gunung Merbabu No.7','Sekolah',39),(70,'SMK Muhammadiyah 2 Balikpapan','smkmuh2balikpapan@sekolah.com','sekolah123','08137000019','Jl. Cempaka No.17','Sekolah',40),(71,'SMAN 1 SENDAWAR','smansasendawar@sma.sch.id','smansa','081234567890','Jalan Sendawar Raya','Sekolah',NULL);
/*!40000 ALTER TABLE `pengguna` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pihak_sekolah`
--

DROP TABLE IF EXISTS `pihak_sekolah`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pihak_sekolah` (
  `id_pengguna` int NOT NULL,
  `id_sekolah` int NOT NULL,
  `jabatan` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id_pengguna`,`id_sekolah`),
  KEY `id_sekolah` (`id_sekolah`),
  CONSTRAINT `fk_pihak_pengguna` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_pihak_sekolah` FOREIGN KEY (`id_sekolah`) REFERENCES `sekolah` (`id_sekolah`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pihak_sekolah`
--

LOCK TABLES `pihak_sekolah` WRITE;
/*!40000 ALTER TABLE `pihak_sekolah` DISABLE KEYS */;
INSERT INTO `pihak_sekolah` VALUES (6,1,'Kepala Sekolah'),(6,21,'Kepala Sekolah'),(9,2,'Wakil Kepala Sekolah'),(11,3,'Guru Matematika'),(12,4,'Guru Bahasa Indonesia'),(15,5,'Guru IPA'),(17,6,'Guru IPS'),(18,7,'Guru Agama'),(19,8,'Guru Olahraga'),(21,9,'Guru Kelas'),(23,10,'Bendahara Sekolah'),(25,11,'Staf Administrasi'),(26,12,'Operator Sekolah'),(28,13,'Guru Komputer'),(30,14,'Wali Kelas'),(32,15,'Guru PKN'),(34,16,'Guru Seni Budaya'),(36,17,'Guru Prakarya'),(37,18,'Staf TU'),(39,19,'Guru Bahasa Inggris'),(40,20,'Guru BK'),(52,22,'Waka Kurikulum'),(53,23,'Guru Kelas'),(54,24,'Guru Agama'),(55,25,'Guru IPS'),(56,26,'Guru IPA'),(57,27,'Guru Matematika'),(58,28,'Guru Komputer'),(59,29,'Guru Seni'),(60,30,'Guru Olahraga'),(61,31,'Guru Bahasa Indonesia'),(62,32,'Guru PKN'),(63,33,'Operator Sekolah'),(64,34,'Wali Kelas'),(65,35,'Staf TU'),(66,36,'Guru Prakarya'),(67,37,'Guru BK'),(68,38,'Guru Kelas'),(69,39,'Kepala Sekolah'),(70,40,'Guru Komputer'),(71,43,'Wakil Kepala Sekolah');
/*!40000 ALTER TABLE `pihak_sekolah` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sekolah`
--

DROP TABLE IF EXISTS `sekolah`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sekolah` (
  `id_sekolah` int NOT NULL AUTO_INCREMENT,
  `id_pengguna` int DEFAULT NULL,
  `nama_sekolah` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `NPSN` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `alamat_sekolah` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `kecamatan` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `kabupaten` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `kategori` enum('SD','SMP','SMA','SMK','SLB') COLLATE utf8mb4_general_ci NOT NULL,
  `kebutuhan` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `status_verifikasi` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id_sekolah`),
  KEY `id_pengguna` (`id_pengguna`),
  CONSTRAINT `FKr1fi1ovswt73x9so6wuhgptaq` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sekolah`
--

LOCK TABLES `sekolah` WRITE;
/*!40000 ALTER TABLE `sekolah` DISABLE KEYS */;
INSERT INTO `sekolah` VALUES (1,6,'SD Negeri 1 Samarinda','20567001','Jl. Mawar No.10','Samarinda Ulu','Samarinda','SD','Perbaikan ruang kelas','Terverifikasi'),(2,9,'SMP Negeri 2 Balikpapan','20567002','Jl. Seroja No.12','Balikpapan Utara','Balikpapan','SMP','Pengadaan komputer lab','Terverifikasi'),(3,11,'SMA Negeri 3 Tenggarong','20567003','Jl. A. Yani No.5','Tenggarong','Kutai Kartanegara','SMA','Renovasi laboratorium','Terverifikasi'),(4,12,'SMK Negeri 1 Bontang','20567004','Jl. Mulawarman No.15','Bontang Selatan','Bontang','SMK','Alat praktik teknik','Terverifikasi'),(5,15,'SMA Katolik Santo Yosef','20567005','Jl. Merdeka No.11','Balikpapan Kota','Balikpapan','SMA','Fasilitas olahraga','Terverifikasi'),(6,17,'SD Islam Terpadu Al-Falah','20567006','Jl. Sentosa No.8','Samarinda Ilir','Samarinda','SD','Meja dan kursi baru','Terverifikasi'),(7,18,'SMP Negeri 4 Balikpapan','20567007','Jl. Sudirman No.5','Balikpapan Selatan','Balikpapan','SMP','Perbaikan toilet','Terverifikasi'),(8,19,'SMA Negeri 5 Samarinda','20567008','Jl. Lambung Mangkurat No.2','Samarinda Kota','Samarinda','SMA','Renovasi ruang guru','Terverifikasi'),(9,21,'SMK Negeri 2 Balikpapan','20567009','Jl. Pandan Wangi No.9','Balikpapan Timur','Balikpapan','SMK','Mesin otomotif','Terverifikasi'),(10,23,'SMA Negeri 6 Tenggarong','20567010','Jl. Kartini No.18','Tenggarong','Kutai Kartanegara','SMA','Buku pelajaran tambahan','Terverifikasi'),(11,25,'SD Negeri 4 Bontang','20567011','Jl. Mawar No.6','Bontang Barat','Bontang','SD','Buku bacaan tambahan','Terverifikasi'),(12,26,'SMP Negeri 5 Samarinda','20567012','Jl. Wijaya Kusuma No.8','Samarinda Kota','Samarinda','SMP','Laboratorium IPA','Terverifikasi'),(13,28,'SMK Negeri 3 Samarinda','20567013','Jl. Juanda No.11','Samarinda Utara','Samarinda','SMK','Komputer akuntansi','Terverifikasi'),(14,30,'SMP Islam Terpadu Al-Ma?arif','20567014','Jl. Mangga No.9','Balikpapan Selatan','Balikpapan','SMP','Peralatan multimedia','Terverifikasi'),(15,32,'SMK Muhammadiyah 1 Balikpapan','20567015','Jl. Cendana No.21','Balikpapan Timur','Balikpapan','SMK','Desain grafis digital','Terverifikasi'),(16,34,'SMP Negeri 9 Samarinda','20567016','Jl. Melati No.10','Samarinda Kota','Samarinda','SMP','Buku penunjang pelajaran','Terverifikasi'),(17,36,'SMK Negeri 7 Samarinda','20567017','Jl. Diponegoro No.17','Samarinda Ilir','Samarinda','SMK','Peralatan multimedia','Terverifikasi'),(18,37,'SD Negeri 9 Tenggarong','20567018','Jl. Syahranie No.11','Tenggarong','Kutai Kartanegara','SD','Buku bacaan anak','Terverifikasi'),(19,39,'SMA Negeri 9 Samarinda','20567019','Jl. Veteran No.20','Samarinda Kota','Samarinda','SMA','Renovasi ruang OSIS','Terverifikasi'),(20,40,'SMK Negeri 8 Balikpapan','20567020','Jl. Pelita Raya No.15','Balikpapan Utara','Balikpapan','SMK','Peralatan jaringan komputer','Terverifikasi'),(21,6,'SD Negeri 5 Balikpapan','20567021','Jl. Pahlawan No.22','Balikpapan Barat','Balikpapan','SD','Perbaikan atap bocor','Terverifikasi'),(22,9,'SMP Negeri 6 Bontang','20567022','Jl. Sutomo No.4','Bontang Selatan','Bontang','SMP','Kursi laboratorium','Terverifikasi'),(23,11,'SMA Negeri 1 Samarinda','20567023','Jl. Bhayangkara No.1','Samarinda Kota','Samarinda','SMA','LCD projector baru','Terverifikasi'),(24,12,'SMK Negeri 2 Balikpapan','20567024','Jl. Pandan Wangi No.18','Balikpapan Timur','Balikpapan','SMK','Peralatan mesin otomotif','Terverifikasi'),(25,15,'SD Negeri 7 Tenggarong','20567025','Jl. Anggrek No.10','Tenggarong','Kutai Kartanegara','SD','Perbaikan plafon','Terverifikasi'),(26,17,'SMP Negeri 3 Samarinda','20567026','Jl. Mulawarman No.13','Samarinda Seberang','Samarinda','SMP','Papan tulis digital','Terverifikasi'),(27,18,'SMA Katolik Santo Yosef Balikpapan','20567027','Jl. Merdeka No.9','Balikpapan Tengah','Balikpapan','SMA','Perbaikan fasilitas olahraga','Terverifikasi'),(28,19,'SMK PGRI 1 Samarinda','20567028','Jl. P. Suryanata No.3','Samarinda Ulu','Samarinda','SMK','Alat peraga teknik listrik','Terverifikasi'),(29,21,'SD Negeri 4 Bontang','20567029','Jl. Mawar No.6','Bontang Barat','Bontang','SD','Buku bacaan tambahan','Terverifikasi'),(30,23,'SMP Negeri 1 Balikpapan','20567030','Jl. Ahmad Dahlan No.20','Balikpapan Selatan','Balikpapan','SMP','Perbaikan pagar sekolah','Terverifikasi'),(31,25,'SMA Negeri 5 Samarinda','20567031','Jl. Lambung Mangkurat No.2','Samarinda Ilir','Samarinda','SMA','Renovasi ruang guru','Terverifikasi'),(32,26,'SMK Negeri 4 Balikpapan','20567032','Jl. Soekarno Hatta No.24','Balikpapan Utara','Balikpapan','SMK','Pengadaan proyektor','Terverifikasi'),(33,28,'SD Negeri 6 Tenggarong','20567033','Jl. Flores No.5','Tenggarong','Kutai Kartanegara','SD','Renovasi kamar mandi','Terverifikasi'),(34,30,'SMP Negeri 5 Samarinda','20567034','Jl. Wijaya Kusuma No.8','Samarinda Kota','Samarinda','SMP','Peralatan laboratorium IPA','Terverifikasi'),(35,32,'SMA Negeri 4 Balikpapan','20567035','Jl. Pelita No.3','Balikpapan Timur','Balikpapan','SMA','Komputer administrasi','Terverifikasi'),(36,34,'SMK Negeri 5 Samarinda','20567036','Jl. Kesuma Bangsa No.11','Samarinda Ulu','Samarinda','SMK','Mesin jahit industri','Terverifikasi'),(37,36,'SD Kristen Eben Haezer Balikpapan','20567037','Jl. Imam Bonjol No.2','Balikpapan Kota','Balikpapan','SD','Renovasi ruang kelas','Terverifikasi'),(38,37,'SMP Negeri 8 Samarinda','20567038','Jl. Basuki Rahmat No.16','Samarinda Ilir','Samarinda','SMP','Alat musik ekstrakurikuler','Terverifikasi'),(39,39,'SMA Negeri 7 Samarinda','20567039','Jl. Gunung Merbabu No.7','Samarinda Utara','Samarinda','SMA','Peralatan laboratorium kimia','Terverifikasi'),(40,40,'SMK Muhammadiyah 2 Balikpapan','20567040','Jl. Cempaka No.17','Balikpapan Tengah','Balikpapan','SMK','Peralatan jaringan komputer','Terverifikasi'),(42,NULL,'SMKN 1 SENDAWAR','234323','Jalan Lalang Besar','Melak','Kutai Barat','SMK','-','Terverifikasi'),(43,NULL,'SMAN 1 SENDAWAR','21345624','Jalan Sendawar Raya','Melak','Kutai Barat','SMA','-','Terverifikasi');
/*!40000 ALTER TABLE `sekolah` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-03 13:14:45
