package ainor.com.my.sqlitedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {

    private int _id;
    private EditText nameEditText;
    private EditText quantityEditText;
    private Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        updateButton = (Button) findViewById(R.id.updateButton);

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

    public void onUpdateButtonClick (View view) {

        // get new data
        String newName = nameEditText.getText().toString();
        int newQuantity = Integer.parseInt(quantityEditText.getText().toString());

        //update data
        ProductHandler productHandler = new ProductHandler(this);
        productHandler.updateProduct(_id, newName, newQuantity);

        // finish this form
        this.setResult(RESULT_OK, null);
        finish();

    }
}
