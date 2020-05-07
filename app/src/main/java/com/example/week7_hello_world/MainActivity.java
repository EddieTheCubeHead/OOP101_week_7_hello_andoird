package com.example.week7_hello_world;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText file_in;
    EditText text_in;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;

        file_in = (EditText) findViewById(R.id.editText);
        text_in = (EditText) findViewById(R.id.editText2);
    }

    public void readFile(View view) {
        String data = "";
        String line = "";

        String file_name = file_in.getText().toString();

        try {
            InputStream in = context.openFileInput(file_name);

            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            while ((line = br.readLine()) != null) {
                System.out.println(line);
                data += (line + "\n");
            }
            in.close();
        } catch (FileNotFoundException e) {
            Log.e("FileNotFound", String.format("Couldn't find file while trying to read '%s'.", file_name));
            Toast.makeText(context, String.format("Couldn't find a file with name '%s'.", file_name), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e("IOException", String.format("Error in the input while trying to read '%s'.", file_name));
            Toast.makeText(context, String.format("Error reading file '%s'.", file_name), Toast.LENGTH_SHORT).show();
        } finally {
            System.out.println("readFile completed");
        }

        text_in.setText(data);
    }

    public void writeFile(View view) {
        String file_name = file_in.getText().toString();
        String content = text_in.getText().toString();

        try {
            OutputStreamWriter out = new OutputStreamWriter(context.openFileOutput(file_name, Context.MODE_PRIVATE));
            out.write(content);
            out.close();
            Toast.makeText(context, String.format("Text saved in file '%s'.", file_name), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e("IOException", String.format("Error trying to creat '%s'.", file_name));
            Toast.makeText(context, String.format("Error creating file '%s'.", file_name), Toast.LENGTH_SHORT).show();
        } finally {
            System.out.println("writeFile completed");
        }
    }
}
