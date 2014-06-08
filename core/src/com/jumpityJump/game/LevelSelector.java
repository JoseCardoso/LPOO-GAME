package com.jumpityJump.game;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class LevelSelector implements Screen {

	private Stage stage;
	private Skin skin;
	private TextureAtlas atlas;
	private Table table, buttonsUp, buttonsMiddle ,buttonsDown;
	private TextButton buttonLevel1 ,buttonLevel2 ,buttonLevel3 ,buttonTutorial1 ,buttonTutorial2,buttonTutorial3,buttonTutorial4,buttonTutorial5,buttonTutorial6, buttonReturn;
	private Label heading;
	private BitmapFont white, black;
	private TweenManager tweenManager;


	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();
		tweenManager.update(delta);// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}



	@Override
	public void show() {
		stage = new Stage();

		atlas = new TextureAtlas("ui/atlas.pack");
		skin = new Skin(atlas);

		table = new Table(skin);
		buttonsUp = new Table(skin);
		buttonsMiddle = new Table(skin);
		buttonsDown = new Table(skin);
		table.setFillParent(true);

		white = new BitmapFont(Gdx.files.internal("font/white.fnt"), false);
		black = new BitmapFont(Gdx.files.internal("font/black.fnt"), false);


		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("button.up");
		textButtonStyle.down = skin.getDrawable("button.down");
		textButtonStyle.pressedOffsetX = 1;
		textButtonStyle.pressedOffsetY = -1;
		textButtonStyle.font = black;



		Gdx.input.setInputProcessor(stage);



		// creating heading
		LabelStyle headlingStyle = new LabelStyle(white, Color.WHITE);
		heading = new Label("LEVEL SELECTOR", headlingStyle);
		heading.setFontScale(2);
		


		// creating buttons

		buttonLevel1 = new TextButton(" 1 ", textButtonStyle);
		buttonLevel1.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new Level1());
			}
		});
		buttonLevel1.pad(15);
		
		buttonLevel2 = new TextButton(" 2 ", textButtonStyle);
		buttonLevel2.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new Level2());
			}
		});
		buttonLevel2.pad(15);
		
		buttonLevel3 = new TextButton(" 3 ", textButtonStyle);
		buttonLevel3.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new Level3());
			}
		});
		buttonLevel3.pad(15);
		
		buttonTutorial1 = new TextButton(" T1 ", textButtonStyle);
		buttonTutorial1.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new Tutorial1());
			}
		});
		buttonTutorial1.pad(15);
		
		buttonTutorial2 = new TextButton(" T2 ", textButtonStyle);
		buttonTutorial2.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new Tutorial2());
			}
		});
		buttonTutorial2.pad(15);
		
		buttonTutorial3 = new TextButton(" T3 ", textButtonStyle);
		buttonTutorial3.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen( new Tutorial3());
			}
		});
		buttonTutorial3.pad(15);
		
		buttonTutorial4 = new TextButton(" T4 ", textButtonStyle);
		buttonTutorial4.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new Tutorial4());
			}
		});
		buttonTutorial4.pad(15);
		
		buttonTutorial5 = new TextButton(" T5 ", textButtonStyle);
		buttonTutorial5.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new Tutorial5());
			}
		});
		buttonTutorial5.pad(15);
		
		buttonTutorial6 = new TextButton(" T6 ", textButtonStyle);
		buttonTutorial6.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new Tutorial6());
			}
		});
		buttonTutorial6.pad(15);

		buttonReturn = new TextButton("RETURN", textButtonStyle);
		buttonReturn.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());
			}
		});
		buttonReturn.pad(15);

		buttonsDown.add(buttonLevel1).spaceRight(20);
		buttonsDown.add(buttonLevel2).spaceRight(20);
		buttonsDown.add(buttonLevel3);
		buttonsUp.add(buttonTutorial1).spaceRight(20);
		buttonsUp.add(buttonTutorial2).spaceRight(20);
		buttonsUp.add(buttonTutorial3);
		buttonsMiddle.add(buttonTutorial4).spaceRight(20);
		buttonsMiddle.add(buttonTutorial5).spaceRight(20);
		buttonsMiddle.add(buttonTutorial6);
		
		// putting stuff together
		table.add(heading).spaceBottom(20).row();
		table.add(buttonsUp).spaceBottom(25).row();
		table.add(buttonsMiddle).spaceBottom(25).row();
		table.add(buttonsDown).spaceBottom(25).row();
		table.add(buttonReturn);
		stage.addActor(table);

		// creating animations
		tweenManager = new TweenManager();
		Tween.registerAccessor(Actor.class, new ActorAccessor());

		// heading color animation
		Timeline.createSequence().beginSequence()
		.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(0, 0, 1))
		.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(0, 1, 0))
		.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(1, 0, 0))
		.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(1, 1, 0))
		.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(0, 1, 1))
		.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(1, 0, 1))
		.push(Tween.to(heading, ActorAccessor.RGB, .5f).target(1, 1, 1))
		.end().repeat(Tween.INFINITY, 0).start(tweenManager);

		// heading and buttons fade-in
		Timeline.createSequence().beginSequence()
		.push(Tween.set(buttonTutorial1, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(buttonTutorial2, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(buttonTutorial3, ActorAccessor.ALPHA).target(0))		
		.push(Tween.set(buttonTutorial4, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(buttonTutorial5, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(buttonTutorial6, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(buttonLevel1, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(buttonLevel2, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(buttonLevel3, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(buttonReturn, ActorAccessor.ALPHA).target(0))
		.push(Tween.from(heading, ActorAccessor.ALPHA, .25f).target(0))
		.push(Tween.to(buttonTutorial1, ActorAccessor.ALPHA, .25f).target(1))
		.push(Tween.to(buttonTutorial2, ActorAccessor.ALPHA, .25f).target(1))
		.push(Tween.to(buttonTutorial3, ActorAccessor.ALPHA, .25f).target(1))
		.push(Tween.to(buttonTutorial4, ActorAccessor.ALPHA, .25f).target(1))
		.push(Tween.to(buttonTutorial5, ActorAccessor.ALPHA, .25f).target(1))
		.push(Tween.to(buttonTutorial6, ActorAccessor.ALPHA, .25f).target(1))
		.push(Tween.to(buttonLevel1, ActorAccessor.ALPHA, .25f).target(1))
		.push(Tween.to(buttonLevel2, ActorAccessor.ALPHA, .25f).target(1))
		.push(Tween.to(buttonLevel3, ActorAccessor.ALPHA, .25f).target(1))
		.push(Tween.to(buttonReturn, ActorAccessor.ALPHA, .25f).target(1))
		.end().start(tweenManager);

		// table fade-in
		Tween.from(table, ActorAccessor.ALPHA, .75f).target(0).start(tweenManager);
		Tween.from(table, ActorAccessor.Y, .75f).target(Gdx.graphics.getHeight() / 8).start(tweenManager);

		tweenManager.update(Gdx.graphics.getDeltaTime());

	}

	@Override
	public void hide() {
		dispose();

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
	}

}