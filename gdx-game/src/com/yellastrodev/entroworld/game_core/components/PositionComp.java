package com.yellastrodev.entroworld.game_core.components;

import com.badlogic.gdx.math.*;

public class PositionComp
{
	public float oX;
	public float oY;
	public float rotation;

	public void SetPos(PositionComp fPos)
	{
		oX = fPos.oX;
		oY = fPos.oY;
		rotation = fPos.rotation;
	}
	
	public static PositionComp setNewPos()
	{
		PositionComp posComp=new PositionComp(
			MathUtils.random(-2.0f, 400.0f),
			MathUtils.random(-2.0f, 600.0f));//MathUtils.random(-2.0f, 600.0f);
		posComp.rotation=0;
		return posComp;
	}
	
	public PositionComp(float x, float y)
	{
		oX = x;
		oY = y;
	}
	
	
}
