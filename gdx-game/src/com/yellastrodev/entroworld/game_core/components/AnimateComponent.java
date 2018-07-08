package com.yellastrodev.entroworld.game_core.components;

import com.badlogic.gdx.graphics.g2d.*;

public class AnimateComponent
{
	public Animation rightAnimation;
	public Animation leftAnimation;
	public Animation topAnimation;
	public Animation botAnimation;
	public AnimationStore mStore;
	public boolean isVisible = true;

	public double mFrameDuration;// = 0.2;

	public int mAnimCount;// = 3;

	public boolean isCycler;// = false;


	public void setAnimation(AnimateComponent fComp)
	{
		rightAnimation=fComp.rightAnimation;
		leftAnimation=fComp.leftAnimation;
		topAnimation=fComp.topAnimation;
		botAnimation=fComp.botAnimation;
		mFrameDuration = fComp.mFrameDuration;
		mAnimCount = fComp.mAnimCount;
		isCycler =  fComp.isCycler;
		
		isVisible = true;
	}
	
	public Animation getTexture(int fVectorIndex)
	{
		Animation fAnimation;
		switch(fVectorIndex)
		{
			case 1:
				fAnimation = topAnimation;
				break;
			case 2:
				fAnimation = leftAnimation;
				break;
			case 3:
				fAnimation = botAnimation;
				break;
			case 4:
				fAnimation = rightAnimation;
				break;
			default:
				fAnimation = rightAnimation;
				break;
		}
		return fAnimation;
	}
	
}
