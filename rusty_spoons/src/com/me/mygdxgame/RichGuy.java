package com.me.mygdxgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.TimeUtils;

public class RichGuy extends Actor {
	static final float SPEED = 20.f;
	protected Texture[] animFrames;
	protected int frameIndex;
	
	private long lastFrameTime = 0;
	Body bod;
	
	protected Vector2 position;
	
	public RichGuy(float x, float y, World world)
	{
		super();
		position = new Vector2(x, y);
		animFrames = new Texture[4];
		
		animFrames[0] = new Texture("data/rich/rich1.png");
		animFrames[1] = new Texture("data/rich/rich2.png");
		animFrames[2] = new Texture("data/rich/rich3.png");
		animFrames[3] = new Texture("data/rich/rich4.png");
		
		BodyDef boxDef = new BodyDef();
		boxDef.type = BodyDef.BodyType.KinematicBody;
		boxDef.position.set((position.x+32)*RustySpoons.WORLD_TO_BOX, (position.y+64)*RustySpoons.WORLD_TO_BOX);
		bod = world.createBody(boxDef);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(32*RustySpoons.WORLD_TO_BOX,  64*RustySpoons.WORLD_TO_BOX);
		FixtureDef fixdef = new FixtureDef();
		fixdef.isSensor = true;
		fixdef.shape = shape;
		Fixture fix = bod.createFixture(fixdef);
		fix.setUserData("RichGuy");
		
		shape.dispose();
	}
	
	@Override
	public void act(float delta)
	{
		
		
		if (TimeUtils.millis() - lastFrameTime > 100)
		{
			frameIndex++;
			
			if (frameIndex > 3)
			{
				frameIndex = 0;
			}
			lastFrameTime = TimeUtils.millis();
		}
		
		bod.setLinearVelocity(-SPEED*delta, 0.f);
		position.x = bod.getPosition().x*RustySpoons.BOX_TO_WORLD - 32;
		position.y = bod.getPosition().y*RustySpoons.BOX_TO_WORLD - 32;
		System.out.println();
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha)
	{
		batch.draw(animFrames[frameIndex],  position.x,  position.y);
	}
}
