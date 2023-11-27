/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util.arvore.avl;

import model.Conta;
import util.arvore.ArvoreBusca;
import util.listadupla.ListaDupla;
import util.listadupla.NoDupla;

/**
 *
 * @author acata
 */
public class ArvoreAVL implements ArvoreBusca {
    
    private NoAVL raiz;
    
    public ArvoreAVL() {}
    
    public ArvoreAVL(ListaDupla<Conta> lista) {
    	NoDupla<Conta> no = lista.getPrim();
    	while(no != null) {
    		this.inserir(no.getInfo());
    		no = no.getProx();
    	}
    }

    private int altura(NoAVL no) {
        if (no == null) return 0;
        return no.altura;
    }

    private int calculaBalanceamento(NoAVL no) {
        if (no == null) return 0;
        return altura(no.esq) - altura(no.dir);
    }

    private NoAVL rotacaoDireita(NoAVL y) {
        NoAVL x = y.esq;
        NoAVL T2 = x.dir;

        x.dir = y;
        y.esq = T2;

        y.altura = Math.max(altura(y.esq), altura(y.dir)) + 1;
        x.altura = Math.max(altura(x.esq), altura(x.dir)) + 1;

        return x;
    }

    private NoAVL rotacaoEsquerda(NoAVL x) {
        NoAVL y = x.dir;
        NoAVL T2 = y.esq;

        y.esq = x;
        x.dir = T2;

        x.altura = Math.max(altura(x.esq), altura(x.dir)) + 1;
        y.altura = Math.max(altura(y.esq), altura(y.dir)) + 1;

        return y;
    }

/*
    public void inserir(Conta conta) {
        raiz = inserirRecursivo(raiz, conta);
    }

    private NoAVL inserirRecursivo(NoAVL no, Conta conta) {
        if (no == null) return new NoAVL(conta);

        if (conta.getCpf() < no.conta.getCpf()) {
            no.esq = inserirRecursivo(no.esq, conta);
        } else if (conta.getCpf() > no.conta.getCpf()) {
            no.dir = inserirRecursivo(no.dir, conta);
        } else {
            // Tratamento para evitar chaves duplicadas, se necessário
            return no;
        }

        no.altura = 1 + Math.max(altura(no.esq), altura(no.dir));

        int balanceamento = calculaBalanceamento(no);

        // Casos de rotação
        if (balanceamento > 1 && conta.getCpf() < no.esq.conta.getCpf()) {
            return rotacaoDireita(no);
        }
        if (balanceamento < -1 && conta.getCpf() > no.dir.conta.getCpf()) {
            return rotacaoEsquerda(no);
        }
        if (balanceamento > 1 && conta.getCpf() > no.esq.conta.getCpf()) {
            no.esq = rotacaoEsquerda(no.esq);
            return rotacaoDireita(no);
        }
        if (balanceamento < -1 && conta.getCpf() < no.dir.conta.getCpf()) {
            no.dir = rotacaoDireita(no.dir);
            return rotacaoEsquerda(no);
        }

        return no;
    }
*/
    
    public void inserir(Conta conta) {
        raiz = inserirRecursivo(raiz, conta);
    }

    private NoAVL inserirRecursivo(NoAVL no, Conta conta) {
        if (no == null) return new NoAVL(conta);

        if (conta.getCpf() <= no.conta.getCpf()) {
            no.esq = inserirRecursivo(no.esq, conta);
        } else {
            no.dir = inserirRecursivo(no.dir, conta);
        }

        no.altura = 1 + Math.max(altura(no.esq), altura(no.dir));

        int balanceamento = calculaBalanceamento(no);

        // Condições de rotação
        if (balanceamento > 1 && conta.getCpf() <= no.esq.conta.getCpf()) {
            return rotacaoDireita(no);
        }
        if (balanceamento < -1 && conta.getCpf() > no.dir.conta.getCpf()) {
            return rotacaoEsquerda(no);
        }
        if (balanceamento > 1 && conta.getCpf() > no.esq.conta.getCpf()) {
            no.esq = rotacaoEsquerda(no.esq);
            return rotacaoDireita(no);
        }
        if (balanceamento < -1 && conta.getCpf() <= no.dir.conta.getCpf()) {
            no.dir = rotacaoDireita(no.dir);
            return rotacaoEsquerda(no);
        }

        return no;
    }
    
/*
    // Método de busca na árvore AVL
    public NoAVL buscar(long cpf) {
        return buscarRecursivo(raiz, cpf);
    }

    private NoAVL buscarRecursivo(NoAVL no, long cpf) {
        if (no == null || no.conta.getCpf() == cpf) {
            return no;
        }

        if (cpf < no.conta.getCpf()) {
            return buscarRecursivo(no.esq, cpf);
        } else {
            return buscarRecursivo(no.dir, cpf);
        }
    }
*/
    
    @Override
    public ListaDupla<Conta> buscar(long cpf) {
        ListaDupla<Conta> nosEncontrados = new ListaDupla<Conta>();
        buscarRecursivo(raiz, cpf, nosEncontrados);
        return nosEncontrados;
    }

    private void buscarRecursivo(NoAVL no, long cpf, ListaDupla<Conta> nosEncontrados) {
        if (no == null) {
            return;
        }

        if (cpf < no.conta.getCpf()) {
            buscarRecursivo(no.esq, cpf, nosEncontrados);
        } else if (cpf > no.conta.getCpf()) {
            buscarRecursivo(no.dir, cpf, nosEncontrados);
        } else {
            // Encontrou um nó com o CPF buscado
            nosEncontrados.inserir(no.getConta());

            // Procura por outros nós com o mesmo CPF na subárvore esquerda
            buscarRecursivo(no.esq, cpf, nosEncontrados);

            // Procura por outros nós com o mesmo CPF na subárvore direita
            buscarRecursivo(no.dir, cpf, nosEncontrados);
        }
    }

    // Método de remoção na árvore AVL
    public void remover(long cpf) {
        raiz = removerRecursivo(raiz, cpf);
    }

    private NoAVL removerRecursivo(NoAVL no, long cpf) {
        if (no == null) {
            return no;
        }

        if (cpf < no.conta.getCpf()) {
            no.esq = removerRecursivo(no.esq, cpf);
        } else if (cpf > no.conta.getCpf()) {
            no.dir = removerRecursivo(no.dir, cpf);
        } else {
            if (no.esq == null || no.dir == null) {
                NoAVL temp = null;
                if (temp == no.esq) {
                    temp = no.dir;
                } else {
                    temp = no.esq;
                }

                if (temp == null) {
                    temp = no;
                    no = null;
                } else {
                    no = temp;
                }
            } else {
                NoAVL temp = menorValorNo(no.dir);
                no.conta = temp.conta;
                no.dir = removerRecursivo(no.dir, temp.conta.getCpf());
            }
        }

        if (no == null) {
            return no;
        }

        no.altura = Math.max(altura(no.esq), altura(no.dir)) + 1;

        int balanceamento = calculaBalanceamento(no);

        if (balanceamento > 1 && calculaBalanceamento(no.esq) >= 0) {
            return rotacaoDireita(no);
        }
        if (balanceamento > 1 && calculaBalanceamento(no.esq) < 0) {
            no.esq = rotacaoEsquerda(no.esq);
            return rotacaoDireita(no);
        }
        if (balanceamento < -1 && calculaBalanceamento(no.dir) <= 0) {
            return rotacaoEsquerda(no);
        }
        if (balanceamento < -1 && calculaBalanceamento(no.dir) > 0) {
            no.dir = rotacaoDireita(no.dir);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    private NoAVL menorValorNo(NoAVL no) {
        NoAVL atual = no;
        while (atual.esq != null) {
            atual = atual.esq;
        }
        return atual;
    }

    // Percurso inordem na árvore AVL
    public void percursoInordem() {
        percursoInordemRecursivo(raiz);
    }

    private void percursoInordemRecursivo(NoAVL no) {
        if (no != null) {
            percursoInordemRecursivo(no.esq);
            System.out.print("Conta: { CPF: " + no.conta.getCpf() + ", agencia: " + no.conta.getAgencia() + ", conta: " 
                    + no.conta.getConta()+ ", saldo: " + no.conta.getSaldo() + " }\n" );
            percursoInordemRecursivo(no.dir);
        }
    }

    // Percurso pré-ordem na árvore AVL
    public void percursoPreOrdem() {
        percursoPreOrdemRecursivo(raiz);
    }

    private void percursoPreOrdemRecursivo(NoAVL no) {
        if (no != null) {
            System.out.print("Conta: { CPF: " + no.conta.getCpf() + ", agencia: " + no.conta.getAgencia() + ", conta: " 
                    + no.conta.getConta()+ ", saldo: " + no.conta.getSaldo() + " }\n" );
            percursoPreOrdemRecursivo(no.esq);
            percursoPreOrdemRecursivo(no.dir);
        }
    }

    // Percurso pós-ordem na árvore AVL
    public void percursoPosOrdem() {
        percursoPosOrdemRecursivo(raiz);
    }

    private void percursoPosOrdemRecursivo(NoAVL no) {
        if (no != null) {
            percursoPosOrdemRecursivo(no.esq);
            percursoPosOrdemRecursivo(no.dir);
            System.out.print("Conta: { CPF: " + no.conta.getCpf() + ", agencia: " + no.conta.getAgencia() + ", conta: " 
                    + no.conta.getConta()+ ", saldo: " + no.conta.getSaldo() + " }\n" );
        }
        
    }
}
