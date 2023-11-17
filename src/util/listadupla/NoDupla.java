package util.listadupla;

public class NoDupla<T> {

    private T info;	// o tipo Item est� declarado no capitulo 1
    private NoDupla prox;
    private NoDupla ant;

    public NoDupla(T elem) {
        this.info = elem;
        this.prox = null;
        this.ant = null;
    }

    public T getInfo() {
        return this.info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public NoDupla getProx() {
        return this.prox;
    }

    public NoDupla getAnt() {
        return this.ant;
    }

    public void setProx(NoDupla novoNo) {
        this.prox = novoNo;
    }

    public void setAnt(NoDupla novoNo) {
        this.ant = novoNo;
    }

}
