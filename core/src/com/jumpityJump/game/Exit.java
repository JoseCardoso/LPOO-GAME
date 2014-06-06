package com.jumpityJump.game;

import com.badlogic.gdx.physics.box2d.World;

public class Exit extends Platform {
	
	public Exit(World world, float cx, float cy, float height , float width){ 
		super(world, cx, cy, height , width);
		
		fixture.setUserData("exit");
	}
	
	
}
