package com.jumpityJump.game;

import java.awt.Checkbox;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SettingsScreen implements Screen {


	private Stage stage;
	private Skin skin;
	private TextureAtlas atlas;
	private Table table;
	private TextButton buttonReturn;
	private Label heading;
	private CheckBox checkVibration;
	private CheckBox checkSound;
	private BitmapFont white, black;
	private TweenManager tweenManager;


	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub

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
		heading = new Label("SETTINGS", headlingStyle);
		heading.setFontScale(2);

		// creating buttons

		buttonReturn = new TextButton("EXIT", textButtonStyle);
		buttonReturn.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				Timeline.createParallel().beginParallel()
				.push(Tween.to(table, ActorAccessor.ALPHA, .75f).target(0))
				.push(Tween.to(table, ActorAccessor.Y, .75f).target(table.getY() - 50)
						.setCallback(new TweenCallback() {

							@Override
							public void onEvent(int type, BaseTween<?> source) {
								Gdx.app.exit();
							}
						}))
						.end().start(tweenManager);
			}
		});
		buttonReturn.pad(15);

		// putting stuff together
		table.add(heading).spaceBottom(100).row();
		table.add(buttonReturn).spaceBottom(15).row();

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
		.push(Tween.set(buttonPlay, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(buttonSettings, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(buttonExit, ActorAccessor.ALPHA).target(0))
		.push(Tween.from(heading, ActorAccessor.ALPHA, .25f).target(0))
		.push(Tween.to(buttonPlay, ActorAccessor.ALPHA, .25f).target(1))
		.push(Tween.to(buttonSettings, ActorAccessor.ALPHA, .25f).target(1))
		.push(Tween.to(buttonExit, ActorAccessor.ALPHA, .25f).target(1))
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
