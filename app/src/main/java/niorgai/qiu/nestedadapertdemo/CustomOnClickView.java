package niorgai.qiu.nestedadapertdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by jianqiu on 12/19/18.
 */
public class CustomOnClickView extends TextView {

    private View.OnClickListener mOnClickListener;

    public CustomOnClickView(Context context) {
        super(context);
    }

    public CustomOnClickView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomOnClickView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
        super.setOnClickListener(onClickListener);
    }

    public OnClickListener getOnClickListener() {
        return mOnClickListener;
    }
}
