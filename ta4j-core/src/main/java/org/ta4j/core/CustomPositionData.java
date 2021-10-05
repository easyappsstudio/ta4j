package org.ta4j.core;

import org.ta4j.core.num.Num;

import java.io.Serializable;

public interface CustomPositionData {

    /**
     * Position's side, can be either BUY or SELL
     * @return position side.
     */
    Trade.TradeType positionSide();

    /**
     * Returns the current stop loss price of the position.
     * @return the current stop loss price, null if no stop loss has been set.
     */
    Num getStopLossPrice();

    /**
     * Returns the original stop loss price of the position.
     * This value will never change even when the stop loss is trailed up/down.
     * @return the original stop loss price of the position, null if no stop loss has been set.
     */
    Num getInitialStopLossPrice();

    /**
     * Position's entry price.
     * @return the position's entry price.
     */
    Num getEntryPrice();

    /**
     * Position's take profit price.
     * @return the take profit price if has been set, null otherwise.
     */
    Num getTakeProfitPrice();

    /**
     * Position entry size
     * @return the entry volume size.
     */
    Num positionEntrySize();
}
