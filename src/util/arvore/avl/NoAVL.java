/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util.arvore.avl;

import model.Conta;

/**
 *
 * @author acata
 */
public class NoAVL {
    
    Conta conta;
    NoAVL esq;
    NoAVL dir;
    int altura;

    NoAVL(Conta conta) {
        this.conta = conta;
        this.altura = 1;
    }

        public Conta getConta() {
            return conta;
        }

        public void setConta(Conta conta) {
            this.conta = conta;
        }

        public NoAVL getEsq() {
            return esq;
        }

        public void setEsq(NoAVL esq) {
            this.esq = esq;
        }

        public NoAVL getDir() {
            return dir;
        }

        public void setDir(NoAVL dir) {
            this.dir = dir;
        }

        public int getAltura() {
            return altura;
        }

        public void setAltura(int altura) {
            this.altura = altura;
        }

    
}
