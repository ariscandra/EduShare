package dao;

import model.Donasi;
import model.Donatur;
import model.Kebutuhan;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * DonasiDAO Implementation
 * Implementasi dengan extensive analytics dan reporting
 * 
 * @author EduShare Team
 */
public class DonasiDAOImpl extends GenericDAOImpl<Donasi, Integer> implements DonasiDAO {
    
    public DonasiDAOImpl() {
        super(Donasi.class);
    }
    
    public DonasiDAOImpl(EntityManager entityManager) {
        super(Donasi.class, entityManager);
    }
    
    @Override
    public List<Donasi> findByDonatur(Donatur donatur) {
        return findByIdDonatur(donatur.getIdPengguna());
    }
    
    @Override
    public List<Donasi> findByIdDonatur(Integer idDonatur) {
        try {
            TypedQuery<Donasi> query = entityManager.createQuery(
                "SELECT d FROM Donasi d WHERE d.donatur.idPengguna = :idDonatur ORDER BY d.tanggalDonasi DESC", 
                Donasi.class
            );
            query.setParameter("idDonatur", idDonatur);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error saat mencari donasi berdasarkan donatur: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<Donasi> findByKebutuhan(Kebutuhan kebutuhan) {
        return findByIdKebutuhan(kebutuhan.getIdKebutuhan());
    }
    
    @Override
    public List<Donasi> findByIdKebutuhan(Integer idKebutuhan) {
        try {
            TypedQuery<Donasi> query = entityManager.createQuery(
                "SELECT d FROM Donasi d WHERE d.kebutuhan.idKebutuhan = :idKebutuhan ORDER BY d.tanggalDonasi DESC", 
                Donasi.class
            );
            query.setParameter("idKebutuhan", idKebutuhan);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error saat mencari donasi berdasarkan kebutuhan: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<Donasi> findByStatus(String status) {
        try {
            TypedQuery<Donasi> query = entityManager.createQuery(
                "SELECT d FROM Donasi d WHERE d.statusDonasi = :status ORDER BY d.tanggalDonasi DESC", 
                Donasi.class
            );
            query.setParameter("status", Donasi.StatusDonasi.valueOf(status));
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error saat mencari donasi berdasarkan status: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<Donasi> findDonasiBerhasil() {
        return findByStatus("berhasil");
    }
    
    @Override
    public List<Donasi> findDonasiPending() {
        return findByStatus("pending");
    }
    
    @Override
    public List<Donasi> findByTanggalRange(LocalDate startDate, LocalDate endDate) {
        try {
            TypedQuery<Donasi> query = entityManager.createQuery(
                "SELECT d FROM Donasi d WHERE d.tanggalDonasi BETWEEN :startDate AND :endDate ORDER BY d.tanggalDonasi DESC", 
                Donasi.class
            );
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error saat mencari donasi berdasarkan tanggal: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<Donasi> findBySekolah(Integer idSekolah) {
        try {
            TypedQuery<Donasi> query = entityManager.createQuery(
                "SELECT d FROM Donasi d WHERE d.kebutuhan.sekolah.idSekolah = :idSekolah ORDER BY d.tanggalDonasi DESC", 
                Donasi.class
            );
            query.setParameter("idSekolah", idSekolah);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error saat mencari donasi berdasarkan sekolah: " + e.getMessage(), e);
        }
    }
    
    /**
     * ANALYTICS QUERIES
     */
    
    @Override
    public BigDecimal getTotalDonasiBerhasil() {
        try {
            TypedQuery<BigDecimal> query = entityManager.createQuery(
                "SELECT COALESCE(SUM(d.jumlahDonasi), 0) FROM Donasi d WHERE d.statusDonasi = 'berhasil'", 
                BigDecimal.class
            );
            BigDecimal total = query.getSingleResult();
            return total != null ? total : BigDecimal.ZERO;
        } catch (Exception e) {
            throw new RuntimeException("Error saat menghitung total donasi: " + e.getMessage(), e);
        }
    }
    
    @Override
    public BigDecimal getTotalDonasiByDonatur(Integer idDonatur) {
        try {
            TypedQuery<BigDecimal> query = entityManager.createQuery(
                "SELECT COALESCE(SUM(d.jumlahDonasi), 0) FROM Donasi d " +
                "WHERE d.donatur.idPengguna = :idDonatur AND d.statusDonasi = 'berhasil'", 
                BigDecimal.class
            );
            query.setParameter("idDonatur", idDonatur);
            BigDecimal total = query.getSingleResult();
            return total != null ? total : BigDecimal.ZERO;
        } catch (Exception e) {
            throw new RuntimeException("Error saat menghitung total donasi donatur: " + e.getMessage(), e);
        }
    }
    
    @Override
    public BigDecimal getTotalDonasiByKebutuhan(Integer idKebutuhan) {
        try {
            TypedQuery<BigDecimal> query = entityManager.createQuery(
                "SELECT COALESCE(SUM(d.jumlahDonasi), 0) FROM Donasi d " +
                "WHERE d.kebutuhan.idKebutuhan = :idKebutuhan AND d.statusDonasi = 'berhasil'", 
                BigDecimal.class
            );
            query.setParameter("idKebutuhan", idKebutuhan);
            BigDecimal total = query.getSingleResult();
            return total != null ? total : BigDecimal.ZERO;
        } catch (Exception e) {
            throw new RuntimeException("Error saat menghitung total donasi kebutuhan: " + e.getMessage(), e);
        }
    }
    
    @Override
    public BigDecimal getTotalDonasiBySekolah(Integer idSekolah) {
        try {
            TypedQuery<BigDecimal> query = entityManager.createQuery(
                "SELECT COALESCE(SUM(d.jumlahDonasi), 0) " +
                "FROM Donasi d WHERE d.kebutuhan.sekolah.idSekolah = :idSekolah " +
                "AND d.statusDonasi = 'berhasil'",
                BigDecimal.class
            );
            query.setParameter("idSekolah", idSekolah);
            BigDecimal total = query.getSingleResult();
            return total != null ? total : BigDecimal.ZERO;
        } catch (Exception e) {
            System.err.println("Error getTotalDonasiBySekolah (JPA): " + e.getMessage());
            return BigDecimal.ZERO;
        }
    }


    
    @Override
    public BigDecimal getTotalDonasiByPeriode(LocalDate startDate, LocalDate endDate) {
        try {
            TypedQuery<BigDecimal> query = entityManager.createQuery(
                "SELECT COALESCE(SUM(d.jumlahDonasi), 0) FROM Donasi d " +
                "WHERE d.tanggalDonasi BETWEEN :startDate AND :endDate AND d.statusDonasi = 'berhasil'", 
                BigDecimal.class
            );
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            BigDecimal total = query.getSingleResult();
            return total != null ? total : BigDecimal.ZERO;
        } catch (Exception e) {
            throw new RuntimeException("Error saat menghitung total donasi periode: " + e.getMessage(), e);
        }
    }
    
    /**
     * REPORTING QUERIES
     */
    
    @Override
    public List<Donasi> getTopDonasi(int limit) {
        try {
            TypedQuery<Donasi> query = entityManager.createQuery(
                "SELECT d FROM Donasi d WHERE d.statusDonasi = 'berhasil' ORDER BY d.jumlahDonasi DESC", 
                Donasi.class
            );
            query.setMaxResults(limit);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error saat mengambil top donasi: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<Donasi> getDonasiTerbaru(int limit) {
        try {
            TypedQuery<Donasi> query = entityManager.createQuery(
                "SELECT d FROM Donasi d ORDER BY d.tanggalDonasi DESC", 
                Donasi.class
            );
            query.setMaxResults(limit);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error saat mengambil donasi terbaru: " + e.getMessage(), e);
        }
    }
    
    @Override
    public long countByStatus(String status) {
        try {
            TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(d) FROM Donasi d WHERE d.statusDonasi = :status", 
                Long.class
            );
            query.setParameter("status", Donasi.StatusDonasi.valueOf(status));
            return query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Error saat menghitung donasi berdasarkan status: " + e.getMessage(), e);
        }
    }
    
    @Override
    public BigDecimal getAverageDonasiAmount() {
        try {
            TypedQuery<BigDecimal> query = entityManager.createQuery(
                "SELECT AVG(d.jumlahDonasi) FROM Donasi d WHERE d.statusDonasi = 'berhasil'", 
                BigDecimal.class
            );
            BigDecimal average = query.getSingleResult();
            return average != null ? average : BigDecimal.ZERO;
        } catch (Exception e) {
            throw new RuntimeException("Error saat menghitung rata-rata donasi: " + e.getMessage(), e);
        }
    }
    
    /**
     * COMPLEX ANALYTICS: Statistik per bulan untuk chart
     */
    @Override
    public Map<Integer, BigDecimal> getStatistikDonasiPerBulan(int year) {
        Map<Integer, BigDecimal> result = new HashMap<>();
        
        try {
            for (int month = 1; month <= 12; month++) {
                LocalDate startDate = LocalDate.of(year, month, 1);
                LocalDate endDate = startDate.plusMonths(1).minusDays(1);
                
                BigDecimal total = getTotalDonasiByPeriode(startDate, endDate);
                result.put(month, total);
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error saat menghitung statistik per bulan: " + e.getMessage(), e);
        }
    }
    
    /**
     * BUSINESS LOGIC: Konfirmasi donasi
     */
    
    @Override
    public List<Object[]> findRiwayatBySekolah(Integer idSekolah) {
        try {
            String jpql = "SELECT d.idDonasi, p.nama, k.namaAlat, d.jumlahDonasi, " +
                          "d.metodePembayaran, d.statusDonasi, d.tanggalDonasi " +
                          "FROM Donasi d " +
                          "JOIN d.kebutuhan k " +
                          "JOIN d.donatur p " + // <=== FIX DI SINI
                          "WHERE k.sekolah.idSekolah = :idSekolah " +
                          "ORDER BY d.tanggalDonasi DESC";

            TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
            query.setParameter("idSekolah", idSekolah);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error saat mengambil riwayat donasi sekolah: " + e.getMessage(), e);
        }
    }

    
    @Override
    public boolean konfirmasiDonasi(Integer idDonasi) {
        EntityTransaction transaction = null;
        try {
            Optional<Donasi> donasiOpt = findById(idDonasi);
            if (donasiOpt.isEmpty()) {
                return false;
            }
            
            transaction = entityManager.getTransaction();
            transaction.begin();
            
            Donasi donasi = donasiOpt.get();
            donasi.konfirmasiBerhasil();
            entityManager.merge(donasi);
            
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error saat konfirmasi donasi: " + e.getMessage(), e);
        }
    }
    
    /**
     * BUSINESS LOGIC: Batalkan donasi
     */
    @Override
    public boolean batalkanDonasi(Integer idDonasi, String alasan) {
        EntityTransaction transaction = null;
        try {
            Optional<Donasi> donasiOpt = findById(idDonasi);
            if (donasiOpt.isEmpty()) {
                return false;
            }
            
            transaction = entityManager.getTransaction();
            transaction.begin();
            
            Donasi donasi = donasiOpt.get();
            donasi.batalkan();
            // Alasan bisa disimpan di field catatan jika ada
            entityManager.merge(donasi);
            
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error saat batalkan donasi: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<Kebutuhan> findKebutuhanTersedia() {
        try {
            TypedQuery<Kebutuhan> query = entityManager.createQuery(
                "SELECT k FROM Kebutuhan k WHERE k.status <> :status ORDER BY k.tanggalPengajuan DESC",
                Kebutuhan.class
            );
            query.setParameter("status", Kebutuhan.Status.terpenuhi);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error saat mengambil kebutuhan yang tersedia: " + e.getMessage(), e);
        }
    }
}
