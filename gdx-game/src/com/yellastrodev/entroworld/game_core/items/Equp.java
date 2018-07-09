package com.yellastrodev.entroworld.game_core.items;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.yellastrodev.entroworld.*;
import com.yellastrodev.entroworld.game_core.components.*;

public class Equp extends Item
{
	public AnimationStore mAnimStore;
	
	public String mEqType;
	
	public EqupStatistic mStats;

	public String mSet;
	
	public static TextureRegion getIcon(String fUri)
	{
		if(fUri.contains("fullsheet"))
			return AnimationInitializer.getTextureArray(
			new Texture(fUri),13,21)[2][0];
		else
			return AnimationInitializer.getTextureArray(
				new Texture(fUri),6,4)[2][0];
	}
	
	public Equp(String fName,String fIcon,String fType,EqupStatistic fStat,String fAdress,String fSet)
	{
		super(fName,getIcon(fIcon));
		mEqType = fType;
		mStats = fStat;
		mSet = fSet;
		//mAnimStore = AnimationInitializer.g
	}
}
