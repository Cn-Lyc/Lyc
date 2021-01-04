package com.example.skilltrain.bean;

import java.util.List;

public class PowerBean {

    /**
     * total : 24
     * rows : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"electricityId":1,"doorNo":123,"title":"第一季度电费","electricityMoney":"0.0","chargeUnit":"大连电力公司","ownerName":"张三","balance":"0.0","liveName":"电费","userId":1,"doorId":1},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"electricityId":2,"doorNo":123,"title":"第二季度水费","electricityMoney":"0.0","chargeUnit":"水电公司","ownerName":"张三","balance":"0.0","liveName":"水费","userId":1,"doorId":1},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"electricityId":3,"doorNo":124,"title":"第三季度电费","electricityMoney":"0.0","chargeUnit":"大连电力公司","ownerName":"李四","balance":"0.0","liveName":"电费","userId":2,"doorId":2},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"electricityId":4,"doorNo":124,"title":"第四季度水费","electricityMoney":"0.0","chargeUnit":"水电公司","ownerName":"李四","balance":"0.0","liveName":"水费","userId":2,"doorId":2},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"electricityId":5,"doorNo":125,"title":"第五季度水费","electricityMoney":"200","chargeUnit":"水电公司","ownerName":"赵六","balance":"5300.0","liveName":"水费","userId":3,"doorId":3},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"electricityId":6,"doorNo":125,"title":"第六季度电费","electricityMoney":"5100.0","chargeUnit":"大连电力公司","ownerName":"赵六","balance":"5300.0","liveName":"电费","userId":3,"doorId":3},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"electricityId":7,"doorNo":126,"title":"第七季度电费","electricityMoney":"1000.0","chargeUnit":"大连电力公司","ownerName":"李小溪","balance":"8900.0","liveName":"电费","userId":4,"doorId":4},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"electricityId":8,"doorNo":126,"title":"第八季度水费","electricityMoney":"100","chargeUnit":"水电公司","ownerName":"李小溪","balance":"8900.0","liveName":"水费","userId":4,"doorId":4},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"electricityId":9,"doorNo":null,"title":"第九季度电费","electricityMoney":"120","chargeUnit":"大连电力公司","ownerName":null,"balance":null,"liveName":"电费","userId":null,"doorId":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"electricityId":10,"doorNo":null,"title":"第十季度水费","electricityMoney":"200","chargeUnit":"水电公司","ownerName":null,"balance":null,"liveName":"水费","userId":null,"doorId":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"electricityId":11,"doorNo":null,"title":"第十一季度水费","electricityMoney":"100","chargeUnit":"水电公司2","ownerName":null,"balance":null,"liveName":"水费","userId":null,"doorId":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"electricityId":12,"doorNo":null,"title":"第十二季度电费","electricityMoney":"200","chargeUnit":"大连电力公司","ownerName":null,"balance":null,"liveName":"电费","userId":null,"doorId":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"electricityId":13,"doorNo":null,"title":"第十三季度电费","electricityMoney":"100","chargeUnit":"大连电力公司","ownerName":null,"balance":null,"liveName":"电费","userId":null,"doorId":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"electricityId":14,"doorNo":null,"title":"第十四季度水费","electricityMoney":"100","chargeUnit":"水电公司","ownerName":null,"balance":null,"liveName":"水费","userId":null,"doorId":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"electricityId":15,"doorNo":null,"title":"第十五季度电费","electricityMoney":"120","chargeUnit":"大连电力公司","ownerName":null,"balance":null,"liveName":"电费","userId":null,"doorId":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"electricityId":16,"doorNo":null,"title":"第十六季度水费","electricityMoney":"200","chargeUnit":"水电公司","ownerName":null,"balance":null,"liveName":"水费","userId":null,"doorId":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"electricityId":17,"doorNo":null,"title":"第十七季度水费","electricityMoney":"200","chargeUnit":"水电公司2","ownerName":null,"balance":null,"liveName":"水费","userId":null,"doorId":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"electricityId":18,"doorNo":null,"title":"第十八季度电费","electricityMoney":"200","chargeUnit":"大连电力公司","ownerName":null,"balance":null,"liveName":"电费","userId":null,"doorId":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"electricityId":19,"doorNo":null,"title":"第十九季度电费","electricityMoney":"100","chargeUnit":"大连电力公司","ownerName":null,"balance":null,"liveName":"电费","userId":null,"doorId":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"electricityId":20,"doorNo":null,"title":"第二十季度水费","electricityMoney":"100","chargeUnit":"水电公司","ownerName":null,"balance":null,"liveName":"水费","userId":null,"doorId":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"electricityId":21,"doorNo":null,"title":"第二十一季度电费","electricityMoney":"120","chargeUnit":"大连电力公司","ownerName":null,"balance":null,"liveName":"电费","userId":null,"doorId":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"electricityId":22,"doorNo":null,"title":"第二十二季度水费","electricityMoney":"200","chargeUnit":"水电公司","ownerName":null,"balance":null,"liveName":"水费","userId":null,"doorId":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"electricityId":23,"doorNo":null,"title":"第二十三季度水费","electricityMoney":"200","chargeUnit":"水电公司2","ownerName":null,"balance":null,"liveName":"水费","userId":null,"doorId":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"electricityId":24,"doorNo":null,"title":"第二十四季度电费","electricityMoney":"200","chargeUnit":"大连电力公司","ownerName":null,"balance":null,"liveName":"电费","userId":null,"doorId":null}]
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
         * electricityId : 1
         * doorNo : 123
         * title : 第一季度电费
         * electricityMoney : 0.0
         * chargeUnit : 大连电力公司
         * ownerName : 张三
         * balance : 0.0
         * liveName : 电费
         * userId : 1
         * doorId : 1
         */

        private Object searchValue;
        private Object createBy;
        private Object createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsDTO params;
        private int electricityId;
        private String doorNo;
        private String title;
        private String electricityMoney;
        private String chargeUnit;
        private String ownerName;
        private String balance;
        private String liveName;
        private int userId;
        private int doorId;

        public RowsDTO(String chargeUnit, String doorNo, String ownerName, String balance, String electricityMoney) {
            this.chargeUnit = chargeUnit;
            this.doorNo = doorNo;
            this.ownerName = ownerName;
            this.balance = balance;
            this.electricityMoney = electricityMoney;
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

        public int getElectricityId() {
            return electricityId;
        }

        public void setElectricityId(int electricityId) {
            this.electricityId = electricityId;
        }

        public String getDoorNo() {
            return doorNo;
        }

        public void setDoorNo(String doorNo) {
            this.doorNo = doorNo;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getElectricityMoney() {
            return electricityMoney;
        }

        public void setElectricityMoney(String electricityMoney) {
            this.electricityMoney = electricityMoney;
        }

        public String getChargeUnit() {
            return chargeUnit;
        }

        public void setChargeUnit(String chargeUnit) {
            this.chargeUnit = chargeUnit;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getLiveName() {
            return liveName;
        }

        public void setLiveName(String liveName) {
            this.liveName = liveName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getDoorId() {
            return doorId;
        }

        public void setDoorId(int doorId) {
            this.doorId = doorId;
        }

        public static class ParamsDTO {
        }
    }
}
