package com.yellastrodev.entroworld.ui.item_editor;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.yellastrodev.entroworld.game_core.items.*;
import com.yellastrodev.entroworld.static_initializers.*;
import com.yellastrodev.entroworld.ui.view.*;
import java.util.*;
import com.yellastrodev.entroworld.ui.*;

public class EqupMenu extends iScreen implements View
{
	private ListEqups mListViews;

	private List<Equp> mItems;
	
	public EqupMenu()
	{
		Gdx.input.setCatchBackKey(true);
		
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
		
		Gdx.input.setInputProcessor(mListViews);
	}
	
	@Override
	public void update(float dTime)
	{
		// TODO: Implement this method
	}
	

	@Override
	public void drawn(SpriteBatch fBatch)
	{
		mListViews.drawn(fBatch);
	}
	
}
