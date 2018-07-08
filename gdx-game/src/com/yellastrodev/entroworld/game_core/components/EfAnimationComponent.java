package com.yellastrodev.entroworld.game_core.components;

import com.badlogic.gdx.graphics.g2d.*;

public class EfAnimationComponent
{
	public Animation MainAnimation;
	
	public EfAnimStore mStore;
	public boolean isVisible = true;

	public double mFrameDuration;// = 0.2;

	public int mAnimCount;// = 3;

	public boolean isCycler;// = false;


	public void setAnimation(EfAnimationComponent fComp)
	{
		MainAnimation = fComp.MainAnimation;
		mFrameDuration = fComp.mFrameDuration;
		mAnimCount = fComp.mAnimCount;
		isCycler =  fComp.isCycler;

		isVisible = true;
	}

	
}
