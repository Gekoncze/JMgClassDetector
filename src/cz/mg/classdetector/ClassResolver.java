package cz.mg.classdetector;

import cz.mg.annotations.classes.Service;
import cz.mg.collections.list.List;


public @Service class ClassResolver {
    public List<Class> resolve(List<String> classNames){
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
