package cz.mg.classdetector;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;

import java.util.StringTokenizer;


public @Service class ClassPackager {
    public @Mandatory Package pack(@Mandatory List<Class> classes){
        Package root = new Package("", null);
        for(Class clazz : classes){
            List<String> path = getPath(clazz);
            Package targetPackage = open(path, root);
            targetPackage.getClasses().addLast(clazz);
        }
        return root;
    }

    private @Mandatory Package open(@Mandatory List<String> path, @Mandatory Package root){
        Package currentPackage = root;
        for(String name : path){
            currentPackage = open(name, currentPackage);
        }
        return currentPackage;
    }

    private @Mandatory Package open(@Mandatory String name, @Mandatory Package parent){
        for(Package packagee : parent.getPackages()){
            if(packagee.getName().equals(name)){
                return packagee;
            }
        }
        parent.getPackages().addLast(new Package(name, parent));
        return parent.getPackages().getLast();
    }

    private @Mandatory List<String> getPath(@Mandatory Class clazz){
        List<String> path = new List<>();
        StringTokenizer tokenizer = new StringTokenizer(clazz.getName(), ".");
        while(tokenizer.hasMoreTokens()){
            path.addLast(tokenizer.nextToken());
        }
        path.removeLast();
        return path;
    }
}
