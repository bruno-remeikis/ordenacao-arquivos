package util.arvore;

import model.Conta;
import util.listadupla.ListaDupla;

public interface ArvoreBusca
{
	ListaDupla<Conta> buscar(long chave);
}
