package model;

import jakarta.persistence.*;

/**
 * Entity Admin - Turunan dari Pengguna
 * Menerapkan INHERITANCE dari class Pengguna
 * 
 * @author EduShare Team
 */
@Entity
@Table(name = "admin")
@DiscriminatorValue("Admin")
@PrimaryKeyJoinColumn(name = "id_pengguna")
public class Admin extends Pengguna {
    
    private static final long serialVersionUID = 1L;
    
    // ENCAPSULATION: Private field dengan getter/setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sekolah", nullable = false)
    private Sekolah sekolah;
    
    // Constructors
    public Admin() {
        super();
    }
    
    public Admin(String nama, String email, String password, String noHp, String alamat, Sekolah sekolah) {
        super(nama, email, password, noHp, alamat);
        this.sekolah = sekolah;
    }
    
    // ABSTRACTION: Implementasi method abstract dari parent
    @Override
    public String getRole() {
        return "Admin";
    }
    
    // POLYMORPHISM: Override method dari parent class
    @Override
    public String getInfoLengkap() {
        String infoSekolah = (sekolah != null) ? sekolah.getNamaSekolah() : "Tidak ada sekolah";
        return super.getInfoLengkap() + String.format(", Role: Admin, Sekolah: %s", infoSekolah);
    }
    
    // Getter and Setter    
    public Sekolah getSekolah() {
        return sekolah;
    }
    
    public void setSekolah(Sekolah sekolah) {
        this.sekolah = sekolah;
    }
    
    @Override
    public String toString() {
        return "Admin{" +
                "idPengguna=" + getIdPengguna() +
                ", nama='" + getNama() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", sekolah=" + (sekolah != null ? sekolah.getNamaSekolah() : "null") +
                '}';
    }
}
