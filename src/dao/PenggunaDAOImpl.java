package dao;

import model.Pengguna;
import jakarta.persistence.*;
import java.util.List;
import java.util.Optional;

/**
 * PenggunaDAO Implementation
 * Implementasi lengkap untuk operasi database Pengguna
 * INHERITANCE: Extends GenericDAOImpl
 * POLYMORPHISM: Override dan implementasi custom methods
 * 
 * @author EduShare Team
 */
public class PenggunaDAOImpl extends GenericDAOImpl<Pengguna, Integer> implements PenggunaDAO {
    
    /**
     * Constructor
     */
    public PenggunaDAOImpl() {
        super(Pengguna.class);
    }
    
    /**
     * Constructor dengan EntityManager custom
     */
    public PenggunaDAOImpl(EntityManager entityManager) {
        super(Pengguna.class, entityManager);
    }
    
    /**
     * Mencari pengguna berdasarkan email
     * Menggunakan JPQL (Java Persistence Query Language)
     */
    @Override
    public Optional<Pengguna> findByEmail(String email) {
        try {
            TypedQuery<Pengguna> query = entityManager.createQuery(
                "SELECT p FROM Pengguna p WHERE p.email = :email", 
                Pengguna.class
            );
            query.setParameter("email", email);
            Pengguna pengguna = query.getSingleResult();
            return Optional.of(pengguna);
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException("Error saat mencari pengguna dengan email: " + e.getMessage(), e);
        }
    }
    
    /**
     * Autentikasi: Mencari pengguna berdasarkan email dan password
     * Method ini untuk login
     */
    @Override
    public Optional<Pengguna> findByEmailAndPassword(String email, String password) {
        try {
            TypedQuery<Pengguna> query = entityManager.createQuery(
                "SELECT p FROM Pengguna p WHERE p.email = :email AND p.password = :password", 
                Pengguna.class
            );
            query.setParameter("email", email);
            query.setParameter("password", password);
            Pengguna pengguna = query.getSingleResult();
            return Optional.of(pengguna);
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException("Error saat autentikasi pengguna: " + e.getMessage(), e);
        }
    }
    
    /**
     * Filter pengguna berdasarkan jenis akun
     */
    @Override
    public List<Pengguna> findByJenisAkun(String jenisAkun) {
        try {
            TypedQuery<Pengguna> query = entityManager.createQuery(
                "SELECT p FROM Pengguna p WHERE p.jenisAkun = :jenisAkun", 
                Pengguna.class
            );
            query.setParameter("jenisAkun", jenisAkun);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error saat mencari pengguna berdasarkan jenis akun: " + e.getMessage(), e);
        }
    }
    
    /**
     * Search pengguna berdasarkan nama
     * Menggunakan LIKE operator untuk partial matching
     */
    @Override
    public List<Pengguna> searchByNama(String nama) {
        try {
            TypedQuery<Pengguna> query = entityManager.createQuery(
                "SELECT p FROM Pengguna p WHERE LOWER(p.nama) LIKE LOWER(:nama)", 
                Pengguna.class
            );
            query.setParameter("nama", "%" + nama + "%");
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error saat search pengguna: " + e.getMessage(), e);
        }
    }
    
    /**
     * Validasi: Cek apakah email sudah terdaftar
     * Penting untuk registrasi
     */
    @Override
    public boolean isEmailExists(String email) {
        try {
            TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(p) FROM Pengguna p WHERE p.email = :email", 
                Long.class
            );
            query.setParameter("email", email);
            Long count = query.getSingleResult();
            return count > 0;
        } catch (Exception e) {
            throw new RuntimeException("Error saat mengecek email: " + e.getMessage(), e);
        }
    }
    
    /**
     * Ganti password pengguna
     * Method untuk fitur "Lupa Password" atau "Ganti Password"
     */
    @Override
    public boolean gantiPassword(Integer idPengguna, String passwordBaru) {
        EntityTransaction transaction = null;
        try {
            Optional<Pengguna> penggunaOpt = findById(idPengguna);
            if (penggunaOpt.isEmpty()) {
                return false;
            }
            
            transaction = entityManager.getTransaction();
            transaction.begin();
            
            Pengguna pengguna = penggunaOpt.get();
            pengguna.setPassword(passwordBaru);
            entityManager.merge(pengguna);
            
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error saat mengganti password: " + e.getMessage(), e);
        }
    }
    
    /**
     * Update profil pengguna
     * Hanya update: nama, no hp, alamat (tidak termasuk email dan password)
     */
    @Override
    public Pengguna updateProfil(Pengguna pengguna) {
        EntityTransaction transaction = null;
        try {
            Optional<Pengguna> existingOpt = findById(pengguna.getIdPengguna());
            if (existingOpt.isEmpty()) {
                throw new RuntimeException("Pengguna tidak ditemukan");
            }
            
            transaction = entityManager.getTransaction();
            transaction.begin();
            
            Pengguna existing = existingOpt.get();
            // Update hanya field yang boleh diubah
            existing.setNama(pengguna.getNama());
            existing.setNoHp(pengguna.getNoHp());
            existing.setAlamat(pengguna.getAlamat());
            
            Pengguna updated = entityManager.merge(existing);
            transaction.commit();
            return updated;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error saat update profil: " + e.getMessage(), e);
        }
    }
}
