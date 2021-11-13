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
    private final @Mandatory @Value String fullName;
    private final @Optional @Link Package parent;
    private final @Mandatory @Part List<Package> packages = new List<>();
    private final @Mandatory @Link List<Class> classes = new List<>();

    public Package(@Mandatory String name, @Optional Package parent) {
        this.name = name;
        this.parent = parent;
        this.fullName = getFullName(this);
    }

    public @Mandatory String getName() {
        return name;
    }

    public @Mandatory String getFullName() {
        return fullName;
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

    private static @Mandatory String getFullName(@Mandatory Package self) {
        List<String> path = new List<>();
        Package current = self;
        while(current != null){
            if(current.name.length() > 0){
                path.addFirst(current.name);
            }
            current = current.parent;
        }
        return new ToStringBuilder<>(path).delim(".").build();
    }
}
