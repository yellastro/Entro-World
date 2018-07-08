package com.yellastrodev.entroworld.game_core.nodes;
import com.yellastrodev.entroworld.game_core.components.*;
import com.badlogic.gdx.graphics.g2d.*;
import java.util.*;
import android.util.*;

public class AnimationNode implements iAnimationNode
{
	public List<AnimateComponent> mAnimComponent =new ArrayList<>();
	public VelocityComp mVelcComponent;
	public DisplayComp mTextrComponent;

	private int mFrameIndex=0;
	
	private boolean isntCycler;

	private int mAnimCount;

	private float mFrameDuration;
	
	public AnimationNode(AnimateComponent acom,VelocityComp veoc,DisplayComp disc)
	{
		//mAnimComponent = new AnimateComponent[1];
		mAnimComponent.add(acom);
		mVelcComponent=veoc;
		mTextrComponent=disc;
	}
	
	public AnimationNode(List<AnimateComponent> acom,VelocityComp veoc,DisplayComp disc)
	{
		//mAnimComponent = new AnimateComponent[1];
		mAnimComponent =(acom);
		mVelcComponent=veoc;
		mTextrComponent=disc;
		
	}
	
	public void updateAnimationComponent(List<AnimateComponent> fComp)
	{
		mAnimComponent=fComp;
	}
	
	
	private double mLastTimeChangeFrame = 0;
	public void update(float stateTimer)
	{
		List<TextureRegion> fTextures=new ArrayList<>();
		
		//mLastTimeChangeFrame=stateTimer+mLastTimeChangeFrame;
		if(stateTimer-mLastTimeChangeFrame>
		   (mAnimComponent.get(0).mFrameDuration))
		{
			mLastTimeChangeFrame =stateTimer;
				//mLastTimeChangeFrame-mAnimComponent.get(0).mFrameDuration;

			
			if(mFrameIndex==mAnimComponent.get(0).mAnimCount-1)
			{
				mFrameIndex=0;
				if(!mAnimComponent.get(0).isCycler)
				{
					setAnimationType("stand");
					return;
				} 
				
					
			}
			else
				mFrameIndex++;
		}
		
		
		for (AnimateComponent qAnim:mAnimComponent)//int i=0;i<fTextures.size();i++)
		{
			if(qAnim.isVisible)
			{
				
				
				Animation qAnn= qAnim.getTexture(mVelcComponent.angularVel);
				try
				{
					TextureRegion qTexture = 
					qAnn.getKeyFrames()[mFrameIndex];
					fTextures.add(qTexture);
				}catch(ArrayIndexOutOfBoundsException e)
				{Log.i("AnimNode",Integer.toString(qAnim.mAnimCount),e);}
				
				/* ---это рабоает---
				fTextures.add(
					qAnim.getTexture(mVelcComponent.angularVel)
					.getKeyFrame(stateTimer,true));
				*/
			}
		}
			
		mTextrComponent.mSprite=fTextures;
		
	}
	
	public void setAnimationType(String fType)
	{
		mFrameIndex=0;
		for(AnimateComponent qAnim:mAnimComponent)
		{
			AnimateComponent qNewAnim = qAnim.mStore.getAnimation(fType);
			if(qNewAnim!=null)
			{
				qAnim.setAnimation(qNewAnim);/*
				mFrameDuration=qAnim.mFrameDuration;
				isntCycler=qAnim.isntCycler;*/
			}
			else
				qAnim.isVisible=false;
		}
		//getAnimation(5);
	}
}
