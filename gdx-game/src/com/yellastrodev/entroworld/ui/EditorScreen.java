package com.yellastrodev.entroworld.ui;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.*;
import com.yellastrodev.entroworld.*;
import com.yellastrodev.entroworld.static_initializers.*;
import java.io.*;
import com.badlogic.gdx.files.*;
import java.util.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.yellastrodev.entroworld.ui.EditorScreen.*;
import com.yellastrodev.entroworld.ui.view.*;
import com.yellastrodev.entroworld.ui.item_editor.*;

public class EditorScreen extends iScreen implements Disposable
{

	private SpriteBatch batch;

	private TextureRegion mBackground;

	private OrthographicCamera camera;

	private TextureRegion mIconBackground;
	
	private TextureRegion mBody;

	private ArrayList<IconAndName> mItemss;

	private ListView mListViews;
	
	private List<View> mViews = new ArrayList<>();

	private EditorFirstView mEditorFirstScreen;

	public EditorScreen(iScreen fPrevScreen,MyGdxGame fGame)
	{
		super(fPrevScreen,fGame);
		mBackground = TextureInit.getUiBackground();
		
		mIconBackground = TextureInit.getUiIconBackground();
		
		//Gdx.input.setInputProcessor(this);
		
		batch = new SpriteBatch();
		
		camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
		camera.position.set(0, 0, 0);
		camera.update();
		
		//mEditorFirstScreen = new EditorFirstView(this);
		
		mBody =
		AnimationInitializer.getTextureArray(
			new Texture(
				Gdx.files.internal(
				"lpc/fullsheet/body/man_white.png"))
			,13,21)[2][1];
		
		getTextureList();
		
		openTextureList();
			
		
			
		//mViews.add(mEditorFirstScreen);
	}

	

	public void openTextureList()
	{
		mListViews = new ListView(this,new Runnable()
			{
				@Override
				public void run()
				{
					openItemCreator(
						mListViews.mItemList.get(mListViews.fI));
				}
			},mItemss);
		mViews.clear();
		mViews.add(mListViews);
		Gdx.input.setInputProcessor(mListViews);
		
	}

	@Override
	public void resume()
	{
		super.resume();
		Gdx.input.setInputProcessor(mListViews);
	}
	
	


	@Override
	public void dispose()
	{
		// TODO: Implement this method
	}
	
	
	public void update(float dTime)
	{
		batch.begin();
		batch.draw(mBackground,0,0,540,950);
		
		for( View qView : mViews)
			qView.drawn(batch);
		
		batch.end();
	}
	
	private void getTextureList()
	{
		FileHandle fFile = 
			Gdx.files.internal("lpc/fullsheet/equp");
			
		List<FileHandle> fFileList = new ArrayList<>();
		getListFromAllDir(fFileList,fFile);
		
		mItemss = new ArrayList<>();
		
		for(FileHandle qFile:fFileList)
		{
			
			mItemss.add(new IconAndName(
				AnimationInitializer.getTextureArray(
					new Texture(qFile),13,21)[2][0],
				qFile.toString()));
		}
		mItemss.addAll(
			getListFromPieceOfSheet());
	}
	
	private List<IconAndName> getListFromPieceOfSheet()
	{
		FileHandle fFile = 
			Gdx.files.internal("lpc/equp/slash");

		
		FileHandle[] fFileList = fFile.list();
		
		List<IconAndName> fIconAndNames = new ArrayList<>();
		
		for(FileHandle qFile:fFileList)
		{
			fIconAndNames.add(new IconAndName(
						   AnimationInitializer.getTextureArray(
							   new Texture(qFile),6,4)[2][0],
						   qFile.toString()));
		}
		
		return fIconAndNames;
	}
	
	private void getListFromAllDir(List<FileHandle> fFileList,FileHandle fFile)
	{
		for(FileHandle qFile:fFile.list())
		{
			if(qFile.name().endsWith(".png"))
				fFileList.add(qFile);
			else if(qFile.isDirectory())
					getListFromAllDir(fFileList,qFile);
		}
	}
	
	
	
	public void openItemCreator(ListItem fItem)
	{
		//mViews.remove(mListViews);
		//Gdx.input.setInputProcessor(null);
		new ItemCreateScreen(fItem,this,mGame);
	}
}
