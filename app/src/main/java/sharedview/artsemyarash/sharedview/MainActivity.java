package sharedview.artsemyarash.sharedview;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.transition.TransitionSet;
import android.view.View;

public class MainActivity extends FragmentActivity {

    public static final long FADE_DEFAULT_TIME = 300;
    public static final long MOVE_DEFAULT_TIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        performTransition();
    }

    private void performTransition() {
        if (isDestroyed()) {
            return;
        }
        FragmentManager fm = getFragmentManager();
        Fragment f = new Fragment1();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.container, f);
        fragmentTransaction.commitAllowingStateLoss();
    }

}
