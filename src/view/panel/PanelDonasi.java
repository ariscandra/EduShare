package view.panel;

import model.Donasi;
import model.Donatur;
import model.Kebutuhan;
import service.DonasiService;
import view.dialog.DialogDetailDonasi;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * PanelDonasi
 * Panel untuk monitoring dan management transaksi donasi
 * Terintegrasi penuh dengan Service Layer
 * 
 * Features:
 * - View all donations dengan real-time data
 * - Filter by status (pending/berhasil/gagal)
 * - View detail donasi (double-click atau button)
 * - Admin controls: Konfirmasi dan Batalkan donasi
 * - Search by donatur name
 * 
 * @author EduShare Team
 */
public class PanelDonasi extends javax.swing.JPanel {
    
    // Service Layer
    private final DonasiService donasiService;
    
    // Data Model
    private List<Donasi> daftarDonasi;
    private Donasi donasiTerpilih;
    
    // Table Configuration
    private static final String[] COLUMN_NAMES = {
        "ID", "Donatur", "Sekolah", "Jumlah", "Tanggal", "Metode", "Status"
    };

    /**
     * Constructor
     */
    public PanelDonasi() {
        this.donasiService = new DonasiService();
        
        initComponents();
        setupTable();
        loadDataDonasi();
        setupTableListeners();
    }
    
    /**
     * Setup table configuration dengan best practices
     */
    private void setupTable() {
        // Set custom table model
        DefaultTableModel model = new DefaultTableModel(COLUMN_NAMES, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Readonly table untuk data integrity
            }
        };
        jTable1.setModel(model);
        
        // Optimize column widths untuk readability
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);   // ID
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);  // Donatur
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(150);  // Sekolah
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(100);  // Jumlah
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(90);   // Tanggal
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(70);   // Metode
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(80);   // Status
        
        // UI/UX improvements
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.setRowHeight(25);
        jTable1.getTableHeader().setReorderingAllowed(false);
        
        // Disable button initially
        lihatDetailBtn.setEnabled(false);
    }
    
    /**
     * Setup table event listeners untuk interaktivitas
     */
    private void setupTableListeners() {
        // Double-click untuk quick view detail
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    viewDetailDonasi();
                }
            }
        });
        
        // Selection listener untuk update state
        jTable1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                updateSelectedDonasi();
            }
        });
    }
    
    /**
     * Load data donasi dari service layer dengan error handling
     * Best Practice: Always wrap database calls in try-catch
     */
    private void loadDataDonasi() {
        try {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0); // Clear existing data
            
            // Fetch from service layer
            daftarDonasi = donasiService.getAllDonasi();
            
            // Populate table dengan safe null handling
            for (Donasi donasi : daftarDonasi) {
                Object[] row = buildTableRow(donasi);
                model.addRow(row);
            }
            
            // Update UI label
            jLabel14.setText("Daftar donasi terbaru (" + daftarDonasi.size() + " donasi)");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error loading donasi data: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Build table row dengan proper null safety
     * Critical: Handle lazy-loaded relationships carefully
     */
    private Object[] buildTableRow(Donasi donasi) {
        Object[] row = new Object[7];
        
        row[0] = donasi.getIdDonasi();
        
        // Safe navigation untuk relasi lazy-loaded
        Donatur donatur = donasi.getDonatur();
        row[1] = (donatur != null && donatur.getNama() != null) ? 
                 donatur.getNama() : "(Anonim)";
        
        // Double-safe navigation untuk nested relationships
        Kebutuhan kebutuhan = donasi.getKebutuhan();
        String namaSekolah = "(Tidak diketahui)";
        if (kebutuhan != null && kebutuhan.getSekolah() != null && 
            kebutuhan.getSekolah().getNamaSekolah() != null) {
            namaSekolah = kebutuhan.getSekolah().getNamaSekolah();
        }
        row[2] = namaSekolah;
        
        row[3] = donasi.getJumlahDonasiFormatted();
        row[4] = donasi.getTanggalDonasi();
        row[5] = donasi.getMetodePembayaran() != null ? 
                 donasi.getMetodePembayaran().name().toUpperCase() : "-";
        row[6] = donasi.getStatusDonasi() != null ? 
                 donasi.getStatusDonasi().name().toUpperCase() : "-";
        
        return row;
    }
    
    /**
     * Update selected donasi dari table selection
     */
    private void updateSelectedDonasi() {
        int selectedRow = jTable1.getSelectedRow();
        
        if (selectedRow >= 0 && selectedRow < daftarDonasi.size()) {
            donasiTerpilih = daftarDonasi.get(selectedRow);
            lihatDetailBtn.setEnabled(true);
        } else {
            donasiTerpilih = null;
            lihatDetailBtn.setEnabled(false);
        }
    }
    
    /**
     * View detail donasi (open dialog)
     */
    private void viewDetailDonasi() {
        if (donasiTerpilih == null) {
            JOptionPane.showMessageDialog(this,
                "Pilih donasi yang ingin dilihat detailnya",
                "Info",
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        try {
            DialogDetailDonasi dialog = new DialogDetailDonasi(
                javax.swing.SwingUtilities.getWindowAncestor(this),
                donasiTerpilih
            );
            dialog.setVisible(true);
            
            // Refresh table setelah dialog ditutup (jika ada perubahan status)
            loadDataDonasi();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error opening detail: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Filter donasi by status
     * Public method untuk dipanggil dari parent dashboard
     */
    public void filterByStatus(String status) {
        try {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            
            List<Donasi> filteredList;
            if ("pending".equalsIgnoreCase(status)) {
                filteredList = donasiService.getDonasiPending();
            } else if ("berhasil".equalsIgnoreCase(status)) {
                filteredList = donasiService.getDonasiBerhasil();
            } else {
                filteredList = donasiService.getAllDonasi();
            }
            
            daftarDonasi = filteredList;
            
            for (Donasi donasi : filteredList) {
                Object[] row = buildTableRow(donasi);
                model.addRow(row);
            }
            
            jLabel14.setText("Donasi (" + status + ") - " + filteredList.size() + " donasi");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error filtering data: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Konfirmasi donasi (admin action)
     * Business Critical: Only pending donations can be confirmed
     */
    public void konfirmasiDonasi() {
        if (donasiTerpilih == null) {
            JOptionPane.showMessageDialog(this,
                "Pilih donasi yang akan dikonfirmasi",
                "Info",
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Business rule validation
        if (donasiTerpilih.getStatusDonasi() != Donasi.StatusDonasi.pending) {
            JOptionPane.showMessageDialog(this,
                "Hanya donasi dengan status 'pending' yang dapat dikonfirmasi",
                "Peringatan",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this,
            "Konfirmasi donasi ini?\n" +
            "Donatur: " + donasiTerpilih.getDonatur().getNama() + "\n" +
            "Jumlah: " + donasiTerpilih.getJumlahDonasiFormatted(),
            "Konfirmasi Donasi",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                DonasiService.DonasiResult result = 
                    donasiService.konfirmasiDonasi(donasiTerpilih.getIdDonasi());
                
                if (result.isSuccess()) {
                    JOptionPane.showMessageDialog(this,
                        result.getMessage(),
                        "Sukses",
                        JOptionPane.INFORMATION_MESSAGE);
                    loadDataDonasi(); // Refresh
                } else {
                    JOptionPane.showMessageDialog(this,
                        result.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                    "Error konfirmasi donasi: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Batalkan donasi (admin action dengan alasan)
     * Includes proper error handling and user feedback
     */
    public void batalkanDonasi() {
        if (donasiTerpilih == null) {
            JOptionPane.showMessageDialog(this,
                "Pilih donasi yang akan dibatalkan",
                "Info",
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Business rule validation
        if (donasiTerpilih.getStatusDonasi() != Donasi.StatusDonasi.pending) {
            JOptionPane.showMessageDialog(this,
                "Hanya donasi dengan status 'pending' yang dapat dibatalkan",
                "Peringatan",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String alasan = JOptionPane.showInputDialog(this,
            "Masukkan alasan pembatalan:",
            "Batalkan Donasi",
            JOptionPane.QUESTION_MESSAGE);
        
        if (alasan != null && !alasan.trim().isEmpty()) {
            try {
                DonasiService.DonasiResult result = 
                    donasiService.batalkanDonasi(donasiTerpilih.getIdDonasi(), alasan);
                
                if (result.isSuccess()) {
                    JOptionPane.showMessageDialog(this,
                        result.getMessage(),
                        "Sukses",
                        JOptionPane.INFORMATION_MESSAGE);
                    loadDataDonasi(); // Refresh
                } else {
                    JOptionPane.showMessageDialog(this,
                        result.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                    "Error membatalkan donasi: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Refresh data - public untuk dipanggil dari parent
     */
    public void refreshData() {
        loadDataDonasi();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel8 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lihatDetailBtn = new javax.swing.JButton();

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Daftar donasi terbaru");
        jPanel8.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel8.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 640, -1));

        lihatDetailBtn.setBackground(new java.awt.Color(30, 136, 229));
        lihatDetailBtn.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        lihatDetailBtn.setForeground(new java.awt.Color(255, 255, 255));
        lihatDetailBtn.setText("Lihat Detail");
        lihatDetailBtn.setToolTipText("");
        lihatDetailBtn.setBorder(null);
        lihatDetailBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lihatDetailBtnActionPerformed(evt);
            }
        });
        jPanel8.add(lihatDetailBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 500, 130, 34));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lihatDetailBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lihatDetailBtnActionPerformed
        // View detail donasi
        viewDetailDonasi();
    }//GEN-LAST:event_lihatDetailBtnActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel14;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton lihatDetailBtn;
    // End of variables declaration//GEN-END:variables
}
