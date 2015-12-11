package com.guoguang.dksq.customerview;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.guoguang.dksq.R;
import com.guoguang.dksq.view.AssureInfoLayout;

/**
 * Created by tk on 2015/11/26.
 */
public class AddAssureDialog<T extends Object> extends Dialog{
    FrameLayout content;
    AssureInfoLayout assureLayout;
    Button btn_sure,btn_cancel;


    public AddAssureDialog(Context context,int type ,T guarant) {
        super(context, R.style.alert_dialog);
        setContentView(R.layout.dialog_guarant);

        content = (FrameLayout) findViewById(R.id.fl_guarantcontent);
        if(type == 1){
            assureLayout =new AssureInfoLayout(context,AssureInfoLayout.DefauleType,guarant);
        }else if(type ==2){
            assureLayout =new AssureInfoLayout(context,AssureInfoLayout.GurantType,guarant);
        }
        content.addView(assureLayout);

        btn_sure= (Button) findViewById(R.id.btn_sure);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_sure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(!assureLayout.checkData()){
                    return;
                }
                if (onSureClickListener != null) {
                    onSureClickListener.onSureClick(AddAssureDialog.this,assureLayout.getData());
                } else {
                    AddAssureDialog.this.dismiss();
                }
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                AddAssureDialog.this.dismiss();
            }
        });
    }


    SureClick onSureClickListener;

    public AddAssureDialog setOnSureClickListener(SureClick onSureClickListener) {
        this.onSureClickListener = onSureClickListener;
        return AddAssureDialog.this;
    }

    public interface SureClick {
        void onSureClick(AddAssureDialog dialog, Object data);
    }
}
