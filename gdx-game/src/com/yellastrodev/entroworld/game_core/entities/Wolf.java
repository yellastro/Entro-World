package com.yellastrodev.entroworld.game_core.entities;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.yellastrodev.entroworld.*;
import com.yellastrodev.entroworld.game_core.*;
import com.yellastrodev.entroworld.game_core.components.*;
import com.yellastrodev.entroworld.game_core.states.state_managers.*;

public class Wolf extends Beast
{

	@Override
	public float getSize()
	{return 1;
	}
	

	
	@Override
	public CoupeOfSheets initAnim()
	{
		CoupeOfSheets fStore = new CoupeOfSheets(
			AnimationInitializer.getWolfAnimationStore());
		
		return fStore;
	}

	
	@Override
	public Class getEnemy()
	{

		return Mob.class;
	}

	@Override
	public Class getFood()
	{
		// TODO: Implement this method
		return Beast.class;
	}

	@Override
	public Class getExcept()
	{
		// TODO: Implement this method
		return Wolf.class;
	}

	public static int height=70;
	public static int widht=70;
	public static int mRunSpeed=70;
	public static int mHP=100;
	public static int mAtackPower=20;
	public static int mAtcSpeed=70;//tipo % of 1per second
	public static int mRange=25;
	public static String mType="Beast";
	String mFoodType="Fresh meat";
	public static Class mEnemy=EnEntity.class;
	public static Class mFood=Beast.class;
	public static Class mFrends=Wolf.class;

	private static String txtRes = "wolfsheet_browngrey.png";

	private static Texture entitexture;

	public Wolf(Engine fcontroller)
	{
		super(fcontroller);
	}
	/*
	@Override
	public StatisticComponent getStatistics()
	{
		return new StatisticComponent
		(mType,mRunSpeed,mHP,mAtackPower
		 ,mFoodType,mRange,mAtcSpeed);
	}*/
	@Override
	public StatisticComponent initStatistic()
	{
		mStatistic=new StatisticComponent
		(mFrends,mRunSpeed,mHP,mAtackPower
		 ,mFood,mEnemy,mRange,mAtcSpeed);
		 mBeastStatisticks=new BeastStatisticComponent();
		return mStatistic;
	}

	@Override
	public Texture getTexture()
	{
		return new Texture( Gdx.files.internal(txtRes)); 
	}

	/*
	@Override
	public void initProcessManager(CoupeOfSheets fStore, Engine fEngine)
	{
		mProcessManager = new MobProcessManager(this, fStore, fEngine);
	}*/

	
	@Override
	public void initLiveCicle()
	{
		mProcessManager.setLifeManager(
			new PlayerController(mEngine,(MobProcessManager)mProcessManager));
	}
	
	
}
