package com.yellastrodev.entroworld.game_core.items;
import com.badlogic.gdx.graphics.g2d.*;

public class Item
{
	public TextureRegion mIcon;
	public String mName;
	
	public Item(String fName,TextureRegion fIcon)
	{
		mName = fName;
		mIcon = fIcon;
	}
}
