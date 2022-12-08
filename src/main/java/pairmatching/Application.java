package pairmatching;

import pairmatching.controller.PairMatchingController;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        PairMatchingController pairMatchingController = new PairMatchingController();
        pairMatchingController.run();
    }
}
