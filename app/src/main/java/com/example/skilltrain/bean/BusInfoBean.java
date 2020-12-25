package com.example.skilltrain.bean;

import java.util.List;

public class BusInfoBean {

    /**
     * total : 4
     * rows : [{"searchValue":null,"createBy":null,"createTime":"2020-10-05 11:23:32","updateBy":null,"updateTime":"2020-10-21 11:23:35","remark":null,"endTime":"19：45","params":{},"id":1,"name":"一号线","first":"光谷金融街","end":"南湖大厦","startTime":"6:30","price":8,"mileage":"20"},{"searchValue":null,"createBy":null,"createTime":"2020-10-13 12:28:57","updateBy":null,"updateTime":"2020-10-22 12:29:00","remark":null,"endTime":"21：45","params":{},"id":2,"name":"二号线","first":"光谷金融街","end":"万达广场","startTime":"6:30","price":8,"mileage":"22"},{"searchValue":null,"createBy":null,"createTime":"2020-10-27 16:57:07","updateBy":null,"updateTime":"2020-10-27 16:57:21","remark":null,"endTime":"22：00","params":{},"id":3,"name":"三号线","first":"香炉礁","end":"金石沙滩","startTime":"6：30","price":9,"mileage":"30"},{"searchValue":null,"createBy":null,"createTime":"2020-10-27 16:59:03","updateBy":null,"updateTime":"2020-10-27 16:59:06","remark":null,"endTime":"23：00","params":{},"id":4,"name":"十二号线","first":"河口","end":"辛寨子","startTime":"5：30","price":12,"mileage":"40"}]
     * code : 200
     * msg : 查询成功
     */

    private int total;
    private int code;
    private String msg;
    private List<RowsDTO> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<RowsDTO> getRows() {
        return rows;
    }

    public void setRows(List<RowsDTO> rows) {
        this.rows = rows;
    }

    public static class RowsDTO {
        /**
         * searchValue : null
         * createBy : null
         * createTime : 2020-10-05 11:23:32
         * updateBy : null
         * updateTime : 2020-10-21 11:23:35
         * remark : null
         * endTime : 19：45
         * params : {}
         * id : 1
         * name : 一号线
         * first : 光谷金融街
         * end : 南湖大厦
         * startTime : 6:30
         * price : 8.0
         * mileage : 20
         */

        private Object searchValue;
        private Object createBy;
        private String createTime;
        private Object updateBy;
        private String updateTime;
        private Object remark;
        private String endTime;
        private ParamsDTO params;
        private String  id;
        private String name;
        private String first;
        private String end;
        private String startTime;
        private String price;
        private String mileage;

        public RowsDTO(String name, String first, String end, String startTime, String price, String mileage) {
            this.name = name;
            this.first = first;
            this.end = end;
            this.startTime = startTime;
            this.price = price;
            this.mileage = mileage;
        }


        public Object getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(Object searchValue) {
            this.searchValue = searchValue;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public ParamsDTO getParams() {
            return params;
        }

        public void setParams(ParamsDTO params) {
            this.params = params;
        }

        public String  getId() {
            return id;
        }

        public void setId(String  id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getMileage() {
            return mileage;
        }

        public void setMileage(String mileage) {
            this.mileage = mileage;
        }

        public static class ParamsDTO {
        }
    }
}
