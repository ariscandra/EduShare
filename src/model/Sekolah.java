package model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Entity Sekolah
 * Menerapkan ENCAPSULATION dengan private fields dan getter/setter
 * 
 * @author EduShare Team
 */
@Entity
@Table(name = "sekolah")
public class Sekolah implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sekolah")
    private Integer idSekolah;

    // 🔥 HAPUS relasi ke Pengguna karena konflik
    // @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // @JoinColumn(name = "id_pengguna", nullable = false)
    // private Pengguna pengguna;

    // ===============================
    // RELASI KE PIHAK SEKOLAH
    // ===============================
    @OneToMany(mappedBy = "sekolah", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PihakSekolah> daftarPihakSekolah = new ArrayList<>();

    // ===============================
    // RELASI LAIN
    // ===============================
    @OneToMany(mappedBy = "sekolah", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Kebutuhan> daftarKebutuhan = new ArrayList<>();

    @OneToMany(mappedBy = "sekolah", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Dokumen> daftarDokumen = new ArrayList<>();

    @OneToMany(mappedBy = "sekolah", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Admin> daftarAdmin = new ArrayList<>();

    @OneToMany(mappedBy = "sekolah", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Donatur> daftarDonatur = new ArrayList<>();

    // ===============================
    // FIELD UTAMA
    // ===============================
    @Column(name = "nama_sekolah", nullable = false, length = 100)
    private String namaSekolah;

    @Column(name = "NPSN", nullable = false, unique = true, length = 20)
    private String npsn;

    @Column(name = "alamat_sekolah", nullable = false, length = 100)
    private String alamatSekolah;

    @Column(name = "kecamatan", nullable = false, length = 100)
    private String kecamatan;

    @Column(name = "kabupaten", nullable = false, length = 100)
    private String kabupaten;

    @Enumerated(EnumType.STRING)
    @Column(name = "kategori", nullable = false)
    private KategoriSekolah kategori;

    @Column(name = "kebutuhan", length = 100)
    private String kebutuhan;

    @Column(name = "status_verifikasi", length = 50)
    private String statusVerifikasi = "Belum Diverifikasi";

    // ENUM KATEGORI SEKOLAH
    public enum KategoriSekolah {
        SD, SMP, SMA, SMK, SLB
    }

    // ===============================
    // CONSTRUCTOR
    // ===============================
    public Sekolah() {
        this.statusVerifikasi = "Belum Diverifikasi";
    }

    public Sekolah(String namaSekolah, String npsn, String alamatSekolah,
                   String kecamatan, String kabupaten,
                   KategoriSekolah kategori, String kebutuhan) {
        this.namaSekolah = namaSekolah;
        this.npsn = npsn;
        this.alamatSekolah = alamatSekolah;
        this.kecamatan = kecamatan;
        this.kabupaten = kabupaten;
        this.kategori = kategori;
        this.kebutuhan = kebutuhan;
        this.statusVerifikasi = "Belum Diverifikasi";
    }

    // ===============================
    // METHOD BANTUAN
    // ===============================
    public void tambahKebutuhan(Kebutuhan kebutuhan) {
        daftarKebutuhan.add(kebutuhan);
        kebutuhan.setSekolah(this);
    }

    public void tambahDokumen(Dokumen dokumen) {
        daftarDokumen.add(dokumen);
        dokumen.setSekolah(this);
    }

    public String getAlamatLengkap() {
        return String.format("%s, %s, %s", alamatSekolah, kecamatan, kabupaten);
    }

    // ===============================
    // GETTER SETTER
    // ===============================
    public Integer getIdSekolah() { return idSekolah; }
    public void setIdSekolah(Integer idSekolah) { this.idSekolah = idSekolah; }

    public String getNamaSekolah() { return namaSekolah; }
    public void setNamaSekolah(String namaSekolah) { this.namaSekolah = namaSekolah; }

    public String getNpsn() { return npsn; }
    public void setNpsn(String npsn) { this.npsn = npsn; }

    public String getAlamatSekolah() { return alamatSekolah; }
    public void setAlamatSekolah(String alamatSekolah) { this.alamatSekolah = alamatSekolah; }

    public String getKecamatan() { return kecamatan; }
    public void setKecamatan(String kecamatan) { this.kecamatan = kecamatan; }

    public String getKabupaten() { return kabupaten; }
    public void setKabupaten(String kabupaten) { this.kabupaten = kabupaten; }

    public KategoriSekolah getKategori() { return kategori; }
    public void setKategori(KategoriSekolah kategori) { this.kategori = kategori; }

    public String getKebutuhan() { return kebutuhan; }
    public void setKebutuhan(String kebutuhan) { this.kebutuhan = kebutuhan; }

    public String getStatusVerifikasi() { return statusVerifikasi; }
    public void setStatusVerifikasi(String statusVerifikasi) { this.statusVerifikasi = statusVerifikasi; }

    public List<PihakSekolah> getDaftarPihakSekolah() { return daftarPihakSekolah; }
    public void setDaftarPihakSekolah(List<PihakSekolah> daftarPihakSekolah) { this.daftarPihakSekolah = daftarPihakSekolah; }

    public List<Kebutuhan> getDaftarKebutuhan() { return daftarKebutuhan; }
    public void setDaftarKebutuhan(List<Kebutuhan> daftarKebutuhan) { this.daftarKebutuhan = daftarKebutuhan; }

    public List<Dokumen> getDaftarDokumen() { return daftarDokumen; }
    public void setDaftarDokumen(List<Dokumen> daftarDokumen) { this.daftarDokumen = daftarDokumen; }

    public List<Admin> getDaftarAdmin() { return daftarAdmin; }
    public void setDaftarAdmin(List<Admin> daftarAdmin) { this.daftarAdmin = daftarAdmin; }

    public List<Donatur> getDaftarDonatur() { return daftarDonatur; }
    public void setDaftarDonatur(List<Donatur> daftarDonatur) { this.daftarDonatur = daftarDonatur; }

    // ===============================
    // EQUALS HASHCODE TOSTRING
    // ===============================
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sekolah)) return false;
        Sekolah sekolah = (Sekolah) o;
        return Objects.equals(idSekolah, sekolah.idSekolah);
    }

    @Override
    public int hashCode() { return Objects.hash(idSekolah); }

    @Override
    public String toString() {
        return "Sekolah{" +
                "idSekolah=" + idSekolah +
                ", namaSekolah='" + namaSekolah + '\'' +
                ", npsn='" + npsn + '\'' +
                ", kategori=" + kategori +
                ", kabupaten='" + kabupaten + '\'' +
                ", statusVerifikasi='" + statusVerifikasi + '\'' +
                '}';
    }
}
