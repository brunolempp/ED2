
import visuals.JanelaPrincipal;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            var janela = new JanelaPrincipal();
            janela.setVisible(true);
        });
    }
}