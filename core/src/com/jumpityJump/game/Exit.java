package com.jumpityJump.game;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Exit extends Platform {
	
	public Exit(World world, float cx, float cy, float height , float width){ 
		super(world, cx, cy, height , width);
		
		fixture.setUserData("exit");
	}
	
	
}
