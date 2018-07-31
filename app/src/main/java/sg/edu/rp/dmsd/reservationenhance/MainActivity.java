package sg.edu.rp.dmsd.reservationenhance;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText ntext;
    EditText numtext;
    EditText stext;
    CheckBox cbsmoking;
    CheckBox cbnonsmoking;
    Button btnreserve;
    Button btnreset;

    EditText etDay;
    EditText etTime;
    TextView tvDay;
    TextView tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ntext = findViewById(R.id.editTextName);
        numtext = findViewById(R.id.editTextNum);
        stext = findViewById(R.id.editTextSize);
        cbsmoking = findViewById(R.id.checkBoxSmoking);
        cbnonsmoking = findViewById(R.id.checkBoxNsmoking);
        btnreserve = findViewById(R.id.buttonSubmit);
        btnreset = findViewById(R.id.buttonReset);

        etDay = findViewById(R.id.editTextDay);
        etTime = findViewById(R.id.editTextTime);
        tvDay = findViewById(R.id.textViewDay);
        tvTime = findViewById(R.id.textViewTime);

        cbsmoking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cbsmoking.isChecked()){
                    Toast.makeText(getApplicationContext(), "Select Smoking Area", Toast.LENGTH_SHORT).show();
                }else if (! cbsmoking.isChecked()){
                    cbsmoking.setChecked(false);
                }
            }
        });


        cbnonsmoking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cbnonsmoking.isChecked()){
                    Toast.makeText(getApplicationContext(), "Select Non-smoking Area", Toast.LENGTH_SHORT).show();
                }else if(! cbnonsmoking.isChecked()){
                    cbnonsmoking.setChecked(false);
                }
            }
        });

        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ntext.setText("");
                numtext.setText("");
                stext.setText("");
                cbsmoking.setChecked(false);
                cbnonsmoking.setChecked(false);
                etDay.setText("");
                etTime.setText("");
            }
        });

        btnreserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);

                String name = ntext.getText().toString();
                String number = numtext.getText().toString();
                String size = stext.getText().toString();
                String day = etDay.getText().toString();
                String time = etTime.getText().toString();

                if(cbsmoking.isChecked()) {
                    myBuilder.setMessage("New Reservation" + "\n" + "Name: " + name  + "\n" + "Smoking:Yes " + "\n" + "Size: " + size  + "\n" + "Date: " + day  + "\n" + "Time: " + time  + "\n");
                    myBuilder.setCancelable(false);
                    myBuilder.setPositiveButton("Reserve",null);
                    myBuilder.setNegativeButton("Cancel",null);
                }else{
                    myBuilder.setMessage("New Reservation" + "\n" + "Name: " + name  + "\n" + "Smoking:No " + "\n" + "Size: " + size  + "\n" + "Date: " + day  + "\n" + "Time: " + time  + "\n");
                    myBuilder.setCancelable(false);
                    myBuilder.setPositiveButton("Reserve",null);
                    myBuilder.setNegativeButton("Cancel",null);
                }

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

       etDay.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                   @Override
                   public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                       tvDay.setText("Date: " + dayOfMonth + "/" + (monthOfYear+1) + "/" + year);
                   }
               };
               Calendar c = Calendar.getInstance();
               int year = c.get(Calendar.YEAR);
               int month = c.get(Calendar.MONTH);
               int day =  c.get(Calendar.DAY_OF_MONTH);
               DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this,myDateListener,year,month,day);
               myDateDialog.show();
           }
       });

      etTime.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                  @Override
                  public void onTimeSet(TimePicker timePicker, int hourofDay, int minute) {
                      tvTime.setText("Time: " + hourofDay + ":" + minute);
                  }
              };
              Calendar c = Calendar.getInstance();
              int minute = c.get(Calendar.HOUR_OF_DAY);
              int hour = c.get(Calendar.MINUTE);
              TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this,myTimeListener,hour,minute,true);
              myTimeDialog.show();
          }
      });

    }
}
