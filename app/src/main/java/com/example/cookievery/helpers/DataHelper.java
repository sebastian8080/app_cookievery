package com.example.cookievery.helpers;

import android.view.View;
import android.widget.EditText;

public class DataHelper {

    public static String getTextValue(View view, int idText) {
        EditText editText = (EditText) view.findViewById(idText);
        return editText.getText().toString().trim();
    }
}
