package com.yellastrodev.entroworld.game_core.entities;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.yellastrodev.entroworld.game_core.components.*;
import com.yellastrodev.entroworld.game_core.*;


public class Tree extends EnEntity
{

	@Override
	public void initLiveCicle()
	{
		// TODO: Implement this method
	}


	@Override
	public void initProcessManager(CoupeOfSheets fStore, Engine fEngine)
	{
		// TODO: Implement this method
	}


	@Override
	public float getSize()
	{
		return 1;
	}

	


	

	@Override
	public CoupeOfSheets initAnim()
	{
		// TODO: Implement this method
		return null;
	}

	@Override
	public void initComponents(CoupeOfSheets fStore,float fScale)
	{
		// TODO: Implement this method
	}


	@Override
	public StatisticComponent initStatistic()
	{
		// TODO: Implement this method
		return null;
	}


	
	public static int height=140;
	public static int widht=100;
	@Override
	private static String txtRes = "tree.png";

	private static Texture entitexture;
	
	public Texture getTexture()
	{
		return new Texture( Gdx.files.internal(txtRes)); 
	}
	
	public Tree()
	{
		super(null);
	}
}
