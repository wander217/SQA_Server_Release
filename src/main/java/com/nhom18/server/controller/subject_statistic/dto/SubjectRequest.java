package com.nhom18.server.controller.subject_statistic.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public class SubjectRequest {
    @Pattern(
            regexp = ("^termSubject\\.id|termSubject\\.subject\\.name|remember|forgot$"),
            message = ("Thuộc tính tìm kiếm không hợp lệ!")
    )
    private String properties;
    private String order;
    @Min(
            value = 0,
            message = ("Số trang phải là số nguyên không âm")
    )
    private int pageNum;
    @Min(
            value = 1,
            message = "Số bản ghi trong trang phải là 1 số dương"
    )
    private int recordPerPage;
    private String searchData;

    public SubjectRequest() {
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getRecordPerPage() {
        return recordPerPage;
    }

    public void setRecordPerPage(int recordPerPage) {
        this.recordPerPage = recordPerPage;
    }

    public String getSearchData() {
        return searchData;
    }

    public void setSearchData(String searchData) {
        this.searchData = searchData;
    }
}
