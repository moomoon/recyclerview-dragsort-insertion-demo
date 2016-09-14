package org.dxm.recyclerviewinsertion;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by ants on 9/13/16.
 */

public abstract class DragCallback extends ItemTouchHelper.SimpleCallback {
    public DragCallback(int dragDirs) {
        super(dragDirs, 0);
    }

    @Override public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }

    private int lastAction = -1;
    private RecyclerView.ViewHolder dragging = null;

    @Override public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
            dragging = viewHolder;
            if (null != viewHolder) {
                onDragStart(viewHolder);
            }
        } else if (actionState == ItemTouchHelper.ACTION_STATE_IDLE && lastAction == ItemTouchHelper.ACTION_STATE_DRAG) {
            if (null != dragging) {
                onDragStop(dragging);
                dragging = null;
            }
        }
        lastAction = actionState;
        super.onSelectedChanged(viewHolder, actionState);
    }

    protected void onDragStart(@NonNull RecyclerView.ViewHolder holder) {
        ViewCompat.animate(holder.itemView).scaleX(1.2F).scaleY(1.2F).alpha(0.8F).setDuration(300).start();
    }

    protected void onDragStop(@NonNull RecyclerView.ViewHolder holder) {
        ViewCompat.animate(holder.itemView).scaleX(1F).scaleY(1F).alpha(1F).setDuration(300).start();
    }
}
