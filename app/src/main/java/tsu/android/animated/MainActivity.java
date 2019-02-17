package tsu.android.animated;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    View layoutOnScreen;
    ImageView imageView;

    boolean rotationFlag = false;
    int directionX = 1;
    int directionY = 1;
    int initColor = 360;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutOnScreen = findViewById(R.id.layout);
        imageView = findViewById(R.id.image_view);
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
                setDrawableToImageById(R.drawable.ic_black_cat);
                return true;
            case R.id.menu_snake:
                setDrawableToImageById(R.drawable.ic_snake);
                return true;
            case R.id.menu_noise:
                saySomethingCat();
                saySomethingSnake(item);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setDrawableToImageById(@DrawableRes int resId) {
        imageView.setImageDrawable(VectorDrawableCompat.create(getResources(), resId, null));
    }

    @Override
    protected void onResume() {
        super.onResume();
        startMyAnimation();
    }

    private void startMyAnimation() {
        final ValueAnimator animation = ValueAnimator
                .ofFloat(0f, 1f)
                .setDuration(3000 /* ms = 1/1000 s*/);
        final float[] hsv = new float[]{0, 0.8f, 1};


        animation.setRepeatMode(ValueAnimator.RESTART);
        animation.setRepeatCount(ValueAnimator.INFINITE);
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                move(value);
                changeColor(value, hsv);
            }
        });
        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Random random = new Random(System.currentTimeMillis());
                rotationFlag = !rotationFlag;
                directionX = random.nextBoolean() ? 1 : -1;
                directionY = random.nextBoolean() ? 1 : -1;
                initColor = random.nextInt(360);
            }
        });
        animation.start();
    }

    private void changeColor(float a, float[] hsv) {
        float value = a < 0.5f ? a : 1 - a;
        hsv[0] = (initColor + (360 * value)) % 360;
        layoutOnScreen.setBackgroundColor(Color.HSVToColor(hsv));

    }

    private void move(float a) {
        float value = a < 0.5f ? a : 1 - a;
        float translationX = directionX * value * 500;
        float translationY = directionY * value * 500;
        imageView.setTranslationX(translationX);
        imageView.setTranslationY(translationY);
        if (rotationFlag) {
            imageView.setRotation(2 * 360 * value);
        } else {
            imageView.setRotation(2 * -360 * value);
        }
    }

    private int dp2px(int dp) {
        return (int) (getResources().getDisplayMetrics().density * dp + 0.5f);
    }

    private void saySomethingSnake(MenuItem item) {
        String saying = item.isChecked() ? "Пшш-шш-шш" : "Вы слышите какой-то шорох";
        Snackbar.make(layoutOnScreen, saying, Snackbar.LENGTH_SHORT).show();
    }

    private void saySomethingCat() {
        Toast.makeText(this, "Мяу-мяу", Toast.LENGTH_SHORT).show();
    }
}
