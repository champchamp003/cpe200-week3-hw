package cpe200;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Course {

    public Course() {
        this("TBA", "000000", "TBA", 30);
    }

    public Course(String n, String cid) {
        course_name = n;
        course_id = cid;
    }

    public Course(String n, String cid, String l) {
        course_name = n;
        course_id = cid;
        lecturer = l;
    }

    public Course(String n, String cid, String l, int max) {
        this.course_name = !n.equalsIgnoreCase("") ? n : "TBA";
        String checkID = "^[0-9]{6,}$";
        if (cid.matches(checkID)) course_id = cid;
        else course_id = "000000";
        if (!l.equals("")) lecturer = l;
        else lecturer = "TBA";
        max_students = max;
        no_students = 0;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = !course_name.equalsIgnoreCase("") ? course_name : this.course_name;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = isValidCourse_id(course_id) ? course_id : this.course_id;
    }

    public String getLecturer() {

        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = !lecturer.equalsIgnoreCase("") ? lecturer : this.lecturer;
    }

    public int getMax_students() {
        return max_students;
    }

    public void setMax_students(int max) {
        if (max >= 0) max_students = max;
    }

    public int getNo_students() {
        return no_students;
    }

    public void setNo_students(int n) {
        if (n <= max_students && n > 0) no_students = n;
    }

    @Override
    public String toString() {
        String n = "", st = "student,";
        if (no_students == 1) n = "ONE";
        else if (no_students == 0) n = "NO";
        else if (no_students > 1) {
            n = Integer.toString(no_students);
            st = "students,";
        }
        String o = this.course_name + " ("
                + this.course_id + "), Teacher: "
                + this.lecturer + ", has " + n + " " + st + " [maximum: " + max_students + "]";

        return o;
    }

    private boolean isValidCourse_id(String id) {
        Pattern p = Pattern.compile(idREGEX);
        Matcher m = p.matcher(id);
        boolean b = m.matches();
        if (b) return true;
        else return false;
    }

    private static final String idREGEX = "\\d{6}";

    private String course_name;
    private String course_id;
    private String lecturer;
    private int max_students;
    private int no_students;
}
