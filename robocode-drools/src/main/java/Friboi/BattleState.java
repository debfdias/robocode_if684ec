//
// BattleState.java
//
// Created by Mateusnbm on May 20, 2017.
//
// Copyright Notice: @author ribadas.
//

package Friboi;

public class BattleState {
	
    private double anchoCampo;
    private double altoCampo;
    private int numeroRondas;
    private int rondaActual;
    private long tiempo;
    private int numeroEnemigos;

    public BattleState(double anchoCampo, double altoCampo, int numeroRondas, int rondaActual, long tiempo, int numeroEnemigos) {
        this.anchoCampo = anchoCampo;
        this.altoCampo = altoCampo;
        this.numeroRondas = numeroRondas;
        this.rondaActual = rondaActual;
        this.tiempo = tiempo;
        this.numeroEnemigos = numeroEnemigos;
    }

    public double getAltoCampo() {
        return altoCampo;
    }

    public void setAltoCampo(double altoCampo) {
        this.altoCampo = altoCampo;
    }

    public double getAnchoCampo() {
        return anchoCampo;
    }

    public void setAnchoCampo(double anchoCampo) {
        this.anchoCampo = anchoCampo;
    }

    public int getNumeroEnemigos() {
        return numeroEnemigos;
    }

    public void setNumeroEnemigos(int numeroEnemigos) {
        this.numeroEnemigos = numeroEnemigos;
    }

    public int getNumeroRondas() {
        return numeroRondas;
    }

    public void setNumeroRondas(int numeroRondas) {
        this.numeroRondas = numeroRondas;
    }

    public int getRondaActual() {
        return rondaActual;
    }

    public void setRondaActual(int rondaActual) {
        this.rondaActual = rondaActual;
    }

    public long getTiempo() {
        return tiempo;
    }

    public void setTiempo(long tiempo) {
        this.tiempo = tiempo;
    }
    
    
}
