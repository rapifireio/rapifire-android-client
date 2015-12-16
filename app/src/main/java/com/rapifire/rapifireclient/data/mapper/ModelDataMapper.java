package com.rapifire.rapifireclient.data.mapper;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by ktomek on 05.12.15.
 */
public interface ModelDataMapper<T, K> extends Func1<List<K>, Observable<List<T>>> {
    T transform(K k);

    List<T> transform(List<K> collection);
}
