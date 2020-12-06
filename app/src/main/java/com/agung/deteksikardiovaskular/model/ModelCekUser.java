package com.agung.deteksikardiovaskular.model;

public class ModelCekUser {
    private String name, dateBirth, dateIssue, chestPT, restingEM, exerciseIA, sTSlope, thalassemia;
    private int restingBP, fastingBS, cholesterol, maxHeartRate, majorVessels;
    private float sTDepression;
    private String result = null;

    public ModelCekUser(String name, String dateBirth, String dateIssue, String chestPT, String restingEM, String exerciseIA, String sTSlope, String thalassemia, int restingBP, int fastingBS, int cholesterol, int maxHeartRate, int majorVessels, float sTDepression, String result) {
        this.name = name;
        this.dateBirth = dateBirth;
        this.dateIssue = dateIssue;
        this.chestPT = chestPT;
        this.restingEM = restingEM;
        this.exerciseIA = exerciseIA;
        this.sTSlope = sTSlope;
        this.thalassemia = thalassemia;
        this.restingBP = restingBP;
        this.fastingBS = fastingBS;
        this.cholesterol = cholesterol;
        this.maxHeartRate = maxHeartRate;
        this.majorVessels = majorVessels;
        this.sTDepression = sTDepression;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getDateIssue() {
        return dateIssue;
    }

    public void setDateIssue(String dateIssue) {
        this.dateIssue = dateIssue;
    }

    public String getChestPT() {
        return chestPT;
    }

    public void setChestPT(String chestPT) {
        this.chestPT = chestPT;
    }

    public String getRestingEM() {
        return restingEM;
    }

    public void setRestingEM(String restingEM) {
        this.restingEM = restingEM;
    }

    public String getExerciseIA() {
        return exerciseIA;
    }

    public void setExerciseIA(String exerciseIA) {
        this.exerciseIA = exerciseIA;
    }

    public String getsTSlope() {
        return sTSlope;
    }

    public void setsTSlope(String sTSlope) {
        this.sTSlope = sTSlope;
    }

    public String getThalassemia() {
        return thalassemia;
    }

    public void setThalassemia(String thalassemia) {
        this.thalassemia = thalassemia;
    }

    public int getRestingBP() {
        return restingBP;
    }

    public void setRestingBP(int restingBP) {
        this.restingBP = restingBP;
    }

    public int getFastingBS() {
        return fastingBS;
    }

    public void setFastingBS(int fastingBS) {
        this.fastingBS = fastingBS;
    }

    public int getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(int cholesterol) {
        this.cholesterol = cholesterol;
    }

    public int getMaxHeartRate() {
        return maxHeartRate;
    }

    public void setMaxHeartRate(int maxHeartRate) {
        this.maxHeartRate = maxHeartRate;
    }

    public int getMajorVessels() {
        return majorVessels;
    }

    public void setMajorVessels(int majorVessels) {
        this.majorVessels = majorVessels;
    }

    public float getsTDepression() {
        return sTDepression;
    }

    public void setsTDepression(float sTDepression) {
        this.sTDepression = sTDepression;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
