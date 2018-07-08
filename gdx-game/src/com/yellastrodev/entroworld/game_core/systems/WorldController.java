package com.yellastrodev.entroworld.game_core.systems;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.yellastrodev.entroworld.*;
import com.yellastrodev.entroworld.game_core.nodes.*;

public class WorldController implements iSystem
{

	@Override
	public void deleteNode(Object ds)
	{
		nodes.remove(ds);
	}

	public TypedList nodes=new TypedList();
	

	private static final String TAG = WorldController.class.getName();

	

	private int selectedSprite;
	public WorldController () { init(); }
	private void init () 
	{
		
		}
	public void update (float deltaTime) 
	{
		//updateTestObjects(deltaTime);
		for(MovedNode movNode:nodes)
		{
			movNode.mPositionComp.oX+=
				movNode.mVelocityComp.velocoX*deltaTime;
			movNode.mPositionComp.oY+=
				movNode.mVelocityComp.velocoY*deltaTime;
			/*if(movNode.mPositionComp.oX>200)
				movNode.mVelocityComp.velocoX=-55;
			if(movNode.mPositionComp.oX<100)
				movNode.mVelocityComp.velocoX=55;*/
		}
	} 
	
	
	@Override
	public void addNode(Object node)
	{
		nodes.add(node);
	}


	@Override
	public void start()
	{
		// TODO: Implement this method
	}

	@Override
	public void end()
	{
		// TODO: Implement this method
	}
}
