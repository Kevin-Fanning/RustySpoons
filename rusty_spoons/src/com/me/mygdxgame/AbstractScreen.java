package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class AbstractScreen implements Screen {
	protected final RustySpoons game;
	
	protected final BitmapFont font;
	protected final SpriteBatch batch;
	protected final Stage stage;
	
	public AbstractScreen(RustySpoons game) {
		this.game = game;
		this.font = new BitmapFont();
		this.batch = new SpriteBatch();
		this.stage = new Stage(800, 600, true);
	}
	
	protected String getName()
	{
		return getClass().getSimpleName();
	}
	
	@Override
	public void show()
	{
	}
	
	@Override
	public void resize(int width, int height)
	{
		stage.setViewport(width,  height,  true);
	}
	
	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0.f, 0.f, 0.f, 1.f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act();
		stage.draw();
	}
	
	@Override
	public void hide()
	{
		
	}
	
	@Override 
	public void pause()
	{
		
	}

	@Override
	public void resume()
	{
		
	}
	
	@Override
	public void dispose()
	{
		stage.dispose();
		batch.dispose();
		font.dispose();
	}
}

