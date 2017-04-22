package ainor.com.my.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ainorsyahrizal on 22/04/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "product.db";
    private static final int DB_VERSION = 1;

    public DatabaseHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table product(" +
                    "_id integer primary key," +
                    "name text," +
                     "quantity integer)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "drop table if exists product";

        db.execSQL(sql);

        onCreate(db);
    }
}
