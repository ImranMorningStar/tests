package com.thrus.test;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import java.nio.channels.FileLock;

public class Redemption extends Game
{
   FileLoader loader;
	

	@Override
	public void create()
	{
        loader= new FileLoader();
        setScreen(new GameScreen(this));
		
	}

	@Override
	public void render()
	{        
	    super.render();
		
	}

	@Override
	public void dispose()
	{
	}

	@Override
	public void resize(int width, int height)
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
}
