package by.dudko.education.algorithm.yandex.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * https://new.contest.yandex.ru/contests/48627/problem?id=215%2F2023_04_08%2F9lIXf3oJtM
 */
public class BillboardAdvertising {
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int billboardsCount = scanner.nextInt();
        int offersCount = scanner.nextInt();
        int weeks = scanner.nextInt();
        AdvertisementOffer[] advertisementOffers = new AdvertisementOffer[offersCount];
        for (int i = 0; i < offersCount; i++) {
            advertisementOffers[i] = new AdvertisementOffer(scanner.nextInt(), scanner.nextInt());
        }
        Arrays.sort(advertisementOffers);
        System.out.println(calculateProfit(billboardsCount, weeks, advertisementOffers));

//        for (int i = 0; i < 23; i++) {
//            Thread thread = new Thread(BillboardAdvertising::stressTest);
//            thread.start();
//        }
//        stressTest();
    }

    private static void stressTest() {
        while (true) {
            int billboards = RANDOM.nextInt(999) + 1;
            int offers = RANDOM.nextInt(99999) + 1;
            int duration = RANDOM.nextInt(99) + 1;
            AdvertisementOffer[] advertisementOffers = new AdvertisementOffer[offers];
            for (int l = 0; l < offers; l++) {
                advertisementOffers[l] = new AdvertisementOffer(RANDOM.nextInt(99) + 1, RANDOM.nextInt(99) + 1);
            }
            Arrays.sort(advertisementOffers);
            AdvertisementOffer[] advertisementOffersCopy = copyAdvertisements(offers, advertisementOffers);
            AdvertisementOffer[] input = copyAdvertisements(offers, advertisementOffers);
            long first = calculateProfit(billboards, duration, advertisementOffers);
            long second = calculateProfit2(billboards, duration, advertisementOffersCopy);
            if (first != second) {
                System.out.format("billboards=%s offers=%s duration=%s%n", billboards, offers, duration);
                System.out.println(Arrays.toString(input));
                System.out.println("Invalid algorithm! First: " + first + " Second: " + second);
                System.exit(1);
            }
        }
    }

    private static AdvertisementOffer[] copyAdvertisements(int offers, AdvertisementOffer[] advertisementOffers) {
        AdvertisementOffer[] advertisementOffersCopy = new AdvertisementOffer[offers];
        for (int l = 0; l < advertisementOffers.length; l++) {
            AdvertisementOffer offer = advertisementOffers[l];
            advertisementOffersCopy[l] = new AdvertisementOffer(offer.price, offer.duration);
        }
        return advertisementOffersCopy;
    }

    private static long calculateProfit(int billboardsCount, int weeks, AdvertisementOffer[] advertisementOffers) {
        int offersCount = advertisementOffers.length;
        long profit = 0;
        int startIndex = Math.min(billboardsCount, offersCount);
        List<AdvertisementOffer> activeOffers = new ArrayList<>(
                Arrays.asList(advertisementOffers).subList(0, startIndex));

        for (int i = 0; i < weeks && !activeOffers.isEmpty(); i++) {
            Iterator<AdvertisementOffer> iterator = activeOffers.iterator();
            List<AdvertisementOffer> offersToAdd = new ArrayList<>();
            while (iterator.hasNext()) {
                AdvertisementOffer offer = iterator.next();
                profit += offer.price;
                int remainingDuration = offer.duration - 1;
                if (remainingDuration == 0) {
                    iterator.remove();
                    if (startIndex < offersCount) {
                        offersToAdd.add(advertisementOffers[startIndex++]);
                    }
                }
                offer.duration = remainingDuration;
            }
            activeOffers.addAll(offersToAdd);
        }
        return profit;
    }

    private static long calculateProfit2(int billboardsCount, int weeks, AdvertisementOffer[] advertisementOffers) {
        long profit = 0;
        for (int i = 0; i < weeks; i++) {
            List<AdvertisementOffer> selected = new ArrayList<>();
            for (int j = 0; j < advertisementOffers.length && selected.size() < billboardsCount; j++) {
                AdvertisementOffer current = advertisementOffers[j];
                if (current.duration != 0) {
                    selected.add(current);
                    profit += current.price;
                    current.duration--;
                }
            }
            if (selected.isEmpty()) {
                break;
            }
        }
        return profit;
    }

    private static long calculateProfitIncorrectSolutionInWhichMultipleBillboardsAllowedForSameCustomer(
            int billboardsCount, int weeks, AdvertisementOffer[] advertisementOffers) {
        long profit = 0;
        for (int i = 0; i < weeks; i++) {
            List<AdvertisementOffer> selected = new ArrayList<>();
            for (int j = 0; j < advertisementOffers.length && selected.size() < billboardsCount; ) {
                AdvertisementOffer current = advertisementOffers[j];
                if (current.duration != 0) {
                    selected.add(current);
                    profit += current.price;
                    current.duration--;
                } else {
                    j++;
                }
            }
            if (selected.isEmpty()) {
                break;
            }
        }
        return profit;
    }

    private static class AdvertisementOffer implements Comparable<AdvertisementOffer> {
        int price;
        int duration;

        AdvertisementOffer(int price, int duration) {
            this.price = price;
            this.duration = duration;
        }

        @Override
        public int compareTo(AdvertisementOffer o) {
            return Integer.compare(o.price, price);
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer();
            sb.append("pr=").append(price);
            sb.append(", dur=").append(duration);
            return sb.toString();
        }
    }
}
