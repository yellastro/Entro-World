package com.yellastrodev.entroworld.ui.view;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;

public class ListItem implements View
{
	public int mX = 0;
	public int mY = 0;
	

	public TextureRegion mIcon;
	public String mFileAdress;

	private BitmapFont Font= new BitmapFont();

	private String info;
	private ListView mListView;

	public ListItem(ListView mListV,TextureRegion fIcon,String fFileAdress)
	{
		mListView = mListV;
		mIcon = fIcon;
		mFileAdress = fFileAdress;

		info=mFileAdress.substring(mFileAdress.lastIndexOf("/"));
	}

	public void drawn(SpriteBatch fBatch,int x,int y)
	{
		fBatch.draw(mListView.mIconBackground,x-5,y-11,90,90);
		fBatch.draw(mListView.mBody,x,y,80,80);
		fBatch.draw(mIcon,x,y,80,80);
		Font.setColor(Color.GREEN); //Красный
		Font.setScale(2);

		Font.draw(fBatch, info,x+100,y+70);

	}
	
	@Override
	public void drawn(SpriteBatch fBatch)
	{
		fBatch.draw(mListView.mIconBackground,mX-5,mY-11,90,90);
		fBatch.draw(mListView.mBody,mX,mY,80,80);
		fBatch.draw(mIcon,mX,mY,80,80);
		Font.setColor(Color.GREEN); //Красный
		Font.setScale(2);

		Font.draw(fBatch, info,mX+100,mY+70);

	}
}
