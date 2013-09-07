package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class Player extends Image {
	
	protected float x,y;
	
	public Player()
	{
		super(new TextureRegion(new Texture("data/face.png")));
		x = 0;
		y = 0;
	}
	
	public void act(float delta)
	{
		super.act(delta);
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
		{
			x -= 5 * delta;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
		{
			x += 5 * delta;
		}
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha)
	{
		super.draw(batch, parentAlpha);
		
	}
	
}
