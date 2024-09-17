/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Icon;

/**
 *
 * @author syahrul
 */
public class sideBarItem extends javax.swing.JPanel {
    public ArrayList<sideBarItem> getSubMenu() {
        return subMenu;
    }

    private ActionListener act;
    private final ArrayList<sideBarItem> subMenu = new ArrayList<>();

    /**
     * Creates new form sideBarItem
     */
    public sideBarItem(Icon icon, boolean sbm, Icon iconSub, String name, ActionListener act, sideBarItem... subMenu) {
        initComponents();
        setBackground(new java.awt.Color(52,58,64));
        mainIcon.setIcon(icon);
        menuName.setText(name);
        subIcon.setIcon(iconSub);
        subIcon.setVisible(sbm);

        if (act != null) {
            this.act = act;
        }
        this.setSize(new Dimension(Integer.MAX_VALUE, 50));
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        this.setMinimumSize(new Dimension(Integer.MIN_VALUE, 50));

        for (sideBarItem subMenu1 : subMenu) {
            this.subMenu.add(subMenu1);
            subMenu1.setVisible(false);
        }
    }

    private void showMenu() {
        new Thread(() -> {
            for (int i = 0; i < subMenu.size(); i++) {
                sleep();
                subMenu.get(i).setVisible(true);
                subMenu.get(i).revalidate();
                subMenu.get(i).repaint();
            }
            showing = true;
            getParent().revalidate();
            getParent().repaint();
        }).start();
    }

    private void hideMenu() {
        new Thread(() -> {
            for (int i = subMenu.size() - 1; i >= 0; i--) {
                sleep();
                subMenu.get(i).setVisible(false);
                subMenu.get(i).revalidate();
                subMenu.get(i).repaint();
            }
            showing = false;
            getParent().revalidate();
            getParent().repaint();
        }).start();
    }
    
    private void sleep() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {}
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pad1 = new javax.swing.JLabel();
        mainIcon = new javax.swing.JLabel();
        subIcon = new javax.swing.JLabel();
        pad = new javax.swing.JLabel();
        menuName = new javax.swing.JLabel();

        setBackground(new java.awt.Color(52, 58, 64));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.X_AXIS));

        pad1.setText("jLabel1");
        pad1.setMaximumSize(new java.awt.Dimension(10, 30));
        pad1.setMinimumSize(new java.awt.Dimension(10, 30));
        pad1.setPreferredSize(new java.awt.Dimension(10, 30));
        add(pad1);

        mainIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mainIcon.setMaximumSize(new java.awt.Dimension(30, 30));
        mainIcon.setMinimumSize(new java.awt.Dimension(30, 30));
        mainIcon.setPreferredSize(new java.awt.Dimension(30, 30));
        add(mainIcon);

        subIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        subIcon.setMaximumSize(new java.awt.Dimension(30, 30));
        subIcon.setMinimumSize(new java.awt.Dimension(30, 30));
        subIcon.setPreferredSize(new java.awt.Dimension(30, 30));
        add(subIcon);

        pad.setText("jLabel1");
        pad.setMaximumSize(new java.awt.Dimension(10, 30));
        pad.setMinimumSize(new java.awt.Dimension(10, 30));
        pad.setPreferredSize(new java.awt.Dimension(10, 30));
        add(pad);

        menuName.setForeground(new java.awt.Color(255, 255, 255));
        menuName.setMaximumSize(new java.awt.Dimension(120, 30));
        menuName.setMinimumSize(new java.awt.Dimension(120, 30));
        menuName.setPreferredSize(new java.awt.Dimension(120, 30));
        menuName.setRequestFocusEnabled(false);
        add(menuName);
    }// </editor-fold>//GEN-END:initComponents

    private boolean showing = false;
    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        if (showing) {
            hideMenu();
        } else {
            showMenu();
        }
        if (act != null) {
            act.actionPerformed(null);
        }
    }//GEN-LAST:event_formMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel mainIcon;
    private javax.swing.JLabel menuName;
    private javax.swing.JLabel pad;
    private javax.swing.JLabel pad1;
    private javax.swing.JLabel subIcon;
    // End of variables declaration//GEN-END:variables
}
