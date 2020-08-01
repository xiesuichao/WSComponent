package com.wanshare.wscomponent.datamanager;

import java.io.Serializable;

/**
 * Created by xiesuichao on 2018/8/2.
 */

public class User implements Serializable{

    private String name;
    private String age;
    private int num;
    private boolean boy;
    private double tall;
    private float feet;
    private short hand;
    private long leg;

    public User() {
    }

    public User(String name, String age, int num, boolean boy, double tall, float feet, short hand, long leg) {
        this.name = name;
        this.age = age;
        this.num = num;
        this.boy = boy;
        this.tall = tall;
        this.feet = feet;
        this.hand = hand;
        this.leg = leg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isBoy() {
        return boy;
    }

    public void setBoy(boolean boy) {
        this.boy = boy;
    }

    public double getTall() {
        return tall;
    }

    public void setTall(double tall) {
        this.tall = tall;
    }

    public float getFeet() {
        return feet;
    }

    public void setFeet(float feet) {
        this.feet = feet;
    }

    public short getHand() {
        return hand;
    }

    public void setHand(short hand) {
        this.hand = hand;
    }

    public long getLeg() {
        return leg;
    }

    public void setLeg(long leg) {
        this.leg = leg;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", num=" + num +
                ", boy=" + boy +
                ", tall=" + tall +
                ", feet=" + feet +
                ", hand=" + hand +
                ", leg=" + leg +
                '}';
    }
}
