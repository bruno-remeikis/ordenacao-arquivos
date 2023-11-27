package util.arvore.abb;

import model.Conta;
import util.listadupla.ListaDupla;

public class NoABB
{
	long chave;
	private ListaDupla<Conta> contas;
	private NoABB esq, dir;
	
	public NoABB(long chave, Conta conta) {
		this.chave = chave;
        this.contas = new ListaDupla<>();
        this.contas.inserir(conta);
        this.esq = this.dir = null;
	}

	public long getChave() {
		return chave;
	}

	public void setChave(long chave) {
		this.chave = chave;
	}

	public ListaDupla<Conta> getContas() {
		return contas;
	}

	public void setContas(ListaDupla<Conta> contas) {
		this.contas = contas;
	}

	public NoABB getEsq() {
		return esq;
	}

	public void setEsq(NoABB esq) {
		this.esq = esq;
	}

	public NoABB getDir() {
		return dir;
	}

	public void setDir(NoABB dir) {
		this.dir = dir;
	}
}
