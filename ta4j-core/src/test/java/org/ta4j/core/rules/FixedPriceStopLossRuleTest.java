package org.ta4j.core.rules;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.ta4j.core.*;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.num.DecimalNum;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FixedPriceStopLossRuleTest {

    @Test
    public void testNullTradingRecord(){
        ClosePriceIndicator mockClosePriceIndicator = mock(ClosePriceIndicator.class);

        FixedPriceStopLossRule rule = new FixedPriceStopLossRule(mockClosePriceIndicator);

        assertFalse(rule.isSatisfied(1));
        assertFalse(rule.isSatisfied(1, null));
    }

    @Test
    public void testIsSatisfiedNoPositionOpened(){
        ClosePriceIndicator mockClosePriceIndicator = mock(ClosePriceIndicator.class);
        Position mockPosition = mock(Position.class);
        when(mockPosition.isOpened()).thenReturn(false);
        TradingRecord mockTradingRecord = mock(TradingRecord.class);
        when(mockTradingRecord.getCurrentPosition()).thenReturn(mockPosition);
        FixedPriceStopLossRule rule = new FixedPriceStopLossRule(mockClosePriceIndicator);

        assertFalse(rule.isSatisfied(1, mockTradingRecord));
    }

    @Test
    public void testIsSatisfiedSell() {
        CustomPositionData mockCustomPositionData = mock(CustomPositionData.class);
        //when(mockCustomPositionData.getEntryPrice()).thenReturn(DecimalNum.valueOf(48873.0));
        when(mockCustomPositionData.getStopLossPrice()).thenReturn(DecimalNum.valueOf(48905.0));
        //when(mockCustomPositionData.positionSide()).thenReturn(Trade.TradeType.SELL);

        Trade mockEntryTrade = mock(Trade.class);
        when(mockEntryTrade.isBuy()).thenReturn(false);

        ClosePriceIndicator mockClosePriceIndicator = mock(ClosePriceIndicator.class);
        //when(mockClosePriceIndicator.getValue(0)).thenReturn(DecimalNum.valueOf(48873.0));
        when(mockClosePriceIndicator.getValue(1)).thenReturn(DecimalNum.valueOf(48872.5));
        when(mockClosePriceIndicator.getValue(2)).thenReturn(DecimalNum.valueOf(48799.0));
        when(mockClosePriceIndicator.getValue(3)).thenReturn(DecimalNum.valueOf(48765.5));
        when(mockClosePriceIndicator.getValue(4)).thenReturn(DecimalNum.valueOf(48709.5));
        when(mockClosePriceIndicator.getValue(5)).thenReturn(DecimalNum.valueOf(49006.0));
        //when(mockClosePriceIndicator.getValue(6)).thenReturn(DecimalNum.valueOf(49587.5));
        //when(mockClosePriceIndicator.getValue(7)).thenReturn(DecimalNum.valueOf(48674.5));
        //when(mockClosePriceIndicator.getValue(8)).thenReturn(DecimalNum.valueOf(48782.0));
        //when(mockClosePriceIndicator.getValue(9)).thenReturn(DecimalNum.valueOf(48750.0));

        Position mockPosition = mock(Position.class);
        when(mockPosition.isOpened()).thenReturn(true);
        when(mockPosition.getEntry()).thenReturn(mockEntryTrade);
        when(mockPosition.getCustomPositionData()).thenReturn(mockCustomPositionData);
        TradingRecord mockTradingRecord = mock(TradingRecord.class);
        when(mockTradingRecord.getCurrentPosition()).thenReturn(mockPosition);
        FixedPriceStopLossRule rule = new FixedPriceStopLossRule(mockClosePriceIndicator);

        assertFalse(rule.isSatisfied(1, mockTradingRecord));
        assertFalse(rule.isSatisfied(2, mockTradingRecord));
        assertFalse(rule.isSatisfied(3, mockTradingRecord));
        assertFalse(rule.isSatisfied(4, mockTradingRecord));
        assertTrue(rule.isSatisfied(5, mockTradingRecord));
        //assertFalse(rule.isSatisfied(6, mockTradingRecord));
        //assertTrue(rule.isSatisfied(7, mockTradingRecord));
    }


    @Test
    public void testIsSatisfiedBuy() {
        CustomPositionData mockCustomPositionData = mock(CustomPositionData.class);
        //when(mockCustomPositionData.getEntryPrice()).thenReturn(DecimalNum.valueOf(48873.0));
        when(mockCustomPositionData.getStopLossPrice()).thenReturn(DecimalNum.valueOf(48750.0));
        //when(mockCustomPositionData.positionSide()).thenReturn(Trade.TradeType.SELL);

        Trade mockEntryTrade = mock(Trade.class);
        when(mockEntryTrade.isBuy()).thenReturn(true);

        ClosePriceIndicator mockClosePriceIndicator = mock(ClosePriceIndicator.class);
        //when(mockClosePriceIndicator.getValue(0)).thenReturn(DecimalNum.valueOf(48873.0));
        when(mockClosePriceIndicator.getValue(1)).thenReturn(DecimalNum.valueOf(48872.5));
        when(mockClosePriceIndicator.getValue(2)).thenReturn(DecimalNum.valueOf(48799.0));
        when(mockClosePriceIndicator.getValue(3)).thenReturn(DecimalNum.valueOf(48765.5));
        when(mockClosePriceIndicator.getValue(4)).thenReturn(DecimalNum.valueOf(48709.5));
        //when(mockClosePriceIndicator.getValue(5)).thenReturn(DecimalNum.valueOf(49006.0));
        //when(mockClosePriceIndicator.getValue(6)).thenReturn(DecimalNum.valueOf(49587.5));
        //when(mockClosePriceIndicator.getValue(7)).thenReturn(DecimalNum.valueOf(48674.5));
        //when(mockClosePriceIndicator.getValue(8)).thenReturn(DecimalNum.valueOf(48782.0));
        //when(mockClosePriceIndicator.getValue(9)).thenReturn(DecimalNum.valueOf(48750.0));

        Position mockPosition = mock(Position.class);
        when(mockPosition.isOpened()).thenReturn(true);
        when(mockPosition.getEntry()).thenReturn(mockEntryTrade);
        when(mockPosition.getCustomPositionData()).thenReturn(mockCustomPositionData);
        TradingRecord mockTradingRecord = mock(TradingRecord.class);
        when(mockTradingRecord.getCurrentPosition()).thenReturn(mockPosition);
        FixedPriceStopLossRule rule = new FixedPriceStopLossRule(mockClosePriceIndicator);

        assertFalse(rule.isSatisfied(1, mockTradingRecord));
        assertFalse(rule.isSatisfied(2, mockTradingRecord));
        assertFalse(rule.isSatisfied(3, mockTradingRecord));
        assertTrue(rule.isSatisfied(4, mockTradingRecord));
        //assertFalse(rule.isSatisfied(5, mockTradingRecord));
        //assertTrue(rule.isSatisfied(6, mockTradingRecord));
    }
}
