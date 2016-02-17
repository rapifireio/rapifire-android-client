package com.rapifire.rapifireclient.data.repository;


import com.rapifire.rapifireclient.data.ProductCommand;
import com.rapifire.rapifireclient.data.Thing;
import com.rapifire.rapifireclient.data.cache.MemoryCache;
import com.rapifire.rapifireclient.data.mapper.ModelDataMapper;
import com.rapifire.rapifireclient.data.mapper.SimpleModelDataMapper;
import com.rapifire.rapifireclient.data.network.ProductsService;
import com.rapifire.rapifireclient.data.network.ThingsService;
import com.rapifire.rapifireclient.di.UserScope;
import com.rapifire.rapifireclient.domain.model.ProductCommandModel;
import com.rapifire.rapifireclient.domain.model.ProductModel;
import com.rapifire.rapifireclient.domain.model.ThingDetailsModel;
import com.rapifire.rapifireclient.domain.model.ThingModel;
import com.rapifire.rapifireclient.domain.repository.ProductCommandsRepository;
import com.rapifire.rapifireclient.domain.repository.ThingsRepository;
import com.rapifire.rapifireclient.data.mapper.ProductCommandsModelDataMapper;


import java.util.List;

import javax.inject.Inject;

import rx.Observable;

@UserScope
public class ProductCommandsDataRepository implements ProductCommandsRepository {

    private final MemoryCache memoryCache;
    private final ProductsService productsService;
    private final ModelDataMapper<ProductCommandModel, ProductCommand> modelDataMapper;

    @Inject
    public ProductCommandsDataRepository(MemoryCache memoryCache,
                                         ProductsService productsService,
                                         ModelDataMapper<ProductCommandModel, ProductCommand> modelDataMapper) {
        this.memoryCache = memoryCache;
        this.productsService = productsService;
        this.modelDataMapper = modelDataMapper;
    }

    public Observable<List<ProductCommandModel>> getProductCommands(ProductModel product, boolean forceSync) {
        Observable<List<ProductCommandModel>> networkObservable = productsService
                .getProductCommands(product.getId())
                .flatMap(modelDataMapper);

        return networkObservable;
    }
}
