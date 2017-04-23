package ainor.com.my.sqlitedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*
    Access sqlite via terminal

    adb -e shell
    # cd /data/data/<package name>/databases
    # ls
    # sqlite3 ./product.db
    >.tables
    >select * from product

 */

public class MainActivity extends AppCompatActivity {

    ProductHandler productHandler;

    EditText nameEditText;
    EditText quantityEditText;

    Button mAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAddButton = (Button) findViewById(R.id.addButton);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        quantityEditText = (EditText) findViewById(R.id.quantityEditText);

    }


    public void onBtnAddClick(View view) {

        //create database connection
        productHandler = new ProductHandler(this);

        String name = nameEditText.getText().toString();
        int quantity = Integer.parseInt(quantityEditText.getText().toString());
        productHandler.addProduct(name, quantity);

        Toast.makeText(this,"Added Successfully", Toast.LENGTH_SHORT).show();

        nameEditText.setText("");
        quantityEditText.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Navigator.navigate(this, item);
        return super.onOptionsItemSelected(item);
    }
}
