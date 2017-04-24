package ainor.com.my.sqlitedemo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class LoadActivity extends AppCompatActivity {

    ProductHandler productHandler;

    Cursor cursor;

    ListView productsListView;

    EditText keywordEditText;

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

        keywordEditText = (EditText) findViewById(R.id.keywordEditText);
        // search as type in editText
        keywordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                loadProducts();

                return false;
            }
        });
    }

    private void loadProducts() {
        // access to the keyword if its there
        EditText keywordEditText = (EditText) findViewById(R.id.keywordEditText);
        String keyword = keywordEditText.getText().toString();
        if (keyword.isEmpty()){
            cursor = productHandler.loadAllProducts();
        } else {
            cursor = productHandler.searchProductKeywordByName(keyword);
        }

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.d("RESULT_OK", String.valueOf(RESULT_OK));
        Log.d("RequestCode", String.valueOf(requestCode));

        if (requestCode == UPDATE_REQUEST_CODE) {
            //reload the form
            loadProducts();
        }
    }

    public void onButtonSearchClick (View view) {
        loadProducts();
    }
}
