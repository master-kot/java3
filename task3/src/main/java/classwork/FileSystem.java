package classwork;

//import jdk.management.resource.internal.inst.FileInputStreamRMHooks;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileSystem {

    static String path = "C:\\Users\\Mikhail\\IdeaProjects\\Java31\\task3\\src\\main\\java\\classwork";
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //System.out.println(Arrays.toString(File.listRoots()));
        File file = new File(path + "1.txt");
        File p = new File(path);
        //p.mkdir();
        System.out.println(p.isDirectory());
        //System.out.println(p.getParentFile());
        //System.out.println(Arrays.toString(p.listFiles()));
        File parent = p;
        while (parent != null) {
            //System.out.println(parent);
            parent = parent.getParentFile();
        }
        dfs(new File("E:\\"), "", 3);
    }

    static void writeUser(File file, User user) throws IOException {
        return;
    }

    static User readUser(File file) throws IOException, ClassNotFoundException {
        return null;
    }

    static void writeData(File file, boolean append) throws IOException {

    }

    static void printFileData(File file) throws FileNotFoundException {

    }

    static void createPaths(File file) throws IOException {

    }

    static void dfs(File file, String depth, int d){
        if (d == 0) return;
        if (file != null) {
            System.out.println(depth + file);
            if (file.isDirectory()) {
                File [] files = file.listFiles();
                if (files != null) {
                    for(File f : files) {
                        dfs(f, depth + "-", d-1);
                    }
                }
            }
        }
    }
}
