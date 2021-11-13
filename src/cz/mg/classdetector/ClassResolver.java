package cz.mg.classdetector;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;


public @Service class ClassResolver {
    public @Mandatory List<Class> resolve(@Mandatory List<String> classNames){
        List<Class> classes = new List<>();
        try {
            for(String className : classNames){
                classes.addLast(Class.forName(className));
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return classes;
    }
}
