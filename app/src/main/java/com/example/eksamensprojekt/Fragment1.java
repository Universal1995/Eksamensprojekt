package com.example.eksamensprojekt;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {
    private Fragment1Listener listener;
    private EditText editText1;
    private Button buttonOK;

    public interface Fragment1Listener{
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment1, container,false);
        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return true;
            }
        });
        editText1 = v.findViewById(R.id.EditTextFra1);
        buttonOK = v.findViewById(R.id.buttonFra1);



        return v;
    }

    public void updateEditText(CharSequence newText){
        editText1.setText(newText);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof Fragment1Listener){
            listener = (Fragment1Listener) context;
        } else {
            throw new RuntimeException(context.toString()+ " must implement fragment1Listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
