package com.example.fitnessapp.services;

import com.example.fitnessapp.utils.*;
import org.springframework.stereotype.Service;

@Service
public class SetService {

    public SetPrediction predict(double currentPR, double weight, int reps) {

        // 1. Calculate estimated 1RM using your real util
        double estimated1RM = OneRepMaxCalculator.average1RM(weight, reps);

        // 2. Determine if this set beats PR
        boolean beatsPR = estimated1RM > currentPR;

        // 3. Calculate reps needed to beat PR at this weight
        int repsNeeded = 1;
        while (OneRepMaxCalculator.average1RM(weight, repsNeeded) <= currentPR) {
            repsNeeded++;
            if (repsNeeded > 50) break; // safety guard
        }

        // 4. Calculate weight needed to beat PR at these reps
        double weightNeeded = weight;
        while (OneRepMaxCalculator.average1RM(weightNeeded, reps) <= currentPR) {
            weightNeeded += 2.5;
            if (weightNeeded > 2000) break; // safety guard
        }

        // 5. Build SetPrediction object using setters
        SetPrediction prediction = new SetPrediction();
        prediction.estimated1RM = estimated1RM;
        prediction.repsNeededToBeatPR = repsNeeded;
        prediction.weightNeededToBeatPR = weightNeeded;
        prediction.willBeatPR = beatsPR;

        return prediction;
    }

    public SetPrediction increaseWeightBy2_5(double currentPR, double weight, int reps) {
        return predict(currentPR, WeightAdjuster.increaseBy2_5(weight), reps);
    }

    public SetPrediction increaseWeightBy5(double currentPR, double weight, int reps) {
        return predict(currentPR, WeightAdjuster.increaseBy5(weight), reps);
    }

    public SetPrediction decreaseWeightBy2_5(double currentPR, double weight, int reps) {
        return predict(currentPR, WeightAdjuster.decreaseBy2_5(weight), reps);
    }

    public SetPrediction decreaseWeightBy5(double currentPR, double weight, int reps) {
        return predict(currentPR, WeightAdjuster.decreaseBy5(weight), reps);
    }

    public SetPrediction increaseReps(double currentPR, double weight, int reps) {
        return predict(currentPR, weight, RepAdjuster.increase(reps));
    }

    public SetPrediction decreaseReps(double currentPR, double weight, int reps) {
        return predict(currentPR, weight, RepAdjuster.decrease(reps));
    }
}
