package com.example.android.dungeonsanddragons;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {
    @BindView(R.id.Race) EditText Race;
    @BindView(R.id.Background) EditText Background;
    @BindView(R.id.Alignment) EditText Alignment;
    @BindView(R.id.Experience) EditText Experience;
    @BindView(R.id.Features) EditText Features;
    @BindView(R.id.Armor) Spinner Armor;
    @BindView(R.id.Weapon) Spinner Weapon;
    @BindView(R.id.Tool) Spinner Tool;
    @BindView(R.id.Language) Spinner Language;
    @BindView(R.id. Ideas) EditText  Ideas;
    @BindView(R.id.Bonds) EditText Bonds;
    @BindView(R.id.Flaws) EditText Flaws;
    @BindView(R.id.Notes) EditText Notes;
    @BindView(R.id.save2) Button savebtn;
    @BindView(R.id.reset2) Button reset;
    @BindView(R.id.gotoA) Button nextAct;
    public void fillSpinners(){
        /*
            Armor
         */
        ArrayList<String> armor=new ArrayList<>();
        armor.add("xxxx");
        armor.add("xxxx");
        armor.add("xxxx");
        armor.add("xxxx");
        armor.add("xxxx");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, armor);
        Armor.setAdapter(dataAdapter);

        ArrayList<String> weapon=new ArrayList<>();
        weapon.add("yyyy");
        weapon.add("yyyy");
        weapon.add("yyyy");
        weapon.add("yyyy");
        weapon.add("yyyy");
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, weapon);
        Weapon.setAdapter(dataAdapter2);

        ArrayList<String> tools=new ArrayList<>();
        tools.add("zzzz");
        tools.add("zzzz");
        tools.add("zzzz");
        tools.add("zzzz");
        tools.add("zzzz");
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tools);
        Tool.setAdapter(dataAdapter3);

        ArrayList<String> langugues=new ArrayList<>();
        langugues.add("wwww");
        langugues.add("wwww");
        langugues.add("wwww");
        langugues.add("wwww");
        langugues.add("wwww");
        ArrayAdapter<String> dataAdapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, langugues);
        Language.setAdapter(dataAdapter4);

    }

    public DaD getValues(){
        DaD dad=new DaD();
        dad.Race=Race.getText()+"";
        dad.Background= Background.getText()+"";
        dad.Alignment=Alignment.getText()+"";
        dad.Experience=Experience.getText()+"";
        dad.Features=Features.getText()+"";
        dad.Armor=Armor.getSelectedItem()+"";
        dad.Weapon=Weapon.getSelectedItem()+"";
        dad.Tool=Tool.getSelectedItem()+"";
        dad.Language=Language.getSelectedItem()+"";
        dad.Ideas=Ideas.getText()+"";
        dad.Bonds=Bonds.getText()+"";
        dad.Flaws=Flaws.getText()+"";
        dad.Notes=Notes.getText()+"";
        return dad;
    }
    @SuppressLint("SetTextI18n")
    public void setValues(DaD dad){
        if(dad==null)
            dad=new DaD();
        Race.setText(dad.Race+"");
        Background.setText(dad.Background+"");
        Alignment.setText(dad.Alignment+"");
        Experience.setText(dad.Experience+"");
        Features.setText(dad.Features+"");
        Ideas.setText(dad.Ideas+"");
        Bonds.setText(dad.Bonds+"");
        Flaws.setText(dad.Flaws+"");
        Notes.setText(dad.Notes+"");
    }
    public void saveData(@Nullable DaD prog){
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(prog);
        prefsEditor.putString("MyObject", json);
        prefsEditor.commit();
    }

    SharedPreferences mPrefs;
    DaD data=new DaD();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        fillSpinners();
        context=this;
        mPrefs = getPreferences(MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        data = gson.fromJson(json, DaD.class);
        if(data ==null)
            data=new DaD();
        setValues(data);
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(context);
                }
                builder.setTitle("Save Progress")
                        .setMessage("Are you sure you want to save your progress?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                data=getValues();
                                saveData(data);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(context);
                }
                builder.setTitle("Delete Progress")
                        .setMessage("Are you sure you want to delete your progress?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                setValues(null);
                                saveData(null);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    nextAct.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent i = new Intent(Main2Activity.this,MainActivity.class);
            startActivity(i);
        }
    });
    }
}
