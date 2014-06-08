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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class HighscoreScreen implements Screen{

	private GameLevel game;
	private Boolean win;
	private Stage stage;
	private Skin skin;
	private TextureAtlas atlas;
	private Table table , buttonTable;
	private TextButton buttonMainMenu, buttonBack;
	private Label heading,score1,score2,score3,score4,score5,score6,score7,score8,score9,score10;
	private BitmapFont white, black;
	private TweenManager tweenManager;
	private long time;
	

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

	public HighscoreScreen(GameLevel game, Boolean win, long time) {
		this.game=game;
		this.win=win;
		this.time=time;
		show();
	}
	
	@Override
	public void show() {
		stage = new Stage();

		atlas = new TextureAtlas("ui/atlas.pack");
		skin = new Skin(atlas);

		table = new Table(skin);
		table.setFillParent(true);
		buttonTable = new Table(skin);

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
		heading = new Label("THIS LEVEL'S BEST:", headlingStyle);
		heading.setFontScale(2);
		

		if (game.getHighScores().getScores()[0] != Long.MAX_VALUE)
			score1 = new Label ("1-			" + game.getHighScores().getScores()[0] + " SECONDS" , headlingStyle);
		else
			score1 = new Label ("1-			-------  SECONDS" , headlingStyle);
		
		if (game.getHighScores().getScores()[1] != Long.MAX_VALUE)
			score2 = new Label ("2-			" + game.getHighScores().getScores()[1] + " SECONDS" , headlingStyle);
		else
			score2 = new Label ("2-			-------  SECONDS" , headlingStyle);
		
		if (game.getHighScores().getScores()[2] != Long.MAX_VALUE)
			score3 = new Label ("3-			" + game.getHighScores().getScores()[2] + " SECONDS" , headlingStyle);
		else
			score3 = new Label ("3-			-------  SECONDS" , headlingStyle);
		
		if (game.getHighScores().getScores()[3] != Long.MAX_VALUE)
			score4 = new Label ("4-			" + game.getHighScores().getScores()[3] + " SECONDS" , headlingStyle);
		else
			score4 = new Label ("4-			-------  SECONDS" , headlingStyle);
		
		if (game.getHighScores().getScores()[4] != Long.MAX_VALUE)
			score5 = new Label ("5-			" + game.getHighScores().getScores()[4] + " SECONDS" , headlingStyle);
		else
			score5 = new Label ("5-			-------  SECONDS" , headlingStyle);
		
		if (game.getHighScores().getScores()[5] != Long.MAX_VALUE)
			score6 = new Label ("6-			" + game.getHighScores().getScores()[5] + " SECONDS" , headlingStyle);
		else
			score6 = new Label ("6-			-------  SECONDS" , headlingStyle);
		
		if (game.getHighScores().getScores()[6] != Long.MAX_VALUE)
			score7 = new Label ("7-			" + game.getHighScores().getScores()[6] + " SECONDS" , headlingStyle);
		else
			score7 = new Label ("7-			-------  SECONDS" , headlingStyle);
		
		if (game.getHighScores().getScores()[7] != Long.MAX_VALUE)
			score8 = new Label ("8-			" + game.getHighScores().getScores()[7] + " SECONDS" , headlingStyle);
		else
			score8 = new Label ("8-			-------  SECONDS" , headlingStyle);
		
		if (game.getHighScores().getScores()[8] != Long.MAX_VALUE)
			score9 = new Label ("9-			" + game.getHighScores().getScores()[8] + " SECONDS" , headlingStyle);
		else
			score9 = new Label ("9-			-------  SECONDS" , headlingStyle);
		
		if (game.getHighScores().getScores()[9] != Long.MAX_VALUE)
			score10 = new Label ("10-			" + game.getHighScores().getScores()[9] + " SECONDS" , headlingStyle);
		else
			score10 = new Label ("10-			-------  SECONDS" , headlingStyle);
	

		// creating buttons

		buttonMainMenu = new TextButton("Main Menu", textButtonStyle);
		buttonMainMenu.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
					((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());

			}
		});
		buttonMainMenu.pad(15);
		
		buttonBack = new TextButton("Back", textButtonStyle);
		buttonBack.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (win)
					((Game) Gdx.app.getApplicationListener()).setScreen(new WinScreen(time, game));
				else
					((Game) Gdx.app.getApplicationListener()).setScreen(new LoseScreen( game));
			}
		});
		buttonBack.pad(15);

		// putting stuff together
		table.add(heading).spaceBottom(25).row();
		table.add(score1).spaceBottom(5).row();
		table.add(score2).spaceBottom(5).row();
		table.add(score3).spaceBottom(5).row();
		table.add(score4).spaceBottom(5).row();
		table.add(score5).spaceBottom(5).row();
		table.add(score6).spaceBottom(5).row();
		table.add(score7).spaceBottom(5).row();
		table.add(score8).spaceBottom(5).row();
		table.add(score9).spaceBottom(5).row();
		table.add(score10).spaceBottom(5).row();
		buttonTable.add(buttonBack).spaceRight(5);
		buttonTable.add(buttonMainMenu).spaceRight(5);
		table.add(buttonTable);
		

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
		.push(Tween.set(score1, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(score2, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(score3, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(score4, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(score5, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(score6, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(score7, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(score8, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(score9, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(score10, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(buttonMainMenu, ActorAccessor.ALPHA).target(0))		
		.push(Tween.set(buttonBack, ActorAccessor.ALPHA).target(0))
		.push(Tween.from(heading, ActorAccessor.ALPHA, .25f).target(0))
		.push(Tween.to(score1, ActorAccessor.ALPHA, .25f).target(1))
		.push(Tween.to(score2, ActorAccessor.ALPHA, .25f).target(1))
		.push(Tween.to(score3, ActorAccessor.ALPHA, .25f).target(1))
		.push(Tween.to(score4, ActorAccessor.ALPHA, .25f).target(1))
		.push(Tween.to(score5, ActorAccessor.ALPHA, .25f).target(1))
		.push(Tween.to(score6, ActorAccessor.ALPHA, .25f).target(1))
		.push(Tween.to(score7, ActorAccessor.ALPHA, .25f).target(1))
		.push(Tween.to(score8, ActorAccessor.ALPHA, .25f).target(1))
		.push(Tween.to(score9, ActorAccessor.ALPHA, .25f).target(1))
		.push(Tween.to(score10, ActorAccessor.ALPHA, .25f).target(1))
		.push(Tween.to(buttonMainMenu, ActorAccessor.ALPHA, .25f).target(1))
		.push(Tween.to(buttonBack, ActorAccessor.ALPHA, .25f).target(1))
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