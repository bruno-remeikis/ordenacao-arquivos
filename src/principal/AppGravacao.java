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
	public static void registrarContas(String folder, String nArq, ListaDupla<Conta> contas, Sort s)
    {
        String path = "src/files/saida/" + folder + "/conta" + nArq + ".txt";
        
        long t0 = System.currentTimeMillis();
        
        contas = s.sort(contas.clone());
        
        try {
            GravaArquivo g = new GravaArquivo(path);

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
        
        System.out.println("- " + folder + ": " + (System.currentTimeMillis() - t0) + "ms");
    }
    
    public static void registrarBuscas(String folder, String nReg, ListaDupla<Long> cpfs, ArvoreBusca ab)
    {
    	String path = "src/files/saida/" + folder + "/conta" + nReg + ".txt";
    	
    	long t0 = System.currentTimeMillis();
    	
    	try {
    		GravaArquivo g = new GravaArquivo(path);
	    	
	    	NoDupla<Long> no = cpfs.getPrim();
	    	while(no != null)
	    	{
	    		ListaDupla<Conta> contasPesq = ab.buscar(no.getInfo());
					
				g.gravaArquivo("CPF " + no.getInfo() + ":\n");
				if(contasPesq.eVazia()) {
					g.gravaArquivo("INEXISTENTE\n");
				}
				else {
					NoDupla<Conta> noPesq = contasPesq.getPrim();
					float saldoTotal = 0;
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
    	
    	System.out.println("- " + folder + ": " + (System.currentTimeMillis() - t0) + "ms");
    }
}
