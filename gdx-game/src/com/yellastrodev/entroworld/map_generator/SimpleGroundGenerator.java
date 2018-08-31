package com.yellastrodev.entroworld.map_generator;

import com.badlogic.gdx.graphics.g2d.*;
import com.yellastrodev.entroworld.static_initializers.*;
import com.yellastrodev.entroworld.game_core.components.*;
import java.util.*;
import com.badlogic.gdx.math.*;
import com.yellastrodev.entroworld.game_core.entities.flowers.*;
import com.yellastrodev.entroworld.game_core.*;

public class SimpleGroundGenerator
{
	public TextureRegion mGround[][];
	static int x=100;
	static int y=60;
	int fH;
	int fW;
	

	static private Engine mEngine;
	
	public static iMap getSimpleMap(Engine fEng)
	{
		mEngine = fEng;
		
		iMap fMap = new iMap(y,x);
		
		TextureRegion fTxt = TextureInit.getSomeGrass();
		fMap.mCellWidth = fTxt.getRegionWidth();
		fMap.mCellHeigth = fTxt.getRegionHeight();
		/*
		mGround.setRegionHeight(fH*x);
		mGround.setRegionWidth(fW*y);
		*/
		for(int i=0;i<x;i++)
		{
			for(int j=0;j<y;j++)
			{
				//fTxt = TextureInit.getSomeGrass();
				
				fMap.mGround[j][i] = fTxt;
				/*.setRegion(fTxt,
					fH*j,fW*i,fH,fW);*/
			}
		}
		GenerateTrees(fMap);
		return fMap;
	}
	
	private static void GenerateTrees(iMap fMap)
	{
		List<PositionOnMapComponent> mTreesPlases = new ArrayList<>();
		boolean qNotOk=false;
		for(int i=0;i<7;i++)
		{
			PositionOnMapComponent qPos;
			
			int k=0;
			
			do{
				qNotOk=false;
				k++;
				if(k>20)
					return;
				qPos = PositionOnMapComponent.setNewPos();
				for(PositionOnMapComponent qqPos:mTreesPlases)
				{
					if(Math.abs(qqPos.oX-qPos.oX)<20 &&
						Math.abs(qqPos.oX-qPos.oX)<20)
					{
						qNotOk=true;
						break;
					}
				}
			} while(qNotOk);
			mTreesPlases.add(qPos);
		}
		for(PositionOnMapComponent qPos:mTreesPlases)
		{
			TreeOne qTree = new TreeOne(mEngine);
			qTree.mPositionComp.SetPos(qPos);
			fMap.mObjects.add(qTree);
		}
		
	}
	
	public void draw(Batch fBatch)
	{
		for(int i=0;i<x;i++)
		{
			
			for(int j=0;j<y;j++)
			{
				fBatch.draw(
				mGround[i][j],i*fW,j*fH);
				/*.setRegion(fTxt,
				 fH*j,fW*i,fH,fW);*/
			}
		}
	}
}
