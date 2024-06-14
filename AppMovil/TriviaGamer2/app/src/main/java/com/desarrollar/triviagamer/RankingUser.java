package com.desarrollar.triviagamer;

public class RankingUser {
    private String Name;
    private int Score;
    private int Played;
    private int Id;

    public RankingUser(String name, int score, int played, int id) {
        this.Name = name;
        this.Score = score;
        this.Played = played;
        this.Id = id;
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

    public int getPlayed() {
        return Played;
    }

    public void setPlayed(int played) {
        Played = played;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}