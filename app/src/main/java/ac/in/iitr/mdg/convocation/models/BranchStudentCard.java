package ac.in.iitr.mdg.convocation.models;

public class BranchStudentCard {
    private String student_name, student_enrollment;

    public BranchStudentCard(String student_name, String student_enrollment) {
        this.student_name = student_name;
        this.student_enrollment = student_enrollment;
    }

    public String getStudent_name() {
        return student_name;
    }

    public String getStudent_enrollment() {
        return student_enrollment;
    }

}
