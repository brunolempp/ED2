package algoritmos;

import visuals.Visualizador;

public class SelectionSort implements AlgoritmoOrdenacao {
    @Override
    public void ordenar(int[] array, Visualizador v) {
        int tamanho = array.length;

        for (int indexLista = 0; indexLista < tamanho - 1; indexLista++) {
            if (Thread.currentThread().isInterrupted()) return;

            int indexMenor = indexLista;
            int auxMenor = array[indexLista];
            for (int j = indexLista + 1; j < tamanho; j++) {
                if (Thread.currentThread().isInterrupted()) return;

                if (auxMenor > array[j]) {
                    auxMenor = array[j];
                    indexMenor = j;
                    v.atualizarDestaques(indexLista, indexMenor);
                    v.pausar(2);

                    if (Thread.currentThread().isInterrupted()) return;
                }
            }
            if (indexMenor != indexLista){
                int auxTemp = array[indexLista];
                array[indexLista] = auxMenor;
                array[indexMenor] = auxTemp;
                v.atualizarDestaques(indexLista, indexMenor);
                v.pausar(2);

                if (Thread.currentThread().isInterrupted()) return;
            }

        }
    }
}
