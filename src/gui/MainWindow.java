/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Geraldo
 */
public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new control.DesktopPane();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        closeMenuItem = new javax.swing.JMenuItem();
        separator = new javax.swing.JPopupMenu.Separator();
        exitMenuItem = new javax.swing.JMenuItem();
        hekpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("PhotoStation");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        fileMenu.setText("File");

        openMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openMenuItem.setText("Open Project");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openMenuItem);

        closeMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        closeMenuItem.setText("Close Projects");
        closeMenuItem.setEnabled(false);
        closeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(closeMenuItem);
        fileMenu.add(separator);

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        exitMenuItem.setText("Quit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        hekpMenu.setText("Help");

        aboutMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        aboutMenuItem.setText("About...");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        hekpMenu.add(aboutMenuItem);

        menuBar.add(hekpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 979, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        JFileChooser fc = new JFileChooser(System.getProperty("user.home"));

        setFilter(fc);

        int res = fc.showOpenDialog(null);

        if (res == JFileChooser.APPROVE_OPTION) {
            String filename = fc.getSelectedFile().getAbsolutePath();
            if (!isImage(filename)) {
                JOptionPane.showMessageDialog(
                        null,
                        "The file selected is not a valid image",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                desktopPane.openInternalFrame(fc.getSelectedFile());
                closeMenuItem.setEnabled(true);
            }
        }
    }//GEN-LAST:event_openMenuItemActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        int res = JOptionPane.showConfirmDialog(
                rootPane,
                "Are you sure?",
                "Exit",
                JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void closeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeMenuItemActionPerformed
        desktopPane.closeAllFrames();
        closeMenuItem.setEnabled(false);
    }//GEN-LAST:event_closeMenuItemActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        JOptionPane.showMessageDialog(
                null,
                "This program uses a thresholding technique to make your pics more fancy"
                + "\nDeveloped by David Medina & Geraldo Rodrigues",
                "About...",
                JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int res = JOptionPane.showConfirmDialog(
                rootPane,
                "Are you sure?",
                "Exit",
                JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }//GEN-LAST:event_formWindowClosing

    private void setFilter(JFileChooser fc) {
        FileFilter filter = new FileNameExtensionFilter(
                "All pictures",
                "jpg",
                "jpeg",
                "jpe",
                "jfif",
                "bmp",
                "dib",
                "tif",
                "tiff",
                "png");
        fc.addChoosableFileFilter(filter);
        filter = new FileNameExtensionFilter(
                "JPEG [*.jpg, *.jpeg, *.jpe, *.jfif]",
                "jpg",
                "jpeg",
                "jpe",
                "jfif");
        fc.addChoosableFileFilter(filter);
        filter = new FileNameExtensionFilter(
                "Mapa de bits [*.bmp, *.dib]",
                "bmp",
                "dib");
        fc.addChoosableFileFilter(filter);
        filter = new FileNameExtensionFilter(
                "GIF [*.gif]",
                "gif");
        fc.addChoosableFileFilter(filter);
        filter = new FileNameExtensionFilter(
                "TIFF [*.tif, *.tiff]",
                "tif",
                "tiff");
        fc.addChoosableFileFilter(filter);
        filter = new FileNameExtensionFilter(
                "PNG [*.png]",
                "png");
        fc.addChoosableFileFilter(filter);
    }

    private boolean isImage(String fileName) {
        String extension = fileName.substring(
                fileName.lastIndexOf(".") + 1)
                .toLowerCase();

        switch (extension) {
            case "jpeg":
                return true;
            case "jpg":
                return true;
            case "jpe":
                return true;
            case "jfif":
                return true;
            case "bmp":
                return true;
            case "dib":
                return true;
            case "gif":
                return true;
            case "tif":
                return true;
            case "tiff":
                return true;
            case "png":
                return true;
            default:
                return false;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem closeMenuItem;
    private control.DesktopPane desktopPane;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu hekpMenu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JPopupMenu.Separator separator;
    // End of variables declaration//GEN-END:variables
}
