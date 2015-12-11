package com.guoguang.dksq.model;

import com.guoguang.dksq.view.AssureInfoLayout;

import java.util.HashMap;

/**
 * Created by tk on 2015/11/19.
 */
public class BaseAssureHanle implements AssureHandle{
    public AssureInfoLayout assure;

    public BaseAssureHanle(AssureInfoLayout assure) {
        this.assure = assure;
    }

    @Override
    public boolean checkData() {
        return true;
    }

    @Override
    public void initData() {

    }
    @Override
    public void saveData() {
    }

    @Override
    public Object getData() {
        return null;
    }

    @Override
    public String getGuarantorID() {
        return null;
    }

    @Override
    public void setGuarantorID(String customerid) {

    }

    @Override
    public void setGuarantyData(HashMap<String, String> map) {

    }
}
