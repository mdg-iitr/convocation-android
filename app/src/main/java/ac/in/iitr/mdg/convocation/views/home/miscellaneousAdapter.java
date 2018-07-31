package ac.in.iitr.mdg.convocation.views.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

        import java.util.List;

import ac.in.iitr.mdg.convocation.views.R;

public class miscellaneousAdapter extends RecyclerView.Adapter<miscellaneousAdapter.MyViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(miscellaneousClass miscellaneous);
    }

    private final miscellaneousAdapter.OnItemClickListener listener;

    private List<miscellaneousClass> miscellaneousList;
        
            public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

                public MyViewHolder(View view) {
                    super(view);
                    title = (TextView) view.findViewById(R.id.miscellaneousText);
                }

                public void bind(final miscellaneousClass miscellaneous, final miscellaneousAdapter.OnItemClickListener listener){
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override public void onClick(View v) {
                            listener.onItemClick(miscellaneous);
                        }
                    });
                }
    }

        
            public miscellaneousAdapter(List<miscellaneousClass> miscellaneousList,miscellaneousAdapter.OnItemClickListener listener) {
                this.miscellaneousList = miscellaneousList;
                this.listener = listener;
            }
        
            @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.miscellaneouscard, parent, false);
        
                return new MyViewHolder(itemView);
            }
        
            @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
                miscellaneousClass miscellaneous = miscellaneousList.get(position);
                holder.title.setText(miscellaneous.getTitle());
            }
        
            @Override
    public int getItemCount() {
                return miscellaneousList.size();
            }
        }
