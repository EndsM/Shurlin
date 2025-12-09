package xyz.shurlin.cultivation.interfaces;

import xyz.shurlin.cultivation.dynamic.TechniqueGrade;
import xyz.shurlin.cultivation.models.GeneratedTechnique;

import java.util.Random;

// In the future, the cultivation package will not be handling the generation of Techniques
// But will work as an API to register a Technique strategy for a Cultivation Type
public interface TechniqueGeneratorType {
    /**
     * Generates a new technique based on this specific rule/type.
     *
     * @param grade The target grade (Yellow, Black, Earth, Heaven)
     * @param random The random instance
     * @return A newly created, unique technique
     */
    GeneratedTechnique generate(TechniqueGrade grade, Random random);
}
