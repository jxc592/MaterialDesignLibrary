package com.guoguang.khgl.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table CUS_INFO.
*/
public class CusInfoDao extends AbstractDao<CusInfo, Long> {

    public static final String TABLENAME = "CUS_INFO";

    /**
     * Properties of entity CusInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property SeqNo = new Property(1, String.class, "seqNo", false, "SEQ_NO");
        public final static Property FullName = new Property(2, String.class, "FullName", false, "FULL_NAME");
        public final static Property CertType = new Property(3, String.class, "CertType", false, "CERT_TYPE");
        public final static Property InsertTime = new Property(4, String.class, "InsertTime", false, "INSERT_TIME");
        public final static Property UpdateTime = new Property(5, String.class, "UpdateTime", false, "UPDATE_TIME");
        public final static Property CertID = new Property(6, String.class, "CertID", false, "CERT_ID");
        public final static Property ManageUserName = new Property(7, String.class, "ManageUserName", false, "MANAGE_USER_NAME");
        public final static Property CusInfo = new Property(8, String.class, "CusInfo", false, "CUS_INFO");
    };


    public CusInfoDao(DaoConfig config) {
        super(config);
    }
    
    public CusInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'CUS_INFO' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'SEQ_NO' TEXT," + // 1: seqNo
                "'FULL_NAME' TEXT," + // 2: FullName
                "'CERT_TYPE' TEXT," + // 3: CertType
                "'INSERT_TIME' TEXT," + // 4: InsertTime
                "'UPDATE_TIME' TEXT," + // 5: UpdateTime
                "'CERT_ID' TEXT," + // 6: CertID
                "'MANAGE_USER_NAME' TEXT," + // 7: ManageUserName
                "'CUS_INFO' TEXT);"); // 8: CusInfo
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'CUS_INFO'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, CusInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String seqNo = entity.getSeqNo();
        if (seqNo != null) {
            stmt.bindString(2, seqNo);
        }
 
        String FullName = entity.getFullName();
        if (FullName != null) {
            stmt.bindString(3, FullName);
        }
 
        String CertType = entity.getCertType();
        if (CertType != null) {
            stmt.bindString(4, CertType);
        }
 
        String InsertTime = entity.getInsertTime();
        if (InsertTime != null) {
            stmt.bindString(5, InsertTime);
        }
 
        String UpdateTime = entity.getUpdateTime();
        if (UpdateTime != null) {
            stmt.bindString(6, UpdateTime);
        }
 
        String CertID = entity.getCertID();
        if (CertID != null) {
            stmt.bindString(7, CertID);
        }
 
        String ManageUserName = entity.getManageUserName();
        if (ManageUserName != null) {
            stmt.bindString(8, ManageUserName);
        }
 
        String CusInfo = entity.getCusInfo();
        if (CusInfo != null) {
            stmt.bindString(9, CusInfo);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public CusInfo readEntity(Cursor cursor, int offset) {
        CusInfo entity = new CusInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // seqNo
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // FullName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // CertType
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // InsertTime
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // UpdateTime
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // CertID
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // ManageUserName
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // CusInfo
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, CusInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setSeqNo(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setFullName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setCertType(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setInsertTime(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setUpdateTime(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setCertID(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setManageUserName(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setCusInfo(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(CusInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(CusInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
