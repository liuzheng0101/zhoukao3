package com.example.zhoukao3.bean;

import java.util.List;

public class UserInfo {
    public String message;
    public String status;
    public List<Result> result;
    public class Result{
        public String commodityId;
        public String commodityName;
        public String masterPic;
        public double price;
    }
}
