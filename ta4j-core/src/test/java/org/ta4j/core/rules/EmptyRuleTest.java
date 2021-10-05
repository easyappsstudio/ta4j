package org.ta4j.core.rules;

import org.junit.Before;
import org.junit.Test;
import org.ta4j.core.Rule;

import static org.junit.Assert.assertFalse;

public class EmptyRuleTest {

    private Rule rule;

    @Before
    public void setup(){
        rule = new EmptyRule();
    }

    @Test
    public void isSatisfied(){
        assertFalse(rule.isSatisfied(0));

    }
}
