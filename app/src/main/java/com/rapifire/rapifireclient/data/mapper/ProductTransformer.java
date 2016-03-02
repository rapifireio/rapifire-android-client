package com.rapifire.rapifireclient.data.mapper;

import com.rapifire.rapifireclient.data.Product;
import com.rapifire.rapifireclient.domain.model.ProductModel;

/**
 * Created by witek on 02.03.16.
 */
public class ProductTransformer {
    public ProductModel transformProduct(Product product) {
        ProductModel result = new ProductModel();
        result.setId(product.getId());
        result.setName(product.getName());
        result.setHartbeat(product.getHartbeat());

        return result;
    }
}
