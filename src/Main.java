
import visuals.JanelaPrincipal;
import javax.swing.SwingUtilities;
import java.util.Random;

public class Main {
    void main() {
        var arr = new int[80];
        var rand = new Random();
        for (var i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(100) + 1;
        }

        SwingUtilities.invokeLater(() -> {
            var janela = new JanelaPrincipal(arr);
            janela.setVisible(true);
        });
    }
}