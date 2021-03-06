package ClassificationTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import io.github.kajdreef.smartphonesensing.ActivityMonitoring.Type;
import io.github.kajdreef.smartphonesensing.Classification.FeatureExtractor;
import io.github.kajdreef.smartphonesensing.Classification.FeatureExtractorAC;
import io.github.kajdreef.smartphonesensing.Classification.FeatureExtractorFFT;
import io.github.kajdreef.smartphonesensing.Classification.FeatureExtractorMag;
import io.github.kajdreef.smartphonesensing.Classification.FeatureExtractorSD;
import io.github.kajdreef.smartphonesensing.Classification.KNN;
import io.github.kajdreef.smartphonesensing.Classification.LabeledFeatureSet;
import io.github.kajdreef.smartphonesensing.Utils.AbstractReader;


public class ClassificationTest {
    KNN knn;
    AbstractReader trainReader;
    AbstractReader validationReader;

    @Before
    public void setUp() {
        //Get allMag from files as 3 arraylists for 1 training point (15-20 points?)
        int step = 3;
        ArrayList<Type> labels = new ArrayList<>();
        ArrayList<Float> x = new ArrayList<>();
        ArrayList<Float> y = new ArrayList<>();
        ArrayList<Float> z = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            labels.add(Type.QUEUE);
            x.add(new Random().nextFloat());
            y.add(new Random().nextFloat());
            z.add(new Random().nextFloat());
        }
        for (int i = 0; i < 1000; i++) {
            labels.add(Type.WALK);
            x.add(new Random().nextFloat()/5 + (float)Math.sin(i*2*Math.PI/50));
            y.add(new Random().nextFloat()/5);
            z.add(new Random().nextFloat()/5);
        }
        ArrayList<FeatureExtractor> extractors = new ArrayList<>();
        extractors.add(new FeatureExtractorFFT());
        extractors.add(new FeatureExtractorSD());
        extractors.add(new FeatureExtractorMag());
        extractors.add(new FeatureExtractorAC());
        ArrayList<LabeledFeatureSet> train = FeatureExtractor.generateDataSet(labels,x,y,z,extractors,step);
        knn = new KNN(5,train);
    }

    @Test
    public void testClassification(){
        int step = 3;
        ArrayList<Type> labels = new ArrayList<>();
        ArrayList<Float> x = new ArrayList<>();
        ArrayList<Float> y = new ArrayList<>();
        ArrayList<Float> z = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            labels.add(Type.QUEUE);
            x.add(new Random().nextFloat());
            y.add(new Random().nextFloat());
            z.add(new Random().nextFloat());
        }

        for (int i = 0; i < 1000; i++) {
            labels.add(Type.WALK);
            x.add(new Random().nextFloat()/5+(float)Math.sin(i*2*Math.PI/50));
            y.add(new Random().nextFloat()/5);
            z.add(new Random().nextFloat()/5);
        }
        ArrayList<FeatureExtractor> extractors = new ArrayList<>();
        extractors.add(new FeatureExtractorFFT());
        extractors.add(new FeatureExtractorSD());
        extractors.add(new FeatureExtractorMag());
        extractors.add(new FeatureExtractorAC());
        ArrayList<LabeledFeatureSet> test = FeatureExtractor.generateDataSet(labels,x,y,z,extractors,step);
        float correct = knn.test(test);
        Assert.assertTrue(correct > (float) 0.5);
    }
}
