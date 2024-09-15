package org.spotifyautomation.utils;

import org.spotifyautomation.pojo.ErrorRoot;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AssertionHelper {
    public static void assertErrorMessage(ErrorRoot error, int errorCode, String errorMessage) {
        assertThat(error.getError().getStatus(), equalTo(errorCode));
        assertThat(error.getError().getMessage(), equalTo(errorMessage));
    }
}
