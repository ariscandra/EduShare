package dao;

import jakarta.persistence.*;
import java.util.List;
import java.util.Optional;

/**
 * Generic DAO Implementation
 * ABSTRACTION & POLYMORPHISM: Base implementation untuk semua DAO
 * Menggunakan JPA EntityManager untuk database operations
 * 
 * @param <T> Tipe entity
 * @param <ID> Tipe primary key
 * @author EduShare Team
 */
public abstract class GenericDAOImpl<T, ID> implements GenericDAO<T, ID> {
    
    // EntityManager dari JPA/Hibernate
    protected EntityManager entityManager;
    
    // Class type untuk entity (untuk queries)
    private final Class<T> entityClass;
    
    /**
     * Constructor yang menginisialisasi EntityManager dan entity class
     * @param entityClass Class type dari entity
     */
    public GenericDAOImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
        // Mendapatkan EntityManager dari EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EduSharePU");
        this.entityManager = emf.createEntityManager();
    }
    
    /**
     * Constructor dengan EntityManager custom (untuk testing atau custom setup)
     * @param entityClass Class type dari entity
     * @param entityManager EntityManager yang akan digunakan
     */
    public GenericDAOImpl(Class<T> entityClass, EntityManager entityManager) {
        this.entityClass = entityClass;
        this.entityManager = entityManager;
    }
    
    /**
     * CREATE: Menyimpan entity baru
     * ENCAPSULATION: Method ini mengenkapsulasi logika penyimpanan
     */
    @Override
    public T save(T entity) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error saat menyimpan entity: " + e.getMessage(), e);
        }
    }
    
    /**
     * UPDATE: Mengupdate entity yang sudah ada
     */
    @Override
    public T update(T entity) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            T mergedEntity = entityManager.merge(entity);
            transaction.commit();
            return mergedEntity;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error saat mengupdate entity: " + e.getMessage(), e);
        }
    }
    
    /**
     * DELETE: Menghapus entity
     */
    @Override
    public void delete(T entity) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            // Pastikan entity dalam managed state
            if (!entityManager.contains(entity)) {
                entity = entityManager.merge(entity);
            }
            entityManager.remove(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error saat menghapus entity: " + e.getMessage(), e);
        }
    }
    
    /**
     * DELETE BY ID: Menghapus entity berdasarkan ID
     */
    @Override
    public void deleteById(ID id) {
        Optional<T> entity = findById(id);
        if (entity.isPresent()) {
            delete(entity.get());
        } else {
            throw new RuntimeException("Entity dengan ID " + id + " tidak ditemukan");
        }
    }
    
    /**
     * READ: Mencari entity berdasarkan ID
     */
    @Override
    public Optional<T> findById(ID id) {
        try {
            T entity = entityManager.find(entityClass, id);
            return Optional.ofNullable(entity);
        } catch (Exception e) {
            throw new RuntimeException("Error saat mencari entity dengan ID " + id + ": " + e.getMessage(), e);
        }
    }
    
    /**
     * READ ALL: Mengambil semua entity
     */
    @Override
    public List<T> findAll() {
        try {
            String queryString = "SELECT e FROM " + entityClass.getSimpleName() + " e";
            TypedQuery<T> query = entityManager.createQuery(queryString, entityClass);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error saat mengambil semua entity: " + e.getMessage(), e);
        }
    }
    
    /**
     * READ ALL WITH PAGINATION: Mengambil entity dengan pagination
     */
    @Override
    public List<T> findAll(int offset, int limit) {
        try {
            String queryString = "SELECT e FROM " + entityClass.getSimpleName() + " e";
            TypedQuery<T> query = entityManager.createQuery(queryString, entityClass);
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error saat mengambil entity dengan pagination: " + e.getMessage(), e);
        }
    }
    
    /**
     * COUNT: Menghitung total entity
     */
    @Override
    public long count() {
        try {
            String queryString = "SELECT COUNT(e) FROM " + entityClass.getSimpleName() + " e";
            TypedQuery<Long> query = entityManager.createQuery(queryString, Long.class);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("Error saat menghitung entity: " + e.getMessage(), e);
        }
    }
    
    /**
     * EXISTS: Mengecek apakah entity dengan ID ada
     */
    @Override
    public boolean existsById(ID id) {
        return findById(id).isPresent();
    }
    
    /**
     * REFRESH: Reload entity dari database
     */
    @Override
    public void refresh(T entity) {
        try {
            entityManager.refresh(entity);
        } catch (Exception e) {
            throw new RuntimeException("Error saat refresh entity: " + e.getMessage(), e);
        }
    }
    
    /**
     * DETACH: Lepaskan entity dari persistence context
     */
    @Override
    public void detach(T entity) {
        try {
            entityManager.detach(entity);
        } catch (Exception e) {
            throw new RuntimeException("Error saat detach entity: " + e.getMessage(), e);
        }
    }
    
    /**
     * Method helper untuk menutup EntityManager
     */
    public void close() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }
    
    /**
     * Method helper untuk mendapatkan EntityManager
     * (berguna untuk custom queries di subclass)
     */
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
