package ac.in.iitr.mdg.convocation.models;


public class ContactCard {
    public String contact_name, contact_post, contact_number;

    public ContactCard(String contact_name, String contact_post, String contact_number) {
        this.contact_name = contact_name;
        this.contact_post = contact_post;
        this.contact_number = contact_number;
    }

    public String getContact_name() {
        return contact_name;
    }

    public String getContact_post() {
        return contact_post;
    }

    public String getContact_number() {
        return contact_number;
    }
}
