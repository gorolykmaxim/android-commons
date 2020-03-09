package com.gorolykmaxim.android.commons.dialog;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

/**
 * A servant of {@link DialogFragment} instances.
 */
public class DialogFragmentServant {
    /**
     * Display the specified {@link DialogFragment} using the specified {@link FragmentManager}.
     *
     * <p>If this dialog is already being displayed, this call will be ignored.</p>
     *
     * @param dialogFragment dialog fragment to display
     * @param fragmentManager fragment manager to add this fragment to
     */
    public void showIfNotShown(DialogFragment dialogFragment, FragmentManager fragmentManager) {
        if (!dialogFragment.isAdded()) {
            dialogFragment.show(fragmentManager, null);
        }
    }
}
