package service;

import dao.*;
import model.*;
import java.util.List;
import java.util.Optional;

/**
 * SekolahService
 * Service untuk handle business logic sekolah
 * Termasuk filtering, dll
 * 
 * @author EduShare Team
 */
public class SekolahService {

    private final SekolahDAO sekolahDAO;
    private final PihakSekolahDAO pihakSekolahDAO;

    public SekolahService() {
        this.sekolahDAO = new SekolahDAOImpl();
        this.pihakSekolahDAO = new PihakSekolahDAOImpl();
    }

    /**
     * Tambah sekolah baru + akun pihak sekolah sekaligus
     */
    public OperationResult tambahSekolahDenganAkun(String namaSekolah, String kategori, String alamat,
                                                   String kecamatan, String kabupaten, String npsn,
                                                   String kebutuhan, String email, String password,
                                                   String noHp, String jabatan) {
        try {
            // ===============================
            // 1️⃣ Buat entitas sekolah baru
            // ===============================
            Sekolah sekolahBaru = new Sekolah();
            sekolahBaru.setNamaSekolah(namaSekolah);
            sekolahBaru.setKategori(Sekolah.KategoriSekolah.valueOf(kategori));
            sekolahBaru.setAlamatSekolah(alamat);
            sekolahBaru.setKecamatan(kecamatan);
            sekolahBaru.setKabupaten(kabupaten);
            sekolahBaru.setNpsn(npsn);
            sekolahBaru.setKebutuhan(kebutuhan);
            sekolahBaru.setStatusVerifikasi("Menunggu");

            // Simpan sekolah dulu agar punya ID
            sekolahDAO.save(sekolahBaru);

            // ===============================
            // 2️⃣ Buat akun pihak sekolah
            // ===============================
            PihakSekolah pihakSekolahBaru = new PihakSekolah();
            pihakSekolahBaru.setNama(namaSekolah);
            pihakSekolahBaru.setEmail(email);
            pihakSekolahBaru.setPassword(password);
            pihakSekolahBaru.setNoHp(noHp);
            pihakSekolahBaru.setAlamat(alamat);
            pihakSekolahBaru.setJabatan(jabatan);
            pihakSekolahBaru.setSekolah(sekolahBaru); // relasi utama

            // Simpan pihak sekolah (setelah sekolah ada)
            pihakSekolahDAO.save(pihakSekolahBaru);

            // ===============================
            // 3️⃣ Tambahkan ke list sekolah (opsional)
            // ===============================
            sekolahBaru.getDaftarPihakSekolah().add(pihakSekolahBaru);
            sekolahDAO.update(sekolahBaru);

            return new OperationResult(true, "Sekolah dan akun pihak sekolah berhasil ditambahkan ✅");

        } catch (Exception e) {
            e.printStackTrace();
            return new OperationResult(false, "Gagal menambah sekolah: " + e.getMessage());
        }
    }

    // ================================================================
    // Versi lama untuk tambah sekolah tanpa akun (tetap dipertahankan)
    // ================================================================
    public OperationResult tambahSekolah(String namaSekolah, String kategori, 
                                          String alamatSekolah, String kecamatan, String kabupaten, 
                                          String npsn, String kebutuhan) {
        if (namaSekolah == null || namaSekolah.trim().isEmpty()) {
            return new OperationResult(false, "Nama sekolah tidak boleh kosong");
        }

        try {
            Sekolah sekolah = new Sekolah();
            sekolah.setNamaSekolah(namaSekolah);
            sekolah.setKategori(Sekolah.KategoriSekolah.valueOf(kategori));
            sekolah.setAlamatSekolah(alamatSekolah);
            sekolah.setKecamatan(kecamatan);
            sekolah.setKabupaten(kabupaten);
            sekolah.setNpsn(npsn);
            sekolah.setKebutuhan(kebutuhan);

            sekolahDAO.save(sekolah);
            return new OperationResult(true, "Sekolah berhasil ditambahkan");

        } catch (Exception e) {
            return new OperationResult(false, "Error: " + e.getMessage());
        }
    }

    // ================================================================
    //  🧩 Method lainnya tetap sama
    // ================================================================
    public OperationResult updateSekolah(Integer idSekolah, String namaSekolah, String kategori,
                                         String alamatSekolah, String kecamatan, String kabupaten,
                                         String npsn, String kebutuhan) {
        try {
            Optional<Sekolah> sekolahOpt = sekolahDAO.findById(idSekolah);
            if (sekolahOpt.isEmpty()) {
                return new OperationResult(false, "Sekolah tidak ditemukan");
            }

            Sekolah sekolah = sekolahOpt.get();
            sekolah.setNamaSekolah(namaSekolah);
            sekolah.setKategori(Sekolah.KategoriSekolah.valueOf(kategori));
            sekolah.setAlamatSekolah(alamatSekolah);
            sekolah.setKecamatan(kecamatan);
            sekolah.setKabupaten(kabupaten);
            sekolah.setNpsn(npsn);
            sekolah.setKebutuhan(kebutuhan);

            sekolahDAO.update(sekolah);
            return new OperationResult(true, "Data sekolah berhasil diperbarui");

        } catch (Exception e) {
            return new OperationResult(false, "Error: " + e.getMessage());
        }
    }

    public List<Sekolah> getAllSekolah() { return sekolahDAO.findAll(); }
    public List<Sekolah> getSekolahTerverifikasi() { return sekolahDAO.findSekolahTerverifikasi(); }
    public long countSekolahTerverifikasi() { return sekolahDAO.countTerverifikasi(); }
    public List<Sekolah> getSekolahMenungguVerifikasi() { return sekolahDAO.findSekolahMenungguVerifikasi(); }
    public List<Sekolah> searchSekolah(String nama) { return sekolahDAO.searchByNama(nama); }
    public List<Sekolah> filterSekolah(String kategori, String alamat, String status) {
        return sekolahDAO.searchWithFilters(kategori, alamat, status);
    }
    public Optional<Sekolah> getSekolahById(Integer id) { return sekolahDAO.findById(id); }
    public List<Sekolah> getSekolahByKategori(Sekolah.KategoriSekolah kategori) {
        return sekolahDAO.findAll().stream()
                .filter(s -> s.getKategori() == kategori)
                .toList();
    }
    public List<Sekolah> getSekolahByKabupaten(String kabupaten) {
        return sekolahDAO.findAll().stream()
                .filter(s -> s.getKabupaten().equalsIgnoreCase(kabupaten))
                .toList();
    }

    public OperationResult deleteSekolah(Integer idSekolah) {
        try {
            Optional<Sekolah> sekolahOpt = sekolahDAO.findById(idSekolah);
            if (sekolahOpt.isEmpty()) {
                return new OperationResult(false, "Sekolah tidak ditemukan");
            }

            Sekolah sekolah = sekolahOpt.get();
            if (!sekolah.getDaftarKebutuhan().isEmpty()) {
                return new OperationResult(false, "Tidak bisa menghapus sekolah yang memiliki kebutuhan");
            }
            if (!sekolah.getDaftarDokumen().isEmpty()) {
                return new OperationResult(false, "Tidak bisa menghapus sekolah yang memiliki dokumen");
            }

            sekolahDAO.deleteById(idSekolah);
            return new OperationResult(true, "Sekolah berhasil dihapus");

        } catch (Exception e) {
            return new OperationResult(false, "Error: " + e.getMessage());
        }
    }

    // ================================================================
    // Inner class hasil operasi
    // ================================================================
    public static class OperationResult {
        private final boolean success;
        private final String message;

        public OperationResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() { return success; }
        public String getMessage() { return message; }
    }
    
    public boolean updateStatusVerifikasi(Integer idSekolah, String status) {
        try {
            Optional<Sekolah> sekolahOpt = sekolahDAO.findById(idSekolah);
            if (sekolahOpt.isPresent()) {
                Sekolah s = sekolahOpt.get();
                s.setStatusVerifikasi(status);
                sekolahDAO.update(s);
                return true;
            }
        } catch (Exception e) {
            System.err.println("Error updating status verifikasi sekolah: " + e.getMessage());
        }
        return false;
    }

    public int getTotalSekolahTerverifikasi() {
        try {
            List<Sekolah> daftarSekolah = sekolahDAO.findAll();
            if (daftarSekolah == null || daftarSekolah.isEmpty()) {
                return 0;
            }
            // Hitung hanya yang status verifikasi = diterima / true / verified
            long total = daftarSekolah.stream()
                    .filter(s -> s.getStatusVerifikasi() != null &&
                                 s.getStatusVerifikasi().equalsIgnoreCase("Terverifikasi"))
                    .count();
            return (int) total;
        } catch (Exception e) {
            System.err.println("Error menghitung total sekolah terverifikasi: " + e.getMessage());
            return 0;
        }
    }

    
    public void close() {
        try {
            if (sekolahDAO instanceof SekolahDAOImpl sekolahImpl) {
                sekolahImpl.close();
            }
            if (pihakSekolahDAO instanceof PihakSekolahDAOImpl pihakSekolahImpl) {
                pihakSekolahImpl.close();
            }
            System.out.println("✅ Semua koneksi di SekolahService telah ditutup dengan aman.");
        } catch (Exception e) {
            System.err.println("Gagal menutup koneksi di SekolahService: " + e.getMessage());
        }
    }
}
