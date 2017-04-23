package ainor.com.my.sqlitedemo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class LoadActivity extends AppCompatActivity {

    ProductHandler productHandler;

    Cursor cursor;

    ListView productsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        productsListView = (ListView) findViewById(R.id.productsListView);

        //get into cursor

        productHandler = new ProductHandler(this);
        loadProducts();

        // Register for context menu for this products listView
        this.registerForContextMenu(productsListView);

        // check which item was long clicked
        productsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                selectedPosition = i;
                return false;
            }
        });
    }

    private void loadProducts() {
        cursor = productHandler.loadAllProducts();
        CursorAdapter cursorAdapter = new SimpleCursorAdapter(
            this, R.layout.product_layout,
                cursor,
                new String[]{"_id","name","quantity"},
                new int[]{R.id.productIdTextView, R.id.productNameTextView, R.id.productQuantityTextView},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER

        );

        //Display products in listView
        productsListView.setAdapter(cursorAdapter);
    }

    int selectedPosition = -1;

    // Display context menu

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.productsListView) {
            this.getMenuInflater().inflate(R.menu.menu_update_delete, menu);
        }

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    private static final int UPDATE_REQUEST_CODE = 1;

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        //Move the cursor to that position
        if (selectedPosition > -1){
            cursor.moveToPosition(selectedPosition);
            int _id = cursor.getInt(0);

            switch (item.getItemId()) {
                case R.id.menuItemUpdate :
                    Intent intent = new Intent(this, UpdateActivity.class);
                    String name = cursor.getString(1);
                    int quantity = cursor.getInt(2);

                    intent.putExtra("_id", _id);
                    intent.putExtra("name", name);
                    intent.putExtra("quantity", quantity);

                    this.startActivityForResult(intent, UPDATE_REQUEST_CODE);
                    break;
                case R.id.menuItemDelete:
                    productHandler.deleteProduct(_id);
                    loadProducts();
                    break;
            }
        }

        return super.onContextItemSelected(item);
    }
}
