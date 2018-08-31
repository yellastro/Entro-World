package com.yellastrodev.entroworld.ui.view;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.yellastrodev.entroworld.static_initializers.*;
import com.yellastrodev.entroworld.ui.*;
import com.yellastrodev.entroworld.*;
import com.yellastrodev.entroworld.ui.item_editor.*;

public class EditorFirstView extends iScreen
{

	private float row_height = 80;

	private EditorScreen mScreen;
	
	InputMultiplexer multiplexer = new InputMultiplexer();

	private Stage mStage;

	public EditorFirstView(iScreen fScreen,MyGdxGame fGame)
	{
		super(fScreen,fGame);

		int fHeigth = Gdx.graphics.getHeight();
		int fWidth = Gdx.graphics.getWidth();

		mStage = new Stage();

		Gdx.input.setInputProcessor(mStage);

		TextButton.TextButtonStyle mButStyle = new TextButton.TextButtonStyle();
		mButStyle.up = new TextureRegionDrawable(TextureInit.getUiMidleButtonUpBackground());
		mButStyle.down = new TextureRegionDrawable(TextureInit.getUiMidleButtonDownBackground());
		mButStyle.font = new BitmapFont();
		mButStyle.font.scale(2);
		

		final TextButton fTextButton = new TextButton("Texture list",mButStyle);
		fTextButton.setName("Create");
		//fTextButton.setPosition(200,600);
		//fTextButton.set
		fTextButton.setSize(GeneralScreen.sMainTextButtonWidth,GeneralScreen.sMainTextButtonHeigth);
		fTextButton.setCenterPosition(fWidth/2,(fHeigth/2)+(GeneralScreen.sMainTextButtonHeigth));

		fTextButton.addListener(new com.badlogic.gdx.scenes.scene2d.EventListener(){
				boolean mOk=true;
				@Override
				public boolean handle(Event p1)
				{
					if(p1.isHandled())
						openTextureList();
					return false;
				}
			});
		mStage.addActor(fTextButton);

		final TextButton fTextButton2 = new TextButton("Equp list",mButStyle);
		fTextButton2.setName("Create");
		fTextButton2.setSize(GeneralScreen.sMainTextButtonWidth,GeneralScreen.sMainTextButtonHeigth);
		fTextButton2.setCenterPosition(fWidth/2,(fHeigth/2)-(GeneralScreen.sMainTextButtonHeigth));

		fTextButton2.addListener(new com.badlogic.gdx.scenes.scene2d.EventListener(){
				boolean mOk=true;
				@Override
				public boolean handle(Event p1)
				{
					if(p1.isHandled())
						openEqupList();
					mOk=false;
					return true;
				}
			});
		mStage.addActor(fTextButton2);
		
		multiplexer.addProcessor( mStage );
		multiplexer.addProcessor( this ); 
	}

	@Override
	public void resume()
	{
		super.resume();
		
		// Your screen
		Gdx.input.setInputProcessor( multiplexer );
	}
	
	
	
	private void openTextureList()
	{
		new EditorScreen(this,mGame);
	}
	
	private void openEqupList()
	{
		new EqupMenu(this,mGame);
	}
	
	@Override
	public void update(float fTime)
	{
		mStage.draw();
		mStage.act();
	}
	
}
