package com.noured.topquiz.model;
/*
 *  Created by Nour Eddine ZEKAOUI - OpenClassromProject on 17/12/2020
 */
public class User {
    private String mFirstname;

    public String getFirstname() {
        return mFirstname;
    }

    public void setFirstname(String firstname) {
        mFirstname = firstname;
    }

    @Override
    public String toString() {
        return "User{" +
                "mFirstname='" + mFirstname + '\'' +
                '}';
    }
}

