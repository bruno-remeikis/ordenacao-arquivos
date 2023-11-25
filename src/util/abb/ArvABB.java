package util.abb;

import model.Conta;
import util.listacontigua.LCConta;

public class ArvABB {
    private NoABB raiz;
    private int quant;

    public ArvABB() {
        this.raiz = null;
        this.quant = 0;
    }

    public NoABB getRaiz() {
        return raiz;
    }

    public int getQuant() {
        return quant;
    }

    public boolean eVazia() {
        return quant == 0;
    }

    public NoABB pesquisa(long cpf) {
        return pesquisa(cpf, this.raiz);
    }

    private NoABB pesquisa(long cpf, NoABB no) {
        if (no == null) {
            return null;
        } else if (cpf == no.getConta().getCpf()) {
            return no;
        } else if (cpf < no.getConta().getCpf()) {
            return pesquisa(cpf, no.getEsq());
        } else {
            return pesquisa(cpf, no.getDir());
        }
    }

    public boolean insere(Conta conta) {
        if (pesquisa(conta.getCpf()) == null) {
            this.raiz = insere(conta, this.raiz);
            return true;
        } else {
            return false;
        }
    }

    private NoABB insere(Conta conta, NoABB no) {
        if (no == null) {
            NoABB novo = new NoABB(conta);
            this.quant++;
            return novo;
        } else if (conta.getCpf() < no.getConta().getCpf()) {
            no.setEsq(insere(conta, no.getEsq()));
        } else {
            no.setDir(insere(conta, no.getDir()));
        }
        return no;
    }

    public boolean remove(long cpf) {
        this.raiz = remove(cpf, this.raiz);
        return true; // A remoção sempre retornará true por enquanto
    }

    private NoABB remove(long cpf, NoABB no) {
        if (no == null) {
            return null;
        }
        if (cpf < no.getConta().getCpf()) {
            no.setEsq(remove(cpf, no.getEsq()));
        } else if (cpf > no.getConta().getCpf()) {
            no.setDir(remove(cpf, no.getDir()));
        } else {
            if (no.getEsq() == null) {
                return no.getDir();
            } else if (no.getDir() == null) {
                return no.getEsq();
            }

            NoABB temp = encontrarMenorNo(no.getDir());
            no.setConta(temp.getConta());
            no.setDir(remove(temp.getConta().getCpf(), no.getDir()));
        }
        return no;
    }

    private NoABB encontrarMenorNo(NoABB no) {
        while (no.getEsq() != null) {
            no = no.getEsq();
        }
        return no;
    }
    
public LCConta camInOrdem() {
    LCConta lista = new LCConta(this.quant);
    fazCamInOrdem(lista, this.raiz);
    return lista;
}

private void fazCamInOrdem(LCConta lista, NoABB no) {
    if (no != null) {
        fazCamInOrdem(lista, no.getEsq());
        lista.insereFim(no.getConta());
        fazCamInOrdem(lista, no.getDir());
    }
}

public LCConta camPreOrdem() {
    LCConta lista = new LCConta(this.quant);
    fazCamPreOrdem(lista, this.raiz);
    return lista;
}

private void fazCamPreOrdem(LCConta lista, NoABB no) {
    if (no != null) {
        lista.insereFim(no.getConta());
        fazCamPreOrdem(lista, no.getEsq());
        fazCamPreOrdem(lista, no.getDir());
    }
}

public LCConta camPosOrdem() {
    LCConta lista = new LCConta(this.quant);
    fazCamPosOrdem(lista, this.raiz);
    return lista;
}

private void fazCamPosOrdem(LCConta lista, NoABB no) {
    if (no != null) {
        fazCamPosOrdem(lista, no.getEsq());
        fazCamPosOrdem(lista, no.getDir());
        lista.insereFim(no.getConta());
    }
}

public ArvABB balancear() {
    LCConta lista = camInOrdem();
    ArvABB arvAux = new ArvABB();
    balancear(lista, arvAux, 0, lista.getQuant() - 1);
    return arvAux;
}

private void balancear(LCConta lista, ArvABB arv, int inicio, int fim) {
    if (inicio <= fim) {
        int meio = (inicio + fim) / 2;
        Conta conta = lista.getConta(meio);
        arv.insere(conta);
        balancear(lista, arv, inicio, meio - 1);
        balancear(lista, arv, meio + 1, fim);
    }
}
}
