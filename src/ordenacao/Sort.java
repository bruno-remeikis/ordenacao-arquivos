package ordenacao;

import model.Conta;
import util.listadupla.ListaDupla;

public abstract interface Sort
{
	ListaDupla<Conta> sort(ListaDupla<Conta> lista);
}
