package com.gorolykmaxim.android.commons.recyclerview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.Objects;

/**
 * {@link DiffUtil.ItemCallback} that treats items as immutable entities.
 *
 * <p>By default {@link DiffUtil.ItemCallback} considers identity of compared items. This way
 * the item might change its state and the callback will notice that. The thing is that sometimes
 * you just need to display a list of items, contents of which won't change. In such case,
 * use this callback. It does not considers identity of compare items and just does items
 * equality check.</p>
 */
public class ImmutableItemDiffCallback extends DiffUtil.ItemCallback {
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean areItemsTheSame(@NonNull Object oldItem, @NonNull Object newItem) {
        return areContentsTheSame(oldItem, newItem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean areContentsTheSame(@NonNull Object oldItem, @NonNull Object newItem) {
        return Objects.equals(oldItem, newItem);
    }
}
