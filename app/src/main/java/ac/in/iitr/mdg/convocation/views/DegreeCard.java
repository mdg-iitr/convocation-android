package ac.in.iitr.mdg.convocation.views;

/**
 * Created by suyash on 8/1/18.
 */

public class DegreeCard {
    String branchName, numberOfStudents;


    public DegreeCard(String branchName, String numberOfStudents) {
        this.branchName = branchName;
        this.numberOfStudents = numberOfStudents;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getNumberOfStudents() {
        return numberOfStudents;
    }

}
