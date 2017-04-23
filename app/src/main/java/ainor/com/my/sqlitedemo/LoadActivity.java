package ainor.com.my.sqlitedemo;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class LoadActivity extends AppCompatActivity {

    ProductHandler productHandler;

    ListView productsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        productsListView = (ListView) findViewById(R.id.productsListView);

        //get into cursor

        productHandler = new ProductHandler(this);
        Cursor cursor = productHandler.loadAllProducts();
        CursorAdapter cursorAdapter = new SimpleCursorAdapter(
            this, R.layout.product_layout,
                cursor,
                new String[]{"_id","name","quantity"},
                new int[]{R.id.productIdTextView, R.id.productNameTextView, R.id.productQuantityTextView}
        );

        productsListView.setAdapter(cursorAdapter);
    }
}
