package com.andaily.zhishifenzi.domain.shared.grouper;

/**
 * K is the group key,
 * E is the  element of group
 *
 * @author Shengzhao Li
 */
public interface Grouper<K, E> {

    GroupResults<K, E> group();

    K groupKey(E element);
}
