package com.example.med4ukm;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class BookAppointment extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private TextView dateText;

    String[] item = {"Sick", "Dental", "Mental Health Checkup", "Body Checkup", "Vaccination"};

    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapterItems;

//    TextInputLayout bookType, , bookDate;
    TextInputLayout bookName, bookContact, bookNric;
    Button bookBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        
        autoCompleteTextView = findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, item);

        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(BookAppointment.this, "Item: " + item, Toast.LENGTH_SHORT).show();


            }
        });

//       dateText= findViewById(R.id.date_text);


        bookName = findViewById(R.id.book_full_name);
        bookContact = findViewById(R.id.book_contact);
        bookNric = findViewById(R.id.book_nric);
//        bookDate = findViewById(R.id.book_date);
//        bookType = findViewById(R.id.book_type);
        bookBtn = findViewById(R.id.book_btn);

        dateText = findViewById(R.id.date_text);
        autoCompleteTextView = findViewById(R.id.auto_complete_txt);

        findViewById(R.id.show_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
    }



    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private Boolean validateName() {
        String val = bookName.getEditText().getText().toString();

        if (val.isEmpty()) {
            bookName.setError("Field cannot be empty");
            return false;
        } else {
            bookName.setError(null);
            bookName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateContact() {
        String val = bookContact.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            bookContact.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            bookContact.setError("Contact no too long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            bookContact.setError("White Spaces are not allowed.");
            return false;
        } else {
            bookContact.setError(null);
            bookContact.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateNric() {
        String val = bookNric.getEditText().getText().toString();
        String icformat = "(([0-9]{2})(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01]))(([0-9]{2}))(([0-9]{4}))";

        if (val.isEmpty()) {
            bookNric.setError("Field cannot be empty");
            return false;
        }   else if(!val.matches(icformat) ) {
            bookNric.setError("Please insert correct NRIC!");
            return false;
        }else {
            bookNric.setError(null);
            bookNric.setErrorEnabled(false);
            return true;
        }

    }


    private Boolean validateDate() {
        String val = dateText.getText().toString();

        if (val.isEmpty()) {
            dateText.setError("Field cannot be empty");
            return false;
        } else {
            dateText.setError(null);
            return true;
        }
    }
//
    private Boolean validateType() {
        String val = autoCompleteTextView.getText().toString();

        if (val.isEmpty()) {
            autoCompleteTextView.setError("Field cannot be empty");
            return false;
        } else {
            autoCompleteTextView.setError(null);
            return true;
        }
    }

    public void bookAppointment(View view) {
        String datePass = dateText.getText().toString();
        String typePass = autoCompleteTextView.getText().toString();
        String bookname = bookName.getEditText().getText().toString();
        if (!validateName() || !validateContact() || !validateNric() || !validateDate() || !validateType()) {
            return;
        }
        Intent intent = new Intent(getApplicationContext(), BookSuccess.class);
        intent.putExtra("name", bookname);
        intent.putExtra("date", datePass);
        intent.putExtra("type", typePass);
        startActivity(intent);

        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance(); //call data from fb
                reference = rootNode.getReference("bookappointment");


                String contact = bookContact.getEditText().getText().toString();
                String nric = bookNric.getEditText().getText().toString();


                String datepicker = dateText.getText().toString();
                String autotype = autoCompleteTextView.getText().toString();


                UserHelperClass2 helperClass = new UserHelperClass2(bookname, contact, nric, datepicker, autotype);
                reference.child(bookname).setValue(helperClass);


            }
        });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayofMonth) {
        int months = month + 1;
        String date = "Month/Day/Year: " + months + "/" + dayofMonth + "/" + year;
        dateText.setText(date);
    }



}