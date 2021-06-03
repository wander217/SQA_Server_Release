package com.nhom18.server.controller.registration.dto;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public class SubjectGroupRequest {
    @Pattern(
        regexp = ("^code|learningDay|shift$"),
        message = ("Thuộc tính sắp xếp không hợp lệ!")
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
    private int searchType;
    private String searchData;
    @Min(
        value = 1,
        message = "Mã môn học phải là số dương"
    )
    private long termSubjectId;

    public SubjectGroupRequest() {
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

    public int getSearchType() {
        return searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }

    public long getTermSubjectId() {
        return termSubjectId;
    }

    public void setTermSubjectId(long termSubjectId) {
        this.termSubjectId = termSubjectId;
    }

    public String getSearchData() {
        return searchData;
    }

    public void setSearchData(String searchData) {
        this.searchData = searchData;
    }
}
