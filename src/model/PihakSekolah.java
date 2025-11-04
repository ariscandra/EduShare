package model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Entity PihakSekolah - Turunan dari Pengguna
 * Menerapkan INHERITANCE dari class Pengguna
 * 
 * @author EduShare Team
 */
@Entity
@Table(name = "pihak_sekolah")
@DiscriminatorValue("Sekolah")
@PrimaryKeyJoinColumn(name = "id_pengguna")
public class PihakSekolah extends Pengguna implements Serializable {

    private static final long serialVersionUID = 1L;

    // ==========================
    // RELASI KE SEKOLAH
    // ==========================
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sekolah", nullable = false)
    private Sekolah sekolah;

    // ==========================
    // ATRIBUT KHUSUS
    // ==========================
    @Column(name = "jabatan", nullable = false, length = 50)
    private String jabatan;

    // ==========================
    // CONSTRUCTOR
    // ==========================
    public PihakSekolah() {
        super();
    }

    public PihakSekolah(String nama, String email, String password, String noHp, String alamat,
                        Sekolah sekolah, String jabatan) {
        super(nama, email, password, noHp, alamat);
        this.sekolah = sekolah;
        this.jabatan = jabatan;
    }

    // ==========================
    // IMPLEMENTASI ABSTRACT METHOD
    // ==========================
    @Override
    public String getRole() {
        return "Pihak Sekolah";
    }

    @Override
    public String getInfoLengkap() {
        String infoSekolah = (sekolah != null) ? sekolah.getNamaSekolah() : "Tidak ada sekolah";
        return super.getInfoLengkap() +
                String.format(", Role: Pihak Sekolah, Jabatan: %s, Sekolah: %s", jabatan, infoSekolah);
    }

    // ==========================
    // GETTER & SETTER
    // ==========================
    public Sekolah getSekolah() {
        return sekolah;
    }

    public void setSekolah(Sekolah sekolah) {
        this.sekolah = sekolah;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    // 🔥 Tambahan penting: shortcut untuk ambil id sekolah langsung
    public Integer getIdSekolah() {
        return (sekolah != null) ? sekolah.getIdSekolah() : null;
    }

    // ==========================
    // EQUALS & HASHCODE
    // ==========================
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PihakSekolah)) return false;
        PihakSekolah that = (PihakSekolah) o;
        return Objects.equals(getIdPengguna(), that.getIdPengguna());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdPengguna());
    }

    // ==========================
    // TO STRING
    // ==========================
    @Override
    public String toString() {
        return "PihakSekolah{" +
                "idPengguna=" + getIdPengguna() +
                ", nama='" + getNama() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", jabatan='" + jabatan + '\'' +
                ", sekolah=" + (sekolah != null ? sekolah.getNamaSekolah() : "null") +
                '}';
    }
}
