package edu.umn.moodlemanaged;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OfficeHoursActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.office_hours);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Dynamically add textviews MOCKUP (TODO get html from DB script in cloud)
        // TODO make unique mockups for each course (use a switch)
        LinearLayout layout = (LinearLayout) findViewById(R.id.oh_view);
        TextView oh = new TextView(this);
        oh.setText(Html.fromHtml(
            "<p>" +
                "<strong>Professor:</strong> Daniel Challou, Ph.D <br/>" +
                "<strong>Email:</strong> challou@cs.umn.edu <br/>" +
                "<strong>Phone:</strong> 612-625-8207 <br/>" +
                "<strong>Office Hours Location:</strong> 587 Shepherd Laboratories <br/>" +
                "<strong>Office Hours:</strong> Tuesday (2:45-3:45pm), Wednesday (3:45-4:45pm), and by Appointment <br/>" +
            "</p>" +
            "<p>" +
                "<strong>Teaching Assistant:</strong> Shaden Smith <br/>" +
                "<strong>Email:</strong> shaden@cs.umn.edu <br/>" +
                "<strong>Office Hours Location:</strong> Keller Hall 2-209  <br/>" +
                "<strong>Office Hours:</strong> Thursday (5:15-6:15pm), Friday (10:00-11:00am) <br/>" +
            "</p>" +
            "<p>" +
                "<strong>Teaching Assistant:</strong> Koorosh Vaziri <br/>" +
                "<strong>Email:</strong> vaziri@cs.umn.edu <br/>" +
                "<strong>Office Hours Location:</strong> Keller Hall 2-209  <br/>" +
                "<strong>Office Hours:</strong> Friday (1:00-3:00pm) <br/>" +
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