package com.example.pizza;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    String[] data = {"Банковская карта", "QIWI кошелек", "Яндекс-деньги", "Наличные в терминале", "PayPal"};

    int sm25 = 250;
    int sm30 = 300;
    int sm40 = 400;

    int current_size_cost = 0;

    int mushrooms = 20;
    int ham = 30;
    int salami = 35;
    int cheeze4 = 55;

    int current_add_cost = 0;

    int current_full_cost = 0;
    String current_size_chosen = "";
    String howTopay = "";

    TextView finals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        finals = (TextView) findViewById(R.id.final_s);


        forPizzaPayment();
        forPizzaSize();
        forPizzaMail();
        forPizzaAdditionComponents();


    }

    private void forPizzaSize() {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.sm25:

                        if (current_full_cost == 0) {
                            current_full_cost += sm25;
                            finals.setText(String.valueOf(current_full_cost));
                        }
                        else {
                            current_full_cost = current_full_cost - current_size_cost + sm25;
                            finals.setText(String.valueOf(current_full_cost));
                        }
                        current_size_cost=sm25;
                        current_size_chosen = "25 см";
                        break;
                    case R.id.sm30:

                        if (current_full_cost == 0) {
                            current_full_cost += sm30;
                            finals.setText(String.valueOf(current_full_cost));
                        }
                        else {
                            current_full_cost = current_full_cost - current_size_cost + sm30;
                            finals.setText(String.valueOf(current_full_cost));
                        }
                        current_size_cost=sm30;
                        current_size_chosen = "30 см";
                        break;
                    case R.id.sm40:

                        if (current_full_cost == 0) {
                            current_full_cost += sm40;
                            finals.setText(String.valueOf(current_full_cost));
                        }
                        else {
                            current_full_cost = current_full_cost - current_size_cost + sm40;
                            finals.setText(String.valueOf(current_full_cost));
                        }
                        current_size_cost=sm40;
                        current_size_chosen = "40 см";
                        break;
                }

            }
        });
    }

    private void forPizzaMail() {
        Switch cb = findViewById(R.id.email_cb);

        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                View em_v = findViewById(R.id.email_v);
                if (isChecked) {
                    em_v.setVisibility(View.VISIBLE);
                } else {
                    em_v.setVisibility(View.GONE);
                }
            }
        });

    }

    private void forPizzaPayment() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setSelection(2);
        howTopay = spinner.getSelectedItem().toString();
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();

                View layout_container = findViewById(R.id.toBecomeVisible);
                if(data[position].contentEquals("Банковская карта")){
                    layout_container.setVisibility(View.VISIBLE);
                }
                else {
                    layout_container.setVisibility(View.GONE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

    }

    private CheckBox mushrooms_p, ham_p, salami_p, cheeze4_p;
    HashSet<String> adds;

    private void forPizzaAdditionComponents() {

        adds = new HashSet<>();

        mushrooms_p = (CheckBox) findViewById(R.id.mushrooms_c);
        ham_p = (CheckBox) findViewById(R.id.ham_c);
        salami_p = (CheckBox) findViewById(R.id.salami_c);
        cheeze4_p = (CheckBox) findViewById(R.id.cheeze4_c);

        mushrooms_p.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mushrooms_p.isChecked()) {
                    current_full_cost += mushrooms;
                    finals.setText(String.valueOf(current_full_cost));
                    adds.add("Грибы");
                }
                else {
                    current_full_cost -= mushrooms;
                    finals.setText(String.valueOf(current_full_cost));
                    adds.remove("Грибы");
                }
                current_add_cost = mushrooms;

            }
        });
        ham_p.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (ham_p.isChecked()) {
                    current_full_cost += ham;
                    finals.setText(String.valueOf(current_full_cost));
                    adds.add("Ветчина");
                }
                else {
                    current_full_cost -= ham;
                    finals.setText(String.valueOf(current_full_cost));
                    adds.remove("Ветчина");
                }
                current_add_cost = ham;

            }
        });

        salami_p.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (salami_p.isChecked()) {
                    current_full_cost += salami;
                    finals.setText(String.valueOf(current_full_cost));
                    adds.add("Салями");
                }
                else {
                    current_full_cost -= salami;
                    finals.setText(String.valueOf(current_full_cost));
                    adds.remove("Салями");
                }
                current_add_cost = salami;

            }
        });

        cheeze4_p.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cheeze4_p.isChecked()) {
                    current_full_cost += cheeze4;
                    finals.setText(String.valueOf(current_full_cost));
                    adds.add("4 сыра");
                }
                else {
                    current_full_cost -= cheeze4;
                    finals.setText(String.valueOf(current_full_cost));
                    adds.remove("4 сыра");
                }
                current_add_cost = cheeze4;

            }
        });
    }

    EditText name_person, phone_person, email_person;
    Button end_b;

    public void iDidIt(View view) {

        name_person = (EditText) findViewById(R.id.name_t);
        String name_p = name_person.getText().toString();
        if (name_p.matches("")) {
            Toast.makeText(this, "Введите имя", Toast.LENGTH_SHORT).show();
            return;
        }

        phone_person = (EditText) findViewById(R.id.phone_t);
        String phone_p = phone_person.getText().toString();
        if (phone_p.matches("")) {
            Toast.makeText(this, "Введите номер тлф", Toast.LENGTH_SHORT).show();
            return;
        }

        email_person = (EditText) findViewById(R.id.email_txt);
        String email_p = email_person.getText().toString();



        int end_price = current_full_cost;

        String[] s = adds.toArray(new String[adds.size()]);
        String additionals = Arrays.toString(s);
        additionals = additionals.substring(1, additionals.length()-1);

        Toast.makeText(getBaseContext(), "Заказ для " + name_p + ", " + phone_p + "\r\nПицца : " + current_size_chosen + "\r\nВнутренности : " + additionals + "\r\nНа сумму " + end_price + " руб\r\nСпособ оплаты - " + howTopay, Toast.LENGTH_SHORT).show();







    }
}