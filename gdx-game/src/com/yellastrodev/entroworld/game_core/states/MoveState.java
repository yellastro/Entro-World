package com.yellastrodev.entroworld.game_core.states;
import com.yellastrodev.entroworld.game_core.components.*;
import com.yellastrodev.entroworld.game_core.states.state_managers.*;

public class MoveState implements iStateOfProsess
{

	public boolean isRun;

	@Override
	public String getAnimKey()
	{
		
		return "run";
	}
	

	public static void getDimention(VelocityComp mVelosComp,PositionComp fTargetPos,PositionComp fCurntPos)
	{
		float rangeX=fTargetPos.oX-fCurntPos.oX;
		float rangeY=fTargetPos.oY-fCurntPos.oY;

		if(Math.abs(rangeX)>Math.abs(rangeY))
		{
			if(rangeX>0)
				mVelosComp.angularVel=4;
			else
				mVelosComp.angularVel=2;
		}
		else
		if(rangeY>0)
			mVelosComp.angularVel=1;
		else
			mVelosComp.angularVel=3;
		
	}
	

	iProcessManager mManager;
	
	StatisticComponent mStatisComp;
	PositionComp mCurrPoss;
	public PositionComp mTargetPoss;
	VelocityComp mVelosComp;
	
	iStateOfProsess nextState;
	
	public MoveState(iProcessManager fManag,StatisticComponent fStatic
		,PositionComp fCurr, PositionComp fTarget,
		VelocityComp fVelos,boolean isRun_NotWalk)
		{
			mManager=fManag;
			mStatisComp=fStatic;
			mCurrPoss=fCurr;
			mTargetPoss=fTarget;
			mVelosComp=fVelos;
			isRun = isRun_NotWalk;
	}
		
	@Override
	public void Start()
	{
		mPauseTime=2;
	}
		
	
	float mPauseTime=0;
	@Override
	public void Update(float deltTime)
	{
		/*PositionComp fCurrentPos=mcurPosition;
		PositionComp fTargetPos=motSt.mtargetPos;*/

		mPauseTime+=deltTime;
		if(mPauseTime>1)
		{
			mPauseTime = 0;
		float rangeX=mTargetPoss.oX-mCurrPoss.oX;
		float rangeY=mTargetPoss.oY-mCurrPoss.oY;
/*
			if(Math.abs(rangeX)>Math.abs(rangeY))
			{
				if(rangeX>0)
					mVelosComp.angularVel=4;
				else
					mVelosComp.angularVel=2;
			}
			else
			if(rangeY>0)
				mVelosComp.angularVel=1;
			else
				mVelosComp.angularVel=3;
		*/getDimention(mVelosComp,mTargetPoss,mCurrPoss);
		if(Math.abs(rangeX)<mStatisComp.mRange&&
			 Math.abs(rangeY)<mStatisComp.mRange)
		{
			Fin();
					//motSt.msuperState.RunState();
		}
		else
		{
			float speed;
			if(isRun)
				speed=mStatisComp.mRunSpeed;
			else
				speed=20;
			//VelocityComp fvelocComp=motSt.mVelocityComp;
			float complxRange=Math.abs(rangeX)+Math.abs(rangeY);
			float Xcoef=rangeX/complxRange;
			float Ycoef=rangeY/complxRange;
		
			mVelosComp.velocoX=Xcoef*speed;
			mVelosComp.velocoY=Ycoef*speed;
			
			//getDimention(mVelosComp);
				
		}
		}
	}
	
	@Override
	public void Pause()
	{

		mVelosComp.velocoX=0;
		mVelosComp.velocoY=0;
	}

	@Override
	public void Break()
	{
		mVelosComp.velocoX=0;
		mVelosComp.velocoY=0;
	}

	@Override
	public void Fin()
	{
		mVelosComp.velocoX=0;
		mVelosComp.velocoY=0;
		
		mManager.nextState();
	}
	
}
