package com.yellastrodev.entroworld.ui.view;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.yellastrodev.entroworld.static_initializers.*;
import com.yellastrodev.entroworld.ui.*;

public class EditorFirstView implements View
{

	private float row_height = 80;

	private EditorScreen mScreen;

	private Stage mStage;

	public EditorFirstView(EditorScreen fScreen)
	{
		mScreen = fScreen;
		
		mStage = new Stage();
		Gdx.input.setInputProcessor(mStage);
		
		Button.ButtonStyle mButStyle = new Button.ButtonStyle();
		mButStyle.up = new TextureRegionDrawable(TextureInit.getUiMidleButtonUpBackground());
		mButStyle.down = new TextureRegionDrawable(TextureInit.getUiMidleButtonDownBackground());


		final Button fButton = new Button(mButStyle);
		fButton.setName("Create");
		fButton.setPosition(50,row_height);
		fButton.setSize(250,row_height);
		fButton.addListener(new com.badlogic.gdx.scenes.scene2d.EventListener(){
				boolean mOk=true;
				@Override
				public boolean handle(Event p1)
				{
					if(mOk)
						openTextureList();
				
					return true;
				}
			});
		mStage.addActor(fButton);
		
		final Button fButton2 = new Button(mButStyle);
		fButton2.setName("Create");
		fButton2.setPosition(350,row_height);
		fButton2.setSize(250,row_height);
		fButton2.addListener(new com.badlogic.gdx.scenes.scene2d.EventListener(){
				boolean mOk=true;
				@Override
				public boolean handle(Event p1)
				{
					if(mOk)
						openEqupList();
					mOk=false;
					return true;
				}
			});
		mStage.addActor(fButton2);
	}
	
	private void openTextureList()
	{
		mScreen.openTextureList();
	}
	
	private void openEqupList()
	{
		mScreen.openEqupList();
	}
	
	@Override
	public void drawn(SpriteBatch fBatch)
	{
		mStage.draw();
		mStage.act();
	}
	
}
