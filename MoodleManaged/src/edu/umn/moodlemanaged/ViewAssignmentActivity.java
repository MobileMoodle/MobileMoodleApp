package edu.umn.moodlemanaged;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ViewAssignmentActivity extends Activity
{
    public static String titleString;
    public static String dateString;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_assignment);
        TextView title = (TextView) findViewById(R.id.event_title);
        title.setText(titleString);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Dynamically edit textview MOCKUP (TODO get html from DB script in cloud)
        // TODO make unique mockups for each event (use a switch)
        // TODO support button, and make views move together

        // Add assignment details
        TextView tv = (TextView) findViewById(R.id.event_view);
        tv.setText(Html.fromHtml(
                "<p>" +
                        "<p><strong>Due Date:</strong><br/>" + dateString + "<br/></p>" +
                        "<p><strong>Weight:</strong><br/>35%<br/></p>" +
                        "<p><strong>Description:</strong><br/>" +
                        "One way to learn about user needs is to ask them. However, in class we discussed a number of problems with just asking users about their needs. Which one of the following is not a problem we discussed?\n" +
                        "<p>People may not be able to identify their problems.<br/>" +
                        "<p>People are not good at coming up with solutions.<br/>" +
                        "<p>People may refuse to talk to you.<br/>" +
                        "<p>People are really bad at predicting whether and how they’d use a new product.<br/>" +
                        "We have spent a lot of time talking about and working with user tasks (specifically as defined in the Lewis & Rieman text). Which of the following statements about tasks is false?\n" +
                        "Tasks are detailed descriptions of a complete user ‘job’.\n" +
                        "Tasks specify how a user does the ‘job’ in a particular user interface.\n" +
                        "Tasks can be used to guide design decisions.\n" +
                        "Tasks can be based on input and feedback from your users.\n" +
                        "Which of the following is the best definition of Norman’s gulf of evaluation?\n" +
                        "Having a good interface design, but no concrete measures by which to evaluate it.\n" +
                        "A user knowing what she wants to achieve, but not knowing how to achieve that goal in the interface.\n" +
                        "A property of an interface that indicates to users what functions it can be used to carry out.\n" +
                        "A user having completed an action but not being able to tell whether it succeeded.\n" +
                        "All of the following are important parts of the start of a user evaluation except which?\n" +
                        "Giving the user a tour of the interface being studied.\n" +
                        "Explaining that the user is free to leave and can stop if he is uncomfortable.\n" +
                        "Reassuring the user that you are not testing her, just testing the interface.\n" +
                        "Explaining, at least in general terms, what is involved in the test you are asking the user to complete." +
                        "<p><strong>Details:</strong><br/>" +
                            "FALL 2014: CSci 5115: UI Design, Implementation & Evaluation<br/>" +
                            "Assignment 7<br/>" +
                            "Issued: 11/4, Due: 11/11<br/><br/>" +
                            "Task:<br/><br/>" +
                            "In this assignment you will learn about creating restricted pages for your website using credentials from" +
                            " a database. This is a very practical php application that is heavily used in web portals and content" +
                            " management systems (CMS) like WordPress, and PHP handles it very well.<br/><br/>" +
                            "Your task is to create a restricted page called: users.php. The Users page will show all the users from" +
                            " the database (screenshot 01.png). This page needs to be protected from anyone who does not have a login" +
                            " and password in your system. You can use $ SESSION variable in PHP to store current user's information," +
                            " and if it is not set, redirect user to the login page.<br/><br/>" +
                            " You must also create two other pages that are critical for your task: login.php, and logout.php. If the" +
                            " user is not logged into your system, he/she should be redirected to the login page. The Login page gets and" +
                            " validates the login and password, checks the login against the database, checks the password against the" +
                            " database, and if everything matches, redirects the user to users.php page.<br/><br/>" +
                            "Functionality:<br/><br/>" +
                            "<ul><li>If user is not authenticated, opening users.php page should redirect user to login.php.</li>" +
                                "<li>login.php should show the login form with at least (screenshot 02.png):</li>" +
                                    "<ul><li>{ Login field }</li>" +
                                    "<li>{ Password field }</li>" +
                                    "<li>{ Submit Button }</li>" +
                                    "<li>{ Div for Errors }</li></ul></ul><br/><br/>" +
                        "When the submit button is selected, your login page should validate the input and display the following" +
                        " errors:<br/><br/>" +
                        "<ul><li>{ Please enter a valid value for Login Name field. }</li>" +
                            "<li>{ Please enter a valid value for Password field. }</li>" +
                            "<li>{ or both, if both fields are empty (screenshot 03.png) }</li></ul><br/><br/>" +
                        "After input validation, your page should query the database and following errors need to be reported:<br/><br/>" +
                            "<ul><li>{ Login is incorrect. Please check the login and try again. (screenshot 04.png) }</li>" +
                            "<li>{ Password is incorrect. Please check the password and try again. (screenshot 05.png) }</li></ul><br/><br/>" +
                        "Passwords are stored in their SHA1 hashed format. You need to convert user entered password string" +
                        " into SHA1 hash by sha1() function of PHP. For a list of user and passwords, check out the upcoming" +
                        " database section. . .<br/><br/>" +
                        /*"\u000F logout.php page clears the session variable, and redirects the user to login.php page.\n" +
                        "\u000F The private users.php page should display and welcome the current user name, and display a logout\n" +
                        "button or link (screenshot 01.png). Pressing the logout button will send the user to logout.php and\n" +
                        "log the user out, and user can no longer open users.php page without re-entering his/her credentials.\n" +
                        "\u000F Users page should display list of users, as seen on Screenshot 01.png, with their corresponding ID,\n" +
                        "NAME, and LOGIN. Respectively, they are acc id, acc name, and acc login \felds in the database.\n" +
                        "\u000F in the Assignment \fles for this assignment, you will \fnd an extra \fle called: database.php. You need\n" +
                        "to use the include or include once statements in your code to get the database server credentials and\n" +
                        "be able to use PHP MySQLi functions and methods.\n" +
                        "Database:\n" +
                        "\u000F Database in this assignment contains one table: tbl accounts\n" +
                        "\u000F This table has following \felds: acc id, acc name, acc login, and acc password\n" +
                        "\u000F There are currently following users in the database, with their current passwords, but your program\n" +
                        "should if/when the database changes:\n" +
                        "{ Login: challou - Passowrd: dc2014\n" +
                        "{ Login: shaden - Password: ss2014\n" +
                        "{ Login: koorosh - Password: kv2014\n" +
                        "{ Login: john - Password: jd2014\n" +
                        "{ Login: jane - Password: jd2014\n" +
                        "\u000F You can use following PHP code to initiate a database connection:\n" +
                        "<?php\n" +
                        "/ / . . .\n" +
                        "inc lude onc e ' database . php ' ;\n" +
                        "// Create conne c t ion\n" +
                        "$conn = new mysql i ( $db servername , $db username , $db password , $db name , $db por t ) ;\n" +
                        "i f ( $conn􀀀>c o n n e c t e r r o r ) f\n" +
                        "// r epo r t e r r o r\n" +
                        "g e l s e f\n" +
                        "// s e t up your query\n" +
                        "g\n" +
                        "/ / . . .\n" +
                        "?>\n" +
                        "Submission Instructions\n" +
                        "At the least, you need to include the following \fles in one zipped \fle for your submission:\n" +
                        "\u000F database.php: Your MySQL database credentials.\n" +
                        "\u000F users.php: The private \fle restricted with login credentials.\n" +
                        "\u000F login.php: Main login page.\n" +
                        "\u000F logout.php: Log out page.\n" +
                        "\u000F style.css: (optional)\n" +*/
                        "Grading Criteria:<br/><br/>" +
                        "<ol><li>Submission instructions are met - 10 points</li>" +
                            "<li>The users.php redirects to login.php page automatically before authentication - 10 points</li>" +
                            "<li>Login page shows the form elements and submit button - 10 points</li>" +
                            "<li>Login page handles input validation, checks and retrieves the user from the database, and shows" +
                            " appropriate errors - 10 points</li>" +
                            "<li>Login page logs the user in and redirects to users.php - 20 points</li>" +
                            "<li>users.php page is showing appropriate User name at the top - 10 points</li>" +
                            "<li>users.php page is showing correct list of users in the system - 20 points</li>" +
                            "<li>Logout functionality works correctly - 10 points</li></ol>" +
                        "<br/></p>" +
                "</p>",
        null, new MyTagHandler()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                titleString = "";
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}