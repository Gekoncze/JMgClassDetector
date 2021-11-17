package cz.mg.classdetector;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;

import java.io.*;


public @Service class ClassWriter {
    public void write(@Mandatory String path, @Mandatory List<Class> classes){
        try {
            write(new FileOutputStream(path), classes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void write(@Mandatory OutputStream stream, @Mandatory List<Class> classes){
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream))){
            for(Class clazz : classes){
                writer.write(clazz.getName());
                writer.newLine();
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
