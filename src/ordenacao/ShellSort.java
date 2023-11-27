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
//				for (int j = i; j >= h && lista.get(j - h).getCpf() > lista.get(j).getCpf(); j -= h)
                for (int j = i; j >= h && comparar(lista.get(j), lista.get(j - h)); j -= h) {
                    trocar(lista, j, j - h);
                }
            }
            h = h / 3;
        }

        return lista;
    }
    
    /**
     * @param conta 
     * @param pivo 
     * @return {@code true} se as chaves de {@code conta} forem maiores que as de {@code pivo}.
     * Ou seja: {@code true} se os itens precisarem ter suas posições trocadas para que se ordenem
     */
    private static boolean comparar(Conta conta, Conta pivo) 
    {
    	// Verificação de chave primária (cpf)
    	if(conta.getCpf() != pivo.getCpf())
    		return conta.getCpf() < pivo.getCpf();
    	
    	// Verificação de chaves secundárias: (agencia e conta)
    	
    	if(conta.getAgencia() != pivo.getAgencia())
    		return conta.getAgencia() < pivo.getAgencia();
    	
    	return conta.getConta() < pivo.getConta();
    }

    private static void trocar(ListaDupla<Conta> lista, int i, int j) {
        Conta temp = lista.get(i);
        lista.set(i, lista.get(j));
        lista.set(j, temp);
    }
}
