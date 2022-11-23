package com.example.myapplication.item;

public class Medicine {
    String NameScience;
    String NameNormal;
    String Information;
    String Uses;
    String Dosage;

    public Medicine(){};

    public Medicine(String nameScience, String nameNormal, String information, String uses, String dosage) {
        NameScience = nameScience;
        NameNormal = nameNormal;
        Information = information;
        Uses = uses;
        this.Dosage = dosage;
    }

    public String getNameScience() {
        return NameScience;
    }

    public void setNameScience(String nameScience) {
        NameScience = nameScience;
    }

    public String getNameNormal() {
        return NameNormal;
    }

    public void setNameNormal(String nameNormal) {
        NameNormal = nameNormal;
    }

    public String getInformation() {
        return Information;
    }

    public void setInformation(String information) {
        Information = information;
    }

    public String getUses() {
        return Uses;
    }

    public void setUses(String uses) {
        Uses = uses;
    }

    public String getDosage() {
        return Dosage;
    }

    public void setDosage(String dosage) {
        this.Dosage = Dosage;
    }
}
