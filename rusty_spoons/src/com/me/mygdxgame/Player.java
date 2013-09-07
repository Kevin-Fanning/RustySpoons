package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.TimeUtils;


public class Player extends Actor implements ContactListener{
	static final float maxSpeed = 150;
	static final float terminalVelocity = 300;
	static final float jumpStrength = 300;
	public float x,y, width, height;
	protected Vector2 velocity, acceleration;
	private float footHeight = 20.f;
	protected boolean canJump = false;
	Body body;
	Fixture feet;
	Fixture torso;
	
	Texture[] anim;
	int animIndex = 0;
	int direction = 1;
	boolean walking = false;
	long lastFrameTime = 0;
	
	public Player(World world)
	{
		super();
		
		anim = new Texture[4];
		anim[0] = new Texture("data/char/walk1.png");
		anim[1] = new Texture("data/char/walk2.png");
		anim[2] = new Texture("data/char/walk3.png");
		anim[3] = new Texture("data/char/walk4.png");
		
		
		
		x = 200;
		y = 300;
		width = 64;
		height = 128;
		
		world.setContactListener(this);
		
		acceleration = new Vector2(0,0);
		velocity = new Vector2(0,0);
		acceleration.y = -8;
		
		BodyDef boxDef = new BodyDef();
		boxDef.type = BodyDef.BodyType.DynamicBody;
		boxDef.position.set((x + 0.5f*width )* RustySpoons.WORLD_TO_BOX, (y + 0.5f*height)*RustySpoons.WORLD_TO_BOX);
		body = world.createBody(boxDef);
		PolygonShape polyShape = new PolygonShape();
		polyShape.setAsBox((width/2)*RustySpoons.WORLD_TO_BOX,  ((height - footHeight)/2)*RustySpoons.WORLD_TO_BOX, new Vector2(0, 54*RustySpoons.WORLD_TO_BOX), 0.f);
		torso = body.createFixture(polyShape, 0.f);
		torso.setUserData("Torso");
		polyShape.dispose();
		                                 
		PolygonShape feetShape = new PolygonShape();
		feetShape.setAsBox((width/2)*RustySpoons.WORLD_TO_BOX,  footHeight / 2 * RustySpoons.WORLD_TO_BOX, new Vector2(0, -10*RustySpoons.WORLD_TO_BOX) , 0.f);
		feet = body.createFixture(feetShape, 0.f);
		feet.setFriction(0.01f);
		feet.setUserData("Feet");
		torso.setFriction(0.01f);
		feetShape.dispose();
	}
	
	public void act(float delta)
	{
		super.act(delta);
		
		
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
		{
			acceleration.x = -16.f;
			direction = -1;
			walking = true;
		}
		
		else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
		{
			acceleration.x = 16.f;
			direction = 1;
			walking = true;
		}
		else if (canJump)
		{
			acceleration.x = -velocity.x/4;
			walking = false;
		}

		if (canJump && Gdx.input.isKeyPressed(Input.Keys.SPACE))
		{
			velocity.y = jumpStrength;
			canJump = false;
		}
		
		if (walking && TimeUtils.millis() - lastFrameTime > 100)
		{
			animIndex += 1;
			if (animIndex >3)
			{
				animIndex = 0;
			}
			lastFrameTime = TimeUtils.millis();
		}

		if (!walking || !canJump)
		{
			animIndex = 0;
		}
		
		if (x > 800*0.8f)
		{
			velocity.x = Math.min(velocity.x,  0.f);
			acceleration.x = Math.min(acceleration.x,  0.f);
		}
		if (x < 800*0.2f)
		{
			velocity.x = Math.max(velocity.x,  0.f);
			acceleration.x = Math.max(acceleration.x,  0.f);
		}
		
		velocity.x += acceleration.x;
		velocity.y += acceleration.y;
		
		if (velocity.x > maxSpeed)
			velocity.x = maxSpeed;
		if (velocity.x < -maxSpeed)
			velocity.x = -maxSpeed;
		
		if (velocity.y < -terminalVelocity)
		{
			velocity.y = -terminalVelocity;
		}
		
		body.setLinearVelocity(velocity.x*delta, velocity.y*delta);
		this.x = body.getPosition().x*RustySpoons.BOX_TO_WORLD - 32;
		this.y = body.getPosition().y*RustySpoons.BOX_TO_WORLD - 32 + footHeight/2;
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha)
	{
		if (direction < 0)
			batch.draw(anim[animIndex], x + width , y, direction * width, height);
		else
			batch.draw(anim[animIndex], x , y, direction * width, height);
		
		System.out.println(velocity + " " + body.getLinearVelocity() + " : (" + x + "," + y + ") " + body.getPosition());
	}

	@Override
	public void beginContact(Contact contact) {
		// TODO Auto-generated method stub
		if (contact.getFixtureA().equals(feet) || contact.getFixtureB().equals(feet))
		{
			if (contact.getFixtureA().getUserData().equals("Floor") || contact.getFixtureB().getUserData().equals("Floor"))
			{
				canJump = true;
			}
			
			
		}
		if (contact.getFixtureA().getUserData() != null)
		{
			System.out.println(contact.getFixtureA().getUserData());
		}
		if (contact.getFixtureB().getUserData() != null)
		{
			System.out.println(contact.getFixtureB().getUserData());
		}
		
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		if (contact.getFixtureA().equals(feet) || contact.getFixtureB().equals(feet))
		{
			canJump = false;
		}
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub                                                                                                                                                                                                                                                                                                                
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}
	
}
