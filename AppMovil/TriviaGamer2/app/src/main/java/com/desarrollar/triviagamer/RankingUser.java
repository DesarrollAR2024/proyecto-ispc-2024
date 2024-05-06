package com.desarrollar.triviagamer;

public class RankingUser {
    private String Name;
    private int Score;

    public RankingUser(String name, int score) {

        this.Name = name;
        this.Score = score;
    }

    public RankingUser() {

    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
