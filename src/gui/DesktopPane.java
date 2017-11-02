package gui;



import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class DesktopPane extends JDesktopPane{
    public void closeAllFrames() {
        JInternalFrame[] ventanas = getAllFrames();
        for (JInternalFrame ventana : ventanas) {
            ventana.dispose();
        }

    }
}
