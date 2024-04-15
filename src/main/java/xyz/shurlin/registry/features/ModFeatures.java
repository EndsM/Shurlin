package xyz.shurlin.registry.features;

// Use this class to init every feature related things
// Which means other classes in this package
public class ModFeatures {

    public static void RegisterAndHandle() {
        // TODO
        registerFeatures();
        ModConfiguredFeatures.Register();

        // Handle this in the last, so every thing it needed will be registered while handle them
        handleBiomeModifications();
    }

    private static void registerFeatures() {
        // Use this to register Feature<>
    }

    private static void handleBiomeModifications() {
        // TODO
    }
}
