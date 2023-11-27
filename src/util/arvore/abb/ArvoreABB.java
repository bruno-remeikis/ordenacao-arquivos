package util.arvore.abb;

import model.Conta;
import util.arvore.ArvoreBusca;
import util.listacontigua.LCConta;
import util.listadupla.ListaDupla;
import util.listadupla.NoDupla;

public class ArvoreABB implements ArvoreBusca
{
	private NoABB raiz;
    private int quantNos;
    private int quantItens;

    public ArvoreABB() {
        this.raiz = null;
        this.quantNos = 0;
        this.quantItens = 0;
    }
    
    public ArvoreABB(ListaDupla<Conta> lista) {
    	super();
    	
    	NoDupla<Conta> no = lista.getPrim();
    	while(no != null) {
    		this.inserir(no.getInfo().getCpf(), no.getInfo());
    		no = no.getProx();
    	}
    }

    private NoABB inserir(NoABB raiz, long chave, Conta conta) {
        if (raiz == null) {
            quantNos++;
            quantItens++;
            return new NoABB(chave, conta);
        }

        if (chave < raiz.getChave()) {
            raiz.setEsq(inserir(raiz.getEsq(), chave, conta));
        } else if (chave > raiz.getChave()) {
            raiz.setDir(inserir(raiz.getDir(), chave, conta));
        } else {
            raiz.getContas().inserir(conta);
            quantItens++;
        }

        return raiz;
    }

    public void inserir(long chave, Conta conta) {
        raiz = inserir(raiz, chave, conta);
    }
    
    @Override
    public ListaDupla<Conta> buscar(long chave) {
        return buscar(raiz, chave);
    }

    private ListaDupla<Conta> buscar(NoABB raiz, long chave) {
        ListaDupla<Conta> resultado = new ListaDupla<Conta>();

        if (raiz == null) {
            return resultado;
        }

        if (chave < raiz.getChave()) {
            resultado.inserirTudo(buscar(raiz.getEsq(), chave));
        } else if (chave > raiz.getChave()) {
            resultado.inserirTudo(buscar(raiz.getDir(), chave));
        } else {
            resultado.inserirTudo(raiz.getContas());
        }

        return resultado;
    }

/*
    private NoABB remover(NoABB raiz, long chave, Conta conta) {
        if (raiz == null) {
            return null;
        }

        if (chave < raiz.getChave()) {
            raiz.setEsq(remover(raiz.getEsq(), chave, conta));
        } else if (chave > raiz.getChave()) {
            raiz.setDir(remover(raiz.getDir(), chave, conta));
        } else {
            raiz.getContas().remover(conta);
            quantItens--;

            if (raiz.getContas().isEmpty()) {
                quantNos--;

                if (raiz.getEsq() == null) {
                    return raiz.getDir();
                } else if (raiz.getDir() == null) {
                    return raiz.getEsq();
                }

                raiz.setChave(valorMinimo(raiz.getDir()));
                raiz.setDir(remover(raiz.getDir(), raiz.getChave(), conta));
            }
        }

        return raiz;
    }

    public void remover(long chave, Conta conta) {
        raiz = remover(raiz, chave, conta);
    }

    private long valorMinimo(NoABB raiz) {
        long valorMinimo = raiz.getChave();
        while (raiz.getEsq() != null) {
            valorMinimo = raiz.getEsq().getChave();
            raiz = raiz.getEsq();
        }
        return valorMinimo;
    }
*/

    public int getQuantNos() {
        return quantNos;
    }

    public int getQuantItens() {
        return quantItens;
    }
    
    // Método para balancear a árvore
    public void balancear() {
        ListaDupla<NoABB> nodes = new ListaDupla<NoABB>();
        inOrderTraversal(raiz, nodes);

        // Construir uma nova árvore a partir da lista ordenada
        raiz = buildBalancedTree(nodes, 0, nodes.size() - 1);
    }

    // Percorre a árvore em ordem e adiciona os nós à lista
    private void inOrderTraversal(NoABB root, ListaDupla<NoABB> nodes) {
        if (root != null) {
            inOrderTraversal(root.getEsq(), nodes);
            nodes.inserir(root);
            inOrderTraversal(root.getDir(), nodes);
        }
    }

    // Constrói uma árvore balanceada a partir de uma lista ordenada
    private NoABB buildBalancedTree(ListaDupla<NoABB> nodes, int inicio, int fim) {
        if (inicio > fim) {
            return null;
        }

        int meio = (inicio + fim) / 2;
        NoABB noMeio = nodes.get(meio);

        // Construir a subárvore esquerda e direita recursivamente
        noMeio.setEsq(buildBalancedTree(nodes, inicio, meio - 1));
        noMeio.setDir(buildBalancedTree(nodes, meio + 1, fim));

        return noMeio;
    }
}
