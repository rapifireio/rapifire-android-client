package com.rapifire.rapifireclient.data.mapper;

import com.rapifire.rapifireclient.data.ProductCommand;
import com.rapifire.rapifireclient.data.Thing;
import com.rapifire.rapifireclient.domain.model.ProductCommandModel;
import com.rapifire.rapifireclient.domain.model.ProductCommandType;
import com.rapifire.rapifireclient.domain.model.ProductModel;
import com.rapifire.rapifireclient.domain.model.ThingModel;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class ProductCommandsModelDataMapper implements ModelDataMapper<ProductCommandModel, ProductCommand> {

    @Override
    public ProductCommandModel transform(ProductCommand command) {
        return transformProductCommand(command);
    }

    @Override
    public List<ProductCommandModel> transform(List<ProductCommand> productCommands) {
        final int size = productCommands.size();
        final List<ProductCommandModel> commandsModelList = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            final ProductCommand tweet = productCommands.get(i);
            commandsModelList.add(transform(tweet));
        }
        return commandsModelList;
    }

    @Override
    public Observable<List<ProductCommandModel>> call(List<ProductCommand> productCommands) {
        return Observable.just(transform(productCommands));
    }

    private ProductCommandModel transformProductCommand(ProductCommand productCommand) {
        ProductCommandModel result = new ProductCommandModel();

        result.setName(productCommand.getName());
        result.setPayload(productCommand.getPayload());
        result.setType("text".equals(productCommand.getType()) ? ProductCommandType.TEXT : ProductCommandType.BINARY);
        result.setVisibility(productCommand.getVisibility());

        return result;
    }
}
