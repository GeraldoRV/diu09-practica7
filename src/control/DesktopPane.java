package control;

import gui.InternalWindow;
import java.io.File;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class DesktopPane extends JDesktopPane {

    private int nFrames = 0;

    public void openInternalFrame(File file) {
        closeAllFrames();
        InternalWindow internalWindow = new InternalWindow();
        internalWindow.initializeProject(file);
        add(internalWindow);
        nFrames++;
    }

    public void openInternalFrame(InternalWindow internalWindow) {
        internalWindow.setLocation(nFrames * 40, nFrames * 10);
        nFrames++;
        add(internalWindow);
    }

    public void closeAllFrames() {
        JInternalFrame[] ventanas = getAllFrames();
        for (JInternalFrame ventana : ventanas) {
            ventana.dispose();
        }
        nFrames = 0;
    }

    public void refreshFrames() {
        JInternalFrame[] ventanas = getAllFrames();
        for (JInternalFrame ventana : ventanas) {
            if (ventana != this.getSelectedFrame()) {
                ventana.dispose();
            }
        }
        nFrames = 1;
    }

    public void quitFrame() {
        getSelectedFrame().dispose();
        nFrames--;
    }
    
}