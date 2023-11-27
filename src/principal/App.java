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
        String[] nRegistros = { "500", "1000", "5000", "10000", "50000" };
        
        try {
        	// Ler arquivo CPF.txt
        	lerCpf = new LerArquivoCpf("src/files/entrada/cpf/CPF.txt");
        	ListaDupla<Long> cpfs = lerCpf.leArquivo();
        	lerCpf.fechaArquivo();
        	
        	for(String n: nRegistros)
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
    
    private static void quickSort(String nReg, ListaDupla<Conta> contas)
    {
    	AppGravacao.registrarContas(
    		"quicksort", nReg, contas.clone(), new QuickSort()
    	);
    }
    
    private static void shellSort(String nReg, ListaDupla<Conta> contas)
    {
    	AppGravacao.registrarContas(
    		"shellsort", nReg, contas.clone(), new ShellSort()
    	);
    }
    
    private static void abb(String nReg, ListaDupla<Conta> contas, ListaDupla<Long> cpfs)
    {
    	ArvoreABB abb = new ArvoreABB(contas);
    	abb.balancear();
    	
    	AppGravacao.registrarBuscas("abb", nReg, cpfs, abb);
    }
    
    private static void avl(String nReg, ListaDupla<Conta> contas, ListaDupla<Long> cpfs)
    {
    	ArvoreAVL avl = new ArvoreAVL(contas);
    	
    	AppGravacao.registrarBuscas("avl", nReg, cpfs, avl);
    }
    
    public static void hashing(String nReg, ListaDupla<Conta> contas, ListaDupla<Long> cpfs)
    {
    	Hashing hashing = new Hashing(353, contas);
    	
    	AppGravacao.registrarBuscas("hashing", nReg, cpfs, hashing);
    }
}
