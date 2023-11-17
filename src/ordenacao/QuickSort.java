package ordenacao;

import util.listadupla.ListaDupla;
import model.Conta;

public class QuickSort
{
    public static ListaDupla<Conta> quickSort(ListaDupla<Conta> lista) {
        ListaDupla<Conta> listaClone = lista.clone();
        quickSort(listaClone, 0, lista.getQuantNos() - 1);
        return listaClone;
    }
    
    public static void quickSort(ListaDupla<Conta> lista, int inicio, int fim) {
        if (inicio < fim) {
            int indiceParticao = particionar(lista, inicio, fim);

            // Recursivamente ordena os subarrays à esquerda e à direita do índice de partição
            quickSort(lista, inicio, indiceParticao - 1);
            quickSort(lista, indiceParticao + 1, fim);
        }
    }

    private static int particionar(ListaDupla<Conta> lista, int inicio, int fim) {
        long pivo = lista.get(fim).getCpf();
        int indiceMenor = inicio - 1;

        // Percorre o array e coloca os elementos menores que o pivo à esquerda e os maiores à direita
        for (int i = inicio; i < fim; i++) {
            if (lista.get(i).getCpf() <= pivo) {
                indiceMenor++;
                trocar(lista, indiceMenor, i);
            }
        }

        // Coloca o pivo na posição correta
        trocar(lista, indiceMenor + 1, fim);

        return indiceMenor + 1;
    }

    private static void trocar(ListaDupla<Conta> lista, int i, int j) {
        Conta temp = lista.get(i);
        lista.set(i, lista.get(j));
        lista.set(j, temp);
    }
}
