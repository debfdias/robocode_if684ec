//
// WESLEY.java
//
// Created by Mateusnbm on Jun 09, 2017.
//
// Copyright:
//
// Copyright (c) 2001-2014 Mathew A. Nelson and Robocode contributors
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://robocode.sourceforge.net/license/epl-v10.html
//


package Friboi;


import robocode.Droid;
import robocode.MessageEvent;
import robocode.TeamRobot;
import static robocode.util.Utils.normalRelativeAngleDegrees;

import java.awt.*;
import java.io.IOException;


public class WESLEY extends TeamRobot implements Droid {
	
	public void run() {
		
		// ...
		
	}

	public void onMessageReceived(MessageEvent e) {
		
		if (e.getMessage() instanceof Point) {
			
			Point p = (Point) e.getMessage();
			
			// Calculate x and y to target
			double dx = p.getX() - this.getX();
			double dy = p.getY() - this.getY();
			
			// Calculate angle to target
			double theta = Math.toDegrees(Math.atan2(dx, dy));

			// Turn gun to target
			turnGunRight(normalRelativeAngleDegrees(theta - getGunHeading()));
			
			// Fire hard!
			fire(3);
			
		}else if (e.getMessage() instanceof RobotColors) {
			
			RobotColors c = (RobotColors) e.getMessage();

			setBodyColor(c.bodyColor);
			setGunColor(c.gunColor);
			setRadarColor(c.radarColor);
			setScanColor(c.scanColor);
			setBulletColor(c.bulletColor);
			
		}
		
	}
	
}

