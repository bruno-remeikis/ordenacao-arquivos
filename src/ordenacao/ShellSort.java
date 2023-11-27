package ordenacao;

import util.listadupla.ListaDupla;
import model.Conta;

public class ShellSort implements Sort
{
	@Override
	public ListaDupla<Conta> sort(ListaDupla<Conta> lista) {
		shellSort(lista);
		return lista;
	}
	
    private static ListaDupla<Conta> shellSort(ListaDupla<Conta> lista) {
        //ListaDupla<Conta> listaClone = lista.clone();

        int n = lista.getQuantNos();
        int h = 1;

        // Define o valor inicial de h (intervalo)
        while (h < n / 3) {
            h = 3 * h + 1;
        }

        // Aplica o algoritmo ShellSort
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && lista.get(j - h).getCpf() > lista.get(j).getCpf(); j -= h) {
                    trocar(lista, j, j - h);
                }
            }
            h = h / 3;
        }

        return lista;
    }

    private static void trocar(ListaDupla<Conta> lista, int i, int j) {
        Conta temp = lista.get(i);
        lista.set(i, lista.get(j));
        lista.set(j, temp);
    }
}
