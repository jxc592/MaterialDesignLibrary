package com.guoguang.dksq.widget;

import android.content.Context;
import android.widget.EditText;

public class TextField extends EditText {

	public TextField(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

//	String inText=""; 
//	
//
//	public TextField(Context context, AttributeSet attrs, int defStyleAttr) {
//		super(context, attrs, defStyleAttr);
//		// TODO Auto-generated constructor stub
//		initThis();
//	}
//
//	public TextField(Context context, AttributeSet attrs) {
//		super(context, attrs);
//		// TODO Auto-generated constructor stub
//		initThis();
//	}
//
//	public TextField(Context context) {
//		super(context);
//		// TODO Auto-generated constructor stub
//		initThis();
//	}
//	TextFieldDialog textFieldDialog;
//	void initThis(){
//		setMaxEms(10);
//		setFocusable(true);
//		setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				if(textFieldDialog == null){
//					textFieldDialog=new TextFieldDialog(v.getContext(),R.style.alert_dialog ,inText,TextField.this);
//				}else {
//					textFieldDialog.setInText(inText);
//				}
//				textFieldDialog.show();
//			}
//		});
//	}
//	
//	@Override
//	public Editable getText() {
//		// TODO Auto-generated method stub
//		if(textFieldDialog == null){
//			return super.getText();
//		}else {
//			return Editable.Factory.getInstance().newEditable(inText);
//		}
//	}
//	@Override
//	public void setText(CharSequence text, BufferType type) {
//		// TODO Auto-generated method stub
//		super.setText(text, type);
//		if(text ==null){
//			return;
//		}
//		this.inText =text.toString();
//	}
//	
//
//	 @Override
//	    protected boolean getDefaultEditable() {
//	        return false;
//	    }
//
//	    @Override
//	    protected MovementMethod getDefaultMovementMethod() {
//	    	return null;
//	        //return ArrowKeyMovementMethod.getInstance();
//	    }
//
//
//	    /**
//	     * Convenience for {@link Selection#setSelection(Spannable, int, int)}.
//	     */
//	    public void setSelection(int start, int stop) {
//	        Selection.setSelection(getText(), start, stop);
//	    }
//
//	    /**
//	     * Convenience for {@link Selection#setSelection(Spannable, int)}.
//	     */
//	    public void setSelection(int index) {
//	        Selection.setSelection(getText(), index);
//	    }
//
//	    /**
//	     * Convenience for {@link Selection#selectAll}.
//	     */
//	    public void selectAll() {
//	        Selection.selectAll(getText());
//	    }
//
//	    /**
//	     * Convenience for {@link Selection#extendSelection}.
//	     */
//	    public void extendSelection(int index) {
//	        Selection.extendSelection(getText(), index);
//	    }
//
//	  
//
//	    @Override
//	    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
//	        super.onInitializeAccessibilityEvent(event);
//	      //  event.setClassName(EditText.class.getName());
//	    }
//
//	    @Override
//	    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
//	        super.onInitializeAccessibilityNodeInfo(info);
//	      //  info.setClassName(EditText.class.getName());
//	    }
//
//	    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//		@Override
//	    public boolean performAccessibilityAction(int action, Bundle arguments) {
//	    	return super.performAccessibilityAction(action, arguments);
////	        switch (action) {
////	            case AccessibilityNodeInfo.ACTION_SET_TEXT: {
////	                CharSequence text = (arguments != null) ? arguments.getCharSequence(
////	                        AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE) : null;
////	                setText(text);
////	                if (text != null && text.length() > 0) {
////	                    setSelection(text.length());
////	                }
////	                return true;
////	            }
////	            default: {
////	                return super.performAccessibilityAction(action, arguments);
////	            }
////	        }
//	    }
	
}
