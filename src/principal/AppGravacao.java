package principal;

import java.io.IOException;

import io.GravaArquivo;
import model.Conta;
import ordenacao.Sort;
import util.arvore.ArvoreBusca;
import util.listadupla.ListaDupla;
import util.listadupla.NoDupla;

public abstract class AppGravacao
{
	public static void registrarContas(String folder, int nArq, ListaDupla<Conta> contas, Sort s)
    {
        String path = "src/files/saida/" + folder + "/conta" + nArq + ".txt";
        
        // Salvar tempo inicial para calcular para que, ao final do processo,
        // tenhamos o tempo que demorou (em milisegundos)
        long t0 = System.currentTimeMillis();
        
        // Ordenar
        contas = s.sort(contas.clone());
        
        try {
            GravaArquivo g = new GravaArquivo(path);

            // Acessar cada nó da lista ordenada e gravar seu registro em arquivo
            NoDupla<Conta> no = contas.getPrim();
            while(no != null) {
                g.gravaArquivo(no.getInfo().toRegistro());
                no = no.getProx();
            }

            g.fechaArquivo();
        }
        catch(IOException e) {
            System.out.println("Erro ao salvar o arquivo no caminho \"" + path + '\"');
        }
        
        // Calcular e printar tempo (em milisegundos) que o processo tomou para ser executado
        System.out.println("- " + folder + ": " + (System.currentTimeMillis() - t0) + "ms");
    }
    
    public static void registrarBuscas(String folder, int nReg, ListaDupla<Long> cpfs, ArvoreBusca ab)
    {
    	String path = "src/files/saida/" + folder + "/conta" + nReg + ".txt";
    	
    	// Salvar tempo inicial para calcular para que, ao final do processo,
        // tenhamos o tempo que demorou (em milisegundos)
    	long t0 = System.currentTimeMillis();
    	
    	try {
    		GravaArquivo g = new GravaArquivo(path);
	    	
    		// Acessar cada CPF para realizar busca
	    	NoDupla<Long> no = cpfs.getPrim();
	    	while(no != null)
	    	{
	    		// Buscar CPF na estrutura (árvore ou tabela hashing)
	    		ListaDupla<Conta> contasPesq = ab.buscar(no.getInfo());
					
	    		// Gravar resultado da busca em arquivo
				g.gravaArquivo("CPF " + no.getInfo() + ":\n");
				if(contasPesq.eVazia()) {
					g.gravaArquivo("INEXISTENTE\n");
				}
				else {
					NoDupla<Conta> noPesq = contasPesq.getPrim();
					float saldoTotal = 0;
					// Acessar cada resultado da busca para gravar no arquivo
					while(noPesq != null)
					{
						Conta c = noPesq.getInfo();
						saldoTotal += c.getSaldo();
						
						g.gravaArquivo(
							"agencia " + c.getAgencia() + "\t" +
							"Conta " + c.getConta() + "\t" +
							"Saldo: " + c.getSaldo() + "\n"
						);
						noPesq = noPesq.getProx();
					}
					g.gravaArquivo("Saldo total: " + saldoTotal + "\n");
				}
	    		
	    		no = no.getProx();
	    	}
	    	
	    	g.fechaArquivo();
    	}
    	catch(IOException e) {
		    System.out.println("Erro ao salvar o arquivo no caminho \"" + path + '\"');
		}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	// Calcular e printar tempo (em milisegundos) que o processo tomou para ser executado
    	System.out.println("- " + folder + ": " + (System.currentTimeMillis() - t0) + "ms");
    }
}
