//
// ValidateRules.java
//
// Created by Mateusnbm on May 20, 2017.
//
// Copyright Notice: @author ribadas.
//


package Friboi;


import java.util.List;
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

import robocode.*;


public class RulesValidator {

    public static String rulesFilepath = "Friboi/rules.drl";
    public static String actionsQuery = "query_actions";
    
    private KnowledgeBuilder kBuilder;
    private KnowledgeBase kBase;
    private StatefulKnowledgeSession kSession;
    
    private Vector<FactHandle> referenciasHechosActuales = new Vector<FactHandle>();

    public RulesValidator() {
    	
    	DebugConveniences.enableDebugMode(true);
        
    	createKnowledgeBase();
        loadEvents();
        
    }

    private void createKnowledgeBase() {
    	
    	String filepath = System.getProperty("JOESLEY.reglas", RulesValidator.rulesFilepath);
    	
    	DebugConveniences.log("Creating the knowledge builder.");
    	DebugConveniences.log("Loading rules from: " + filepath);
    	
        kBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kBuilder.add(ResourceFactory.newClassPathResource(filepath, RulesValidator.class), ResourceType.DRL);
        
        if (kBuilder.hasErrors()) {
        	
        	System.err.println("Error, unable to setup the knowledge builder.");
            DebugConveniences.log(kBuilder.getErrors().toString());
            
        }

        kBase = KnowledgeBaseFactory.newKnowledgeBase();
        kBase.addKnowledgePackages(kBuilder.getKnowledgePackages());

        kSession = kBase.newStatefulKnowledgeSession();
        
    }
    
    public void loadEvents() {
		
		ScannedRobotEvent e = new ScannedRobotEvent("pepe", 100, 10, 10, 10, 10);
        FactHandle referenciaHecho = kSession.insert(e);
        
        referenciasHechosActuales.add(referenciaHecho);
        
        kSession.fireAllRules();
        
	}

    public static void main(String args[]) {
    	
        new RulesValidator();
        
    }
    
}




