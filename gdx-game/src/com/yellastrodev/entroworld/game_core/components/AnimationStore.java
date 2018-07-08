package com.yellastrodev.entroworld.game_core.components;

public class AnimationStore
{
	public AnimateComponent mRunAnimation;
	public AnimateComponent mStandAnimation;
	public AnimateComponent mAtackAnimation;
	public AnimateComponent mDeadAnimation;


	public AnimateComponent getAnimation(String fKey)
	{
		if(fKey.equals("run"))
		{
			return mRunAnimation;
		}
		
		if(fKey.equals("stand"))
			return mStandAnimation;
			
		if(fKey.equals("atack"))
			return mAtackAnimation;
		
		if(fKey.equals("dead"))
			return mDeadAnimation;
			
		return mStandAnimation;
	}
}
