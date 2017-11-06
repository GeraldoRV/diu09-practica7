package gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class DesktopPane extends JDesktopPane{
    InternalWindow internalWindow;
    List<InternalWindow> internalWindows;
    int nVentanas = 0;
    public DesktopPane() {
        internalWindow = new InternalWindow();
    }
    

    public void openInternalFrame(File file) {
        internalWindow.initializeProject(file);
        this.add(internalWindow);
    }
    
    public void openAnotherFrame(BufferedImage img, int valor){
        internalWindow = new InternalWindow();
        nVentanas++;
        internalWindow.setLocation(nVentanas*40, nVentanas*10);
        internalWindow.nuew(img, valor);
        
        add(internalWindow);
    }
    
    public void closeAllFrames() {
        JInternalFrame[] ventanas = getAllFrames();
        for (JInternalFrame ventana : ventanas) {
            ventana.dispose();
        }

    }
}
