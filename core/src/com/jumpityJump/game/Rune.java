package com.jumpityJump.game;

import java.util.Random;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Rune {

	public Body body;
	Fixture fixture;
	float cx,cy;
	private String type,name;
	
	public Rune(World world, float cx, float cy, float radius, String type, String name) {
		this.type = type;
		this.cx = cx;
		this.cy = cy;
		this.name = "rune" +name;
		this.name += type;
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.KinematicBody;
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
	
	public Rune(World world, float cx, float cy, float radius,String name) {

		type = randomType();
		this.cx = cx;
		this.cy = cy;
		this.name = "rune" +name;
		this.name += type;
		
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

	private String randomType() {
		// TODO Auto-generated method stub
		Random r = new Random();
		int choice = r.nextInt() %5;
		
		switch (choice)
		{
		case 0:
			return "Acceleration Boost";
		case 1:
			return "Double Damage";
		case 2:
			return "Ranged Atack";
		case 3:
			return "Stealth";
		case 4:
			return "Invulnerability";
		}
		
		return null;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
