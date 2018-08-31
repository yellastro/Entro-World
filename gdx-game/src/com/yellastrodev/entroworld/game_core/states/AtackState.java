package com.yellastrodev.entroworld.game_core.states;

import com.badlogic.gdx.math.*;
import com.yellastrodev.entroworld.game_core.components.*;
import com.yellastrodev.entroworld.game_core.states.state_managers.*;

public class AtackState implements iStateOfProsess
{
	
	public static String sAnimKey="atack";

	@Override
	public String getAnimKey()
	{
		return "atack";
	}
	

	@Override
	public void Pause()
	{
		// TODO: Implement this method
	}


	private float mAtackPause=0;

	private iProcessManager mManager;
	private StatisticComponent mStatisComp;
	public PositionOnMapComponent mTargetPoss;
	private PositionOnMapComponent mCurrPoss;
	public iProcessManager mTargetManager;
	
	public AtackState(iProcessManager fManag,StatisticComponent fStatic
		,PositionOnMapComponent fCurr, PositionOnMapComponent fTarget,iProcessManager targetManager)
	{
		mManager=fManag;
		mStatisComp=fStatic;
		mCurrPoss=fCurr;
		mTargetPoss=fTarget;
		mTargetManager=targetManager;
	}
	
	@Override
	public void Start()
	{
		mTargetPoss=mManager.mTargetPoss;
		mAtackPause=(float)(0.01*mStatisComp.mAtcSpeed);
		MoveState.getDimention(mManager.mVelosComp
			,mTargetPoss,mCurrPoss);
	}
	
	@Override
	public void Update(float deltTime)
	{
		mAtackPause=mAtackPause+deltTime;
		
		float rangeX=mTargetPoss.oX-mCurrPoss.oX;
		float rangeY=mTargetPoss.oY-mCurrPoss.oY;

		if(Math.abs(rangeX)>mStatisComp.mRange+5||
		   Math.abs(rangeY)>mStatisComp.mRange+5)
		{ 
			Break();
			return;
		}
		
		
		if(mAtackPause>(0.01*mStatisComp.mAtcSpeed))//mustbe atackspeed
		{
			mAtackPause=0;
			mManager.changeAnimateType(sAnimKey);
			//тут он короче берет из таргет
			if(MathUtils.random(0,100)<80)
			if(mTargetManager.onHit(
				mStatisComp.mAtacPower,mManager.mEntity))
				
				Fin();
			
			//его статеманагер и там делает онхит
			//урон есть в зыс статах. все
		}
		
	}

	@Override
	public void Break()
	{
		mManager.onOutOfRange();
	}

	@Override
	public void Fin()
	{
		mManager.kill();
		mManager.nextState();
	}
	
}
