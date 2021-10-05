package org.ta4j.core.indicators;

import org.ta4j.core.Indicator;
import org.ta4j.core.num.Num;

public class MACDSignalIndicator extends CachedIndicator<Num> {

    private final EMAIndicator signal;

    public MACDSignalIndicator(Indicator<Num> indicator) {
        this(indicator, 9);
    }

    public MACDSignalIndicator(Indicator<Num> indicator, int signalLength) {
        super(indicator);

        signal = new EMAIndicator(indicator, signalLength);
    }

    @Override
    protected Num calculate(int index) {
        return signal.getValue(index);
    }
}
