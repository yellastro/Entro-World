package com.yellastrodev.entroworld.game_core.states;
import com.badlogic.gdx.math.*;
import com.yellastrodev.entroworld.game_core.*;
import com.yellastrodev.entroworld.game_core.components.*;
import com.yellastrodev.entroworld.game_core.entities.*;
import com.yellastrodev.entroworld.game_core.nodes.*;
import com.yellastrodev.entroworld.game_core.states.state_managers.*;
import java.util.*;
import com.yellastrodev.entroworld.game_core.states.life_cycles.*;

public class ProsessStateManager extends iProcessManager
{

	public iLifeCycle mLiveManager;
	
	
	public ProsessStateManager(EnEntity fEnt,CoupeOfSheets fStore,Engine fcontroller)
	{
		isSearch = false;
		mEngine=fcontroller;
		mEntity=fEnt;
		mStore=fStore;
	}
	
	@Override
	public void inIt(iAnimationNode fNode)
	{
		mAnimationNode = (AnimationNode)fNode;
		mStatisComp=mEntity.mStatistic;
		mCurrPoss=(PositionComp)mEntity.getComponent(PositionComp.class);
		mVelosComp=(VelocityComp)mEntity.getComponent(VelocityComp.class);
		mAnimationComponent=(AnimateComponent)mEntity.getComponent(AnimateComponent.class);
	}
	
	@Override
	public void setLifeManager(iLifeCycle fManager)
	{
		mLiveManager = fManager;
		nextState();
	}

	public float fPassiveTimer=0;
	
	@Override
	public void Update(float time)
	{
		getActiveState().Update(time);
		mLiveManager.update(time);
		fPassiveTimer=fPassiveTimer+time;
		if(fPassiveTimer>2&&isSearch&&mPassiveState!=null)
		{
			mPassiveState.Update(time);
			fPassiveTimer=0;
		}
			
	}
	
	public void startSearchinTarget(SearchFilter fFilter)
	{
		mPassiveState=new SearchingState(this,mEngine,
			mStatisComp,mCurrPoss,fFilter);
		mPassiveState.Start();
		isSearch=true;
	}
	
	public void GetTarget(EnEntity fTarget)
	{
		mTargetPoss=(PositionComp)fTarget
			.getComponent(PositionComp.class);
		setActiveState(getAtackState
			(fTarget.mProcessManager));
		isSearch=false;
	}
	
	public void RunThere(PositionComp fTargetPos)
	{
		mTargetPoss=fTargetPos;
		//mTargetPoss.oX=MathUtils.random(-2.0f, 400.0f);
		//mTargetPoss.oY=MathUtils.random(-2.0f, 400.0f);
		setActiveState(
			getRunSt());
		//changeAnimateType("run");
	}
	
	@Override
	public void kill()
	{
		mLiveManager.kill();
	}
	
	public void WalkThere(PositionComp fTargetPos)
	{
		mTargetPoss=fTargetPos;
		//mTargetPoss.oX=MathUtils.random(-2.0f, 400.0f);
		//mTargetPoss.oY=MathUtils.random(-2.0f, 400.0f);
		setActiveState(
			getWalkState());
		//changeAnimateType("run");
	}

	private iStateOfProsess getWalkState()
	{
		if(mStateRun==null)
		{
			mStateRun=new MoveState(this
									,mStatisComp
									,mCurrPoss,mTargetPoss,mVelosComp,false);
		}
		else
		{
			mStateRun.mTargetPoss=mTargetPoss;
			mStateRun.isRun = false;
		}
			
		return mStateRun;
	}
	
	public void WalkAround()
	{
		mTargetPoss=PositionComp.setNewPos();

		WalkThere(mTargetPoss);
	}
	
	public void runAway()
	{
		mTargetPoss=PositionComp.setNewPos();

		RunThere(mTargetPoss);
	}
	
	public void Stay()
	{
		setActiveState(getStandState());
	}
	
	@Override
	public boolean onHit(int fDamage,EnEntity fAtacker)
	{
		mStatisComp.mHP-=fDamage;
		
		if(mStatisComp.mHP<1)
		{
			onDead();
			return true;
		}
		
		if(mTargetPoss!=(fAtacker.Components.get(PositionComp.class)))
			
			if(fAtacker.mStatistic.mHP<mStatisComp.mHP*3)
				GetTarget(fAtacker);
			else 
				runAway();
		
		return false;
	}
	
	@Override
	public void onOutOfRange()
	{
		RunThere(mTargetPoss);
	}
	
	public void onDead()
	{
		//changeAnimateType("dead");
		mStatesStack.clear();
		mStatesStack.add(new RestState(this));
		mEntity.Dead();
	}
	
	@Override
	public void nextState()
	{
		if(!mStatesStack.isEmpty())
			mStatesStack.remove(0);
		
		if(mStatesStack.size()<1)
		{
			//StatesStack.add(getStandState());
			mLiveManager.onOverStates();
			return;
		}
		mStatesStack.get(0).Start();
		changeAnimateType(getActiveState().getAnimKey());
	}
	
	public void setActiveState(iStateOfProsess ob)
	{
		if(!mStatesStack.isEmpty())
			getActiveState().Pause();
		mStatesStack.addFirst(ob);
		ob.Start();
		changeAnimateType(getActiveState().getAnimKey());
	}
	
	@Override
	public iStateOfProsess getActiveState()
	{
		return mStatesStack.getFirst();
	}
	
	AtackState getAtackState(iProcessManager fTargetMng)
	{
		//if(mAtackState==null)
		{
			mAtackState=new AtackState(this
				,mStatisComp
				,mCurrPoss,mTargetPoss
				,fTargetMng);
		}/*
		else
		{
			mAtackState.mTargetManager=fTargetMng;
			mAtackState.mTargetPoss=mTargetPoss;
		}
		//changeAnimateType("atack");*/
		return mAtackState;
	}
	
	MoveState getRunSt()
	{
		if(mStateRun==null)
		{
			mStateRun=new MoveState(this
				,mStatisComp
				,mCurrPoss,mTargetPoss,mVelosComp,true);
		}
		else
		{
			mStateRun.mTargetPoss=mTargetPoss;
			mStateRun.isRun=true;
		}
			
		return mStateRun;
	}
	
	RestState getStandState()
	{
		//isSearch=true;
		//changeAnimateType("stand");
		return new RestState(this);
	}
	
	public void changeAnimateType(String fKey)
	{
		mAnimationNode.setAnimationType(fKey);
		//String fKey=fState.stateKey;
		/*AnimateComponent fComp=
			mStore.getAnimation(fKey);
		mAnimationComponent.setAnimation(fComp);*/
	}
}
