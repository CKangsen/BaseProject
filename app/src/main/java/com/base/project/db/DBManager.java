package com.base.project.db;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.text.TextUtils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import static android.database.sqlite.SQLiteDatabase.CONFLICT_IGNORE;

/**
 * Created by pradmin on 2017/7/14.
 */

public class DBManager {

    public static final String TAG = DBManager.class.getSimpleName();

    private DBWrapper mDBWrapper;
    private static DBManager DBManagerInstance;
    private ContentResolver mContentResolver;

    DBManager(Context context) {
        mDBWrapper = new DBHelper(context).getDatabase();
        mContentResolver = context.getContentResolver();
    }

    public static DBManager getInstance(Context context) {
        if (DBManagerInstance == null) {
            DBManagerInstance = new DBManager(context);
        }
        return DBManagerInstance;
    }

    //  联系人表的uri
    public static Uri CONTACTS_URI = ContactsContract.Contacts.CONTENT_URI;
    public static Uri RAW_CONTACTS_URI = ContactsContract.RawContacts.CONTENT_URI;
    public static Uri DATA_CONTACTS_URI = ContactsContract.Data.CONTENT_URI;
    public static Uri DATA_CALLLOG_URI = CallLog.Calls.CONTENT_URI;
    public static Uri PHONE_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
    public static Uri EMAIL_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI;



//    /**
//     * 取出映射表中本地部分id
//     *
//     * @return Hashtable<Integer,Integer> K：contact_id V:version
//     */
//    public Hashtable<Integer, Integer> getMappingLocalPart() {
//        long initTime = System.currentTimeMillis();
//        Hashtable<Integer, Integer> hashtable = new Hashtable<>();
//        Cursor localCursor = queryAllInMapping();
//        while (localCursor.moveToNext()) {
//            hashtable.put(localCursor.getInt(IDMappingDB.CONTACT_ID_INDEX),
//                    localCursor.getInt(IDMappingDB.LOCAL_VERSION_INDEX));
//        }
//        LogUtils.d("DBManager", " getMappingLocalPart  Hashtable Time:" + (System.currentTimeMillis() - initTime));
//        return hashtable;
//    }
//
//    /**
//     * 取出映射表中云端部分id
//     *
//     * @return Hashtable<Integer,Integer> K：server_id V:version
//     */
//    public Hashtable<String, Integer> getMappingServerPart() {
//        vOpenid= String.valueOf(ApplicationPrefsManager.getInstance(Factory.get().getApplicationContext()).getOpenid());
//        long initTime = System.currentTimeMillis();
//        Hashtable<String, Integer> hashtable = new Hashtable<>();
//        Cursor localCursor = queryAllInMapping();
//        while (localCursor.moveToNext()) {
//            hashtable.put(localCursor.getString(IDMappingDB.SERVER_INDEX),
//                    localCursor.getInt(IDMappingDB.SERVER_VERSION_INDEX));
//        }
//        LogUtils.d("DBManager", " getMappingServerPart  Hashtable Time:" + (System.currentTimeMillis() - initTime));
//        return hashtable;
//    }
//
//    /**
//     * 通过localids 获取映射表中云端部分id
//     *
//     * @return List<String> serverids
//     * @params localids
//     */
//    public List<String> getMappingServerIdListByLocalId(List<Integer> localids) {
//        vOpenid= String.valueOf(ApplicationPrefsManager.getInstance(Factory.get().getApplicationContext()).getOpenid());
//        long initTime = System.currentTimeMillis();
//        List<String> serverIdList = new ArrayList<>();
//        if (localids == null || localids.size() == 0) {
//            return serverIdList;
//        }
//
//        Cursor cursor = queryRowsInMappingByLocalIDs(localids);
//        while (cursor.moveToNext()) {
//            serverIdList.add(cursor.getString(IDMappingDB.SERVER_INDEX));
//        }
//
//        LogUtils.d("DBManager", " getMappingServerIdListByLocalId  Hashtable Time:" + (System.currentTimeMillis() - initTime));
//        return serverIdList;
//    }
//
//    public List<String> getServerIdListByLocalId(List<Integer> localids) {
//        long initTime = System.currentTimeMillis();
//        List<String> serverIdList = new ArrayList<>();
//        if (localids == null || localids.size() == 0) {
//            return serverIdList;
//        }
//
//        Cursor cursor = queryRowsInMappingByLocalIDs(localids);
//        while (cursor.moveToNext()) {
//            serverIdList.add(IdPatternUtils.formatServerId(cursor.getString(IDMappingDB.SERVER_INDEX),
//                    cursor.getInt(IDMappingDB.SERVER_VERSION_INDEX)));
//        }
//
//        LogUtils.d("DBManager", " getMappingServerIdListByLocalId  Hashtable Time:" + (System.currentTimeMillis() - initTime));
//        return serverIdList;
//    }
//
//    public List<String> getServerIdListWithoutVersionByLocalId(List<Integer> localids) {
//        long initTime = System.currentTimeMillis();
//        List<String> serverIdList = new ArrayList<>();
//        if (localids == null || localids.size() == 0) {
//            return serverIdList;
//        }
//
//        Cursor cursor = queryRowsInMappingByLocalIDs(localids);
//        while (cursor.moveToNext()) {
//            serverIdList.add(cursor.getString(IDMappingDB.SERVER_INDEX));
//        }
//
//        LogUtils.d("DBManager", " getServerIdListWithoutVersionByLocalId  Hashtable Time:" + (System.currentTimeMillis() - initTime));
//        return serverIdList;
//    }
//
//    /**
//     * serverids 获取映射表中云端部分id
//     *
//     * @return List<Integer> localids
//     * @params serverids
//     */
//    public List<Integer> getMappingLocalIdListByServerId(List<String> serverids) {
//        long initTime = System.currentTimeMillis();
//        List<Integer> localIdList = new ArrayList<>();
//        if (serverids == null || serverids.size() == 0) {
//            return localIdList;
//        }
//        Cursor cursor = queryRowsInMappingByServerIDs(serverids);
//        while (cursor.moveToNext()) {
//            localIdList.add(cursor.getInt(IDMappingDB.CONTACT_ID_INDEX));
//        }
//        LogUtils.d("DBManager", " getMappingServerIdListByLocalId  Hashtable Time:" + (System.currentTimeMillis() - initTime));
//        return localIdList;
//    }
//
//
//    public Cursor queryAllInMapping() {
//        vOpenid= String.valueOf(ApplicationPrefsManager.getInstance(Factory.get().getApplicationContext()).getOpenid());
//        Cursor query = mDBWrapper.query(IDMappingDB.CONTACT_MAPPING_TABLE_NAME, null, OPEN_ID+"= ?", new String[]{vOpenid+""}, null, null, null);
//        return query;
//    }
//
//    public Cursor queryRowInMappingByLocalID(int local_id) {
//        vOpenid= String.valueOf(ApplicationPrefsManager.getInstance(Factory.get().getApplicationContext()).getOpenid());
//        Cursor query = mDBWrapper.query(IDMappingDB.CONTACT_MAPPING_TABLE_NAME, null, IDMappingDB.CONTACT_ID + "= ? and "+OPEN_ID+"= ?", new String[]{local_id + "",vOpenid+""},
//                null, null, null);
//        return query;
//    }
//
//    public Cursor queryRowInMappingByServerID(String server_id) {
//        vOpenid= String.valueOf(ApplicationPrefsManager.getInstance(Factory.get().getApplicationContext()).getOpenid());
//        Cursor query = mDBWrapper.query(IDMappingDB.CONTACT_MAPPING_TABLE_NAME, null, IDMappingDB.SERVER_ID + "= ? and "+OPEN_ID+"= ?", new String[]{server_id + "",vOpenid+""},
//                null, null, null);
//        return query;
//    }
//
//    public Cursor queryRowsInMappingByLocalIDs(List<Integer> localids) {
//        vOpenid= String.valueOf(ApplicationPrefsManager.getInstance(Factory.get().getApplicationContext()).getOpenid());
//        String selectionArgs = "(";
//        for (Integer id : localids) {
//            selectionArgs = selectionArgs + id + ",";
//        }
//        selectionArgs = selectionArgs.substring(0, selectionArgs.length() - 1);
//        selectionArgs = selectionArgs + ")";
//
//        Cursor query = mDBWrapper.query(IDMappingDB.CONTACT_MAPPING_TABLE_NAME, null, IDMappingDB.CONTACT_ID + " in " + selectionArgs+" and "+OPEN_ID+"= ?", new String[]{vOpenid+""},
//                null, null, null);
//        return query;
//    }
//
//    public Cursor queryRowsInMappingByServerIDs(List<String> serverids) {
//        vOpenid= String.valueOf(ApplicationPrefsManager.getInstance(Factory.get().getApplicationContext()).getOpenid());
//        String selectionArgs = "( ";
//        for (String id : serverids) {
//            selectionArgs = selectionArgs +"'"+ id +"'"+ ",";
//        }
//        selectionArgs = selectionArgs.substring(0, selectionArgs.length() - 1);
//        selectionArgs = selectionArgs + " )";
//
//        Cursor query = mDBWrapper.query(IDMappingDB.CONTACT_MAPPING_TABLE_NAME, null, IDMappingDB.SERVER_ID + " in " + selectionArgs+" and "+OPEN_ID+"= ?",new String[]{vOpenid+""},
//                null, null, null);
//        return query;
//    }
//
//
//    public Cursor queryRowInServerdeleteByServerID(String server_id) {
//        vOpenid= String.valueOf(ApplicationPrefsManager.getInstance(Factory.get().getApplicationContext()).getOpenid());
//        Cursor query = mDBWrapper.query(ServerDeleteDB.SERVER_DELETE_TABLE_NAME, null, ServerDeleteDB.SEVER_ID + "= ?"+" and "+OPEN_ID+"= ?", new String[]{server_id + "",vOpenid+""},
//                null, null, null);
//        return query;
//    }
//
//
//    /**
//     * 插入服务器删除表
//     * @param id
//     * @param md5
//     * @param data
//     * @return
//     */
//    public long insertInServerdelete(String id, String md5, String data) {
//
//        vOpenid= String.valueOf(ApplicationPrefsManager.getInstance(Factory.get().getApplicationContext()).getOpenid());
//
//        ContentValues cv = new ContentValues();
//        cv.put(ServerDeleteDB.SEVER_ID, id);
//        cv.put(ServerDeleteDB.MD5, md5);
//        cv.put(ServerDeleteDB.CONTACT_DATA, data);
//        cv.put(OPEN_ID, vOpenid);
//        long row = -1;
//        row = mDBWrapper.insertWithOnConflict(ServerDeleteDB.SERVER_DELETE_TABLE_NAME, null, cv, CONFLICT_IGNORE);
//        LogUtils.d("DBManager", "insertInServerdelete row: " + row + "   server_id: " + id);
//
//        return row;
//    }
//
//    public ContactBean getServerDeleteContactBeanByid(String server_id) {
//        vOpenid= String.valueOf(ApplicationPrefsManager.getInstance(Factory.get().getApplicationContext()).getOpenid());
//        ContactBean mContactBean = new ContactBean();
//        Cursor query = mDBWrapper.query(ServerDeleteDB.SERVER_DELETE_TABLE_NAME, new String[]{ServerDeleteDB.CONTACT_DATA}, ServerDeleteDB.SEVER_ID + "= ? and "+ OPEN_ID+"= ?", new String[]{server_id + "",vOpenid+""}, null, null, null);
//        if (query.moveToNext()) {
//            mContactBean = JsonUtils.JsonToContactBean(query.getString(query.getColumnIndex(ServerDeleteDB.CONTACT_DATA)));
//        }
//        return mContactBean;
//    }
//
//    public Hashtable<String,ContactBean> getHashServerDeleteContactBeans() {
//        vOpenid= String.valueOf(ApplicationPrefsManager.getInstance(Factory.get().getApplicationContext()).getOpenid());
//        Hashtable<String,ContactBean> mHashtable = new Hashtable();
//        Cursor query = mDBWrapper.query(ServerDeleteDB.SERVER_DELETE_TABLE_NAME, new String[]{ServerDeleteDB.SEVER_ID, ServerDeleteDB.CONTACT_DATA}, OPEN_ID+"= ?", new String[]{vOpenid+""}, null, null, null);
//        while (query.moveToNext()) {
//            mHashtable.put(query.getString(0),
//                    JsonUtils.JsonToContactBean(query.getString(1)));
//        }
//        return mHashtable;
//    }
//
//    public List<ContactBean> getListServerDeleteContactBeans() {
//        vOpenid= String.valueOf(ApplicationPrefsManager.getInstance(Factory.get().getApplicationContext()).getOpenid());
//        List<ContactBean> mList = new ArrayList<ContactBean>();
//        Cursor query = mDBWrapper.query(ServerDeleteDB.SERVER_DELETE_TABLE_NAME, new String[]{ServerDeleteDB.CONTACT_DATA}, OPEN_ID+"= ?", new String[]{vOpenid+""}, null, null, null);
//        while (query.moveToNext()) {
//            mList.add(JsonUtils.JsonToContactBean(query.getString(query.getColumnIndex(ServerDeleteDB.CONTACT_DATA))));
//        }
//        return mList;
//    }
//
//
//    public long insertInMapping(int contact_id, int local_version, String server_id, int server_version) {
//        vOpenid= String.valueOf(ApplicationPrefsManager.getInstance(Factory.get().getApplicationContext()).getOpenid());
//        ContentValues cv = new ContentValues();
//        cv.put(IDMappingDB.CONTACT_ID, contact_id);
//        cv.put(IDMappingDB.LOCAL_VERSION, local_version);
//        cv.put(IDMappingDB.SERVER_ID, server_id);
//        cv.put(IDMappingDB.SERVER_VERSION, server_version);
//        cv.put(OPEN_ID, vOpenid);
//        long row = -1;
//
//        row = mDBWrapper.insertWithOnConflict(IDMappingDB.CONTACT_MAPPING_TABLE_NAME, null, cv,CONFLICT_IGNORE);
//        LogUtils.d("DBManager", "insertInMapping : " + row + "  contact_id: " + contact_id + "  server_id: " + server_id);
//
//        return row;
//    }
//
//    public long deleteRowInMappingByLocalID(int local_id) {
//        vOpenid= String.valueOf(ApplicationPrefsManager.getInstance(Factory.get().getApplicationContext()).getOpenid());
//        long row = mDBWrapper.delete(IDMappingDB.CONTACT_MAPPING_TABLE_NAME, IDMappingDB.CONTACT_ID + "= ? and "+OPEN_ID+"= ?", new String[]{local_id + "",vOpenid+""});
//        LogUtils.d("DBManager", "deleteRowInMappingByLocalID : " + row+"  id:"+local_id);
//        return row;
//    }
//
//    public long deleteRowInMappingByServerID(String server_id) {
//        vOpenid= String.valueOf(ApplicationPrefsManager.getInstance(Factory.get().getApplicationContext()).getOpenid());
//        long row = mDBWrapper.delete(IDMappingDB.CONTACT_MAPPING_TABLE_NAME, IDMappingDB.SERVER_ID + "= ? and "+OPEN_ID+"= ?", new String[]{server_id + "",vOpenid+""});
//        LogUtils.d("DBManager", "deleteRowInMappingByServerID : " + row+"  id:"+server_id);
//        return row;
//    }
//
//    public long updateRowInMapping(int contact_id, int local_version, String server_id, int server_version) {
//        vOpenid= String.valueOf(ApplicationPrefsManager.getInstance(Factory.get().getApplicationContext()).getOpenid());
//        ContentValues cv = new ContentValues();
//        cv.put(IDMappingDB.CONTACT_ID, contact_id);
//        cv.put(IDMappingDB.LOCAL_VERSION, local_version);
//        cv.put(IDMappingDB.SERVER_ID, server_id);
//        cv.put(IDMappingDB.SERVER_VERSION, server_version);
//        long row = mDBWrapper.update(IDMappingDB.CONTACT_MAPPING_TABLE_NAME, cv, IDMappingDB.CONTACT_ID + "= ? and " + IDMappingDB.SERVER_ID + "= ? and "+OPEN_ID+"= ?",
//                new String[]{contact_id + "", server_id + "",vOpenid+""});
//        LogUtils.d("DBManager", "updateRowInMapping : " + row
//                +"  contact id:"+contact_id+"  local_version:"+local_version+"  server id:"+server_id+"  server_version:"+server_version);
//        return row;
//    }
//
//    public long updateRowInMappingByServerid(int contact_id, int local_version, String server_id, int server_version) {
//        vOpenid= String.valueOf(ApplicationPrefsManager.getInstance(Factory.get().getApplicationContext()).getOpenid());
//        ContentValues cv = new ContentValues();
//        cv.put(IDMappingDB.CONTACT_ID, contact_id);
//        cv.put(IDMappingDB.LOCAL_VERSION, local_version);
//        cv.put(IDMappingDB.SERVER_ID, server_id);
//        cv.put(IDMappingDB.SERVER_VERSION, server_version);
//        long row = mDBWrapper.update(IDMappingDB.CONTACT_MAPPING_TABLE_NAME, cv,  IDMappingDB.SERVER_ID + "= ? and "+OPEN_ID+"= ?",
//                new String[]{server_id + "",vOpenid+""});
//        LogUtils.d("DBManager", "updateRowInMappingByServerid : " + row
//                +"  contact id:"+contact_id+"  local_version:"+local_version+"  server id:"+server_id+"  server_version:"+server_version);
//        return row;
//    }
//
//    /**
//     * 插入一条联系人信息到手机
//     *
//     * @return String contactid+version 拼成的字串
//     *         更新映射表*/
//    public String insertOneContactIntoPhone(ContactBean contactBean) {
//
//        ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();
//
//        // 添加Google账号，这里值为null，表示不添加
//        ContentProviderOperation operation1 = ContentProviderOperation.newInsert(RAW_CONTACTS_URI)
//                .withValue("account_name", "Phone")//
//                .withValue("account_type", "Local Phone Account")//
//                .withValue(ContactsContract.RawContacts.AGGREGATION_MODE, ContactsContract.RawContacts.AGGREGATION_MODE_DISABLED)
//                .withYieldAllowed(true)
//                .build();
//
//        operations.add(operation1);
//
//        // 添加data表中name字段
//        ContactNameBean name = contactBean.getName();
//        if (name != null) {
//            ContentProviderOperation tempOperation = ContentProviderOperation.newInsert(DATA_CONTACTS_URI)
//                    // 第二个参数int previousResult:表示上一个操作的位于operations的第0个索引，
//                    // 所以能够将上一个操作返回的raw_contact_id作为该方法的参数
//                    .withValueBackReference("raw_contact_id", 0)
//                    .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
//                    .withValue(ContactsContract.Data.DATA1, name.getDisplay_name())
//                    .withValue(ContactsContract.Data.DATA2, name.getGiven_name())
//                    .withValue(ContactsContract.Data.DATA3, name.getFamily_name())
//                    .withValue(ContactsContract.Data.DATA5, name.getMiddle_name())
//                    .withValue(ContactsContract.Data.DATA4, name.getPrefix())
//                    .withValue(ContactsContract.Data.DATA6, name.getSuffix())
//                    .withValue(ContactsContract.Data.DATA7, name.getPhonetic_given_name())
//                    .withValue(ContactsContract.Data.DATA8, name.getPhonetic_middle_name())
//                    .withValue(ContactsContract.Data.DATA9, name.getPhonetic_family_name())
//                    .withValue(ContactsContract.Data.DATA10, name.getFull_name_style())
//                    .withValue(ContactsContract.Data.DATA11, name.getPhonetic_name_style())
//                    .withYieldAllowed(true)
//                    .build();
//            operations.add(tempOperation);
//        }
//
//
//        // 添加data表中phone字段
//        List<ContactPhoneBean> phone_list = contactBean.getPhoneList();
//        if (phone_list != null) {
//            for (ContactPhoneBean contactPhoneBean : phone_list) {
//                if (contactPhoneBean.getCustom() != null){
//                    ContentProviderOperation tempOperation = ContentProviderOperation.newInsert(DATA_CONTACTS_URI)
//                            .withValueBackReference("raw_contact_id", 0)
//                            .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
//                            .withValue(ContactsContract.Data.DATA1, contactPhoneBean.getNumber())
//                            //.withValue(ContactsContract.Data.DATA2, 2)
//                            .withValue(ContactsContract.Data.DATA3, contactPhoneBean.getCustom())
//                            .withValue(ContactsContract.Data.DATA4, contactPhoneBean.getNormalized_number())
//                            .withYieldAllowed(true)
//                            .build();
//                    operations.add(tempOperation);
//                } else {
//                    ContentProviderOperation tempOperation = ContentProviderOperation.newInsert(DATA_CONTACTS_URI)
//                            .withValueBackReference("raw_contact_id", 0)
//                            .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
//                            .withValue(ContactsContract.Data.DATA1, contactPhoneBean.getNumber())
//                            .withValue(ContactsContract.Data.DATA2, contactPhoneBean.getType())
//                            .withValue(ContactsContract.Data.DATA3, contactPhoneBean.getCustom())
//                            .withValue(ContactsContract.Data.DATA4, contactPhoneBean.getNormalized_number())
//                            .withYieldAllowed(true)
//                            .build();
//                    operations.add(tempOperation);
//                }
//            }
//        }
//
//        // 添加data表中email字段
//        List<ContactEmailBean> email_list = contactBean.getEmailList();
//        if (email_list != null) {
//            for (ContactEmailBean contactEmailBean : email_list) {
//                ContentProviderOperation tempOperation = ContentProviderOperation.newInsert(DATA_CONTACTS_URI)
//                        .withValueBackReference("raw_contact_id", 0)
//                        .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
//                        .withValue(ContactsContract.Data.DATA1, contactEmailBean.getAddress())
//                        .withValue(ContactsContract.Data.DATA2, contactEmailBean.getType())
//                        .withValue(ContactsContract.Data.DATA3, contactEmailBean.getLabel())
//                        .withValue(ContactsContract.Data.DATA4, contactEmailBean.getDispaly_name())
//                        .withYieldAllowed(true)
//                        .build();
//                operations.add(tempOperation);
//            }
//        }
//
//        // 添加data表中Organization字段
//        List<ContactOrganizationBean> organization_list = contactBean.getOrganizetionList();
//        if (organization_list != null) {
//            for (ContactOrganizationBean contactOrganizationBean : organization_list) {
//                ContentProviderOperation tempOperation = ContentProviderOperation.newInsert(DATA_CONTACTS_URI)
//                        .withValueBackReference("raw_contact_id", 0)
//                        .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)
//                        .withValue(ContactsContract.Data.DATA1, contactOrganizationBean.getCompany())
//                        .withValue(ContactsContract.Data.DATA2, contactOrganizationBean.getType())
//                        .withValue(ContactsContract.Data.DATA3, contactOrganizationBean.getLabel())
//                        .withValue(ContactsContract.Data.DATA4, contactOrganizationBean.getTitle())
//                        .withValue(ContactsContract.Data.DATA5, contactOrganizationBean.getDepartment())
//                        .withValue(ContactsContract.Data.DATA6, contactOrganizationBean.getJob_description())
//                        .withValue(ContactsContract.Data.DATA7, contactOrganizationBean.getSymbol())
//                        .withValue(ContactsContract.Data.DATA8, contactOrganizationBean.getPhonetic_name())
//                        .withValue(ContactsContract.Data.DATA9, contactOrganizationBean.getOffice_location())
//                        .withValue(ContactsContract.Data.DATA10, contactOrganizationBean.getPhonetic_name_style())
//                        .withYieldAllowed(true)
//                        .build();
//                operations.add(tempOperation);
//            }
//        }
//
//        // 添加data表中Im字段
//        List<ContactImBean> im_list = contactBean.getImList();
//        if (im_list != null) {
//            for (ContactImBean imBean : im_list) {
//                ContentProviderOperation tempOperation = ContentProviderOperation.newInsert(DATA_CONTACTS_URI)
//                        .withValueBackReference("raw_contact_id", 0)
//                        .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE)
//                        .withValue(ContactsContract.Data.DATA1, imBean.getData())
//                        .withValue(ContactsContract.Data.DATA2, imBean.getType())
//                        .withValue(ContactsContract.Data.DATA3, imBean.getLabel())
//                        .withValue(ContactsContract.Data.DATA5, imBean.getProtocol())
//                        .withValue(ContactsContract.Data.DATA6, imBean.getCustom_protocol())
//                        .withYieldAllowed(true)
//                        .build();
//                operations.add(tempOperation);
//            }
//        }
//
//        // 添加data表中Nickname字段
//        ContactNickNameBean nickNameBean = contactBean.getNickname();
//        if (nickNameBean != null) {
//            ContentProviderOperation tempOperation = ContentProviderOperation.newInsert(DATA_CONTACTS_URI)
//                    .withValueBackReference("raw_contact_id", 0)
//                    .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Nickname.CONTENT_ITEM_TYPE)
//                    .withValue(ContactsContract.Data.DATA1, nickNameBean.getName())
//                    .withValue(ContactsContract.Data.DATA2, nickNameBean.getType())
//                    .withValue(ContactsContract.Data.DATA3, nickNameBean.getLabel())
//                    .withYieldAllowed(true)
//                    .build();
//            operations.add(tempOperation);
//        }
//
//        // 添加data表中PostalAddress字段
//        List<ContactPostalAddressBean> postalAddress_list = contactBean.getPostalAddressList();
//        if (postalAddress_list != null) {
//            for (ContactPostalAddressBean contactPostalAddressBean : postalAddress_list) {
//                ContentProviderOperation tempOperation = ContentProviderOperation.newInsert(DATA_CONTACTS_URI)
//                        .withValueBackReference("raw_contact_id", 0)
//                        .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE)
//                        .withValue(ContactsContract.Data.DATA1, contactPostalAddressBean.getFormatted_address())
//                        .withValue(ContactsContract.Data.DATA2, contactPostalAddressBean.getType())
//                        .withValue(ContactsContract.Data.DATA3, contactPostalAddressBean.getLabel())
//                        .withValue(ContactsContract.Data.DATA4, contactPostalAddressBean.getStreet())
//                        .withValue(ContactsContract.Data.DATA5, contactPostalAddressBean.getPobox())
//                        .withValue(ContactsContract.Data.DATA6, contactPostalAddressBean.getNeighborhood())
//                        .withValue(ContactsContract.Data.DATA7, contactPostalAddressBean.getCity())
//                        .withValue(ContactsContract.Data.DATA8, contactPostalAddressBean.getRegion())
//                        .withValue(ContactsContract.Data.DATA9, contactPostalAddressBean.getPostcode())
//                        .withValue(ContactsContract.Data.DATA10, contactPostalAddressBean.getCountry())
//                        .withYieldAllowed(true)
//                        .build();
//                operations.add(tempOperation);
//            }
//        }
//
//        // 添加data表中Website字段
//        List<ContactWebsiteBean> website_list = contactBean.getWebsiteList();
//        if (website_list != null) {
//            for (ContactWebsiteBean contactWebsiteBean : website_list) {
//                ContentProviderOperation tempOperation = ContentProviderOperation.newInsert(DATA_CONTACTS_URI)
//                        .withValueBackReference("raw_contact_id", 0)
//                        .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE)
//                        .withValue(ContactsContract.Data.DATA1, contactWebsiteBean.getUrl())
//                        .withValue(ContactsContract.Data.DATA2, contactWebsiteBean.getType())
//                        .withValue(ContactsContract.Data.DATA3, contactWebsiteBean.getLabel())
//                        .withYieldAllowed(true)
//                        .build();
//                operations.add(tempOperation);
//            }
//        }
//
//        // 添加data表中sipaddress字段
//        ContactSipAddressBean contactSipAddressBean = contactBean.getSipAddress();
//        if (contactSipAddressBean != null) {
//            ContentProviderOperation tempOperation = ContentProviderOperation.newInsert(DATA_CONTACTS_URI)
//                    .withValueBackReference("raw_contact_id", 0)
//                    .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.SipAddress.CONTENT_ITEM_TYPE)
//                    .withValue(ContactsContract.Data.DATA1, contactSipAddressBean.getSip_address())
//                    .withValue(ContactsContract.Data.DATA2, contactSipAddressBean.getType())
//                    .withValue(ContactsContract.Data.DATA3, contactSipAddressBean.getLabel())
//                    .withYieldAllowed(true)
//                    .build();
//            operations.add(tempOperation);
//        }
//
//        boolean status = false;
//        ContentProviderResult[] cpResult;
//        String localId ="";
//        try {
//            cpResult = mContentResolver.applyBatch(ContactsContract.AUTHORITY, operations);
//            Uri uri = cpResult[cpResult.length-1].uri;
//            Cursor cu = mContentResolver.query(uri,new String[]{"contact_id","version"},null,null,null);
//            cu.moveToNext();
//            int contactid = cu.getInt(0);
//            int version = cu.getInt(1);
//            localId = IdPatternUtils.formatLocalId(contactid,version);
//            status  = true;
//            LogUtils.d("DBManager", " insertOneContactIntoPhone success :"+localId);
//        } catch (Exception e) {
//            e.printStackTrace();
//            status = false;
//            LogUtils.d("DBManager", " insertOneContactIntoPhone fail :"+contactBean.getName().toJsonString());
//        } finally {
//            return localId;
//        }
//    }
//
//    /**
//     * 批量删除联系人
//     * @param deleteConatctIdList 需要删除的联系人的contact id集合
//     * */
//    public void batchDeleteContacts(List<Integer> deleteConatctIdList) {
//
//        if(deleteConatctIdList == null || deleteConatctIdList.size()==0){
//            LogUtils.d("DBManager", " batchDeleteContacts deleteConatctIdList is null or size = 0.");
//            return;
//        }
//
//        ArrayList<ContentProviderOperation> ops = new ArrayList<>();
//        for (Integer contactId : deleteConatctIdList) {
//
//            //根据姓名求id
//            Cursor cursor = mContentResolver.query(RAW_CONTACTS_URI, new String[]{ContactsContract.Data._ID},
//                    "contact_id=?", new String[]{contactId+""}, null);
//            if(cursor.moveToFirst()){
//                int id = cursor.getInt(0);
//                //根据id删除data中的相应数据
//
//                ops.add(ContentProviderOperation.newDelete(RAW_CONTACTS_URI)
//                        .withSelection("contact_id=?",new String[]{contactId+""})
//                        .withYieldAllowed(true)
//                        .build());
//                ops.add(ContentProviderOperation.newDelete(DATA_CONTACTS_URI)
//                        .withSelection("raw_contact_id=?",new String[]{id + ""})
//                        .withYieldAllowed(true)
//                        .build());
//            }
//        }
//        try {
//            mContentResolver.applyBatch(ContactsContract.AUTHORITY, ops);
//            LogUtils.d("DBManager", " batchDeleteContacts deleteConatctIdList size:"+deleteConatctIdList.size()+"  "+deleteConatctIdList.toString());
//            for (Integer contactid: deleteConatctIdList) {
//                deleteRowInMappingByLocalID(contactid);
//            }
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        } catch (OperationApplicationException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void updateOneContactIntoPhone(ContactBean contactBean){
//        //从云端更新联系人到本地
//        //先删除本地联系人，再插入该联系人云端数据，最后更新映射表
//        String serverid = contactBean.getServerId();
//        String serverversion = contactBean.getServer_version();
//        Cursor cu = queryRowInMappingByServerID(contactBean.getServerId());
//        cu.moveToNext();
//        int contactid = cu.getInt(cu.getColumnIndex(IDMappingDB.CONTACT_ID));
//        batchDeleteContacts(Arrays.asList(contactid));
//        String localid = insertOneContactIntoPhone(contactBean);
//        //插入联系人到本地成功后，插入映射表（因为前面已删除映射，所以插入新的映射）
//        if (!TextUtils.isEmpty(localid)){
//            int tempcontactid = IdPatternUtils.getIdByParseLocalId(localid);
//            int templocalversion = IdPatternUtils.getVersionByParseLocalId(localid);
//            insertInMapping(tempcontactid,templocalversion,serverid, Integer.parseInt(serverversion));
//        }
//
//    }
//
//
//
//    public Cursor queryRowInSMSIDmappingByMD5(String md5){
//        vOpenid= String.valueOf(ApplicationPrefsManager.getInstance(Factory.get().getApplicationContext()).getOpenid());
//        Cursor query = mDBWrapper.query(SMSidMappingDB.SMS_IDMAPPING_TABLE_NAME,null,SMSidMappingDB.MD5+"= ? and "+OPEN_ID+"= ?",new String[]{md5+"",vOpenid+""},
//                null,null,null);
//        return query;
//    }
//
//    /**
//     * 插入短信ID表
//     * @param addList 插入列表
//     * @return
//     */
//    public void insertSMSIDmapping(List<SmsInfo> addList)
//    {
//        vOpenid= String.valueOf(ApplicationPrefsManager.getInstance(Factory.get().getApplicationContext()).getOpenid());
//        if (addList!=null && addList.size()>0) {
//            for (int i=0;i<addList.size();i++) {
//                String md5=addList.get(i).getServer_id();;
//                if (TextUtils.isEmpty(md5)) {
//                    md5= GetMD5Utils.getMD5(addList.get(i).getAddress()
//                            + addList.get(i).getDate() + addList.get(i).getType());
//                }
//
//
//                ContentValues cv = new ContentValues();
//                cv.put(SMSidMappingDB.MD5, md5);
//                cv.put(OPEN_ID, vOpenid);
//                long row = -1;
////                if (queryRowInSMSIDmappingByMD5(md5).moveToNext()) {
////                    LogUtils.d("DBManager", "insertSMSIDmapping : " + row + "   exited");
////                } else {
////                    row = mDBWrapper.insert(SMSidMappingDB.SMS_IDMAPPING_TABLE_NAME, null, cv);
////                }
//                row = mDBWrapper.insertWithOnConflict(SMSidMappingDB.SMS_IDMAPPING_TABLE_NAME, null, cv,CONFLICT_IGNORE);
//                LogUtils.d("DBManager", "insertSMSIDmapping : " + row +  "  md5: " + md5);
//            }
//        }
//    }
//
//
//
//
//    public Cursor querySMSInMapping(){
//        vOpenid= String.valueOf(ApplicationPrefsManager.getInstance(Factory.get().getApplicationContext()).getOpenid());
//        Cursor query = mDBWrapper.query(SMSidMappingDB.SMS_IDMAPPING_TABLE_NAME,null,OPEN_ID+"= ?", new String[]{vOpenid+""},null,null,null);
//        return query;
//    }
//
//    public Hashtable<String,String> getSMSidmappingList()
//    {
//        Hashtable<String,String> hashtable = new Hashtable<>();
//        Cursor localCursor = querySMSInMapping();
//        while (localCursor.moveToNext()){
//            hashtable.put(localCursor.getString(1),
//                    localCursor.getString(1));
//        }
//        return hashtable;
//    }
//
//
//    /**
//     * 插入短信表
//     * @return
//     */
//    public void insertSMS(List<SmsInfo> restoreList )
//    {
//        Uri uri = Uri.parse("content://sms/");
//        for (int i=0;i<restoreList.size();i++) {
//            ContentValues cv = new ContentValues();
//            cv.put("address", restoreList.get(i).getAddress());
//            cv.put("date", restoreList.get(i).getDate());
//            cv.put("body", restoreList.get(i).getBody());
//            cv.put("type", restoreList.get(i).getType());
//            cv.put("type", restoreList.get(i).getType());
//            try {
//                mContentResolver.insert(uri, cv);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public Cursor queryCallLogInMapping(){
//        vOpenid= String.valueOf(ApplicationPrefsManager.getInstance(Factory.get().getApplicationContext()).getOpenid());
//        Cursor query = mDBWrapper.query(CallLogIdMappingDB.CALLLOG_IDMAPPING_TABLE_NAME,null,OPEN_ID+"= ?", new String[]{vOpenid+""},null,null,null);
//        return query;
//    }
//
//    /**
//     * @return Hashtable<String,String> K : callLog id ，V : md5
//     * */
//    public Hashtable<String,String> getCallLogIdMappingList()
//    {
//        vOpenid= String.valueOf(ApplicationPrefsManager.getInstance(Factory.get().getApplicationContext()).getOpenid());
//        Hashtable<String,String> hashtable = new Hashtable<>();
//        Cursor localCursor = queryCallLogInMapping();
//        while (localCursor.moveToNext()){
//            hashtable.put(localCursor.getString(2),
//                    localCursor.getString(2));
//        }
//        return hashtable;
//    }
//
//    /**
//     * 插入通话记录ID表
//     * @param addList 插入列表
//     * @return
//     */
//    public void insertCallLogIDMapping(List<CallLogBean> addList)
//    {
//        vOpenid= String.valueOf(ApplicationPrefsManager.getInstance(Factory.get().getApplicationContext()).getOpenid());
//        if (addList!=null && addList.size()>0) {
//            for (int i=0;i<addList.size();i++) {
//                String id=addList.get(i).getServerid();
//                String md5=addList.get(i).getServerid();;
//                ContentValues cv = new ContentValues();
////                cv.put(CallLogIdMappingDB._ID, id);
//                cv.put(CallLogIdMappingDB.MD5, md5);
//                cv.put(OPEN_ID, vOpenid);
//                long row = -1;
////                if (queryRowInCallLogIDMappingByMD5(md5).moveToNext()){
////                    LogUtils.d("DBManager","insertCallLogIDMapping : "+row+"   exited");
////                } else {
////                    row = mDBWrapper.insert(CallLogIdMappingDB.CALLLOG_IDMAPPING_TABLE_NAME,null,cv);
////                }
//                row = mDBWrapper.insertWithOnConflict(CallLogIdMappingDB.CALLLOG_IDMAPPING_TABLE_NAME,null,cv,CONFLICT_IGNORE);
//                LogUtils.d("DBManager","insertCallLogIDMapping : "+row+"  _id: "+id+"  md5: "+md5);
//            }
//        }
//    }
//
//    public Cursor queryRowInCallLogIDMappingByMD5(String md5){
//        vOpenid= String.valueOf(ApplicationPrefsManager.getInstance(Factory.get().getApplicationContext()).getOpenid());
//        Cursor query = mDBWrapper.query(CallLogIdMappingDB.CALLLOG_IDMAPPING_TABLE_NAME,null,CallLogIdMappingDB.MD5+"= ? and "+OPEN_ID+"= ?",new String[]{md5+"",vOpenid+""},
//                null,null,null);
//        return query;
//    }
//
//    /**
//     * 插入一条联系人信息到手机
//     *
//     * @return true 插入成功，false 插入失败*/
//    public boolean insertOneCallLogIntoPhone(CallLogBean callLogBean) {
//
//        ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();
//
////        Cursor cu = mContentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,new String[]{"raw_contact_id","_id"},
////                "data1 = ?",new String[]{callLogBean.getNumber()}," contact_id desc");
////
////        int raw_contact_id = 0;
////        int data_id = 0;
////        if (cu.getCount()>0){
////            cu.moveToNext();
////            raw_contact_id = cu.getInt(0);
////            data_id = cu.getInt(1);
////        }
//
//        //查询获取本地匹配的联系人姓名
//        Uri uri = Uri.parse("content://com.android.contacts/data/phones/filter/" + callLogBean.getNumber());
//        ContentResolver resolver = mContentResolver;
//        Cursor cursor = resolver.query(uri, new String[]{"display_name"}, null, null, " _id desc");
//        String name = "";
//        if (cursor.moveToFirst()) {
//            name = cursor.getString(0);
//            LogUtils.i(TAG, "insertOneCallLogIntoPhone match name:"+name);
//        }
//        cursor.close();
//
//        // 添加通话记录
//        ContentProviderOperation operation1 = ContentProviderOperation.newInsert(CallLog.Calls.CONTENT_URI)
//                .withValue(CallLog.Calls.NUMBER, callLogBean.getNumber())//
//                .withValue(CallLog.Calls.DATE, callLogBean.getDate())//
//                .withValue(CallLog.Calls.TYPE, callLogBean.getType())//
//                .withValue(CallLog.Calls.NUMBER_PRESENTATION, callLogBean.getPresentation())//
//                .withValue(CallLog.Calls.DURATION, callLogBean.getDuration())//
//                .withValue(CallLog.Calls.DATA_USAGE, callLogBean.getData_usage())//
//                .withValue(CallLog.Calls.FEATURES, callLogBean.getFeatures())//
//                .withValue(CallLog.Calls.CACHED_NAME, name/*callLogBean.getCachename()*/)//
//                .withValue(CallLog.Calls.CACHED_NUMBER_TYPE, callLogBean.getCachenumbertype())//
//                .withValue(CallLog.Calls.CACHED_NUMBER_LABEL, callLogBean.getCachenumberlabel())//
//                .withValue(CallLog.Calls.COUNTRY_ISO, callLogBean.getCountryiso())//
//                .withValue(CallLog.Calls.IS_READ, callLogBean.getIs_read())//
//                .withValue(CallLog.Calls.GEOCODED_LOCATION, callLogBean.getGeocoded_location())//
//                //.withValue("raw_contact_id",raw_contact_id)//TODO
//                //.withValue("data_id",data_id)//
//                .withYieldAllowed(true)
//                .build();
//
//        operations.add(operation1);
//
//        boolean status = false;
//        try {
//            mContentResolver.applyBatch(CallLog.AUTHORITY, operations);
//            status  = true;
//            LogUtils.d(TAG, " insertOneCallLogIntoPhone success :"+callLogBean.toJsonString());
//        } catch (Exception e) {
//            e.printStackTrace();
//            status = false;
//            LogUtils.d(TAG, " insertOneCallLogIntoPhone fail :"+callLogBean.toJsonString());
//        } finally {
//            return status;
//        }
//    }

}
