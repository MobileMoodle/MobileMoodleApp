package edu.umn.moodlemanaged;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.umn.moodlemanaged.R;

public class SyllabusActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.syllabus);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Dynamically add textviews MOCKUP (TODO get html from DB script in cloud)
        // TODO make unique mockups for each course (use a switch)
        LinearLayout layout = (LinearLayout) findViewById(R.id.oh_view);
        TextView oh = new TextView(this);
        oh.setText(Html.fromHtml(
            "<p>" +
                "<h2>Course Syllabus</h2>" +
            "</p>" +
            "<p>" +
                "<h3>UI Design, Implementation & Evaluation (2014)</h3>" +
            "</p>" +
            "<p>" +
                "<h4>Instructors</h4>" +
            "</p>" +
            "<p>" +
                "<strong>Joseph A. Konstan</strong>" +
                "<strong>Email:</strong> konstan@cs.umn.edu <br/>" +
                "<strong>Office:</strong> KHKH 5-207 <br/>" +
                "<strong>Office Hours:</strong> Thursday 10:00-11:00 A.M. <br/>" +
            "</p>" +
            "<p>" +
                "<strong>Loren Terveen</strong>" +
                "<strong>Email:</strong> terveen@cs.umn.edu <br/>" +
                "<strong>Office:</strong> KHKH 5-191 <br/>" +
                "<strong>Office Hours:</strong> Monday 11:00-12:00 A.M. <br/>" +
            "</p>" +
            "<p>" +
                "<h4>Teaching Assistants</h4>" +
            "</p>" +
            "<p>" +
                "<strong>Dan Jarrat</strong>" +
                "<strong>Email:</strong> jarra011@umn.edu <br/>" +
            "</p>" +
            "<p>" +
                "<strong>Hannah Miller</strong>" +
                "<strong>Email:</strong> mill6273@umn.edu <br/>" +
            "</p>" +
            "<p>" +
                 "<h4>Course Description</h4>" +
            "</p>" +
            "<p>" +
                "<strong>Upon successful completion of this course, you will be able to:</strong>" +
                "<ul>" +
                "<li>Identify basic principles of design psychology and apply them to analyze software user interfaces and other designed products.<li>" +
                "<li>Apply user- and task-centered design methods, including techniques and methods to:<li>" +
                    "<ul>" +
                        "<li>Elicit and represent user needs<li>" +
                        "<li>Create low fidelity design prototypes<li>" +
                        "<li>Evaluate designs, both without and with users<li>" +
                    "<ul>" +
                "<li>Identify and apply common user interface design principles and patterns<li>" +
                "<li>Design and implement user interfaces for the Android mobile operating system.<li>" +
                "<li>Identify and explain key foundational concepts and theories in Human-Computer Interaction.<li>" +
            "</p>"
        ));
        layout.addView(oh);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}