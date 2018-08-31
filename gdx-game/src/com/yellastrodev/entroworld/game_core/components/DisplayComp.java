package com.yellastrodev.entroworld.game_core.components;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import java.util.*;

public class DisplayComp
{
	public List<TextureRegion> mSprite =new ArrayList<>();

	public float mScale;
	
	public float mHeigth;
	public float mWidth;
	
	public int mPointX;
	public int mPointY;
	
	public float mRotate = 0;
	
	public DisplayComp(Texture txtr)
	{
		
		mSprite.add(new TextureRegion(txtr));
	}
	public DisplayComp(TextureRegion fReg,float fScale)
	{
		mSprite.add(fReg);
		mScale = fScale;
		mHeigth = (fReg.getRegionHeight()*mScale);
		mWidth = (fReg.getRegionWidth()*mScale);
		mPointX =(int) mWidth/2;
		mPointY = (int) mHeigth/5;
	}
}
