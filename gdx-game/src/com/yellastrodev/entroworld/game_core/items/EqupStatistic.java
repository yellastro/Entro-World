package com.yellastrodev.entroworld.game_core.items;

public class EqupStatistic
{
	public int mArmor;
	public int mDamage;
	public int mAtackSpeed;
	
	public EqupStatistic(String fArmor)
	{
		try{
			mArmor = Integer.parseInt(fArmor);
		}
		catch(NumberFormatException e)
		{
			mArmor = 0;
		}
		
	}
	
	public EqupStatistic(String fDamage,String fAtSp)
	{
		try
		{
			mDamage = Integer.parseInt(fDamage);
		}
		catch(NumberFormatException e)
		{
			mDamage = 0;
		}
	}
}
