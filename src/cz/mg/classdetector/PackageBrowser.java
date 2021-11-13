package cz.mg.classdetector;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;


public @Service class PackageBrowser {
    public @Mandatory Package open(@Mandatory List<String> path, @Mandatory Package root){
        Package currentPackage = root;
        for(String name : path){
            currentPackage = open(name, root.getPackages());
        }
        return currentPackage;
    }

    private @Mandatory Package open(@Mandatory String name, @Mandatory List<Package> packages){
        for(Package packagee : packages){
            if(packagee.getName().equals(name)){
                return packagee;
            }
        }
        throw new IllegalArgumentException("Could not find package '" + name + "'.");
    }
}
