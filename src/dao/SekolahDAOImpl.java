package dao;

import model.Sekolah;
import jakarta.persistence.*;
import java.util.Collections;
import java.util.List;
import model.Kebutuhan;

/**
 * SekolahDAO Implementation
 * Implementasi query dinamis, relasi, dan analitik donasi
 * 
 * @author EduShare
 */
public class SekolahDAOImpl extends GenericDAOImpl<Sekolah, Integer> implements SekolahDAO {
    
    public SekolahDAOImpl() {
        super(Sekolah.class);
    }

    public SekolahDAOImpl(EntityManager entityManager) {
        super(Sekolah.class, entityManager);
    }

    @Override
    public List<Sekolah> searchByNama(String namaSekolah) {
        try {
            TypedQuery<Sekolah> query = entityManager.createQuery(
                "SELECT s FROM Sekolah s WHERE LOWER(s.namaSekolah) LIKE LOWER(:nama)",
                Sekolah.class
            );
            query.setParameter("nama", "%" + namaSekolah + "%");
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error saat mencari sekolah: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Sekolah> findByKategori(String kategori) {
        try {
            TypedQuery<Sekolah> query = entityManager.createQuery(
                "SELECT s FROM Sekolah s WHERE s.kategori = :kategori",
                Sekolah.class
            );
            query.setParameter("kategori", kategori);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error saat mencari berdasarkan kategori: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Sekolah> findByAlamat(String alamat) {
        try {
            TypedQuery<Sekolah> query = entityManager.createQuery(
                "SELECT s FROM Sekolah s WHERE LOWER(s.alamatSekolah) LIKE LOWER(:alamat)",
                Sekolah.class
            );
            query.setParameter("alamat", "%" + alamat + "%");
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error saat mencari berdasarkan alamat: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Sekolah> findSekolahByStatusKebutuhan(String statusVerifikasi) {
        try {
            // Convert String ke Enum sebelum dikirim ke query
            Kebutuhan.Status statusEnum = Kebutuhan.Status.valueOf(statusVerifikasi);

            TypedQuery<Sekolah> query = entityManager.createQuery(
                "SELECT DISTINCT s FROM Sekolah s JOIN s.kebutuhanList k WHERE k.status = :statusEnum",
                Sekolah.class
            );
            query.setParameter("statusEnum", statusEnum);

            return query.getResultList();

        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Status '" + statusVerifikasi + "' tidak valid di enum Kebutuhan.Status", e);
        } catch (Exception e) {
            throw new RuntimeException("Error saat mencari sekolah berdasarkan status kebutuhan: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Sekolah> findSekolahTerverifikasi() {
        return findSekolahByStatusKebutuhan(Kebutuhan.Status.diverifikasi.name());
    }


    @Override
    public List<Sekolah> findSekolahMenungguVerifikasi() {
        return findSekolahByStatusKebutuhan("diajukan");
    }

    @Override
    public List<Sekolah> searchWithFilters(String kategori, String alamat, String statusKebutuhan) {
        try {
            StringBuilder jpql = new StringBuilder("SELECT DISTINCT s FROM Sekolah s LEFT JOIN s.kebutuhanList k WHERE 1=1");

            if (kategori != null && !kategori.isEmpty()) jpql.append(" AND s.kategori = :kategori");
            if (alamat != null && !alamat.isEmpty()) jpql.append(" AND LOWER(s.alamatSekolah) LIKE LOWER(:alamat)");
            if (statusKebutuhan != null && !statusKebutuhan.isEmpty()) jpql.append(" AND k.status = :status");

            TypedQuery<Sekolah> query = entityManager.createQuery(jpql.toString(), Sekolah.class);

            if (kategori != null && !kategori.isEmpty()) query.setParameter("kategori", kategori);
            if (alamat != null && !alamat.isEmpty()) query.setParameter("alamat", "%" + alamat + "%");
            if (statusKebutuhan != null && !statusKebutuhan.isEmpty()) query.setParameter("status", statusKebutuhan);

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error saat search dengan filter kompleks: " + e.getMessage(), e);
        }
    }

    @Override
    public long countByKategori(String kategori) {
        try {
            TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(s) FROM Sekolah s WHERE s.kategori = :kategori", Long.class
            );
            query.setParameter("kategori", kategori);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Error menghitung jumlah sekolah per kategori: " + e.getMessage(), e);
        }
    }

    @Override
    public long countTerverifikasi() {
        try {
            TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(DISTINCT s.idSekolah) FROM Sekolah s JOIN s.kebutuhanList k WHERE k.status = 'diverifikasi'",
                Long.class
            );
            return query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Error saat menghitung sekolah terverifikasi: " + e.getMessage(), e);
        }
    }


    @Override
    public List<Sekolah> getTopSekolahByDonasi(int limit) {
        try {
            TypedQuery<Sekolah> query = entityManager.createQuery(
                "SELECT s FROM Sekolah s " +
                "JOIN s.kebutuhanList k " +
                "JOIN k.donasiList d " +
                "WHERE d.statusDonasi = 'berhasil' " +
                "GROUP BY s.idSekolah " +
                "ORDER BY SUM(d.jumlahDonasi) DESC",
                Sekolah.class
            );
            query.setMaxResults(limit);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error mengambil top sekolah by donasi: " + e.getMessage(), e);
        }
    }
    
    @Override
    public void close() {
        try {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
                System.out.println("✅ EntityManager SekolahDAOImpl berhasil ditutup.");
            }
        } catch (Exception e) {
            System.err.println("❌ Gagal menutup EntityManager SekolahDAOImpl: " + e.getMessage());
        }
    }

}
