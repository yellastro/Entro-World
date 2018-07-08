package com.yellastrodev.entroworld.game_core.nodes;
import com.yellastrodev.entroworld.game_core.components.*;

public class MovedNode
{
	public VelocityComp mVelocityComp;
	public PositionComp mPositionComp;
	
	public MovedNode(VelocityComp vel, PositionComp pos)
	{
		mVelocityComp=vel;
		mPositionComp=pos;
	}
}
