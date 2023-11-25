package util.abb;

import model.Conta;

public class NoABB {
	private Conta conta;
	private NoABB esq, dir;
	public NoABB(Conta conta) {
		this.conta = conta;
		this.esq = null;
		this.dir = null;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
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
