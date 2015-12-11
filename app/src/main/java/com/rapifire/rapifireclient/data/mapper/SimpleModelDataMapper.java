package com.rapifire.rapifireclient.data.mapper;

import java.text.ParseException;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

public interface SimpleModelDataMapper<T, K> extends Func1<K, Observable<T>> {
    T transform(K k) throws ParseException;
}
