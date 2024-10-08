/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.jabatan;

import java.awt.*;

import java.sql.SQLException;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;
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
public class jabatanList extends javax.swing.JPanel {
    private DatabaseManager dbManager;
    private DMLSQL dmlSql;
    private DefaultTableModel tableModel;
    public layout layout = null;

    /**
     * Creates new form jabatanList
     */
    public jabatanList() {
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
        Object[] Baris = {"ID","Nama"};
        tableModel = new DefaultTableModel(null,Baris);
        String search = cariInput.getText();
        try {
            String[] columnsToSelect = {"id","nama"};
            String[] conditionColumns = {"nama"};
            String[] operators = {"LIKE"};
            Object[] conditionValues = {"%"+search+"%"};
            List<Object[]> results = dmlSql.selectData("jabatan", columnsToSelect, null, null, conditionColumns, operators, conditionValues, true,false);
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
        title = new javax.swing.JLabel();
        tambahButton = new javax.swing.JButton();
        body = new javax.swing.JPanel();
        cariInput = new javax.swing.JTextField();
        cariButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setBackground(new java.awt.Color(227, 242, 253));

        header.setBackground(new java.awt.Color(227, 242, 253));

        title.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        title.setText("Jabatan Manajemen");
        title.setPreferredSize(new java.awt.Dimension(172, 27));

        tambahButton.setBackground(new java.awt.Color(0, 123, 255));
        tambahButton.setForeground(new java.awt.Color(255, 255, 255));
        tambahButton.setText("Tambah Jabatan");
        tambahButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tambahButton.setPreferredSize(new java.awt.Dimension(116, 27));
        tambahButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tambahButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tambahButtonMouseExited(evt);
            }
        });
        tambahButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tambahButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tambahButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        body.setBackground(new java.awt.Color(227, 242, 253));
        body.setToolTipText("");

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
        table.setGridColor(new java.awt.Color(222, 226, 230));
        table.setSelectionBackground(new java.awt.Color(227, 242, 253));
        table.setSelectionForeground(new java.awt.Color(52, 58, 64));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bodyLayout.createSequentialGroup()
                        .addComponent(cariInput, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cariButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cariInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cariButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
                    .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tambahButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahButtonActionPerformed
        jabatanForm pegawaiCreate = new jabatanForm(null);
        pegawaiCreate.layout = layout;
        layout.pn_main.removeAll();
        layout.pn_main.add(pegawaiCreate);
        layout.pn_main.repaint();
        layout.pn_main.revalidate();
    }//GEN-LAST:event_tambahButtonActionPerformed

    private void cariInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariInputKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            datatable();
        }
    }//GEN-LAST:event_cariInputKeyPressed

    private void cariButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariButtonActionPerformed
        datatable();
    }//GEN-LAST:event_cariButtonActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        int row = table.getSelectedRow();
        String id = tableModel.getValueAt(row, 0).toString();

        jabatanForm pegawaiCreate = new jabatanForm(id);
        pegawaiCreate.layout = layout;
        pegawaiCreate.parent = this;

        layout.pn_main.removeAll();
        layout.pn_main.add(pegawaiCreate);
        layout.pn_main.repaint();
        layout.pn_main.revalidate();
    }//GEN-LAST:event_tableMouseClicked

    private void tambahButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahButtonMouseEntered
        tambahButton.setBackground(new Color(0, 86, 179));
    }//GEN-LAST:event_tambahButtonMouseEntered

    private void tambahButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahButtonMouseExited
        tambahButton.setBackground(new Color(0, 123, 255));
    }//GEN-LAST:event_tambahButtonMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private javax.swing.JButton cariButton;
    private javax.swing.JTextField cariInput;
    private javax.swing.JPanel header;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JButton tambahButton;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
