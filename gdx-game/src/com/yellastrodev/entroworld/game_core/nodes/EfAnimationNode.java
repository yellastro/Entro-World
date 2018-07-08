package com.yellastrodev.entroworld.game_core.nodes;

import com.badlogic.gdx.graphics.g2d.*;
import com.yellastrodev.entroworld.game_core.components.*;
import java.util.*;

public class EfAnimationNode implements iAnimationNode
{
	public List<EfAnimationComponent> mAnimComponent =new ArrayList<>();
	public VelocityComp mVelcComponent;
	public DisplayComp mTextrComponent;
	
	public List<EfAnimStore> mStote = new ArrayList<>();

	private int mFrameIndex=0;

	

	public EfAnimationNode(List<EfAnimStore> acom,VelocityComp veoc,DisplayComp disc)
	{
		//mAnimComponent = new AnimateComponent[1];
		mStote =(acom);
		mVelcComponent=veoc;
		mTextrComponent=disc;
		setAnimationType("fly");
	}

	public void updateAnimationComponent(List<EfAnimationComponent> fComp)
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
				if(!mAnimComponent.get(0).isCycler)
				{
					setAnimationType("stand");
					return;
				} 
				else
					mFrameIndex=0;
			}
			else
				mFrameIndex++;
		}


		for (EfAnimationComponent qAnim:mAnimComponent)//int i=0;i<fTextures.size();i++)
		{
			if(qAnim.isVisible)
			{

				
				Animation qAnn= qAnim.MainAnimation;
				TextureRegion qTexture = 
					qAnn.getKeyFrames()[mFrameIndex];
				float fRotate=(mVelcComponent.velocoY/
				mVelcComponent.velocoX)*45;
				mTextrComponent.mRotate = fRotate+90;
				
				fTextures.add(qTexture);
				
			}
		}

		mTextrComponent.mSprite=fTextures;

	}

	public void setAnimationType(String fType)
	{
		mFrameIndex=0;
		mAnimComponent = new ArrayList<>();
		int i=0;
		for(EfAnimStore qStore:mStote)
		{
			
			EfAnimationComponent qNewAnim = qStore.getAnimation(fType);
			if(qNewAnim!=null)
			{
				mAnimComponent.add(qNewAnim);/*
				 mFrameDuration=qAnim.mFrameDuration;
				 isntCycler=qAnim.isntCycler;*/
			}
			//else
				//qAnim.isVisible=false;
		}
		//getAnimation(5);
	}
}
