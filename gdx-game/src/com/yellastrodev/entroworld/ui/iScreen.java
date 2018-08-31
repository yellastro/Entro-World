package com.yellastrodev.entroworld.ui;
import com.badlogic.gdx.*;
import org.apache.http.impl.client.*;
import com.yellastrodev.entroworld.*;
import com.badlogic.gdx.graphics.g2d.*;

public abstract class iScreen extends InputAdapter
{
	private iScreen mPreviousScreen;
	
	protected MyGdxGame mGame;

	protected SpriteBatch mBatch;
	
	public abstract void update(float dTime)
	
	public iScreen(iScreen fPrevScreen,MyGdxGame fGame)
	{
		mGame = fGame;
		mPreviousScreen = fPrevScreen;
		mBatch = new SpriteBatch();
		
		resume();
	}
	
	@Override
	public boolean keyDown(int keycode) {
        if((keycode == 4||keycode == 131)
			&&mPreviousScreen!=null){
			finish();
        }
		else
			super.keyDown(keycode);
        return false;
	}

	public void resume()
	{
		mGame.mScreen = this;
		Gdx.input.setInputProcessor(this);
		Gdx.input.setCatchBackKey(true);
	}
	
	public void finish()
	{
		Gdx.input.setInputProcessor(null);
		Gdx.input.setCatchBackKey(false);
		mPreviousScreen.resume();
		
		//mBatch.dispose();
	}
}
