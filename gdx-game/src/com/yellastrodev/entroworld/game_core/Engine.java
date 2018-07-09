package com.yellastrodev.entroworld.game_core;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.yellastrodev.entroworld.*;
import com.yellastrodev.entroworld.game_core.components.*;
import com.yellastrodev.entroworld.game_core.entities.*;
import com.yellastrodev.entroworld.game_core.nodes.*;
import com.yellastrodev.entroworld.game_core.states.*;
import com.yellastrodev.entroworld.game_core.systems.*;
import java.util.*;
import com.badlogic.gdx.graphics.*;
import com.yellastrodev.entroworld.game_core.entities.effects.*;
import com.yellastrodev.entroworld.game_core.states.state_managers.*;
import com.yellastrodev.entroworld.static_initializers.*;
import com.yellastrodev.entroworld.game_core.entities.flowers.*;
import com.yellastrodev.entroworld.ui.*;

public class Engine extends iScreen
{
	public TypedList<iSystem> mSystems = new TypedList<>();
	public TypedList<EnEntity> mEntities = new TypedList<>();
	
	
	public ArrayList mNodes = new ArrayList();
	//public ArrayList<
	MobProcessManager wlfState;
	
	public AnimationInitializer animInit=new AnimationInitializer();
	EnFactory entityFactory = new EnFactory(this);

	private Chicken chic;

	public void removeEntiry()
	{
		// TODO: Implement this method
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		//chic=entityFactory.createChiken();
		//addEntity(chic);
		float fX = Gdx.input.getX();
		float fY = 900-Gdx.input.getY();

		/*
		for(EnEntity fEntity:mEntities)
		{
			if(Math.abs(fEntity.mPositionComp.oX-fX)<80
			   &&Math.abs(fEntity.mPositionComp.oY-fY)<80)
			{
				FireBall fFireBall = new FireBall(this,PositionComp.setNewPos());
				fFireBall.mProcessManager.GetTarget(fEntity);
				addEntity(fFireBall);
				break;
			}
		}
		*/
		((PlayerController)wlfState.mLifeManager).onTap(fX,fY);
		//.onTap(fX,fY);
		//wlfState.mPassiveState.Update(2);
		//wlfState.GetTarget(chic);
		//wlfState.RunThere((PositionComp)chic
		//	.getComponent(PositionComp.class));
		return super.touchDown(screenX, screenY, pointer, button);
	}
	Wolf wlzf;
	
	
	public void init()
	{
		WorldController worldController = new WorldController();

		WorldRenderer worldRenderer = new WorldRenderer(this);
		
		sysAnimations sysAnimate = new sysAnimations();
		
		
		Gdx.input.setInputProcessor(this);
		//sysMotionStatets sysMotState = new sysMotionStatets();
		
		addSystem(worldController,0);
		addSystem(worldRenderer,1);
		addSystem(sysAnimate,1);
		addSystem(new sysProsessStates(),0);
		//addSystem(sysMotState,0);
		//
		//addEntity(entityFactory.createTree());
		wlzf=new Wolf(this);
		
		wlfState=(MobProcessManager)
			wlzf.mProcessManager;
		
		for(PositionComp qPos:worldRenderer.mMap.mTreesPlases)
		{
			TreeOne qTree = new TreeOne(this);
			qTree.mPositionComp.SetPos(qPos);
			addEntity(qTree);
		}
			
		for(int i=0;i<8;i++)
		{
			//addEntity(new TreeOne(this));
			addEntity(new Chicken(this));
		}
		
		addEntity(wlzf);
		addEntity(new Chicken(this));
		//addEntity(new Imp(this));
		//addEntity(new Golem(this));
		
		//addEntity(chic);
		//addEntity(entityFactory.createSkelet());
		
		worldRenderer.sortByLvl();
		//addEntity(entityFactory.createGolem());
	}
	
	public void addEntity(EnEntity Enntity)
	{
		PositionComp posc=null;
		DisplayComp disc=null;
		VelocityComp velc=null;
		AnimateComponent anic=null;
		mEntities.add( Enntity );
		for(Object comp:Enntity.Components)
		{
			if (comp.getClass()==PositionComp.class)
				posc=(PositionComp)comp;
			if (comp.getClass()==DisplayComp.class)
				disc=(DisplayComp)comp;
			if (comp.getClass()==VelocityComp.class)
				velc=(VelocityComp)comp;
			if (comp.getClass()==AnimateComponent.class)
				anic=(AnimateComponent)comp;
				//Enntity.mNodes.add(comp);
			
			/*if (comp.getClass()==MotionState.class)
				((sysMotionStatets)mSystems
				.get(sysMotionStatets.class)).addNode(comp);
		*/}
		if(Enntity.mProcessManager!=null)
		{
			(mSystems.get(sysProsessStates.class))
				.addNode(Enntity.mProcessManager);
		}
		
		if(disc!=null&&posc!=null)
		{
			DisplayNode dNode= new DisplayNode(disc,posc,Enntity.mStatistic);
			
			iSystem ss=mSystems.get(WorldRenderer.class);
			ss.addNode(dNode);
			Enntity.mNodes.add(dNode);
		}
		if(velc!=null&&posc!=null)
		{
			MovedNode dNode= new MovedNode(velc,posc);
			iSystem ss=mSystems.get(WorldController.class);
			ss.addNode(dNode);
			Enntity.mNodes.add(dNode);
		}/*
		if(anic!=null)
		{
			AnimationNode aNode=new AnimationNode(anic,velc,disc);
			iSystem ss=mSystems.get(sysAnimations.class);
			ss.addNode(aNode);
			Enntity.mNodes.add(aNode);
		}*/
		// создание узлов из компонентов сущностей и добавление их в список узлов
		// наблюдение за последующим добавлением и удалением компонентов к 
		// сущности и соответствующие изменения в зависимых узлах
	}
	
	public EnEntity getEntity(Class fType)
	{
		return mEntities.get(fType);
	}
	
	public void removeAnimation(EnEntity fEntity)
	{
		Object ds;
		if((ds=fEntity.mNodes.get(AnimationNode.class))!=null)
		{
			iSystem ss=mSystems.get(sysAnimations.class);
			ss.deleteNode(ds);
		}
	}
	
	public void removeEntity(EnEntity Enntity)
	{
		// удаление узлов, содержащих компоненты сущности
		// и их извлечение из списка узлов
		Object ds;
		if((ds=Enntity.mNodes.get(DisplayNode.class))!=null)
		{
			iSystem ss=mSystems.get(WorldRenderer.class);
			ss.deleteNode(ds);
		}
		if((ds=Enntity.mNodes.get(MovedNode.class))!=null)
		{
			iSystem ss=mSystems.get(WorldController.class);
			ss.deleteNode(ds);
		}
		if((ds=Enntity.mNodes.get(AnimationNode.class))!=null)
		{
			iSystem ss=mSystems.get(sysAnimations.class);
			ss.deleteNode(ds);
		}
		if((ds=Enntity.mProcessManager)!=null)
		{
			iSystem ss=mSystems.get(sysProsessStates.class);
			ss.deleteNode(ds);
		}
		mEntities.remove( Enntity );
	}
	
	
	public void addSystem(iSystem syst, int prior )
	{
		mSystems.add(prior,syst);
		syst.start();
	}
	public void removeSystem(iSystem system )
	{
		system.end();
		mSystems.remove( system );
	}
	public ArrayList getNodeList(Class tip )
	{
		ArrayList nodes  = new ArrayList();
		return nodes;
// create the nodes from the current set of entities 
		// and populate the node list return nodes;
	}
	public void update(float time)
	{
		for (iSystem system:mSystems)
		{ 
			system.update(time );
		}
	}
	
	public void printInfo(Batch batch)
	{
		BitmapFont FontRed2 = new BitmapFont();
        FontRed2.setColor(Color.RED); //Красный
        FontRed2.setScale(2);

        String info="";
		
		if(wlfState.mTargetPoss!=null)
		{
			
			PositionComp fVelc = wlfState.mTargetPoss;
			info =wlfState.mEntity.toString();//+" t="+fVelc.targetPos.oX+"/"+fVelc.targetPos.oY;
			}//wlfState.getActiveState().getClass().toString().substring(50)
		/*StatisticComponent fChStat=(StatisticComponent)
		chic.getComponent(StatisticComponent.class);
		
		info=fChStat.mHP+" of "+fChStat.mMaxHP+"hp";
		*/

		info+="_"+
		Gdx.graphics.getFramesPerSecond();


        FontRed2.draw(batch, info, 10, 100);
		
		BitmapFont FontRed1 = new BitmapFont();
        FontRed1.setColor(Color.GREEN); //Красный
        FontRed1.setScale(1);

        String info2=wlfState.isSearch+""+wlzf.mBeastStatisticks.mHunger+"\n"
		//wlfState.getActiveState().getClass().toString().substring(50)
			;
		for(iStateOfProsess qprs:wlfState.mStatesStack)
			info2=info2+"_"+qprs.getAnimKey();
			

		
  
       FontRed1.draw(batch, info2, 10, 50);
        
    }
	
}
