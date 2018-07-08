package com.yellastrodev.entroworld.game_core.components;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import java.util.*;

public class DisplayComp
{
	public List<TextureRegion> mSprite =new ArrayList<>();

	public float mScale;
	
	public float mRotate = 0;
	
	public DisplayComp(Texture txtr)
	{
		
		mSprite.add(new TextureRegion(txtr));
	}
	public DisplayComp(TextureRegion fReg,float fScale)
	{
		mSprite.add(fReg);
		mScale = fScale;
	}
}
