package com.yellastrodev.entroworld.static_initializers;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.yellastrodev.entroworld.*;
import com.badlogic.gdx.graphics.g2d.*;
import org.apache.commons.codec.net.*;
import com.badlogic.gdx.math.*;

public class TextureInit
{
	private static TextureRegion[][] mGrassArray;
	private static TextureRegion[][] mTreeArray;

	private static Texture mUiTextureSprite = null;
	
	public static TextureRegion getUiVertBackground()
	{
		int fMaxColl = 17;
		int fMaxRow = 10;

		int fStartColl = 2;
		int fStartRow = 2;

		int fColl = 2;
		int fRow = 1;

		if(mUiTextureSprite==null)
		 	mUiTextureSprite = new Texture(Gdx.files.internal("ui/scrollsandblocks.png"));

		return getSpriteRegion(mUiTextureSprite,
							   fMaxColl,fMaxRow,fStartColl,fStartRow,fColl,fRow);
	}
	
	public static TextureRegion getUiMidleButtonDownBackground()
	{
		int fMaxColl = 17;
		int fMaxRow = 10;

		int fStartColl = 2;
		int fStartRow = 2;

		int fColl = 2;
		int fRow = 1;

		if(mUiTextureSprite==null)
		 	mUiTextureSprite = new Texture(Gdx.files.internal("ui/scrollsandblocks.png"));

		return getSpriteRegion(mUiTextureSprite,
							   fMaxColl,fMaxRow,fStartColl,fStartRow,fColl,fRow);
	}
	
	public static TextureRegion getUiMidleButtonUpBackground()
	{
		int fMaxColl = 17;
		int fMaxRow = 10;

		int fStartColl = 2;
		int fStartRow = 1;

		int fColl = 2;
		int fRow = 1;

		if(mUiTextureSprite==null)
		 	mUiTextureSprite = new Texture(Gdx.files.internal("ui/scrollsandblocks.png"));

		return getSpriteRegion(mUiTextureSprite,
							   fMaxColl,fMaxRow,fStartColl,fStartRow,fColl,fRow);
	}
	
	public static TextureRegion getUiLongItemBackground()
	{
		int fMaxColl = 17;
		int fMaxRow = 10;

		int fStartColl = 1;
		int fStartRow = 3;

		int fColl = 3;
		int fRow = 1;

		if(mUiTextureSprite==null)
		 	mUiTextureSprite = new Texture(Gdx.files.internal("ui/scrollsandblocks.png"));

		return getSpriteRegion(mUiTextureSprite,
							   fMaxColl,fMaxRow,fStartColl,fStartRow,fColl,fRow);
	}
	
	public static TextureRegion getUiIconBackground()
	{
		int fMaxColl = 17;
		int fMaxRow = 10;
		
		int fStartColl = 1;
		int fStartRow = 1;

		int fColl = 1;
		int fRow = 1;
		
		if(mUiTextureSprite==null)
		 	mUiTextureSprite = new Texture(Gdx.files.internal("ui/scrollsandblocks.png"));
		
		return getSpriteRegion(mUiTextureSprite,
							   fMaxColl,fMaxRow,fStartColl,fStartRow,fColl,fRow);
	}
	
	public static TextureRegion getUiBackground()
	{
		int fStartColl = 8;
		int fStartRow = 8;
		
		int fColl = 3;
		int fRow = 3;
		if(mUiTextureSprite==null)
		 	mUiTextureSprite = new Texture(Gdx.files.internal("ui/scrollsandblocks.png"));
/*
		TextureRegion[][] mUiBackGround =  AnimationInitializer.getTextureArray
		(mUiTextureSprite,10, 17);*/
		
	int fHeigth = mUiTextureSprite.getHeight()/10;
		//mUiBackGround[1][1].getRegionHeight();
	int fWidth = mUiTextureSprite.getWidth()/17;
		//mUiBackGround[1][1].getRegionWidth();

	TextureRegion fFinTexture = new TextureRegion();
	fFinTexture.setTexture(mUiTextureSprite);
	int x = (fHeigth*fStartColl)-fHeigth;
	int y = (fWidth*fStartRow)-fWidth;
/*
	for(int i=0;i<3;i++)
	for(int j=0;j<3;j++)
	{*/
		fFinTexture.setRegion(
			x,y,fWidth*fColl,fHeigth*fRow);
	
		
		return fFinTexture;
	}
	
	private static TextureRegion getSpriteRegion(Texture fTexture,
		int fMaxColl,int fMaxRow,int fStartColl,int fStartRow,int fColl,int fRow)
	{
		int fHeigth = mUiTextureSprite.getHeight()/fMaxRow;
		//mUiBackGround[1][1].getRegionHeight();
		int fWidth = mUiTextureSprite.getWidth()/fMaxColl;
		//mUiBackGround[1][1].getRegionWidth();

		TextureRegion fFinTexture = new TextureRegion();
		fFinTexture.setTexture(fTexture);
		int x = (fHeigth*fStartRow)-fHeigth;
		int y = (fWidth*fStartColl)-fWidth;
		/*
		 for(int i=0;i<3;i++)
		 for(int j=0;j<3;j++)
		 {*/
		fFinTexture.setRegion(
			y,x,fWidth*fColl,fHeigth*fRow);


		return fFinTexture;
	}
	
	public static TextureRegion getSomeTree()
	{
		if(mTreeArray==null)
		{
			mTreeArray =  AnimationInitializer.getTextureArray
			(new Texture(Gdx.files.internal("textures/green_trees.png")),
			 2,1);
		}
		return mTreeArray[0][MathUtils.random(1)];
	}
	
	public static TextureRegion getSomeGrass()
	{
		TextureRegion[][] fArray = getGroundMatrixTextr();
		int x = MathUtils.random(20);
		if(x>7)
			x=2;
		else
			if(x>2)
				x=1;
			else
				x=0;
		TextureRegion fText = fArray[5][x];
		return fText;
	}
	
	public static TextureRegion[][] getGroundMatrixTextr()
	{
		if(mGrassArray==null)
			return (mGrassArray=
			AnimationInitializer.getTextureArray
			(new Texture(Gdx.files.internal("textures/grass.png")),
			3,6));
		else
			return mGrassArray;
	}
}
