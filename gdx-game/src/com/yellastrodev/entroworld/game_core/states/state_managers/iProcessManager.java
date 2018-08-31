package com.yellastrodev.entroworld.game_core.states.state_managers;
import com.yellastrodev.entroworld.game_core.*;
import com.yellastrodev.entroworld.game_core.components.*;
import com.yellastrodev.entroworld.game_core.entities.*;
import com.yellastrodev.entroworld.game_core.nodes.*;
import com.yellastrodev.entroworld.game_core.states.*;
import com.yellastrodev.entroworld.game_core.states.life_cycles.*;
import java.util.*;

public abstract class iProcessManager
{
	public EnEntity mEntity;
	public Engine mEngine;
	
	public StatisticComponent mStatisComp;
	public PositionOnMapComponent mCurrPoss;
	public PositionOnMapComponent mTargetPoss;
	public VelocityComp mVelosComp;
	public AnimateComponent mAnimationComponent;
	public AnimationNode mAnimationNode;
	public CoupeOfSheets mStore;

	public AtackState mAtackState;
	

	public LinkedList<iStateOfProsess> mStatesStack=new LinkedList<>();

	public MoveState  mStateRun;

	public iStateOfProsess mPassiveState;
	public  boolean isSearch;

	public abstract void setLifeManager(iLifeCycle fManager)
	
	public abstract void Update(float dTime)
	
	public abstract iStateOfProsess getActiveState()

	public abstract void inIt(iAnimationNode fNode)
	
	public abstract void GetTarget(EnEntity fEntity)
	
	public abstract void RunThere(PositionOnMapComponent fPos)
	
	public abstract void Stay()

	public abstract void kill();

	public abstract void onOutOfRange();
	
	public abstract boolean onHit(int fDamage,EnEntity fEntity);

	public abstract void changeAnimateType(String sAnimKey);
	
	public abstract void nextState();

}
