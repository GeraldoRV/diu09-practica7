package gui;

import java.beans.PropertyVetoException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class DesktopPane extends JDesktopPane {

    List<InternalWindow> internalWindows;
    int nVentanas = 0;

    public DesktopPane() {
        internalWindows = new ArrayList<>();
    }

    public void openInternalFrame(File file) {
        closeAllFrames();
        nVentanas = 0;
        internalWindows.clear();
        InternalWindow internalWindow = new InternalWindow();
        internalWindow.initializeProject(file);
        this.add(internalWindow);
        internalWindows.add(internalWindow);
        nVentanas++;
    }

    public void openInternalFrame(InternalWindow internalWindow) {
        internalWindow.setLocation(nVentanas * 40, nVentanas * 10);
        internalWindow.setIndex(nVentanas);
        nVentanas++;
        add(internalWindow);
        internalWindows.add(internalWindow);

    }

    public void closeAllFrames() {
        JInternalFrame[] ventanas = getAllFrames();
        for (JInternalFrame ventana : ventanas) {
            ventana.dispose();
        }
    }

    public void refreshFrames() {
        JInternalFrame[] ventanas = getAllFrames();
        for (JInternalFrame ventana : ventanas) {
            if (ventana != this.getSelectedFrame()) {
                ventana.dispose();

            }
        }
    }

    public void quitFrames(int index) {
        internalWindows.get(index).dispose();
        List<Integer> hijos = internalWindows.get(index).getChild();
        hijos.forEach((hijo) -> {
            internalWindows.get(hijo).dispose();
        });
    }

    public void closeFrames(int index) {
        List<Integer> hijos = internalWindows.get(index).getChild();
        hijos.forEach((Integer hijo) -> {
            try {
                internalWindows.get(hijo).setClosed(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(DesktopPane.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
