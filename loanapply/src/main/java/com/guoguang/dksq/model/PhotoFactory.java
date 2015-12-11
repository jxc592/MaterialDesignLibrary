package com.guoguang.dksq.model;

import com.aidl.cilent.entity.PhotoEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 生成信用卡照片的实体的类
 * @author tk
 *
 */
public class PhotoFactory {

	public static List<PhotoEntity> CreateEntities(String id) {
		// TODO Auto-generated method stub
		List<PhotoEntity> aEntities=new ArrayList<>();
		PhotoEntity entity=new PhotoEntity();
		entity.setBp(true);
		entity.setPhotoTypeCode("90");
		entity.setPhotoTypeName("身份证明材料");
		entity.setCusNumId(id);
		
		aEntities.add(entity);
		
		PhotoEntity entity1=new PhotoEntity();
		entity1.setBp(false);
		entity1.setPhotoTypeCode("91");
		entity1.setPhotoTypeName("收入证明材料");
		entity1.setCusNumId(id);
		aEntities.add(entity1);
		
		PhotoEntity entity2=new PhotoEntity();
		entity2.setBp(true);
		entity2.setPhotoTypeCode("92");
		entity2.setPhotoTypeName("征信资料");
		entity2.setCusNumId(id);
		aEntities.add(entity2);
		
		PhotoEntity entity3=new PhotoEntity();
		entity3.setBp(false);
		entity3.setPhotoTypeCode("93");
		entity3.setPhotoTypeName("用途证明文件");
		entity3.setCusNumId(id);
		aEntities.add(entity3);
		
		PhotoEntity entity4=new PhotoEntity();
		entity4.setBp(false);
		entity4.setPhotoTypeCode("94");
		entity4.setPhotoTypeName("其他材料");
		entity4.setCusNumId(id);
		aEntities.add(entity4);
		
		return aEntities;
	}

	public static HashMap<String, String> photo_Key_value;
	static{ 
		photo_Key_value = new HashMap<>();
		photo_Key_value.put("90", "身份证明材料");
		photo_Key_value.put("91", "收入证明材料");
		photo_Key_value.put("92", "征信材料");
		photo_Key_value.put("93", "用途证明文件");
		photo_Key_value.put("94", "其他");
//		photo_Key_value.put("95", "公积金账户证明");
//		photo_Key_value.put("96", "社保缴纳证明");
//		photo_Key_value.put("97", "房产");
//		photo_Key_value.put("98", "车产");
//		photo_Key_value.put("99", "工作证明");
	}

	
	

}
