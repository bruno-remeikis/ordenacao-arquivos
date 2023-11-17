package io;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;

public class LerArquivoCpf extends LerArquivo
{
    public LerArquivoCpf(String nome) throws FileNotFoundException {
        super(nome);
    }
    
    @Override
    public List leArquivo() throws NoSuchElementException, ArrayIndexOutOfBoundsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Object separaDados(String linha) throws NoSuchElementException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
