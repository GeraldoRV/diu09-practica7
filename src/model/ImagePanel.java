package model;

import boofcv.alg.filter.binary.GThresholdImageOps;
import boofcv.core.image.ConvertImage;
import boofcv.gui.binary.VisualizeBinaryData;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.struct.image.GrayU8;
import boofcv.struct.image.Planar;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
    private BufferedImage image;
    private int threshold;
    
    public final static int SUCCESS = 0;
    public final static int FAILURE = -1;
    
    public int setImage(File file) {
        if(file == null)
            return FAILURE;
        try {
            this.image = ImageIO.read(file);
        } catch (IOException ex) {
            JOptionPane.showConfirmDialog(
                    null, 
                    "An error ocurred when loading the image", 
                    "Image error", 
                    JOptionPane.ERROR_MESSAGE);
            return FAILURE;
        }
        this.repaint();
        return SUCCESS;
    }
    
    public int setImage(String path) {
        if(path == null || path.equals(""))
            return FAILURE;
        
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException ex) {
            JOptionPane.showConfirmDialog(
                    null, 
                    "An error ocurred when loading the image", 
                    "Image error", 
                    JOptionPane.ERROR_MESSAGE);
        }
        this.repaint();
        return SUCCESS;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }
    
    public BufferedImage umbralizar(BufferedImage image, int threshold) {
        // Convierte la imagen en color Buffered Image en formato de la librería BoofCV
        Planar<GrayU8> colorImage = ConvertBufferedImage.convertFromPlanar(
                image, 
                null, 
                true, 
                GrayU8.class);

        // Crea dos imágenes en niveles de grises
        GrayU8 grayImage = new GrayU8(image.getWidth(), image.getHeight());
        GrayU8 thresholdImage = new GrayU8(image.getWidth(), image.getHeight());
        
        // Convierte a niveles de gris la imagen de entrada
        ConvertImage.average(colorImage, grayImage);
        
        // Umbraliza la imagen:
        //  - Píxeles con nivel de gris > Umbral se pone a 0
        //  - Píxeles con nivel de gris <= Umbral se ponen a 1
        GThresholdImageOps.threshold(grayImage, thresholdImage, threshold, false);

        // Se devuelve la imagen umbralizada en formato BufferedImage
        return VisualizeBinaryData.renderBinary(thresholdImage, false, null);
    }

    
    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
    
    @Override
    public void paintComponent(Graphics g){
        if(image != null)
            this.setPreferredSize(
                    new Dimension(
                            image.getWidth(), 
                            image.getHeight()));
        
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
}
