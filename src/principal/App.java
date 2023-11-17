package principal;

import java.io.IOException;

import io.GravaArquivo;
import io.LerArquivoConta;
import model.Conta;
import ordenacao.QuickSort;
import util.listadupla.ListaDupla;

public class App
{
    public static void main(String[] args)
    {
        LerArquivoConta lac;
        String[] nContas = { "500"/*, "1000", "5000", "10000", "50000"*/ };
        
        try {
            /*GravaArquivo g = new GravaArquivo(
                System.getProperty("user.dir") + "/src/files/contas/conta500.txt"
            );*/
            
            for(String n: nContas) 
            {
                lac = new LerArquivoConta("src/files/contas/conta" + n + ".txt");
                
                ListaDupla<Conta> contas = lac.leArquivo();
                
                QuickSort.quickSort(contas);
                contas.print();
                
                lac.fechaArquivo();
            }
            
            //g.fechaArquivo();
            
            //ListaSimples<Conta> ls = new ListaSimples();
            //ls.inserir(new Conta());
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
