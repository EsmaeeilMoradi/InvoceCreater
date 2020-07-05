package com.e_moradi.testproject.view.customer_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.e_moradi.testproject.R;
import com.e_moradi.testproject.model.test_db.DatabaseManager;
import com.e_moradi.testproject.model.test_db.Person;

public class TestDBActivity extends AppCompatActivity {

    private EditText edtName, edtFamily, edtID;
    private Button btnInsert, btnView, btnUpdate, btnDelete;
    private TextView txtCount;
    private DatabaseManager dbm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_db);


        edtName = findViewById(R.id.edt_name);
        edtFamily = findViewById(R.id.edt_family);
        edtID = findViewById(R.id.edt_id);

        btnInsert = findViewById(R.id.btn_insert);
        btnView = findViewById(R.id.btn_view);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);

        dbm = new DatabaseManager(this);
        setCount();


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickInsert();
            }
        });
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Essi", "View btm Click");

                String vID = edtID.getText().toString();

                Person vPrs = dbm.getPerson(vID);
                edtName.setText(vPrs.pName);
                edtFamily.setText(vPrs.pFamily);
                Log.e("Essi", "pID" + vPrs.pID);
                Log.e("Essi", "pName" + vPrs.pName);
                Log.e("Essi", "pFamily" + vPrs.pFamily);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uID, uName, uFamily;
                uID = edtID.getText().toString();
                uName = edtName.getText().toString();
                uFamily = edtFamily.getText().toString();

                Person uperson= new Person();

                uperson.pID=uID;
                uperson.pName=uName;
                uperson.pFamily=uFamily;
                dbm.updatePerson(uperson);

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String delID=edtID.getText().toString();
                Person dPerson=new Person();
                dPerson.pID=delID;
                boolean del =dbm.deletePerson(dPerson);
                if (del){
                    Toast.makeText(TestDBActivity.this, "اطلاعات حذف شد", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(TestDBActivity.this, "در حذف اطلاعات مشکلی وجود دارد", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    private void setCount() {
        int sCount=dbm.personCount();
        txtCount =findViewById(R.id.txt_count);
        txtCount.setText(" sum Person" +sCount );
    }

    private void clickInsert() {
        String mID, mName, mFamily;

        mID = edtID.getText().toString();
        mName = edtName.getText().toString();
        mFamily = edtFamily.getText().toString();
        if (TextUtils.isEmpty(mID) || TextUtils.isEmpty(mName) || TextUtils.isEmpty(mFamily)) {
            Toast.makeText(TestDBActivity.this, "تمامی فیلد ها باید تکمیل شوند.", Toast.LENGTH_SHORT).show();
        } else {

            Person iPerson = new Person();
            iPerson.pID = mID;
            iPerson.pName = mName;
            iPerson.pFamily = mFamily;

            dbm.insertPerson(iPerson);

            Toast.makeText(TestDBActivity.this, "با موفقیت در دیتابیس ذخیره شد.", Toast.LENGTH_SHORT).show();


        }

    }

}
