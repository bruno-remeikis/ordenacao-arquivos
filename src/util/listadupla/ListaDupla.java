package util.listadupla;

import java.util.List;

public class ListaDupla<T>/* implements Cloneable*/ {

    private NoDupla<T> prim;
    private NoDupla<T> ult;
    private int quantNos;

    public ListaDupla() {
        this.prim = null;
        this.ult = null;
        this.quantNos = 0;
    }
    
    public ListaDupla(List<T> list) {
        this();
        for(T obj: list)
            this.inserir(obj);
    }
    
    public int size() {
        return this.quantNos;
    }

    public int getQuantNos() {
        return this.quantNos;
    }

    public NoDupla<T> getPrim() {
        return this.prim;
    }

    public NoDupla<T> getUlt() {
        return this.ult;
    }

    public void setQuantNos(int valorNovo) {
        this.quantNos = valorNovo;
    }

    public void setPrim(NoDupla<T> novoNo) {
        this.prim = novoNo;
    }

    public void setUlt(NoDupla<T> novoNo) {
        this.ult = novoNo;
    }

    public boolean eVazia() {
        return (this.prim == null);
    }
    //insere um novo n� no final da lista ou se a lista estiver vazia, insere
    // o primeiro n� na lista

    public void inserir(T elem) {
        NoDupla<T> novoNo = new NoDupla<T>(elem);
        if (this.eVazia()) {
            this.prim = novoNo;
        } else {
            novoNo.setAnt(this.ult);
            this.ult.setProx(novoNo);
        }
        this.ult = novoNo;
        this.quantNos++;
    }
    
    public T get(int i)
    {
        NoDupla<T> no = prim;
        for(int j = 0; j < i; j++)
            no = no.getProx();
        
        return no.getInfo();
    }
    
    public void set(int i, T obj)
    {
        NoDupla<T> no = prim;
        for(int j = 0; j < i; j++)
            no = no.getProx();
        
        no.setInfo(obj);
    }

    public void printAll() {
        NoDupla<T> no = prim;
        while(no != null) {
            System.out.println(no.getInfo().toString());
            no = no.getProx();
        }
    }

    @Override
    public ListaDupla<T> clone()
    {
        ListaDupla<T> clone = new ListaDupla();
        NoDupla<T> no = prim;
        while(no != null) {
            clone.inserir(no.getInfo());
            no = no.getProx();
        }
        return clone;
    }
}
