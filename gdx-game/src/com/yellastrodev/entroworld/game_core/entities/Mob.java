package com.yellastrodev.entroworld.game_core.entities;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;
import com.yellastrodev.entroworld.game_core.*;
import com.yellastrodev.entroworld.game_core.components.*;
import com.yellastrodev.entroworld.game_core.nodes.*;
import com.yellastrodev.entroworld.game_core.states.*;
import com.yellastrodev.entroworld.game_core.systems.*;

import java.util.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.yellastrodev.entroworld.game_core.states.state_managers.*;

public abstract class Mob extends EnEntity
{
	public abstract Class getEnemy();
	public abstract Class getFood();
	public abstract Class getExcept();
	
	
	
	public int height;
	public int widht;
	public int mRunSpeed;
	public int mHP;
	public int mAtackPower;
	public int mAtcSpeed;//tipo % of 1per second
	public int mRange;
	public String mType;
	String mFoodType;
	public Class mEnemy;
	public Class mFood;
	public Class mFrends;
	
	public Mob(Engine fcontroller)
	{
		super(fcontroller);
	}

	@Override
	public void initComponents(CoupeOfSheets fStore,float fScale)
	{
		StatisticComponent stc=getStatistics();
		addComponent(stc);
		mPositionComp = PositionComp.setNewPos();
		addComponent(mPositionComp);
		DisplayComp fdispComp=new DisplayComp
					 (fStore.mStores.get(0).mStandAnimation.rightAnimation.getKeyFrame(0,false)
					 ,fScale);
		addComponent(fdispComp);
		VelocityComp velcom=new VelocityComp();
		addComponent(velcom);

		List<TextureRegion> fTextures = new ArrayList<>();
		List<AnimateComponent> fAniCompList = new ArrayList<>();
		for(AnimationStore qSheets:fStore.mStores)
		{
			if(qSheets.mStandAnimation!=null)
			fdispComp.mSprite.add(qSheets.mStandAnimation.rightAnimation.getKeyFrame(0,false));
			
			AnimateComponent aniComp=new AnimateComponent();
			aniComp.mStore = qSheets;
			fAniCompList.add(aniComp);
			addComponent(aniComp);
		}
		
		makeAnimationNode(fAniCompList,velcom,fdispComp);
	}

	@Override
	public void initLiveCicle()
	{
		mProcessManager.setLifeManager(
			new LiveCycleManager((MobProcessManager)mProcessManager,this));
	}
	
	protected void makeAnimationNode(List<AnimateComponent> fAnimComp,VelocityComp fVelocityCom,DisplayComp fDisplComp)
	{
		AnimationNode aNode=new AnimationNode(fAnimComp,fVelocityCom,fDisplComp);
		iSystem ss=mEngine.mSystems.get(sysAnimations.class);
		ss.addNode(aNode);
		mAnimateNode = aNode;
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

	@Override
	public void initProcessManager(CoupeOfSheets fStore, Engine fEngine)
	{
		mProcessManager=new MobProcessManager(
			this,fStore,fEngine);
	}
	
	

	@Override
	public abstract StatisticComponent initStatistic()
	
}
