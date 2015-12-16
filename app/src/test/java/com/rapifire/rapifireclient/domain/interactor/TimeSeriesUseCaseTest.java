package com.rapifire.rapifireclient.domain.interactor;

import com.rapifire.rapifireclient.domain.model.ChartItemModel;
import com.rapifire.rapifireclient.domain.model.ChartModel;
import com.rapifire.rapifireclient.domain.model.TimeSeriesModel;
import com.rapifire.rapifireclient.domain.repository.TimeSeriesRepository;
import com.rapifire.rapifireclient.helper.SynchronousExecutorService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by ktomek on 09.12.15.
 */
public class TimeSeriesUseCaseTest {

    public static final long TIME_MILLIS = 1449137850919l;

    @Mock
    TimeSeriesRepository timeSeriesRepository;

    TimeSeriesUseCase timeSeriesUseCase;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        timeSeriesUseCase = new TimeSeriesUseCase(Schedulers.from(new SynchronousExecutorService()), Schedulers.from(new SynchronousExecutorService()), timeSeriesRepository, "");
    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void shouldEmitDoubleTypeChartItems() {
        when(timeSeriesRepository.getTimeSeries(anyString(), anyLong())).thenReturn(Observable.just(Arrays.asList(new TimeSeriesModel(TIME_MILLIS, Double.valueOf(10), null))));
        TestSubscriber<ChartModel> testChartSubscriber = new TestSubscriber<>();

        timeSeriesUseCase.execute(testChartSubscriber);

        testChartSubscriber.assertCompleted();
        testChartSubscriber.assertNoErrors();
        testChartSubscriber.assertValueCount(1);
        ChartModel chartModel = testChartSubscriber.getOnNextEvents().get(0);
        assertEquals(TimeSeriesType.DOUBLE, chartModel.getType());
    }

    @Test
    public void shouldEmitStringTypeChartItems() {
        when(timeSeriesRepository.getTimeSeries(anyString(), anyLong())).thenReturn(Observable.just(Arrays.asList(new TimeSeriesModel(TIME_MILLIS, null, "50"))));
        TestSubscriber<ChartModel> testChartSubscriber = new TestSubscriber<>();

        timeSeriesUseCase.execute(testChartSubscriber);

        testChartSubscriber.assertCompleted();
        testChartSubscriber.assertNoErrors();
        testChartSubscriber.assertValueCount(1);
        ChartModel chartModel = testChartSubscriber.getOnNextEvents().get(0);
        assertEquals(TimeSeriesType.STRING, chartModel.getType());
    }

    @Test
    public void shouldEmitNoneTypeChartItems() {
        when(timeSeriesRepository.getTimeSeries(anyString(), anyLong())).thenReturn(Observable.just(Arrays.asList(new TimeSeriesModel(TIME_MILLIS, null, null))));
        TestSubscriber<ChartModel> testChartSubscriber = new TestSubscriber<>();

        timeSeriesUseCase.execute(testChartSubscriber);

        testChartSubscriber.assertCompleted();
        testChartSubscriber.assertNoErrors();
        testChartSubscriber.assertValueCount(0);
    }


    @Test
    public void shouldEmitDoubleChartItems() {
        when(timeSeriesRepository.getTimeSeries(anyString(), anyLong())).thenReturn(Observable.just(Arrays.asList(new TimeSeriesModel(TIME_MILLIS, Double.valueOf(50), null))));
        TestSubscriber<ChartModel> testChartSubscriber = new TestSubscriber<>();

        timeSeriesUseCase.execute(testChartSubscriber);

        testChartSubscriber.assertCompleted();
        testChartSubscriber.assertNoErrors();
        testChartSubscriber.assertValueCount(1);
        ChartModel chartModel = testChartSubscriber.getOnNextEvents().get(0);
        assertEquals(new ChartItemModel("11:17", Double.valueOf(50)), chartModel.getItems().get(0));
    }

    @Test
    public void shouldEmitStringChartItems() {
        when(timeSeriesRepository.getTimeSeries(anyString(), anyLong())).thenReturn(Observable.just(Arrays.asList(new TimeSeriesModel(TIME_MILLIS, null, "label"))));
        TestSubscriber<ChartModel> testChartSubscriber = new TestSubscriber<>();

        timeSeriesUseCase.execute(testChartSubscriber);

        testChartSubscriber.assertCompleted();
        testChartSubscriber.assertNoErrors();
        testChartSubscriber.assertValueCount(1);
        ChartModel chartModel = testChartSubscriber.getOnNextEvents().get(0);
        assertEquals(new ChartItemModel("label", Double.valueOf(1)), chartModel.getItems().get(0));
    }

    @Test
    public void shouldEmitStringChartItemsGroupedAndCounted() {
        when(timeSeriesRepository.getTimeSeries(anyString(), anyLong())).thenReturn(Observable.just(Arrays.asList(new TimeSeriesModel(TIME_MILLIS, null, "label"), new TimeSeriesModel(TIME_MILLIS, null, "label"), new TimeSeriesModel(TIME_MILLIS, null, "label2"))));
        TestSubscriber<ChartModel> testChartSubscriber = new TestSubscriber<>();

        timeSeriesUseCase.execute(testChartSubscriber);

        testChartSubscriber.assertCompleted();
        testChartSubscriber.assertNoErrors();
        testChartSubscriber.assertValueCount(1);
        ChartModel chartModel = testChartSubscriber.getOnNextEvents().get(0);
        assertEquals(new ChartItemModel("label", Double.valueOf(2)), chartModel.getItems().get(0));
        assertEquals(new ChartItemModel("label2", Double.valueOf(1)), chartModel.getItems().get(1));
    }
}