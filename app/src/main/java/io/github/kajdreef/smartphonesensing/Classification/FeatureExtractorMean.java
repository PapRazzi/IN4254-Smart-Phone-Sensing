package io.github.kajdreef.smartphonesensing.Classification;

import java.util.ArrayList;

public class FeatureExtractorMean extends FeatureExtractor {
    @Override
    public FeatureSet extractFeatures(ArrayList<Float> x, ArrayList<Float> y, ArrayList<Float> z) {
        ArrayList<Float> magnitude = new ArrayList<>(x.size());

        for (int i = 0;i<x.size();i++){
            magnitude.add(i,(float)Math.sqrt(Math.pow(x.get(i),2.0)+Math.pow(y.get(i),2.0)+Math.pow(z.get(i),2.0)));
        }
        return new FeatureSet(sum(magnitude)/x.size());
    }

    private float sum(ArrayList<Float> in){
        float s = 0 ;
        for(Float f : in){
            s = s+f;
        }
        return s;
    }
}
