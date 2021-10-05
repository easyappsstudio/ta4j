package org.ta4j.core.rules;

import org.ta4j.core.Position;
import org.ta4j.core.TradingRecord;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.num.Num;

public class FixedPriceStopGainRule extends AbstractRule {

    private final ClosePriceIndicator closePriceIndicator;

    public FixedPriceStopGainRule(ClosePriceIndicator closePriceIndicator) {
        this.closePriceIndicator = closePriceIndicator;
    }

    @Override
    public boolean isSatisfied(int index, TradingRecord tradingRecord) {
        boolean satisfied = false;
        // No trading history or no position opened, no loss
        if (tradingRecord != null) {
            Position currentPosition = tradingRecord.getCurrentPosition();
            if (currentPosition.isOpened()) {

                Num takeProfitPrice = currentPosition.getCustomPositionData().getTakeProfitPrice();
                Num currentPrice = closePriceIndicator.getValue(index);

                if (currentPosition.getEntry().isBuy()) {
                    satisfied = isBuyGainSatisfied(takeProfitPrice, currentPrice);
                } else {
                    satisfied = isSellGainSatisfied(takeProfitPrice, currentPrice);
                }
            }
        }
        traceIsSatisfied(index, satisfied);
        return satisfied;
    }

    private boolean isSellGainSatisfied(Num takeProfitPrice, Num currentPrice) {
        return currentPrice.isLessThanOrEqual(takeProfitPrice);
    }

    private boolean isBuyGainSatisfied(Num takeProfitPrice,  Num currentPrice) {
        return currentPrice.isGreaterThanOrEqual(takeProfitPrice);
    }
}
