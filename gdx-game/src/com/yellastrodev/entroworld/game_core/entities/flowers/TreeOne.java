package com.yellastrodev.entroworld.game_core.entities.flowers;
import com.yellastrodev.entroworld.game_core.components.*;
import com.yellastrodev.entroworld.static_initializers.*;
import com.yellastrodev.entroworld.game_core.*;

public class TreeOne extends Flower
{

	@Override
	public StatisticComponent initStatistic()
	{
		// TODO: Implement this method
		 mStatistic=new StatisticComponent
		(Flower.class,0,200,0
		 ,null,null,0,0);
		return mStatistic;
	}
	
	
	public TreeOne(Engine fEngine)
	{
		super(fEngine);
	}

	@Override
	public float getSize()
	{
		// TODO: Implement this method
		return 2;
	}

	@Override
	public CoupeOfSheets initAnim()
	{
		// TODO: Implement this method
		return null;
	}

	@Override
	public DisplayComp initDisplayComp(float fScale)
	{
		
		return new DisplayComp
		(TextureInit.getSomeTree()
		 ,fScale);
	}
	
	
}
