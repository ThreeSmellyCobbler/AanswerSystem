package com.tsco.web.utils;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Pattern;


public class PatternRegexTest {

    @Test
    public void testEmail() {
        Assert.assertThat(Pattern.compile(PatternRegex.email).matcher("12aa@qq.com").matches(), Is.is(true));
    }
}
