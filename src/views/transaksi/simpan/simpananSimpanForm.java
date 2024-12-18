/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.transaksi.simpan;

import java.awt.Color;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import database.DMLSQL;
import database.DatabaseManager;
import database.UserID;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import views.layout;

/**
 *
 * @author syahrul
 */
public class simpananSimpanForm extends javax.swing.JPanel {
    private DatabaseManager dbManager;
    private DMLSQL dmlSql;
    private String id_anggota;
    public simpananAnggotaList parent = null;
    public layout layout = null;
    private JComboBox<ComboItem> comboBox;

    /**
     * Creates new form pegawaiCreate
     */
    public simpananSimpanForm(String id) {
        initComponents();
        this.dbManager = new DatabaseManager();
        this.dmlSql = new DMLSQL(this.dbManager);
        setJenisSelect();
        id_anggota = id;
    }

    private static class ComboItem {
        private String label;
        private String value;

        public ComboItem(String label, String value) {
            this.label = label;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return label; // Displayed label in JComboBox
        }
    }

    private void setJenisSelect() {
        this.comboBox = jenisInput;
        String[] columnsToSelect = {"id","nama"};
        List<Object[]> results;
        try {
            results = dmlSql.selectData("jenis_simpanan", columnsToSelect, null, null, null, null, null, false,false);
            for (Object[] row : results) {
                comboBox.addItem(new ComboItem((String)row[1],(String)row[0]));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Query Fail. "+e.getMessage());
        }
    }

    private void saveData() {
        try {
            UUID uuid = UUID.randomUUID();
            ComboItem jenis = (ComboItem) comboBox.getSelectedItem();
            String[] insertColumns = {"id","id_pegawai","id_anggota","id_jenis_simpanan","tipe","ket","tanggal","jumlah_simpan"};
            Object[] insertValues = {uuid.toString(), UserID.getUserId(), id_anggota, jenis.getValue(), "Simpan", keteranganInput.getText(),new SimpleDateFormat("yyyy-MM-dd").format(tglInput.getDate()),jumlahInput.getText()};
            dmlSql.insertData("simpanan", insertColumns, insertValues);
            dbManager.close();
            print(id_anggota,Integer.parseInt(jumlahInput.getText()),keteranganInput.getText(),jenis.getValue(),new SimpleDateFormat("EEEE, dd MMMM yyyy", new Locale("id", "ID")).format(tglInput.getDate()));
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan!");
            simpananAnggotaList simpananAnggotaList = new simpananAnggotaList(id_anggota);
            simpananAnggotaList.layout = layout;
            layout.pn_main.removeAll();
            layout.pn_main.add(simpananAnggotaList);
            layout.pn_main.repaint();
            layout.pn_main.revalidate();
            parent.datatable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Query Fail. "+e.getMessage());
        }
    }
    
    private void print(String id, int amount, String note, String id_simpanan, String date) {
        try {
            var formatSymbols = new DecimalFormatSymbols();
            formatSymbols.setGroupingSeparator('.');
            formatSymbols.setDecimalSeparator(',');
            DecimalFormat decimalFormat = new DecimalFormat("Rp #,##0", formatSymbols);
            String path = "./src/report/notaSimpan.jasper";
            HashMap parameter = new HashMap();
            parameter.put("ID",id);
            parameter.put("AMOUNT", decimalFormat.format(amount));
            parameter.put("ID_SIMPANAN", id_simpanan);
            parameter.put("DATE", date);
            parameter.put("NOTE", note);
            JasperPrint print = JasperFillManager.fillReport(path,parameter,dbManager.getConnection());
            JasperViewer.viewReport(print, false);
        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "Gagal View Nota. "+err);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        batalButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        simpanButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jenisInput = new javax.swing.JComboBox<>();
        tglInput = new com.toedter.calendar.JDateChooser();
        jumlahInput = new javax.swing.JTextField();
        keteranganInput = new javax.swing.JTextField();

        setBackground(new java.awt.Color(227, 242, 253));

        header.setBackground(new java.awt.Color(227, 242, 253));
        header.setPreferredSize(new java.awt.Dimension(676, 39));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Simpan Form");

        batalButton.setBackground(new java.awt.Color(108, 117, 125));
        batalButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        batalButton.setForeground(new java.awt.Color(255, 255, 255));
        batalButton.setText("Batal");
        batalButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        batalButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                batalButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                batalButtonMouseExited(evt);
            }
        });
        batalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(batalButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(batalButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(227, 242, 253));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBackground(new java.awt.Color(227, 242, 253));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 200));

        jLabel2.setText("Keterangan");

        simpanButton.setBackground(new java.awt.Color(40, 167, 69));
        simpanButton.setForeground(new java.awt.Color(255, 255, 255));
        simpanButton.setText("Simpan");
        simpanButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        simpanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Tipe Simpanan");

        jLabel4.setText("Tanggal");

        jLabel5.setText("Jumlah");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(simpanButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jumlahInput))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tglInput, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(keteranganInput)
                            .addComponent(jenisInput, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(keteranganInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jenisInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tglInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jumlahInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(simpanButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
                    .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void simpanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanButtonActionPerformed
        saveData();
    }//GEN-LAST:event_simpanButtonActionPerformed

    private void batalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalButtonActionPerformed
        simpananAnggotaList simpananAnggotaList = new simpananAnggotaList(id_anggota);
        simpananAnggotaList.layout = layout;
        layout.pn_main.removeAll();
        layout.pn_main.add(simpananAnggotaList);
        layout.pn_main.repaint();
        layout.pn_main.revalidate();
    }//GEN-LAST:event_batalButtonActionPerformed

    private void batalButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batalButtonMouseEntered
        batalButton.setBackground(new Color(90, 98, 104));
    }//GEN-LAST:event_batalButtonMouseEntered

    private void batalButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batalButtonMouseExited
        batalButton.setBackground(new Color(108, 117, 125));
    }//GEN-LAST:event_batalButtonMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton batalButton;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JComboBox<ComboItem> jenisInput;
    private javax.swing.JTextField jumlahInput;
    private javax.swing.JTextField keteranganInput;
    private javax.swing.JButton simpanButton;
    private com.toedter.calendar.JDateChooser tglInput;
    // End of variables declaration//GEN-END:variables
}
