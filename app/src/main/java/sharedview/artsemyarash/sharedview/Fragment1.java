package sharedview.artsemyarash.sharedview;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pascalwelsch.arrayadapter.ArrayAdapter;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;


/**
 * Created by artsemyarash on 1/17/18.
 */

public class Fragment1 extends Fragment {
    private float _yDelta;
    private SlidingUpPanelLayout slidingUpPanelLayout;
    private boolean listWasExpanded = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, null);
        final RecyclerView recyclerView = view.findViewById(R.id.my_recycler_view);
        final ArrayList<String> aaa = new ArrayList<>();
        slidingUpPanelLayout = view.findViewById(R.id.sliding_layout);
        slidingUpPanelLayout.setPanelHeight(dpToPx(32));
        slidingUpPanelLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                if (panel.getY() <= slidingUpPanelLayout.getHeight() - dpToPx(100) && !listWasExpanded) {
                    showPanel();
                }
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                if (newState == SlidingUpPanelLayout.PanelState.EXPANDED && recyclerView.getAdapter() == null) {
                    listWasExpanded = true;
                    hidePanel();
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(new ArrayAdapter<String, ViewHolder>(aaa) {
                        @Override
                        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                            final View view = LayoutInflater.from(parent.getContext())
                                    .inflate(R.layout.item, parent, false);
                            return new ViewHolder(view);
                        }

                        @Override
                        public void onBindViewHolder(ViewHolder holder, int position) {
                            holder.titleView.setText(getItem(position));
                        }

                        @Nullable
                        @Override
                        public Object getItemId(@NonNull String item) {
                            return null;
                        }
                    });
                    RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) slidingUpPanelLayout.getLayoutParams();
                    lp.setMargins(0, 0, 0, 0);
                    slidingUpPanelLayout.setLayoutParams(lp);
                    recyclerView.scheduleLayoutAnimation();
                }
                if (newState == SlidingUpPanelLayout.PanelState.COLLAPSED) {
                    recyclerView.setAdapter(null);
                    listWasExpanded = false;
                }
            }
        });
        slidingUpPanelLayout.findViewById(R.id.drag_view).setOnClickListener(null);
        int resId = R.anim.layout_anim;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
        recyclerView.setLayoutAnimation(animation);

        aaa.add("11111");
        aaa.add("22222");
        aaa.add("33333");
        aaa.add("44444");
        aaa.add("55555");
        aaa.add("55555");
        aaa.add("55555");
        aaa.add("55555");
        aaa.add("55555");
        recyclerView.scheduleLayoutAnimation();
        return view;
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }


    public void hidePanel() {
        int dp32 = dpToPx(32);
        if (slidingUpPanelLayout.getPanelHeight() != dp32) {
            slidingUpPanelLayout.setPanelHeight(dp32);
        }
    }

    public void showPanel() {
        int dp100 = dpToPx(100);
        if (slidingUpPanelLayout.getPanelHeight() != dp100) {
            slidingUpPanelLayout.setPanelHeight(dp100);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleView;

        public ViewHolder(final View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.text);
        }
    }
}
