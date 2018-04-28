package com.example.android.dungeonsanddragons;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.Name) EditText Name;
    @BindView(R.id.Level) EditText Level;
    @BindView(R.id.Class) EditText Class;
    @BindView(R.id.Initiative) EditText Initiative;
    @BindView(R.id.Heart) EditText Heart;
    @BindView(R.id.Proficiency) EditText Proficiency;
    @BindView(R.id.Hit_Dice) EditText Hit_Dice;
    @BindView(R.id.Speed) EditText Speed;
    @BindView(R.id.Strength) EditText Strength;
    @BindView(R.id.intelligent) EditText intelligent;
    @BindView(R.id.Dexterity) EditText Dexterity;
    @BindView(R.id.wisdom) EditText wisdom;
    @BindView(R.id.Constitution) EditText Constitution;
    @BindView(R.id.Charisma) EditText Charisma;
    @BindView(R.id.save) Button savebtn;
    @BindView(R.id.reset) Button reset;
    @BindView(R.id.nextAct) Button fab;
    SharedPreferences mPrefs;
    static DaD data=new DaD();
    Context context;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(i);
            }
        });
    }

    public void saveData(@Nullable DaD prog){
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(prog);
        prefsEditor.putString("MyObject", json);
        prefsEditor.commit();
    }
    public DaD getValues(){
        DaD dad=new DaD();
        dad.Name=Name.getText()+"";
        dad.Level= Level.getText()+"";
        dad.Class=Class.getText()+"";
        dad.Initiative=Initiative.getText()+"";
        dad.Heart=Heart.getText()+"";
        dad.Proficiency=Proficiency.getText()+"";
        dad.Hit_Dice=Hit_Dice.getText()+"";
        dad.Speed=Speed.getText()+"";
        dad.Strength=Strength.getText()+"";
        dad.intelligent=intelligent.getText()+"";
        dad.Dexterity=Dexterity.getText()+"";
        dad.wisdom=wisdom.getText()+"";
        dad.Constitution=Constitution.getText()+"";
        dad.Charisma=Charisma.getText()+"";
        return dad;
    }
    @SuppressLint("SetTextI18n")
    public void setValues(DaD dad){
        if(dad==null)
            dad=new DaD();
        Name.setText(dad.Name+"");
        Level.setText(dad.Level+"");
        Class.setText(dad.Class+"");
        Initiative.setText(dad.Initiative+"");
        Heart.setText(dad.Heart+"");
        Proficiency.setText(dad.Proficiency+"");
        Hit_Dice.setText(dad.Hit_Dice+"");
        Speed.setText(dad.Speed+"");
        Strength.setText(dad.Strength+"");
        intelligent.setText(dad.intelligent+"");
        Dexterity.setText(dad.Dexterity+"");
        wisdom.setText(dad.wisdom+"");
        Constitution.setText(dad.Constitution+"");
        Charisma.setText(dad.Charisma+"");
    }
}
