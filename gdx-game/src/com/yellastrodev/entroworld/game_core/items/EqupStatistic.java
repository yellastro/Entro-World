package com.yellastrodev.entroworld.game_core.items;

public class EqupStatistic
{
	public int mArmor;
	public int mDamage;
	public int mAtackSpeed;
	
	public EqupStatistic(String fArmor)
	{
		mArmor = Integer.parseInt(fArmor);
	}
	
	public EqupStatistic(String fDamage,String fAtSp)
	{
		mDamage = Integer.parseInt(fDamage);
	}
}
