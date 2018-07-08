package com.yellastrodev.entroworld.ui.view;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import java.util.*;
import com.badlogic.gdx.assets.loaders.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.yellastrodev.entroworld.static_initializers.*;
import com.yellastrodev.entroworld.ui.*;

public class ItemCreateScreen implements View
{
	
	java.util.List<TextField> mTextFields = new ArrayList<>();
	
	final TextField TextField1;

	private float row_height = 80;

	private ListItem mItem;
	
	public static final String[] mtypes = 
	{"WEAPON","HEAD","MASK","HELMET","SHOULDER","CHEST","TSHORT","BELT","LEGS","FEET","BEHIND",
	"HANDS"};
	
	public static final String[] mSets = 
	{"Chain armor","Iron armor","Golden armor","Farmers","Leather","Worker","Just suit"};

	private Stage mStage;

	private EditorScreen mScreen;
	
	public ItemCreateScreen(ListItem fItem,EditorScreen fScreen)
	{
		//Skin skin = new Skin();
		mScreen = fScreen;
		mItem = fItem;
		mStage = new Stage();
		
		List.ListStyle fSekStyle =new List.ListStyle();
		fSekStyle.font = new BitmapFont();
		fSekStyle.font.scale(1.2f);
		fSekStyle.fontColorUnselected = Color.BLACK;
		fSekStyle.fontColorSelected = Color.GREEN;
		fSekStyle.background = new TextureRegionDrawable(TextureInit.getUiLongItemBackground());
		fSekStyle.selection = fSekStyle.background;
		
		
		final List<String> fSeckBox = new List<>(fSekStyle);
		fSeckBox.setItems(mtypes);
		fSeckBox.setSize(200,650);
		fSeckBox.setPosition(20,50);
		
		final List<String> fSetList = new List<>(fSekStyle);
		fSetList.setItems(mSets);
		fSetList.setSize(200,450);
		fSetList.setPosition(230,80);
		mStage.addActor(fSetList);
		
		TextField.TextFieldStyle TextField1Style = new TextField.TextFieldStyle();
		BitmapFont myFont = new BitmapFont();
		myFont.scale(2);
		//TextField1Style.background = new TextureRegionDrawable(mItem.mIcon);
		TextField1Style.font = myFont;
		TextField1Style.fontColor = Color.RED;
		TextField1Style.background = new TextureRegionDrawable(TextureInit.getUiLongItemBackground());
		
		TextField1 = new TextField("",TextField1Style);
		TextField1.setSize(Gdx.graphics.getWidth()-100,row_height);
		TextField1.setPosition(50,Gdx.graphics.getHeight()-row_height);
		TextField1.setMessageText("Name");
		TextField1.setMaxLength(20);
		
		final TextField TextField2 = new TextField("", TextField1Style);
		TextField2.setSize(Gdx.graphics.getWidth() - 100, row_height);
		TextField2.setPosition(50, (Gdx.graphics.getHeight() - (row_height*2)));
		TextField2.setMessageText("Armor");
		TextField2.setMaxLength(3);
		
		
		final TextField TextField3 = new TextField("", TextField1Style);
		TextField3.setSize(Gdx.graphics.getWidth() - 100, row_height);
		TextField3.setPosition(50, (Gdx.graphics.getHeight() - (row_height*3)));
		TextField3.setMessageText("Atack");
		TextField3.setMaxLength(3);
		
		Button.ButtonStyle mButStyle = new Button.ButtonStyle();
		mButStyle.up = new TextureRegionDrawable(TextureInit.getUiMidleButtonUpBackground());
		mButStyle.down = new TextureRegionDrawable(TextureInit.getUiMidleButtonDownBackground());
		
		
		
		final Button fButton = new Button(mButStyle);
		fButton.setName("Create");
		fButton.setPosition(250,row_height);
		fButton.setSize(Gdx.graphics.getWidth() - 250,row_height);
		fButton.addListener(new com.badlogic.gdx.scenes.scene2d.EventListener(){
				boolean mOk=true;
				@Override
				public boolean handle(Event p1)
				{
					if(mOk)
					createItem(TextField1.getText(),
						mItem.mFileAdress,
						fSeckBox.getSelected().toString(),
						TextField2.getText(),
						TextField3.getText(),fSetList.getSelected().toString());
					finish();
					mOk=false;
					return true;
				}
		});
		
		
		
		Gdx.input.setInputProcessor(mStage);
		mStage.addActor(TextField1);
		mStage.addActor(TextField2);
		mStage.addActor(TextField3);
		mStage.addActor(fButton);
		mStage.addActor(fSeckBox);
		//TextField1.set(Align.center);
		mTextFields.add(TextField1);
		
		//TextField mTextField = new TextField();
	}

	public void createItem(String fName, String fAdrss, String fType, String fArm, String fDmg,String fSet)
	{
		ItemLoader.createItem(fAdrss,fName,fType,
			fArm,fDmg,fSet);
		
	}

	@Override
	public void drawn(SpriteBatch fBatch)
	{
		mItem.drawn(fBatch,290,400);
		mStage.draw();
		mStage.act();
		//TextField1.draw(fBatch,255f);
		//mTextFields.get(0).draw(fBatch,255);
	}
	
	private void finish()
	{
		mScreen.openMainScreen();
	}
	
}
