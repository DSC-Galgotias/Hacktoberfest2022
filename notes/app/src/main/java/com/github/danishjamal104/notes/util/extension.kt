package com.github.danishjamal104.notes.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.lang.RuntimeException
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

fun String.toSHA1(): String {
    try {
        val md = MessageDigest.getInstance("SHA-1")
        val messageDigest = md.digest(this.toByteArray())
        val no = BigInteger(1, messageDigest)
        var hashtext: String = no.toString(16)
        while (hashtext.length < 32) {
            hashtext = "0$hashtext"
        }
        return hashtext
    }catch (e: NoSuchAlgorithmException) {
        throw RuntimeException(e)
    }
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.disable() {
    this.isEnabled = false
}

fun View.enable() {
    this.isEnabled = true
}

fun Fragment.shortToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Fragment.longToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
}

fun Context.showDefaultMaterialAlert(
    title: String,
    message: String,
    positiveButtonPress: () -> Unit
) {
    MaterialAlertDialogBuilder(this)
        .setTitle(title).setMessage(message)
        .setPositiveButton("Yes") { _, _ -> positiveButtonPress() }
        .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }.create().show()
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}