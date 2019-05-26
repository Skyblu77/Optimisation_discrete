package algo;

import algo.mapping.MPermutation;
import utils.Board;
import utils.Order;

import java.util.List;
import java.util.Random;

public class RandomWalk implements IAlgo {

    Random rand = new Random();
    int numberOfIterations = 1000000;
    MPermutation mapping = new MPermutation();

    @Override
    public Order compute(Board board) {
        Order solution = new Order(board.getSIZE());
        Order bestSolution = solution.clone();

        long bestFitness = Long.MAX_VALUE;
        for (int i = 0; i < numberOfIterations; i++) {
            List<Order> neighbors = mapping.getNeighbors(solution);
            int pickedOne = rand .nextInt(neighbors.size());
            solution = neighbors.get(pickedOne).clone();
            long fitness = board.computeFitness(solution);
            if(fitness < bestFitness){
                bestSolution = solution.clone();
                bestFitness = fitness;
            }
        }
        return bestSolution;
    }
}