/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.transaksi.pinjam;

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
public class pinjamanPinjamForm extends javax.swing.JPanel {
    private DatabaseManager dbManager;
    private DMLSQL dmlSql;
    private JComboBox<ComboItem> comboBoxJenis;
    private JComboBox<ComboItem> comboBoxAnggota;
    public pinjamanList parent = null;
    public layout layout = null;

    /**
     * Creates new form pegawaiCreate
     */
    public pinjamanPinjamForm() {
        initComponents();
        this.dbManager = new DatabaseManager();
        this.dmlSql = new DMLSQL(this.dbManager);
        setJenisSelect();
        setAnggotaSelect();
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
        this.comboBoxJenis = jenisInput;
        String[] columnsToSelect = {"id","nama"};
        List<Object[]> results;
        try {
            results = dmlSql.selectData("jenis_pinjaman", columnsToSelect, null, null, null, null, null, false, false);
            for (Object[] row : results) {
                comboBoxJenis.addItem(new ComboItem((String)row[1],(String)row[0]));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Query Fail. "+e.getMessage());
        }
    }

    private void setAnggotaSelect() {
        this.comboBoxAnggota = anggotaInput;
        String[] columnsToSelect = {"id","nama"};
        List<Object[]> results;
        try {
            results = dmlSql.selectData("anggota", columnsToSelect, null, null, null, null, null, false, false);
            for (Object[] row : results) {
                comboBoxAnggota.addItem(new ComboItem((String)row[1],(String)row[0]));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Query Fail. "+e.getMessage());
        }
    }

    private void setCicilan() {
        int jumlah = Integer.parseInt(jumlahInput.getText());
        int jumlahTenor = Integer.parseInt(jumlahTenorInput.getText());
        int cicilan = (int)Math.ceil(jumlah/jumlahTenor);
        cicilan = (int)Math.ceil(cicilan+(cicilan*0.03));
        jumlahCicilanInput.setText(Integer.toString(cicilan));
    }

    private void saveData() {
        try {
            UUID uuid = UUID.randomUUID();
            ComboItem jenis = (ComboItem) comboBoxJenis.getSelectedItem();
            ComboItem anggota = (ComboItem) comboBoxAnggota.getSelectedItem();
            String[] insertColumns = {"id","id_pegawai","id_anggota","id_jenis_pinjaman","tanggal","ket","jumlah_tenor","jumlah_pinjam","jumlah_cicilan"};
            Object[] insertValues = {uuid.toString(),UserID.getUserId(),anggota.getValue(),jenis.getValue(),new SimpleDateFormat("yyyy-MM-dd").format(tglInput.getDate()),keteranganInput.getText(),jumlahTenorInput.getText(),jumlahInput.getText(),jumlahCicilanInput.getText()};
            dmlSql.insertData("pinjaman", insertColumns, insertValues);
            dbManager.close();
            print(uuid.toString(),new SimpleDateFormat("EEEE, dd MMMM yyyy",new Locale("id", "ID")).format(tglInput.getDate()));
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan!");
            pinjamanList pinjamanList = new pinjamanList();
            pinjamanList.layout = layout;
            layout.pn_main.removeAll();
            layout.pn_main.add(pinjamanList);
            layout.pn_main.repaint();
            layout.pn_main.revalidate();
            parent.datatable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Query Fail. "+e.getMessage());
        }
    }
    
    private void print(String id,String date) {
        try {
            String path = "./src/report/notaPinjam.jasper";
            HashMap parameter = new HashMap();
            parameter.put("ID",id);
            parameter.put("DATE",date);
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
        anggotaInput = new javax.swing.JComboBox<>();
        tglInput = new com.toedter.calendar.JDateChooser();
        jumlahInput = new javax.swing.JTextField();
        keteranganInput = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jenisInput = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jumlahTenorInput = new javax.swing.JTextField();
        jumlahCicilanInput = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

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
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 350));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBackground(new java.awt.Color(227, 242, 253));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 300));

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

        jLabel3.setText("Anggota");

        jLabel4.setText("Tanggal");

        jLabel5.setText("Jumlah");

        tglInput.setDateFormatString("yyyy-MM-dd");

        jumlahInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jumlahInputKeyReleased(evt);
            }
        });

        jLabel6.setText("Tipe Simpanan");

        jLabel7.setText("Jumlah Tenor");

        jumlahTenorInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jumlahTenorInputKeyReleased(evt);
            }
        });

        jumlahCicilanInput.setEditable(false);

        jLabel8.setText("Jumlah Cicilan");

        jLabel9.setText("(admin 3%)");

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
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(keteranganInput)
                            .addComponent(anggotaInput, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jenisInput, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jumlahTenorInput))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jumlahCicilanInput))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                    .addComponent(anggotaInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jenisInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tglInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jumlahInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jumlahTenorInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jumlahCicilanInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void simpanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanButtonActionPerformed
        saveData();
    }//GEN-LAST:event_simpanButtonActionPerformed

    private void batalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalButtonActionPerformed
        pinjamanList pinjamanList = new pinjamanList();
        pinjamanList.layout = layout;
        layout.pn_main.removeAll();
        layout.pn_main.add(pinjamanList);
        layout.pn_main.repaint();
        layout.pn_main.revalidate();
    }//GEN-LAST:event_batalButtonActionPerformed

    private void batalButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batalButtonMouseEntered
        batalButton.setBackground(new Color(90, 98, 104));
    }//GEN-LAST:event_batalButtonMouseEntered

    private void batalButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batalButtonMouseExited
        batalButton.setBackground(new Color(108, 117, 125));
    }//GEN-LAST:event_batalButtonMouseExited

    private void jumlahInputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlahInputKeyReleased
        setCicilan();
    }//GEN-LAST:event_jumlahInputKeyReleased

    private void jumlahTenorInputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlahTenorInputKeyReleased
        setCicilan();
    }//GEN-LAST:event_jumlahTenorInputKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<ComboItem> anggotaInput;
    private javax.swing.JButton batalButton;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JComboBox<ComboItem> jenisInput;
    private javax.swing.JTextField jumlahCicilanInput;
    private javax.swing.JTextField jumlahInput;
    private javax.swing.JTextField jumlahTenorInput;
    private javax.swing.JTextField keteranganInput;
    private javax.swing.JButton simpanButton;
    private com.toedter.calendar.JDateChooser tglInput;
    // End of variables declaration//GEN-END:variables
}
