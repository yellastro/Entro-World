package com.yellastrodev.entroworld.game_core.entities.mobs.undead;

import com.yellastrodev.entroworld.game_core.*;
import com.yellastrodev.entroworld.game_core.components.*;
import com.yellastrodev.entroworld.game_core.entities.*;
import com.yellastrodev.entroworld.game_core.entities.mobs.*;
import com.yellastrodev.entroworld.game_core.nodes.*;
import com.yellastrodev.entroworld.game_core.systems.*;
import com.yellastrodev.entroworld.*;

public class Skelet extends Undead
{

	@Override
	public float getSize()
	{
		return 1;
	}

	@Override
	public CoupeOfSheets initAnim()
	{
		CoupeOfSheets fStore = new CoupeOfSheets(
			AnimationInitializer.getSkeletAnimStore());
		fStore.mStores.add(AnimationInitializer.getSwordAnimation());
		fStore.mStores.add(AnimationInitializer.getHeadChainArmor());
		
		return fStore;
	}

	@Override
	public static Class mEnemy=Mob.class;
	public static Class mFood=Beast.class;
	public static Class mFrends=Undead.class;

	public static int height=70;
	public static int widht=70;
	public static int mRunSpeed=80;
	public static int mHP=200;
	public static int mAtackPower=20;
	public static int mAtcSpeed=100;//tipo % of 1per second
	public static int mRange=95;
	
	public AnimationStore mEqup;
	public AnimationStore mBody;
	
	public Skelet(Engine fcontroller)
	{
		super(fcontroller);
		mBody = fcontroller.animInit.getSkeletAnimStore();
		mEqup = fcontroller.animInit.getSwordAnimation();
	}

	
	public AnimationStore[] getTextures()
	{
		AnimationStore[] fTextures= new AnimationStore[2];
		fTextures[0]=mBody;fTextures[1]=mEqup;
		return fTextures;
	}
	
	

	@Override
	public Class getEnemy()
	{
		// TODO: Implement this method
		return Mob.class;
	}

	@Override
	public Class getFood()
	{
		// TODO: Implement this method
		return Mob.class;
	}

	@Override
	public Class getExcept()
	{
		// TODO: Implement this method
		return Undead.class;
	}

	@Override
	public StatisticComponent initStatistic()
	{
		mStatistic=new StatisticComponent
		(mFrends,mRunSpeed,mHP,mAtackPower
		 ,mFood,mEnemy,mRange,mAtcSpeed);
		return mStatistic;
	}

	/*
	@Override
	protected void makeAnimationNode(AnimateComponent fAnimComp, VelocityComp fVelocityCom, DisplayComp fDisplComp)
	{
		AnimationNode aNode=new AnimationNode(fAnimComp,fVelocityCom,fDisplComp);
		iSystem ss=mEngine.mSystems.get(sysAnimations.class);
		ss.addNode(aNode);
		mNodes.add(aNode);
		
		AnimationInitializer.getSwordAnimation();
	}
	*/
	
}
