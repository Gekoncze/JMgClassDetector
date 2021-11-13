package cz.mg.classdetector;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;


public @Utility class Package {
    private final @Mandatory String name;
    private final @Mandatory List<Package> packages = new List<>();
    private final @Mandatory List<Class> classes = new List<>();

    public Package(@Mandatory String name) {
        this.name = name;
    }

    public @Mandatory String getName() {
        return name;
    }

    public @Mandatory List<Package> getPackages() {
        return packages;
    }

    public @Mandatory List<Class> getClasses() {
        return classes;
    }
}
