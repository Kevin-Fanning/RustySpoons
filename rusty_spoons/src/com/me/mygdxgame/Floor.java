package com.me.mygdxgame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Floor extends Actor {
	private ShapeRenderer shapeRenderer;
	public float x,y, width, height;
	public Floor(int x, int y, int width, int height, World world)
	{
		super();
		shapeRenderer = new ShapeRenderer();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		BodyDef boxDef = new BodyDef();
		boxDef.position.set((x + 0.5f*width )* RustySpoons.WORLD_TO_BOX, (y + 0.5f*height)*RustySpoons.WORLD_TO_BOX);
		Body floorBody = world.createBody(boxDef);
		PolygonShape polyShape = new PolygonShape();
		polyShape.setAsBox((width/2.f)*RustySpoons.WORLD_TO_BOX,  (height/2.f)*RustySpoons.WORLD_TO_BOX);
		floorBody.createFixture(polyShape, 0.f);
		polyShape.dispose();
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha)
	{
		shapeRenderer.begin(ShapeType.FilledRectangle);
		shapeRenderer.setColor(Color.PINK);
		shapeRenderer.filledRect(x, y, width, height);
		shapeRenderer.end();
	}
}
