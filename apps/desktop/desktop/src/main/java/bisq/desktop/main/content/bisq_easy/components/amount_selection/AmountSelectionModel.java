/*
 * This file is part of Bisq.
 *
 * Bisq is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * Bisq is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Bisq. If not, see <http://www.gnu.org/licenses/>.
 */

package bisq.desktop.main.content.bisq_easy.components.amount_selection;

import bisq.bisq_easy.BisqEasyTradeAmountLimits;
import bisq.common.currency.Market;
import bisq.common.currency.MarketRepository;
import bisq.common.monetary.Monetary;
import bisq.desktop.common.view.Model;
import bisq.offer.Direction;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
public class AmountSelectionModel implements Model {
    private final boolean useQuoteCurrencyForMinMaxRange;
    private final double sliderMin = 0;
    private final double sliderMax = 1;

    private final ObjectProperty<Monetary> baseSideAmount = new SimpleObjectProperty<>();
    private final ObjectProperty<Monetary> quoteSideAmount = new SimpleObjectProperty<>();
    private final StringProperty spendOrReceiveString = new SimpleStringProperty();

    private final DoubleProperty sliderValue = new SimpleDoubleProperty();
    private final BooleanProperty sliderFocus = new SimpleBooleanProperty();

    @Setter
    private ObjectProperty<Monetary> minRangeMonetary = new SimpleObjectProperty<>(BisqEasyTradeAmountLimits.DEFAULT_MIN_BTC_TRADE_AMOUNT);
    @Setter
    private ObjectProperty<Monetary> maxRangeMonetary = new SimpleObjectProperty<>(BisqEasyTradeAmountLimits.DEFAULT_MAX_BTC_TRADE_AMOUNT);
    @Setter
    private ObjectProperty<Monetary> minRangeBaseSideValue = new SimpleObjectProperty<>();
    @Setter
    private ObjectProperty<Monetary> maxRangeBaseSideValue = new SimpleObjectProperty<>();
    @Setter
    private ObjectProperty<Monetary> minRangeQuoteSideValue = new SimpleObjectProperty<>();
    @Setter
    private ObjectProperty<Monetary> maxRangeQuoteSideValue = new SimpleObjectProperty<>();
    @Setter
    private Monetary leftMarkerQuoteSideValue;
    @Setter
    private Monetary rightMarkerQuoteSideValue;
    private final StringProperty sliderTrackStyle = new SimpleStringProperty();
    @Setter
    private Market market = MarketRepository.getDefault();
    @Setter
    private Direction direction = Direction.BUY;
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty minRangeValueAsString = new SimpleStringProperty();
    private final StringProperty maxRangeValueAsString = new SimpleStringProperty();

    public AmountSelectionModel(boolean useQuoteCurrencyForMinMaxRange) {
        this.useQuoteCurrencyForMinMaxRange = useQuoteCurrencyForMinMaxRange;
    }

    void reset() {
        baseSideAmount.set(null);
        quoteSideAmount.set(null);
        spendOrReceiveString.set(null);
        sliderValue.set(0L);
        sliderFocus.set(false);
        market = MarketRepository.getDefault();
        direction = Direction.BUY;
        leftMarkerQuoteSideValue = null;
        rightMarkerQuoteSideValue = null;
    }
}
