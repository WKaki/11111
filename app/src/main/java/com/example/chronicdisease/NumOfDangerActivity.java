package com.example.chronicdisease;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chronicdisease.base_elestical.BloodPressure;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class NumOfDangerActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ArrayList<String> selected = new ArrayList<>();
    //ArrayList<Integer> selected_position = new ArrayList<>();
    private TextView num_of_factor_txv;
    private Context context;
    private Button go_check_tod;
    private int num_of_danger;
    private int pressure_level;
    private Intent it = new Intent();

    public int getNum_of_danger() {
        return num_of_danger;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num_of_danger);
        final String[] mItems1 = {"男性>55岁","女性＞65岁","吸烟","血脂异常",
                "早发心血管疾病家族史","腹型肥胖或肥胖",
                "缺乏体力活动","高敏C反应蛋白≥3mg/L或C反应蛋白≥10mg/L"};
        ListView lv = (ListView) findViewById(R.id.danger_factor_lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.num_of_danger_item,mItems1);
        lv.setAdapter(adapter);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lv.setOnItemClickListener(this);
        num_of_factor_txv = findViewById(R.id.num_of_factorTxv);  //must below the setContentView
        num_of_factor_txv.setText(selected.size() + "");
        go_check_tod=findViewById(R.id.go_check_tod);
        Intent get=getIntent();
        pressure_level=get.getIntExtra("pressure_level",0);
        it.setClass(this, CheckTOActivity.class);
        go_check_tod.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0) {
                it.putExtra("pressure_level",pressure_level);
                it.putExtra("num_of_danger", num_of_danger);
                startActivity(it);
            }
        });
    }
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        num_of_factor_txv = findViewById(R.id.num_of_factorTxv);
        TextView txv = (TextView) view;
        String item = txv.getText().toString();
        if (selected.contains(item)) {
            selected.remove(item);
        } else {
            selected.add(item);
        }
        if (selected.size() > 0) {
            num_of_factor_txv.setText(selected.size() + "");
            num_of_danger=selected.size();
        }
        else {
            num_of_factor_txv.setText("0");
            num_of_danger = selected.size();
        }
    }
}
