/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view.dialog;

import dao.KebutuhanDAO;
import dao.KebutuhanDAOImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import model.Kebutuhan;
import model.Sekolah;
import service.DokumenService;
import service.KebutuhanService;

/**
 *
 * @author ACER NITRO
 */
public class DialogTambahKebutuhan extends javax.swing.JDialog {
    
    private final Sekolah sekolahLogin;
    private final KebutuhanDAO kebutuhanDAO;
    private final KebutuhanService kebutuhanService;
    private Kebutuhan kebutuhanEdit;
    private File selectedFile;
    private String uploadedFilePath;
    private boolean fileUploaded = false;

    public DialogTambahKebutuhan(java.awt.Frame parent, Sekolah sekolahLogin) {
        super(parent, true);
        initComponents();
        this.sekolahLogin = sekolahLogin;
        EntityManager em = Persistence.createEntityManagerFactory("EduSharePU").createEntityManager();
        this.kebutuhanDAO = new KebutuhanDAOImpl(em);
        this.kebutuhanService = new KebutuhanService();
        this.kebutuhanEdit = null;
        setLocationRelativeTo(parent);

        // Styling
        txtDeskripsi.setBorder(new CompoundBorder(new LineBorder(new Color(200, 200, 200), 1, true),
                new EmptyBorder(5, 10, 5, 10)));
        txtJumlah.setBorder(new CompoundBorder(new LineBorder(new Color(200, 200, 200), 1, true),
                new EmptyBorder(5, 10, 5, 10)));
        txtNamaAlat.setBorder(new CompoundBorder(new LineBorder(new Color(200, 200, 200), 1, true),
                new EmptyBorder(5, 10, 5, 10)));
        txtTargetDonasi.setBorder(new CompoundBorder(new LineBorder(new Color(200, 200, 200), 1, true),
                new EmptyBorder(5, 30, 5, 10)));
        txtTotalKebutuhan.setBorder(new CompoundBorder(new LineBorder(new Color(200, 200, 200), 1, true),
                new EmptyBorder(5, 30, 5, 10)));
        ajukanBtn.setEnabled(false);
    }

    public DialogTambahKebutuhan(java.awt.Frame parent, Sekolah sekolahLogin, Kebutuhan kebutuhanEdit) {
        this(parent, sekolahLogin);
        this.kebutuhanEdit = kebutuhanEdit;

        if (this.kebutuhanEdit != null) {
            txtNamaAlat.setText(kebutuhanEdit.getNamaAlat());
            txtJumlah.setText(String.valueOf(kebutuhanEdit.getJumlah()));
            txtDeskripsi.setText(kebutuhanEdit.getDeskripsi() == null ? "" : kebutuhanEdit.getDeskripsi());
            txtTotalKebutuhan.setText(kebutuhanEdit.getTotalKebutuhan() != null ? kebutuhanEdit.getTotalKebutuhan().toString() : "");
            txtTargetDonasi.setText(kebutuhanEdit.getTargetDonasi() != null ? kebutuhanEdit.getTargetDonasi().toString() : "");

            switch (kebutuhanEdit.getPrioritas()) {
                case tinggi -> cbPrioritas.setSelectedItem("Tinggi");
                case sedang -> cbPrioritas.setSelectedItem("Sedang");
                case rendah -> cbPrioritas.setSelectedItem("Rendah");
            }

            // Cek apakah sudah ada dokumen
            DokumenService dokService = new DokumenService();
            var dokumen = dokService.findDokumenBySekolahAndKategori(
                    sekolahLogin.getIdSekolah(), "Proposal Donasi");

            if (dokumen.isPresent()) {
                String pathFile = dokumen.get().getPathFile();
                String namaFile = dokumen.get().getNamaFile();
                dokumenName.setText("📄 " + namaFile + " (dokumen sudah diupload)");
                uploadBtn.setText("Lihat Dokumen");

                // Hilangkan listener bawaan
                for (var listener : uploadBtn.getActionListeners()) {
                    uploadBtn.removeActionListener(listener);
                }

                // Tambahkan fungsi buka file
                uploadBtn.addActionListener(e -> {
                    try {
                        String fixedPath = pathFile;
                        if (fixedPath.startsWith("/") || fixedPath.startsWith("\\")) {
                            fixedPath = fixedPath.substring(1);
                        }
                        File file = new File(System.getProperty("user.dir"), fixedPath);
                        if (file.exists()) {
                            Desktop.getDesktop().open(file);
                        } else {
                            JOptionPane.showMessageDialog(this, "File tidak ditemukan di: " + file.getAbsolutePath(),
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(this, "Gagal membuka dokumen: " + ex.getMessage(),
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });
            } else {
                dokumenName.setText("Belum ada dokumen diunggah");
            }

            setTitle("Edit Kebutuhan");
            ajukanBtn.setText("Simpan Perubahan");
            ajukanBtn.setEnabled(true);
        }
    }



  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel9 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtNamaAlat = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtJumlah = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDeskripsi = new javax.swing.JTextArea();
        batalBtn = new javax.swing.JButton();
        ajukanBtn = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        uploadBtn = new javax.swing.JButton();
        dokumenName = new javax.swing.JLabel();
        cbPrioritas = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtTotalKebutuhan = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtTargetDonasi = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Ajukan Kebutuhan Alat Pendidikan");
        jPanel9.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, -1));

        txtNamaAlat.setBackground(new java.awt.Color(255, 255, 255));
        txtNamaAlat.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        txtNamaAlat.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtNamaAlat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaAlatActionPerformed(evt);
            }
        });
        jPanel9.add(txtNamaAlat, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 630, 50));

        jLabel6.setBackground(new java.awt.Color(51, 51, 51));
        jLabel6.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Nama Alat");
        jPanel9.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 70, -1));

        txtJumlah.setBackground(new java.awt.Color(255, 255, 255));
        txtJumlah.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        txtJumlah.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJumlahActionPerformed(evt);
            }
        });
        jPanel9.add(txtJumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 630, 50));

        jLabel7.setBackground(new java.awt.Color(51, 51, 51));
        jLabel7.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Jumlah");
        jPanel9.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 70, -1));

        jLabel8.setBackground(new java.awt.Color(51, 51, 51));
        jLabel8.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Prioritas");
        jPanel9.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 70, -1));

        jLabel9.setBackground(new java.awt.Color(51, 51, 51));
        jLabel9.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Deskripsi");
        jPanel9.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 70, -1));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        txtDeskripsi.setBackground(new java.awt.Color(255, 255, 255));
        txtDeskripsi.setColumns(20);
        txtDeskripsi.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        txtDeskripsi.setRows(5);
        txtDeskripsi.setBorder(null);
        jScrollPane1.setViewportView(txtDeskripsi);

        jPanel9.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 310, 150));

        batalBtn.setBackground(new java.awt.Color(255, 255, 255));
        batalBtn.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        batalBtn.setForeground(new java.awt.Color(153, 153, 153));
        batalBtn.setText("Batal");
        batalBtn.setToolTipText("");
        batalBtn.setBorder(null);
        batalBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalBtnActionPerformed(evt);
            }
        });
        jPanel9.add(batalBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 600, 130, 34));

        ajukanBtn.setBackground(new java.awt.Color(30, 136, 229));
        ajukanBtn.setFont(new java.awt.Font("Poppins", 0, 10)); // NOI18N
        ajukanBtn.setForeground(new java.awt.Color(255, 255, 255));
        ajukanBtn.setText("Ajukan Kebutuhan");
        ajukanBtn.setToolTipText("");
        ajukanBtn.setBorder(null);
        ajukanBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajukanBtnActionPerformed(evt);
            }
        });
        jPanel9.add(ajukanBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 600, 130, 34));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        uploadBtn.setBackground(new java.awt.Color(30, 136, 229));
        uploadBtn.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        uploadBtn.setForeground(new java.awt.Color(255, 255, 255));
        uploadBtn.setText("Upload Dokumen");
        uploadBtn.setToolTipText("");
        uploadBtn.setBorder(null);
        uploadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadBtnActionPerformed(evt);
            }
        });
        jPanel4.add(uploadBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, 170, 40));

        dokumenName.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        dokumenName.setForeground(new java.awt.Color(51, 51, 51));
        dokumenName.setText("Silahkan upload dokumen pendukung untuk menerima donasi");
        jPanel4.add(dokumenName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel9.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 630, 40));

        cbPrioritas.setBackground(new java.awt.Color(255, 255, 255));
        cbPrioritas.setForeground(new java.awt.Color(51, 51, 51));
        cbPrioritas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tinggi", "Sedang", "Rendah" }));
        jPanel9.add(cbPrioritas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 630, 50));

        jLabel10.setBackground(new java.awt.Color(51, 51, 51));
        jLabel10.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Rp.");
        jPanel9.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 480, 30, 30));

        jLabel16.setBackground(new java.awt.Color(51, 51, 51));
        jLabel16.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Rp.");
        jPanel9.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 400, 30, 30));

        txtTotalKebutuhan.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalKebutuhan.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        txtTotalKebutuhan.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtTotalKebutuhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalKebutuhanActionPerformed(evt);
            }
        });
        jPanel9.add(txtTotalKebutuhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 390, 300, 50));

        jLabel11.setBackground(new java.awt.Color(51, 51, 51));
        jLabel11.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Target Donasi (Dalam Rupiah)");
        jPanel9.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 450, 190, -1));

        txtTargetDonasi.setBackground(new java.awt.Color(255, 255, 255));
        txtTargetDonasi.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        txtTargetDonasi.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        txtTargetDonasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTargetDonasiActionPerformed(evt);
            }
        });
        jPanel9.add(txtTargetDonasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 470, 300, 50));

        jLabel12.setBackground(new java.awt.Color(51, 51, 51));
        jLabel12.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Total Kebutuhan (Dalam Rupiah)");
        jPanel9.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 370, 220, -1));

        jLabel13.setBackground(new java.awt.Color(51, 51, 51));
        jLabel13.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Rp.");
        jPanel9.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 399, 30, 30));

        jLabel14.setBackground(new java.awt.Color(51, 51, 51));
        jLabel14.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Rp.");
        jPanel9.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 490, 30, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNamaAlatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaAlatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaAlatActionPerformed

    private void txtJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJumlahActionPerformed

    private void batalBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalBtnActionPerformed
        // TODO add your handling code here:
        if (fileUploaded && uploadedFilePath != null) {
            try {
                File file = new File(uploadedFilePath);
                if (file.exists()) {
                    Files.delete(file.toPath());
                    System.out.println("Upload dibatalkan: " + file.getAbsolutePath());
                }
            } catch (IOException e) {
                System.err.println("Gagal hapus file: " + e.getMessage());
            }
        }
        this.dispose();
    }//GEN-LAST:event_batalBtnActionPerformed

    private void ajukanBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajukanBtnActionPerformed
        // TODO add your handling code here:
         try {
            String nama = txtNamaAlat.getText().trim();
            int jumlah = Integer.parseInt(txtJumlah.getText().trim());
            String prioritas = ((String) cbPrioritas.getSelectedItem()).toLowerCase();
            String deskripsi = txtDeskripsi.getText().trim();

            BigDecimal totalKebutuhan = new BigDecimal(txtTotalKebutuhan.getText().replaceAll("[^0-9]", ""));
            BigDecimal targetDonasi = new BigDecimal(txtTargetDonasi.getText().replaceAll("[^0-9]", ""));

            if (kebutuhanEdit == null) {
                var result = kebutuhanService.tambahKebutuhan(sekolahLogin.getIdSekolah(), nama, jumlah,
                        prioritas, deskripsi, totalKebutuhan, targetDonasi);

                JOptionPane.showMessageDialog(this, result.getMessage(),
                        result.isSuccess() ? "Sukses" : "Gagal",
                        result.isSuccess() ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.WARNING_MESSAGE);

            } else {
                var result = kebutuhanService.updateKebutuhan(kebutuhanEdit.getIdKebutuhan(), nama, jumlah,
                        prioritas, deskripsi, totalKebutuhan, targetDonasi);

                JOptionPane.showMessageDialog(this, result.getMessage(),
                        result.isSuccess() ? "Sukses" : "Gagal",
                        result.isSuccess() ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.WARNING_MESSAGE);
            }

            dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Jumlah, total kebutuhan, dan target donasi harus berupa angka!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ajukanBtnActionPerformed

    private void uploadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadBtnActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Pilih Dokumen Pendukung");
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getSelectedFile();
            String namaFile = selectedFile.getName();

            String folder = "uploads/sekolah" + sekolahLogin.getIdSekolah() + "/";
            new File(folder).mkdirs();
            String pathFile = folder + namaFile;

            try {
                Files.copy(selectedFile.toPath(), Paths.get(pathFile), StandardCopyOption.REPLACE_EXISTING);

                DokumenService dokService = new DokumenService();
                var result = dokService.uploadDokumen(sekolahLogin.getIdSekolah(), "Proposal Donasi", namaFile, pathFile);

                if (result.isSuccess()) {
                    dokumenName.setText("📄 " + namaFile + " (berhasil diupload)");
                    uploadedFilePath = pathFile;
                    fileUploaded = true;
                    ajukanBtn.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(this, result.getMessage(), "Gagal", JOptionPane.WARNING_MESSAGE);
                }

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal upload file: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_uploadBtnActionPerformed

    private void txtTotalKebutuhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalKebutuhanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalKebutuhanActionPerformed

    private void txtTargetDonasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTargetDonasiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTargetDonasiActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ajukanBtn;
    private javax.swing.JButton batalBtn;
    private javax.swing.JComboBox<String> cbPrioritas;
    private javax.swing.JLabel dokumenName;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtDeskripsi;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtNamaAlat;
    private javax.swing.JTextField txtTargetDonasi;
    private javax.swing.JTextField txtTotalKebutuhan;
    private javax.swing.JButton uploadBtn;
    // End of variables declaration//GEN-END:variables
}
