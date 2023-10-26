package edu.hw3;

import edu.hw3.task6.PriorityStockMarket;
import edu.hw3.task6.Stock;
import edu.hw3.task6.StockMarket;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {

    @Test
    void test() {
        StockMarket market = new PriorityStockMarket();

        assertThat(market.mostValuableStock()).isNull();

        market.add(new Stock(1));
        assertThat(market.mostValuableStock()).isEqualTo(new Stock(1));

        market.add(new Stock(3));
        assertThat(market.mostValuableStock()).isEqualTo(new Stock(3));

        market.add(new Stock(2));
        assertThat(market.mostValuableStock()).isEqualTo(new Stock(3));

        market.add(new Stock(4));
        assertThat(market.mostValuableStock()).isEqualTo(new Stock(4));

        market.remove(new Stock(3));
        assertThat(market.mostValuableStock()).isEqualTo(new Stock(4));

        market.remove(new Stock(4));
        assertThat(market.mostValuableStock()).isEqualTo(new Stock(2));
    }
}
