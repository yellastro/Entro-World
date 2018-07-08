package com.yellastrodev.entroworld.game_core.components;

public class StatisticComponent
{
	public float mRunSpeed;
	public Class mEnemy;
	public Class mFood;
	public Class mFrends;
	public int mHP;
	public int mMaxHP;
	
	public int mAtacPower;
	public int mRange;
	public int mAtcSpeed;
	
	
	
	
	public StatisticComponent(Class fType,
		float fRunSpeed,int fHp,int fAtac,Class fFoodt,
		Class fEnemy,int fRange,int fatcs)
	{
		mFrends=fType;
		mRunSpeed=fRunSpeed;
		mMaxHP=mHP=fHp;
		mAtacPower=fAtac;
		mFood=fFoodt;
		mEnemy=fEnemy;
		
		mRange=fRange;
		mAtcSpeed=fatcs;
	}
		
}
