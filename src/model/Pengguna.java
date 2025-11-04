package model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Entity Pengguna - Base class untuk semua tipe pengguna
 * Menerapkan Inheritance dengan strategy JOINED
 * 
 * @author EduShare Team
 */
@Entity
@Table(name = "pengguna")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "jenis_user", discriminatorType = DiscriminatorType.STRING)
public abstract class Pengguna implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    // ENCAPSULATION: Private fields dengan getter/setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pengguna")
    private Integer idPengguna;
    
    @Column(name = "nama", nullable = false, length = 100)
    private String nama;
    
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;
    
    @Column(name = "password", nullable = false, length = 100)
    private String password;
    
    @Column(name = "no_hp", length = 20)
    private String noHp;
    
    @Column(name = "alamat", length = 200)
    private String alamat;
    
    @Column(name = "jenis_user", insertable = false, updatable = false)
    private String jenisAkun;
    
    // Constructors
    public Pengguna() {
    }
    
    public Pengguna(String nama, String email, String password, String noHp, String alamat) {
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.noHp = noHp;
        this.alamat = alamat;
    }
    
    // ABSTRACTION: Method abstract yang harus diimplementasi oleh subclass
    public abstract String getRole();
    
    // POLYMORPHISM: Method yang bisa di-override oleh subclass
    public String getInfoLengkap() {
        return String.format("Nama: %s, Email: %s, No HP: %s", nama, email, noHp);
    }
    
    // Getter and Setter - ENCAPSULATION
    public Integer getIdPengguna() {
        return idPengguna;
    }
    
    public void setIdPengguna(Integer idPengguna) {
        this.idPengguna = idPengguna;
    }
    
    public String getNama() {
        return nama;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getNoHp() {
        return noHp;
    }
    
    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }
    
    public String getAlamat() {
        return alamat;
    }
    
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
    public String getJenisAkun() {
        return jenisAkun;
    }
    
    public void setJenisAkun(String jenisAkun) {
        this.jenisAkun = jenisAkun;
    }
    
    // Override equals dan hashCode untuk JPA
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pengguna)) return false;
        Pengguna pengguna = (Pengguna) o;
        return Objects.equals(idPengguna, pengguna.idPengguna);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(idPengguna);
    }
    
    @Override
    public String toString() {
        return "Pengguna{" +
                "idPengguna=" + idPengguna +
                ", nama='" + nama + '\'' +
                ", email='" + email + '\'' +
                ", jenisAkun='" + jenisAkun + '\'' +
                '}';
    }
}
