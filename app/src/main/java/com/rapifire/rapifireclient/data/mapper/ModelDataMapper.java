package com.rapifire.rapifireclient.data.mapper;

import java.text.ParseException;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by ktomek on 05.12.15.
 */
public interface ModelDataMapper<T, K> extends Func1<List<K>, Observable<List<T>>> {
    T transform(K k) throws ParseException;

    List<T> transform(List<K> collection) throws ParseException;
}
