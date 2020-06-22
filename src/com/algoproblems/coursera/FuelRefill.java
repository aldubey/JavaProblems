package com.algoproblems.coursera;

public class FuelRefill {
    static String str;

    FuelRefill() {
        str = "";
    }

    public static int minRefuelStops(int target, int startFuel, int[][] stations) {
        if (startFuel >= target) {
            return 0;
        }
        if ((stations == null || stations.length == 0 || stations[0].length == 0)) {
            return -1;
        }

        if (startFuel < stations[0][0]) {
            return -1;
        }

        int count = 0;
        int distanceToCover = target;
        int remainingFuel = startFuel;
        int[] station;
        int i = 0;
        int nextStationDistance = 0;
        int previous = 0;
        do {
            station = stations[i];
            distanceToCover = target - station[0];
            remainingFuel = remainingFuel - (station[0] - previous);
            if (i + 1 == stations.length) {
                nextStationDistance = distanceToCover;
                if (nextStationDistance > remainingFuel + station[1]) {
                    return -1;
                }
            } else {
                nextStationDistance = stations[i + 1][0] - station[0];
            }
            if (remainingFuel == 0 || nextStationDistance - remainingFuel > remainingFuel) {
                remainingFuel += station[1];
                count++;
            }
            previous = station[0];
            ++i;
        } while (distanceToCover > remainingFuel);

        return count;
    }

    private static boolean isRefillRequired(int remainingFuel, int distanceToCover) {
        return remainingFuel < distanceToCover;
    }


    public static void main(String[] args) {
        //Input: target = 1, startFuel = 1, stations = []
        //Output: 0

        //target = 100, startFuel = 1, stations = [[10,100]]
        // Output: -1

        //Input: target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
        //Output: 2

        // 999 1000                [[5,100],[997,100],[998,100]]

        int target = 100;
        int startFuel = 50;
        //int[][] stations = {{5, 100}, {997, 100}, {998, 100}};
        //   int[][] stations = {{10, 60}, {20, 30}, {30, 60}, {60, 40}};
        // int[][] stations = {{25, 25}, {50, 25}, {75, 25}};
        int[][] stations = {{25,50},{50, 25}};
        System.out.println("minRefuelStops: " + minRefuelStops(target, startFuel, stations));
    }

    /*
     *
     *
     *
     *
     *
     * */
}
