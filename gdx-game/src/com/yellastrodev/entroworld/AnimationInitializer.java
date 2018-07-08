package com.yellastrodev.entroworld;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.yellastrodev.entroworld.game_core.components.*;

public class AnimationInitializer
{
	
	static AnimationStore mImpAnimation=null;
	static AnimationStore mChickAnimation=null;
	static AnimationStore mGolemAnimation=null;
	static AnimationStore mSkeletAnimation=null;

	private static AnimationStore mSwordAnimation;
	public static AnimationStore getHeadChainArmor()
	{/*
		AnimationStore animStore=new AnimationStore();
		TextureRegion[][] walkTextures=getRegion(new Texture(Gdx.files.internal("lpc/walkcycle/HEAD_chain_armor_helmet.png"))
												 ,9,4,0);
		AnimateComponent run=getLpcComponent(walkTextures,9);

		Texture ftxt=
		new Texture(Gdx.files.internal("lpc/walkcycle/HEAD_chain_armor_helmet.png"));
		

		int flength=6;

		//Texture ftexture=new Texture(Gdx.files.internal("lpc/slash/BODY_skeleton.png"));


		walkTextures=getRegion(new Texture(Gdx.files.internal("lpc/slash/HEAD_chain_armor_helmet.png"))
							   ,flength,4,0);

		AnimateComponent atack=getLpcComponent(walkTextures,flength);


		animStore.mRunAnimation=run;
		animStore.mStandAnimation=run;
		animStore.mAtackAnimation=atack;


		
		return animStore;*/
		
		return getLPCFullCicleAnimFromPieaces("HEAD_chain_armor_helmet");
	}
	
	public static AnimationStore getLPCFullCicleAnimFromPieaces(String fRes)
	{
		AnimationStore animStore=new AnimationStore();
		
		animStore.mRunAnimation = getWalkLPCAnimation(fRes);
		animStore.mStandAnimation = getStandLPCAnimation(fRes);
		animStore.mAtackAnimation=getAtack6xAnimation(fRes);

		//mSkeletAnimation=animStore;
		return animStore;
	}
	
	
	public static AnimationStore getSkeletAnimStore()
	{
		if(!(mSkeletAnimation==null))
			return mSkeletAnimation;

		String fRes = "BODY_skeleton";
		AnimationStore animStore=new AnimationStore();
		TextureRegion[][] walkTextures=getRegion(new Texture(Gdx.files.internal("lpc/walkcycle/BODY_skeleton.png"))
			,9,4,0);
		AnimateComponent run=getWalkLPCAnimation(fRes);
		
		
		int flength=6;
		
		//Texture ftexture=new Texture(Gdx.files.internal("lpc/slash/BODY_skeleton.png"));
		
		/*
		walkTextures=getRegion(new Texture(Gdx.files.internal("lpc/slash/BODY_skeleton.png"))
			,flength,4,0);
		*/
		animStore.mRunAnimation=run;
		animStore.mStandAnimation=getStandLPCAnimation(fRes);
		animStore.mAtackAnimation=getAtack6xAnimation(fRes);
		
		
		mSkeletAnimation=animStore;
		return animStore;
	}
	
	public static AnimationStore getSwordAnimation()
	{
		if(!(mSwordAnimation==null))
			return mSwordAnimation;

		AnimationStore fanimStore=new AnimationStore();
		fanimStore.mAtackAnimation=getAtack6xAnimation("WEAPON_rapier");


		mSwordAnimation=fanimStore;
		return fanimStore;
	}
	
	public static AnimateComponent getSingleLPCAnimation(String fResurseFile,int fCount,double fFDuration,boolean isCycler)
	{
		TextureRegion[][] fAtackTextures;

		fAtackTextures=getRegion(new Texture(Gdx.files.internal("lpc/"+fResurseFile+".png"))
								 ,fCount,4,0);
		AnimateComponent fComponent = getLpcComponent(fAtackTextures,fCount);
		fComponent.isCycler=isCycler;
		fComponent.mAnimCount=fCount;
		fComponent.mFrameDuration=fFDuration;
		return fComponent;
	}
	
	public static AnimateComponent getStandLPCAnimation(String fResurseFile)
	{
		TextureRegion[][] fAtackTextures;
		int flength=1;

		return getSingleLPCAnimation(("walkcycle/"+fResurseFile),
									 flength,1,true);
	}
	
	public static AnimateComponent getWalkLPCAnimation(String fResurseFile)
	{
		TextureRegion[][] fAtackTextures;
		int flength=9;

		return getSingleLPCAnimation(("walkcycle/"+fResurseFile),
			flength,0.08,true);
	}
	
	public static AnimateComponent getAtack6xAnimation(String fResurseFile)
	{
		//TextureRegion[][] fAtackTextures;
		int flength=6;

		return getSingleLPCAnimation(("slash/"+fResurseFile),
			flength,0.08,false);
		
		/* ---это работает---
		fAtackTextures=getRegion(new Texture(Gdx.files.internal("lpc/slash/"+fResurseFile+".png"))
			,flength,4,0);
		AnimateComponent fComponent = getLpcComponent(fAtackTextures,flength);
		fComponent.isntCycler=true;
		fComponent.mAnimCount=flength;
		fComponent.mFrameDuration=1/5;
		return fComponent;*/
	}
	
	public static AnimateComponent getLpcComponent(TextureRegion[][] fTextures,int fALeng)
	{
		AnimateComponent fComp=new AnimateComponent();
		fComp.topAnimation=getAnimationFromLevel(fTextures,0,fALeng);
		fComp.leftAnimation=getAnimationFromLevel(fTextures,1,fALeng);
		fComp.botAnimation=getAnimationFromLevel(fTextures,2,fALeng);
		fComp.rightAnimation=getAnimationFromLevel(fTextures,3,fALeng);
		
		return fComp;
	}
	
	public static AnimationStore getGolemAnimStore()
	{
		if(!(mGolemAnimation==null))
			return mGolemAnimation;

		int fLeng = 7;
			
		AnimationStore animStore=new AnimationStore();
		TextureRegion[][] profTextures=getRegion(new Texture(Gdx.files.internal("mobs/golem/golem-walk.png"))
												 ,fLeng,4,0);

		AnimateComponent run=getLpcComponent(profTextures,fLeng);
		run.mFrameDuration = 0.2;
		run.mAnimCount = fLeng;
		run.isCycler = true;
		
		AnimateComponent stand=getLpcComponent(profTextures,1);
		stand.mFrameDuration = 0.4;
		stand.mAnimCount = 1;
		stand.isCycler = true;
		/*
		run.topAnimation=getAnimationFromLevel(profTextures,0,7);
		run.leftAnimation=getAnimationFromLevel(profTextures,1,7);
		run.botAnimation=getAnimationFromLevel(profTextures,2,7);
		run.rightAnimation=getAnimationFromLevel(profTextures,3,7);
		*/
		TextureRegion[][] eatSprite=getRegion(new Texture(Gdx.files.internal("mobs/golem/golem-atk.png"))
											  ,7,4,0);
		AnimateComponent attack=getLpcComponent(eatSprite,fLeng);
		attack.mFrameDuration = 0.2;
		attack.mAnimCount = fLeng;
		attack.isCycler = false;
		/*
		attack.topAnimation=getAnimationFromLevel(eatSprite,0,7);
		attack.leftAnimation=getAnimationFromLevel(eatSprite,1,7);
		attack.botAnimation=getAnimationFromLevel(eatSprite,2,7);
		attack.rightAnimation=getAnimationFromLevel(eatSprite,3,7);

		*/

		animStore.mRunAnimation=run;
		animStore.mStandAnimation=stand;
		animStore.mAtackAnimation=attack;
		return animStore;
	}
	
	public static AnimationStore getImpAnimStore()
	{
		if(!(mImpAnimation==null))
			return mImpAnimation;

		int fLeng = 4;
		AnimationStore animStore=new AnimationStore();
		TextureRegion[][] profTextures=getRegion(new Texture(Gdx.files.internal("mobs/imp/walk_vanilla.png"))
												 ,4,4,0);

		AnimateComponent run=getLpcComponent(profTextures,fLeng);
		run.mFrameDuration = 0.1;
		run.mAnimCount = fLeng;
		run.isCycler = true;
		
		TextureRegion[][] StayTextures=getRegion(new Texture(Gdx.files.internal("mobs/imp/stay_vanilla.png"))
												 ,4,4,0);
		
		AnimateComponent stand=getLpcComponent(StayTextures,4);
		stand.mFrameDuration = 0.2;
		stand.mAnimCount = 4;
		stand.isCycler = true;
		/*
		run.topAnimation=getAnimationFromLevel(profTextures,0,4);
		run.leftAnimation=getAnimationFromLevel(profTextures,1,4);
		run.botAnimation=getAnimationFromLevel(profTextures,2,4);
		run.rightAnimation=getAnimationFromLevel(profTextures,3,4);
*/
		TextureRegion[][] eatSprite=getRegion(new Texture(Gdx.files.internal("mobs/imp/attack_vanilla.png"))
											  ,4,4,0);
		AnimateComponent attack=getLpcComponent(eatSprite,fLeng);
		attack.mFrameDuration = 0.1;
		attack.mAnimCount = fLeng;
		attack.isCycler = false;
		/*
		attack.topAnimation=getAnimationFromLevel(eatSprite,0,4);
		attack.leftAnimation=getAnimationFromLevel(eatSprite,1,4);
		attack.botAnimation=getAnimationFromLevel(eatSprite,2,4);
		attack.rightAnimation=getAnimationFromLevel(eatSprite,3,4);
*/

		animStore.mRunAnimation=run;
		animStore.mStandAnimation=stand;
		animStore.mAtackAnimation=attack;
		return animStore;
	}
	
	public static AnimationStore getChickenAnimStore()
	{
		if(!(mChickAnimation==null))
			return mChickAnimation;
		int fLeng = 4;
		AnimationStore animStore=new AnimationStore();
		TextureRegion[][] profTextures=getRegion(new Texture(Gdx.files.internal("chicken_walk.png"))
												 ,4,4,0);

		AnimateComponent run=getLpcComponent(profTextures,fLeng);
		run.mFrameDuration = 0.4;
		run.mAnimCount = fLeng;
		run.isCycler = true;
		/*
		run.rightAnimation=getAnimationFromLevel(profTextures,3,4);
		run.leftAnimation=getAnimationFromLevel(profTextures,1,4);
		run.topAnimation=getAnimationFromLevel(profTextures,0,4);
		run.botAnimation=getAnimationFromLevel(profTextures,2,4);
		*/
		TextureRegion[][] eatSprite=getRegion(new Texture(Gdx.files.internal("chicken_eat.png"))
												 ,4,4,0);
		AnimateComponent stay=getLpcComponent(profTextures,2);
		stay.mFrameDuration = 0.4;
		stay.mAnimCount = fLeng;
		stay.isCycler = true;
		/*
		stay.rightAnimation=getAnimationFromLevel(eatSprite,3,2);
		stay.leftAnimation=getAnimationFromLevel(eatSprite,1,2);
		stay.topAnimation=getAnimationFromLevel(eatSprite,0,2);
		stay.botAnimation=getAnimationFromLevel(eatSprite,2,2);
		*/
		AnimateComponent eat=getLpcComponent(profTextures,fLeng);
		eat.mFrameDuration = 0.4;
		eat.mAnimCount = fLeng;
		eat.isCycler = false;
		
		/*
		eat.rightAnimation=getAnimationFromLevel(eatSprite,3,4);
		eat.leftAnimation=getAnimationFromLevel(eatSprite,1,4);
		eat.topAnimation=getAnimationFromLevel(eatSprite,0,4);
		eat.botAnimation=getAnimationFromLevel(eatSprite,2,4);
		*/

		animStore.mRunAnimation=run;
		animStore.mStandAnimation=stay;
		animStore.mAtackAnimation=eat;
		return animStore;
	}
	
	public AnimationStore getRabbitAnimStore()
	{
		AnimationStore animStore=new AnimationStore();
		TextureRegion[][] profTextures=getRegion(new Texture(Gdx.files.internal("bunnysheet5.png"))
			,8,8,0);
		
		AnimateComponent run=new AnimateComponent();
		run.rightAnimation=getAnimationFromLevel(profTextures,5,4);
		run.leftAnimation=getAnimationFromLevel(profTextures,7,4);
		run.topAnimation=getAnimationFromLevel(profTextures,3,4);
		run.botAnimation=getAnimationFromLevel(profTextures,1,4);
		
		animStore.mRunAnimation=run;
		animStore.mStandAnimation=run;
		return animStore;
	}
	
	public static AnimationStore getWolfAnimationStore()
	{
		AnimationStore animStore=new AnimationStore();
		TextureRegion[][] profTextures=getRegion(new Texture(Gdx.files.internal("wolfsheet_browngrey.png")),10,12,5);
		TextureRegion[][] anfasTextures=getRegion(new Texture(Gdx.files.internal("wolfsheet_browngrey.png")),20,6,0);

		AnimateComponent run=new AnimateComponent();
		run.rightAnimation=getAnimationFromLevel(profTextures,4,5);
		run.leftAnimation=getAnimationFromLevel(profTextures,10,5);
		run.topAnimation=getAnimationFromLevel(anfasTextures,3,5,5);
		run.botAnimation=getAnimationFromLevel(anfasTextures,3,5);
		run.mAnimCount = 5;
		run.mFrameDuration = 0.08;
		run.isCycler = true;
		animStore.mRunAnimation=run;
		
		AnimateComponent rest=new AnimateComponent();
		rest.rightAnimation=getAnimationFromLevel(profTextures,2,2);
		rest.leftAnimation=getAnimationFromLevel(profTextures,6,2);
		rest.topAnimation=getAnimationFromLevel(anfasTextures,1,2,5);
		rest.botAnimation=getAnimationFromLevel(anfasTextures,1,2);
		
		rest.mAnimCount = 2;
		rest.mFrameDuration = 0.6;
		rest.isCycler = true;
		
		animStore.mStandAnimation=rest;
		
		AnimateComponent atack=new AnimateComponent();
		atack.rightAnimation=getAnimationFromLevel(profTextures,5,5);
		atack.leftAnimation=getAnimationFromLevel(profTextures,11,5);
		atack.topAnimation=getAnimationFromLevel(anfasTextures,4,5,5);
		atack.botAnimation=getAnimationFromLevel(anfasTextures,4,5);

		atack.mAnimCount = 5;
		atack.mFrameDuration = 0.08;
		atack.isCycler = false;
		animStore.mAtackAnimation=atack;
		
		return animStore;
	}
	
	public static TextureRegion[][] getRegion(Texture res,int coll,int row,int startColl)
	{
		TextureRegion[][] tmp=getTextureArray(res,coll,row);
		TextureRegion[][] tresultarray=
			new TextureRegion[row][coll-startColl];
		
        for (int i = 0; i < row; i++) {
            for (int j = startColl; j < coll; j++) {
				int iji=j-startColl;
				tresultarray[i][iji] = tmp[i][j];
			}
        }
		return tresultarray;
	}
	
	public static Animation getAnimationFromLevel(TextureRegion[][] tmp,int lvl,int size,int start)
	{
		TextureRegion[] walkFrames;
		walkFrames = new TextureRegion[size];

        int index = 0;
        for (int i = 0; i < size; i++) 
            walkFrames[index++] = tmp[lvl][i+start];

        Animation walkAnimation = new Animation(0.085f, walkFrames); // #11
		return walkAnimation;
	}

	public static Animation getAnimationFromLevel(TextureRegion[][] tmp,int lvl,int size)
	{
		TextureRegion[] walkFrames;
		walkFrames = new TextureRegion[size];

        int index = 0;
        for (int i = 0; i < size; i++) 
            walkFrames[index++] = tmp[lvl][i];

        Animation walkAnimation = new Animation(0.085f, walkFrames); // #11
		return walkAnimation;
	}

	public static Animation getAnimation(Texture textur,int level)
	{
		TextureRegion[] walkFrames;
		walkFrames = new TextureRegion[5];
		TextureRegion[][] tmp=getTextureArray(textur,10,12);

        int index = 0;
        for (int i = 5; i < 10; i++) {
			walkFrames[index++] = tmp[level][i];
        }
        Animation walkAnimation = new Animation(0.85f, walkFrames); // #11
		return walkAnimation;
	}

	public static TextureRegion[][] getTextureArray(Texture MainSheet,int coll,int row)
	{
		TextureRegion[][] tmp = TextureRegion.split(MainSheet, MainSheet.getWidth()/coll, MainSheet.getHeight()/row); // #10
        return tmp;
	}
}
