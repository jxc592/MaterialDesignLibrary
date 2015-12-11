package com.guoguang.dksq.model;

import com.aidl.cilent.util.ToastCoustom;
import com.guoguang.dksq.contants.Contants;
import com.guoguang.dksq.database.Guarantee;
import com.guoguang.dksq.view.AssureInfoLayout;

import java.util.HashMap;

/**
 * Created by tk on 2015/11/19.
 */
public class GurantAssureHandle extends BaseAssureHanle{

    Guarantee guarantee;

    public GurantAssureHandle(AssureInfoLayout assure) {
        super(assure);
    }

    public GurantAssureHandle(AssureInfoLayout assure, Guarantee guarantee) {
        super(assure);
        this.guarantee = guarantee;
    }

    @Override
    public boolean checkData() {
        String CertID = assure.getEt_CertID().getText().toString();
        String ContractSerialNo = assure.getEt_ContractSerialNo().getText().toString();
        String GuarantorName =  assure.getEt_GuarantorName().getText().toString();
        String GuarantyValue =  assure.getEt_GuarantyValue().getText().toString();
        String LoanCardNo =  assure.getEt_LoanCardNo().getText().toString();
        String GuarantyType = assure.getSp_GuarantyType().getSelectedCode();
        String ContractType =  assure.getSp_ContractType().getSelectedCode();
        String CertType = assure.getSp_CertType().getSelectedCode();
        String GuarantyCurrency = assure.getSp_GuarantyCurrency().getSelectedCode();
        String GuarantyFreeType = assure.getSp_GuarantyFreeType().getSelectedCode();
        String VouchWays =  assure.getSp_VouchWays().getSelectedCode();
        String GuarantyStage = assure.getSp_GuarantyStage().getSelectedCode();

        if("".equals(ContractType)){
            ToastCoustom.show("担保类型不能为空");
            return false;
        }
        if("".equals(CertType)){
            ToastCoustom.show("保证人证件类型不能为空");
            return false;
        }
        if("".equals(CertID)){
            ToastCoustom.show("保证人证件号码不能为空");
            return false;
        }
        if("".equals(GuarantorName)){
            ToastCoustom.show("保证人名称不能为空");
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
        if(guarantee == null){
            guarantee = new Guarantee();
        }
        guarantee.setCertID(CertID);
        guarantee.setCertType(CertType);
        guarantee.setContractType(ContractType);
        guarantee.setGuarantyType(GuarantyType);
        guarantee.setContractSerialNo(ContractSerialNo);
        guarantee.setGuarantyCurrency(GuarantyCurrency);
        guarantee.setLoanCardNo(LoanCardNo);
        guarantee.setGuarantorName(GuarantorName);
        guarantee.setGuarantyValue(GuarantyValue);
        guarantee.setGuarantyFreeType(GuarantyFreeType);
        guarantee.setVouchWays(VouchWays);
        guarantee.setGuarantyStage(GuarantyStage);
        guarantee.setCustomerID(Contants.loanBusinessInfo.getCustomerID());
        return super.checkData();
    }

    @Override
    public void initData() {
        super.initData();
        if(guarantee == null){
            return;
        }
        assure.getEt_CertID().setText(guarantee.getCertID());
        assure.getEt_ContractSerialNo().setText(guarantee.getContractSerialNo());
        assure.getEt_GuarantorName().setText(guarantee.getGuarantorName());
        assure.getEt_GuarantyValue().setText(guarantee.getGuarantyValue());
        assure.getEt_LoanCardNo().setText(guarantee.getLoanCardNo());

        //担保类型 为保证 010010
        assure.getSp_GuarantyType().setCode("010010");
        assure.getSp_ContractType().setCode(guarantee.getContractType());
        assure.getSp_CertType().setCode(guarantee.getCertType());
        assure.getSp_GuarantyCurrency().setCode(guarantee.getGuarantyCurrency());
        assure.getSp_GuarantyFreeType().setCode(guarantee.getGuarantyFreeType());
        assure.getSp_VouchWays().setCode(guarantee.getVouchWays());
        assure.getSp_GuarantyStage().setCode(guarantee.getGuarantyStage());
        assure.getEt_GuarantyDataName().setText(guarantee.getGuarantyDataName());

    }

    @Override
    public void saveData() {
        super.saveData();

        String CertID = assure.getEt_CertID().getText().toString();
        String ContractSerialNo = assure.getEt_ContractSerialNo().getText().toString();
        String GuarantorName =  assure.getEt_GuarantorName().getText().toString();
        String GuarantyValue =  assure.getEt_GuarantyValue().getText().toString();
        String LoanCardNo =  assure.getEt_LoanCardNo().getText().toString();
        String GuarantyType = assure.getSp_GuarantyType().getSelectedCode();
        String ContractType =  assure.getSp_ContractType().getSelectedCode();
        String CertType = assure.getSp_CertType().getSelectedCode();
        String GuarantyCurrency = assure.getSp_GuarantyCurrency().getSelectedCode();
        String GuarantyFreeType = assure.getSp_GuarantyFreeType().getSelectedCode();
        String VouchWays =  assure.getSp_VouchWays().getSelectedCode();
        String GuarantyStage = assure.getSp_GuarantyStage().getSelectedCode();

        if(guarantee == null){
            guarantee = new Guarantee();
        }
        guarantee.setCertID(CertID);
        guarantee.setCertType(CertType);
        guarantee.setContractType(ContractType);
        guarantee.setGuarantyType(GuarantyType);
        guarantee.setContractSerialNo(ContractSerialNo);
        guarantee.setGuarantyCurrency(GuarantyCurrency);
        guarantee.setLoanCardNo(LoanCardNo);
        guarantee.setGuarantorName(GuarantorName);
        guarantee.setGuarantyValue(GuarantyValue);
        guarantee.setGuarantyFreeType(GuarantyFreeType);
        guarantee.setVouchWays(VouchWays);
        guarantee.setGuarantyStage(GuarantyStage);
        guarantee.setCustomerID(Contants.loanBusinessInfo.getCustomerID());
    }

    @Override
    public Object getData() {
        return guarantee;
    }


    @Override
    public String getGuarantorID() {
        if(guarantee ==null){
            return "";
        }
        return guarantee.getGuarantorID();
    }

    @Override
    public void setGuarantorID(String customerid) {
        super.setGuarantorID(customerid);
        if(guarantee==null){
            guarantee = new Guarantee();
        }
        guarantee.setGuarantorID(customerid);
    }

    @Override
    public void setGuarantyData(HashMap<String, String> map) {
        super.setGuarantyData(map);
        if(guarantee==null){
            guarantee = new Guarantee();
        }
        guarantee.setGuarantyData(map.get("code"));
        guarantee.setGuarantyDataName(map.get("value"));
    }
}
