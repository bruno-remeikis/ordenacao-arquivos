package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import util.listadupla.ListaDupla;

public abstract class LerArquivo<T> {

    // objeto do tipo Scanner para realizar a leitura dos dados do arquivo.
    protected Scanner entrada;
    
    /**
     * Construtor
     *
     * @param nome => Nome do arquivo que sera aberto para leitura
     * @throws FileNotFoundException => Excecao se nao encontrar o arquivo
     */
    public LerArquivo(String nome) throws FileNotFoundException
    {
        try {
            // Instanciamento do objeto do tipo Scanner, tendo como argumento
            // File que será o arquivo que será lido
            this.entrada = new Scanner(new File(nome));
        }
        catch (FileNotFoundException e) {
            throw new FileNotFoundException("ARQUIVO NAO ENCONTRADO");
        }
    }

    /**
     * Metodo para ler os dados contidos no arquivo
     *
     * @return => Lista de elementos do tipo T
     * @throws IllegalStateException => Excecao se houver erro ao ler o arquivo
     */
    public abstract ListaDupla<T> leArquivo()
        throws NoSuchElementException, ArrayIndexOutOfBoundsException;
   
            
    /**
     * Metodo para transformar uma linha do arquivo em um objeto do tipo T
     *
     * @param linha => String contendo a linha do arquivo que sera transformada
     * @return => o objeto T criado a partir da linha passada
     * @throws NoSuchElementException => Excecao causada por elementos
     * insuficientes na String, durante a transformacao
     */
    protected abstract T separaDados(String linha) throws NoSuchElementException;

    /**
     * Metodo para fechar o arquivo de leitura
     *
     * @throws IllegalStateException => Excecao causada se nao conseguir fechar
     * o arquivo.
     */
    public void fechaArquivo() throws IllegalStateException
    {
        try {
            this.entrada.close();
        }
        catch (IllegalStateException e) {
            throw new IllegalStateException("ERRO AO FECHAR O ARQUIVO: " + e.getMessage());
        }
    }
}
