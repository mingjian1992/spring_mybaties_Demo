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

import java.util.List;

/**
 * @author Shengzhao Li
 */

public interface Paginated<T> {

    List<T> getList();

    int getPageNumber();

    int getPerPageSize();

    int getTotalSize();

    String getSortName();

    PaginatedSort getSort();

    int getTotalPages();
}