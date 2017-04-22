package ainor.com.my.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
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
}
