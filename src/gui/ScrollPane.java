package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

public class ScrollPane extends JScrollPane {
    private ImagePanel panel;
    private JViewport view;
    private final JScrollBar hBar;
    private final JScrollBar vBar;
    private Point origin;
    private MainWindow main;
    
    public ScrollPane() {
        panel = new ImagePanel();
        
        hBar = this.getHorizontalScrollBar();
        vBar = this.getVerticalScrollBar();
        view = this.getViewport();
        origin = new Point(0, 0);
        
        hBar.addAdjustmentListener(barListener());
        vBar.addAdjustmentListener(barListener());
        
        showPaint();
    }
    
    private AdjustmentListener barListener() {
        return (AdjustmentEvent e) -> {
            System.out.println("Hola");
        };
    }

    private void showPaint() {
        if(panel.getImage() != null) {
            hBar.setValues(0, 10, 0, panel.getWidth());
            vBar.setValues(0, 10, 0, panel.getHeight());
            view.add(panel);
            this.revalidate();
        }
        
        this.setViewportBorder(
                BorderFactory.createLineBorder(Color.black));
        this.setAutoscrolls(true);
        this.setEnabled(true);
        this.repaint();
        panel.repaint();
    }

    public ImagePanel getPanel() {
        return panel;
    }

    public void setPanel(ImagePanel panel) {
        this.panel = panel;
        showPaint();
    }
    
    public int setPanel(File file) {
        if(panel.setImage(file) == ImagePanel.FAILURE)
            return ImagePanel.FAILURE;
        
        showPaint();
        return ImagePanel.SUCCESS;
    }

    public MainWindow getMain() {
        return main;
    }

    public void setMain(MainWindow main) {
        this.main = main;
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
    
    public void reset() {
        panel = new ImagePanel();
        this.setViewportView(this.createViewport());
        this.repaint();
    }
}
