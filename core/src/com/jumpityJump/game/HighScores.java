package com.jumpityJump.game;

public class HighScores {

	private long scores[] = new long[10];

	public HighScores() {
		// TODO Auto-generated constructor stub
		for(int i = 0; i < 10; i++)
			scores[i] = 0;
	}

	public void newScore(long score)
	{
		for(int i = 0; i < 10; i++)
			if(scores[i] == 0 || score < scores[i])
			{
				scores[i] = score;
				break;
			}	
	}

	public long[] getScores() {
		return scores;
	}	
}

