package org.ta4j.core;

import org.ta4j.core.num.DecimalNum;
import org.ta4j.core.num.Num;

import java.io.Serializable;

public class BaseCustomPositionData implements CustomPositionData, Serializable {

    private static final long serialVersionUID = -2355139882210599050L;

    private final Num entryPrice;
    private final Num stopLossPrice;
    private final Num initialStopLossPrice;
    private final Num takeProfitPrice;
    private final Trade.TradeType positionSide;

    public BaseCustomPositionData(Trade.TradeType positionSide,
                                  Num entryPrice,
                                  Num stopLossPrice,
                                  Num initialStopLossPrice,
                                  Num takeProfitPrice) {
        this.entryPrice = entryPrice;
        this.stopLossPrice = stopLossPrice;
        this.initialStopLossPrice = initialStopLossPrice;
        this.takeProfitPrice = takeProfitPrice;
        this.positionSide = positionSide;
    }

    @Override
    public Trade.TradeType positionSide() {
        return positionSide;
    }

    @Override
    public Num getStopLossPrice() {
        return stopLossPrice;
    }

    @Override
    public Num getInitialStopLossPrice() {
        return initialStopLossPrice;
    }

    @Override
    public Num getEntryPrice() {
        return entryPrice;
    }

    @Override
    public Num getTakeProfitPrice() {
        return takeProfitPrice;
    }

    @Override
    public Num positionEntrySize() {
        return DecimalNum.valueOf(1);
    }
}
