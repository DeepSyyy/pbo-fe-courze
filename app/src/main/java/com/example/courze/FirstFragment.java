package com.example.courze;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.courze.databinding.FragmentFirstBinding;
import android.text.InputType;


public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private EditText passwordToggle;
    private boolean isPasswordVisible = false;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        passwordToggle = binding.getRoot().findViewById(R.id.password_toggle);
        setupPasswordToggle();
        return binding.getRoot();
    }

    private void setupPasswordToggle() {
        passwordToggle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_lock_24, 0, R.drawable.ic_baseline_visibility_off_24, 0);

        passwordToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle password visibility
                togglePasswordVisibility(passwordToggle);
            }
        });
    }



    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    private void togglePasswordVisibility(EditText editText) {
        isPasswordVisible = !isPasswordVisible;

        int visibleIconId = isPasswordVisible ? R.drawable.ic_baseline_visibility_24 : R.drawable.ic_baseline_visibility_off_24;
        editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, visibleIconId, 0);
        // Change to visible text or password mode
        editText.setInputType(isPasswordVisible ?
                InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL :
                InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        // Change the drawable to visible or hidden icon


        // Move the cursor to the end of the text to maintain cursor position
        editText.setSelection(editText.getText().length());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
