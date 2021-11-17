package cz.mg.classdetector;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;

import java.util.StringTokenizer;


public @Service class PackageBrowser {
    public @Mandatory Package open(@Mandatory String path, @Mandatory Package root){
        List<String> pathParts = new List<>();
        StringTokenizer tokenizer = new StringTokenizer(path, ".");
        while(tokenizer.hasMoreTokens()){
            pathParts.addLast(tokenizer.nextToken());
        }
        return open(pathParts, root);
    }

    public @Mandatory Package open(@Mandatory List<String> path, @Mandatory Package root){
        Package currentPackage = root;
        for(String name : path){
            currentPackage = open(name, currentPackage.getPackages());
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
