package com.j2core.sts.webcrawler.taskservice.model.dto;

/*
 * Created by Sotnyk Tetiana.
 */

/**
 * The class container for keep word's information
 */
public class WordEntity {

    private String word;
    private int amount;

    public WordEntity(){

    }

    public WordEntity(String word, int amount) {
        this.word = word;
        this.amount = amount;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
