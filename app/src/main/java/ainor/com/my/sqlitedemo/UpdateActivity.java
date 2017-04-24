package ainor.com.my.sqlitedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {

    private int _id;
    private EditText nameEditText;
    private EditText quantityEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        //get to the intent that activated this activity
        Intent intent = this.getIntent();

        //get current data
        _id = intent.getIntExtra("_id", -1);
        String name = intent.getStringExtra("name");
        int quantity = intent.getIntExtra("quantity", -1);

        //set these current values to the form
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        quantityEditText = (EditText) findViewById(R.id.quantityEditText);
        nameEditText.setText(name);
        // add +"" to convert int to string
        quantityEditText.setText(quantity + "");
    }
}
