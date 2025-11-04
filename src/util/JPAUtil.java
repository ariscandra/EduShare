package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.sql.Connection;
/**
 * JPAUtil - Utility class untuk manage EntityManagerFactory
 * Menggunakan Singleton pattern untuk efisiensi
 */
public class JPAUtil {
    
    private static EntityManagerFactory entityManagerFactory;
    
    // Static initialization block
    static {
        try {
            // Create EntityManagerFactory dari persistence.xml
            entityManagerFactory = Persistence.createEntityManagerFactory("EduSharePU");
            System.out.println("✓ EntityManagerFactory initialized successfully");
        } catch (Exception e) {
            System.err.println("✗ Error initializing EntityManagerFactory: " + e.getMessage());
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }


    
    /**
     * Get EntityManager instance
     * @return EntityManager
     */
    public static EntityManager getEntityManager() {
        if (entityManagerFactory == null) {
            throw new IllegalStateException("EntityManagerFactory is not initialized");
        }
        return entityManagerFactory.createEntityManager();
    }
    
    /**
     * Close EntityManagerFactory
     * Panggil method ini saat aplikasi shutdown
     */
    public static void close() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
            System.out.println("✓ EntityManagerFactory closed");
        }
    }
    
    /**
     * Check if EntityManagerFactory is open
     * @return true if open, false otherwise
     */
    public static boolean isOpen() {
        return entityManagerFactory != null && entityManagerFactory.isOpen();
    }
}
