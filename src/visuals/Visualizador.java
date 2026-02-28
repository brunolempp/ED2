package visuals;

import javax.swing.*;
import java.awt.*;

public class Visualizador extends JPanel {
    private int[] array;
    private int indiceAtual = -1;
    private int indiceProximo = -1;

    public Visualizador(int[] array) {
        this.array = array;
        setBackground(Color.DARK_GRAY);
    }

    public void atualizarDestaques(int atual, int proximo){
        this.indiceAtual = atual;
        this.indiceProximo = proximo;
        repaint();
    }

    public void pausar(int milisegundos){
        repaint();
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {}
    }

    public void setArray(int[] novoArray) {
        this.array = novoArray;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (array == null || array.length == 0) return;

        // Usando double para a matemática não deixar buracos pretos na tela
        double larguraBarra = (double) getWidth() / array.length;
        double alturaProporcao = (double) getHeight() / 100.0; // 100 é o valor máximo do array

        for (var i = 0; i < array.length; i++) {

            if (i == indiceAtual || i == indiceProximo) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.CYAN);
            }

            // Calculando o (X, Y) exato para colunas verticais
            int x = (int) (i * larguraBarra);
            int altura = (int) (array[i] * alturaProporcao);
            int y = getHeight() - altura; // Joga a barra lá pro chão da janela

            // Math.ceil garante que a barra preencha 100% do espaço, sem vãos
            g.fillRect(x, y, (int) Math.ceil(larguraBarra), altura);
        }
    }
}
