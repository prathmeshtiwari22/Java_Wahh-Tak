package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][]  doctor_details1= {
            {"Doctor Name : Prathmesh Tiwari","Hospital Address : Pimpri"," Exp : 4yrs"," Mobile No:8830085862","600"},
            {"Doctor Name : Abhijeet Pandey","Hospital Address : Pune"," Exp : 5yrs"," Mobile No:8890085862","900"},
            {"Doctor Name : Advait ","Hospital Address : Patna"," Exp : 4yrs"," Mobile No:8870085862","300"},
            {"Doctor Name : Sharvesh Singh","Hospital Address : Delhi"," Exp : 14yrs"," Mobile No:8850085862","400"},
            {"Doctor Name : Raut Patil","Hospital Address : Saki Naka"," Exp : 7yrs"," Mobile No:8820085862","200"},
            {"Doctor Name : Prayas Pandey","Hospital Address : Vasai"," Exp : 1yrs"," Mobile No:8810085862","800"}

    };
    private String[][] doctor_details2 = {
            {"Doctor Name : John Doe", "Hospital Address : New York", " Exp : 10yrs", " Mobile No:1234567890", "700"},
            {"Doctor Name : Jane Smith", "Hospital Address : Los Angeles", " Exp : 8yrs", " Mobile No:9876543210", "550"},
            {"Doctor Name : David Johnson", "Hospital Address : Chicago", " Exp : 12yrs", " Mobile No:5551234567", "900"},
            {"Doctor Name : Emily Davis", "Hospital Address : Houston", " Exp : 6yrs", " Mobile No:3337894561", "450"},
            {"Doctor Name : Michael Wilson", "Hospital Address : Miami", " Exp : 15yrs", " Mobile No:7778889999", "800"},
            {"Doctor Name : Sarah Brown", "Hospital Address : San Francisco", " Exp : 9yrs", " Mobile No:1112223333", "650"}
    };
    private String[][] doctor_details3 = {
            {"Doctor Name : Alok Verma","Hospital Address : Mumbai"," Exp : 3yrs"," Mobile No:8765432109","750"},
            {"Doctor Name : Priya Sharma","Hospital Address : Chennai"," Exp : 6yrs"," Mobile No:7890123456","1200"},
            {"Doctor Name : Rajesh Kumar","Hospital Address : Bangalore"," Exp : 8yrs"," Mobile No:9876543210","1600"},
            {"Doctor Name : Neha Gupta","Hospital Address : Kolkata"," Exp : 2yrs"," Mobile No:8765432109","500"},
            {"Doctor Name : Anil Patel","Hospital Address : Ahmedabad"," Exp : 5yrs"," Mobile No:7890123456","1000"},
            {"Doctor Name : Poonam Singh","Hospital Address : Jaipur"," Exp : 7yrs"," Mobile No:9876543210","1400"}
    };
    private String[][] doctor_details4 = {
            {"Doctor Name : Siddharth Jain","Hospital Address : Hyderabad"," Exp : 9yrs"," Mobile No:8765432109","1800"},
            {"Doctor Name : Ananya Sharma","Hospital Address : Chennai"," Exp : 7yrs"," Mobile No:7890123456","1400"},
            {"Doctor Name : Rohit Mehta","Hospital Address : Bangalore"," Exp : 12yrs"," Mobile No:9876543210","2400"},
            {"Doctor Name : Sneha Gupta","Hospital Address : Kolkata"," Exp : 6yrs"," Mobile No:8765432109","1200"},
            {"Doctor Name : Sunil Patel","Hospital Address : Ahmedabad"," Exp : 15yrs"," Mobile No:7890123456","3000"},
            {"Doctor Name : Radhika Singh","Hospital Address : Jaipur"," Exp : 10yrs"," Mobile No:9876543210","2000"}
    };
    private String[][] doctor_details5 = {
            {"Doctor Name : Priyanka Sharma","Hospital Address : Mumbai"," Exp : 4yrs"," Mobile No:8765432109","1200"},
            {"Doctor Name : Rahul Verma","Hospital Address : Chennai"," Exp : 8yrs"," Mobile No:7890123456","2400"},
            {"Doctor Name : Sunita Gupta","Hospital Address : Bangalore"," Exp : 6yrs"," Mobile No:9876543210","1800"},
            {"Doctor Name : Manish Patel","Hospital Address : Kolkata"," Exp : 10yrs"," Mobile No:8765432109","3000"},
            {"Doctor Name : Shweta Singh","Hospital Address : Ahmedabad"," Exp : 12yrs"," Mobile No:7890123456","3600"},
            {"Doctor Name : Karan Kumar","Hospital Address : Jaipur"," Exp : 5yrs"," Mobile No:9876543210","1500"}
    };



    TextView tv;
    Button btn;
    String[][] doctor_details = {};
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textViewDDDTITLE);
        btn = findViewById(R.id.buttonMOAdd); // Initialize the button here

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physician")==0)
            doctor_details=doctor_details1;
        else if (title.compareTo("Dietician")==0) {
            doctor_details=doctor_details2;
        }
        else if (title.compareTo("Dentist")==0) {
            doctor_details=doctor_details3;
        }
        else if (title.compareTo("Surgeon")==0) {
            doctor_details=doctor_details4;
        }
        else{
            doctor_details=doctor_details5;
        }


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });

        list = new ArrayList<>();
        for(int i=0;i<doctor_details.length;i++){
            item =new HashMap<String,String>();
            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5","Cons Fee:"+doctor_details[i][4]+"/-");
            list.add(item);
        }
        sa =new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
        );
        ListView lst=findViewById(R.id.listViewLT);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it =new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text1",doctor_details[i][0]);
                it.putExtra("text2",doctor_details[i][1]);
                it.putExtra("text3",doctor_details[i][2]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);
            }
        });

    }
}
