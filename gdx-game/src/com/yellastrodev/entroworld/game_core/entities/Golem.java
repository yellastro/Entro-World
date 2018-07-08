package com.yellastrodev.entroworld.game_core.entities;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.yellastrodev.entroworld.*;
import com.yellastrodev.entroworld.game_core.*;
import com.yellastrodev.entroworld.game_core.components.*;

public class Golem extends Mob
{

	@Override
	public float getSize()
	{
		return 2;
	}

	@Override
	public CoupeOfSheets initAnim()
	{
		CoupeOfSheets fStore = new CoupeOfSheets(
			AnimationInitializer.getGolemAnimStore());
		
		return fStore;
	}


	


	
	
	@Override
	public Class getEnemy()
	{
		// TODO: Implement this method
		return null;
	}

	@Override
	public Class getFood()
	{
		// TODO: Implement this method
		return null;
	}

	@Override
	public Class getExcept()
	{
		// TODO: Implement this method
		return null;
	}

	public static int height=70;
	public static int widht=70;
	public static int mRunSpeed=40;
	public static int mHP=500;
	public static int mAtackPower=20;
	public static int mAtcSpeed=120;//tipo % of 1per second
	public static int mRange=35;
	public static String mType="Beast";
	String mFoodType="Demon";
	
	public static Class mEnemy=Demon.class;
	public static Class mFood=Demon.class;
	public static Class mFrends=Golem.class;
	

	private static String txtRes = "wolfsheet_browngrey.png";

	private static Texture entitexture;

	public Golem(Engine fcontroller)
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
		return null;
	}

	@Override
	public Texture getTexture()
	{
		return new Texture( Gdx.files.internal(txtRes)); 
	}
	
}
