package com.jumpityJump.game;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Platform {
	

	public Body body;
	Fixture fixture;
	float force=0;
	float cx,cy,height,width;
	boolean isWall;
	
	public Platform(World world, float cx, float cy, float height , float width) {
		
		this.cx = cx;
		this.cy = cy;
		this.height = height;
		this.width = width;
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(cx, cy);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width,height);
		
		FixtureDef fixtureDef= new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.restitution = 0.0f;
		fixtureDef.friction =1f;
		fixtureDef.density = 0;
		
		
		body = world.createBody(bodyDef);	
		fixture = body.createFixture(fixtureDef);
		fixture.setUserData("platform");
	}

	public float getHeight() {
		return height;
	}

	public float getWidth() {
		return width;
	}

}
