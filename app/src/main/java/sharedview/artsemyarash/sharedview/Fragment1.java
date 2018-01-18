package sharedview.artsemyarash.sharedview;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import com.pascalwelsch.arrayadapter.ArrayAdapter;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;


/**
 * Created by artsemyarash on 1/17/18.
 */

public class Fragment1 extends Fragment {
    private float _yDelta;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment1, null);
        final RecyclerView recyclerView = view.findViewById(R.id.my_recycler_view);
        final ArrayList<String> aaa = new ArrayList<>();
        SlidingUpPanelLayout slidingUpPanelLayout = view.findViewById(R.id.sliding_layout);
        slidingUpPanelLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {

            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                if (newState == SlidingUpPanelLayout.PanelState.EXPANDED) {
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
                    recyclerView.scheduleLayoutAnimation();
                }
                if (newState == SlidingUpPanelLayout.PanelState.COLLAPSED) {
                    recyclerView.setAdapter(null);
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

//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(new ArrayAdapter<String, ViewHolder>(aaa) {
//            @Override
//            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                final View view = LayoutInflater.from(parent.getContext())
//                        .inflate(R.layout.item, parent, false);
//                return new ViewHolder(view);
//            }
//
//            @Override
//            public void onBindViewHolder(ViewHolder holder, int position) {
//                holder.titleView.setText(getItem(position));
//            }
//
//            @Nullable
//            @Override
//            public Object getItemId(@NonNull String item) {
//                return null;
//            }
//        });
        recyclerView.scheduleLayoutAnimation();
//        recyclerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                aaa.add("22222");
//                aaa.add("33333");
//                aaa.add("44444");
//                aaa.add("55555");
//                recyclerView.getLayoutParams().height = view.getHeight();
//                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
//                ((ArrayAdapter)recyclerView.getAdapter()).addAll(aaa);
//                recyclerView.getAdapter().notifyDataSetChanged();
//
//                recyclerView.scheduleLayoutAnimation();
//            }
//        });
        return view;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleView;

        public ViewHolder(final View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.text);
        }
    }
}
