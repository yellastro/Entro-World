package com.yellastrodev.entroworld.game_core.components;

public class EfAnimStore
{
	public EfAnimationComponent mFlyAnimation;
	public EfAnimationComponent mHitAnimation;
	
	public EfAnimationComponent getAnimation(String fKey)
	{
		if(fKey.equals("fly"))
		{
			return mFlyAnimation;
		}

		if(fKey.equals("hit"))
			return mHitAnimation;

		

		return mFlyAnimation;
	}
}
