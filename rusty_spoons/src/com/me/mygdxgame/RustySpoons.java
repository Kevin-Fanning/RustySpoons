package com.me.mygdxgame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class RustySpoons implements ApplicationListener {
	static final float WORLD_TO_BOX = 0.01f;
	static final float BOX_TO_WORLD = 100.0f;
	
	private Screen currentScreen;
	
	@Override
	public void create() {		
		currentScreen = new LevelOne(this);
		
	}

	@Override
	public void dispose() {
		currentScreen.dispose();
	}

	@Override
	public void render() {		
		currentScreen.render(0.1f);
		
		
	}

	@Override
	public void resize(int width, int height) {
		currentScreen.resize(width, height);
	}

	@Override
	public void pause() {
		currentScreen.pause();
	}

	@Override
	public void resume() {
		currentScreen.resume();
	}
}
