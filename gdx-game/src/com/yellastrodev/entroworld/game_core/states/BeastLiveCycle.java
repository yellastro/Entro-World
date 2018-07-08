package com.yellastrodev.entroworld.game_core.states;
import com.badlogic.gdx.math.*;
import com.yellastrodev.entroworld.game_core.*;
import com.yellastrodev.entroworld.game_core.components.*;
import com.yellastrodev.entroworld.game_core.entities.*;
import com.yellastrodev.entroworld.game_core.states.state_managers.*;

public class BeastLiveCycle extends LiveCycleManager
{
	BeastStatisticComponent mBeastStatistic;
	
	int mHungerTime=5;
	
	public BeastLiveCycle(MobProcessManager fPross,Beast fBeast)
	{
		super(fPross,fBeast);
		mBeastStatistic=fBeast.mBeastStatisticks;
	}
	
	float dtime;
	@Override
	public void update(float fTime)
	{
		dtime=dtime+fTime;
		
		if(dtime>mHungerTime)
		{
			dtime=0;
			mBeastStatistic.mHunger-=5;
		}
	}
	
	@Override
	public void onOverStates()
	{
		
		if(mBeastStatistic.mHunger<60)
		{
			mProcessManager.startSearchinTarget(
				new SearchFilter(((Beast)mEntity).getFood(),
					((Beast)mEntity).getExcept()));
			
			mProcessManager.WalkAround();
		}
		else
		{/*
			int x=MathUtils.random(1, 10);
			if(x>6)
				mProcessManager.stay();
			else
				mProcessManager.runAway();*/
			super.onOverStates();
			
		}
			
	}

	@Override
	public void kill()
	{
		mBeastStatistic.mHunger+=10;
	}
	
	
}
