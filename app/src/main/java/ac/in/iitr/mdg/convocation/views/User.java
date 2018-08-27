package ac.in.iitr.mdg.convocation.views;

import android.provider.ContactsContract;

public class User {

    private String Name;
    private String EnrollmentNo;
    private String Email;
    private String Phone_no;
    private String bank_trans_id;
    private String address;
    private String Mob_no;
    private String EmailId;
    private Integer people_details;
    private Integer vehicle_details;
    private Boolean terms1;
    private Boolean terms2;

    public User(){

    }

    public User(String name, String enno, String email,String ph_no, String bank,String addr,String mob_no,String emailid,Integer people, Integer vehicles,Boolean terms1, Boolean terms2){

        this.Name = name;
        this.EnrollmentNo = enno;
        this.Email =email;
        this.Phone_no =ph_no;
        this.bank_trans_id =bank;
        this.address =addr;
        this.Mob_no = mob_no;
        this.EmailId =emailid;
        this.people_details =people;
        this.vehicle_details= vehicles;
        this.terms1 =terms1;
        this.terms2 =terms2;
    }


    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public Integer getPeople_details() {
        return people_details;
    }

    public Boolean getTerms1() {
        return terms1;
    }

    public String getEmail() {
        return Email;
    }

    public String getBank_trans_id() {
        return bank_trans_id;
    }

    public String getAddress() {
        return address;
    }

    public String getEnrollmentNo() {
        return EnrollmentNo;
    }

    public String getEmailId() {
        return EmailId;
    }

    public String getMob_no() {
        return Mob_no;
    }

    public Integer getVehicle_details() {
        return vehicle_details;
    }

    public String getPhone_no() {
        return Phone_no;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBank_trans_id(String bank_trans_id) {
        this.bank_trans_id = bank_trans_id;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }

    public void setEnrollmentNo(String enrollmentNo) {
        EnrollmentNo = enrollmentNo;
    }

    public void setPhone_no(String phone_no) {
        Phone_no = phone_no;
    }

    public Boolean getTerms2() {
        return terms2;
    }

    public void setMob_no(String mob_no) {
        Mob_no = mob_no;
    }

    public void setPeople_details(Integer people_details) {
        this.people_details = people_details;
    }

    public void setTerms1(Boolean terms1) {
        this.terms1 = terms1;
    }

    public void setVehicle_details(Integer vehicle_details) {
        this.vehicle_details = vehicle_details;
    }

    public void setTerms2(Boolean terms2) {
        this.terms2 = terms2;
    }
}
