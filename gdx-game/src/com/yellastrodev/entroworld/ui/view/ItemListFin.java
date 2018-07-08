package com.yellastrodev.entroworld.ui.view;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.yellastrodev.entroworld.game_core.items.*;

public class ItemListFin implements View
{
	public int mX = 0;
	public int mY = 0;


	public Equp mItem;
	private BitmapFont Font= new BitmapFont();

	private ListView mListView;

	public ItemListFin(ListView mListV,TextureRegion fIcon,String fFileAdress)
	{
		mListView = mListV;
	}

	public void drawn(SpriteBatch fBatch,int x,int y)
	{
		fBatch.draw(mListView.mIconBackground,x-5,y-11,90,90);
		fBatch.draw(mListView.mBody,x,y,80,80);
		fBatch.draw(mItem.mIcon,x,y,80,80);
		Font.setColor(Color.GREEN); //Красный
		Font.setScale(2);

		Font.draw(fBatch, mItem.mStats.toString(),x+100,y+70);

	}

	@Override
	public void drawn(SpriteBatch fBatch)
	{
		fBatch.draw(mListView.mIconBackground,mX-5,mY-11,90,90);
		fBatch.draw(mListView.mBody,mX,mY,80,80);
		fBatch.draw(mItem.mIcon,mX,mY,80,80);
		Font.setColor(Color.GREEN); //Красный
		Font.setScale(2);

		Font.draw(fBatch, mItem.mStats.toString(),mX+100,mY+70);

	}
}

