package com.gorolykmaxim.android.commons.dialog;

import android.app.Dialog;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

/**
 * A servant of various dialog types.
 */
public class DialogServant {
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

    /**
     * Display the specified {@link Dialog}.
     *
     * <p>If this dialog is already being displayed, this call will be ignored.</p>
     *
     * @param dialog dialog to display
     */
    public void showIfNotShown(Dialog dialog) {
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }
}
