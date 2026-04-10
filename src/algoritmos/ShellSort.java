package algoritmos;

import visuals.Visualizador;

public class ShellSort implements AlgoritmoOrdenacao{
    @Override
    public void ordenar(int[] array, Visualizador v) {
        int tamanho = array.length;

        for (int salto = tamanho / 2; salto > 0; salto/=2){
            if (Thread.currentThread().isInterrupted()) return;

            for (int i = salto; i < tamanho; i++){
                if (Thread.currentThread().isInterrupted()) return;

                int j = i;
                int auxTemp = array[i];

                while (j >= salto && array[j - salto] > auxTemp){
                    if (Thread.currentThread().isInterrupted()) return;

                    array[j] = array[j - salto];
                    j -= salto;

                    v.atualizarDestaques(j, j + salto);
                    v.pausar(2);

                    if (Thread.currentThread().isInterrupted()) return;
                }

                array[j] = auxTemp;

                v.atualizarDestaques(j, i);
                v.pausar(2);

                if (Thread.currentThread().isInterrupted()) return;
            }
        }
    }
}
