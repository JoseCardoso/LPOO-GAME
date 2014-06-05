package com.jumpityJump.game;

import java.util.Random;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Key {
	public Body body;
	Fixture fixture;
	float cx,cy;
	String name;
	
	public Key(World world, float cx, float cy, float radius) {
		this.cx = cx;
		this.cy = cy;
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(cx, cy);
		
		CircleShape shape = new CircleShape();
		shape.setRadius(radius);
		
		FixtureDef fixtureDef= new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.restitution = 0.0f;
		fixtureDef.friction =0;
		fixtureDef.density = 0;
		
		
		body = world.createBody(bodyDef);	
		fixture = body.createFixture(fixtureDef);
		fixture.setUserData("key");
	}

	public Key(World world, float cx, float cy, float radius, String name) {
		this.cx = cx;
		this.cy = cy;
		this.name = "key" + name;
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(cx, cy);
		
		CircleShape shape = new CircleShape();
		shape.setRadius(radius);
		
		FixtureDef fixtureDef= new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.restitution = 0.0f;
		fixtureDef.friction =0;
		fixtureDef.density = 0;
		
		
		body = world.createBody(bodyDef);	
		fixture = body.createFixture(fixtureDef);
		fixture.setUserData(this.name);
	}
	
}
