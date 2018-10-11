package com.example.jaha0025.cst8334_project;

import android.content.Context;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ActOfKindness {
    int aId;
    String aTitle, aDescription, aQuestion;
    public ActOfKindness(){ }

    public ActOfKindness(String title, String description, String question){
        this.aTitle = title;
        this.aDescription = description;
        this.aQuestion = question;
    }

    public static ArrayList<ActOfKindness> insertActs(Context context){
        ArrayList<ActOfKindness> acts = new ArrayList<ActOfKindness>();
        String title, description, question;
        InputStream is1 = context.getResources().openRawResource(R.raw.aok_titles);
        InputStream is2 = context.getResources().openRawResource(R.raw.aok_descriptions);
        InputStream is3 = context.getResources().openRawResource(R.raw.aok_questions);
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(is1));
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(is2));
        BufferedReader reader3 = new BufferedReader(new InputStreamReader(is3));

        if (is1 != null && is2 != null && is3 != null) {
            try{
                while(((title = reader1.readLine()) != null &&
                        (description = reader2.readLine()) != null) &&
                        (question = reader3.readLine()) != null)
                {
                    acts.add(new ActOfKindness(title, description, question));
                }
                is1.close();
                is2.close();
                is3.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return acts;

    }
}
