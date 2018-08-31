package com.yellastrodev.entroworld.game_core.nodes;
import com.yellastrodev.entroworld.game_core.components.*;

public class MovedNode
{
	public VelocityComp mVelocityComp;
	public PositionOnMapComponent mPositionComp;
	
	public MovedNode(VelocityComp vel, PositionOnMapComponent pos)
	{
		mVelocityComp=vel;
		mPositionComp=pos;
	}
}
