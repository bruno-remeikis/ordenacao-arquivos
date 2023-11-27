package ordenacao;

import util.listadupla.ListaDupla;
import model.Conta;

public class QuickSort implements Sort
{
	@Override
	public ListaDupla<Conta> sort(ListaDupla<Conta> lista)
	{
		quickSort(lista, 0, lista.getQuantNos() - 1);
    	return lista;
	}
    
    private static void quickSort(ListaDupla<Conta> lista, int inicio, int fim) {
        if (inicio < fim) {
            int indiceParticao = particionar(lista, inicio, fim);

            // Recursivamente ordena os subarrays à esquerda e à direita do índice de partição
            quickSort(lista, inicio, indiceParticao - 1);
            quickSort(lista, indiceParticao + 1, fim);
        }
    }

    private static int particionar(ListaDupla<Conta> lista, int inicio, int fim) {
        Conta pivo = lista.get(fim);
        int indiceMenor = inicio - 1;

        // Percorre o array e coloca os elementos menores que o pivo à esquerda e os maiores à direita
        for (int i = inicio; i < fim; i++) {
            if (/*lista.get(i).getCpf() <= pivo.getCpf()*/ comparar(lista.get(i), pivo)) {
                indiceMenor++;
                trocar(lista, indiceMenor, i);
            }
        }

        // Coloca o pivo na posição correta
        trocar(lista, indiceMenor + 1, fim);

        return indiceMenor + 1;
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
