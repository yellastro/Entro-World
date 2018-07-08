package com.yellastrodev.entroworld.static_initializers;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.yellastrodev.entroworld.game_core.components.*;
import com.yellastrodev.entroworld.*;

public  class OneDirectionAnimInit
{
	public static EfAnimStore getFireBallAnimation()
	{
		EfAnimStore fStore = new EfAnimStore();
		
		fStore.mFlyAnimation = getFireBallFlyAnimatin();
		fStore.mHitAnimation = getExplodeAnimation();
		
		return fStore;
	}
	
	public static EfAnimationComponent getFireBallFlyAnimatin()
	{
		TextureRegion[][] f2DRegion;

		String fRes = "flames";
		int fSizeX = 4;
		int fSizeY = 3;
		
		f2DRegion=AnimationInitializer.getRegion(new Texture(Gdx.files.internal("effects/"+fRes+".png"))
			,fSizeX,fSizeY,0);
								 
		TextureRegion[] walkFrames;
		walkFrames = new TextureRegion[fSizeX*fSizeY];

        int index = 0;
        for (int i = 0; i < fSizeX; i++)
			for(int j = 0; j<fSizeY; j++)
            walkFrames[index++] = f2DRegion[j][i];

        Animation fAnimation = new Animation(0.085f, walkFrames);
		EfAnimationComponent fComponent =
			new EfAnimationComponent();
		fComponent.MainAnimation=fAnimation;
		fComponent.mAnimCount=fSizeX*fSizeY;
		fComponent.mFrameDuration = 0.04;
		fComponent.isCycler = true;
		
		return fComponent;
	}
	
	public static EfAnimationComponent getExplodeAnimation()
	{
		

		String fRes = "Explosion Medium";
		int fSizeX = 6;
		int fSizeY = 1;

		Animation fAnimation = getEfectAnim(fSizeX,fSizeY,fRes);
		
		EfAnimationComponent fComponent =
			new EfAnimationComponent();
		fComponent.MainAnimation=fAnimation;
		fComponent.mAnimCount=fSizeX*fSizeY;
		fComponent.mFrameDuration = 0.04;
		fComponent.isCycler = true;

		return fComponent;
	}
	
	public static Animation getEfectAnim(int fSizeX,int fSizeY,String fRes)
	{
		TextureRegion[][] f2DRegion;
		f2DRegion=AnimationInitializer.getRegion(new Texture(Gdx.files.internal("effects/"+fRes+".png"))
												 ,fSizeX,fSizeY,0);
		
		TextureRegion[] walkFrames;
		walkFrames = new TextureRegion[fSizeX*fSizeY];

        int index = 0;
        for (int i = 0; i < fSizeX; i++)
			for(int j = 0; j<fSizeY; j++)
				walkFrames[index++] = f2DRegion[j][i];

        Animation fAnimation = new Animation(0.085f, walkFrames);
		
		return fAnimation;
	}
	
	}
