package com.yellastrodev.entroworld.ui.view;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.yellastrodev.entroworld.*;
import com.yellastrodev.entroworld.game_core.items.*;
import com.yellastrodev.entroworld.static_initializers.*;
import com.yellastrodev.entroworld.ui.*;
import java.util.*;

public class ListEqups extends InputAdapter implements View
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

	public List<EqupView> mItemList;

	public TextureRegion mBackground;
	public TextureRegion mIconBackground;
	public TextureRegion mBody;

	private iScreen mScreen;

	public ListEqups(iScreen fScreen,Runnable fReturn,List<Equp> fList)
	{
		mScreen = fScreen;
		mReturn = fReturn;
		mItemList = new ArrayList<>();
		for(Equp qEq:fList)
			mItemList.add(new EqupView(
				qEq.mIcon,qEq.mName));

		mTopOver = mViewSize-(mItemSize*mItemList.size());

		mBackground = TextureInit.getUiBackground();
		
		Gdx.input.setInputProcessor(this);
		Gdx.input.setCatchBackKey(true);
	}
	
	@Override
	public boolean keyDown(int keycode) 
	{
        mScreen.keyDown(keycode);
  
        return false;
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
		Gdx.input.setInputProcessor(null);
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

	boolean isTouchedThisScreen = false;

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		isTouchedThisScreen = true;
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
		if(isTouchedThisScreen)
		{
			setPosY();
			if(Math.abs(mStartScrlY-mScrollY)<10)
				onPress(Gdx.input.getY());
		}
		isTouchedThisScreen = false;
		return super.touchUp(screenX, screenY, pointer, button);
	}
}
