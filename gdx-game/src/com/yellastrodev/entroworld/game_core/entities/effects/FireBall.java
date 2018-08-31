package com.yellastrodev.entroworld.game_core.entities.effects;
import com.badlogic.gdx.math.*;
import com.yellastrodev.entroworld.game_core.*;
import com.yellastrodev.entroworld.game_core.components.*;
import com.yellastrodev.entroworld.game_core.nodes.*;
import com.yellastrodev.entroworld.game_core.systems.*;
import com.yellastrodev.entroworld.static_initializers.*;
import java.util.*;
import com.yellastrodev.entroworld.game_core.states.state_managers.*;
import com.yellastrodev.entroworld.game_core.entities.*;

public class FireBall extends Effect
{

	@Override
	public void initLiveCicle()
	{
		// TODO: Implement this method
	}
	

	PositionOnMapComponent mStartPos;
	
	public FireBall(Engine fEng,PositionOnMapComponent fPoss)
	{
		super(fEng);
		//mStartPos = new PositionComp();
		mStartPos=fPoss;
	}
	
	@Override
	public void initComponents(CoupeOfSheets fStore,float fScale)
	{
		mStatistic=initStatistic();
		//addComponent(stc);
		//addComponent(mStartPos);
		EfAnimationComponent fAnimc = OneDirectionAnimInit.getFireBallAnimation().mFlyAnimation;
		//fAnimc.MainAnimation = fAnimc.mStore.mFlyAnimation.MainAnimation;
		EfAnimStore fStores = OneDirectionAnimInit.getFireBallAnimation();
		DisplayComp fdispComp=new DisplayComp
		(fStores.mFlyAnimation.MainAnimation.getKeyFrame(0)
		 ,fScale);
		addComponent(fdispComp);
		VelocityComp velcom=new VelocityComp();
		addComponent(velcom);
		//mStartPos = new PositionComp();
		addComponent(PositionOnMapComponent.setNewPos());
		

		/*
		List<TextureRegion> fTextures = new ArrayList<>();
		List<AnimateComponent> fAniCompList = new ArrayList<>();
		for(AnimationStore qSheets:fStore.mStores)
		{
			if(qSheets.mStandAnimation!=null)
				fdispComp.mSprite.add(qSheets.mStandAnimation.rightAnimation.getKeyFrame(0,false));

			AnimateComponent aniComp=new AnimateComponent();
			aniComp.mStore = qSheets;
			fAniCompList.add(aniComp);
			addComponent(aniComp);
		}*/

		makeAnimationNode(fStores,velcom,fdispComp);
	}


	
	
	
	

	private void makeAnimationNode(EfAnimStore fAnimc, VelocityComp velcom, DisplayComp fdispComp)
	{
		
		List<EfAnimStore> fList=new ArrayList<>();
		fList.add(fAnimc);
		EfAnimationNode fNode= new EfAnimationNode(fList,
			velcom,fdispComp);
			
		iSystem ss=mEngine.mSystems.get(sysAnimations.class);
		ss.addNode(fNode);
		mAnimateNode = fNode;
	}
	
	@Override
	public float getSize()
	{
		// TODO: Implement this method
		return 1.5f;
	}
	
	@Override
	public void initProcessManager(CoupeOfSheets fStore, Engine fEngine)
	{
		mProcessManager = new EffectProcesManager(this,fStore,fEngine);
	}

	@Override
	public CoupeOfSheets initAnim()
	{/*
		CoupeOfSheets fCoupe = new CoupeOfSheets(
			);*/
		return null;
	}

	@Override
	public StatisticComponent initStatistic()
	{
		// TODO: Implement this method
		return new StatisticComponent(null,150,0,1
			  ,EnEntity.class,EnEntity.class
			  ,35,20);
	}

	
}
