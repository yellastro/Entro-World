package com.yellastrodev.entroworld.game_core.states;
import com.yellastrodev.entroworld.game_core.entities.*;
import com.yellastrodev.entroworld.game_core.components.*;
import com.yellastrodev.entroworld.game_core.*;
import com.yellastrodev.entroworld.game_core.states.life_cycles.*;
import com.yellastrodev.entroworld.game_core.states.state_managers.*;

public class LiveCycleManager implements iLifeCycle
{
	MobProcessManager mProcessManager;
	EnEntity mEntity;
	StatisticComponent mStatistic;
	SearchFilter mFirtsFiltr;

	public void kill()
	{
		// TODO: Implement this method
	}
	
	public void update(float fTime)
	{
		
	}
	
	public LiveCycleManager(MobProcessManager fpross,EnEntity fEnty)
	{
		mProcessManager=fpross;
		mEntity=fEnty;
		mStatistic=fEnty.mStatistic;
		
		mProcessManager.startSearchinTarget(
		mFirtsFiltr=new SearchFilter(mStatistic.mEnemy,mStatistic.mFrends));
	}
	
	public void onOverStates()
	{
		
		mProcessManager.startSearchinTarget(mFirtsFiltr);
		mProcessManager.WalkAround();
	}
}
