// Generated by view binder compiler. Do not edit!
package com.example.notesfirebase.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.notesfirebase.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentCreateNotesBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final FloatingActionButton buttonSaveNotes;

  @NonNull
  public final EditText etNoteTitle;

  @NonNull
  public final EditText etNotes;

  private FragmentCreateNotesBinding(@NonNull FrameLayout rootView,
      @NonNull FloatingActionButton buttonSaveNotes, @NonNull EditText etNoteTitle,
      @NonNull EditText etNotes) {
    this.rootView = rootView;
    this.buttonSaveNotes = buttonSaveNotes;
    this.etNoteTitle = etNoteTitle;
    this.etNotes = etNotes;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentCreateNotesBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentCreateNotesBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_create_notes, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentCreateNotesBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonSaveNotes;
      FloatingActionButton buttonSaveNotes = ViewBindings.findChildViewById(rootView, id);
      if (buttonSaveNotes == null) {
        break missingId;
      }

      id = R.id.etNoteTitle;
      EditText etNoteTitle = ViewBindings.findChildViewById(rootView, id);
      if (etNoteTitle == null) {
        break missingId;
      }

      id = R.id.etNotes;
      EditText etNotes = ViewBindings.findChildViewById(rootView, id);
      if (etNotes == null) {
        break missingId;
      }

      return new FragmentCreateNotesBinding((FrameLayout) rootView, buttonSaveNotes, etNoteTitle,
          etNotes);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
