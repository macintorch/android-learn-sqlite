package ainor.com.my.sqlitedemo;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

/**
 * Created by ainorsyahrizal on 23/04/2017.
 */

public class Navigator {

    public static void navigate(Context context, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItemAdd:
                if (!(context instanceof MainActivity)) {
                    // bring to add activity
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                }
                break;
            case R.id.menuItemLoad:
                if (!(context instanceof LoadActivity)) {
                Intent intent = new Intent(context, LoadActivity.class);
                context.startActivity(intent);
                break;
            }
        }
    }
}
