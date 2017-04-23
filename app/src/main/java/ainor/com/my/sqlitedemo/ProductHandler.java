package ainor.com.my.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ainorsyahrizal on 23/04/2017.
 */

public class ProductHandler {

    private SQLiteDatabase db;

    public ProductHandler(Context context) {
        DatabaseHandler databaseHandler = new DatabaseHandler(context);
        this.db = databaseHandler.getWritableDatabase();
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            db.close();
        } catch (Exception e) {

        }

        super.finalize();
    }


    //Operations

    public void addProduct(String name, int quantity) {

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("quantity", quantity);
        db.insert(
                "product",
                null,
                values
        );
    }

    // Load all products

    public Cursor loadAllProducts() {
        Cursor cursor = db.query(
             "product", new String[]{"_id", "name", "quantity"},
                null,
                null,
                null,
                null,
                null
        );
        return cursor;
    }

    //Delete
    public void deleteProduct(int _id) {
        db.delete(
               "product",
                "_id=?",
                new String[]{_id+""}
        );
    }

    //Update

    public void updateProduct(int _id, String name, int quantity) {

    }

}
