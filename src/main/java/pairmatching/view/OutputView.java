package pairmatching.view;

import java.util.List;
import java.util.stream.Collectors;
import pairmatching.model.Pair;
import pairmatching.model.Pairs;

public class OutputView {

    public void printPair(Pairs pairs) {
        List<Pair> pairList = pairs.getPairs();
        List<List<String>> pairsName = pairList.stream()
                .map(Pair::getCrewNames)
                .collect(Collectors.toList());
        for (List<String> pairName : pairsName) {
            System.out.println(String.join(" : ",pairName));
        }
    }
}
