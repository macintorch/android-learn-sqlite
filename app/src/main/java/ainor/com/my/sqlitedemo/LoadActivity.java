package ainor.com.my.sqlitedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class LoadActivity extends AppCompatActivity {

    ListView productsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        productsListView = (ListView) findViewById(R.id.productsListView);
    }
}
