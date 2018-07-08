package com.yellastrodev.entroworld.game_core.systems;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.*;
import com.yellastrodev.entroworld.*;
import com.yellastrodev.entroworld.game_core.*;
import com.yellastrodev.entroworld.game_core.nodes.*;
import com.yellastrodev.entroworld.static_initializers.*;
import com.yellastrodev.entroworld.map_generator.*;
import com.yellastrodev.entroworld.game_core.entities.flowers.*;
import com.yellastrodev.entroworld.game_core.components.*;

public class WorldRenderer implements Disposable,iSystem 
{
	private float mSortInterval=0;

	@Override
	public void deleteNode(Object ds)
	{
		Nodes.remove(ds);
	}


	@Override
	public void addNode(Object node)
	{
		Nodes.add(node);
	}
	
	public TypedList Nodes=new TypedList();
	
	
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

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Engine mEngine;
	
	public WorldRenderer (Engine fEng)
	{
		this.mEngine = fEng;
		init();
	}
	
	private void init ()
	{
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
		camera.position.set(0, 0, 0);
		camera.update();
		
		
	}
	
	BitmapFont FontRed2 = new BitmapFont();

	public SimpleGroundGenerator mMap = new SimpleGroundGenerator();
	
	@Override
	public void update(float time)
	{
		mSortInterval+=time;
		if(mSortInterval>1f)
			sortByLvl();
		//renderTestObjects();
		batch.begin();
		
		mMap.draw(batch);
		for(DisplayNode nd:Nodes)
		{
			nd.rendering(batch);
				
			if(nd.mStatsts!=null)
			{
				FontRed2.setColor(Color.RED); //Красный
				FontRed2.setScale(1);

				
				String info="";
				//wlfState.getActiveState().getClass().toString().substring(50)

				info=nd.mStatsts.mHP+"/"+nd.mStatsts.mMaxHP+"hp"+" _lvl="+Nodes.indexOf(nd);

				FontRed2.draw(batch, info,nd.mPositionComp.oX, nd.mPositionComp.oY);
			}
		}
		mEngine.printInfo(batch);
		batch.end();
	}

	public void sortByLvl()
	{
		mSortInterval=0;
		for(int i=1;i<Nodes.size();i++)
		{
			DisplayNode fNode1 =(DisplayNode) Nodes.get(i);
			//for(int j=i-1;j>0;j--)
			int j = i-1;
			DisplayNode fNode2 =(DisplayNode) Nodes.get(j);
			while(fNode1.mPositionComp.oY >
				  fNode2.mPositionComp.oY&&j>0)
			{
				j--;
				fNode2 =(DisplayNode) Nodes.get(j);
			}
			fNode2 =(DisplayNode) Nodes.get(j+1);
			if(fNode1.mPositionComp.oY >
			   fNode2.mPositionComp.oY)
			{
				Nodes.remove(fNode1);
				Nodes.add(j,fNode1);
			}
		}
	}
	
	public void resize (int width, int height) { }
	@Override public void dispose () { } 
	
}
