package com.ring.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;
import rx.observers.TestSubscriber;

/**
 * Created by ring
 * on 16/3/22.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class RxUnitTest {

    @Before
    public void setUp() throws Exception {
        // setup
    }

    @Test
    public void testOperatorDebounce() throws Exception {
        // test
        TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        Observable.just(1, 2, 3, 4, 5)
                .debounce(100, TimeUnit.SECONDS)
                .subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        //testSubscriber.assertReceivedOnNext(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> nums = testSubscriber.getOnNextEvents();
        Assert.assertEquals(nums.size(), 1);
        Assert.assertEquals(((int) nums.get(0)), 5);
    }

    @Test
    public void testOperatorConCat() throws Exception {
        TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        Observable<Integer> observable1 = Observable.just(1, 2);
        Observable<Integer> observable2 = Observable.just(2, 3);
        Observable<Integer> observable3 = Observable.just(3, 4);

        Observable.concat(observable1, observable2, observable3)
                .first(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer > 2;
                    }
                })
                .subscribe(testSubscriber);

        List<Integer> onNextEvents = testSubscriber.getOnNextEvents();
        Assert.assertEquals(onNextEvents.size(), 1);
        Assert.assertEquals(((int) onNextEvents.get(0)), 3);
    }

    @Test
    public void testSwitchMap() throws Exception {
        TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();

    }
    @Test
    public void test() throws Exception {

    }
}
