package com.thoughtworks.trains;

import java.util.*;

/**
 * Created by sijieliu on 3/31/18.
 */
public class ResolverOne {
    private final List<String> input;

    //key: from -> value: {to, distance}
    private final Map<String, List<Path>> graph;

    //key: from + to -> value: distance
    private final Map<String, Integer> distanceMap;
    private static final String NO_SUCH_ROUTE = "NO SUCH ROUTE";
    private static final int DISTANCE_THRESHOLD = 200;
    private static final int STOP_THRESHOLD = 10;

    public ResolverOne(List<String> input) {
        this.input = input;
        this.graph = new HashMap<>();
        this.distanceMap = new HashMap<>();
    }

    public void resolve() {
        initGraph();

        //q1
        printDistance(1, "ABC");

        //q2
        printDistance(2, "AD");

        //q3
        printDistance(3, "ADC");

        //q4
        printDistance(4, "AEBCD");

        //q5
        printDistance(5, "AED");

        //q6
        List<String> q6 = getRoutes("C", "C", 3, -1);
        log(6, q6.size() - 1);

        //q7
        List<String> q7 = getRoutes("A", "C", 4, -1);
        int ctr = 0;

        for(int i = 1; i < q7.size(); i++) {
            ctr += q7.get(i).length() == 5 ? 1 : 0;
        }

        log(7, ctr);

        //q8
        List<String> q8 = getRoutes("A", "C", -1, -1);
        log(8, Integer.parseInt(q8.get(0)));

        //q9
        List<String> q9 = getRoutes("B", "B", -1, -1);
        log(9, Integer.parseInt(q9.get(0)));

        //q10
        List<String> q10 = getRoutes("C", "C", -1, 30);
        log(10, q10.size() - 1);
    }

    //get routes between two stops
    //the first element of the returned route array is the minimum distance
    private List<String> getRoutes(String from, String to, int stopLimit, int disLimit) {
        List<String> routes = new ArrayList<>();
        routes.add(Integer.toString(Integer.MAX_VALUE));

        dfs(from, routes, from, to, 0, stopLimit, disLimit);

        return routes;
    }

    //build graph
    private void initGraph() {
        for(String s : input) {
            if(s.length() < 3) {
                throw new RuntimeException(Main.INVALID_INPUT);
            }

            String from = s.substring(0, 1);
            String to = s.substring(1, 2);
            int length = Integer.parseInt(s.substring(2));

            List<Path> paths = graph.computeIfAbsent(from, k -> new ArrayList<>());

            Path path = new Path(length, to);
            paths.add(path);
            distanceMap.put(from + to, length);
        }
    }

    private void dfs(String route, List<String> routes, String from, String to, int dis, int stopLimit, int disLimit) {
        if(from.equals(to) && dis != 0) {
            routes.add(route);
            int minDis = Integer.parseInt(routes.get(0));
            minDis = Math.min(dis, minDis);
            routes.set(0, Integer.toString(minDis));
        }

        if(stopLimit > 0 && route.length() > stopLimit || route.length() > STOP_THRESHOLD) {
            return;
        }

        if(disLimit > 0 && dis >= disLimit || dis > DISTANCE_THRESHOLD) {
            return;
        }

        List<Path> nextStops = graph.get(from);

        for(Path nextStop: nextStops) {
            int nextDis = nextStop.getLength();
            String dest = nextStop.getDest();

            if((disLimit == -1 || dis + nextDis < disLimit) && (stopLimit == -1 || route.length() <= stopLimit)) {
                dfs(route + dest, routes, dest, to, dis + nextDis, stopLimit, disLimit);
            }
        }
    }

    private void printDistance(int idx, String route) {
        int res = 0;

        for(int i = 0; i + 2 <= route.length(); i++) {
            String stop = route.substring(i, i + 2);

            if(distanceMap.containsKey(stop)) {
                res += distanceMap.get(stop);
            }
            else {
                log(idx, NO_SUCH_ROUTE);
                return;
            }
        }

        log(idx, res);
    }

    private void log(int idx, Object res) {
        System.out.println("Output #" + idx + ": " + res);
    }
}
