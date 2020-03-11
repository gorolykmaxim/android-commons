package com.gorolykmaxim.android.commons.recyclerview;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Generic view holder do display any type of {@link View}.
 *
 * <p>Typically in most tutorials you'll find an example of a
 * {@link androidx.recyclerview.widget.RecyclerView.ViewHolder}, that will fully control the
 * underlying view (which in tern is usually created by inflating a layout resource). Thus,
 * your view holder partially behaves as a view. There is a case though, when you would want
 * to use your existing custom view as an item in the recycler view. This way you would
 * be able to re-use that view somewhere else (outside the recycler view) as opposed to your
 * "smart" view holder, that can be used only in another recycler view.</p>
 *
 * @param <T> specific type of {@link View}, held by this holder
 */
public class GenericViewHolder<T> extends RecyclerView.ViewHolder {
    /**
     * Get instance of view, from the specified view holder (which is expected to be
     * a {@link GenericViewHolder}), in a desired type.
     *
     * @param viewHolder generic view holder to obtain view from
     * @param <D> expected type of view
     * @return view held by the view holder
     */
    public static <D> D getViewOf(RecyclerView.ViewHolder viewHolder) {
        GenericViewHolder<D> genericViewHolder = (GenericViewHolder<D>)viewHolder;
        return genericViewHolder.getView();
    }

    /**
     * {@inheritDoc}
     */
    public GenericViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    /**
     * Get view, held by this view holder in its original type.
     *
     * @return view held by this view holder
     */
    public T getView() {
        return (T) itemView;
    }
}
