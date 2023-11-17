package io;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import model.Conta;
import util.listadupla.ListaDupla;

public class LerArquivoConta extends LerArquivo<Conta>
{
    public LerArquivoConta(String nome) throws FileNotFoundException
    {
        super(nome);
    }
    
    @Override
    public ListaDupla<Conta> leArquivo()
        throws NoSuchElementException, ArrayIndexOutOfBoundsException
    {
        ListaDupla<Conta> contas = new ListaDupla();
        String linha;
        try {
            // A função hasNext() indica se ainda existe uma String para ser lida.
            while(this.entrada.hasNext())
            {
                // A função nextLine() devolve a próxima linha como  uma String.
                linha = this.entrada.nextLine();
                
                contas.inserir(separaDados(linha));
            }
            return contas;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Arquivo corrompido");
        }
    }
    
    @Override
    protected Conta separaDados(String linha) throws NoSuchElementException
    {
        try {
            // O método split quebra uma String em várias substrings a partir
            // do caracter definido como argumento,nesse caso ";", cria
            // um vetor de String e armazena cada substring em um posicao
            String[] dados = linha.split(";");

            Conta conta = new Conta();
            conta.setAgencia( Integer.parseInt(dados[0]) );
            conta.setConta( Integer.parseInt(dados[1]) );
            conta.setSaldo( Float.parseFloat(dados[2]) );
            conta.setCpf( Long.parseLong(dados[3]) );
            
            return conta;
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException("ARQUIVO DIFERENTE DO REGISTRO: " + e.getMessage());
        }
    }
}
