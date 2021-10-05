package org.ta4j.core.rules;

import org.ta4j.core.Position;
import org.ta4j.core.TradingRecord;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.num.Num;

public class FixedPriceStopLossRule extends AbstractRule {

    /**
     * The close price indicator
     */
    private final ClosePriceIndicator closePriceIndicator;

    public FixedPriceStopLossRule(ClosePriceIndicator closePriceIndicator) {
        this.closePriceIndicator = closePriceIndicator;
    }

    @Override
    public boolean isSatisfied(int index, TradingRecord tradingRecord) {

        boolean satisfied = false;
        // No trading history or no position opened, no loss
        if (tradingRecord != null) {
            Position currentPosition = tradingRecord.getCurrentPosition();
            if (currentPosition.isOpened()) {

                Num stopLossValue = currentPosition.getCustomPositionData().getStopLossPrice();
                Num currentPrice = closePriceIndicator.getValue(index);

                if (currentPosition.getEntry().isBuy()) {
                    satisfied = isBuyStopSatisfied(stopLossValue, currentPrice);
                } else {
                    satisfied = isSellStopSatisfied(stopLossValue, currentPrice);
                }
            }
        }
        traceIsSatisfied(index, satisfied);
        return satisfied;
    }

    private boolean isSellStopSatisfied(Num stopLossValue, Num currentPrice) {
        return currentPrice.isGreaterThanOrEqual(stopLossValue);
    }

    private boolean isBuyStopSatisfied(Num stopLossValue, Num currentPrice) {
        return currentPrice.isLessThanOrEqual(stopLossValue);
    }
}
