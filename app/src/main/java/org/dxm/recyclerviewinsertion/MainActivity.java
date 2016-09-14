package org.dxm.recyclerviewinsertion;

import android.databinding.DataBindingUtil;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import org.dxm.recyclerviewinsertion.databinding.ActivityMainBinding;

import java.util.Collections;

import static android.support.v7.widget.helper.ItemTouchHelper.*;
import static android.support.v7.widget.helper.ItemTouchHelper.LEFT;

public class MainActivity extends AppCompatActivity {

    final SampleAdapter adapter = new SampleAdapter();
    InsertionAnimator animator;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
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
        animator = new InsertionAnimator() {
            @Override public boolean isInsertion(RecyclerView.ViewHolder holder) {
                return holder.getAdapterPosition() == adapter.getItemCount() - 1;
            }
        };
        binding.recyclerView.setItemAnimator(animator);
        final SampleAdapter albumAdapter = new SampleAdapter();
        for (int i = 0; i < 500; i++) {
            albumAdapter.insertItemAt(0, Utils.randomColor());
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 11);
        binding.album.setLayoutManager(gridLayoutManager);
        binding.album.setAdapter(albumAdapter);
        albumAdapter.setClickCallback(new SampleAdapter.ClickCallback() {
            @Override public void onClicked(int position, final Integer color, RecyclerView.ViewHolder holder) {
                albumAdapter.colors.remove(position);
                albumAdapter.notifyItemRemoved(position);
                animator.setNextAdditionAnchor(Utils.translate(new PointF(0, 0), holder.itemView, binding.recyclerView));
                adapter.insertItemAt(adapter.getItemCount(), color);
                binding.recyclerView.scrollToPosition(adapter.getItemCount() - 1);
            }
        });
    }
}
