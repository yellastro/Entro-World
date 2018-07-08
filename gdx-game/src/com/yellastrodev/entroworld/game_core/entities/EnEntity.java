package com.yellastrodev.entroworld.game_core.entities;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.yellastrodev.entroworld.*;
import com.yellastrodev.entroworld.game_core.components.*;
import com.yellastrodev.entroworld.game_core.*;
import com.yellastrodev.entroworld.game_core.states.*;
import com.yellastrodev.entroworld.game_core.nodes.*;
import com.yellastrodev.entroworld.game_core.states.state_managers.*;

abstract public class EnEntity
{
	public Engine mEngine;
	public iProcessManager mProcessManager;
	public StatisticComponent mStatistic;
	public PositionComp mPositionComp;
	
	public TypedList mNodes=new TypedList();
	public iAnimationNode mAnimateNode;
	
	public TypedList Components=new TypedList();
	
	private static String txtRes = "data/android.jpg";
	private static Texture entitexture;

	public EnEntity(Engine fEngine)
	{
		CoupeOfSheets fStore = initAnim();
		this.initStatistic();
		mEngine=fEngine;
		initComponents(fStore,getSize());
		initProcessManager(fStore,fEngine);
		mProcessManager.inIt(mAnimateNode);
		initLiveCicle();
		addComponent(mProcessManager);
	}
	public abstract float getSize()

	
	public abstract CoupeOfSheets initAnim()
	
	public void Dead()
	{
		mEngine.removeEntity(this);
	}
	
	public abstract StatisticComponent initStatistic()
	
	
	public abstract void initComponents(CoupeOfSheets fStore,float fScale)
	
	public abstract void initProcessManager(CoupeOfSheets fStore,Engine fEngine)
	
	
	public abstract void initLiveCicle()

	public StatisticComponent getStatistics()
	{
		// TODO: Implement this method
		return mStatistic;
	}
	public Texture getTexture()
	{
		if(entitexture==null)
			entitexture =
				new Texture( Gdx.files.internal(txtRes));
		return entitexture;
	}
	
	public void addComponent(Object comp)
	{
		Components.add(comp);
	}
	
	public Object getComponent(Class t)
	{
		for(Object E:Components)
			if(E.getClass()==t)
				return E;
		return null;
	}
	
	public boolean isInstanceOf(Class fClass)
	{
		Class fThis=this.getClass();
		while(fThis!=EnEntity.class)
		{
			if(fThis==fClass)
				return true;
			else
				fThis=fThis.getSuperclass();
		}
		return false;
	}
}
