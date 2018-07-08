package com.yellastrodev.entroworld.game_core.nodes;
import com.yellastrodev.entroworld.game_core.components.*;
import com.badlogic.gdx.graphics.g2d.*;

public class DisplayNode
{
	public DisplayComp mDisplayComp;
	public PositionComp mPositionComp;
	public StatisticComponent mStatsts;

	public DisplayNode( DisplayComp dsp,PositionComp pos,StatisticComponent fStats)
	{
		mDisplayComp 	= dsp;
		mPositionComp 	= pos;
		mStatsts 		= fStats;
	}
	
	public void rendering(Batch fBatch)
	{
		for(TextureRegion qTexture:mDisplayComp.mSprite)
		{
			float originX = (qTexture.getRegionHeight()/2)*mDisplayComp.mScale;
			float originY = (qTexture.getRegionWidth()/2)*mDisplayComp.mScale;
			fBatch.draw(qTexture,
				mPositionComp.oX,mPositionComp.oY,
				0,0,
				qTexture.getRegionWidth()*mDisplayComp.mScale,
				qTexture.getRegionHeight()*mDisplayComp.mScale,
				1,1,mDisplayComp.mRotate
				
				);
		}
	}
}
