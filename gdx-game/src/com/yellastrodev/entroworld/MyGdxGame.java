package com.yellastrodev.entroworld;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.yellastrodev.entroworld.game_core.*;
import com.yellastrodev.entroworld.game_core.systems.*;
import com.yellastrodev.entroworld.ui.*;

public class MyGdxGame implements ApplicationListener
{
	Texture texture;
	SpriteBatch batch;

	private WorldController worldController;
	public iScreen mScreen;
	private sysWorldRenderer worldRenderer;

	private boolean pause;

	@Override
	public void create()
	{
		//texture = new Texture(Gdx.files.internal("android.jpg"));
		batch = new SpriteBatch();
		//((Engine)mScreen).init();
		mScreen = new GeneralScreen(this);
		
		
		// Set Libgdx log level to DEBUG
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
// Initialize controller and renderer
		
		pause = false;
	}

	@Override
	public void render()
	{        
	   /* Gdx.gl.glClearColor(1, 1, 1, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(texture, Gdx.graphics.getWidth() / 4, 0, 
				   Gdx.graphics.getWidth() / 2, Gdx.graphics.getWidth() / 2);
		batch.end();*/
		
		float delttime=Gdx.graphics.getDeltaTime();
		// Update game world by the time that has passed 
		// since last rendered frame.
		
		/*if(!pause)
			worldController.update(delttime);
		*/
		// Sets the clear screen color to: Cornflower Blue 
		Gdx.gl.glClearColor(0x64/255.0f, 0x95/255.0f, 0xed/255.0f, 0xff/255.0f);
		// Clears the screen 
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// Render game world to screen 
	
		mScreen.update(delttime);
		
		//worldRenderer.update(delttime);
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
		pause=true;
	}

	@Override
	public void resume()
	{
		pause = false;
	}
}
