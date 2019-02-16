package tsu.android.animated;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    View layoutOnScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutOnScreen = findViewById(R.id.layout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.animations, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_kotik:
                saySomethingCat();
                return true;
            case R.id.menu_snake:
                saySomethingSnake();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saySomethingSnake() {
        Snackbar.make(layoutOnScreen, "Пшшш-шшш-шшш", Snackbar.LENGTH_SHORT).show();
    }

    private void saySomethingCat() {
        Toast.makeText(this, "Мяу-мяу", Toast.LENGTH_SHORT).show();
    }
}
