package principal;

import java.io.IOException;

import io.LerArquivoConta;
import io.LerArquivoCpf;
import model.Conta;
import ordenacao.QuickSort;
import ordenacao.ShellSort;
import util.arvore.abb.ArvoreABB;
import util.arvore.avl.ArvoreAVL;
import util.hashing.Hashing;
import util.listadupla.ListaDupla;

public abstract class App
{
    public static void main(String[] args)
    {
        LerArquivoConta lerConta;
        LerArquivoCpf lerCpf;
        int[] nRegistros = { 500, 1000, 5000, 10000, 50000 };
        
        try {
        	// Ler arquivo CPF.txt
        	lerCpf = new LerArquivoCpf("src/files/entrada/cpf/CPF.txt");
        	ListaDupla<Long> cpfs = lerCpf.leArquivo();
        	lerCpf.fechaArquivo();
        	
        	for(int n: nRegistros)
        	{
        		// Ler atquivo conta`n`.txt
        		lerConta = new LerArquivoConta("src/files/entrada/contas/conta" + n + ".txt");
                ListaDupla<Conta> contas = lerConta.leArquivo();
                lerConta.fechaArquivo();
                
                System.out.println(n + " REGISTROS");
        		
        		quickSort(n, contas);
            	shellSort(n, contas);
            	abb(n, contas, cpfs);
            	avl(n, contas, cpfs);
            	hashing(n, contas, cpfs);
            	
            	System.out.println();
        	}
        }
        catch(IOException e) {
        	e.printStackTrace();
        }
    }
    
    private static void quickSort(int nReg, ListaDupla<Conta> contas)
    {
    	AppGravacao.registrarContas(
    		"quicksort", nReg, contas.clone(), new QuickSort()
    	);
    }
    
    private static void shellSort(int nReg, ListaDupla<Conta> contas)
    {
    	AppGravacao.registrarContas(
    		"shellsort", nReg, contas.clone(), new ShellSort()
    	);
    }
    
    private static void abb(int nReg, ListaDupla<Conta> contas, ListaDupla<Long> cpfs)
    {
    	ArvoreABB abb = new ArvoreABB(contas);
    	abb.balancear();
    	
    	AppGravacao.registrarBuscas("abb", nReg, cpfs, abb);
    }
    
    private static void avl(int nReg, ListaDupla<Conta> contas, ListaDupla<Long> cpfs)
    {
    	ArvoreAVL avl = new ArvoreAVL(contas);
    	
    	AppGravacao.registrarBuscas("avl", nReg, cpfs, avl);
    }
    
    public static void hashing(int nReg, ListaDupla<Conta> contas, ListaDupla<Long> cpfs)
    {
    	// Tamanho ideal para o Hashing Encadeado:
    	// Numero primo mais próximo de (`nReg` * 1.1)
    	int hashSize = 0;
    	switch(nReg) {
    		case 500:   hashSize = 557;   break;
    		case 1000:  hashSize = 1103;  break;
    		case 5000:  hashSize = 5501;  break;
    		case 10000: hashSize = 11069; break;
    		case 50000: hashSize = 55001; break;
    	}
    	
    	Hashing hashing = new Hashing(hashSize, contas);
    	
    	AppGravacao.registrarBuscas("hashing", nReg, cpfs, hashing);
    }
}
