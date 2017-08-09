//
// DebugConveniences.java
//
// Created by Mateusnbm on May 20, 2017.
//
// Copyright Notice: @author ribadas.
//


package Friboi;


import java.util.List;

import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;


public final class DebugConveniences {
	
	public static boolean debugModeEnabled = false;

	public static void enableDebugMode(boolean enable) {
		
		debugModeEnabled = enable;
		
	}

	public static void log(String message) {
		
		if (debugModeEnabled == true) {
			
			System.out.println("<DEBUG:LOG>: " + message);			
			
		}
		
	}

	public static void dumpFacts(StatefulKnowledgeSession ksession) {
		
		if (debugModeEnabled == true){
			
			for (FactHandle f: ksession.getFactHandles()){
				
				System.out.println("  "+ksession.getObject(f));
				
			}
			
		}
		
	}

	public static void dumpActions(List<Action> acciones) {
		
		if (debugModeEnabled == true){
			
			for (Action a: acciones) {
				
				System.out.println("  "+a.toString());				
				
			}
			
		}
		
	}
	
}
