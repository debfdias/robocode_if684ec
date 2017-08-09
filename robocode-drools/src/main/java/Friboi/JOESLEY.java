//
// RobotDrools.java
//
// Created by Mateusnbm on May 20, 2017.
//
// Copyright Notice: @author ribadas.
//


package Friboi;


import java.awt.Color;
import java.io.IOException;
import java.util.Vector;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;
import org.drools.runtime.rule.QueryResultsRow;
import robocode.AdvancedRobot;
import robocode.BulletHitBulletEvent;
import robocode.BulletHitEvent;
import robocode.BulletMissedEvent;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.RobotDeathEvent;
import robocode.RobotStatus;
import robocode.ScannedRobotEvent;
import robocode.StatusEvent;
import robocode.TeamRobot;

import java.awt.*;
import java.io.IOException;


public class JOESLEY extends TeamRobot {

    public static String rulesFilepath = "Friboi/rules.drl";
    public static String actionsQuery = "query_actions";
    
    private KnowledgeBuilder kBuilder;
    private KnowledgeBase kBase;
    private StatefulKnowledgeSession kSession;
    
    private Vector<FactHandle> referenciasHechosActuales = new Vector<FactHandle>();
    
    public JOESLEY() {}
    
    @Override
    public void run() {
    	
    	DebugConveniences.enableDebugMode(true);
    	
    	// Robot appearance.
    	
    	RobotColors c = new RobotColors();

		c.bodyColor = Color.gray;
		c.gunColor = Color.black;
		c.radarColor = Color.yellow;
		c.scanColor = Color.yellow;
		c.bulletColor = Color.yellow;

		setBodyColor(c.bodyColor);
		setGunColor(c.gunColor);
		setRadarColor(c.radarColor);
		setScanColor(c.scanColor);
		setBulletColor(c.bulletColor);
		
		// Broadcast team colors.
		
		try {
			
			broadcastMessage(c);
			
		} catch (IOException ignored) {}

    	// Create the knowledge base and setup the rules.
    	
    	createKnowledgeBase();
    	
    	// Enable robot, radar and the canon to be independent.
    	
        setAdjustGunForRobotTurn(true);
        setAdjustRadarForGunTurn(true);
        setAdjustRadarForRobotTurn(true);
        
        // Main control loop.

        while (true) {
        	
        	DebugConveniences.log("Round started.");
            
        	loadRobotState();
            loadBattleState();

            // Activate the rules.
            
            DebugConveniences.dumpFacts(kSession);           
            kSession.fireAllRules();
            cleanPreviousIterations();

            // Retrieve, fire and dispose actions.
            
            Vector<Action> actions = retrieveActions();
            
            fireActions(actions);
            
            DebugConveniences.log("Round actions:");
            DebugConveniences.dumpActions(actions);
            
            // Cleared actions for this round.
            
            execute();

        }

    }

    private void createKnowledgeBase() {
    	
        String filepath = System.getProperty("JOESLEY.reglas", JOESLEY.rulesFilepath);
        
        DebugConveniences.log("Creating the knowledge builder.");
        DebugConveniences.log("Load rules from: " + filepath);
        
        kBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kBuilder.add(ResourceFactory.newClassPathResource(filepath, JOESLEY.class), ResourceType.DRL);
        
        if (kBuilder.hasErrors()) {
        	
            System.err.println("Error, unable to setup the knowledge builder.");
            DebugConveniences.log(kBuilder.getErrors().toString());
            
        }

        kBase = KnowledgeBaseFactory.newKnowledgeBase();
        kBase.addKnowledgePackages(kBuilder.getKnowledgePackages());
        
        kSession = kBase.newStatefulKnowledgeSession();
        
    }

    private void loadRobotState() {
    	
    	RobotState state = new RobotState(this);
    	
        referenciasHechosActuales.add(kSession.insert(state));
        
    }
    
    private void loadBattleState() {
    	
    	BattleState state = new BattleState(
    		getBattleFieldWidth(), 
    		getBattleFieldHeight(),
            getNumRounds(), getRoundNum(),
            getTime(),
            getOthers()
        );
    	
        referenciasHechosActuales.add(kSession.insert(state));
        
    }

    private void cleanPreviousIterations() {
    	
        for (FactHandle fact:this.referenciasHechosActuales) {
        	
            kSession.retract(fact);
            
        }
        
        this.referenciasHechosActuales.clear();
        
    }

    private Vector<Action> retrieveActions() {
    	
    	Action action;
        Vector<Action> actionsList = new Vector<Action>();

        for (QueryResultsRow resultado : kSession.getQueryResults(JOESLEY.actionsQuery)) {
        	
            action = (Action) resultado.get("accion");
            action.setRobot(this);
            actionsList.add(action);
            
            kSession.retract(resultado.getFactHandle("accion"));
            
        }

        return actionsList;
    }

    private void fireActions(Vector<Action> actions) {
    	
        for (Action action:actions) {
        	
            action.iniciarEjecucion();
            
        }
        
    }
    
    // Pass along the events fired to the active memory.
    
    @Override
    public void onBulletHit(BulletHitEvent event) {
    	
          referenciasHechosActuales.add(kSession.insert(event));
          
    }

    @Override
    public void onBulletHitBullet(BulletHitBulletEvent event) {
    	
        referenciasHechosActuales.add(kSession.insert(event));
        
    }

    @Override
    public void onBulletMissed(BulletMissedEvent event) {
    	
        referenciasHechosActuales.add(kSession.insert(event));
        
    }

    @Override
    public void onHitByBullet(HitByBulletEvent event) {
    	
        referenciasHechosActuales.add(kSession.insert(event));
        
    }

    @Override
    public void onHitRobot(HitRobotEvent event) {
    	
        referenciasHechosActuales.add(kSession.insert(event));
        
    }

    @Override
    public void onHitWall(HitWallEvent event) {
    	
        referenciasHechosActuales.add(kSession.insert(event));
        
    }

    @Override
    public void onRobotDeath(RobotDeathEvent event) {
    	
        referenciasHechosActuales.add(kSession.insert(event));
        
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
    	
    	// Don't fire on teammates.
    	
    	if (isTeammate(event.getName())) {
    		
    		return;
    	}
    	
    	// Broadcast a random target position (mostly the active one).
    	
    	double enemyBearing = this.getHeading() + event.getBearing();
    	double enemyX = getX() + event.getDistance() * Math.sin(Math.toRadians(enemyBearing));
    	double enemyY = getY() + event.getDistance() * Math.cos(Math.toRadians(enemyBearing));

    	try {
    		
    		broadcastMessage(new Point(enemyX, enemyY));
    		
    	} catch (IOException ignored) {}
    	
        referenciasHechosActuales.add(kSession.insert(event));
        
    }


}
