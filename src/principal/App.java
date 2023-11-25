package principal;

import java.io.IOException;

import io.GravaArquivo;
import io.LerArquivoConta;
import model.Conta;
import ordenacao.QuickSort;
import ordenacao.ShellSort;
import util.abb.ArvABB;
import util.abb.NoABB;
import util.avl.ArvAVL;
import util.listadupla.ListaDupla;
import util.listadupla.NoDupla;

public class App
{
    public static void main(String[] args)
    {
        LerArquivoConta lac;
        String[] nContas = { "500"/*, "1000", "5000", "10000", "50000"*/ };
        
        try {
            for(String n: nContas) 
            {
                // -- LEITURA --
                lac = new LerArquivoConta("src/files/entrada/contas/conta" + n + ".txt");
                ListaDupla<Conta> contas = lac.leArquivo();
                //contas.printAll();
                lac.fechaArquivo();
                
                // -- ORDENAÇÃO E GRAVAÇÃO --
                registrarContas("quicksort", n, QuickSort.quickSort(contas));
                registrarContas("shellsort", n, ShellSort.shellSort(contas));
                
                
                /*TESTE ARVORE ABB
                NoDupla<Conta> no = contas.getPrim();
                ArvABB arvAbb = new ArvABB();
                
                while(no != null) {
                arvAbb.insere(no.getInfo());
                no = no.getProx();
            }
                arvAbb.balancear();
                
                System.out.println(arvAbb.camInOrdem().toString());
                */
                
                
                NoDupla<Conta> no = contas.getPrim();
                ArvAVL arvAvl = new ArvAVL();
                
                while(no != null) {
                arvAvl.inserir(no.getInfo());
                no = no.getProx();
            }
                
                
                arvAvl.percursoInordem();
                
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void registrarContas(String folder, String nArq, ListaDupla<Conta> contas)
    {
        String path = "src/files/saida/" + folder + "/conta" + nArq + ".txt";
        
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
    }
    
    
}
