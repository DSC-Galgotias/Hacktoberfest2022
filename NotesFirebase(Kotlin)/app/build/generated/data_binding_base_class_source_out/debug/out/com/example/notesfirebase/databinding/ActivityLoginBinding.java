// Generated by view binder compiler. Do not edit!
package com.example.notesfirebase.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.notesfirebase.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLoginBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView imageView2;

  @NonNull
  public final MaterialCardView numberLayout;

  @NonNull
  public final MaterialCardView otpLayout;

  @NonNull
  public final TextView sendOtp;

  @NonNull
  public final TextView textView1;

  @NonNull
  public final TextView textView4;

  @NonNull
  public final TextInputEditText userNumber;

  @NonNull
  public final TextInputEditText userOtp;

  @NonNull
  public final TextView verifyOtp;

  private ActivityLoginBinding(@NonNull ConstraintLayout rootView, @NonNull ImageView imageView2,
      @NonNull MaterialCardView numberLayout, @NonNull MaterialCardView otpLayout,
      @NonNull TextView sendOtp, @NonNull TextView textView1, @NonNull TextView textView4,
      @NonNull TextInputEditText userNumber, @NonNull TextInputEditText userOtp,
      @NonNull TextView verifyOtp) {
    this.rootView = rootView;
    this.imageView2 = imageView2;
    this.numberLayout = numberLayout;
    this.otpLayout = otpLayout;
    this.sendOtp = sendOtp;
    this.textView1 = textView1;
    this.textView4 = textView4;
    this.userNumber = userNumber;
    this.userOtp = userOtp;
    this.verifyOtp = verifyOtp;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.imageView2;
      ImageView imageView2 = ViewBindings.findChildViewById(rootView, id);
      if (imageView2 == null) {
        break missingId;
      }

      id = R.id.numberLayout;
      MaterialCardView numberLayout = ViewBindings.findChildViewById(rootView, id);
      if (numberLayout == null) {
        break missingId;
      }

      id = R.id.otpLayout;
      MaterialCardView otpLayout = ViewBindings.findChildViewById(rootView, id);
      if (otpLayout == null) {
        break missingId;
      }

      id = R.id.sendOtp;
      TextView sendOtp = ViewBindings.findChildViewById(rootView, id);
      if (sendOtp == null) {
        break missingId;
      }

      id = R.id.textView1;
      TextView textView1 = ViewBindings.findChildViewById(rootView, id);
      if (textView1 == null) {
        break missingId;
      }

      id = R.id.textView4;
      TextView textView4 = ViewBindings.findChildViewById(rootView, id);
      if (textView4 == null) {
        break missingId;
      }

      id = R.id.userNumber;
      TextInputEditText userNumber = ViewBindings.findChildViewById(rootView, id);
      if (userNumber == null) {
        break missingId;
      }

      id = R.id.userOtp;
      TextInputEditText userOtp = ViewBindings.findChildViewById(rootView, id);
      if (userOtp == null) {
        break missingId;
      }

      id = R.id.verifyOtp;
      TextView verifyOtp = ViewBindings.findChildViewById(rootView, id);
      if (verifyOtp == null) {
        break missingId;
      }

      return new ActivityLoginBinding((ConstraintLayout) rootView, imageView2, numberLayout,
          otpLayout, sendOtp, textView1, textView4, userNumber, userOtp, verifyOtp);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
