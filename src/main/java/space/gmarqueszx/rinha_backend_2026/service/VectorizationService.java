package space.gmarqueszx.rinha_backend_2026.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.gmarqueszx.rinha_backend_2026.model.TransactionRequest;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Service
public class VectorizationService {
    private static final double MAX_AMOUNT = 10000;
    private static final double MAX_INSTALLMENTS = 12;
    private static final double AMOUNT_VS_AVG_RATIO = 10;
    private static final double MAX_MINUTES = 1440;
    private static final double MAX_KM = 1000;
    private static final double MAX_TX_COUNT_24H = 20;
    private static final double MAX_MERCHANT_AVG_AMOUNT = 10000;

    public double[] vectorize(TransactionRequest request) {
        double[] vector = new double[14];

        ZonedDateTime dt = request.transaction().requestAt().atZone(ZoneOffset.UTC);

        //Dimension 0 = Amount
        double amount = clamp(request.transaction().amount() / MAX_AMOUNT);
        vector[0] = amount;

        //Dimension 1 = Installments
        double installments = clamp(request.transaction().installments() / MAX_INSTALLMENTS);
        vector[1] = installments;

        //Dimension 2 = AmountVsAvg
        double amountVsAvg = clamp((request.transaction().amount() /
                request.customer().avgAmount()) / AMOUNT_VS_AVG_RATIO);
        vector[2] = amountVsAvg;

        //Dimension 3 = HourOfDay
        double hourOfDay = clamp(dt.getHour() / 23.0);
        vector[3] = hourOfDay;

        //Dimension 4 = DayOfWeek
        double dayOfWeek = (dt.getDayOfWeek().getValue() - 1) / 6.0;
        vector[4] = dayOfWeek;

        //Dimension 5 = MinutesSinceLastTx
        if (request.lastTransaction() == null) {
            vector[5] = -1;
        } else {
            long minutes = Duration.between(
                    request.lastTransaction().timestamp(),
                    request.transaction().requestAt()
            ).toMinutes();
            vector[5] = clamp(minutes / MAX_MINUTES);
        };

        //Dimension 6 = KmFromLastTx
        if (request.lastTransaction() == null) {
            vector[6] = -1;
        } else {
            vector[6] = clamp(request.lastTransaction().kmFromCurrent() / MAX_KM);
        }

        //Dimension 7 = KmFromHome
        double kmFromHome = clamp(request.terminal().kmFromHome() / MAX_KM);
        vector[7] = kmFromHome;

        //Dimension 8 = TxCount24h
        double txCount24h = clamp(request.customer().txCount24h() / MAX_TX_COUNT_24H);
        vector[8] = txCount24h;

        //Dimension 9 = IsOnline
        if (request.terminal().isOnline()) {
            vector[9] = 1;
        } else {
            vector[9] = 0;
        }

        //Dimension 10 = CardPresent
        if (request.terminal().cardPresent()) {
            vector[10] = 1;
        } else {
            vector[10] = 0;
        }

        //Dimension 11 = UnknownMerchant
        if (request.customer().knownMerchants().contains(request.merchant().id())) {
            vector[11] = 0;
        } else {
            vector[11] = 1;
        }

        //Dimension 12 = MccRisk
        vector[12] = 0.5;

        //Dimension 13 = MerchantAvgAmount
        double merchantAvgAmount = clamp(request.merchant().avgAmount() / MAX_MERCHANT_AVG_AMOUNT);
        vector[13] = merchantAvgAmount;

        return vector;
    }

    private double clamp(double value) {
        return Math.max(0.0, Math.min(1.0, value));
    }
}
