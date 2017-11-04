package gui;

import java.io.File;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class DesktopPane extends JDesktopPane{
    InternalWindow internalWindow;
    
    public DesktopPane() {
        internalWindow = new InternalWindow();
    }
    
    public void test(File file) {
        internalWindow.initializeProject(file);
        this.add(internalWindow);
    }
    public void closeAllFrames() {
        JInternalFrame[] ventanas = getAllFrames();
        for (JInternalFrame ventana : ventanas) {
            ventana.dispose();
        }

    }
}
