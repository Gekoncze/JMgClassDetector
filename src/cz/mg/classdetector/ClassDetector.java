package cz.mg.classdetector;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public @Service class ClassDetector {
    public @Mandatory List<Class> find(@Mandatory String path){
        try {
            return find(new FileInputStream(path));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public @Mandatory List<Class> find(@Mandatory InputStream stream){
        List<Class> classes = new List<>();
        try {
            ZipInputStream zip = new ZipInputStream(stream);
            for(ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
                if(!entry.isDirectory()) {
                    if(entry.getName().endsWith(".class")){
                        String classFileFullName = entry.getName().replace('/', '.');
                        String classFullName = removeClassExtension(classFileFullName);
                        try {
                            classes.addLast(Class.forName(classFullName));
                        } catch (ClassNotFoundException e){
                        }
                    }
                }
            }
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return classes;
    }

    private @Mandatory String removeClassExtension(@Mandatory String classFileName){
        return classFileName.substring(0, classFileName.length() - ".class".length());
    }
}
