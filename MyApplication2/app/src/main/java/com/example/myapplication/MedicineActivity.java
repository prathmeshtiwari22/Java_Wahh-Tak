package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MedicineActivity extends AppCompatActivity {
    Button btnBack, btnGotoCart;
    private String[][] medicinePackages = {
            {"Medicine 1: Aspirin", "Painkiller", "10 tablets", "5.99"},
            {"Medicine 2: Amoxicillin", "Antibiotic", "20 capsules", "12.99"},
            {"Medicine 3: Ibuprofen", "Painkiller", "30 tablets", "7.49"},
            {"Medicine 4: Loratadine", "Antihistamine", "15 tablets", "9.99"},
            {"Medicine 5: Omeprazole", "Antacid", "14 capsules", "6.99"}
    };

    private String[] medicine_details = {
            "Aspirin\n" +
                    "Type: Painkiller\n" +
                    "Quantity: 10 tablets\n" +
                    "Price: $5.99\n",
            "Amoxicillin\n" +
                    "Type: Antibiotic\n" +
                    "Quantity: 20 capsules\n" +
                    "Price: $12.99\n",
            "Ibuprofen\n" +
                    "Type: Painkiller\n" +
                    "Quantity: 30 tablets\n" +
                    "Price: $7.49\n",
            "Loratadine\n" +
                    "Type: Antihistamine\n" +
                    "Quantity: 15 tablets\n" +
                    "Price: $9.99\n",
            "Omeprazole\n" +
                    "Type: Antacid\n" +
                    "Quantity: 14 capsules\n" +
                    "Price: $6.99\n"
    };

    ArrayList<HashMap<String, String>> list;
    SimpleAdapter sa;
    ListView lstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        btnBack = findViewById(R.id.buttonMOBACK);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MedicineActivity.this, MedicineActivity.class));
            }
        });

        list = new ArrayList<>();
        for (int i = 0; i < medicine_details.length; i++) {
            HashMap<String, String> item = new HashMap<>();
            item.put("line1", medicinePackages[i][0]);
            item.put("line2", medicinePackages[i][1]);
            item.put("line3", medicinePackages[i][2]);
            item.put("line4", medicinePackages[i][3]);
            item.put("line5", "Total Cost: " + medicinePackages[i][4] + "/-");
            list.add(item);
        }

        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});

        lstView = findViewById(R.id.listViewLT);
        lstView.setAdapter(sa);

        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(MedicineActivity.this, MedicineDetailksActivity.class);
                it.putExtra("text1", medicinePackages[i][0]);
                it.putExtra("text2", medicine_details[i]);
                it.putExtra("text3", medicinePackages[i][4]);
                startActivity(it);
            }
        });
    }
}
