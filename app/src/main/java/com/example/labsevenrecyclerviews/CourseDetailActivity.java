package com.example.labsevenrecyclerviews;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CourseDetailActivity extends AppCompatActivity {

    // keys used with putExtra() / getXxxExtra()
    public static final String EXTRA_CODE = "extra_code";
    public static final String EXTRA_TITLE = "extra_title";
    public static final String EXTRA_INSTRUCTOR = "extra_instructor";
    public static final String EXTRA_EMAIL = "extra_email";
    public static final String EXTRA_DESCRIPTION = "extra_description";
    public static final String EXTRA_CREDIT_HOURS = "extra_credit_hours";

    TextView tvDetailTitle, tvDetailInstructor, tvDetailCredits, tvDetailDescription;
    Button btnEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_course_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detailRoot), (v, windowInsets) -> {
            Insets bars = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(bars.left, 0, bars.right, bars.bottom);
            return windowInsets;
        });

        tvDetailTitle = findViewById(R.id.tvDetailTitle);
        tvDetailInstructor = findViewById(R.id.tvDetailInstructor);
        tvDetailCredits = findViewById(R.id.tvDetailCredits);
        tvDetailDescription = findViewById(R.id.tvDetailDescription);
        btnEmail = findViewById(R.id.btnEmail);

        // receive the data of the tapped course
        Intent intent = getIntent();
        String code = intent.getStringExtra(EXTRA_CODE);
        String title = intent.getStringExtra(EXTRA_TITLE);
        String instructor = intent.getStringExtra(EXTRA_INSTRUCTOR);
        final String email = intent.getStringExtra(EXTRA_EMAIL);
        String description = intent.getStringExtra(EXTRA_DESCRIPTION);
        int creditHours = intent.getIntExtra(EXTRA_CREDIT_HOURS, 0);

        // screen title = course code
        setTitle(code);

        tvDetailTitle.setText(title);
        tvDetailInstructor.setText(instructor);
        tvDetailCredits.setText(creditHours + " Credit Hours");
        tvDetailDescription.setText(description);

        // email ONLY this course's instructor
        btnEmail.setOnClickListener(v -> {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:" + email));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Query regarding " + code);
            try {
                startActivity(emailIntent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(CourseDetailActivity.this,
                        "No email app found on this device", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
