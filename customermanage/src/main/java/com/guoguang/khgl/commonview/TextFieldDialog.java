package com.guoguang.khgl.commonview;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.guoguang.dkkhgl.R;
import com.guoguang.khgl.widget.TextField;

public class TextFieldDialog extends Dialog {
	TextField editText;
	String inText;
	public TextFieldDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public TextFieldDialog(Context context,int strle, String inText ,TextField editText) {
		super(context,strle);
		this.inText = inText;
		this.editText =editText;
	}

	public String getInText() {
		return inText;
	}

	public void setInText(String inText) {
		this.inText = inText;
		inputEditText.setText(inText);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onTouchEvent(event);
	}
		
	EditText inputEditText;
	Button sureButton,cancelButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_textfield);
		View vewiView = getWindow().getDecorView();
		inputEditText = (EditText) findViewById(R.id.et_dainput);
		if(inText!=null)
			inputEditText.setText(inText);
		sureButton =(Button) findViewById(R.id.confirm_button);
		cancelButton= (Button) findViewById(R.id.cancel_button);
		cancelButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TextFieldDialog.this.dismiss();
			}
		});
		sureButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				editText.setText(inputEditText.getText());
				TextFieldDialog.this.dismiss();
			}
		});
		//vewiView.star
		//vewiView.setOnDragListener(l)
		//ObjectAnimator.ofFloat(vewiView, propertyName, values)
	}
	
}
