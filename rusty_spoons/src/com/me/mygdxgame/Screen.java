package com.me.mygdxgame;

public interface Screen {
	public void pause();
	public void resume();
	public void render(float delta);
	public void show();
	public void resize(int width,int height);
}
