package visuals;

import algoritmos.AlgoritmoOrdenacao;
import algoritmos.BubbleSort;
import algoritmos.SelectionSort;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class JanelaPrincipal extends JFrame {
    private final Visualizador visualizador;
    private int[] arrayAtual;

    public JanelaPrincipal() {
        this.arrayAtual = gerarArray(80);

        setTitle("Laboratório ED2 - Uniube");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        this.visualizador = new Visualizador(arrayAtual);

        // Monta o menu superior
        var painelMenu = new JPanel();
        // Adicione os próximos algoritmos aqui depois:
        var comboAlgoritmos = new JComboBox<>(new String[]{"Bubble Sort","Selection Sort","Em Construção"});
        var btnRodar = new JButton("Dar a Largada");
        var btnEmbaralhar = new JButton("Embaralhar");

        painelMenu.add(new JLabel("Motor:"));
        painelMenu.add(comboAlgoritmos);
        painelMenu.add(btnRodar);
        painelMenu.add(btnEmbaralhar);

        // Ações dos botões
        btnEmbaralhar.addActionListener(_ -> {
            arrayAtual = gerarArray(80);
            visualizador.setArray(arrayAtual);
        });

        btnRodar.addActionListener(_ -> {
            btnRodar.setEnabled(false);

            Thread.startVirtualThread(() -> {
                String escolha = Objects.requireNonNull(comboAlgoritmos.getSelectedItem()).toString();

                // O Switch visionário do JDK 25. Copie e cole linhas aqui no futuro:
                AlgoritmoOrdenacao motor = switch (escolha) {
                    case "Bubble Sort" -> new BubbleSort();
                    case "Selection Sort" -> new SelectionSort();
                    default -> new BubbleSort();
                };

                motor.ordenar(arrayAtual, visualizador);
                btnRodar.setEnabled(true);
            });
        });

        add(painelMenu, BorderLayout.NORTH);
        add(visualizador, BorderLayout.CENTER);
    }

    private int[] gerarArray(int tamanho) {
        var arr = new int[tamanho];
        var rand = new Random();
        for (var i = 0; i < arr.length; i++) arr[i] = rand.nextInt(100) + 1;
        return arr;
    }
}