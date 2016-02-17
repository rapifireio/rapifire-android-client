package com.rapifire.rapifireclient.data.network;

import com.rapifire.rapifireclient.data.ProductCommand;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by witek on 17.02.16.
 */
public interface ProductsService {
    @GET("/api/v1/products/{productId}/commands/")
    Observable<List<ProductCommand>> getProductCommands(@Path("productId") String productId);
}

