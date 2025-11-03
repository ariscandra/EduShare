<img src="https://github.com/user-attachments/assets/ea632656-6bb6-4c2d-a4c8-15baa6bf9137" alt="Header Atas" width="100%">

<p align="center"><strong>
    Selamat Datang di Panduan Program Sistem Donasi Alat Pendidikan EduShare!  
</strong></p>

## 📚 Daftar Isi
- [👥 Profil](#-profil)
- [🚀 Deskripsi Program](#-deskripsi-program)
- [📖 Fitur Program](#-fitur-program)
- [🖥️ Implementasi OOP](#️-implementasi-oop)
- [📂 Struktur Packages](#-struktur-packages)
- [📚 Libraries](#-libraries)
- [📲 Penggunaan Program](#-penggunaan-program)

## 👥 Profil

<div align="center">
<strong>Kelompok The Hood Council</strong><br><br>

| Ahmad Sepriza | Ahmad Dani | Aris Candra Muzaffar | Moreno Ferdinand Farhantino |
|----------------------|----------------------------|----------------|----------------|
| **NIM:** 2409116025 <br> **Kelas:** Sistem Informasi A '24 <br> [![Riza](https://img.shields.io/badge/-Riza-FFFFFF?logo=github&logoColor=black)](https://github.com/ahmadsepriza) &nbsp; &nbsp; | **NIM:** 2409116074 <br> **Kelas:** Sistem Informasi B '24 <br> [![Dani](https://img.shields.io/badge/-Dani-FFFFFF?logo=github&logoColor=black)](https://github.com/ahmddanii) &nbsp; &nbsp; | **NIM:** 2409116088 <br> **Kelas:** Sistem Informasi C '24 <br> [![Aris](https://img.shields.io/badge/-Aris-FFFFFF?logo=github&logoColor=black)](https://github.com/ariscandra) &nbsp; &nbsp; | **NIM:** 2409116097 <br> **Kelas:** Sistem Informasi C '24 <br> [![Moreno](https://img.shields.io/badge/-Moreno-FFFFFF?logo=github&logoColor=black)](https://github.com/MorenoEndo) |

</div>

## 🚀 Deskripsi Program

**EduShare** adalah aplikasi desktop berbasis Java yang dirancang untuk memfasilitasi sistem donasi alat pendidikan secara transparan dan terstruktur. Program ini menghubungkan sekolah-sekolah yang membutuhkan bantuan alat pendidikan dengan para donatur yang ingin berkontribusi dalam pemerataan akses pendidikan di Indonesia.

### Latar Belakang

Ketimpangan akses fasilitas pendidikan masih menjadi tantangan besar di Indonesia. Banyak sekolah, terutama di daerah terpencil, mengalami kekurangan alat pembelajaran dasar seperti buku, komputer, dan alat peraga. Di sisi lain, masyarakat yang ingin berdonasi sering kesulitan menemukan informasi sekolah mana yang benar-benar membutuhkan dan bagaimana menyalurkan bantuan secara tepat sasaran.

### Solusi yang Ditawarkan

EduShare hadir sebagai platform yang:
- Memberikan transparansi penuh atas kebutuhan sekolah dan penyaluran donasi
- Memudahkan donatur menemukan sekolah yang membutuhkan bantuan
- Menjamin akuntabilitas melalui sistem verifikasi dan dokumentasi
- Mendukung pemerataan akses pendidikan yang berkualitas di seluruh Indonesia

### Teknologi yang Digunakan

Program ini dikembangkan menggunakan:
- **Bahasa Pemrograman**: Java dengan paradigma Object-Oriented Programming (OOP)
- **GUI Framework**: Java Swing
- **Database**: MySQL untuk penyimpanan data persisten
- **ORM**: JPA/Hibernate untuk mapping object-relational
- **Arsitektur**: Model-View-Controller (MVC) yang memisahkan logika bisnis, tampilan, dan kontrol data

### Tujuan Program

1. **Mendorong pemerataan fasilitas pendidikan** di seluruh Indonesia
2. **Mewujudkan transparansi dan akuntabilitas** dalam setiap transaksi donasi
3. **Mendukung partisipasi masyarakat** secara inklusif dalam pembangunan pendidikan
4. **Menyelaraskan dengan SDGs 4** (Quality Education) untuk pendidikan berkualitas
5. **Memberikan inovasi digital** untuk mengatasi ketimpangan akses pendidikan

## 📖 Fitur Program

Program EduShare memiliki tiga jenis pengguna dengan fitur-fitur khusus yang disesuaikan dengan kebutuhan masing-masing:

### 🎯 Fitur untuk Admin

<details>
<summary><strong>Klik untuk melihat detail fitur Admin</strong></summary>

Admin berperan sebagai pengawas sistem yang memastikan validitas data dan kelancaran proses donasi.

1. **Login**
   - Admin dapat masuk ke sistem untuk mengakses seluruh data sekolah, donasi, dan dokumen

2. **Melihat Daftar Sekolah**
   - Menampilkan semua sekolah yang terdaftar dalam sistem
   - Melihat status verifikasi setiap sekolah
   - Mengakses informasi detail lokasi dan kebutuhan sekolah

3. **Verifikasi Sekolah**
   - Memeriksa kelengkapan data sekolah yang mendaftar
   - Menyetujui atau menolak pengajuan pendaftaran sekolah
   - Memastikan data sekolah valid sebelum ditampilkan ke donatur

4. **Verifikasi Dokumen**
   - Memeriksa dokumen pendukung yang diunggah sekolah
   - Memvalidasi keaslian dokumen seperti surat pernyataan dan foto kondisi sekolah
   - Memberikan catatan atau feedback untuk dokumen yang ditolak

5. **Verifikasi Donasi**
   - Mengecek dan menyetujui donasi yang diajukan oleh donatur
   - Memperbarui status distribusi bantuan
   - Memastikan donasi sampai ke sekolah yang tepat

6. **Manajemen Data**
   - Menambah, mengedit, dan menghapus data sekolah
   - Mengelola data donatur dalam sistem
   - Memantau seluruh aktivitas donasi dalam platform

</details>

### 💝 Fitur untuk Donatur

<details>
<summary><strong>Klik untuk melihat detail fitur Donatur</strong></summary>

Donatur adalah pengguna yang ingin menyalurkan bantuan kepada sekolah-sekolah yang membutuhkan.

1. **Register**
   - Membuat akun baru dengan mengisi data pribadi
   - Memasukkan informasi seperti nama, email, nomor telepon, dan alamat
   - Mendapatkan akses ke seluruh fitur donasi setelah registrasi berhasil

2. **Login**
   - Masuk ke sistem menggunakan akun yang telah terdaftar
   - Mengakses dashboard dan fitur donasi

3. **Melihat Daftar Sekolah**
   - Menjelajahi sekolah-sekolah yang telah diverifikasi admin
   - Melihat informasi kebutuhan alat pendidikan setiap sekolah
   - Membaca detail kondisi dan prioritas kebutuhan sekolah

4. **Memberikan Donasi**
   - Memilih sekolah tujuan donasi
   - Menentukan kebutuhan spesifik yang ingin dipenuhi
   - Memasukkan jumlah atau nilai donasi
   - Memilih metode donasi (barang atau uang)

5. **Riwayat Donasi**
   - Melihat seluruh riwayat donasi yang telah dilakukan
   - Memantau status penyaluran donasi
   - Mengakses bukti dan detail setiap transaksi donasi

</details>

### 🏫 Fitur untuk Pihak Sekolah

<details>
<summary><strong>Klik untuk melihat detail fitur Pihak Sekolah</strong></summary>

Pihak Sekolah adalah pengguna yang mewakili institusi pendidikan untuk mengajukan kebutuhan dan mengelola donasi masuk.

1. **Login**
   - Masuk ke sistem untuk mengelola data kebutuhan dan dokumen sekolah

2. **Mengajukan Kebutuhan**
   - Menambahkan daftar alat pendidikan yang dibutuhkan
   - Mencantumkan detail seperti nama alat, jumlah, dan prioritas
   - Memberikan deskripsi kondisi dan urgensi kebutuhan
   - Menetapkan status untuk setiap kebutuhan

3. **Mengelola Kebutuhan**
   - Edit informasi kebutuhan yang sudah diajukan
   - Menghapus kebutuhan yang sudah terpenuhi
   - Update status kebutuhan secara real-time

4. **Mengunggah Dokumen**
   - Upload dokumen pendukung untuk proses verifikasi
   - Menyediakan surat pernyataan resmi dari sekolah
   - Mengunggah foto kondisi sekolah dan fasilitas yang ada
   - Upload izin operasional atau dokumen legal lainnya

5. **Riwayat Donasi Masuk**
   - Melihat seluruh donasi yang diterima sekolah
   - Memantau status penyaluran dari donatur
   - Mengakses detail donatur dan bantuan yang diberikan

</details>

## 🖥️ Implementasi OOP

Program EduShare dibangun dengan menerapkan lima pilar utama Object-Oriented Programming (OOP) secara komprehensif. Berikut adalah penjelasan detail penerapan setiap pilar:

### 1️⃣ Encapsulation (Enkapsulasi)

<details>
<summary><strong>Klik untuk melihat implementasi Encapsulation</strong></summary>

Encapsulation adalah prinsip menyembunyikan detail implementasi internal dan hanya menyediakan akses melalui method publik. Dalam EduShare, semua class entity mengimplementasikan enkapsulasi dengan baik.

**Implementasi:**

Semua atribut class dibuat **private** dan diakses melalui **getter/setter** public.

**Contoh di Class `Pengguna.java`:**

```java
public abstract class Pengguna implements Serializable {
    
    // ENCAPSULATION: Private fields
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
    
    // ... getter dan setter lainnya
}
```

**Lokasi Penerapan:**
- `model/Pengguna.java` - baris 19-50 (deklarasi atribut private) dan baris 60-120 (getter/setter)
- `model/Admin.java` - baris 19-25 dan baris 50-58
- `model/Donatur.java` - baris 19-30 dan baris 55-75
- `model/PihakSekolah.java` - baris 25-35 dan baris 65-85
- `model/Sekolah.java` - baris 20-60 dan baris 100-200
- `model/Kebutuhan.java` - baris 20-55 dan baris 85-150
- `model/Donasi.java` - baris 20-50 dan baris 80-140
- `model/Dokumen.java` - baris 20-45 dan baris 75-120

**Manfaat Encapsulation dalam EduShare:**
- Data sensitif seperti password terlindungi dari akses langsung
- Validasi dapat dilakukan di dalam setter sebelum mengubah nilai
- Perubahan implementasi internal tidak mempengaruhi kode yang menggunakan class tersebut
- Meningkatkan maintainability dan keamanan aplikasi

</details>

### 2️⃣ Inheritance (Pewarisan)

<details>
<summary><strong>Klik untuk melihat implementasi Inheritance</strong></summary>

Inheritance memungkinkan class child mewarisi atribut dan method dari class parent. EduShare menggunakan inheritance untuk membuat hierarki pengguna yang efisien.

**Implementasi:**

Class `Pengguna` sebagai parent class (superclass) yang diturunkan ke tiga child class: `Admin`, `Donatur`, dan `PihakSekolah`. Menggunakan strategi **JOINED** untuk inheritance di JPA.

**Contoh di Class Parent `Pengguna.java`:**

```java
@Entity
@Table(name = "pengguna")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "jenis_user", discriminatorType = DiscriminatorType.STRING)
public abstract class Pengguna implements Serializable {
    
    // Atribut yang diwariskan ke semua child class
    private Integer idPengguna;
    private String nama;
    private String email;
    private String password;
    private String noHp;
    private String alamat;
    
    // Abstract method yang harus diimplementasi child class
    public abstract String getRole();
    
    // Method yang bisa di-override
    public String getInfoLengkap() {
        return String.format("Nama: %s, Email: %s, No HP: %s", 
                           nama, email, noHp);
    }
}
```

**Contoh Child Class `Admin.java`:**

```java
@Entity
@Table(name = "admin")
@DiscriminatorValue("Admin")
@PrimaryKeyJoinColumn(name = "id_pengguna")
public class Admin extends Pengguna {  // INHERITANCE
    
    // Atribut tambahan khusus Admin
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sekolah", nullable = false)
    private Sekolah sekolah;
    
    public Admin() {
        super();  // Memanggil constructor parent
    }
    
    // Implementasi method abstract dari parent
    @Override
    public String getRole() {
        return "Admin";
    }
    
    // Override method dari parent class
    @Override
    public String getInfoLengkap() {
        String infoSekolah = (sekolah != null) ? 
                            sekolah.getNamaSekolah() : "Tidak ada sekolah";
        return super.getInfoLengkap() + 
               String.format(", Role: Admin, Sekolah: %s", infoSekolah);
    }
}
```

**Contoh Child Class `Donatur.java`:**

```java
@Entity
@Table(name = "donatur")
@DiscriminatorValue("Donatur")
@PrimaryKeyJoinColumn(name = "id_pengguna")
public class Donatur extends Pengguna {  // INHERITANCE
    
    // Atribut tambahan khusus Donatur
    @Column(name = "total_donasi")
    private Integer totalDonasi;
    
    // Implementasi method abstract
    @Override
    public String getRole() {
        return "Donatur";
    }
}
```

**Contoh Child Class `PihakSekolah.java`:**

```java
@Entity
@Table(name = "pihak_sekolah")
@DiscriminatorValue("Sekolah")
@PrimaryKeyJoinColumn(name = "id_pengguna")
public class PihakSekolah extends Pengguna {  // INHERITANCE
    
    // Atribut tambahan khusus Pihak Sekolah
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sekolah", nullable = false)
    private Sekolah sekolah;
    
    @Column(name = "jabatan", nullable = false, length = 50)
    private String jabatan;
    
    // Implementasi method abstract
    @Override
    public String getRole() {
        return "Pihak Sekolah";
    }
    
    @Override
    public String getInfoLengkap() {
        String infoSekolah = (sekolah != null) ? 
                            sekolah.getNamaSekolah() : "Tidak ada sekolah";
        return super.getInfoLengkap() +
                String.format(", Role: Pihak Sekolah, Jabatan: %s, Sekolah: %s", 
                            jabatan, infoSekolah);
    }
}
```

**Inheritance pada Layer DAO:**

```java
// Parent: GenericDAOImpl<T, ID>
public abstract class GenericDAOImpl<T, ID> implements GenericDAO<T, ID> {
    protected EntityManager entityManager;
    
    public T save(T entity) { /* implementasi */ }
    public T update(T entity) { /* implementasi */ }
    // ... method CRUD lainnya
}

// Child: PenggunaDAOImpl extends GenericDAOImpl
public class PenggunaDAOImpl extends GenericDAOImpl<Pengguna, Integer> 
                             implements PenggunaDAO {
    
    public PenggunaDAOImpl() {
        super(Pengguna.class);  // Memanggil constructor parent
    }
    
    // Method tambahan khusus Pengguna
    public Optional<Pengguna> findByEmail(String email) {
        // implementasi spesifik
    }
}
```

**Lokasi Penerapan:**
- **Model Layer:**
  - `model/Pengguna.java` (parent) - baris 18-25 (anotasi inheritance)
  - `model/Admin.java` (child) - baris 13 (extends Pengguna)
  - `model/Donatur.java` (child) - baris 13 (extends Pengguna)
  - `model/PihakSekolah.java` (child) - baris 15 (extends Pengguna)

- **DAO Layer:**
  - `dao/GenericDAOImpl.java` (parent) - seluruh class
  - `dao/PenggunaDAOImpl.java` (child) - baris 15 (extends GenericDAOImpl)
  - `dao/AdminDAOImpl.java` (child) - baris 14 (extends GenericDAOImpl)
  - `dao/DonaturDAOImpl.java` (child) - baris 14 (extends GenericDAOImpl)
  - `dao/PihakSekolahDAOImpl.java` (child) - baris 16 (extends GenericDAOImpl)
  - `dao/SekolahDAOImpl.java` (child) - baris 14 (extends GenericDAOImpl)
  - `dao/KebutuhanDAOImpl.java` (child) - baris 16 (extends GenericDAOImpl)
  - `dao/DonasiDAOImpl.java` (child) - baris 16 (extends GenericDAOImpl)
  - `dao/DokumenDAOImpl.java` (child) - baris 14 (extends GenericDAOImpl)

**Manfaat Inheritance dalam EduShare:**
- Menghindari duplikasi kode untuk atribut dan method yang sama
- Semua jenis pengguna memiliki atribut dasar yang sama (nama, email, password)
- Mudah menambahkan jenis pengguna baru tanpa mengubah kode existing
- Operasi CRUD generic dapat digunakan oleh semua entity melalui GenericDAO

</details>

### 3️⃣ Abstraction (Abstraksi)

<details>
<summary><strong>Klik untuk melihat implementasi Abstraction</strong></summary>

Abstraction adalah proses menyembunyikan detail implementasi dan hanya menampilkan fungsionalitas penting. Dalam EduShare, abstraksi diterapkan melalui abstract class dan interface.

**Implementasi:**

**A. Abstract Class `Pengguna`:**

```java
public abstract class Pengguna implements Serializable {
    
    // Atribut yang diwariskan
    private Integer idPengguna;
    private String nama;
    private String email;
    // ... atribut lainnya
    
    // ABSTRACTION: Abstract method - harus diimplementasi oleh subclass
    public abstract String getRole();
    
    // Concrete method yang bisa digunakan langsung atau di-override
    public String getInfoLengkap() {
        return String.format("Nama: %s, Email: %s, No HP: %s", 
                           nama, email, noHp);
    }
}
```

Method `getRole()` adalah abstract method yang **wajib** diimplementasikan oleh setiap child class. Setiap jenis pengguna akan memiliki role yang berbeda.

**B. Abstract Class `GenericDAOImpl`:**

```java
public abstract class GenericDAOImpl<T, ID> implements GenericDAO<T, ID> {
    
    protected EntityManager entityManager;
    private final Class<T> entityClass;
    
    // Constructor dengan parameter entityClass
    public GenericDAOImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EduSharePU");
        this.entityManager = emf.createEntityManager();
    }
    
    // Implementasi method CRUD yang bisa langsung digunakan child class
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
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Error saat menyimpan entity: " + e.getMessage(), e);
        }
    }
    
    @Override
    public Optional<T> findById(ID id) {
        try {
            T entity = entityManager.find(entityClass, id);
            return Optional.ofNullable(entity);
        } catch (Exception e) {
            throw new RuntimeException("Error saat mencari entity: " + e.getMessage(), e);
        }
    }
    
    // ... method CRUD lainnya
}
```

Class ini abstract karena setiap entity memiliki query khusus yang berbeda, namun operasi CRUD dasar sudah disediakan untuk semua child class.

**Implementasi di Child Class:**

```java
// Admin mengimplementasi method abstract getRole()
@Override
public String getRole() {
    return "Admin";
}

// Donatur mengimplementasi method abstract getRole()
@Override
public String getRole() {
    return "Donatur";
}

// PihakSekolah mengimplementasi method abstract getRole()
@Override
public String getRole() {
    return "Pihak Sekolah";
}
```

**Lokasi Penerapan:**
- `model/Pengguna.java` - baris 48 (abstract method `getRole()`)
- `model/Admin.java` - baris 33-36 (implementasi `getRole()`)
- `model/Donatur.java` - baris 35-38 (implementasi `getRole()`)
- `model/PihakSekolah.java` - baris 45-48 (implementasi `getRole()`)
- `dao/GenericDAOImpl.java` - baris 17 (abstract class declaration), seluruh class berisi implementasi abstrak

**Manfaat Abstraction dalam EduShare:**
- Memaksa setiap jenis pengguna mendefinisikan role mereka sendiri
- Menyembunyikan kompleksitas operasi database di balik method sederhana
- Membuat kode lebih modular dan mudah dipahami
- Child class fokus pada implementasi spesifik tanpa perlu tahu detail teknis

</details>

### 4️⃣ Polymorphism (Polimorfisme)

<details>
<summary><strong>Klik untuk melihat implementasi Polymorphism</strong></summary>

Polymorphism memungkinkan satu method memiliki banyak bentuk implementasi. EduShare menerapkan polymorphism dalam bentuk method overriding dan method overloading.

**Implementasi:**

**A. Method Overriding - Method yang sama, implementasi berbeda:**

**Method `getInfoLengkap()` di berbagai class:**

```java
// Di parent class Pengguna
public String getInfoLengkap() {
    return String.format("Nama: %s, Email: %s, No HP: %s", nama, email, noHp);
}

// Di class Admin - OVERRIDE dengan menambah info sekolah
@Override
public String getInfoLengkap() {
    String infoSekolah = (sekolah != null) ? 
                        sekolah.getNamaSekolah() : "Tidak ada sekolah";
    return super.getInfoLengkap() + 
           String.format(", Role: Admin, Sekolah: %s", infoSekolah);
}

// Di class PihakSekolah - OVERRIDE dengan menambah info jabatan
@Override
public String getInfoLengkap() {
    String infoSekolah = (sekolah != null) ? 
                        sekolah.getNamaSekolah() : "Tidak ada sekolah";
    return super.getInfoLengkap() +
            String.format(", Role: Pihak Sekolah, Jabatan: %s, Sekolah: %s", 
                        jabatan, infoSekolah);
}
```

**Method `getRole()` - Polymorphic behavior:**

```java
// Di class Admin
@Override
public String getRole() {
    return "Admin";
}

// Di class Donatur
@Override
public String getRole() {
    return "Donatur";
}

// Di class PihakSekolah
@Override
public String getRole() {
    return "Pihak Sekolah";
}
```

**Penggunaan Polymorphism di AuthenticationService:**

```java
public LoginResult login(String email, String password) {
    // Polymorphism: variabel bertipe Pengguna bisa menampung Admin, Donatur, atau PihakSekolah
    Optional<Admin> admin = adminDAO.authenticate(email, password);
    if (admin.isPresent()) {
        currentUser = admin.get();  // Admin adalah Pengguna
        return new LoginResult(true, "Login berhasil sebagai Admin", admin.get());
    }
    
    Optional<Donatur> donatur = donaturDAO.authenticate(email, password);
    if (donatur.isPresent()) {
        currentUser = donatur.get();  // Donatur adalah Pengguna
        return new LoginResult(true, "Login berhasil sebagai Donatur", donatur.get());
    }
    
    Optional<PihakSekolah> pihakSekolah = pihakSekolahDAO.authenticate(email, password);
    if (pihakSekolah.isPresent()) {
        currentUser = pihakSekolah.get();  // PihakSekolah adalah Pengguna
        return new LoginResult(true, "Login berhasil sebagai Pihak Sekolah", pihakSekolah.get());
    }
    
    return new LoginResult(false, "Email atau password salah", null);
}

// Method yang menggunakan polymorphism
public static Pengguna getCurrentUser() {
    return currentUser;  // Bisa return Admin, Donatur, atau PihakSekolah
}

// Type checking dengan polymorphism
public boolean isAdmin() {
    return currentUser instanceof Admin;
}

public boolean isDonatur() {
    return currentUser instanceof Donatur;
}

public boolean isPihakSekolah() {
    return currentUser instanceof PihakSekolah;
}
```

**B. Polymorphism di Dashboard - Dynamic Dashboard Loading:**

```java
// Di LoginForm.java
Pengguna user = authController.login(email, password);

if (user != null) {
    // POLYMORPHISM: Switch berdasarkan role yang di-override tiap class
    switch (user.getRole()) {
        case "Admin" -> {
            new DashboardAdmin().setVisible(true);
            this.dispose();
        }
        case "Donatur" -> {
            new DashboardDonatur(user).setVisible(true);
            this.dispose();
        }
        case "Pihak Sekolah" -> {
            new DashboardSekolah().setVisible(true);
            this.dispose();
        }
        default -> JOptionPane.showMessageDialog(null, "Role tidak dikenal!");
    }
}
```

**C. Polymorphism di DAO Layer:**

```java
// GenericDAO interface - kontrak untuk semua DAO
public interface GenericDAO<T, ID> {
    T save(T entity);
    T update(T entity);
    void delete(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
}

// Setiap DAO mengimplementasi interface yang sama dengan behavior berbeda
public class AdminDAOImpl extends GenericDAOImpl<Admin, Integer> implements AdminDAO {
    // Implementasi spesifik untuk Admin
    public Optional<Admin> authenticate(String email, String password) {
        // Query khusus Admin
    }
}

public class DonaturDAOImpl extends GenericDAOImpl<Donatur, Integer> implements DonaturDAO {
    // Implementasi spesifik untuk Donatur
    public Optional<Donatur> authenticate(String email, String password) {
        // Query khusus Donatur
    }
}
```

**Lokasi Penerapan:**
- **Method Overriding:**
  - `model/Pengguna.java` - baris 50-53 (method `getInfoLengkap()` original)
  - `model/Admin.java` - baris 38-43 (override `getInfoLengkap()`)
  - `model/PihakSekolah.java` - baris 50-56 (override `getInfoLengkap()`)
  - `model/Admin.java` - baris 33-36 (override `getRole()`)
  - `model/Donatur.java` - baris 35-38 (override `getRole()`)
  - `model/PihakSekolah.java` - baris 45-48 (override `getRole()`)

- **Penggunaan Polymorphis:**
  - `service/AuthenticationService.java` - baris 35-75 (method `login()`)
  - `service/AuthenticationService.java` - baris 85-105 (type checking methods)
  - `view/auth/LoginForm.java` - baris 95-115 (dynamic dashboard loading)

**Manfaat Polymorphism dalam EduShare:**
- Satu variabel `Pengguna` bisa menampung Admin, Donatur, atau PihakSekolah
- Memudahkan penambahan jenis pengguna baru tanpa mengubah banyak kode
- Dashboard yang ditampilkan otomatis sesuai dengan role pengguna
- Kode lebih fleksibel dan mudah di-maintain

</details>

### 5️⃣ Interface

<details>
<summary><strong>Klik untuk melihat implementasi Interface</strong></summary>

Interface mendefinisikan kontrak atau blueprint yang harus diikuti oleh class yang mengimplementasikannya. EduShare menggunakan interface untuk mendefinisikan operasi database yang standar.

**Implementasi:**

**A. Interface `GenericDAO<T, ID>` - Blueprint untuk operasi CRUD:**

```java
/**
 * Generic DAO Interface - Base interface untuk semua DAO
 * Menerapkan INTERFACE (Pilar OOP ke-5)
 * Menyediakan operasi CRUD dasar untuk semua entity
 */
public interface GenericDAO<T, ID> {
    
    /**
     * Menyimpan entity baru ke database (CREATE)
     */
    T save(T entity);
    
    /**
     * Mengupdate entity yang sudah ada (UPDATE)
     */
    T update(T entity);
    
    /**
     * Menghapus entity dari database (DELETE)
     */
    void delete(T entity);
    
    /**
     * Menghapus entity berdasarkan ID
     */
    void deleteById(ID id);
    
    /**
     * Mencari entity berdasarkan ID (READ)
     */
    Optional<T> findById(ID id);
    
    /**
     * Mengambil semua entity dari database
     */
    List<T> findAll();
    
    /**
     * Mengambil entity dengan pagination
     */
    List<T> findAll(int offset, int limit);
    
    /**
     * Menghitung total entity di database
     */
    long count();
    
    /**
     * Mengecek apakah entity dengan ID tertentu ada
     */
    boolean existsById(ID id);
    
    /**
     * Refresh entity dari database (reload dari DB)
     */
    void refresh(T entity);
    
    /**
     * Detach entity dari persistence context
     */
    void detach(T entity);
}
```

**B. Interface untuk Operasi Entity yang Spesifik:**

**Interface `PenggunaDAO`:**

```java
public interface PenggunaDAO extends GenericDAO<Pengguna, Integer> {
    
    /**
     * Mencari pengguna berdasarkan email
     */
    Optional<Pengguna> findByEmail(String email);
    
    /**
     * Autentikasi pengguna
     */
    Optional<Pengguna> findByEmailAndPassword(String email, String password);
    
    /**
     * Mencari pengguna berdasarkan jenis
     */
    List<Pengguna> findByJenisUser(String jenisUser);
}
```

**Interface `AdminDAO`:**

```java
public interface AdminDAO extends GenericDAO<Admin, Integer> {
    
    /**
     * Mencari admin berdasarkan email
     */
    Optional<Admin> findByEmail(String email);
    
    /**
     * Autentikasi admin
     */
    Optional<Admin> authenticate(String email, String password);
    
    /**
     * Mencari admin berdasarkan sekolah
     */
    List<Admin> findBySekolah(Sekolah sekolah);
    
    /**
     * Mencari admin berdasarkan ID sekolah
     */
    List<Admin> findByIdSekolah(Integer idSekolah);
    
    /**
     * Menghitung jumlah admin per sekolah
     */
    long countBySekolah(Integer idSekolah);
}
```

**Interface `DonaturDAO`:**

```java
public interface DonaturDAO extends GenericDAO<Donatur, Integer> {
    
    Optional<Donatur> findByEmail(String email);
    Optional<Donatur> authenticate(String email, String password);
    List<Donatur> findByNama(String nama);
    long countTotalDonatur();
}
```

**Interface `SekolahDAO`:**

```java
public interface SekolahDAO extends GenericDAO<Sekolah, Integer> {
    
    Optional<Sekolah> findByNamaSekolah(String namaSekolah);
    Optional<Sekolah> findByNpsn(String npsn);
    List<Sekolah> findByKategori(String kategori);
    List<Sekolah> findByKabupaten(String kabupaten);
    List<Sekolah> searchByKeyword(String keyword);
    long countByKategori(String kategori);
}
```

**Interface `KebutuhanDAO`:**

```java
public interface KebutuhanDAO extends GenericDAO<Kebutuhan, Integer> {
    
    List<Kebutuhan> findBySekolah(Sekolah sekolah);
    List<Kebutuhan> findByStatus(String status);
    List<Kebutuhan> findByPrioritas(String prioritas);
    List<Kebutuhan> findKebutuhanTersedia();
    boolean delete(Integer idKebutuhan);
}
```

**Interface `DonasiDAO`:**

```java
public interface DonasiDAO extends GenericDAO<Donasi, Integer> {
    
    List<Donasi> findByDonatur(Donatur donatur);
    List<Donasi> findByKebutuhan(Kebutuhan kebutuhan);
    List<Donasi> findByStatus(String status);
    long getTotalDonasiByDonatur(Integer idDonatur);
    List<Donasi> findDonasiMenungguVerifikasi();
}
```

**C. Implementasi Interface di Class:**

```java
// Class mengimplementasi interface dan wajib menyediakan semua method
public class AdminDAOImpl extends GenericDAOImpl<Admin, Integer> 
                          implements AdminDAO {  // INTERFACE
    
    public AdminDAOImpl() {
        super(Admin.class);
    }
    
    // Implementasi method dari interface AdminDAO
    @Override
    public Optional<Admin> findByEmail(String email) {
        try {
            TypedQuery<Admin> query = entityManager.createQuery(
                "SELECT a FROM Admin a WHERE a.email = :email", 
                Admin.class
            );
            query.setParameter("email", email);
            Admin admin = query.getSingleResult();
            return Optional.of(admin);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
    
    @Override
    public Optional<Admin> authenticate(String email, String password) {
        try {
            TypedQuery<Admin> query = entityManager.createQuery(
                "SELECT a FROM Admin a WHERE a.email = :email AND a.password = :password", 
                Admin.class
            );
            query.setParameter("email", email);
            query.setParameter("password", password);
            Admin admin = query.getSingleResult();
            return Optional.of(admin);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
    
    @Override
    public List<Admin> findBySekolah(Sekolah sekolah) {
        return findByIdSekolah(sekolah.getIdSekolah());
    }
    
    // ... implementasi method lainnya dari interface
}
```

**Lokasi Penerapan:**

- **Interface Definitions:**
  - `dao/GenericDAO.java` - seluruh class (baris 1-85)
  - `dao/PenggunaDAO.java` - seluruh class
  - `dao/AdminDAO.java` - seluruh class
  - `dao/DonaturDAO.java` - seluruh class
  - `dao/PihakSekolahDAO.java` - seluruh class
  - `dao/SekolahDAO.java` - seluruh class
  - `dao/KebutuhanDAO.java` - seluruh class
  - `dao/DonasiDAO.java` - seluruh class
  - `dao/DokumenDAO.java` - seluruh class

- **Interface Implementations:**
  - `dao/GenericDAOImpl.java` - baris 17 (implements GenericDAO<T, ID>)
  - `dao/AdminDAOImpl.java` - baris 14 (implements AdminDAO)
  - `dao/DonaturDAOImpl.java` - baris 14 (implements DonaturDAO)
  - `dao/PihakSekolahDAOImpl.java` - baris 16 (implements PihakSekolahDAO)
  - `dao/SekolahDAOImpl.java` - baris 14 (implements SekolahDAO)
  - `dao/KebutuhanDAOImpl.java` - baris 16 (implements KebutuhanDAO)
  - `dao/DonasiDAOImpl.java` - baris 16 (implements DonasiDAO)
  - `dao/DokumenDAOImpl.java` - baris 14 (implements DokumenDAO)

**Manfaat Interface dalam EduShare:**
- Mendefinisikan kontrak yang jelas untuk operasi database
- Memudahkan testing dengan mock implementations
- Memastikan konsistensi method signature di semua DAO
- Mendukung loose coupling - implementasi bisa diganti tanpa mengubah code yang menggunakan interface
- Memfasilitasi dependency injection dan clean architecture

</details>

### 📊 Ringkasan Penerapan OOP

<details>
<summary><strong>Klik untuk melihat ringkasan lengkap</strong></summary>

| Pilar OOP | Lokasi Utama | Implementasi |
|-----------|--------------|--------------|
| **Encapsulation** | Semua class di `model/` | Private attributes dengan public getter/setter |
| **Inheritance** | `model/Pengguna.java` dan turunannya<br>`dao/GenericDAOImpl.java` dan turunannya | Class hierarchy dengan extends keyword |
| **Abstraction** | `model/Pengguna.java`<br>`dao/GenericDAOImpl.java` | Abstract class dengan abstract methods |
| **Polymorphism** | `service/AuthenticationService.java`<br>Semua class di `model/` | Method overriding, dynamic binding |
| **Interface** | Semua interface di `dao/` | Interface definitions dengan implements keyword |

**Distribusi Penerapan OOP:**

- **Model Layer** (Semua): Semua lima pilar OOP
  - Encapsulation: Semua attributes private
  - Inheritance: Hierarki Pengguna
  - Abstraction: Abstract class Pengguna
  - Polymorphism: Override methods
  - Interface: Implements Serializable

- **DAO Layer** (Semua): Semua lima pilar OOP
  - Encapsulation: Protected EntityManager
  - Inheritance: Extends GenericDAOImpl
  - Abstraction: Abstract GenericDAOImpl
  - Polymorphism: Override CRUD operations
  - Interface: Implements DAO interfaces

- **Service Layer** (80%): Fokus pada Polymorphism dan Encapsulation
  - Business logic yang memanfaatkan polymorphic behavior
  - Encapsulation untuk service operations

- **Controller Layer** (60%): Fokus pada Polymorphism
  - Dynamic dashboard loading berdasarkan user role

- **View Layer** (40%): Minimal OOP
  - Menggunakan hasil polymorphic operations dari layer lain

</details>

## 📂 Struktur Packages

Program EduShare diorganisir dalam struktur package yang terstruktur dan mengikuti prinsip MVC (Model-View-Controller) dengan separation of concerns yang jelas.

<details>
<summary><strong>Klik untuk melihat struktur lengkap</strong></summary>

### 📦 Source Packages

```
src/
│
├── conf/
│   └── (default package)
│       └── Persistence.xml           # Konfigurasi JPA (backup)
│
├── META-INF/
│   └── persistence.xml               # Konfigurasi JPA/Hibernate
│
├── controller/
│   └── AuthController.java           # Controller untuk autentikasi
│
├── dao/                              # Data Access Object Layer
│   ├── GenericDAO.java               # Interface DAO generic
│   ├── GenericDAOImpl.java           # Implementasi DAO generic
│   │
│   ├── AdminDAO.java                 # Interface DAO Admin
│   ├── AdminDAOImpl.java             # Implementasi DAO Admin
│   │
│   ├── DokumenDAO.java               # Interface DAO Dokumen
│   ├── DokumenDAOImpl.java           # Implementasi DAO Dokumen
│   │
│   ├── DonasiDAO.java                # Interface DAO Donasi
│   ├── DonasiDAOImpl.java            # Implementasi DAO Donasi
│   │
│   ├── DonaturDAO.java               # Interface DAO Donatur
│   ├── DonaturDAOImpl.java           # Implementasi DAO Donatur
│   │
│   ├── KebutuhanDAO.java             # Interface DAO Kebutuhan
│   ├── KebutuhanDAOImpl.java         # Implementasi DAO Kebutuhan
│   │
│   ├── PenggunaDAO.java              # Interface DAO Pengguna
│   ├── PenggunaDAOImpl.java          # Implementasi DAO Pengguna
│   │
│   ├── PihakSekolahDAO.java          # Interface DAO Pihak Sekolah
│   ├── PihakSekolahDAOImpl.java      # Implementasi DAO Pihak Sekolah
│   │
│   ├── SekolahDAO.java               # Interface DAO Sekolah
│   └── SekolahDAOImpl.java           # Implementasi DAO Sekolah
│
├── model/                            # Entity/Model Layer
│   ├── Pengguna.java                 # Entity parent untuk user
│   ├── Admin.java                    # Entity Admin (extends Pengguna)
│   ├── Donatur.java                  # Entity Donatur (extends Pengguna)
│   ├── PihakSekolah.java             # Entity Pihak Sekolah (extends Pengguna)
│   ├── Sekolah.java                  # Entity Sekolah
│   ├── Kebutuhan.java                # Entity Kebutuhan
│   ├── Donasi.java                   # Entity Donasi
│   └── Dokumen.java                  # Entity Dokumen
│
├── resources/                        # Resources untuk UI
│   ├── gambar/
│   │   ├── Rectangle 7 (1).png
│   │   ├── Rectangle 7 copycopy.png
│   │   └── logo edushare.png
│   │
│   └── icon/
│       ├── icons8-add-20.png
│       ├── icons8-edit-20.png
│       ├── icons8-refresh-20.png
│       └── icons8-trash-20.png
│
├── service/                          # Business Logic Layer
│   ├── AuthenticationService.java    # Service untuk autentikasi
│   ├── DonasiService.java            # Service untuk donasi
│   ├── DokumenService.java           # Service untuk dokumen
│   ├── KebutuhanService.java         # Service untuk kebutuhan
│   ├── PenggunaService.java          # Service untuk pengguna
│   └── SekolahService.java           # Service untuk sekolah
│
├── util/                             # Utility Classes
│   └── JPAUtil.java                  # Utility untuk JPA EntityManager
│
└── view/                             # View/Presentation Layer
    ├── MainApp.java                  # Main entry point aplikasi
    │
    ├── auth/                         # Package untuk authentication UI
    │   ├── LoginForm.java            # Form login
    │   └── RegisterForm.java         # Form registrasi
    │
    ├── dashboard/                    # Package untuk dashboard UI
    │   ├── DashboardAdmin.java       # Dashboard untuk Admin
    │   ├── DashboardDonatur.java     # Dashboard untuk Donatur
    │   └── DashboardSekolah.java     # Dashboard untuk Pihak Sekolah
    │
    ├── dialog/                       # Package untuk dialog windows
    │   ├── DialogDetailDonasi.java   # Dialog detail donasi
    │   ├── DialogDetailKebutuhan.java # Dialog detail kebutuhan
    │   ├── DialogDetailSekolah.java  # Dialog detail sekolah
    │   ├── DialogManajemenDonatur.java # Dialog manajemen donatur
    │   ├── DialogManajemenSekolah.java # Dialog manajemen sekolah
    │   ├── DialogTambahKebutuhan.java # Dialog tambah kebutuhan
    │   └── DialogTambahSekolah.java  # Dialog tambah sekolah
    │
    └── panel/                        # Package untuk panel components
        ├── PanelDaftarSekolah.java   # Panel daftar sekolah
        ├── PanelDashboardAdmin.java  # Panel dashboard admin
        ├── PanelDashboardDonatur.java # Panel dashboard donatur
        ├── PanelDashboardSekolah.java # Panel dashboard sekolah
        ├── PanelDonasi.java          # Panel manajemen donasi
        ├── PanelDonasiSaya.java      # Panel donasi saya
        ├── PanelDonatur.java         # Panel manajemen donatur
        ├── PanelKebutuhan.java       # Panel manajemen kebutuhan
        ├── PanelRiwayatDonasi.java   # Panel riwayat donasi
        ├── PanelSekolah.java         # Panel manajemen sekolah
        └── PanelVerifikasiKebutuhan.java # Panel verifikasi kebutuhan
```

### 🔍 Penjelasan Setiap Package

**1. `conf/` dan `META-INF/`**
- Berisi file konfigurasi JPA/Hibernate
- `persistence.xml` mendefinisikan koneksi database dan class-class entity

**2. `controller/`**
- Mengatur alur kontrol aplikasi
- Menghubungkan view dengan service layer
- Handle user input dan routing

**3. `dao/` (Data Access Object)**
- Bertanggung jawab untuk semua operasi database
- Menggunakan pattern Interface dan Implementation
- Generic DAO untuk operasi CRUD standar
- Specific DAO untuk query khusus setiap entity

**4. `model/`**
- Berisi entity classes yang merepresentasikan tabel database
- Menggunakan JPA annotations (@Entity, @Table, dll)
- Menerapkan inheritance untuk hierarki Pengguna

**5. `resources/`**
- Menyimpan aset visual (gambar, icon)
- Terorganisir dalam sub-folder berdasarkan jenis

**6. `service/`**
- Business logic layer
- Mengatur aturan bisnis dan validasi
- Menjembatani antara controller dan DAO

**7. `util/`**
- Utility classes dan helper functions
- JPAUtil untuk mengelola EntityManager

**8. `view/`**
- Presentation layer (GUI)
- Diorganisir berdasarkan fungsi:
  - `auth/`: Form login dan register
  - `dashboard/`: Dashboard utama per role
  - `dialog/`: Pop-up windows untuk detail dan input
  - `panel/`: Komponen panel yang dapat di-swap di dashboard

</details>

### 📐 Arsitektur MVC

<details>
<summary><strong>Klik untuk melihat diagram arsitektur</strong></summary>

<div align="center">
<img src="https://github.com/user-attachments/assets/aa52f730-14bc-44f6-ac7d-f2e19666d587" alt="Header Atas" width="100%">
</div>

**Alur Data:**
1. **User** berinteraksi dengan **View** (LoginForm, Dashboard, dll)
2. **View** memanggil **Controller** (AuthController)
3. **Controller** memanggil **Service** (AuthenticationService, DonasiService, dll)
4. **Service** melakukan business logic dan memanggil **DAO**
5. **DAO** berinteraksi dengan **Database** melalui **JPA/Hibernate**
6. **Data** dikembalikan melalui layer yang sama secara terbalik

</details>

## 📚 Libraries

Program EduShare menggunakan berbagai library dan framework untuk mendukung fungsionalitas aplikasi.

<details>
<summary><strong>Klik untuk melihat daftar libraries</strong></summary>

### 🔧 Core Libraries

| Library | Versi | Fungsi |
|---------|-------|--------|
| **JDK** | 24 (Default) | Java Development Kit untuk menjalankan aplikasi |

### 🎨 UI/GUI Libraries

| Library | Versi | Fungsi |
|---------|-------|--------|
| **AbsoluteLayout.jar** | - | Layout manager untuk positioning komponen GUI secara absolut |
| **flatlaf-3.6.2.jar** | 3.6.2 | Modern Look and Feel untuk Java Swing dengan tampilan flat design |

### 🗄️ Database & Persistence Libraries

| Library | Versi | Fungsi |
|---------|-------|--------|
| **mysql-connector-j** | 8.0.33 | JDBC driver untuk koneksi ke database MySQL |
| **hibernate-core** | 6.3.1.Final | Framework ORM utama untuk mapping object-relational |
| **hibernate-entitymanager** | 5.6.15.Final | Entity Manager untuk mengelola entity lifecycle |
| **hibernate-commons-annotations** | 7.0.3.Final | Anotasi umum untuk Hibernate |
| **jakarta.persistence-api** | 3.1.0 | API standar JPA untuk persistence operations |

### 🔄 Libraries Pendukung

| Library | Versi | Fungsi |
|---------|-------|--------|
| **antlr4-runtime** | 4.13.0 | Runtime library untuk parser generator (dependency Hibernate) |
| **byte-buddy** | 1.14.9 | Code generation library untuk dynamic proxy (Hibernate) |
| **classmate** | 1.5.1 | Library untuk type introspection |
| **jandex** | 3.2.3 | Indexing library untuk annotated classes |
| **jboss-logging** | 3.6.1.Final | Logging framework untuk Hibernate |

### 🔌 Jakarta EE Libraries

| Library | Versi | Fungsi |
|---------|-------|--------|
| **jakarta.activation-api** | 2.1.4 | API untuk handling data types |
| **jakarta.inject-api** | 2.0.1 | Dependency injection API |
| **jakarta.transaction-api** | 2.0.1 | Transaction management API |
| **jakarta.xml.bind-api** | 4.0.4 | XML binding API |

### 📦 JAXB Libraries

| Library | Versi | Fungsi |
|---------|-------|--------|
| **jaxb-core** | 4.0.2 | Core JAXB implementation |
| **jaxb-runtime** | 4.0.2 | Runtime for JAXB |
| **istack-commons-runtime** | 4.2.0 | Common utilities untuk JAXB |
| **txw2** | 4.0.2 | Typed XML writer |

### 🎯 Konfigurasi Utama

**File `persistence.xml`:**
```xml
<persistence-unit name="EduSharePU" transaction-type="RESOURCE_LOCAL">
    <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
    
    <!-- Entity Classes -->
    <class>model.Pengguna</class>
    <class>model.Admin</class>
    <class>model.Donatur</class>
    <class>model.PihakSekolah</class>
    <class>model.Sekolah</class>
    <class>model.Kebutuhan</class>
    <class>model.Donasi</class>
    <class>model.Dokumen</class>
    
    <properties>
        <!-- Database Connection -->
        <property name="jakarta.persistence.jdbc.driver" 
                  value="com.mysql.cj.jdbc.Driver"/>
        <property name="jakarta.persistence.jdbc.url" 
                  value="jdbc:mysql://127.0.0.1:3306/edushare"/>
        <property name="jakarta.persistence.jdbc.user" value="root"/>
        <property name="jakarta.persistence.jdbc.password" value=""/>
        
        <!-- Hibernate Settings -->
        <property name="hibernate.dialect" 
                  value="org.hibernate.dialect.MySQLDialect"/>
        <property name="hibernate.hbm2ddl.auto" value="update"/>
        <property name="hibernate.show_sql" value="true"/>
    </properties>
</persistence-unit>
```

</details>

## 📲 Penggunaan Program

Berikut adalah panduan lengkap penggunaan program EduShare untuk setiap jenis pengguna.

### 🔐 Autentikasi

<details>
<summary><strong>1. Halaman Login</strong></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/18abd24b-b5b3-4d39-8d49-a3b80753bcb4" alt="" width="500px">
</div>

Halaman login adalah tampilan pertama yang muncul saat program dijalankan. Pada halaman ini:
- **Field Email**: Masukkan email yang telah terdaftar
- **Field Password**: Masukkan password akun
- **Tombol "Masuk"**: Klik untuk login ke sistem
- **Link "Ingin Berdonasi? Daftar sekarang"**: Klik untuk membuka form registrasi

**Validasi Login:**
- Sistem akan memvalidasi email dan password
- Jika berhasil, akan diarahkan ke dashboard sesuai role pengguna
- Jika gagal, akan muncul dialog error

</details>

<details>
<summary><strong>2. Dialog Login Berhasil</strong></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/e0929a06-8803-4b9e-9784-e7af0de2f557" alt="" width="500px">
</div>

Setelah berhasil login, sistem akan menampilkan dialog konfirmasi:
- Menampilkan pesan "Login berhasil sebagai [Jenis User][Nama Pengguna]"
- Klik "OK" untuk melanjutkan ke dashboard

</details>

<details>
<summary><strong>3. Dialog Login Gagal</strong></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/dc01d0fc-65a9-4249-8bc2-3d2716282fec" alt="" width="500px">
</div>

Jika login gagal, sistem akan menampilkan pesan error:
- "Email atau password salah"
- Klik "OK" dan coba login kembali dengan benar

</details>

<details>
<summary><strong>4. Halaman Register</strong></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/10f55db4-067b-46cb-8c50-08e0affad0f8" alt="" width="500px">
</div>

Halaman registrasi untuk membuat akun baru sebagai Donatur:
- **Field Nama**: Masukkan nama lengkap
- **Field Email**: Masukkan email valid (akan digunakan untuk login)
- **Field Password**: Masukkan password
- **Field Nomor HP**: Masukkan nomor telepon aktif
- **Klik "Selanjutnya"** untuk melanjutkan ke dialog input alamat

</details>

<details>
<summary><strong>5. Register - Input Alamat</strong></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/4b9da1cd-0c19-4099-9c4d-d3f5b82b0583" alt="" width="500px">
</div>

Setelah mengisi data pribadi, lanjutkan dengan mengisi alamat:
- **Field Alamat**: Masukkan alamat lengkap tempat tinggal
- **Tombol "OK"**: Klik untuk menyelesaikan proses registrasi
- **Tombol "Cancel"**: Kembali ke halaman sebelumnya jika ingin mengubah data

</details>

<details>
<summary><strong>6. Register Berhasil</strong></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/84c989f4-8c00-4f52-b6b7-0b2ac70290ba" alt="" width="500px">
</div>

Setelah berhasil mendaftar, sistem akan mengalihkan ke laman login.

</details>

### 👨‍💼 Dashboard dan Fitur Admin

<details>
<summary><strong>1. Dashboard Admin</strong></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/e3661763-2f10-4a8b-b480-e83423b3c808" alt="" width="500px">
</div>

Dashboard utama untuk Admin menampilkan:
- **Menu Sidebar Kiri**:
  - Dashboard: Tampilan ringkasan statistik
  - Manajemen Sekolah: Kelola data sekolah
  - Manajemen Donatur: Kelola data donatur
  - Manajemen Donasi: Verifikasi dan kelola donasi
  - Manajemen Dokumen: Verifikasi dokumen sekolah
- **Panel Konten**: Menampilkan statistik overview sistem
- **Informasi User**: Nama dan role admin di kanan atas
- **Tombol Logout**: Untuk keluar dari sistem

</details>

<details>
<summary><strong>2. Manajemen Sekolah</strong></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/2e095afc-b78f-492e-8a63-ad3d8f99d31e" alt="" width="500px">
</div>

Halaman untuk mengelola data sekolah:
- **Tabel Daftar Sekolah**: Menampilkan semua sekolah yang terdaftar dengan informasi:
  - ID Sekolah
  - Nama Sekolah
  - NPSN (Nomor Pokok Sekolah Nasional)
  - Alamat
  - Kabupaten
  - Kategori
  - Status Kebutuhan
- **Tombol "Tambah Sekolah"**: Menambah sekolah baru
- **Tombol "Lihat Detail"**: Melihat informasi lengkap sekolah
- **Tombol "Edit"**: Mengubah data sekolah
- **Tombol "Hapus"**: Menghapus data sekolah (dengan konfirmasi)
- **Tombol "Refresh"**: Memuat ulang data dari database

</details>

<details>
<summary><strong>3. Tambah Sekolah</strong></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/c9e849d0-e521-40cb-8c34-2960fd935e76" alt="" width="500px">
</div>

Dialog untuk menambahkan sekolah baru:
- **Field Nama Sekolah**: Nama lengkap sekolah
- **Field NPSN**: Nomor Pokok Sekolah Nasional (unique)
- **Field Alamat Lengkap**: Alamat detail sekolah
- **Field Kecamatan**: Nama kecamatan
- **Field Kabupaten**: Nama kabupaten/kota
- **Dropdown Kategori**: Pilih kategori (SD, SMP, SMA, SMK)
- **Fields Data Akun Sekolah**: informasi mengenai akun pihak sekolah untuk keperluan login ke sistem
- **Tombol "Tambah Sekolah"**: Menyimpan data sekolah baru dan Pihak sekolah baru

</details>

<details>
<summary><strong>4. Detail Sekolah</strong></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/c4a8085e-c749-4635-9422-9623f3dc0b5a" alt="" width="500px">
</div>

Dialog menampilkan informasi detail sekolah:
- Semua informasi sekolah ditampilkan dalam format read-only
- Dapat melihat data lengkap sekolah

</details>

<details>
<summary><strong>5. Konfirmasi Hapus Sekolah</strong></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/0f4a2068-fb3b-42f5-b7c4-561e4b80ceff" alt="" width="500px">
</div>

Dialog konfirmasi sebelum menghapus data sekolah:
- Pesan: "Apakah Anda yakin ingin menghapus sekolah ini?"
- **Tombol "Yes"**: Konfirmasi penghapusan
- **Tombol "No"**: Batalkan penghapusan


</details>

<details>
<summary><strong>6. Refresh Data Sekolah</strong></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/b2bb3e3c-9a26-4dd4-a0b3-1aa1e404fc5d" alt="" width="500px">
</div>

Setelah klik tombol refresh:
- Data tabel dimuat ulang dari database
- Menampilkan data terbaru

</details>

<details>
<summary><strong>7. Manajemen Donatur</strong></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/a000b6e7-2d54-4954-a23d-083564616e4f" alt="" width="500px">
</div>

Halaman untuk mengelola data donatur:
- **Tabel Daftar Donatur**: Menampilkan semua donatur terdaftar
- **Informasi yang ditampilkan**:
  - ID Donatur
  - Nama Donatur
  - Email
  - Nomor HP
  - Total Donasi
- **Tombol "Lihat Detail"**: Melihat profil lengkap donatur

</details>

<details>
<summary><strong>8. Profil Donatur</strong></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/2e5075fa-1092-423b-b9fd-611f44fbd849" alt="" width="500px">
</div>

Dialog menampilkan profil lengkap donatur:
- Informasi pribadi donatur
- Alamat lengkap
- Total donasi yang telah diberikan
- **Tombol "Oke"**: Menutup dialog

</details>

<details>
<summary><strong>9. Manajemen Donasi</strong></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/96188ef9-40d7-4913-b952-cf18acbc94dc" alt="" width="500px">
</div>

Halaman untuk verifikasi dan kelola donasi:
- **Tabel Daftar Donasi**: Menampilkan semua transaksi donasi
- **Informasi yang ditampilkan**:
  - ID Donasi
  - Nama Donatur
  - Sekolah Tujuan
  - Jumlah Donasi
  - Metode Pembayaran
  - Status Donasi
- **Tombol "Lihat Detail"**: Melihat detail lengkap donasi

</details>

<details>
<summary><strong>10. Detail Donasi</strong></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/9d0d9b3e-3b50-4628-8bcf-7bc0c7e35ae6" alt="" width="500px">
</div>

Dialog menampilkan detail lengkap donasi:
- Informasi donatur
- Informasi sekolah penerima
- Jumlah donasi
- Tanggal donasi
- Status donasi

</details>

<details>
<summary><strong>11. Manajemen Dokumen</strong></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/834481d4-15fe-4f1c-9c3e-ed487c81fc92" alt="" width="500px">
</div>

Halaman untuk verifikasi dokumen sekolah:
- **Tabel Daftar Dokumen**: Menampilkan dokumen yang diunggah sekolah
- **Informasi yang ditampilkan**:
  - ID Dokumen
  - Nama Sekolah
  - Nama Kebutuhan
  - Jumlah
  - Prioritas
  - Status Verifikasi
- **Tombol "Lihat Detail"**: Melihat dokumen dan kebutuhan terkait

</details>

<details>
<summary><strong>12. Detail Kebutuhan Sekolah</strong></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/4c56d452-65c8-46e7-80cd-c679b8028e62" alt="" width="500px">
</div>

Dialog menampilkan detail kebutuhan sekolah yang terkait dengan dokumen:
- Daftar kebutuhan sekolah
- Status setiap kebutuhan
- Prioritas kebutuhan
- Jumlah yang dibutuhkan
- Keterangan tambahan
- Lihat Dokumen Pendukung
- **Tombol "Verifikasi"**: Verifikasi kebutuhan
- **Tombol "Tolak"**: Dokumen ditolak

</details>

### 💝 Dashboard dan Fitur Donatur

<details>
<summary><strong>1. Dashboard Donatur</strong></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/3cbdf320-478a-484d-bfbd-1f5844455dcd" alt="" width="500px">
</div>

Dashboard utama untuk Donatur menampilkan:
- **Menu Sidebar Kiri**:
  - Dashboard: Tampilan ringkasan aktivitas
  - Daftar Sekolah: Menjelajahi sekolah untuk didonasi
  - Riwayat Donasi: Melihat riwayat semua donasi
- **Panel Konten**: Menampilkan ringkasan donasi dan aktivitas terbaru
- **Informasi User**: Nama donatur di kanan atas
- **Tombol Logout**: Keluar dari sistem

</details>

<details>
<summary><strong>2. Daftar Sekolah</strong></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/50730c33-7a9e-400d-b411-22ee019c6615" alt="" width="500px">
</div>

Halaman untuk menjelajahi sekolah yang membutuhkan donasi:
- **Tabel Daftar Sekolah**: Menampilkan sekolah yang telah diverifikasi
- **Informasi yang ditampilkan**:
  - Nama Sekolah
  - Nama Alat
  - Jumlah
  - Keterangan
- **Tombol "Lihat Detail"**: Melihat detail sekolah dan kebutuhan

</details>

<details>
<summary><strong>3. Detail Sekolah</strong></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/327db182-11bd-442a-a563-ce20c27da86b" alt="" width="500px">
</div>

Dialog menampilkan detail lengkap sekolah:
- Informasi sekolah (Nama, NPSN, Alamat, Kategori)
- **Daftar Kebutuhan**: Tabel menampilkan kebutuhan sekolah dengan:
  - Nama Alat
  - Jumlah yang dibutuhkan
  - Jumlah yang sudah terkumpul
  - Prioritas
  - Deskripsi
  - Status
- **Tombol "Donasi"**: Untuk memberikan donasi

</details>

<details>
<summary><strong>4. Input Nilai Donasi</strong></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/dc7740b2-df11-4f36-9c3c-6597d5466363" alt="" width="500px">
</div>

Dialog untuk input detail donasi:
- **Informasi Kebutuhan**: Nama alat dan deskripsi
- **Field Jumlah Donasi**: Masukkan nilai atau jumlah donasi
- **Dropdown Metode Pembayaran**: Pilih metode (Transfer Bank, QRIS, Cash)
- **Tombol "OK"**: Konfirmasi donasi
- **Tombol "Cancel"**: Batalkan proses donasi

</details>

<details>
<summary><strong>5. Dialog Konfirmasi Donasi</strong></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/d52520b5-83d7-4c8b-90cb-92b6125dc552" alt="" width="500px">
</div>

Setelah klik tombol donasi, muncul dialog konfirmasi:
- Pesan: "Donasi Anda sedang menunggu konfirmasi dari Admin"
- **Tombol "OK"**: Kembali ke daftar sekolah

</details>

<details>
<summary><strong>6. Riwayat Donasi</strong></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/cb220315-b191-4766-9e00-705a98d692e6" alt="" width="500px">
</div>

Halaman menampilkan riwayat semua donasi yang telah dilakukan:
- **Tabel Riwayat Donasi**:
  - Sekolah Tujuan
  - Kebutuhan yang Dipenuhi
  - Jumlah Donasi
  - Tanggal Donasi
  - Status Donasi (Pending/Diverifikasi/Selesai/Ditolak)

</details>

### 🏫 Dashboard dan Fitur Pihak Sekolah

<details>
<summary><strong>1. Dashboard Pihak Sekolah</strong></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/84d5452a-1a9f-467d-a267-737ddd6413d9" alt="" width="500px">
</div>

Dashboard utama untuk Pihak Sekolah:
- **Menu Sidebar Kiri**:
  - Dashboard: Ringkasan data sekolah
  - Manajemen Kebutuhan: Kelola kebutuhan sekolah
  - Riwayat Donasi: Donasi yang diterima
- **Panel Konten**: Statistik kebutuhan dan donasi
- **Informasi Sekolah**: Nama sekolah dan jabatan user
- **Tombol Logout**: Keluar dari sistem

</details>

<details>
<summary><strong>2. Manajemen Kebutuhan</strong></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/5a0d4f97-8083-4828-a441-979647d18907" alt="" width="500px">
</div>

Halaman untuk mengelola kebutuhan sekolah:
- **Tabel Daftar Kebutuhan**:
  - ID Kebutuhan
  - Nama Kebutuhan
  - Jumlah
  - Status
  - Prioritas
  - Deskripsi
- **Tombol "Buat Kebutuhan"**: Menambah kebutuhan baru
- **Tombol "Edit"**: Mengubah data kebutuhan
- **Tombol "Hapus"**: Menghapus kebutuhan

</details>

<details>
<summary><strong>3. Tambah Kebutuhan</strong></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/ef4cfdc7-4b3e-4365-8cc2-31b9aa47224c" alt="" width="500px">
</div>

Dialog untuk menambahkan kebutuhan baru:
- **Field Nama Alat**: Nama alat pendidikan yang dibutuhkan
- **Field Jumlah**: Jumlah alat yang dibutuhkan
- **Dropdown Prioritas**: Pilih tingkat prioritas
- **Field Deskripsi**: Penjelasan detail kebutuhan
- **Total Kebutuhan(Dalam Rupiah)**: Total Kebutuhan sekolah dalam bentuk uang
- **Target Donasi(Dalam Rupiah)**: Target donasi yang diperlukan sekolah, apakah sepenuhnya perlu donasi atau ada uang yang sudah terkumpul
- **Field Upload Dokumen**: Dokumen Pendukung agar dapat diverifikasi admin
- **Tombol "Ajukan Kebutuhan"**: Menyimpan kebutuhan baru
- **Tombol "Batal"**: Membatalkan penambahan

</details>

<details>
<summary><strong>4. Dialog Berhasil Menambah Kebutuhan</strong></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/6c5ce17e-d033-456c-b9cb-70662da51009" alt="" width="500px">
</div>

Setelah berhasil menambah kebutuhan:
- Dialog konfirmasi: "Kebutuhan berhasil ditambahkan!"
- Data kebutuhan telah tersimpan
- **Tombol "OK"**: Kembali ke halaman manajemen kebutuhan

</details>

<details>
<summary><strong>5. Edit Kebutuhan</strong></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/6d12e20b-6bc4-46bd-b6aa-c90d5fe2ee90" alt="" width="500px">
</div>

Dialog untuk mengedit kebutuhan yang sudah ada:
- Form ter-isi dengan data kebutuhan yang dipilih
- Dapat mengubah semua field
- **Tombol "Simpan Perubahan"**: Menyimpan perubahan
- **Tombol "Batal"**: Membatalkan edit

</details>

<details>
<summary><strong>6. Konfirmasi Hapus Kebutuhan</strong></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/29f152da-beea-448d-b7a4-7bbdbc8c396c" alt="" width="500px">
</div>

Dialog konfirmasi sebelum menghapus kebutuhan:
- Pesan: "Apakah Anda yakin ingin menghapus kebutuhan ini?"
- **Tombol "Yes"**: Konfirmasi penghapusan
- **Tombol "No"**: Batalkan penghapusan

</details>

<details>
<summary><strong>7. Dialog Berhasil Menghapus</strong></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/86b807a4-42dc-410c-8647-c88256f3f2eb" alt="" width="500px">
</div>

Setelah berhasil menghapus kebutuhan:
- Dialog konfirmasi: "Kebutuhan berhasil dihapus!"
- Data telah dihapus dari database
- **Tombol "OK"**: Kembali ke halaman manajemen kebutuhan

</details>

<details>
<summary><strong>8. Riwayat Donasi Masuk</strong></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/aedc9fcc-9347-449a-8d3e-502ddc4da845" alt="" width="500px">
</div>

Halaman menampilkan riwayat donasi yang diterima sekolah:
- **Tabel Riwayat Donasi**:
  - Nama Sekolah
  - Kebutuhan yang Dipenuhi
  - Jumlah Donasi
  - Tanggal Donasi
  - Status Donasi

</details>

### 🚪 Logout

<details>
<summary><strong>1. Konfirmasi Logout</strong></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/9c848ca2-1c5a-43a7-9c2f-ef807cb887f7" alt="" width="500px">
</div>

Saat klik tombol Logout:
- Dialog konfirmasi: "Apakah Anda yakin ingin keluar?"
- **Tombol "Yes"**: Konfirmasi logout
- **Tombol "No"**: Batalkan dan tetap menggunakan sistem

</details>

<details>
<summary><strong>2. Dialog Logout Berhasil</strong></summary>

<div align="center">
  <img src="https://github.com/user-attachments/assets/66c090e8-032b-4f97-9fb7-f9bfcb3e333c" alt="" width="500px">
</div>

Setelah konfirmasi logout:
- Dialog: "Logout berhasil. Sampai jumpa, [Nama User]"
- Session user dihapus dari sistem
- **Tombol "OK"**: Kembali ke halaman login

</details>

---
> [!NOTE]
> **🎉 Terimakasih! 🎉**
> Kepada abang, mba, dan semua rekan yang sudah membantu kami dalam menyelesaikan proyek ini 🙏

---
[⬆️ Kembali ke Awal](#top)
<img src="https://github.com/user-attachments/assets/20faea5d-0da9-42e6-94df-a6f9a6879d0f" alt="Footer Readme" width="100%">
