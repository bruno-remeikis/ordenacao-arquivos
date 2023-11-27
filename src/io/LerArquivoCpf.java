package io;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

import model.Conta;
import util.listadupla.ListaDupla;

public class LerArquivoCpf extends LerArquivo<Long>
{
	public LerArquivoCpf(String nome) throws FileNotFoundException
	{
		super(nome);
	}
	
	@Override
	public ListaDupla<Long> leArquivo() throws NoSuchElementException, ArrayIndexOutOfBoundsException
	{
		ListaDupla<Long> cpfs = new ListaDupla<Long>();
        String linha;
        try {
            // A função hasNext() indica se ainda existe uma String para ser lida.
            while(this.entrada.hasNext())
            {
                // A função nextLine() devolve a próxima linha como  uma String.
                linha = this.entrada.nextLine();
                
                cpfs.inserir(separaDados(linha));
            }
            
            return cpfs;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Arquivo corrompido");
        }
	}

	@Override
	protected Long separaDados(String linha) throws NoSuchElementException
	{
		try {
            return Long.parseLong(linha);
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException("ARQUIVO DIFERENTE DO REGISTRO: " + e.getMessage());
        }
	}

}
