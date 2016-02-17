package com.rapifire.rapifireclient.domain.repository;

import com.rapifire.rapifireclient.domain.model.ProductCommandModel;
import com.rapifire.rapifireclient.domain.model.ProductModel;
import com.rapifire.rapifireclient.domain.model.ThingDetailsModel;
import com.rapifire.rapifireclient.domain.model.ThingModel;

import java.util.List;

import rx.Observable;

public interface ProductCommandsRepository {
    Observable<List<ProductCommandModel>> getProductCommands(ProductModel product, boolean forceSync);
}
