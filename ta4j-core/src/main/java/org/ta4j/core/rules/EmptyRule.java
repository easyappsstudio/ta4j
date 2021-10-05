package org.ta4j.core.rules;

import org.ta4j.core.TradingRecord;

public class EmptyRule extends AbstractRule{

    @Override
    public boolean isSatisfied(int index, TradingRecord tradingRecord) {
        return false;
    }
}
