package com.yellastrodev.entroworld.map_generator;

import com.badlogic.gdx.graphics.g2d.*;
import com.yellastrodev.entroworld.static_initializers.*;
import com.yellastrodev.entroworld.game_core.components.*;
import java.util.*;
import com.badlogic.gdx.math.*;

public class SimpleGroundGenerator
{
	public TextureRegion mGround[][];
	int x=100;
	int y=60;
	int fH;
	int fW;
	public List<PositionComp> mTreesPlases = new ArrayList<>();
	
	public SimpleGroundGenerator()
	{
		mGround = new TextureRegion[x][y];
		
		TextureRegion fTxt = TextureInit.getSomeGrass();
		fW = fTxt.getRegionWidth();
		fH = fTxt.getRegionHeight();
		/*
		mGround.setRegionHeight(fH*x);
		mGround.setRegionWidth(fW*y);
		*/
		for(int i=0;i<x;i++)
		{
			for(int j=0;j<y;j++)
			{
				fTxt = TextureInit.getSomeGrass();
				
				mGround[i][j] = fTxt;
				/*.setRegion(fTxt,
					fH*j,fW*i,fH,fW);*/
			}
		}
		GenerateTrees();
	}
	
	private void GenerateTrees()
	{
		for(int i=0;i<7;i++)
		{
			PositionComp qPos;
			boolean qNotOk=false;
			int k=0;
			
			do{
				k++;
				if(k>20)
					return;
				qPos = PositionComp.setNewPos();
				for(PositionComp qqPos:mTreesPlases)
				{
					if(Math.abs(qqPos.oX-qPos.oX)<20 &&
						Math.abs(qqPos.oX-qPos.oX)<20)
					{
						qNotOk=true;
						break;
					}
				}
			}
			while(qNotOk);
			mTreesPlases.add(qPos);
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
