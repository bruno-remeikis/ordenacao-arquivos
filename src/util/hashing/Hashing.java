package util.hashing;

import model.Conta;
import ordenacao.QuickSort;
import util.arvore.ArvoreBusca;
import util.listadupla.ListaDupla;
import util.listadupla.NoDupla;

public class Hashing implements ArvoreBusca
{	
	private int operador; 
	private ListaDupla<Conta>[] tabela; 

	public Hashing(int operador) {
		this.operador = operador;
        this.tabela = new ListaDupla[operador];

        for (int i = 0; i < operador; i++) {
            tabela[i] = new ListaDupla<Conta>();
        }
    }
	
	public Hashing(int operador, ListaDupla<Conta> lista) {
		this(operador);
		
		NoDupla<Conta> no = lista.getPrim();
		while(no != null) {
			this.inserir(no.getInfo());
			no = no.getProx();
		}
	}

    // Método para calcular o índice na tabela de hash
    private int calcularIndice(long chave) {
        return (int) (chave % operador);
    }

    // Método para adicionar uma conta à tabela de hash
    public void inserir(Conta conta) {
        int indice = calcularIndice(conta.getCpf());
        tabela[indice].inserir(conta);
    }

    // Método para pesquisar contas com uma determinada chave na tabela de hash
    @Override
    public ListaDupla<Conta> buscar(long chave) {
        int indice = calcularIndice(chave);
        return buscar(chave, indice);
    }
    
    private ListaDupla<Conta> buscar(long chave, int indice) {
    	ListaDupla<Conta> pesquisa = new ListaDupla<Conta>();
    	
    	NoDupla<Conta> no = tabela[indice].getPrim();
    	while(no != null) {
    		if(no.getInfo().getCpf() == chave)
    			pesquisa.inserir(no.getInfo());
    		no = no.getProx();
    	}
    	
    	return pesquisa;
    }
}
