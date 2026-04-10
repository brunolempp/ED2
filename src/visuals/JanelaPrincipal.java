package visuals;

import algoritmos.*;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class JanelaPrincipal extends JFrame {
    private final Visualizador visualizador;
    private int[] arrayAtual;
    private Thread threadOrdenacao;
    private long execucaoAtual = 0;

    private static final int TAMANHO_ARRAY_PADRAO = 1000;

    public JanelaPrincipal() {
        this.arrayAtual = gerarArray(TAMANHO_ARRAY_PADRAO);

        setTitle("Laboratório ED2 - Uniube");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        this.visualizador = new Visualizador(arrayAtual);

        var painelMenu = new JPanel();
        painelMenu.setLayout(new BoxLayout(painelMenu, BoxLayout.Y_AXIS));

        var painelControlesSuperiores = new JPanel(new FlowLayout(FlowLayout.LEFT));
        var painelControlesInferiores = new JPanel(new FlowLayout(FlowLayout.LEFT));
        var comboAlgoritmos = new JComboBox<>(new String[]{"Escolha", "Bubble Sort", "Selection Sort", "Insertion Sort", "Shell Sort"});
        var spinnerTamanho = new JSpinner(new SpinnerNumberModel(TAMANHO_ARRAY_PADRAO, 10, 10000, 10));
        var sliderVelocidade = new JSlider(0, 100, 70);
        var lblVelocidade = new JLabel("Velocidade: 70%");
        var btnRodar = new JButton("Dar a Largada");
        var btnEmbaralhar = new JButton("Embaralhar");
        var btnAbortar = new JButton("Abortar");
        btnRodar.setEnabled(false);
        btnAbortar.setEnabled(false);

        sliderVelocidade.setPaintTicks(true);
        sliderVelocidade.setPaintLabels(true);
        sliderVelocidade.setMajorTickSpacing(25);
        sliderVelocidade.setMinorTickSpacing(5);
        sliderVelocidade.setSnapToTicks(false);
        visualizador.setVelocidadeGrafico(sliderVelocidade.getValue());

        painelControlesSuperiores.add(new JLabel("Tamanho:"));
        painelControlesSuperiores.add(spinnerTamanho);
        painelControlesSuperiores.add(new JLabel("Motor:"));
        painelControlesSuperiores.add(comboAlgoritmos);
        painelControlesSuperiores.add(new JLabel("Velocidade:"));
        painelControlesSuperiores.add(sliderVelocidade);
        painelControlesSuperiores.add(lblVelocidade);

        painelControlesInferiores.add(btnRodar);
        painelControlesInferiores.add(btnEmbaralhar);
        painelControlesInferiores.add(btnAbortar);

        painelMenu.add(painelControlesSuperiores);
        painelMenu.add(painelControlesInferiores);

        spinnerTamanho.addChangeListener(_ -> {
            int novoTamanho = (int) spinnerTamanho.getValue();
            arrayAtual = gerarArray(novoTamanho);
            visualizador.setArray(arrayAtual);
        });

        comboAlgoritmos.addActionListener(_ -> {
            String escolha = Objects.requireNonNull(comboAlgoritmos.getSelectedItem()).toString();
            btnRodar.setEnabled(!"Escolha".equals(escolha));
        });

        sliderVelocidade.addChangeListener(_ -> {
            int velocidade = sliderVelocidade.getValue();
            visualizador.setVelocidadeGrafico(velocidade);
            lblVelocidade.setText("Velocidade: " + velocidade + "%");
        });

        btnEmbaralhar.addActionListener(_ -> {
            int tamanho = (int) spinnerTamanho.getValue();
            arrayAtual = gerarArray(tamanho);
            visualizador.setArray(arrayAtual);
        });

        btnAbortar.addActionListener(_ -> {
            btnAbortar.setEnabled(false);
            Thread thread = threadOrdenacao;
            if (thread != null) {
                thread.interrupt();
            }
        });

        btnRodar.addActionListener(_ -> {
            String escolha = Objects.requireNonNull(comboAlgoritmos.getSelectedItem()).toString();
            if ("Escolha".equals(escolha)) {
                JOptionPane.showMessageDialog(this, "Selecione um algoritmo antes de iniciar.");
                return;
            }

            long idExecucao = ++execucaoAtual;

            btnRodar.setEnabled(false);
            btnEmbaralhar.setEnabled(false);
            spinnerTamanho.setEnabled(false);
            comboAlgoritmos.setEnabled(false);
            btnAbortar.setEnabled(true);

            threadOrdenacao = Thread.startVirtualThread(() -> {
                AlgoritmoOrdenacao motor = switch (escolha) {
                    case "Bubble Sort" -> new BubbleSort();
                    case "Selection Sort" -> new SelectionSort();
                    case "Insertion Sort" -> new InsertionSort();
                    case "Shell Sort" -> new ShellSort();
                    default -> null;
                };

                if (motor != null) {
                    motor.ordenar(arrayAtual, visualizador);
                }

                if (!Thread.currentThread().isInterrupted()) {
                    visualizador.animarConclusao();
                }

                SwingUtilities.invokeLater(() -> {
                    if (idExecucao != execucaoAtual) {
                        return;
                    }

                    threadOrdenacao = null;
                    btnAbortar.setEnabled(false);
                    btnEmbaralhar.setEnabled(true);
                    spinnerTamanho.setEnabled(true);
                    comboAlgoritmos.setEnabled(true);
                    btnRodar.setEnabled(true);
                });
            });
        });

        add(painelMenu, BorderLayout.NORTH);
        add(visualizador, BorderLayout.CENTER);
    }

    private int[] gerarArray(int tamanho) {
        var arr = new int[tamanho];
        var rand = new Random();
        for (var i = 0; i < arr.length; i++) arr[i] = rand.nextInt(tamanho * 10) + 1;
        return arr;
    }
}