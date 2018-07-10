package com.yellastrodev.entroworld.ui.view;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.yellastrodev.entroworld.*;
import com.yellastrodev.entroworld.static_initializers.*;

public class EqupView implements View
{final public static TextureRegion sIconBackground = TextureInit.getUiIconBackground();
	final public static TextureRegion sBody =
	AnimationInitializer.getTextureArray(
		new Texture(
			Gdx.files.internal(
				"lpc/fullsheet/body/man_white.png"))
		,13,21)[2][1];


	public int mX = 0;
	public int mY = 0;


	public TextureRegion mIcon;
	public String mFileAdress;

	private BitmapFont Font= new BitmapFont();

	private String info;

	public EqupView(TextureRegion fIcon,String fFileAdress)
	{
		mIcon = fIcon;
		mFileAdress = fFileAdress;

		info=fFileAdress;
	}

	public void drawn(SpriteBatch fBatch,int x,int y)
	{
		fBatch.draw(sIconBackground,x-5,y-11,90,90);
		fBatch.draw(sBody,x,y,80,80);
		fBatch.draw(mIcon,x,y,80,80);
		Font.setColor(Color.GREEN); //Красный
		Font.setScale(2);

		Font.draw(fBatch, info,x+100,y+70);

	}

	@Override
	public void drawn(SpriteBatch fBatch)
	{
		fBatch.draw(sIconBackground,mX-5,mY-11,90,90);
		fBatch.draw(sBody,mX,mY,80,80);
		fBatch.draw(mIcon,mX,mY,80,80);
		Font.setColor(Color.GREEN); //Красный
		Font.setScale(2);

		Font.draw(fBatch, info,mX+100,mY+70);

	}
}
