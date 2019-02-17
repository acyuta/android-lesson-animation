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

    /**
     * Вызывается при первом показе меню
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.animations, menu);
        return true;
    }

    /**
     * Вызывается каждый раз перед отображением меню
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * Вызывается каждый раз, когда мы нажимаем на меню
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_kotik:
                return true;
            case R.id.menu_snake:
                item.setChecked(!item.isChecked());
                return true;
            case R.id.menu_noise:
                saySomethingCat();
                saySomethingSnake(item);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        startMyAnimation();
    }

    private void startMyAnimation() {

    }

    private void saySomethingSnake(MenuItem item) {
        String saying = item.isChecked() ? "Пшш-шш-шш" : "Вы слышите какой-то шорох";
        Snackbar.make(layoutOnScreen, saying, Snackbar.LENGTH_SHORT).show();
    }

    private void saySomethingCat() {
        Toast.makeText(this, "Мяу-мяу", Toast.LENGTH_SHORT).show();
    }
}
