package com.moopsup.rocketboy;

public class ScoreEntry 
{
	String name;
	int score;
	int level;
	
	public ScoreEntry(String name, int score, int level)
	{
		this.name = name;
		this.score = score;
		this.level = level;
	}
	
	public void setScore(int score)
	{
		this.score = score;
	}
	public void setLevel(int level)
	{
		this.level = level;
	}
}
