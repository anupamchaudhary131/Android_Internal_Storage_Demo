package com.example.android_internal_storage_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    EditText edtFileName, edtDataValues;
    Button btnSave, btnRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtFileName = (EditText) findViewById(R.id.edtFileName);
        edtDataValues = (EditText) findViewById(R.id.edtDataValues);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnRead = (Button) findViewById(R.id.btnRead);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fileName = edtFileName.getText().toString();
                String data = edtDataValues.getText().toString();

                FileOutputStream fos;
                try {
                    fos = openFileOutput(fileName, Context.MODE_PRIVATE);
                    fos.write(data.getBytes());
                    fos.close();

                    Toast.makeText(MainActivity.this, "The Data and file Name is Saved", Toast.LENGTH_LONG).show();
                }
                catch(FileNotFoundException e){
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


         btnRead.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 String fileName = edtFileName.getText().toString();
                 StringBuffer buffer = new StringBuffer();

                 try{
                     BufferedReader inputReader = new BufferedReader(new InputStreamReader(openFileInput(fileName)));
                     String inputString;

                     while((inputString = inputReader.readLine()) != null){
                         buffer.append(inputString + "\n");

                     }
                 }
                 catch (FileNotFoundException e) {
                     e.printStackTrace();
                 }
                 catch (IOException e) {
                     e.printStackTrace();
                 }

                 Toast.makeText(MainActivity.this, buffer.toString(), Toast.LENGTH_SHORT).show();
             }
         });
    }
}