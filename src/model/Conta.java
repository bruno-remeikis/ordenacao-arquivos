package model;

public class Conta
{
    private int agencia;
    private int conta;
    private float saldo;
    private long cpf;

    public Conta() {}

    public Conta(int agencia, int conta, float saldo, long cpf) {
        this.agencia = agencia;
        this.conta = conta;
        this.saldo = saldo;
        this.cpf = cpf;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public int getConta() {
        return conta;
    }

    public void setConta(int conta) {
        this.conta = conta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Conta: { CPF: " + cpf + ", agencia: " + agencia + ", conta: " + conta + ", saldo: " + saldo + " }";
    }
}
