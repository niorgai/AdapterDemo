package niorgai.qiu.nestedadapertdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jianqiu on 11/26/18.
 */
public class OutsideAdapter extends RecyclerView.Adapter<OutsideAdapter.OutsideHolder> {

    private List<Integer> mList;
    private RecyclerView.RecycledViewPool mRecycledViewPool = new RecyclerView.RecycledViewPool() {

        @Nullable
        @Override
        public RecyclerView.ViewHolder getRecycledView(int viewType) {
            RecyclerView.ViewHolder holder = super.getRecycledView(viewType);
            if (holder instanceof InnerAdapter.InnerHolder) {
                InnerAdapter.InnerHolder innerHolder = (InnerAdapter.InnerHolder) holder;
                Log.e("nest pool: ", innerHolder.getAdapterPosition() + " " + holder.toString() + " " + innerHolder.mText.getOnClickListener().toString());
            }
            return holder;
        }
    };

    public void setList(List<Integer> list) {
        mList = list;
    }

    @NonNull
    @Override
    public OutsideHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        OutsideHolder holder = new OutsideHolder(LayoutInflater.from(context).inflate(R.layout.item_outside, viewGroup, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OutsideHolder outsideHolder, int i) {
        outsideHolder.mIndex.setText(String.valueOf(mList.get(i)));
        List<Integer> innerList = new ArrayList<>();
        for (Integer integer = 0; integer < mList.get(i); integer++) {
            innerList.add(integer);
        }
        outsideHolder.mAdapter.setList(innerList);
        outsideHolder.mAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class OutsideHolder extends RecyclerView.ViewHolder {

        TextView mIndex;
        RecyclerView mRecyclerView;
        InnerAdapter mAdapter;

        public OutsideHolder(@NonNull View itemView) {
            super(itemView);
            mIndex = itemView.findViewById(R.id.index);
            mRecyclerView = itemView.findViewById(R.id.recycler_view);
            mRecyclerView.setRecycledViewPool(mRecycledViewPool);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
            mAdapter = new InnerAdapter();
            mRecyclerView.setAdapter(mAdapter);
        }
    }
}
