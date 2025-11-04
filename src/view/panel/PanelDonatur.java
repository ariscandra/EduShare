package view.panel;

import model.Donatur;
import service.PenggunaService;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import view.dialog.DialogManajemenDonatur;
import java.util.List;

/**
 * PanelDonatur
 * Panel untuk management dan monitoring donatur
 * Terintegrasi penuh dengan Service Layer
 * 
 * Features:
 * - View list semua donatur
 * - View detail donatur
 * - Search donatur by nama
 * - View riwayat donasi per donatur
 * 
 * @author EduShare Team
 */
public class PanelDonatur extends javax.swing.JPanel {
    
    // Service Layer
    private final PenggunaService penggunaService;
    
    // Data Model
    private List<Donatur> daftarDonatur;
    private Donatur donaturTerpilih;
    
    // Table Configuration
    private static final String[] COLUMN_NAMES = {
        "ID", "Nama", "Email", "No HP", "Sekolah", "Total Donasi"
    };

    /**
     * Constructor
     */
    public PanelDonatur() {
        this.penggunaService = new PenggunaService();
        
        initComponents();
        setupTable();
        loadDataDonatur();
        
        // Setup listeners
        setupTableListeners();
    }
    
    /**
     * Setup table configuration
     */
    private void setupTable() {
        // Set custom table model
        DefaultTableModel model = new DefaultTableModel(COLUMN_NAMES, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Readonly table
            }
        };
        jTable1.setModel(model);
        
        // Set column widths untuk optimal display
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);   // ID
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);  // Nama
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(180);  // Email
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(100);  // No HP
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(120);  // Sekolah
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(80);   // Total Donasi
        
        // Set selection mode
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        
        // Better visual
        jTable1.setRowHeight(25);
        jTable1.getTableHeader().setReorderingAllowed(false);
    }
    
    /**
     * Setup table event listeners
     */
    private void setupTableListeners() {
        // Double-click untuk view detail
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    viewDetailDonatur();
                }
            }
        });
        
        // Selection listener untuk update donaturTerpilih
        jTable1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                updateSelectedDonatur();
            }
        });
    }
    
    /**
     * Load data donatur dari database
     */
    private void loadDataDonatur() {
        try {
            // Clear existing data
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            
            // Load fresh data from service layer
            daftarDonatur = penggunaService.getAllDonatur();
            
            // Populate table
            for (Donatur donatur : daftarDonatur) {
                Object[] row = new Object[6];
                row[0] = donatur.getIdPengguna();
                row[1] = donatur.getNama();
                row[2] = donatur.getEmail();
                row[3] = donatur.getNoHp();
                row[4] = (donatur.getSekolah() != null) ? 
                         donatur.getSekolah().getNamaSekolah() : "-";
                row[5] = donatur.getTotalDonasi();
                
                model.addRow(row);
            }
            
            // Update label
            jLabel14.setText("Donatur yang telah donasi (" + daftarDonatur.size() + " donatur)");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error loading data donatur: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Update reference donatur terpilih dari table selection
     */
    private void updateSelectedDonatur() {
        int selectedRow = jTable1.getSelectedRow();
        
        if (selectedRow >= 0 && selectedRow < daftarDonatur.size()) {
            donaturTerpilih = daftarDonatur.get(selectedRow);
            jButton10.setEnabled(true);
        } else {
            donaturTerpilih = null;
            jButton10.setEnabled(false);
        }
    }
    
    /**
     * View detail donatur (open dialog)
     */
    private void viewDetailDonatur() {
        if (donaturTerpilih == null) {
            JOptionPane.showMessageDialog(this,
                "Pilih donatur yang ingin dilihat detailnya",
                "Info",
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        try {
            // Open dialog detail donatur
            DialogManajemenDonatur dialog = new DialogManajemenDonatur(
                (java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(this),
                true,
                donaturTerpilih.getIdPengguna()
            );
            dialog.setVisible(true);
            
            // Refresh table setelah dialog ditutup (jika ada perubahan)
            loadDataDonatur();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error opening detail: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Search donatur by nama
     */
    public void searchDonatur(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            // Jika search kosong, load semua data
            loadDataDonatur();
            return;
        }
        
        try {
            // Clear table
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            
            // Search menggunakan service layer
            daftarDonatur = penggunaService.searchDonatur(keyword);
            
            // Populate dengan hasil search
            for (Donatur donatur : daftarDonatur) {
                Object[] row = new Object[6];
                row[0] = donatur.getIdPengguna();
                row[1] = donatur.getNama();
                row[2] = donatur.getEmail();
                row[3] = donatur.getNoHp();
                row[4] = (donatur.getSekolah() != null) ? 
                         donatur.getSekolah().getNamaSekolah() : "-";
                row[5] = donatur.getTotalDonasi();
                
                model.addRow(row);
            }
            
            // Update label dengan hasil search
            jLabel14.setText("Hasil pencarian '" + keyword + "' (" + 
                           daftarDonatur.size() + " donatur)");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error searching donatur: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Refresh data (public method untuk dipanggil dari parent)
     */
    public void refreshData() {
        loadDataDonatur();
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
        jButton10 = new javax.swing.JButton();

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Donatur yang telah donasi");
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

        jButton10.setBackground(new java.awt.Color(30, 136, 229));
        jButton10.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Lihat Detail");
        jButton10.setToolTipText("");
        jButton10.setBorder(null);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 500, 130, 34));

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

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // View detail donatur
        viewDetailDonatur();
    }//GEN-LAST:event_jButton10ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
