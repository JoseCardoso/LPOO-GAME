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
	private Table table, buttonsUp, buttonsDown;
	private TextButton buttonLevel1 ,buttonLevel2 ,buttonLevel3 ,buttonLevel4 ,buttonLevel5 ,buttonLevel6 , buttonReturn;
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
				//((Game) Gdx.app.getApplicationListener()).setScreen(new Level1());
			}
		});
		buttonLevel3.pad(15);
		
		buttonLevel4 = new TextButton(" 4 ", textButtonStyle);
		buttonLevel4.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				//((Game) Gdx.app.getApplicationListener()).setScreen(new Level1());
			}
		});
		buttonLevel4.pad(15);
		
		buttonLevel5 = new TextButton(" 5 ", textButtonStyle);
		buttonLevel5.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				//((Game) Gdx.app.getApplicationListener()).setScreen(new Level1());
			}
		});
		buttonLevel5.pad(15);
		
		buttonLevel6 = new TextButton(" 6 ", textButtonStyle);
		buttonLevel6.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				//((Game) Gdx.app.getApplicationListener()).setScreen(new Level1());
			}
		});
		buttonLevel6.pad(15);

		buttonReturn = new TextButton("RETURN", textButtonStyle);
		buttonReturn.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());
			}
		});
		buttonReturn.pad(15);

		buttonsUp.add(buttonLevel1).spaceRight(20);
		buttonsUp.add(buttonLevel2).spaceRight(20);
		buttonsUp.add(buttonLevel3);
		buttonsDown.add(buttonLevel4).spaceRight(20);
		buttonsDown.add(buttonLevel5).spaceRight(20);
		buttonsDown.add(buttonLevel6);
		
		// putting stuff together
		table.add(heading).spaceBottom(20).row();
		table.add(buttonsUp).spaceBottom(25).row();
		table.add(buttonsDown).spaceBottom(25).row();


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
		.push(Tween.set(buttonLevel1, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(buttonLevel2, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(buttonLevel3, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(buttonLevel4, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(buttonLevel5, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(buttonLevel6, ActorAccessor.ALPHA).target(0))
		.push(Tween.from(heading, ActorAccessor.ALPHA, .25f).target(0))
		.push(Tween.to(buttonLevel1, ActorAccessor.ALPHA, .25f).target(1))
		.push(Tween.to(buttonLevel2, ActorAccessor.ALPHA, .25f).target(1))
		.push(Tween.to(buttonLevel3, ActorAccessor.ALPHA, .25f).target(1))
		.push(Tween.to(buttonLevel4, ActorAccessor.ALPHA, .25f).target(1))
		.push(Tween.to(buttonLevel5, ActorAccessor.ALPHA, .25f).target(1))
		.push(Tween.to(buttonLevel6, ActorAccessor.ALPHA, .25f).target(1))
		.end().start(tweenManager);

		// table fade-in
		Tween.from(table, ActorAccessor.ALPHA, .75f).target(0).start(tweenManager);
		Tween.from(table, ActorAccessor.Y, .75f).target(Gdx.graphics.getHeight() / 8).start(tweenManager);

		tweenManager.update(Gdx.graphics.getDeltaTime());

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

}