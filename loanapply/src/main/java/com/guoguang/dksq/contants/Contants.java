package com.guoguang.dksq.contants;

import android.os.Environment;

import com.aidl.cilent.AidlClient;
import com.guoguang.dksq.database.Assure;
import com.guoguang.dksq.database.Guarantee;
import com.guoguang.dksq.database.House;
import com.guoguang.dksq.database.LoanBusiness;
import com.guoguang.dksq.entity.AreaEntity;
import com.guoguang.dksq.entity.LoanInfoEntity;
import com.guoguang.dksq.entity.RegistInfo;

import java.util.HashMap;
import java.util.List;

public class Contants {
//	public static CustomInfo tempCustomInfo = null;
//	public static AreaEntity areaEntity = null;
	public static AidlClient mAidlClient =null;
	public static String header =null; 
	public static boolean debug = false;
	
	public static RegistInfo registInfo;
	
	public static boolean YesNoLoaded=false;
	
	public static LoanInfoEntity loanInfoEntity;
	
	public static LoanBusiness loanBusinessInfo = null;
	public static Assure assureInfo = null;
	public static House houseInfo = null;
	public static List<Guarantee> guranteeinfos = null;
	public static LoanInfoEntity getLoanInfoEntity() {
		return loanInfoEntity;
	}

	public static void setLoanInfoEntity(LoanInfoEntity loanInfoEntity) {
		Contants.loanInfoEntity = loanInfoEntity;
		if(loanInfoEntity == null){
			return;
		}
		loanBusinessInfo = loanInfoEntity.getLoanBusiness();
		assureInfo = loanInfoEntity.getAssure();
		houseInfo = loanInfoEntity.getHouse();
		guranteeinfos = loanInfoEntity.getGuarantees();
	}
	
	public static void clear(){
		loanInfoEntity = null;
		assureInfo=null;
		houseInfo=null;
		guranteeinfos = null;
		loanBusinessInfo=null;
		Contants.toSavePhotoHashMap = null;
	}

	public static final String savePicPath= Environment
			.getExternalStorageDirectory() + "/hbBank/dksq/";
	public static HashMap<String,HashMap<String,String>> toSavePhotoHashMap;


	public static  String UPLOADFILE_IP = "192.168.51.151";
	public static String FILE_DONW_IP ="192.168.1.151";
	public static  int UPLOADFILE_PORT = 8001;
	public static  int FILE_UPDOWN_PORT = 80;

	public static int defaultcolor;
	public static int othercolor;


	public static AreaEntity areaEntity;
}
