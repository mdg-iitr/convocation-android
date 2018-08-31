package ac.in.iitr.mdg.convocation.responsemodels;

public class DegreeCard {

    private String branchName, numberOfStudents;

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
