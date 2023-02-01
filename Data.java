package com.ZiyanGuo;

public class Data {
    private double Pregnancies;
    private double Glucose;
    private double BloodPressure;
    private double SkinThickness;
    private double Insulin;
    private double BMI;
    private double DiabetesPedigreeFunction;
    private double Age;
    private double Outcome;
    private double Distance;//曼哈顿
    private double siistance;//欧式
    private double mkDistance;//mk

    public Data(double pregnancies, double glucose, double bloodPressure, double skinThickness, double insulin, double BMI, double diabetesPedigreeFunction, double age, double outcome) {
        Pregnancies = pregnancies;
        Glucose = glucose;
        BloodPressure = bloodPressure;
        SkinThickness = skinThickness;
        Insulin = insulin;
        this.BMI = BMI;
        DiabetesPedigreeFunction = diabetesPedigreeFunction;
        Age = age;
        Outcome = outcome;
    }

    public double getSiistance() {
        return siistance;
    }

    public void setSiistance(double siistance) {
        this.siistance = siistance;
    }

    public double getMkDistance() {
        return mkDistance;
    }

    public void setMkDistance(double mkDistance) {
        this.mkDistance = mkDistance;
    }

    public double getDistance() {
        return Distance;
    }

    public void setDistance(double distance) {
        Distance = distance;
    }

    public double getPregnancies() {
        return Pregnancies;
    }

    public void setPregnancies(double pregnancies) {
        Pregnancies = pregnancies;
    }

    public double getGlucose() {
        return Glucose;
    }

    public void setGlucose(double glucose) {
        Glucose = glucose;
    }

    public double getBloodPressure() {
        return BloodPressure;
    }

    public void setBloodPressure(double bloodPressure) {
        BloodPressure = bloodPressure;
    }

    public double getSkinThickness() {
        return SkinThickness;
    }

    public void setSkinThickness(double skinThickness) {
        SkinThickness = skinThickness;
    }

    public double getInsulin() {
        return Insulin;
    }

    public void setInsulin(double insulin) {
        Insulin = insulin;
    }

    public double getBMI() {
        return BMI;
    }

    public void setBMI(double BMI) {
        this.BMI = BMI;
    }

    public double getDiabetesPedigreeFunction() {
        return DiabetesPedigreeFunction;
    }

    public void setDiabetesPedigreeFunction(double diabetesPedigreeFunction) {
        DiabetesPedigreeFunction = diabetesPedigreeFunction;
    }

    public double getAge() {
        return Age;
    }

    public void setAge(double age) {
        Age = age;
    }

    public double getOutcome() {
        return Outcome;
    }

    public void setOutcome(double outcome) {
        Outcome = outcome;
    }

    public double [] getall() {
        double[] ans =  {this.Age,this.BMI,this.BloodPressure,this.DiabetesPedigreeFunction,this.Glucose,this.Insulin,this.Pregnancies,this.SkinThickness};
        return ans;
    }
}
