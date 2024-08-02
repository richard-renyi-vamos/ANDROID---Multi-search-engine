package com.example.searchengineapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner searchEngineSpinner;
    private Button searchButton;

    private String[] searchEngines = {"Google", "Bing", "Yahoo", "DuckDuckGo"};
    private String[] searchUrls = {
            "https://www.google.com/search?q=",
            "https://www.bing.com/search?q=",
            "https://search.yahoo.com/search?p=",
            "https://duckduckgo.com/?q="
    };

    private String selectedUrl = searchUrls[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchEngineSpinner = findViewById(R.id.searchEngineSpinner);
        searchButton = findViewById(R.id.searchButton);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, searchEngines);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchEngineSpinner.setAdapter(adapter);

        searchEngineSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUrl = searchUrls[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText queryEditText = findViewById(R.id.queryEditText);
                String query = queryEditText.getText().toString();
                if (!query.isEmpty()) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(selectedUrl + query));
                    startActivity(browserIntent);
                }
            }
        });
    }
}
