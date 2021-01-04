package com.example.skilltrain.bean;

import java.util.List;

public class WaterBean {

    /**
     * total : 9
     * rows : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"ownerName":"赵六","typeName":"父母","payObjectId":2,"liveName":"水费","doorNo":125,"userId":2,"classifyId":2},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"ownerName":"李小溪","typeName":"其他","payObjectId":3,"liveName":"水费","doorNo":126,"userId":2,"classifyId":2},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"ownerName":"李四","typeName":"父母","payObjectId":2,"liveName":"水费","doorNo":124,"userId":18,"classifyId":2},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"ownerName":"张三","typeName":"父母","payObjectId":2,"liveName":"水费","doorNo":123,"userId":1,"classifyId":2},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"ownerName":"张三","typeName":"父母","payObjectId":2,"liveName":"水费","doorNo":123,"userId":1,"classifyId":2},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"ownerName":"张三","typeName":"父母","payObjectId":2,"liveName":"水费","doorNo":123,"userId":20,"classifyId":2},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"ownerName":"张三","typeName":"父母","payObjectId":2,"liveName":"水费","doorNo":123,"userId":99,"classifyId":2},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"ownerName":"张三","typeName":null,"payObjectId":null,"liveName":"水费","doorNo":123,"userId":60,"classifyId":2},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"ownerName":"张三","typeName":null,"payObjectId":null,"liveName":"水费","doorNo":123,"userId":60,"classifyId":2}]
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
         * createTime : null
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * ownerName : 赵六
         * typeName : 父母
         * payObjectId : 2
         * liveName : 水费
         * doorNo : 125
         * userId : 2
         * classifyId : 2
         */

        private Object searchValue;
        private Object createBy;
        private Object createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsDTO params;
        private String ownerName;
        private String typeName;
        private int payObjectId;
        private String liveName;
        private String doorNo;
        private int userId;
        private int classifyId;

        public RowsDTO(String ownerName, String doorNo) {
            this.ownerName = ownerName;
            this.doorNo = doorNo;
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

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public ParamsDTO getParams() {
            return params;
        }

        public void setParams(ParamsDTO params) {
            this.params = params;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public int getPayObjectId() {
            return payObjectId;
        }

        public void setPayObjectId(int payObjectId) {
            this.payObjectId = payObjectId;
        }

        public String getLiveName() {
            return liveName;
        }

        public void setLiveName(String liveName) {
            this.liveName = liveName;
        }

        public String getDoorNo() {
            return doorNo;
        }

        public void setDoorNo(String doorNo) {
            this.doorNo = doorNo;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getClassifyId() {
            return classifyId;
        }

        public void setClassifyId(int classifyId) {
            this.classifyId = classifyId;
        }

        public static class ParamsDTO {
        }
    }
}
