package com.example.hblpsl;

public class Team {
    private String teamOwner;
    private String bowlingCoach;
    private String battingCoach;
    private String teamName;
    private String captainName;
    private String coachName;
    private String homeCity;
    private int matchesPlayed;
    private int matchesWon;
    private int matchesLost;
    private int points;
    private static int counter = 0;

    public Team( String teamName,String captainName, String coachName, String bowlingCoach, String battingCoach, String teamOwner,
                String homeCity, int matchesPlayed, int matchesWon, int matchesLost, int points) {
        this.teamOwner = teamOwner;
        this.bowlingCoach = bowlingCoach;
        this.battingCoach = battingCoach;
        this.teamName = teamName;
        this.captainName = captainName;
        this.coachName = coachName;
        this.homeCity = homeCity;
        this.matchesPlayed = matchesPlayed;
        this.matchesWon = matchesWon;
        this.matchesLost = matchesLost;
        this.points = points;
    }

    public Team() {}

    public String getTeamOwner() {
        return teamOwner;
    }

    public void setTeamOwner(String teamOwner) {
        this.teamOwner = teamOwner;
    }

    public String getBowlingCoach() {
        return bowlingCoach;
    }

    public void setBowlingCoach(String bowlingCoach) {
        this.bowlingCoach = bowlingCoach;
    }

    public String getBattingCoach() {
        return battingCoach;
    }

    public void setBattingCoach(String battingCoach) {
        this.battingCoach = battingCoach;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCaptainName() {
        return captainName;
    }

    public void setCaptainName(String captainName) {
        this.captainName = captainName;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getHomeCity() {
        return homeCity;
    }

    public void setHomeCity(String homeCity) {
        this.homeCity = homeCity;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public int getMatchesWon() {
        return matchesWon;
    }

    public void setMatchesWon(int matchesWon) {
        this.matchesWon = matchesWon;
    }

    public int getMatchesLost() {
        return matchesLost;
    }

    public void setMatchesLost(int matchesLost) {
        this.matchesLost = matchesLost;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String addToFile(){
        return teamName+","+captainName+","+coachName+","+bowlingCoach+","+battingCoach+","+teamOwner+","+homeCity+","+matchesPlayed+","+matchesWon+","+matchesLost+","+points;
    }
}


