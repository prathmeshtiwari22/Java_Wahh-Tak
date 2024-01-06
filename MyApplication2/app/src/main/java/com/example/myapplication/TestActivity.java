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

public class TestActivity extends AppCompatActivity {
    private String[][] packages=
            {
                    {"Package 1: FULL BODY CHECKUP","","","","999"},
                    {"Package 2: BLOOD GLUCOSE FASTING","","","","499"},
                    {"Package 3: REGULAR CHECKUP","","","","599"},
                    {"Package 4: THYROID CHECKUP","","","","699"},
                    {"Package 5: IMMUNITY CHECKUP","","","","799"}
            };
    private String[] package_details = {
            "Blood Glucose Fasting\n",
            "Complete Blood Count (CBC)\n"+
                    "Lipid Profile\n"+
                    "Liver Function Tests (LFTs)\n"+
                    "Kidney Function Tests (KFTs)\n",
            "Thyroid Function Tests (TFTs)\n"+
                    "Hemoglobin A1c (HbA1c)\n"+
                    "C-Reactive Protein (CRP)"+
                    "Serum Electrolytes"+
                    "Urine Analysis",
            "Vitamin D Level",
            "Vitamin B12 Level\n",
            "Prothrombin Time (PT/INR)\n",
            "Chest X-ray\n",
            "Electrocardiogram (ECG/EKG)\n"+
                    "Bone Density Scan (DEXA)\n"+
                    "Hepatitis B Surface Antigen (HBsAg)",
            "HIV Antibody Test\n",
            "Thyroid Ultrasound"
    };
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnGotoCart,btnBack;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        btnGotoCart =findViewById(R.id.buttonMOAdd);
        btnBack =findViewById(R.id.buttonMOBACK);
        listView=findViewById(R.id.listViewLT);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TestActivity.this,HomeActivity.class));
            }
        });

        list = new ArrayList();
        for(int i=0;i<package_details.length;i++){
            item =new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total Cost:"+packages[i][4]+"/-");
            list.add(item);
        }
        sa =new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        ListView lst=findViewById(R.id.listViewLT);
        lst.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it= new Intent(TestActivity.this,LabTestDetailsActivity.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",package_details[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);
            }
        });

    }
}