//
// Action.java
//
// Created by Mateusnbm on May 20, 2017.
//
// Copyright Notice: @author ribadas.
//


package Friboi;


import robocode.AdvancedRobot;



public class Action {
	
    private int        type;
    private double     parametro;
    private int        priority;

    private AdvancedRobot robot;

    public static final int AVANZAR=1;
    public static final int RETROCEDER=2;
    public static final int STOP=3;
    public static final int DISPARAR=4;
    public static final int GIRAR_TANQUE_DER=5;
    public static final int GIRAR_TANQUE_IZQ=6;
    public static final int GIRAR_RADAR_DER=7;
    public static final int GIRAR_RADAR_IZQ=8;
    public static final int GIRAR_CANON_DER=9;
    public static final int GIRAR_CANON_IZQ=10;


    public Action() {}

    public Action(int type, double parametro, int priority) {
    	
        this.type = type;
        this.parametro = parametro;
        this.priority = priority;
        
    }

    public double getParametro() {
    	
        return parametro;
        
    }

    public void setParametro(double parametro) {
    	
        this.parametro = parametro; 
        
    }

    public int getType() {
    	
        return type;
        
    }

    public void setType(int type) {
    	
        this.type = type;
        
    }

    public int getPriority() {
    	
        return priority;
        
    }

    public void setPriority(int priority) {
    	
        this.priority = priority;
        
    }



    public void iniciarEjecucion() {
    	
        if (this.robot != null) {
        	
            switch (this.type) {
                case Action.DISPARAR: robot.setFire(parametro); break;
                case Action.AVANZAR: robot.setAhead(parametro); break;
                case Action.RETROCEDER: robot.setBack(parametro); break;
                case Action.STOP: robot.setStop(); break;
                case Action.GIRAR_CANON_DER: robot.setTurnGunRight(parametro); break;
                case Action.GIRAR_CANON_IZQ: robot.setTurnGunLeft(parametro); break;
                case Action.GIRAR_RADAR_DER: robot.setTurnRadarRight(parametro); break;
                case Action.GIRAR_RADAR_IZQ: robot.setTurnRadarLeft(parametro); break;
                case Action.GIRAR_TANQUE_DER: robot.setTurnRight(parametro); break;
                case Action.GIRAR_TANQUE_IZQ: robot.setTurnLeft(parametro); break;
            }
            
        }
        
    }

    void setRobot(AdvancedRobot robot) {
    	
        this.robot = robot;
        
    }

    public String toString() {
    	
        String etqTipo="";
        
        switch (this.type) {
        	case Action.DISPARAR:etqTipo="Disparar"; break;
            case Action.AVANZAR: etqTipo="Avanzar"; break;
            case Action.RETROCEDER: etqTipo="Retroceder"; break;
            case Action.STOP: etqTipo="Stop"; break;
            case Action.GIRAR_CANON_DER: etqTipo="Girar cañón derecha"; break;
            case Action.GIRAR_CANON_IZQ: etqTipo="Girar cañón izquierda"; break;
            case Action.GIRAR_RADAR_DER: etqTipo="Girar radar derecha"; break;
            case Action.GIRAR_RADAR_IZQ: etqTipo="Girar radar izquierda"; break;
            case Action.GIRAR_TANQUE_DER: etqTipo="Girar tanque derecha"; break;
            case Action.GIRAR_TANQUE_IZQ: etqTipo="Girar tanque izquierda"; break;
        }
        
        return "Accion[tipo:"+etqTipo+", param:"+parametro+", prioridad:"+priority+"]";
    }

}
