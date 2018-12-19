package niorgai.qiu.nestedadapertdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by jianqiu on 11/26/18.
 */
public class InnerAdapter extends RecyclerView.Adapter<InnerAdapter.InnerHolder> {

    private List<Integer> mList;

    public void setList(List<Integer> list) {
        mList = list;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        final InnerHolder holder = new InnerHolder(LayoutInflater.from(context).inflate(R.layout.item_inner, viewGroup, false));
        holder.mText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("nest inner: ", holder.getAdapterPosition() + " " + mList.size() + " " + this.toString());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder viewHolder, int i) {
        viewHolder.mText.setText(String.valueOf(mList.get(i)));
        Log.e("nest bind: ", i + " " + viewHolder.getAdapterPosition() + " " + mList.size() + " " + viewHolder.mText.getOnClickListener().toString());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class InnerHolder extends RecyclerView.ViewHolder {

        CustomOnClickView mText;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            mText = itemView.findViewById(R.id.text);
        }
    }
}
