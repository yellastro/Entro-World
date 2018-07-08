package com.yellastrodev.entroworld.game_core.systems;
import java.util.*;
import com.yellastrodev.entroworld.game_core.nodes.*;

public class sysAnimations implements iSystem
{

	@Override
	public void deleteNode(Object ds)
	{
		mNodes.remove(ds);
	}

	
	ArrayList<iAnimationNode> mNodes=new ArrayList<>();
	float mStateTimer=0;

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

	@Override
	public void update(float fStateTime)
	{
		mStateTimer+=fStateTime;
		for(iAnimationNode aNode:mNodes)
		{
			aNode.update(mStateTimer);
			
			/*switch(aNode.velcComponent.angularVel)
			{
				case 1:
					aNode.textrComponent.mSprite = 
						aNode.animComponent.topAnimation.getKeyFrame(stateTimer, true);
					break;
				case 2:
					aNode.textrComponent.mSprite = 
						aNode.animComponent.leftAnimation.getKeyFrame(stateTimer, true);
					break;
				case 3:
					aNode.textrComponent.mSprite = 
						aNode.animComponent.botAnimation.getKeyFrame(stateTimer, true);
					break;
				case 4:
					aNode.textrComponent.mSprite = 
						aNode.animComponent.rightAnimation.getKeyFrame(stateTimer, true);
					break;
				default:
					aNode.textrComponent.mSprite = 
						aNode.animComponent.rightAnimation.getKeyFrame(stateTimer,true);
			}*/
			//это должно быть там, где меняется направление
			/*
			if(Math.abs(aNode.velcComponent.velocoX)>Math.abs(aNode.velcComponent.velocoY))
			{
				if(aNode.velcComponent.velocoX>0)
					aNode.textrComponent.mSprite = 
						aNode.animComponent.rightAnimation.getKeyFrame(stateTimer,true);
				else
					aNode.textrComponent.mSprite = 
						aNode.animComponent.leftAnimation.getKeyFrame(stateTimer,true);
			}
			else
				if(aNode.velcComponent.velocoY>0)
					aNode.textrComponent.mSprite =
						aNode.animComponent.topAnimation.getKeyFrame(stateTimer,true);
				else
				aNode.textrComponent.mSprite =
					aNode.animComponent.botAnimation.getKeyFrame(stateTimer,true);
			*/
		}
	}

	@Override
	public void addNode(Object node)
	{
		mNodes.add((iAnimationNode)node);
	}
	
	
	
}
