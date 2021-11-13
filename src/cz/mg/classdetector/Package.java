package cz.mg.classdetector;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.ToStringBuilder;
import cz.mg.collections.list.List;


public @Utility class Package {
    private final @Mandatory @Value String name;
    private final @Optional @Link Package parent;
    private final @Mandatory @Part List<Package> packages = new List<>();
    private final @Mandatory @Link List<Class> classes = new List<>();

    public Package(@Mandatory String name, @Optional Package parent) {
        this.name = name;
        this.parent = parent;
    }

    public @Mandatory String getName() {
        return name;
    }

    public @Optional Package getParent() {
        return parent;
    }

    public @Mandatory List<Package> getPackages() {
        return packages;
    }

    public @Mandatory List<Class> getClasses() {
        return classes;
    }

    public @Mandatory String getFullName() {
        List<String> path = new List<>();
        Package current = this;
        while(current != null){
            if(current.name.length() > 0){
                path.addFirst(current.name);
            }
            current = current.parent;
        }
        return new ToStringBuilder<>(path).delim(".").build();
    }
}
