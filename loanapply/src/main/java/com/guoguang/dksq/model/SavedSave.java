package com.guoguang.dksq.model;

import com.aidl.cilent.util.ToastCoustom;
import com.guoguang.dksq.application.ProLoadApp;
import com.guoguang.dksq.contants.Contants;
import com.guoguang.dksq.database.Assure;
import com.guoguang.dksq.database.AssureDao;
import com.guoguang.dksq.database.Guarantee;
import com.guoguang.dksq.database.GuaranteeDao;
import com.guoguang.dksq.database.House;
import com.guoguang.dksq.database.HouseDao;
import com.guoguang.dksq.database.LoanBusiness;
import com.guoguang.dksq.database.LoanBusinessDao;
import com.guoguang.dksq.database.LoanRecord;
import com.guoguang.dksq.database.LoanRecordDao;
import com.guoguang.dksq.database.PhotoEntity;
import com.guoguang.dksq.database.PhotoEntityDao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 根据存在流水的保存草稿方法类，保存草稿首先根据已经存在的流水查询各种信息在表中是否存在，不存在的话则不更新
 * @author tk
 *
 */
public  class SavedSave {
	String SeqNo;
	
	public SavedSave(String seqNo) {
		super();
		SeqNo = seqNo;
	}

	public void save() {
		// TODO Auto-generated method stub
		List<LoanRecord> loanRecords = ProLoadApp.getmDaoSession().
				queryBuilder(LoanRecord.class).
				where(LoanRecordDao.Properties.SeqNo.eq(SeqNo)).list();
		
		if(loanRecords.size()==0){
			ToastCoustom.show("贷款草稿记录存在流水，本地不存在");
		}else if (loanRecords.size()>1) {
			ToastCoustom.show("贷款草稿记录存在流水，本地存在多条记录");
		}else {
			LoanRecord loanRecord = loanRecords.get(0);
			loanRecord.setBusinessType(Contants.loanBusinessInfo.getBusinessType());
			loanRecord.setCustomerName(Contants.loanBusinessInfo.getCustomerName());
			loanRecord.setCustomerID(Contants.loanBusinessInfo.getCustomerID());
			loanRecord.setContractFlag(Contants.loanBusinessInfo.getApplyType());
			loanRecord.setInputUserID(Contants.loanBusinessInfo.getInputUserID());
			loanRecord.setInputUserName(Contants.loanBusinessInfo.getInputUserName());
			loanRecord.setUpdateTime(DateUtil.formatDkDate());
			ProLoadApp.getmDaoSession().update(loanRecord);
		}
		
		List<LoanBusiness> loanBusinesses = ProLoadApp.getmDaoSession().
				queryBuilder(LoanBusiness.class).
				where(LoanBusinessDao.Properties.SeqNo.eq(SeqNo)).list();
		if(loanBusinesses.size()==0){
			ToastCoustom.show("贷款业务信息存在流水，本地不存在");
		}else if (loanBusinesses.size()>1) {
			ToastCoustom.show("贷款业务信息存在流水，本地存在多条记录");
		}else {
			if(Contants.loanBusinessInfo!=null)
				ProLoadApp.getmDaoSession().update(Contants.loanBusinessInfo);
		}

		if(Contants.assureInfo!=null){
			List<Assure> assures = ProLoadApp.getmDaoSession().
					queryBuilder(Assure.class).
					where(AssureDao.Properties.SeqNo.eq(SeqNo)).list();
			if(assures.size()==0){
				Contants.assureInfo.setSeqNo(SeqNo);
				ProLoadApp.getmDaoSession().insert(Contants.assureInfo);
			}else if (assures.size()>1) {
				ToastCoustom.show("贷款担保信息存在流水，本地存在多条记录");
			}else {
				Contants.assureInfo.setId(assures.get(0).getId());
				ProLoadApp.getmDaoSession().update(Contants.assureInfo);
			}
		}else {
			List<Assure> assures = ProLoadApp.getmDaoSession().
					queryBuilder(Assure.class).
					where(AssureDao.Properties.SeqNo.eq(SeqNo)).list();
			if(assures.size()>0){
				ProLoadApp.getmDaoSession().delete(assures.get(0));
			}
		}

		if(Contants.guranteeinfos!=null){
			List<Guarantee> gurantees = ProLoadApp.getmDaoSession().
					queryBuilder(Guarantee.class).
					where(GuaranteeDao.Properties.SeqNo.eq(SeqNo)).list();
			if(gurantees.size()==0){
				//不存在就直接插入
				for(Guarantee guarantee:Contants.guranteeinfos){
					guarantee.setSeqNo(SeqNo);
					ProLoadApp.getmDaoSession().insert(guarantee);
				}
			}else {
				//本地存在，则先删除，再插入
                ProLoadApp.getmDaoSession().getGuaranteeDao().deleteInTx(gurantees);
				for(Guarantee guarantee:Contants.guranteeinfos){
					guarantee.setSeqNo(SeqNo);
					ProLoadApp.getmDaoSession().insert(guarantee);
				}
			}
		}else{
			List<Guarantee> gurantees = ProLoadApp.getmDaoSession().
					queryBuilder(Guarantee.class).
					where(GuaranteeDao.Properties.SeqNo.eq(SeqNo)).list();
			if(gurantees.size()>0){
                ProLoadApp.getmDaoSession().getGuaranteeDao().deleteInTx(gurantees);
			}
		}

		if(Contants.houseInfo!=null){
			List<House> houses = ProLoadApp.getmDaoSession().
					queryBuilder(House.class).
					where(HouseDao.Properties.SeqNo.eq(SeqNo)).list();
			if(houses.size()==0){
				Contants.houseInfo.setSeqNo(SeqNo);
				ProLoadApp.getmDaoSession().insert(Contants.houseInfo);
			}else if (houses.size()>1) {
				ToastCoustom.show("贷款抵押物信息存在流水，本地存在多条记录");
			}else {
				ProLoadApp.getmDaoSession().update(Contants.houseInfo);
			}
		}

		List<PhotoEntity> photoEntities = ProLoadApp.getmDaoSession().
				queryBuilder(PhotoEntity.class).
				where(PhotoEntityDao.Properties.SeqNo.eq(SeqNo)).list();

        if(photoEntities.size() != 0){
            ProLoadApp.getmDaoSession().getPhotoEntityDao().deleteInTx(photoEntities);
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
