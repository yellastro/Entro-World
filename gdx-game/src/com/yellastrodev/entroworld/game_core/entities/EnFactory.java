package com.yellastrodev.entroworld.game_core.entities;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.yellastrodev.entroworld.*;
import com.yellastrodev.entroworld.game_core.*;
import com.yellastrodev.entroworld.game_core.components.*;
import com.yellastrodev.entroworld.game_core.entities.mobs.undead.*;

public class EnFactory
{
	Engine mEngine;
	
	public EnFactory(Engine engin)
	{
		mEngine=engin;
	}
	
	public Skelet createSkelet()
	{
		Skelet fsk=new Skelet(mEngine);
		
		return fsk;
	}
	
	public Golem createGolem()
	{
		Golem fgol=new Golem(mEngine);
		
		createMob(fgol);
		//fgol.addComponent(new ProsessStateManager(fgol,,mEngine));
		
		return fgol;
	}
	
	public Imp createImp()
	{
		Imp fChik=new Imp(mEngine);

		createMob(fChik);
		//fChik.addComponent(new ProsessStateManager(fChik,,mEngine));

		return fChik;
	}
	
	
	public Tree createTree()
	{
		Tree ent = new Tree();
		
		ent.addComponent(setNewPos());
		
		ent.addComponent(setNewDisp
			(ent.getTexture()));
		
		return ent;
	}
	
	public EnEntity createMob(EnEntity fEnti)
	{/*
		fEnti.mEngine=mEngine;
		
		StatisticComponent stc=fEnti.getStatistics();
		fEnti.addComponent(stc);
		fEnti.addComponent(setNewPos());
		fEnti.addComponent(setNewDisp
						 (fEnti.getTexture()));
		VelocityComp velcom=new VelocityComp();
		fEnti.addComponent(velcom);

		AnimateComponent aniComp=new AnimateComponent();
		fEnti.addComponent(aniComp);
		
		*/
		return fEnti;
	}
	
	public Chicken createChiken()
	{
		Chicken fChik=new Chicken(mEngine);
		
		createMob(fChik);
		//fChik.addComponent(new ProsessStateManager(fChik,,mEngine));
		
		return fChik;
	}
	
	public Wolf createWolf()
	{
		Wolf fWolf=new Wolf(mEngine);
		
		createMob(fWolf);
		
		//fWolf.addComponent(new ProsessStateManager(fWolf,,mEngine));
		
		return fWolf;
	}
	
	private PositionComp setNewPos()
	{
		PositionComp posComp=new PositionComp(
		MathUtils.random(-2.0f, 400.0f),
		MathUtils.random(-2.0f, 600.0f));//MathUtils.random(-2.0f, 600.0f);
		posComp.rotation=0;
		return posComp;
	}
	
	private DisplayComp setNewDisp(Texture textur)
	{
		DisplayComp discom=new DisplayComp(textur);
		//discom.mSprite.setSize(h,w);
		return discom;
	}
	
	AnimationInitializer animInit=new AnimationInitializer();
	
	private AnimateComponent setNewAnimate(Texture textur)
	{
		AnimateComponent comp =new AnimateComponent();
		
		TextureRegion[][] profTextures=animInit.getRegion(textur,10,12,5);
		
		comp.rightAnimation=animInit.getAnimationFromLevel(profTextures,4,5);
		
		comp.leftAnimation=animInit.getAnimationFromLevel(profTextures,10,5);
	
		return comp;
	}
	/*
	TextureRegion[][] getRegion(Texture res,int coll,int row,int startColl)
	{
		TextureRegion[][] tmp=getTextureArray(res,coll,row);
		//tmp=getTextureArray(tmp[1][0],5,7);
		// #5
		TextureRegion[][] tresultarray=new TextureRegion[row][coll-startColl];
        for (int i = 0; i < row; i++) {
            for (int j = startColl; j < coll; j++) {
				int iji=j-startColl;
				tresultarray[i][iji] = tmp[i][j];
			 }
        }
		return tresultarray;
	}

	Animation getAnimationFromLevel(TextureRegion[][] tmp,int lvl,int size)
	{
		TextureRegion[] walkFrames;
		walkFrames = new TextureRegion[size];

        int index = 0;
        for (int i = 0; i < size; i++) 
            walkFrames[index++] = tmp[lvl][i];
     
        Animation walkAnimation = new Animation(0.085f, walkFrames); // #11
		return walkAnimation;
	}
	
	public Animation getAnimation(Texture textur,int level)
	{
		TextureRegion[] walkFrames;
		walkFrames = new TextureRegion[5];
		TextureRegion[][] tmp=getTextureArray(textur,10,12);
		
        int index = 0;
        for (int i = 5; i < 10; i++) {
            //for (int j = 0; j < 12; j++) {
			walkFrames[index++] = tmp[level][i];
			/*if(index==11)
			 {
			 break;}*/
			// }
       /* }
        Animation walkAnimation = new Animation(0.85f, walkFrames); // #11
		return walkAnimation;
	}
	
	public TextureRegion[][] getTextureArray(Texture MainSheet,int coll,int row)
	{
		TextureRegion[][] tmp = TextureRegion.split(MainSheet, MainSheet.getWidth()/coll, MainSheet.getHeight()/row); // #10
        return tmp;
	}*/
}
