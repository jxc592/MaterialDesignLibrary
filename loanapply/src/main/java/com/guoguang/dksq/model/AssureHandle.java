package com.guoguang.dksq.model;

import java.util.HashMap;

/**
 * Created by tk on 2015/11/19.
 */
public interface AssureHandle {
    void initData();
    void saveData();
    boolean checkData();
    Object getData();

    String getGuarantorID();
    void setGuarantorID(String GuarantorID);
    void setGuarantyData(HashMap<String,String> map);
}
