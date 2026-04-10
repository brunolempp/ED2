package visuals;

import javax.swing.*;
import java.awt.*;

public class Visualizador extends JPanel {
    private static final int LIMIAR_ANIMACAO_BAIXA = 200;
    private static final int LIMIAR_ANIMACAO_MEDIA = 500;
    private static final int LIMIAR_ANIMACAO_ALTA = 1000;
    private static final Color VERDE_CONCLUSAO = new Color(76, 175, 80);
    private static final int VELOCIDADE_MIN = 0;
    private static final int VELOCIDADE_MAX = 100;

    private int[] array;
    private int indiceAtual = -1;
    private int indiceProximo = -1;
    private int indiceConcluido = -1;
    private volatile int velocidadeGrafico = 70;

    public Visualizador(int[] array) {
        this.array = array;
        setBackground(Color.DARK_GRAY);
    }

    public void atualizarDestaques(int atual, int proximo){
        this.indiceAtual = atual;
        this.indiceProximo = proximo;
        this.indiceConcluido = -1;
        repaint();
    }

    public void limparDestaques() {
        this.indiceAtual = -1;
        this.indiceProximo = -1;
        this.indiceConcluido = -1;
        repaint();
    }

    public void setVelocidadeGrafico(int velocidadeGrafico) {
        this.velocidadeGrafico = Math.max(VELOCIDADE_MIN, Math.min(VELOCIDADE_MAX, velocidadeGrafico));
    }

    public void pausar(int milisegundos){
        repaint();
        try {
            Thread.sleep(calcularPausaEfetiva(milisegundos));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private int calcularPausaEfetiva(int milisegundos) {
        if (array == null) {
            return milisegundos;
        }

        int basePorTamanho = calcularPausaBasePorTamanho(milisegundos);
        double multiplicadorVelocidade = calcularMultiplicadorVelocidade();

        return Math.max(0, (int) Math.round(basePorTamanho * multiplicadorVelocidade));
    }

    private int calcularPausaBasePorTamanho(int milisegundos) {
        int tamanho = array.length;

        if (tamanho <= LIMIAR_ANIMACAO_BAIXA) {
            return milisegundos;
        }

        if (tamanho <= LIMIAR_ANIMACAO_MEDIA) {
            return Math.max(1, milisegundos / 2);
        }

        if (tamanho <= LIMIAR_ANIMACAO_ALTA) {
            return Math.max(1, milisegundos / 4);
        }

        return Math.max(1, milisegundos / 6);
    }

    private double calcularMultiplicadorVelocidade() {
        double velocidadeNormalizada = velocidadeGrafico / 100.0;
        return 4.0 - (3.75 * velocidadeNormalizada);
    }

    public void setArray(int[] novoArray) {
        this.array = novoArray;
        limparDestaques();
        repaint();
    }

    public void animarConclusao() {
        if (array == null || array.length == 0) {
            return;
        }

        limparDestaques();

        for (int i = 0; i < array.length; i++) {
            if (Thread.currentThread().isInterrupted()) {
                return;
            }

            this.indiceConcluido = i;
            repaint();

            try {
                Thread.sleep(calcularPausaConclusao());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    private int calcularPausaConclusao() {
        if (array == null) {
            return 0;
        }

        int tamanho = array.length;
        int base;

        if (tamanho <= LIMIAR_ANIMACAO_BAIXA) {
            base = 4;
        } else if (tamanho <= LIMIAR_ANIMACAO_MEDIA) {
            base = 3;
        } else if (tamanho <= LIMIAR_ANIMACAO_ALTA) {
            base = 2;
        } else {
            base = 1;
        }

        return Math.max(0, (int) Math.round(base * calcularMultiplicadorVelocidade()));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (array == null || array.length == 0) return;

        double larguraBarra = (double) getWidth() / array.length;

        int maiorValor = 1;
        for (int valor : array) {
            if (valor > maiorValor) {
                maiorValor = valor;
            }
        }
        double alturaProporcao = (double) getHeight() / maiorValor;

        for (var i = 0; i < array.length; i++) {

            if (i <= indiceConcluido) {
                g.setColor(VERDE_CONCLUSAO);
            } else if (i == indiceAtual || i == indiceProximo) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.CYAN);
            }

            int x = (int) (i * larguraBarra);
            int altura = (int) (array[i] * alturaProporcao);
            int y = getHeight() - altura;

            g.fillRect(x, y, (int) Math.ceil(larguraBarra), altura);
        }
    }
}
