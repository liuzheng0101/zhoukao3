package com.example.zhoukao3.bean;

import java.util.List;

public class CartInfo {
    public String status;
    public String message;
    public List<Result> result;
    public class Result{
        public String commodityId;
        public String commodityName;
        public String count;
        public String pic;
        public double price;
        public int productNum;
        public boolean isChecked;
    }
}
