package com.yellastrodev.entroworld.ui.view;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.yellastrodev.entroworld.*;
import com.yellastrodev.entroworld.static_initializers.*;
import java.util.*;
import com.yellastrodev.entroworld.ui.*;
import com.badlogic.gdx.math.*;

public class ListView extends InputAdapter implements View
{
	private Runnable mReturn;

	public int mScrollCoef;
	public int mPosY;
	private int mTopOver;
	private int mStartScrlY;
	private int mDeltaScrlY;
	private int mScrollY;

	public final int mItemSize = 100;
	public final int mViewSize = 940;

	public List<ListItem> mItemList;

	public TextureRegion mBackground;
	public TextureRegion mIconBackground;
	public TextureRegion mBody;

	public ListView(Runnable fReturn,List<IconAndName> fList)
	{
		mReturn = fReturn;
		mItemList = new ArrayList<>();
		for(int i=0;i<fList.size();i++)
			mItemList.add(new ListItem(this,
				fList.get(i).mIcon,fList.get(i).mFileAdress));

		mTopOver = mViewSize-(mItemSize*mItemList.size());
		
		mBackground = TextureInit.getUiBackground();
		mIconBackground = TextureInit.getUiIconBackground();
		mBody =
			AnimationInitializer.getTextureArray(
			new Texture(
				Gdx.files.internal(
					"lpc/fullsheet/body/man_white.png"))
			,13,21)[2][1];
		
		Gdx.input.setInputProcessor(this);
		
	}

	BitmapFont mFont = new BitmapFont();

	public void drawn(SpriteBatch batch)
	{
		for(int i=0;i<mItemList.size();i++)
			mItemList.get(i).drawn(batch,50,
								(mItemSize*i)+(mPosY+mScrollCoef));

		mFont.draw(batch,mPosY+"/"+mScrollCoef+"/"+mScrollY,480,900);

	}

	public int fI;
	private void onPress(int fY)
	{
		fY = (mViewSize-mPosY)-fY;
		fI=Math.abs(fY/mItemSize);
		mReturn.run();
	}

	public void setPosY()
	{

		mPosY = mPosY + mScrollCoef;
		if(mPosY > 0)
			mPosY = 0;
		else if(mPosY < mTopOver)
			mPosY = mTopOver;

		mScrollCoef = 0;
	}

	public void setScroll(int fScrol)
	{
		mScrollCoef = fScrol;
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		mStartScrlY = Gdx.input.getY();
		return super.touchDown(screenX, screenY, pointer, button);
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		mDeltaScrlY = Gdx.input.getDeltaY();
		mScrollY = Gdx.input.getY();
		setScroll(mStartScrlY-mScrollY);
		return super.touchDragged(screenX, screenY, pointer);
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{

		setPosY();
		if(Math.abs(mStartScrlY-mScrollY)<10)
			onPress(Gdx.input.getY());
		return super.touchUp(screenX, screenY, pointer, button);
	}
}
