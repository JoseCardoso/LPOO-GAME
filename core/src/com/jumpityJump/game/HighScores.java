package com.jumpityJump.game;

import java.util.Arrays;

public class HighScores {

	private long scores[] = new long[10];

	public HighScores() {
		// TODO Auto-generated constructor stub
		for(int i = 0; i < 10; i++)
			scores[i] = Long.MAX_VALUE;
	}

	public void newScore(long score)
	{
		for(int i = 0; i < 10; i++)
			if(scores[i] == 0 || score < scores[i])
			{
				scores[i] = score/1000;
				break;
			}	
		Arrays.sort(scores);
	}

	public long[] getScores() {
		return scores;
	}	
}

