package com.nhom18.server.controller.subject_statistic.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public class SubjectTeacherRequest {
    @Pattern(
            regexp = ("^id|tchCode|fullname$"),
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
    private int searchType;
    private String searchData;
    private long termSubjectId;

    public SubjectTeacherRequest() {
    }

    public String getProperties() {
        return properties;
    }

    public String getOrder() {
        return order;
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getRecordPerPage() {
        return recordPerPage;
    }

    public int getSearchType() {
        return searchType;
    }

    public String getSearchData() {
        return searchData;
    }

    public long getTermSubjectId() {
        return termSubjectId;
    }
}
