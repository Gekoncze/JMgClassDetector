package cz.mg.classdetector;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;

import java.io.*;


public @Service class ClassReader {
    public @Mandatory List<Class> read(@Mandatory String path){
        try {
            return read(new FileInputStream(path));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public @Mandatory List<Class> read(@Mandatory InputStream stream){
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(stream))){
            List<Class> classes = new List<>();
            String line;
            while((line = reader.readLine()) != null){
                try {
                    classes.addLast(Class.forName(line));
                } catch (ClassNotFoundException e){
                }
            }
            return classes;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
