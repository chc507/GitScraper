package receiver.service.android.vogella.com.gitscraper.activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import receiver.service.android.vogella.com.gitscraper.R;
import receiver.service.android.vogella.com.gitscraper.model.SQLiteDBHelper;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener{
    //Create SQLite Reference
    SQLiteOpenHelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;

    //Share Preference
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    //UI element;
    EditText emailEditText;
    EditText passwordEditText;
    Button loginBtn;
    Button registerBtn;

    String LOGIN = "Error in LogIn Activity: ";

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnsignin:

                String rEmail = emailEditText.getText().toString();
                String rPassword = passwordEditText.getText().toString();

                cursor = db.rawQuery("Select *FROM " + SQLiteDBHelper.TABLE_NAME +
                                         " WHERE " + SQLiteDBHelper.COLUMN_EMAIL+"=? AND " +
                                         SQLiteDBHelper.COLUMN_PASSWORD+"=?", new String[] {rEmail, rPassword});

                if (cursor != null) {
                    if (cursor.getCount() > 0) {
                        cursor.moveToFirst();
                        String userEmail= cursor.getString(cursor.getColumnIndex(SQLiteDBHelper.COLUMN_EMAIL));
                        Toast.makeText(LogInActivity.this, "Login Success", Toast.LENGTH_SHORT).show();

                        editor.putString ("userEmail", userEmail);
                        editor.commit();

                        Intent intent = new Intent(LogInActivity.this,RepoListActivity.class);
                        intent.putExtra("email",userEmail);
                        startActivity(intent);

                        //Removing this activity from the stack for preventing back button press.
                        finish();
                    } else {
                        Toast.makeText(LogInActivity.this, "Login Fail, Please Re-enter", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    //I am showing Alert Dialog Box here for alerting user about wrong credentials
                    final AlertDialog.Builder builder = new AlertDialog.Builder(LogInActivity.this);
                    builder.setTitle("Alert");
                    builder.setMessage("Username or Password is wrong.");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

                break;
            case R.id.btnreg:
                //Add a new user entry to the database
                db = dbHelper.getWritableDatabase();
                final String wEmail = emailEditText.getText().toString();
                final String wPassword = passwordEditText.getText().toString();

                //insert data to the database
                ContentValues values = new ContentValues();
                values.put(SQLiteDBHelper.COLUMN_EMAIL,wEmail);
                values.put(SQLiteDBHelper.COLUMN_PASSWORD,wPassword);
                long id = db.insert(SQLiteDBHelper.TABLE_NAME,null,values);

                //insert data into  sharepreference
                editor.putString ("userEmail", wEmail);
                editor.commit();


                final AlertDialog.Builder builder = new AlertDialog.Builder(LogInActivity.this);
                builder.setTitle("Information");
                builder.setMessage("Your Account is Successfully Create!");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //Intent intent = new Intent(LogInActivity.this,RepoListActivity.class);
                        Intent intent = new Intent(LogInActivity.this,LogInActivity.class);
                        intent.putExtra("email",wEmail);
                        startActivity(intent);
                        finish();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();;
                break;

            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Hide app bar for full screen
        //ActionBar actionBar = getSupportActionBar();
        //actionBar.hide();

        //getting the reference from useremail, password, and textview
        emailEditText  = findViewById(R.id.txtemail);
        passwordEditText  = findViewById(R.id.txtpass);
        loginBtn = findViewById(R.id.btnsignin);
        registerBtn = findViewById(R.id. btnreg);

        //create db reference
        dbHelper = new SQLiteDBHelper(this);
        db = dbHelper.getReadableDatabase();

        //create sp reference
        // 0 for private mode
        sp = getApplicationContext().getSharedPreferences("GitScraper", 0);
        editor = sp.edit();

        registerBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
    }


}
