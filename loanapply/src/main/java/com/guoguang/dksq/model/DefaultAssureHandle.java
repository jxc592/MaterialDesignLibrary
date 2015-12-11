package com.guoguang.dksq.model;

import com.aidl.cilent.util.ToastCoustom;
import com.guoguang.dksq.contants.Contants;
import com.guoguang.dksq.database.Assure;
import com.guoguang.dksq.view.AssureInfoLayout;

import java.util.HashMap;

/**
 * Created by tk on 2015/11/19.
 */
public class DefaultAssureHandle extends BaseAssureHanle{

    Assure assureinfo;

    public DefaultAssureHandle(AssureInfoLayout assure) {
        super(assure);
    }

    public DefaultAssureHandle(AssureInfoLayout assure, Assure assureinfo) {
        super(assure);
        this.assureinfo = assureinfo;
    }

    @Override
    public boolean checkData() {
        String ContractType = assure.getSp_ContractType().getSelectedCode();
        String GuarantyType = assure.getSp_GuarantyType().getSelectedCode();
        String CertType = assure.getSp_CertType().getSelectedCode();
        String CertID = assure.getEt_CertID().getText().toString();
        String GuarantorName = assure.getEt_GuarantorName().getText().toString();
        String GuarantyCurrency = assure.getSp_GuarantyCurrency().getSelectedCode();
        String GuarantyValue= assure.getEt_GuarantyValue().getText().toString();
        if("".equals(ContractType)){
            ToastCoustom.show("担保类型不能为空");
            return false;
        }
        if("".equals(CertType)){
            ToastCoustom.show("抵押人证件类型不能为空");
            return false;
        }
        if("".equals(CertID)){
            ToastCoustom.show("抵押人证件号码不能为空");
            return false;
        }
        if("".equals(GuarantorName)){
            ToastCoustom.show("抵押人名称不能为空");
            return false;
        }
        if("".equals(GuarantyCurrency)){
            ToastCoustom.show("币种不能为空");
            return false;
        }
        if("".equals(GuarantyValue)){
            ToastCoustom.show("担保金额不能为空");
            return false;
        }

        if(assureinfo == null){
            assureinfo = new Assure();
        }
        assureinfo.setContractType(ContractType);
        assureinfo.setGuarantyType(GuarantyType);
        assureinfo.setCertType(CertType);
        assureinfo.setCertID(CertID);
        assureinfo.setGuarantorName(GuarantorName);
        assureinfo.setGuarantyCurrency(GuarantyCurrency);
        assureinfo.setGuarantyValue(GuarantyValue);
        assureinfo.setUpdateDate(DateUtil.formatDkDate());
        //设置这笔担保是为谁担保的
        assureinfo.setCustomerID(Contants.loanBusinessInfo.getCustomerID());
        return true;
    }

    @Override
    public void initData() {
        Assure assureInfo = null;
        if(assureinfo==null){
            return;
        }
        assureInfo = assureinfo;
        assure.getSp_ContractType().setCode(assureInfo.getContractType());
        assure.getSp_GuarantyType().setCode("050");
        assure.getSp_CertType().setCode(assureInfo.getCertType());
        assure.getSp_GuarantyCurrency().setCode(assureInfo.getGuarantyCurrency());
        assure.getEt_CertID().setText(assureInfo.getCertID());
        assure.getEt_GuarantorName().setText(assureInfo.getGuarantorName());
        assure.getEt_GuarantyValue().setText(assureInfo.getGuarantyValue());
    }

    @Override
    public void saveData() {
        String ContractType = assure.getSp_ContractType().getSelectedCode();
        String GuarantyType = assure.getSp_GuarantyType().getSelectedCode();
        String CertType = assure.getSp_CertType().getSelectedCode();
        String CertID = assure.getEt_CertID().getText().toString();
        String GuarantorName = assure.getEt_GuarantorName().getText().toString();
        String GuarantyCurrency = assure.getSp_GuarantyCurrency().getSelectedCode();
        String GuarantyValue= assure.getEt_GuarantyValue().getText().toString();

        if(assureinfo == null){
            assureinfo = new Assure();
        }
        assureinfo.setContractType(ContractType);
        assureinfo.setGuarantyType(GuarantyType);
        assureinfo.setCertType(CertType);
        assureinfo.setCertID(CertID);
        assureinfo.setGuarantorName(GuarantorName);
        assureinfo.setGuarantyCurrency(GuarantyCurrency);
        assureinfo.setGuarantyValue(GuarantyValue);
        assureinfo.setUpdateDate(DateUtil.formatDkDate());
        //设置这笔担保是为谁担保的
        assureinfo.setCustomerID(Contants.loanBusinessInfo.getCustomerID());
    }


    @Override
    public Object getData() {
        return assureinfo;
    }

    @Override
    public String getGuarantorID() {
        if(assureinfo==null) return "";
        return assureinfo.getGuarantorID();
    }

    @Override
    public void setGuarantorID(String customerid) {
        super.setGuarantorID(customerid);
        if(assureinfo == null){
            assureinfo = new Assure();
        }
        assureinfo.setGuarantorID(customerid);
    }

    @Override
    public void setGuarantyData(HashMap<String, String> map) {
        super.setGuarantyData(map);
    }
}
