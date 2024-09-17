/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.transaksi.pinjam;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import database.DMLSQL;
import database.DatabaseManager;
import views.layout;

/**
 *
 * @author syahrul
 */
public class pinjamanList extends javax.swing.JPanel {
    private DatabaseManager dbManager;
    private DMLSQL dmlSql;
    private DefaultTableModel tableModel;
    public layout layout = null;

    /**
     * Creates new form simpananAnggotaList
     */
    public pinjamanList() {
        initComponents();
        this.dbManager = new DatabaseManager();
        this.dmlSql = new DMLSQL(this.dbManager);
        datatable();
    }

    private static class EvenRowRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // Mengubah background baris genap menjadi light gray
            if (row % 2 == 0) {
                renderer.setBackground(new Color(248, 249, 250));
                renderer.setForeground(new Color(52, 58, 64));
            } else {
                renderer.setBackground(new Color(227, 242, 253));
                renderer.setForeground(new Color(52, 58, 64));
            }

            return renderer;
        }
    }

    public void datatable() {
        Object[] Baris = {"ID","Pembuat (pegawai)","Anggota","Jenis","Keterangan","Tanggal","Tenor","Jumlah","Cicilan","Cicilan Dibayar"};
        tableModel = new DefaultTableModel(null,Baris);
        String search = cariInput.getText();
        try {
            String[] columnsToSelect = {"pinjaman.id","pegawai.nama","anggota.nama","jenis_pinjaman.nama","pinjaman.ket","pinjaman.tanggal","pinjaman.jumlah_tenor","pinjaman.jumlah_pinjam","pinjaman.jumlah_cicilan","COALESCE(COUNT(angsur_pinjaman.id), 0)"};
            String[] joinTable = {"jenis_pinjaman","pegawai","anggota","angsur_pinjaman"};
            String[] joinCondition = {"pinjaman.id_jenis_pinjaman = jenis_pinjaman.id","pinjaman.id_pegawai = pegawai.id","pinjaman.id_anggota = anggota.id","pinjaman.id = angsur_pinjaman.id_pinjaman"};
            String[] conditionColumns = {"pegawai.nama","anggota.nama","pinjaman.tanggal"};
            String[] operators = {"LIKE","LIKE","="};
            Object[] conditionValues = {"%"+search+"%","%"+search+"%",search};
            List<Object[]> results = dmlSql.selectData("pinjaman", columnsToSelect, joinTable, joinCondition, conditionColumns, operators, conditionValues, true, true);
            for (Object[] row : results) {
                tableModel.addRow(row);
            }
            table.setModel(tableModel);
            dbManager.close();

            TableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    label.setBackground(new Color(0, 123, 255)); // Mengubah background header
                    label.setForeground(Color.WHITE); // Mengubah warna teks header
                    label.setFont(new Font("Arial", Font.BOLD, 14)); // Mengubah font header
                    label.setHorizontalAlignment(SwingConstants.LEADING); // Mengubah perataan teks header
                    return label;
                }
            };

            // Menerapkan custom header renderer ke setiap kolom
            for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
                table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
            }

            table.setDefaultRenderer(Object.class, new EvenRowRenderer());

            TableColumnModel columnModel = table.getColumnModel();
            TableColumn column = columnModel.getColumn(0); // Index of the column to hide
            column.setPreferredWidth(0);
            column.setMinWidth(0);
            column.setMaxWidth(0);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Query Fail. "+e.getMessage());
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

        header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pinjamButton = new javax.swing.JButton();
        main = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        cariInput = new javax.swing.JTextField();
        cariButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(227, 242, 253));

        header.setBackground(new java.awt.Color(227, 242, 253));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Pinjaman Anggota");

        pinjamButton.setBackground(new java.awt.Color(0, 123, 255));
        pinjamButton.setForeground(new java.awt.Color(255, 255, 255));
        pinjamButton.setText("Pinjam");
        pinjamButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pinjamButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pinjamButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 452, Short.MAX_VALUE)
                .addComponent(pinjamButton)
                .addContainerGap())
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(pinjamButton)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        main.setBackground(new java.awt.Color(227, 242, 253));

        table.setModel(new javax.swing.table.DefaultTableModel(
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
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        cariInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cariInputKeyPressed(evt);
            }
        });

        cariButton.setText("Cari");
        cariButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainLayout = new javax.swing.GroupLayout(main);
        main.setLayout(mainLayout);
        mainLayout.setHorizontalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
                    .addGroup(mainLayout.createSequentialGroup()
                        .addComponent(cariInput, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cariButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        mainLayout.setVerticalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cariInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cariButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void pinjamButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pinjamButtonActionPerformed
        pinjamanPinjamForm pinjamanPinjamForm = new pinjamanPinjamForm();
        pinjamanPinjamForm.layout = layout;
        pinjamanPinjamForm.parent = this;

        layout.pn_main.removeAll();
        layout.pn_main.add(pinjamanPinjamForm);
        layout.pn_main.repaint();
        layout.pn_main.revalidate();
    }//GEN-LAST:event_pinjamButtonActionPerformed

    private void cariInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariInputKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            datatable();
        }
    }//GEN-LAST:event_cariInputKeyPressed

    private void cariButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariButtonActionPerformed
        datatable();
    }//GEN-LAST:event_cariButtonActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        int row = table.getSelectedRow();
        String id = tableModel.getValueAt(row, 0).toString();

        pinjamanAngsurList pinjamanAngsurList = new pinjamanAngsurList(id);
        pinjamanAngsurList.layout = layout;
        pinjamanAngsurList.parent = this;

        layout.pn_main.removeAll();
        layout.pn_main.add(pinjamanAngsurList);
        layout.pn_main.repaint();
        layout.pn_main.revalidate();
    }//GEN-LAST:event_tableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cariButton;
    private javax.swing.JTextField cariInput;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel main;
    private javax.swing.JButton pinjamButton;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
