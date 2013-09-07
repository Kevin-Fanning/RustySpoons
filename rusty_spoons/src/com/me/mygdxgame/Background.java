package com.me.mygdxgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Background extends Actor {
	protected Texture tex;
	protected TextureRegion texRegion1;
	protected TextureRegion[] texRegions;
	protected int position1, position2, position3;
	
	public Background()
	{
		super();
		
		position1 = 0;
		position2 = 512;
		position3 = 1024;
		
		texRegions = new TextureRegion[3];
		tex = new Texture("data/back.png");
		texRegions[0] = new TextureRegion(tex, 512, 600);
		texRegions[1] = new TextureRegion(tex, 512, 0, 512, 600);
		texRegions[2] = new TextureRegion(tex, 1024, 0, 357, 600);
	}
	
	public void scroll(int value)
	{
		position1 -= value;
		position2 -= value;
		position3 -= value;
		
	}
	
	@Override
	public void act(float delta)
	{
		if (position1 < -512)
		{
			position1 = position3 + 357;
		}
		if (position2 < -512)
		{
			position2 = position1 + 512;
		}
		if (position3 < -357)
		{
			position3 = position2 + 512;
		}
	}
	
	@Override 
	public void draw(SpriteBatch batch, float parentAlpha)
	{
		batch.draw(texRegions[0],  position1,  0);
		batch.draw(texRegions[1],  position2,  0);
		batch.draw(texRegions[2],  position3,  0);
	}
}
