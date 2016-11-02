/*
 * Copyright (c) 2013 Honyee Industry Group Co., Ltd
 * www.honyee.biz
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Honyee Industry Group Co., Ltd ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with Honyee Industry Group Co., Ltd.
 */
package com.andaily.zhishifenzi.domain.shared.paginated;

import com.andaily.zhishifenzi.domain.shared.security.SecurityUtils;
import org.displaytag.pagination.PaginatedList;
import org.displaytag.properties.SortOrderEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shengzhao Li
 */
public class DefaultPaginated<T> implements Paginated<T>, PaginatedList {

    public static final int DEFAULT_PER_PAGE_SIZE = 20;

    protected List<T> list = new ArrayList<T>();
    protected int perPageSize;
    protected int totalSize = 0;
    protected int pageNumber = 1;

    protected String sortName;
    protected PaginatedSort sort;

    protected String sortCriterion;
    private SortOrderEnum sortOrderEnum;
    private String searchId;


    public DefaultPaginated() {
        this(DEFAULT_PER_PAGE_SIZE);
    }

    public DefaultPaginated(int perPageSize) {
        this.perPageSize = perPageSize;
    }

    @Override
    public List<T> getList() {
        return list;
    }

    public Map<String, Object> queryMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("user", SecurityUtils.currentUser());
        map.put("perPageSize", getPerPageSize());
        map.put("startIndex", getStartIndex());
        return map;
    }

    public int getTotalPages() {
        if (totalSize % perPageSize == 0) {
            return (totalSize / perPageSize);
        } else {
            return (totalSize / perPageSize) + 1;
        }
    }

    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public int getObjectsPerPage() {
        return perPageSize;
    }

    @Override
    public int getFullListSize() {
        return totalSize;
    }

    @Override
    public String getSortCriterion() {
        return sortCriterion;
    }

    public void setSortCriterion(String sortCriterion) {
        this.sortCriterion = sortCriterion;
    }

    @Override
    public SortOrderEnum getSortDirection() {
        return sortOrderEnum;
    }

    @Override
    public String getSearchId() {
        return searchId;
    }

    @Override
    public int getPerPageSize() {
        return perPageSize;
    }

    @Override
    public int getTotalSize() {
        return totalSize;
    }

    @Override
    public String getSortName() {
        return sortName;
    }

    @Override
    public PaginatedSort getSort() {
        return sort;
    }

    public int getStartIndex() {
        return (getPageNumber() - 1) * getPerPageSize();
    }

    @SuppressWarnings("unchecked")
    public <K extends Paginated> K load(PaginatedLoader<T> paginatedLoader) {
        if (this.totalSize == 0) {
            this.totalSize = paginatedLoader.loadTotalSize();
        }
        this.list = paginatedLoader.loadList();
        afterLoad();
        return (K) this;
    }

    public void afterLoad() {
        // Callback after load data.
    }

    public void setPerPageSize(int perPageSize) {
        this.perPageSize = perPageSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public void setSort(PaginatedSort sort) {
        this.sort = sort;
    }

    public boolean isHasNext() {
        return (getStartIndex() + this.perPageSize < totalSize);
    }

    public boolean isHasPrevious() {
        return getStartIndex() != 0;
    }

    public void setSortOrderEnum(SortOrderEnum sortOrderEnum) {
        this.sortOrderEnum = sortOrderEnum;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }
}