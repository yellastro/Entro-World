package com.yellastrodev.entroworld.game_core.entities;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.yellastrodev.entroworld.*;
import com.yellastrodev.entroworld.game_core.*;
import com.yellastrodev.entroworld.game_core.components.*;

public class Chicken extends Beast
{

	@Override
	public float getSize()
	{
		// TODO: Implement this method
		return 1;
	}


	@Override
	public CoupeOfSheets initAnim()
	{
		CoupeOfSheets fStore = new CoupeOfSheets(
			AnimationInitializer.getChickenAnimStore());
		
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
	
	public static int mRunSpeed=40;
	public static int mHP=20;
	public static int mAtackPower=2;
	public static int mRange=15;
	public static String mType="Fresh meat";
	String mFoodType = "Grass";
	
	public static Class mEnemy=null;
	public static Class mFood=String.class;
	public static Class mFrends=Chicken.class;
	

	private String txtRes="chicken_walk.png";
	
	public Chicken(Engine fcontroller)
	{
		super(fcontroller);
	}

	@Override
	public StatisticComponent initStatistic()
	{
		mBeastStatisticks=new BeastStatisticComponent();
		mStatistic=new StatisticComponent
		(mFrends,mRunSpeed,mHP,mAtackPower
		 ,mFood,mEnemy,mRange,mAtcSpeed);;
		return mStatistic;
	}
	

	
	@Override
	public Texture getTexture()
	{
		return new Texture( Gdx.files.internal(txtRes)); 
	}
}
