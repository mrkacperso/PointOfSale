import javax.swing.*;

/**
 * Created by Kacper on 26.05.2017.
 *
 * Main application launcher class
 */

public class MainLauncher {

    public MainLauncher(){
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CoreAppLogic screen = new CoreAppLogic("Point of Sale");
            screen.setVisible(true);
        });
    }

}
