package algoritmos;

import visuals.Visualizador;

public class BubbleSort implements AlgoritmoOrdenacao{
    @Override
    public void ordenar(int[] array, Visualizador v){
        int tamanho = array.length;
        boolean trocou = true;

        while(trocou){
            trocou = false;

            for(int i = 0; i < tamanho - 1; i++){
                v.atualizarDestaques(i,i + 1);
                v.pausar(2);

                if(array[i] > array[i+1]){
                    int auxTemp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = auxTemp;

                    trocou = true;
                    v.pausar(2);
                }
            }
            tamanho--;
        }
    }
}
