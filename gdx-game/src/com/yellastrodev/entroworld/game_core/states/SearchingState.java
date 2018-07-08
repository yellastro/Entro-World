package com.yellastrodev.entroworld.game_core.states;
import com.yellastrodev.entroworld.game_core.*;
import com.yellastrodev.entroworld.game_core.components.*;
import com.yellastrodev.entroworld.game_core.entities.*;
import java.util.*;
import com.yellastrodev.entroworld.game_core.states.state_managers.*;

public class SearchingState implements iStateOfProsess
{

	@Override
	public String getAnimKey()
	{return null;}
	
	
	Engine mEngine;
	iProcessManager mManager;
	StatisticComponent mStats;
	PositionComp mCurrPoss;
	
	EnEntity fCloserEntt;
	
	SearchFilter mFilter;
	
	public SearchingState(iProcessManager fProsM,Engine fEng,StatisticComponent fStat
			,PositionComp fPoss,SearchFilter fFilter)
	{
		mManager=fProsM;
		mEngine=fEng;
		mStats=fStat;
		mCurrPoss=fPoss;
		mFilter=fFilter;
	}

	@Override
	public void Pause()
	{
		// TODO: Implement this method
	}

	@Override
	public void Start()
	{
		Update(1);
	}

	@Override
	public void Update(float deltTime)
	{
		Class fTypeOfFood=mFilter.target;
		Class fExcept=mFilter.except;

		ArrayList<EnEntity> fentts=mEngine.mEntities;
		ArrayList<EnEntity> fEnemies=new ArrayList<>();

		for(EnEntity fEnt:fentts)
		{
			if(fEnt.isInstanceOf(fTypeOfFood)
			   &&!fEnt.isInstanceOf(fExcept))
				fEnemies.add(fEnt);
		}
		
		if(!fEnemies.isEmpty())
		{
			float fCloserRange=800;
			for(EnEntity qEnty:fEnemies)
			{
				PositionComp qEnmPos = (PositionComp)qEnty
					.getComponent(PositionComp.class);

				float rangeX=qEnmPos.oX-mCurrPoss.oX;
				float rangeY=qEnmPos.oY-mCurrPoss.oY;


				float fComRange=Math.abs(rangeX)-Math.abs(rangeY);

				if(Math.abs(rangeX)<fCloserRange&&
				   Math.abs(rangeY)<fCloserRange)
				{
					fCloserEntt=qEnty;

					Fin();
					return;
				}
			}
			
		}
		return;
		
		/*
		float fCloserRange=400;
		for(EnEntity qEnty:fEnemies)
		{
			PositionComp qEnmPos = (PositionComp)qEnty
				.getComponent(PositionComp.class);

			float rangeX=qEnmPos.oX-mCurrPoss.oX;
			float rangeY=qEnmPos.oY-mCurrPoss.oY;
			
			

			if(Math.abs(rangeX)<200&&
			   Math.abs(rangeY)<200)
			{
				fCloserEntt=qEnty;
				
				Fin();
				break;
			}
		}*/
		
	}

	@Override
	public void Break()
	{
		// TODO: Implement this method
	}

	@Override
	public void Fin()
	{
		//mManager.isSearch=false;
		//условие трусости
	//	if(fCloserEntt.mStatistic.mHP<mStats.mHP)
			mManager.GetTarget(fCloserEntt);
		/*else 
			mManager.runAway();*/
		
	}
	
}
