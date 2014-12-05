package edu.umn.moodlemanaged;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import edu.umn.moodlemanaged.adapters.CustomTabListener;

public class MoodleManaged extends Activity {
    public static DBHelper mydb;
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.deleteDatabase("local.db");
        this.deleteDatabase("test.db");
        mydb = new DBHelper(this);

        Event e = new Event("Read Mathis Chapters 27-35", "CSCI-5115", false,"2014/11/08 13:24:36","assignment",1,1,5,30);
        Event e2 = new Event("Read ABC Chapters 27-35", "CSCI-5609",false,"2014/11/18 13:24:36","assignment",2,2,10,20);
        Event e3 = new Event("Exam", "CSCI-8001",false,"2014/11/18 13:44:36","exam",3,2,35,100);
        Course c1 = new Course(1,"CSCI 5115","User Interface Design, Implementation & Evaluation","Syllabus\n" +
                "Fall 2014 - CSCI 4511W: Introduction to Artificial Intelligence\n" +
                "\n" +
                "Time   :\n" +
                "\n" +
                " \n" +
                "\n" +
                "MW 4:00-5:15\n" +
                "\n" +
                "Location:\n" +
                "\n" +
                " \n" +
                "\n" +
                "Phys 170\n" +
                "\n" +
                "Instructor:\n" +
                "\n" +
                " \n" +
                "\n" +
                "Amy Larson (lars1050@umn.edu)\n" +
                "Office: Shepherd Labs 591\n" +
                "\n" +
                "Teaching Assistants:\n" +
                "\n" +
                " \n" +
                "\n" +
                "Julio Godoy \n" +
                "\n" +
                "Mark Valovage \n" +
                "\n" +
                "Tahir Sousa\n" +
                "\n" +
                "Class Email\n" +
                "\n" +
                "help-cs4511@cs.umn.edu\n" +
                "\n" +
                "Textbook\n" +
                "\n" +
                "Artificial Intelligence. A Modern Approach (3rd Edition) Stuart Russell and Peter Norvig, Prentice-Hall, 2010.\n" +
                "\n" +
                "Course Description\n" +
                "This course covers some of the early work in problem solving, most notably search, logic, and planning. The various problem-solving approaches are specialized algorithms, thus analysis with respect to space and time complexity is an essential part of the class. Course assessment is based on 3 major projects that encompass implementation, experimentation, and analysis, with a written report of results. Some in-class exams and written homework will also be administered. A significant portion of the material will be delivered online, leaving class time for discussion and group work. This course is highly group focused, but each student will be individually assessed and graded. Details of content are provided on the main Moodle page. For a quick overview of content and schedule see Schedule At-A-Glance.\n" +
                "\n" +
                "Writing Intensive Course\n" +
                "The W in CSCI 4511W indicates that this class is a writing intensive course.\n" +
                "\n" +
                " Writing is an activity in which you engage on a daily basis as a means of communicating information, emotions, and ideas with others through email, texting, blogging, and on occasion, pen and paper. Writing is also a means for organizing your thoughts, encouraging conversation, processing and synthesizing information, and developing a deeper understanding of concepts through articulation. Writing is essential to the educational and scientific process, which is why the U has established writing intensive courses that focus on communication skills in the context of your field of study. In this course, writing will be an integral part of the curriculum and the assessment. University policy on writing intensive courses can be found athttps://onestop.umn.edu/faculty/lib_eds/guidelines/writing_intensive.html\n" +
                "\n" +
                " Prerequisites\n" +
                "\n" +
                "This course does not have any special prerequisites, but it is a 4000-level computer science course and there are expectations of a student’s knowledge base. This includes basic knowledge of graph theory, asymptotic growth, data structures, and programming constructs with some programming experience. Knowledge of formal logic is helpful but not required. Students who do not have this background should contact Dr. Larson to discuss it.\n" +
                "\n" +
                " Course Assessment \n" +
                "\n" +
                "36%     Project*. 3 part group project.\n" +
                "\n" +
                "10%     Individual Write-Up of 1 part of the project.\n" +
                "\n" +
                "10%     Final Project** with Write-Up. Group or individual. Topic of choice.\n" +
                "\n" +
                "24%     Quizams. 3-4 Medium length in-class exams.\n" +
                "\n" +
                "12%     Homework: 3-4 Small written assignments (optionally completed in groups)                                                                \n" +
                "\n" +
                "8%       Participation. Weekly completion of online lessons and in-class involvement.\n" +
                "\n" +
                "Writing is integral to all assignments and comprises a significant portion of the grade.\n" +
                "\n" +
                "Grading is not curved. Grades are not rounded up.\n" +
                "\n" +
                "A\t93.0% or above\n" +
                "A-\t90.0%\n" +
                "B+\t85%\n" +
                "B\t80%\n" +
                "B-\t75%\n" +
                "C+\t70%\n" +
                "C\t65%\n" +
                "C-\t60%\n" +
                "D+\t55%\n" +
                "D\t50%\n" +
                "F\tless than 50%\n" +
                "All assignments must be submitted electronically. All written reports for the projects must be written in LaTeX and submitted as a PDF format. The LaTeX source file will be part of the submission.\n" +
                "\n" +
                "Late work is not accepted.\n" +
                "\n" +
                "Students are welcome to request a regrade of an assignment, but must do so within 10 days of grades being posted (except for the final assignment, for which there is no limit).\n" +
                "\n" +
                "*Project\n" +
                "\n" +
                "Comparison of search techniques. Implementation in Python. Experimental set-up at discretion of group.\n" +
                "Comparison of CSP variations or compare CSP to Search. Implementation in Python or Lisp.\n" +
                "Lisp and logic component.\n" +
                "**Final project of your choosing. Individual or in groups up to 3 students. Preferred format is an experimental set-up to analyze real-world implementation of learned approach. Topic choice is wide-open. Research paper is an option, but a consult with Dr. Larson is required to determine appropriate scope and topic.\n" +
                "\n" +
                "Academic Integrity Policy\n" +
                "\n" +
                "Academic integrity is a serious issue and has become more and more prevalent in computer science courses. It is simply not tolerated. I do not anticipate any issues in this course with respect to copying each other’s work, because almost everything is completed cooperatively (except for exams). Where students might get into trouble is copying off the web. Do not take text, code, ideas, or solutions off the web (or from a published/unpublished solution) without citing your reference. Assignments will state when you can and cannot use external resources (i.e. something not the textbook). Please adhere to these rules for your own sake, as they are in place to help you learn the material (and to follow a standard code of conduct for science and industry).\n" +
                "\n" +
                "Consequences of academic misconduct may be an F in the course or an F on the assignment, as well as an incident report to the Office for Student Conduct and Academic Integrity (OSCAI). More information on academic misconduct is available at Note on Academic Conduct for New Students.\n" +
                "\n" +
                "Last modified: Thursday, September 4, 2014, 6:59 PM");
        Course c2 = new Course(2,"CSCI 5609","Visualization","This is another course.");
        Grade g1 = new Grade(1,1,"Homework 1",true,5,20,30);
        mydb.insertEventWithGrade(e);
        mydb.insertEventWithGrade(e2);
        mydb.insertEventWithGrade(e3);

        mydb.insertCourse(c1);
        mydb.insertCourse(c2);
        //mydb.getGrades(2,"assignment");
        //mydb.getGrades(2,"exam");
        //mydb.setGrade(1,30);
        //mydb.getCourses();
        //mydb.getEvents();
        getData();
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
       
        actionBar.setDisplayShowTitleEnabled(true);
        
        // Creating Planning Tab
        Tab tab = actionBar.newTab()
        		.setText("Schedule")
        		.setTabListener(new CustomTabListener<PlanningFragment>(this, "planning", PlanningFragment.class));
        
        actionBar.addTab(tab, 0, false);
        
        
        // Creating Courses Tab
        tab = actionBar.newTab()
        		.setText("Courses")
        		.setTabListener(new CustomTabListener<CoursesFragment>(this, "courses", CoursesFragment.class));

        actionBar.addTab(tab, 1, true);      
        
        // Creating Grades Tab
        tab = actionBar.newTab()
        		.setText("Grades")
        		.setTabListener(new CustomTabListener<GradesFragment>(this, "grades", GradesFragment.class));

        actionBar.addTab(tab, 2, false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        int id = item.getItemId();
        if (id == R.id.action_events) {
            //TODO
            return true;
        } else if (id == R.id.action_account) {
            //TODO
            return true;
        } else if (id == R.id.action_settings) {
            //TODO
            return true;
        } else if (id == R.id.action_info) {
            //TODO
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    // Prevent accidental exiting of app
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setNegativeButton("No", null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .show();
    }
    public void getData(){
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());
        RequestQueue rq = new RequestQueue(cache,network);
        rq.start();
        //String url = "http://104.131.162.115/";
        String url = "http://104.131.162.115:8000/html/blog/";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Do something with the response
                        Log.i("response",response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        Log.i("Error",error.toString());
                    }
                });
        rq.add(stringRequest);
    }


}