package com.yellastrodev.entroworld.ui.item_editor;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.yellastrodev.entroworld.*;
import com.yellastrodev.entroworld.game_core.items.*;
import com.yellastrodev.entroworld.static_initializers.*;
import com.yellastrodev.entroworld.ui.*;
import com.yellastrodev.entroworld.ui.view.*;
import java.util.*;

public class EqupMenu extends iScreen implements View
{
	private ListEqups mListViews;

	InputMultiplexer multiplexer = new InputMultiplexer();
	
	private List<Equp> mItems;
	
	public EqupMenu(iScreen fPrevScreen,MyGdxGame fGame)
	{
		super(fPrevScreen,fGame);
		
		
		mItems = ItemLoader.getEqupList();
		
		mListViews = new ListEqups(this,
				new Runnable()
			{
				@Override
				public void run()
				{
					/*openItemCreator(
						mListViews.mItemList.get(mListViews.fI));
				*/}
			},mItems);
			
		multiplexer.addProcessor( mListViews );
		multiplexer.addProcessor( this );
		
		Gdx.input.setInputProcessor(multiplexer);
		
	}
	
	@Override
	public void update(float dTime)
	{
		mBatch.begin();
		mListViews.drawn(mBatch);
		mBatch.end();
	}
	

	@Override
	public void drawn(SpriteBatch fBatch)
	{
		mListViews.drawn(fBatch);
	}
	
}
