package util.listacontigua;

import model.Conta;

public class LCConta {
    private Conta[] lista;
    private int quant;

    public LCConta(int tamanho) {
        this.lista = new Conta[tamanho];
        this.quant = 0;
    }

    public int getQuant() {
        return this.quant;
    }

    public int tamanho() {
        return this.lista.length;
    }

    public boolean eVazio() {
        return quant == 0;
    }

    public boolean eCheio() {
        return quant == lista.length;
    }

    public Conta getConta(int pos) {
        if (pos < 0 || pos >= quant) {
            return null;
        } else {
            return this.lista[pos];
        }
    }

    public int pesquisa(Conta conta) {
        for (int i = 0; i < quant; i++) {
            if (conta.getCpf() == lista[i].getCpf()) {
                return i;
            }
        }
        return -1;
    }

    public int insereFim(Conta conta) {
        if (eCheio()) {
            return -1;
        }
        lista[quant] = conta;
        quant++;
        return 0;
    }

    public int insere(Conta conta, int pos) {
        if (eCheio()) {
            return -1;
        }
        if (pos < 0 || pos > quant) {
            return -2;
        }
        for (int i = quant - 1; i >= pos; i--) {
            lista[i + 1] = lista[i];
        }
        lista[pos] = conta;
        quant++;
        return 0;
    }

    public boolean remove(Conta conta) {
        int pos = pesquisa(conta);
        if (pos == -1) {
            return false;
        }
        for (int i = pos; i < quant - 1; i++) {
            lista[i] = lista[i + 1];
        }
        quant--;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder aux = new StringBuilder();
        for (int i = 0; i < quant; i++) {
            aux.append(lista[i].toString()).append("   " + "\n");
        }
        return aux.toString();
    }
}