package org.dxm.recyclerviewinsertion;

/**
 * Created by ants on 9/13/16.
 */

public interface ItemTouchHelperAdapter {

    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
