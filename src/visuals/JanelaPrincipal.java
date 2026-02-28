package visuals;

import algoritmos.AlgoritmoOrdenacao;
import algoritmos.BubbleSort;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class JanelaPrincipal extends JFrame {
    private Visualizador visualizador;
    private int[] arrayAtual;

    public JanelaPrincipal(int[] arrayInicial) {
        this.arrayAtual = arrayInicial;

        setTitle("Laboratório ED2 - Uniube");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        this.visualizador = new Visualizador(arrayAtual);

        // Monta o menu superior
        var painelMenu = new JPanel();
        // Adicione os próximos algoritmos aqui depois:
        var comboAlgoritmos = new JComboBox<>(new String[]{"Bubble Sort", "Em Construção"});
        var btnRodar = new JButton("Dar a Largada");
        var btnEmbaralhar = new JButton("Embaralhar");

        painelMenu.add(new JLabel("Motor:"));
        painelMenu.add(comboAlgoritmos);
        painelMenu.add(btnRodar);
        painelMenu.add(btnEmbaralhar);

        // Ações dos botões
        btnEmbaralhar.addActionListener(e -> {
            arrayAtual = gerarArray(80);
            visualizador.setArray(arrayAtual);
        });

        btnRodar.addActionListener(e -> {
            btnRodar.setEnabled(false);

            Thread.startVirtualThread(() -> {
                String escolha = comboAlgoritmos.getSelectedItem().toString();

                // O Switch visionário do JDK 25. Copie e cole linhas aqui no futuro:
                AlgoritmoOrdenacao motor = switch (escolha) {
                    case "Bubble Sort" -> new BubbleSort();
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