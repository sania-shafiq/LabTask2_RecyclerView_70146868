package com.example.labsevenrecyclerviews;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CourseAdapter.OnItemClickListener {

    RecyclerView rvCourses;
    ArrayList<Course> courses;
    CourseAdapter courseAdapter;

    public void getIds() {
        rvCourses = findViewById(R.id.rvCourses);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, windowInsets) -> {
            Insets bars = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return windowInsets;
        });

        // getting Ids from XML layout
        getIds();

        // list data (BS Software Engineering courses)
        courses = new ArrayList<>();
        courses.add(new Course("CS02310", "Mobile Application Development",
                "Dr. Ayesha Khan", "ayesha.khan@uol.edu.pk",
                "Building Android apps with Java and Android Studio: activities, intents, "
                        + "RecyclerView, storage and networking.", 4));
        courses.add(new Course("CS01210", "Data Structures and Algorithms",
                "Mr. Hamza Malik", "hamza.malik@uol.edu.pk",
                "Arrays, linked lists, stacks, queues, trees, graphs, sorting and searching, "
                        + "with analysis of time complexity.", 4));
        courses.add(new Course("SE01310", "Software Requirement Engineering",
                "Ms. Sana Riaz", "sana.riaz@uol.edu.pk",
                "Eliciting, analysing, specifying and validating software requirements, "
                        + "including use cases and user stories.", 3));
        courses.add(new Course("SE02110", "Software Design and Architecture",
                "Dr. Usman Tariq", "usman.tariq@uol.edu.pk",
                "Design principles, UML, design patterns and architectural styles used to "
                        + "structure large software systems.", 3));
        courses.add(new Course("CS02110", "Database Systems",
                "Ms. Hira Anwar", "hira.anwar@uol.edu.pk",
                "Relational model, ER diagrams, SQL, normalization, transactions and "
                        + "indexing.", 4));
        courses.add(new Course("CS01410", "Operating Systems",
                "Mr. Bilal Ahmad", "bilal.ahmad@uol.edu.pk",
                "Processes, threads, CPU scheduling, memory management, file systems and "
                        + "deadlocks.", 3));
        courses.add(new Course("SE02210", "Software Quality Assurance and Testing",
                "Ms. Maryam Iqbal", "maryam.iqbal@uol.edu.pk",
                "Test planning, black-box and white-box testing, test automation and "
                        + "quality metrics.", 3));
        courses.add(new Course("CS01510", "Computer Networks",
                "Mr. Faisal Mehmood", "faisal.mehmood@uol.edu.pk",
                "Network layers, TCP/IP, routing, switching, application protocols and "
                        + "basic network security.", 3));
        courses.add(new Course("SE03110", "Software Project Management",
                "Dr. Nadia Hussain", "nadia.hussain@uol.edu.pk",
                "Planning, estimation, scheduling, risk management and agile project "
                        + "delivery.", 3));

        // adapter (this activity listens to the clicks)
        courseAdapter = new CourseAdapter(courses, this);

        // vertical list + adapter
        rvCourses.setLayoutManager(new LinearLayoutManager(this));
        rvCourses.setAdapter(courseAdapter);
    }

    @Override
    public void onItemClick(int position) {
        // read only the tapped course
        Course course = courses.get(position);

        // explicit Intent carrying only that course's data
        Intent intent = new Intent(MainActivity.this, CourseDetailActivity.class);
        intent.putExtra(CourseDetailActivity.EXTRA_CODE, course.getCode());
        intent.putExtra(CourseDetailActivity.EXTRA_TITLE, course.getTitle());
        intent.putExtra(CourseDetailActivity.EXTRA_INSTRUCTOR, course.getInstructor());
        intent.putExtra(CourseDetailActivity.EXTRA_EMAIL, course.getEmail());
        intent.putExtra(CourseDetailActivity.EXTRA_DESCRIPTION, course.getDescription());
        intent.putExtra(CourseDetailActivity.EXTRA_CREDIT_HOURS, course.getCreditHours());
        startActivity(intent);
    }
}
