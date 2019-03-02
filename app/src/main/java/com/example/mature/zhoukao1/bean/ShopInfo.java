package com.example.mature.zhoukao1.bean;

import java.util.List;

public class ShopInfo {
    public String message;
    public String status;
    public ResultBean result;
    public static class ResultBean{
        public List<listBean> rxxp;
        public List<listBean> pzsh;
        public List<listBean> mlss;
        public static class listBean{
            public int id;
            public String name;
            public List<CommodityListBean> commodityList;
            public static class CommodityListBean{
                public int commodityId;
                public String commodityName;
                public String masterPic;
                public String price;
                public int saleNum;
            }
        }
    }
}
