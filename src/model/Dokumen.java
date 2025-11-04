package model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entity Dokumen
 * Menerapkan ENCAPSULATION dengan private fields dan getter/setter
 * 
 * @author EduShare Team
 */
@Entity
@Table(name = "dokumen")
public class Dokumen implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    // ENCAPSULATION: Private fields dengan getter/setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dokumen")
    private Integer idDokumen;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sekolah", nullable = false)
    private Sekolah sekolah;
    
    @Column(name = "jenis_dokumen", nullable = false, length = 100)
    private String jenisDokumen;
    
    @Column(name = "nama_file", nullable = false, length = 100)
    private String namaFile;
    
    @Column(name = "path_file", nullable = false, length = 100)
    private String pathFile;
    
    @Column(name = "tanggal_upload", nullable = false)
    private LocalDateTime tanggalUpload;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status_verifikasi", nullable = false)
    private StatusVerifikasi statusVerifikasi;
    
    @Column(name = "catatan_admin", columnDefinition = "TEXT")
    private String catatanAdmin;
    
    // Enum untuk status verifikasi
    public enum StatusVerifikasi {
        menunggu, diterima, ditolak
    }
    
    // Constructors
    public Dokumen() {
        this.tanggalUpload = LocalDateTime.now();
        this.statusVerifikasi = StatusVerifikasi.menunggu;
    }
    
    public Dokumen(Sekolah sekolah, String jenisDokumen, String namaFile, String pathFile) {
        this.sekolah = sekolah;
        this.jenisDokumen = jenisDokumen;
        this.namaFile = namaFile;
        this.pathFile = pathFile;
        this.tanggalUpload = LocalDateTime.now();
        this.statusVerifikasi = StatusVerifikasi.menunggu;
    }
    
    // Business methods
    public void verifikasi(String catatan) {
        this.statusVerifikasi = StatusVerifikasi.diterima;
        this.catatanAdmin = catatan;
    }
    
    public void tolak(String catatan) {
        this.statusVerifikasi = StatusVerifikasi.ditolak;
        this.catatanAdmin = catatan;
    }
    
    public boolean isDiterima() {
        return statusVerifikasi == StatusVerifikasi.diterima;
    }
    
    public boolean isDitolak() {
        return statusVerifikasi == StatusVerifikasi.ditolak;
    }
    
    public boolean isMenunggu() {
        return statusVerifikasi == StatusVerifikasi.menunggu;
    }
    
    public String getFileExtension() {
        int lastIndexOfDot = namaFile.lastIndexOf('.');
        if (lastIndexOfDot == -1) {
            return "";
        }
        return namaFile.substring(lastIndexOfDot + 1);
    }
    
    // Getter and Setter
    public Integer getIdDokumen() {
        return idDokumen;
    }
    
    public void setIdDokumen(Integer idDokumen) {
        this.idDokumen = idDokumen;
    }
    
    public Sekolah getSekolah() {
        return sekolah;
    }
    
    public void setSekolah(Sekolah sekolah) {
        this.sekolah = sekolah;
    }
    
    public String getJenisDokumen() {
        return jenisDokumen;
    }
    
    public void setJenisDokumen(String jenisDokumen) {
        this.jenisDokumen = jenisDokumen;
    }
    
    public String getNamaFile() {
        return namaFile;
    }
    
    public void setNamaFile(String namaFile) {
        this.namaFile = namaFile;
    }
    
    public String getPathFile() {
        return pathFile;
    }
    
    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }
    
    public LocalDateTime getTanggalUpload() {
        return tanggalUpload;
    }
    
    public void setTanggalUpload(LocalDateTime tanggalUpload) {
        this.tanggalUpload = tanggalUpload;
    }
    
    public StatusVerifikasi getStatusVerifikasi() {
        return statusVerifikasi;
    }
    
    public void setStatusVerifikasi(StatusVerifikasi statusVerifikasi) {
        this.statusVerifikasi = statusVerifikasi;
    }
    
    public String getCatatanAdmin() {
        return catatanAdmin;
    }
    
    public void setCatatanAdmin(String catatanAdmin) {
        this.catatanAdmin = catatanAdmin;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dokumen)) return false;
        Dokumen dokumen = (Dokumen) o;
        return Objects.equals(idDokumen, dokumen.idDokumen);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(idDokumen);
    }
    
    @Override
    public String toString() {
        return "Dokumen{" +
                "idDokumen=" + idDokumen +
                ", jenisDokumen='" + jenisDokumen + '\'' +
                ", namaFile='" + namaFile + '\'' +
                ", tanggalUpload=" + tanggalUpload +
                ", statusVerifikasi=" + statusVerifikasi +
                '}';
    }
}
