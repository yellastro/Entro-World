package com.yellastrodev.entroworld.game_core.nodes;
import com.yellastrodev.entroworld.game_core.components.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.yellastrodev.entroworld.map_generator.*;
import com.yellastrodev.entroworld.game_core.systems.*;
import com.badlogic.gdx.graphics.*;

public class DisplayNode
{
	public DisplayComp mDisplayComp;
	public PositionOnMapComponent mPositionComp;
	public StatisticComponent mStatsts;
	
	BitmapFont FontRed2 = new BitmapFont();

	public DisplayNode( DisplayComp dsp,PositionOnMapComponent pos,StatisticComponent fStats)
	{
		mDisplayComp 	= dsp;
		mPositionComp 	= pos;
		mStatsts 		= fStats;
	}
	
	public void rendering(Batch fBatch,sysWorldRenderer fMap)
	{
		for(TextureRegion qTexture:mDisplayComp.mSprite)
		{
			float originX = (qTexture.getRegionHeight()/2)*mDisplayComp.mScale;
			float originY = (qTexture.getRegionWidth()/2)*mDisplayComp.mScale;
			fBatch.draw(qTexture,
				mPositionComp.oX-fMap.mCameraPosX-mDisplayComp.mPointX,
				mPositionComp.oY-fMap.mCameraPosY-mDisplayComp.mPointY,
				0,0,
				mDisplayComp.mWidth,
				mDisplayComp.mHeigth,
				1,1,mDisplayComp.mRotate
				
				);
			if(mStatsts!=null)
			{
				FontRed2.setColor(Color.RED); //Красный
				FontRed2.setScale(1);


				String info="";
				//wlfState.getActiveState().getClass().toString().substring(50)

				info=mStatsts.mHP+"/"+mStatsts.mMaxHP+"hp";

				FontRed2.draw(fBatch, info,mPositionComp.oX-fMap.mCameraPosX,
					mPositionComp.oY-fMap.mCameraPosY);
			}
		}
	}
}
