package dao;

import java.util.List;
import java.util.Optional;

/**
 * Generic DAO Interface - Base interface untuk semua DAO
 * Menerapkan INTERFACE (Pilar OOP ke-5)
 * Menyediakan operasi CRUD dasar untuk semua entity
 * 
 * @param <T> Tipe entity
 * @param <ID> Tipe primary key
 * @author EduShare Team
 */
public interface GenericDAO<T, ID> {
    
    /**
     * Menyimpan entity baru ke database (CREATE)
     * @param entity Entity yang akan disimpan
     * @return Entity yang telah disimpan dengan ID
     */
    T save(T entity);
    
    /**
     * Mengupdate entity yang sudah ada (UPDATE)
     * @param entity Entity yang akan diupdate
     * @return Entity yang telah diupdate
     */
    T update(T entity);
    
    /**
     * Menghapus entity dari database (DELETE)
     * @param entity Entity yang akan dihapus
     */
    void delete(T entity);
    
    /**
     * Menghapus entity berdasarkan ID
     * @param id ID entity yang akan dihapus
     */
    void deleteById(ID id);
    
    /**
     * Mencari entity berdasarkan ID (READ)
     * @param id ID entity yang dicari
     * @return Optional berisi entity jika ditemukan
     */
    Optional<T> findById(ID id);
    
    /**
     * Mengambil semua entity dari database
     * @return List berisi semua entity
     */
    List<T> findAll();
    
    /**
     * Mengambil entity dengan pagination
     * @param offset Starting position
     * @param limit Jumlah maksimal data
     * @return List berisi entity sesuai pagination
     */
    List<T> findAll(int offset, int limit);
    
    /**
     * Menghitung total entity di database
     * @return Jumlah total entity
     */
    long count();
    
    /**
     * Mengecek apakah entity dengan ID tertentu ada
     * @param id ID yang akan dicek
     * @return true jika entity ada, false jika tidak
     */
    boolean existsById(ID id);
    
    /**
     * Refresh entity dari database (reload dari DB)
     * @param entity Entity yang akan di-refresh
     */
    void refresh(T entity);
    
    /**
     * Detach entity dari persistence context
     * @param entity Entity yang akan di-detach
     */
    void detach(T entity);
}
