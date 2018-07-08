package com.yellastrodev.entroworld.game_core;
import com.yellastrodev.entroworld.game_core.states.*;
import com.yellastrodev.entroworld.game_core.components.*;
import com.yellastrodev.entroworld.game_core.entities.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.*;
import com.yellastrodev.entroworld.game_core.states.life_cycles.*;
import com.yellastrodev.entroworld.game_core.states.state_managers.*;

public class PlayerController implements iLifeCycle
{
	private Engine mEngine;
	private MobProcessManager mProcessor;
	
	public PlayerController(Engine fEngine,MobProcessManager fProcessor)
	{
		mProcessor = fProcessor;
		mEngine = fEngine;
	}
	
	public void update(float dTime)
	{
		// Check if the screen is touched
		if(Gdx.input.justTouched())
		{ // Get input touch coordinates
		// and set the temp vector with these values
			
		}
			//get the touch coordinates
			//with respect to the camera's viewport
			//camera.unproject(GameManager.temp);
		/*float touchX = GameManager.temp.x;
		float touchY= GameManager.temp.y;*/
//iterate the doors array and //check if we tapped/touched/clicked on any door
	}
	
	public void onTap(float oX, float oY)
	{
		mProcessor.mStatesStack.clear();
		for(EnEntity fEntity:mEngine.mEntities)
		{
			if(Math.abs(fEntity.mPositionComp.oX-oX)<80
				&&Math.abs(fEntity.mPositionComp.oY-oY)<80
				&&fEntity!=mProcessor.mEntity)
			{
				Atack(fEntity);
				return;
			}
		}
		RunTo(oX,oY);
	}
	
	private void Atack(EnEntity fEntity)
	{
		mProcessor.GetTarget(fEntity);
	}
	
	private void RunTo(float oX,float oY)
	{
		mProcessor.RunThere(
			new PositionComp(oX,oY));
	}
	
	@Override
	public void kill()
	{
		// TODO: Implement this method
	}

	@Override
	public void onOverStates()
	{
		mProcessor.Stay();
	}

}
