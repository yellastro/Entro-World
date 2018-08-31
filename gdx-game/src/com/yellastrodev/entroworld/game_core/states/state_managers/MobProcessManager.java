package com.yellastrodev.entroworld.game_core.states.state_managers;
import com.yellastrodev.entroworld.game_core.entities.*;
import com.yellastrodev.entroworld.game_core.nodes.*;
import com.yellastrodev.entroworld.game_core.states.life_cycles.*;
import com.yellastrodev.entroworld.game_core.states.*;
import com.yellastrodev.entroworld.game_core.components.*;
import com.yellastrodev.entroworld.game_core.*;

public class MobProcessManager extends iProcessManager
{
	public iLifeCycle mLifeManager;
	
	private float mPassiveTimer = 0;

	public MobProcessManager(Mob fMob, CoupeOfSheets fStore, Engine fEngine)
	{
		isSearch = false;
		mEngine=fEngine;
		mEntity=fMob;
		mStore=fStore;
	}
	
	@Override
	public void inIt(iAnimationNode fNode)
	{
		mAnimationNode = (AnimationNode)fNode;
		mStatisComp=mEntity.mStatistic;
		mCurrPoss=(PositionOnMapComponent)mEntity.getComponent(PositionOnMapComponent.class);
		mVelosComp=(VelocityComp)mEntity.getComponent(VelocityComp.class);
		mAnimationComponent=(AnimateComponent)mEntity.getComponent(AnimateComponent.class);
		setActiveState(getStandState());
	}
	
	@Override
	public void setLifeManager(iLifeCycle fManager)
	{
		mLifeManager = fManager;
		nextState();
	}
	
	//		---Main function---

	@Override
	public void Update(float dTime)
	{
		getActiveState().Update(dTime);
		mLifeManager.update(dTime);
		mPassiveTimer=mPassiveTimer+dTime;
		if(mPassiveTimer>2&&isSearch&&mPassiveState!=null)
		{
			mPassiveState.Update(dTime);
			mPassiveTimer=0;
		}
	}
	
	@Override
	public void nextState()
	{
		if(!mStatesStack.isEmpty())
			mStatesStack.remove(0);

		if(mStatesStack.size()<1)
		{
			//StatesStack.add(getStandState());
			mLifeManager.onOverStates();
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
	
	//		---Public Commands---
	
	public void startSearchinTarget(SearchFilter fFilter)
	{
		mPassiveState=new SearchingState(this,mEngine,
										 mStatisComp,mCurrPoss,fFilter);
		mPassiveState.Start();
		isSearch=true;
	}
	
	@Override
	public void GetTarget(EnEntity fEntity)
	{
		mTargetPoss=(PositionOnMapComponent)fEntity
			.getComponent(PositionOnMapComponent.class);
		setActiveState(getAtackState
					   (fEntity.mProcessManager));
		isSearch=false;
	}

	@Override
	public void RunThere(PositionOnMapComponent fPos)
	{
		mTargetPoss=fPos;
		setActiveState(
			getRunSt());
	}
	
	public void runAway()
	{
		mTargetPoss=PositionOnMapComponent.setNewPos();

		RunThere(mTargetPoss);
	}

	@Override
	public void Stay()
	{
		setActiveState(getStandState());
	}
	
	public void WalkThere(PositionOnMapComponent fTargetPos)
	{
		mTargetPoss=fTargetPos;
		setActiveState(
			getWalkState());
	}

	public void WalkAround()
	{
		mTargetPoss=PositionOnMapComponent.setNewPos();

		WalkThere(mTargetPoss);
	}
	
	//		---Actions---
	
	@Override
	public void kill()
	{
		nextState();
	}

	@Override
	public void onOutOfRange()
	{
		RunThere(mTargetPoss);
	}

	@Override
	public boolean onHit(int fDamage, EnEntity fAtacker)
	{
		mStatisComp.mHP-=fDamage;

		if(mStatisComp.mHP<1)
		{
			onDead();
			return true;
		}
		
		if(!(getActiveState().getClass()==AtackState.class)
			&&!mTargetPoss.equals(fAtacker.Components.get(PositionOnMapComponent.class)))

			if(fAtacker.mStatistic.mHP<mStatisComp.mHP*6)
				GetTarget(fAtacker);
			else 
				runAway();

		return false;
	}
	
	public void onDead()
	{
		//changeAnimateType("dead");
		mStatesStack.clear();
		mStatesStack.add(new RestState(this));
		mEntity.Dead();
	}
	
	//		---Initiators---
	
	AtackState getAtackState(iProcessManager fTargetMng)
	{
		mAtackState=new AtackState(this
			   ,mStatisComp
			   ,mCurrPoss,mTargetPoss
			   ,fTargetMng);
		
		return mAtackState;
	}

	MoveState getRunSt()
	{
		mStateRun=new MoveState(this
				,mStatisComp
				,mCurrPoss,mTargetPoss,mVelosComp,true);

		return mStateRun;
	}
	
	private iStateOfProsess getWalkState()
	{
		mStateRun=new MoveState(this
					,mStatisComp
					,mCurrPoss,mTargetPoss,mVelosComp,false);
		
		return mStateRun;
	}
	
	RestState getStandState()
	{
		return new RestState(this);
	}

	@Override
	public void changeAnimateType(String sAnimKey)
	{
		mAnimationNode.setAnimationType(sAnimKey);
	}

}
