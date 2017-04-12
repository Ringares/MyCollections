package com.ring.test;


import android.content.Intent;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class RobolectricExample {

    @Before
    public void setUp() throws Exception {
        // setup
    }

    @Test
    public void clickingLogin_shouldStartLoginActivity() {
        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
        mainActivity.findViewById(R.id.login).performClick();

        Intent expectedIntent = new Intent(mainActivity, LoginActivity.class);
        ShadowActivity shadowActivity = Shadows.shadowOf(mainActivity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertEquals(expectedIntent, actualIntent);
        assertThat(expectedIntent, Is.is(actualIntent));
    }
}