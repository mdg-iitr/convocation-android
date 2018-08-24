package ac.in.iitr.mdg.convocation.views;

/**
 * Created by suyash on 8/24/18.
 */

public class Contact {
    public String contact_category;
    public ContactCard contactCard;
    public static final int TYPE_CATEGORY = 0;
    public static final int TYPE_CONTACT = 1;
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
