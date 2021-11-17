package cz.mg.classdetector;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.collections.list.List;

import java.io.IOException;


public class ClassDetectorTest {
    private static final String JAR_PATH = "/home/me/Desktop/Dev/Java/JMgClassDetector/out/artifacts/JMgClassDetector_jar/JMgClassDetector.jar";
    private static final String RESULTS_PATH = "/home/me/Desktop/Dev/Java/JMgClassDetector/test/cz/mg/classdetector/results.txt";

    public static void main(String[] args) {
        System.out.print("Running test ... ");
        try {
            new ClassDetectorTest().test();
            System.out.println("OK");
        } catch (RuntimeException e){
            System.out.println("FAILED");
            System.out.println();
            e.printStackTrace();
        }
    }

    private void test() {
        new ClassWriter().write(RESULTS_PATH, new ClassDetector().find(JAR_PATH));
        Package root = new ClassPackager().pack(new ClassReader().read(RESULTS_PATH));

        Package classDetectorPackage = new PackageBrowser().open(
            new List<>("cz", "mg", "classdetector"),
            root
        );

        assertNotNull(classDetectorPackage);
        assertEquals("classdetector", classDetectorPackage.getName());
        assertEquals("cz.mg.classdetector", classDetectorPackage.getFullName());

        assertNotNull(classDetectorPackage.getParent());
        assertEquals("mg", classDetectorPackage.getParent().getName());
        assertEquals("cz.mg", classDetectorPackage.getParent().getFullName());

        assertNotNull(classDetectorPackage.getParent().getParent());
        assertEquals("cz", classDetectorPackage.getParent().getParent().getName());
        assertEquals("cz", classDetectorPackage.getParent().getParent().getFullName());

        assertNotNull(classDetectorPackage.getParent().getParent().getParent());
        assertEquals("", classDetectorPackage.getParent().getParent().getParent().getName());
        assertEquals("", classDetectorPackage.getParent().getParent().getParent().getFullName());

        assertNull(classDetectorPackage.getParent().getParent().getParent().getParent());

        assertContains(classDetectorPackage.getClasses(), ClassDetector.class);
        assertContains(classDetectorPackage.getClasses(), ClassPackager.class);
        assertContains(classDetectorPackage.getClasses(), ClassReader.class);
        assertContains(classDetectorPackage.getClasses(), ClassWriter.class);
        assertContains(classDetectorPackage.getClasses(), Package.class);
        assertContains(classDetectorPackage.getClasses(), PackageBrowser.class);
    }

    private void assertContains(@Mandatory List<Class> classes, @Mandatory Class clazz){
        for(Class expectedClass : classes){
            if(expectedClass == clazz) return;
        }
        throw new RuntimeException("Missing class '" + clazz.getSimpleName() + "'.");
    }

    private void assertEquals(@Mandatory String expectation, @Mandatory String reality){
        if(!expectation.equals(reality)){
            throw new RuntimeException("Expected '" + expectation + "', but got '" + reality + "'.");
        }
    }

    private void assertNotNull(@Optional Package packagee){
        if(packagee == null){
            throw new RuntimeException("Unexpected null value.");
        }
    }

    private void assertNull(@Optional Package packagee){
        if(packagee != null){
            throw new RuntimeException("Unexpected non-null value.");
        }
    }
}
