package cz.mg.classdetector;

import cz.mg.collections.list.List;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.runner.SingleTestRunner;

import java.io.FileInputStream;
import java.io.IOException;


public class ClassDetectorTest implements Test {
    private static final String PATH = "/home/me/Desktop/Dev/Java/JMgClassDetector/out/artifacts/JMgClassDetector_jar/JMgClassDetector.jar";

    public static void main(String[] args) {
        new SingleTestRunner().run(new ClassDetectorTest());
    }

    @TestCase
    public void testAll() throws IOException {
        try (FileInputStream stream = new FileInputStream(PATH)) {
            Package root = new ClassPackager().pack(
                new ClassResolver().resolve(
                    new ClassDetector().find(stream)
                )
            );

            Package classDetectorPackage = new PackageBrowser().open(
                new List<>("cz", "mg", "classdetector"),
                root
            );

            assertContains(classDetectorPackage.getClasses(), ClassDetector.class);
            assertContains(classDetectorPackage.getClasses(), ClassPackager.class);
            assertContains(classDetectorPackage.getClasses(), ClassResolver.class);
            assertContains(classDetectorPackage.getClasses(), Package.class);
            assertContains(classDetectorPackage.getClasses(), PackageBrowser.class);
        }
    }
}
