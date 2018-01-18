package sharedview.artsemyarash.sharedview;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;

public class MainActivity extends FragmentActivity {
    Fragment1 f;
    public static final long FADE_DEFAULT_TIME = 300;
    public static final long MOVE_DEFAULT_TIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        performTransition();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            if (f != null) {
                f.hidePanel();
            }
        }
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP){
            if (f != null) {
                f.showPanel();
            }
        }
        return true;
    }

    private void performTransition() {
        if (isDestroyed()) {
            return;
        }
        FragmentManager fm = getFragmentManager();
        f = new Fragment1();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.container, f);
        fragmentTransaction.commitAllowingStateLoss();
    }

}
