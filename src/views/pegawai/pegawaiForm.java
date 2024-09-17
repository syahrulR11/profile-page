/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.pegawai;

import java.awt.Color;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.swing.JOptionPane;
import javax.swing.JComboBox;

import database.DMLSQL;
import database.DatabaseManager;
import database.Password;
import views.layout;


/**
 *
 * @author syahrul
 */
public class pegawaiForm extends javax.swing.JPanel {
    private DatabaseManager dbManager;
    private DMLSQL dmlSql;
    private String id = null;
    public pegawaiList parent = null;
    public layout layout = null;
    private JComboBox<ComboItem> comboBox;

    /**
     * Creates new form pegawaiCreate
     */
    public pegawaiForm(String id) {
        initComponents();
        this.dbManager = new DatabaseManager();
        this.dmlSql = new DMLSQL(this.dbManager);
        setJabatanSelect();
        if (id != null) {
            this.id = id;
            setData(id);
        } else {
            hapusButton.setVisible(false);
        }
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

    private void setJabatanSelect() {
        this.comboBox = jabatanInput;
        String[] columnsToSelect = {"id","nama"};
        List<Object[]> results;
        try {
            results = dmlSql.selectData("jabatan", columnsToSelect, null, null, null, null, null, false,false);
            for (Object[] row : results) {
                comboBox.addItem(new ComboItem((String)row[1],(String)row[0]));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Query Fail. "+e.getMessage());
        }
    }

    private void setData(String id) {
        String[] columnsToSelect = {"nama","email","no_telp","jenis_kelamin","alamat","password","jabatan_id"};
        String[] conditionColumns = {"pegawai.id"};
        String[] operators = {"="};
        Object[] conditionValues = {id};
        Object[] result;
        try {
            result = dmlSql.findData("pegawai", columnsToSelect, null, null, conditionColumns, operators, conditionValues, false,false);
            String jabatan_id = (String) result[6];
            for (int i = 0; i < comboBox.getItemCount(); i++) {
                ComboItem item = comboBox.getItemAt(i);
                if (item.getValue().equals(jabatan_id)) {
                    comboBox.setSelectedItem(item);
                    break;
                }
            }
            namaInput.setText((String)result[0]);
            emailInput.setText((String)result[1]);
            notelpInput.setText((String)result[2]);
            if ("Laki-laki".equals(result[3])) {
                jkLInput.setSelected(true);
            } else {
                jkPInput.setSelected(true);
            }
            alamatInput.setText((String)result[4]);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Query Fail. "+e.getMessage());
        }
    }

    private void saveData() {
        try {
            if (this.id != null) {
                String jenis_kelamin = "";
                if (jkLInput.isSelected()) {
                    jenis_kelamin = "Laki-laki";
                } else {
                    jenis_kelamin = "Perempuan";
                }
                ComboItem jabatan = (ComboItem) comboBox.getSelectedItem();
                String[] updateColumns = {"jabatan_id","nama","email","no_telp","jenis_kelamin","alamat"};
                Object[] updateValues = {jabatan.getValue(),namaInput.getText(),emailInput.getText(),notelpInput.getText(),jenis_kelamin,alamatInput.getText()};
                if (passwordInput.getPassword().length > 0) {
                    int newSizeInsertColumns = updateColumns.length + 1;
                    String[] newInsertColumns = new String[newSizeInsertColumns];
                    System.arraycopy(updateColumns, 0, newInsertColumns, 0, updateColumns.length);
                    newInsertColumns[newSizeInsertColumns - 1] = "password";
                    updateColumns = newInsertColumns;

                    int newSizeInsertValues = updateValues.length + 1;
                    Object[] newInsertValues = new Object[newSizeInsertValues];
                    System.arraycopy(updateValues, 0, newInsertValues, 0, updateValues.length);
                    newInsertValues[newSizeInsertValues - 1] = Password.hashPassword(new String(passwordInput.getPassword()));
                    updateValues = newInsertValues;
                }
                dmlSql.updateData("pegawai", updateColumns, updateValues, this.id);
                dbManager.close();
                JOptionPane.showMessageDialog(null, "Data Berhasil Diubah!");
                pegawaiList pegawaiList = new pegawaiList();
                pegawaiList.layout = layout;
                layout.pn_main.removeAll();
                layout.pn_main.add(pegawaiList);
                layout.pn_main.repaint();
                layout.pn_main.revalidate();
            } else {
                if (passwordInput.getPassword().length == 0) {
                    JOptionPane.showMessageDialog(null, "Password Wajib Diisi!");
                } else {
                    UUID uuid = UUID.randomUUID();
                    String jenis_kelamin = "";
                    if (jkLInput.isSelected()) {
                        jenis_kelamin = "Laki-laki";
                    } else {
                        jenis_kelamin = "Perempuan";
                    }
                    ComboItem jabatan = (ComboItem) comboBox.getSelectedItem();
                    String[] insertColumns = {"id","jabatan_id","nama","email","no_telp","jenis_kelamin","alamat","password"};
                    Object[] insertValues = {uuid.toString(),jabatan.getValue(),namaInput.getText(),emailInput.getText(),notelpInput.getText(),jenis_kelamin,alamatInput.getText(),Password.hashPassword(new String(passwordInput.getPassword()))};
                    dmlSql.insertData("pegawai", insertColumns, insertValues);
                    dbManager.close();
                    JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan!");
                    pegawaiList pegawaiList = new pegawaiList();
                    pegawaiList.layout = layout;
                    layout.pn_main.removeAll();
                    layout.pn_main.add(pegawaiList);
                    layout.pn_main.repaint();
                    layout.pn_main.revalidate();
                }
            }
            parent.datatable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Query Fail. "+e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jkInputGroup = new javax.swing.ButtonGroup();
        header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        batalButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        simpanButton = new javax.swing.JButton();
        hapusButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        namaInput = new javax.swing.JTextField();
        jabatanInput = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        emailInput = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        notelpInput = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jkLInput = new javax.swing.JRadioButton();
        jkPInput = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        alamatInput = new javax.swing.JTextArea();
        passwordInput = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(227, 242, 253));

        header.setBackground(new java.awt.Color(227, 242, 253));
        header.setPreferredSize(new java.awt.Dimension(676, 39));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Pegawai Form");

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
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 425, Short.MAX_VALUE)
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
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 400));

        jLabel2.setText("Jabatan");

        simpanButton.setBackground(new java.awt.Color(40, 167, 69));
        simpanButton.setForeground(new java.awt.Color(255, 255, 255));
        simpanButton.setText("Simpan");
        simpanButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        simpanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanButtonActionPerformed(evt);
            }
        });

        hapusButton.setBackground(new java.awt.Color(220, 53, 69));
        hapusButton.setForeground(new java.awt.Color(255, 255, 255));
        hapusButton.setText("Hapus");
        hapusButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hapusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Nama");

        jLabel4.setText("Email");

        jLabel5.setText("No Telepon");

        jLabel6.setText("Jenis Kelamin");

        jkInputGroup.add(jkLInput);
        jkLInput.setText("Laki-laki");

        jkInputGroup.add(jkPInput);
        jkPInput.setText("Perempuan");

        jLabel7.setText("Alamat");

        alamatInput.setColumns(20);
        alamatInput.setRows(5);
        jScrollPane1.setViewportView(alamatInput);

        jLabel8.setText("Password");
        jLabel8.setMaximumSize(new java.awt.Dimension(120, 22));
        jLabel8.setName(""); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jabatanInput, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(namaInput))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(emailInput))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(notelpInput))
                    .addComponent(simpanButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hapusButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jkLInput)
                        .addGap(18, 18, 18)
                        .addComponent(jkPInput)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordInput)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jabatanInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(namaInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(notelpInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jkLInput)
                    .addComponent(jkPInput))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(simpanButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(hapusButton)
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
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void batalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalButtonActionPerformed
        pegawaiList pegawaiList = new pegawaiList();
        pegawaiList.layout = layout;
        layout.pn_main.removeAll();
        layout.pn_main.add(pegawaiList);
        layout.pn_main.repaint();
        layout.pn_main.revalidate();
    }//GEN-LAST:event_batalButtonActionPerformed

    private void batalButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batalButtonMouseEntered
        batalButton.setBackground(new Color(90, 98, 104));
    }//GEN-LAST:event_batalButtonMouseEntered

    private void batalButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batalButtonMouseExited
        batalButton.setBackground(new Color(108, 117, 125));
    }//GEN-LAST:event_batalButtonMouseExited

    private void hapusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusButtonActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, "Yakin ingin hapus?", "hapus", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            try {
                dmlSql.deleteData("pegawai", "id", this.id);
                dbManager.close();
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus!");
                pegawaiList pegawaiList = new pegawaiList();
                pegawaiList.layout = layout;
                layout.pn_main.removeAll();
                layout.pn_main.add(pegawaiList);
                layout.pn_main.repaint();
                layout.pn_main.revalidate();
                parent.datatable();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Query Fail. "+e.getMessage());
            }
        }
    }//GEN-LAST:event_hapusButtonActionPerformed

    private void simpanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanButtonActionPerformed
        saveData();
    }//GEN-LAST:event_simpanButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea alamatInput;
    private javax.swing.JButton batalButton;
    private javax.swing.JTextField emailInput;
    private javax.swing.JButton hapusButton;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<ComboItem> jabatanInput;
    private javax.swing.ButtonGroup jkInputGroup;
    private javax.swing.JRadioButton jkLInput;
    private javax.swing.JRadioButton jkPInput;
    private javax.swing.JTextField namaInput;
    private javax.swing.JTextField notelpInput;
    private javax.swing.JPasswordField passwordInput;
    private javax.swing.JButton simpanButton;
    // End of variables declaration//GEN-END:variables
}
