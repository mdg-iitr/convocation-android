package ac.in.iitr.mdg.convocation.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by suyash on 8/24/18.
 */

public class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<Contact> list;

    public ContactAdapter(Context context, List<Contact> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case Contact.TYPE_CATEGORY:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_contact_category, parent, false);
                return new ContactCategoryHolder(view);

            case Contact.TYPE_CONTACT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_contact, parent, false);
                return new ContactHolder(view);

            default:
                return null;
        }

    }

    @Override
    public int getItemViewType(int position) {
        switch (list.get(position).getType()) {
            case 0:
                return Contact.TYPE_CATEGORY;
            case 1:
                return Contact.TYPE_CONTACT;
            default:
                return -1;
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Contact mylist = list.get(position);
        if (mylist != null) {
            if (mylist.getType() == Contact.TYPE_CONTACT) {
                ((ContactHolder) holder).contact_name.setText(mylist.getContactCard().getContact_name());
                ((ContactHolder) holder).contact_post.setText(mylist.getContactCard().getContact_post());
                ((ContactHolder) holder).contact_number.setText(mylist.getContactCard().getContact_number());
            } else if (mylist.getType() == Contact.TYPE_CATEGORY) {
                ((ContactCategoryHolder) holder).category.setText(mylist.getContact_category());
            }
        }
    }

    @Override
    public int getItemCount() {
        int arr = 0;
        try {
            if (list.size() == 0) {
                arr = 0;
            } else {
                arr = list.size();
            }
        } catch (Exception e) {

        }
        return arr;
    }


    class ContactHolder extends RecyclerView.ViewHolder {
        TextView contact_name, contact_post, contact_number;

        public ContactHolder(View itemView) {
            super(itemView);
            contact_name = itemView.findViewById(R.id.contactCard_name);
            contact_post = itemView.findViewById(R.id.contactCard_post);
            contact_number = itemView.findViewById(R.id.contactCard_mobile);
        }
    }

    class ContactCategoryHolder extends RecyclerView.ViewHolder {
        public TextView category;

        public ContactCategoryHolder(View itemView) {
            super(itemView);
            category = (TextView) itemView.findViewById(R.id.contact_category);
        }
    }
}

