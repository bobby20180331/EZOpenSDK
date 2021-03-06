package com.videogo.been;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AlarmContant {
    public static final int MESSAGE_TYPE_TRUCK_IDENTITY = 0 ;
    public static final int MESSAGE_TYPE_ILLEGAL_BUILDING= 1 ;
    public static final int MESSAGE_TYPE_ILLEGAL_PLANT= 2 ;
    public static final int MESSAGE_TYPE_STRAW_BURNING= 3 ;
    public static final int MESSAGE_TYPE_RIVER_MONITOR= 4 ;
    public static final int MESSAGE_TYPE_COMPANY_MANAGE= 5 ;

    public static final int USER_TYPE_CHENGGUAN = 6;
    public static final int USER_TYPE_SHIWUJU = 7;
    public static final int USER_TYPE_HUANBAOJU = 8;
    public static final int USER_TYPE_ZHIFAJU = 9;
    public static final int USER_TYPE_FAZHANJU = 10;
    public static final int USER_TYPE_SUPER = 11;
    public static final String DEVICE_SERIAL_NUM = "D85325086";
    public static final String service_url = "http://183.208.120.226:18080/";
    //public static final String service_url = "http://192.168.60.103:8080/";
    public static final String location_url ="http://api.map.baidu.com/reverse_geocoding/v3/";

    public static List<String> list_chengguan = new ArrayList<>();
    public static List<String> list_shiwuju = new ArrayList<>();
    public static List<String> list_huanbaoju = new ArrayList<>();
    public static List<String> list_zhifaju = new ArrayList<>();
    public static List<String> list_fazhanju = new ArrayList<>();
    public static List<String> list_super = new ArrayList<>();
    public static List<File> list_file_pic = new ArrayList<>();
    public static String[] typeString = new String[]{"违法乱建","违章种植","垃圾倾倒","漂浮物","渣土车","火情预警"};
    public static final List<String> getList_chengguan(){
        list_chengguan.clear();
        list_chengguan.add(typeString[0]);
        list_chengguan.add(typeString[1]);
        list_chengguan.add(typeString[2]);
        list_chengguan.add(typeString[3]);
        return list_chengguan;
    }

    public static final List<String> getList_shiwuju(){
        list_shiwuju.clear();
        list_shiwuju.add(typeString[0]);
        list_shiwuju.add(typeString[1]);
        list_shiwuju.add(typeString[2]);
        list_shiwuju.add(typeString[3]);
        return list_shiwuju;
    }
    public static final List<String> getList_huanbaoju(){
        list_huanbaoju.clear();
        list_huanbaoju.add(typeString[4]);
        return list_huanbaoju;
    }
    public static final List<String> getList_zhifaju(){
        list_zhifaju.clear();
        list_zhifaju.add(typeString[0]);
        list_zhifaju.add(typeString[1]);
        list_zhifaju.add(typeString[4]);
        list_zhifaju.add(typeString[5]);
        return list_zhifaju;
    }
    public static final List<String> getList_fazhanju(){
        list_fazhanju.clear();
        list_fazhanju.add(typeString[5]);
        return list_fazhanju;
    }
    public static final List<String> getList_super(){
        list_super.clear();
        list_super.add(typeString[0]);
        list_super.add(typeString[1]);
        list_super.add(typeString[2]);
        list_super.add(typeString[3]);
        list_super.add(typeString[4]);
        list_super.add(typeString[5]);
        return list_super;
    }
    public static String getAlarmType(int type){
        List<String> list = new ArrayList<>();
        list = getList_super();
        String str_type = list.get(type);
        return str_type;
    }
    public static final List<File> getListFile(){
        list_file_pic.clear();
        list_file_pic.add(new File(Environment.getExternalStorageDirectory().toString()+"/EZOpenSDK/cash/78b8998fe074fcfc708f8d91d93678aa.jpg"));
        return list_file_pic;
    }
    public static int gettype(String str){
        if (str.equals(typeString[0])){
            return AlarmContant.MESSAGE_TYPE_TRUCK_IDENTITY;
        }else if (str.equals(typeString[1])){
            return AlarmContant.MESSAGE_TYPE_ILLEGAL_BUILDING;
        }else if (str.equals(typeString[2])){
            return AlarmContant.MESSAGE_TYPE_ILLEGAL_PLANT;
        }else if (str.equals(typeString[3])){
            return AlarmContant.MESSAGE_TYPE_STRAW_BURNING;
        }else if (str.equals(typeString[4])){
            return AlarmContant.MESSAGE_TYPE_RIVER_MONITOR;
        }else if (str.equals(typeString[5])){
            return AlarmContant.MESSAGE_TYPE_COMPANY_MANAGE;
        }
        return 0;
    }
}
