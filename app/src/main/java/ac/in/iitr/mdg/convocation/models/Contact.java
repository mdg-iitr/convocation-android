package ac.in.iitr.mdg.convocation.models;

public class Contact {
    public static final int TYPE_CATEGORY = 0;
    public static final int TYPE_CONTACT = 1;
    public String contact_category;
    public ContactCard contactCard;
    private int type;

    public Contact(int type, String contact_category) {
        this.contact_category = contact_category;
        this.type = type;
    }

    public Contact(int type, ContactCard contactCard) {
        this.contactCard = contactCard;
        this.type = type;
    }

    public String getContact_category() {
        return contact_category;
    }

    public ContactCard getContactCard() {
        return contactCard;
    }

    public int getType() {
        return type;
    }

}
