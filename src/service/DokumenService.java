package service;

import dao.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import model.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * DokumenService
 * Service untuk handle business logic dokumen
 * 
 * @author EduShare Team
 */
public class DokumenService {
    
    private final DokumenDAO dokumenDAO;
    private final SekolahDAO sekolahDAO;
    
    public DokumenService() {
        this.dokumenDAO = new DokumenDAOImpl();
        this.sekolahDAO = new SekolahDAOImpl();
    }
    
    /**
     * BUSINESS LOGIC: Upload dokumen baru (untuk pihak sekolah)
     */
    public OperationResult uploadDokumen(Integer idSekolah, String jenisDokumen, String namaFile,
                                          String pathFile) {
        // Validasi input
        if (jenisDokumen == null || jenisDokumen.trim().isEmpty()) {
            return new OperationResult(false, "Jenis dokumen harus dipilih");
        }
        if (namaFile == null || namaFile.trim().isEmpty()) {
            return new OperationResult(false, "Nama file tidak boleh kosong");
        }
        if (pathFile == null || pathFile.trim().isEmpty()) {
            return new OperationResult(false, "Path file tidak valid");
        }
        
        // Validasi ekstensi file
        if (!isValidFileExtension(namaFile)) {
            return new OperationResult(false, "Format file tidak didukung. Gunakan PDF, JPG, PNG, atau DOCX");
        }
        
        try {
            // Validasi sekolah exists
            Optional<Sekolah> sekolahOpt = sekolahDAO.findById(idSekolah);
            if (sekolahOpt.isEmpty()) {
                return new OperationResult(false, "Sekolah tidak ditemukan");
            }
            
            // Buat dokumen baru
            Dokumen dokumen = new Dokumen();
            dokumen.setSekolah(sekolahOpt.get());
            dokumen.setJenisDokumen(jenisDokumen);
            dokumen.setNamaFile(namaFile);
            dokumen.setPathFile(pathFile);
            dokumen.setTanggalUpload(LocalDateTime.now());
            dokumen.setStatusVerifikasi(Dokumen.StatusVerifikasi.menunggu);
            
            dokumenDAO.save(dokumen);
            
            return new OperationResult(true, "Dokumen berhasil diupload");
            
        } catch (Exception e) {
            return new OperationResult(false, "Error: " + e.getMessage());
        }
    }
    
    /**
     * Validasi ekstensi file
     */
    private boolean isValidFileExtension(String filename) {
        String[] allowedExtensions = {".pdf", ".jpg", ".jpeg", ".png", ".docx"};
        String lowerFilename = filename.toLowerCase();
        
        for (String ext : allowedExtensions) {
            if (lowerFilename.endsWith(ext)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * BUSINESS LOGIC: Verifikasi dokumen (untuk admin)
     */
    public OperationResult verifikasiDokumen(Integer idDokumen, String catatan) {
        try {
            boolean success = dokumenDAO.verifikasiDokumen(idDokumen, catatan);
            
            if (success) {
                return new OperationResult(true, "Dokumen berhasil diverifikasi");
            } else {
                return new OperationResult(false, "Gagal verifikasi dokumen");
            }
            
        } catch (Exception e) {
            return new OperationResult(false, "Error: " + e.getMessage());
        }
    }
    
    /**
     * BUSINESS LOGIC: Tolak dokumen (untuk admin)
     */
    public OperationResult tolakDokumen(Integer idDokumen, String alasan) {
        if (alasan == null || alasan.trim().isEmpty()) {
            return new OperationResult(false, "Alasan penolakan harus diisi");
        }
        
        try {
            boolean success = dokumenDAO.tolakDokumen(idDokumen, alasan);
            
            if (success) {
                return new OperationResult(true, "Dokumen berhasil ditolak");
            } else {
                return new OperationResult(false, "Gagal tolak dokumen");
            }
            
        } catch (Exception e) {
            return new OperationResult(false, "Error: " + e.getMessage());
        }
    }
    
    /**
     * Get dokumen by sekolah
     */
    public List<Dokumen> getDokumenBySekolah(Integer idSekolah) {
        return dokumenDAO.findByIdSekolah(idSekolah);
    }
    
    /**
     * Get dokumen menunggu verifikasi (untuk admin)
     */
    public List<Dokumen> getDokumenMenungguVerifikasi() {
        return dokumenDAO.findDokumenMenungguVerifikasi();
    }
    
    /**
     * Get dokumen terverifikasi
     */
    public List<Dokumen> getDokumenTerverifikasi() {
        return dokumenDAO.findDokumenTerverifikasi();
    }
    
    /**
     * Get dokumen ditolak
     */
    public List<Dokumen> getDokumenDitolak() {
        return dokumenDAO.findDokumenDitolak();
    }
    
    /**
     * Filter dokumen by jenis
     */
    public List<Dokumen> getDokumenByJenis(String jenis) {
        return dokumenDAO.findByJenisDokumen(jenis);
    }
    
    /**
     * Get dokumen by ID
     */
    public Optional<Dokumen> getDokumenById(Integer id) {
        return dokumenDAO.findById(id);
    }
    
    /**
     * Delete dokumen
     */
    public OperationResult deleteDokumen(Integer idDokumen) {
        try {
            Optional<Dokumen> dokumenOpt = dokumenDAO.findById(idDokumen);
            if (dokumenOpt.isEmpty()) {
                return new OperationResult(false, "Dokumen tidak ditemukan");
            }
            
            Dokumen dokumen = dokumenOpt.get();
            
            // Business rule: hanya bisa hapus dokumen yang belum diverifikasi
            if (dokumen.getStatusVerifikasi() == Dokumen.StatusVerifikasi.diterima) {
                return new OperationResult(false, "Tidak bisa menghapus dokumen yang sudah diverifikasi");
            }
            
            // TODO: Hapus file fisik dari storage
            // deletePhysicalFile(dokumen.getPathFile());
            
            dokumenDAO.deleteById(idDokumen);
            
            return new OperationResult(true, "Dokumen berhasil dihapus");
            
        } catch (Exception e) {
            return new OperationResult(false, "Error: " + e.getMessage());
        }
    }
    
    /**
     * Update dokumen (re-upload)
     */
    public OperationResult updateDokumen(Integer idDokumen, String namaFile, String pathFile) {
        if (namaFile == null || namaFile.trim().isEmpty()) {
            return new OperationResult(false, "Nama file tidak boleh kosong");
        }
        
        if (!isValidFileExtension(namaFile)) {
            return new OperationResult(false, "Format file tidak didukung");
        }
        
        try {
            Optional<Dokumen> dokumenOpt = dokumenDAO.findById(idDokumen);
            if (dokumenOpt.isEmpty()) {
                return new OperationResult(false, "Dokumen tidak ditemukan");
            }
            
            Dokumen dokumen = dokumenOpt.get();
            
            // Business rule: hanya bisa update dokumen yang belum diverifikasi atau ditolak
            if (dokumen.getStatusVerifikasi() == Dokumen.StatusVerifikasi.diterima) {
                return new OperationResult(false, "Tidak bisa update dokumen yang sudah diverifikasi");
            }
            
            dokumen.setNamaFile(namaFile);
            dokumen.setPathFile(pathFile);
            dokumen.setTanggalUpload(LocalDateTime.now());
            dokumen.setStatusVerifikasi(Dokumen.StatusVerifikasi.menunggu);
            
            dokumenDAO.update(dokumen);
            
            return new OperationResult(true, "Dokumen berhasil diupdate");
            
        } catch (Exception e) {
            return new OperationResult(false, "Error: " + e.getMessage());
        }
    }
    
    /**
     * Get jumlah dokumen menunggu verifikasi per sekolah
     */
    public long countDokumenMenunggu(Integer idSekolah) {
        return dokumenDAO.countMenungguVerifikasiBySekolah(idSekolah);
    }
    
    /**
    * Cari dokumen berdasarkan id sekolah dan jenis/kategori dokumen
    * Contoh penggunaan: findDokumenBySekolahAndKategori(1, "Proposal Donasi")
    */
    public Optional<Dokumen> findDokumenBySekolahAndKategori(Integer idSekolah, String jenisDokumen) {
        EntityManager em = Persistence.createEntityManagerFactory("EduSharePU").createEntityManager();
        DokumenDAOImpl dokumenDAO = new DokumenDAOImpl(em);
        try {
            TypedQuery<Dokumen> query = em.createQuery(
                "SELECT d FROM Dokumen d WHERE d.sekolah.idSekolah = :idSekolah AND LOWER(d.jenisDokumen) = LOWER(:jenisDokumen) ORDER BY d.tanggalUpload DESC",
                Dokumen.class
            );
            query.setParameter("idSekolah", idSekolah);
            query.setParameter("jenisDokumen", jenisDokumen);
            query.setMaxResults(1); // ambil dokumen terbaru
            List<Dokumen> result = query.getResultList();
            if (!result.isEmpty()) {
                return Optional.of(result.get(0));
            }
            return Optional.empty();
        } catch (Exception e) {
            return Optional.empty();
        } finally {
            em.close();
        }
    }
    
    /**
     * Inner class untuk Operation Result
     */
    public static class OperationResult {
        private final boolean success;
        private final String message;
        
        public OperationResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }
        
        public boolean isSuccess() {
            return success;
        }
        
        public String getMessage() {
            return message;
        }
    }
    
    /**
     * Clean up resources
     */
    public void close() {
        if (dokumenDAO instanceof DokumenDAOImpl) {
            ((DokumenDAOImpl) dokumenDAO).close();
        }
        if (sekolahDAO instanceof SekolahDAOImpl) {
            ((SekolahDAOImpl) sekolahDAO).close();
        }
    }
}
