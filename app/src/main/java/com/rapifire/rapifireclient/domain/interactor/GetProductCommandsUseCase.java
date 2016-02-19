package com.rapifire.rapifireclient.domain.interactor;

import com.rapifire.rapifireclient.domain.model.ProductCommandModel;
import com.rapifire.rapifireclient.domain.model.ProductModel;
import com.rapifire.rapifireclient.domain.model.ThingDetailsModel;
import com.rapifire.rapifireclient.domain.model.ThingModel;
import com.rapifire.rapifireclient.domain.repository.ProductCommandsRepository;
import com.rapifire.rapifireclient.domain.repository.ThingDetailsRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;

public class GetProductCommandsUseCase extends UseCase<List<ProductCommandModel>> {

    private final ProductCommandsRepository repository;

    @Inject
    public GetProductCommandsUseCase(@Named("workerScheduler") Scheduler workerSheduler,
                                     @Named("postWorkScheduler") Scheduler postWorkSheduler,
                                     ProductCommandsRepository repository) {
        super(workerSheduler, postWorkSheduler);
        this.repository = repository;
    }

    public void execute(final Subscriber useCaseSubscriber, ProductModel thing) {
        this.subscription = this.buildUseCaseObservable(thing)
                .subscribeOn(workerSheduler)
                .observeOn(postWorkSheduler)
                .subscribe(useCaseSubscriber);
    }

    protected Observable<List<ProductCommandModel>> buildUseCaseObservable(ProductModel product) {
        return repository.getProductCommands(product, false);
    }
}
