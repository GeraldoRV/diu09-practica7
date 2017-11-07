package gui;

import control.ScrollPane;
import control.DesktopPane;
import model.ImagePanel;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author david
 */
public class InternalWindow extends javax.swing.JInternalFrame {

    /**
     * Creates new form InternalWindow2
     */
    public InternalWindow() {
        initComponents();
        child = new ArrayList();
        setDefaults();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new control.ScrollPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        OpenMenu = new javax.swing.JMenuItem();
        closeMenu = new javax.swing.JMenuItem();
        separatorMenu = new javax.swing.JPopupMenu.Separator();
        quitMenu = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        thresholdMenu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(500, 500));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jMenu1.setText("File");

        OpenMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        OpenMenu.setText("Open");
        OpenMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenMenuActionPerformed(evt);
            }
        });
        jMenu1.add(OpenMenu);

        closeMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        closeMenu.setText("Close");
        closeMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeMenuActionPerformed(evt);
            }
        });
        jMenu1.add(closeMenu);
        jMenu1.add(separatorMenu);

        quitMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        quitMenu.setText("Quit");
        quitMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitMenuActionPerformed(evt);
            }
        });
        jMenu1.add(quitMenu);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Effect");

        thresholdMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        thresholdMenu.setText("Threshold...");
        thresholdMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thresholdMenuActionPerformed(evt);
            }
        });
        jMenu2.add(thresholdMenu);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public ScrollPane getScrollPane() {
        return scrollPane;
    }

    public void initializeProject(File file) {
        scrollPane.setPanel(file);
        scrollPane.repaint();
        this.setTitle(file.getName());
        setIndex(0);
    }

    private void OpenMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenMenuActionPerformed
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
                if (scrollPane.setPanel(fc.getSelectedFile()) == ImagePanel.FAILURE) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Cannot load the image. Please, try again!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

                scrollPane.repaint();

                closeMenu.setEnabled(true);
                thresholdMenu.setEnabled(true);
                
                this.setTitle(fc.getName(fc.getSelectedFile()));
            }
        }
        DesktopPane desktop = (DesktopPane) this.getDesktopPane();
            desktop.refreshFrames();
            
    }//GEN-LAST:event_OpenMenuActionPerformed

    private void closeMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeMenuActionPerformed
        scrollPane.reset();
        closeMenu.setEnabled(false);
        thresholdMenu.setEnabled(false);
        this.setTitle("");
        DesktopPane desktop = (DesktopPane) this.getDesktopPane();
        desktop.closeFrames(index);
    }//GEN-LAST:event_closeMenuActionPerformed

    private void quitMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitMenuActionPerformed
        int res = JOptionPane.showConfirmDialog(
                rootPane, 
                "Are you sure?", 
                "Exit", 
                JOptionPane.YES_NO_OPTION);
        
        if (res == JOptionPane.YES_OPTION) {
            DesktopPane desktop = (DesktopPane) this.getDesktopPane();
                desktop.quitFrames(this.index);

            try {
                this.setClosed(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(InternalWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_quitMenuActionPerformed

    private void thresholdMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thresholdMenuActionPerformed
        JSlider jSlider = new JSlider();

        if (generateThresholdWindow(jSlider) == JOptionPane.OK_OPTION) {

            BufferedImage img = scrollPane.getPanel().umbralizar(
                    scrollPane.getPanel().getImage(),
                    jSlider.getValue());

            InternalWindow internalWindow = new InternalWindow();

            internalWindow.changeImage(img);
            internalWindow.setTitle("Threshold : " + jSlider.getValue());

            DesktopPane desktop = (DesktopPane) this.getDesktopPane();
            desktop.openInternalFrame(internalWindow);
            child.add(internalWindow.getIndex());
        }
    }//GEN-LAST:event_thresholdMenuActionPerformed

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        int res = JOptionPane.showConfirmDialog(
                rootPane, 
                "Are you sure?", 
                "Exit", 
                JOptionPane.YES_NO_OPTION);
        
        if (res == JOptionPane.YES_OPTION) {
            DesktopPane desktop = (DesktopPane) this.getDesktopPane();
                desktop.quitFrames(this.index);

            try {
                this.setClosed(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(InternalWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_formInternalFrameClosing

    public void changeImage(BufferedImage img) {
        scrollPane.setPanel(img);
        scrollPane.repaint();
    }

    private void setDefaults() {

        this.setIconifiable(true);
        this.setMaximizable(true);
        this.setResizable(true);
        this.setClosable(true);

        this.setVisible(true);
        this.setEnabled(true);
        this.setFocusable(true);
        this.moveToFront();
        try {
            this.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(InternalWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.show();
    }

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

    private boolean isImage(String filename) {
        String extension = filename.substring(
                filename.lastIndexOf(".") + 1)
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

    private int generateThresholdWindow(JSlider jSlider) {
        jSlider.setMajorTickSpacing(51);
        jSlider.setPaintTicks(true);
        jSlider.setPaintLabels(true);
        jSlider.setMaximum(255);
        jSlider.setValue(128);

        JPanel jPanel = new JPanel(new GridLayout(0, 1));
        jPanel.add(new JLabel("Select a value : "));

        JPanel jPanel1 = new JPanel(new FlowLayout(FlowLayout.TRAILING, 15, 5));
        jPanel1.add(jSlider);
        JLabel jLabel = new JLabel(Integer.toString(jSlider.getValue()));
        jLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        jPanel1.add(jLabel);

        jPanel.add(jPanel.add(jPanel1));

        jSlider.addChangeListener((ChangeEvent e) -> {
            jLabel.setText(Integer.toString(jSlider.getValue()));
        });

        int res = JOptionPane.showConfirmDialog(rootPane,
                new JPanel[]{jPanel, jPanel1},
                "Select a Threshold",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        return res;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem OpenMenu;
    private javax.swing.JMenuItem closeMenu;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem quitMenu;
    private control.ScrollPane scrollPane;
    private javax.swing.JPopupMenu.Separator separatorMenu;
    private javax.swing.JMenuItem thresholdMenu;
    // End of variables declaration//GEN-END:variables
    private int index;
    private List<Integer> child;

    public List<Integer> getChild() {
        return child;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
    
}
