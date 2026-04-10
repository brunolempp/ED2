package algoritmos;

import visuals.Visualizador;

public class InsertionSort implements AlgoritmoOrdenacao {
    @Override
    public void ordenar(int[] array, Visualizador v) {
        int tamanho = array.length;
        int auxTemp;

        for (int i = 1; i < tamanho; i++) {
            if (Thread.currentThread().isInterrupted()) return;

            int j = i - 1;
            auxTemp = array[i];

            while (j >= 0 && array[j] > auxTemp) {
                if (Thread.currentThread().isInterrupted()) return;

                array[j + 1] = array[j];

                j--;

                v.atualizarDestaques(j, j + 1);
                v.pausar(2);

                if (Thread.currentThread().isInterrupted()) return;
            }

            array[j + 1] = auxTemp;

            v.atualizarDestaques(j, j + 1);
            v.pausar(2);

            if (Thread.currentThread().isInterrupted()) return;
        }
    }
}

