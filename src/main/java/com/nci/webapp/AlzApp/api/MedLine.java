package com.nci.webapp.AlzApp.api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MedLine {

    public void apiCall() throws IOException {
        //Reading JSON from file system
        BufferedReader br=new BufferedReader(new FileReader("https://medlineplus.gov/download/genetics/condition/alzheimer-disease.json"));
        String line;
        StringBuilder sbuilderObj = new StringBuilder();
        while((line=br.readLine()) !=null){
            sbuilderObj.append(line);
        }
        System.out.println("Original Json :: "+sbuilderObj.toString());
        //Using JSONObject
        JSONObject jsonObj = new JSONObject(sbuilderObj.toString());

        String text = jsonObj.getJSONObject("text-list").getString("text");
        String url = jsonObj.getJSONObject("ghr_page").getString("ghr_page");
//        String age = jsonObj.getJSONObject("empInfo").getString("age");
        System.out.println("###### Emp Info ############");
        System.out.println("Name     : "+text);
        System.out.println("Position : "+url);
//        System.out.println("Age      : "+age);


        List<String> synonyms = new ArrayList<>();
        //Fetching nested Json using JSONArray
        JSONArray arrObj = jsonObj.getJSONArray("synonym-list");
        for (int i = 0; i < arrObj .length(); i++) {
            String synonym = arrObj .getJSONObject(i).getString("synonym");
            synonyms.add(synonym);
            System.out.println("###### Emp Skills (nested) ###########");
            System.out.println(synonyms.toString());
        }
    }
}
