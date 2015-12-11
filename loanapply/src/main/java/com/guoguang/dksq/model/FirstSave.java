package com.guoguang.dksq.model;


import com.guoguang.dksq.application.ProLoadApp;
import com.guoguang.dksq.contants.Contants;
import com.guoguang.dksq.database.Guarantee;
import com.guoguang.dksq.database.LoanRecord;
import com.guoguang.dksq.database.PhotoEntity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class FirstSave {
	String SeqNo;
	
	public FirstSave(String seqNo) {
		super();
		SeqNo = seqNo;
	}

	public void save(){
		LoanRecord loanRecord = new LoanRecord();
		loanRecord.setSeqNo(SeqNo);
		loanRecord.setBusinessType(Contants.loanBusinessInfo.getBusinessType());
		loanRecord.setCustomerName(Contants.loanBusinessInfo.getCustomerName());
		loanRecord.setCustomerID(Contants.loanBusinessInfo.getCustomerID());
		loanRecord.setContractFlag(Contants.loanBusinessInfo.getApplyType());
		loanRecord.setUpdateTime(DateUtil.formatDkDate());
		loanRecord.setInputUserID(Contants.registInfo.getInputUserID());
		loanRecord.setInputUserName(Contants.registInfo.getInputUserName());
		ProLoadApp.getmDaoSession().insert(loanRecord);

		if("".equals(Contants.loanBusinessInfo.getInputUserName())){
			Contants.loanBusinessInfo.setInputUserID(Contants.registInfo.getInputUserID());
			Contants.loanBusinessInfo.setIncomeOrgID(Contants.registInfo.getInputOrgID());
			Contants.loanBusinessInfo.setInputUserName(Contants.registInfo.getInputUserName());
			Contants.loanBusinessInfo.setIncomeOrgName(Contants.registInfo.getInputOrgName());
		}
		Contants.loanBusinessInfo.setSeqNo(SeqNo);
		ProLoadApp.getmDaoSession().insert(Contants.loanBusinessInfo);
		
		if(Contants.assureInfo != null){
			Contants.assureInfo.setSeqNo(SeqNo);
			ProLoadApp.getmDaoSession().insert(Contants.assureInfo);
		}
		
		if(Contants.houseInfo!=null){
			Contants.houseInfo.setSeqNo(SeqNo);
			ProLoadApp.getmDaoSession().insert(Contants.houseInfo);
		}

        if(Contants.guranteeinfos !=null){
			for(Guarantee guarantee:Contants.guranteeinfos){
				guarantee.setSeqNo(SeqNo);
				ProLoadApp.getmDaoSession().insert(guarantee);
			}
        }

		if(Contants.toSavePhotoHashMap!=null){
            HashMap<String,HashMap<String,String>> photos = Contants.toSavePhotoHashMap;
            Set<String> firSet = photos.keySet();
            for(Iterator<String> fiIterator=firSet.iterator();fiIterator.hasNext();){
                String firkey = fiIterator.next();
                HashMap<String, String> nodeMap = photos.get(firkey);
                if(nodeMap==null){
                    continue;
                }
                Set<String> nodeSet = nodeMap.keySet();
                for (Iterator iterator = nodeSet.iterator(); iterator.hasNext();) {
                    String nodeKey = (String) iterator.next();
                    String pathValue = nodeMap.get(nodeKey);
                    if(pathValue==null||"".equals(pathValue)){
                        continue;
                    }
                    PhotoEntity mPhotoEntity = new PhotoEntity();
                    mPhotoEntity.setSeqNo(SeqNo);
                    mPhotoEntity.setPhotoTypeCode(firkey);
                    mPhotoEntity.setPhotoPath(pathValue);
                    mPhotoEntity.setCusNumId(Contants.loanBusinessInfo.getCustomerID());
                    mPhotoEntity.setPosition(nodeKey);
                    ProLoadApp.getmDaoSession().insert(mPhotoEntity);
                }
            }
        }
	}
}
