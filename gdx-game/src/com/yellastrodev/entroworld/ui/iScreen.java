package com.yellastrodev.entroworld.ui;
import com.badlogic.gdx.*;

public abstract class iScreen extends InputAdapter
{
	private iScreen mBackScreen;
	
	public abstract void update(float dTime)
	
	@Override
	public boolean keyDown(int keycode) {
        if(keycode == 4||keycode == 131){
			Gdx.input.setInputProcessor(null);
			mBackScreen.resume();
        }
        return false;
	}

	public void resume()
	{
		Gdx.input.setInputProcessor(this);
	}
}
