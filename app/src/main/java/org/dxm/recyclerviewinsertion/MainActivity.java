package org.dxm.recyclerviewinsertion;

import android.databinding.DataBindingUtil;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;

import org.dxm.recyclerviewinsertion.databinding.ActivityMainBinding;

import java.util.Collections;

import static android.support.v7.widget.helper.ItemTouchHelper.*;
import static android.support.v7.widget.helper.ItemTouchHelper.LEFT;

public class MainActivity extends AppCompatActivity {

    final SampleAdapter adapter = new SampleAdapter();
    final InsertionAnimator animator = new InsertionAnimator();
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        GridLayoutManager manager = new GridLayoutManager(this, 10);
        binding.recyclerView.setItemAnimator(animator);
        binding.recyclerView.setLayoutManager(manager);
        binding.setAdapter(adapter);
        new ItemTouchHelper(new DragCallback(LEFT | RIGHT) {
            @Override public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                final int from = viewHolder.getAdapterPosition();
                final int to = target.getAdapterPosition();
                Collections.swap(adapter.colors, from, to);
                adapter.notifyItemMoved(from, to);
                return true;
            }
        }).attachToRecyclerView(binding.recyclerView);
    }

    public void insertion(View view) {
        animator.setNextAdditionAnchor(new Point(0, -50));
        adapter.insertItemAt(adapter.getItemCount(), Utils.randomColor());
    }
}
