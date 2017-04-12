package com.ring.test;

import android.content.Context;

import org.hamcrest.core.AnyOf;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ring
 *
 * http://junit.org/junit4/javadoc/latest/org/junit/Assert.html
 * https://code.google.com/archive/p/hamcrest/wikis/Tutorial.wiki
 * http://site.mockito.org/mockito/docs/current/org/mockito/Mockito.html
 *
 * on 16/7/13.
 */
@RunWith(MockitoJUnitRunner.class)
public class JUnitTest {

    private static final String FAKE_STRING = "HELLO WORLD";

    @Mock
    Context mMockContext;

    @Test
    public void basicTest() {
       assertThat("12121",false, is(false));
       assertThat("asd", AnyOf.anyOf(is("asd"),is("212")));
    }

    @Test
    public void testMock(){
        when(mMockContext.getString(R.string.app_name)).thenReturn(FAKE_STRING);
        assertThat(mMockContext.getString(R.string.app_name),is(FAKE_STRING));
    }
    @Test
    public void testMock2(){
        //mock creation
        List mockedList = mock(List.class);

        //using mock object
        mockedList.add("one");
        mockedList.clear();

        //verification
        verify(mockedList).add("on1e");
        verify(mockedList).clear();
    }

}
