package com.yellastrodev.entroworld.game_core.states.state_managers;

import com.badlogic.gdx.math.*;
import com.yellastrodev.entroworld.game_core.*;
import com.yellastrodev.entroworld.game_core.components.*;
import com.yellastrodev.entroworld.game_core.entities.*;
import com.yellastrodev.entroworld.game_core.nodes.*;
import com.yellastrodev.entroworld.game_core.states.*;
import java.util.*;
import com.yellastrodev.entroworld.game_core.states.life_cycles.*;
import com.yellastrodev.entroworld.game_core.states.action_states.*;

public class EffectProcesManager extends iProcessManager
{

	@Override
	public void Stay()
	{
		// TODO: Implement this method
	}


	@Override
	public void setLifeManager(iLifeCycle fManager)
	{
		// TODO: Implement this method
	}


	@Override
	public void kill()
	{
		// TODO: Implement this method
	}

	
	public LinkedList<iStateOfProsess> StatesStack=new LinkedList<>();

	

	public iStateOfProsess mPassiveState;
	public boolean isSearch=false;
	public float fPassiveTimer=0;

	
	public EfAnimationComponent mAnimationComponent;
	public EfAnimationNode mAnimationNode;
	public CoupeOfSheets mStore;
	
	public iProcessManager mTargetManager;

	private AtackState mAtackState;

	public EffectProcesManager(EnEntity fEnt,CoupeOfSheets fStore,Engine fcontroller)
	{
		mEngine=fcontroller;
		mEntity=fEnt;
		//mStore=fStore;
	}

	@Override
	public void inIt(iAnimationNode fNode)
	{
		mAnimationNode = (EfAnimationNode)fNode;
		mStatisComp=mEntity.mStatistic;
		mCurrPoss=(PositionComp)mEntity.getComponent(PositionComp.class);
		mVelosComp=(VelocityComp)mEntity.getComponent(VelocityComp.class);
		mAnimationComponent=(EfAnimationComponent)mEntity.getComponent(EfAnimationComponent.class);
		runAway();
	}

	
	public void Update(float time)
	{
		getActiveState().Update(time);
		//mLiveManager.update(time);
		fPassiveTimer=fPassiveTimer+time;
		if(fPassiveTimer>2&&isSearch&&mPassiveState!=null)
		{
			mPassiveState.Update(time);
			fPassiveTimer=0;
		}

	}

	public void startSearchinTarget(SearchFilter fFilter)
	{
		/*mPassiveState=new SearchingState(this,mEngine,
										 mStatisComp,mCurrPoss,fFilter);
		mPassiveState.Start();*/
		isSearch=true;
	}

	public void GetTarget(EnEntity fTarget)
	{
		mTargetPoss=(PositionComp)fTarget
			.getComponent(PositionComp.class);
		mTargetManager = fTarget.mProcessManager;
		setActiveState(getRunSt());
		isSearch=false;
	}
	
	public void HitTarget()
	{
		mTargetManager.onHit(mStatisComp.mAtacPower,mEntity);
		changeAnimateType("fly");
		setActiveState(new RestState(this));
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

	public void stay()
	{
		setActiveState(getStandState());
	}

	public boolean onHit(int fDamage,EnEntity fAtacker)
	{
		mStatisComp.mHP-=fDamage;

		if(mStatisComp.mHP<1)
		{
			onDead();
			return true;
		}

		if(!mTargetPoss.equals(fAtacker.Components.get(PositionComp.class)))

			if(fAtacker.mStatistic.mHP<mStatisComp.mHP*1.5)
				GetTarget(fAtacker);
			else 
				runAway();

		return false;
	}

	public void onOutOfRange()
	{
		RunThere(mTargetPoss);
	}

	public void onDead()
	{
		changeAnimateType("hit");
		StatesStack.clear();
		//StatesStack.add(new RestState(this));
		mEntity.Dead();
	}

	public void nextState()
	{

		/*
		if(StatesStack.size()<2)
		{
			runAway();
		}
		StatesStack.remove(0);
		StatesStack.get(0).Start();*/
		HitTarget();
		
		changeAnimateType(getActiveState().getAnimKey());
	}

	public void setActiveState(iStateOfProsess ob)
	{
		if(!StatesStack.isEmpty())
			getActiveState().Pause();
		StatesStack.addFirst(ob);
		ob.Start();
		changeAnimateType(getActiveState().getAnimKey());
	}

	public iStateOfProsess getActiveState()
	{
		return StatesStack.getFirst();
	}

	AtackState getAtackState(iProcessManager fTargetMng)
	{
		if(mAtackState==null)
		{
			mAtackState=new AtackState(this
									   ,mStatisComp
									   ,mCurrPoss,mTargetPoss
									   ,fTargetMng);
		}
		else
		{
			mAtackState.mTargetManager=fTargetMng;
			mAtackState.mTargetPoss=mTargetPoss;
		}
		//changeAnimateType("atack");
		return mAtackState;
	}

	MoveState getRunSt()
	{
			mStateRun=new BulletFly(this
									,mStatisComp
									,mCurrPoss,mTargetPoss,mVelosComp);
		return mStateRun;
	}

	RestState getStandState()
	{
		//isSearch=true;
		changeAnimateType("stand");
		return new RestState(null);
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
