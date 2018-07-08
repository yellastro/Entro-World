package com.yellastrodev.entroworld.game_core.entities;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.yellastrodev.entroworld.game_core.*;
import com.yellastrodev.entroworld.game_core.components.*;
import com.yellastrodev.entroworld.*;

public class Imp extends Demon
{

	@Override
	public float getSize()
	{return 2;
	}

	@Override
	public CoupeOfSheets initAnim()
	{
		CoupeOfSheets fStore = new CoupeOfSheets(
			AnimationInitializer.getImpAnimStore());
		
		return fStore;
	}
	

	public static Class mEnemy=Mob.class;
	public static Class mFood=Beast.class;
	public static Class mFrends=Demon.class;
	
	
	

	public static int height=70;
	public static int widht=70;
	public static int mRunSpeed=60;
	public static int mHP=500;
	public static int mAtackPower=20;
	public static int mAtcSpeed=120;//tipo % of 1per second
	public static int mRange=35;
	public static String mType="Demon";
	String mFoodType="Beast";

	private static String txtRes = "wolfsheet_browngrey.png";

	private static Texture entitexture;

	public Imp(Engine fcontroller)
	{
		super(fcontroller);
	}
	
	@Override
	public StatisticComponent getStatistics()
	{
		return mStatistic;
	}
	
	@Override
	public StatisticComponent initStatistic()
	{
		mStatistic=new StatisticComponent
		(mFrends,mRunSpeed,mHP,mAtackPower
		 ,mFood,mEnemy,mRange,mAtcSpeed);
		return mStatistic;
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
		return Demon.class;
	}

	@Override
	public Texture getTexture()
	{
		return new Texture( Gdx.files.internal(txtRes)); 
	}
}
