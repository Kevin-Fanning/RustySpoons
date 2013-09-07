package com.me.mygdxgame;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class LevelOne extends AbstractScreen {
	
	protected Player player;
	protected Floor floor;
	protected Background bg;
	
	protected World world;
	private Box2DDebugRenderer debugRenderer;
	
	Camera camera;
	
	public LevelOne(RustySpoons game)
	{
		super(game);
		
		camera = new OrthographicCamera(800*RustySpoons.WORLD_TO_BOX, 600*RustySpoons.WORLD_TO_BOX);
		camera.translate(new Vector3(1,1,0));
		world = new World(new Vector2(0, -30), true);
		debugRenderer = new Box2DDebugRenderer();
		
		player = new Player(world);
		
		floor = new Floor(0, 100, 2000, 30, world);
		
		bg = new Background();
		
		stage.addActor(bg);
		stage.addActor(player);
		stage.addActor(floor);
		
	}
	
	@Override
	public void render(float delta)
	{
		super.render(delta);
		
		if (player.x > stage.getWidth()*0.8f && player.walking && !(player.velocity.x < 0))
		{
			bg.scroll(2);
		}
		if (player.x < stage.getWidth()*0.2f)
		{
			//bg.scroll(-2);
		}
		
		world.step(1/60f,  6,  2);
	}
}
