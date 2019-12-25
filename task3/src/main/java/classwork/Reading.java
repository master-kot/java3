package classwork;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Reading {

    void ScannerRead(File file) throws FileNotFoundException {
        Scanner in = new Scanner(file);
        while (in.hasNextLine()) {
            System.out.println(in.nextLine());
        }
    }

    void ScannerImageRead(File img) throws FileNotFoundException {
        Scanner in = new Scanner(img);
        while (in.hasNextByte()) {
            System.out.println(in.nextByte());
        }
    }

    void FileInputStreamRead(File img) throws IOException {
        FileReader reader = new FileReader(img);
        int x;
        while ((x = reader.read()) != -1) {
            System.out.print(x + " ");
        }
    }

    void ImgWrite(File first, File second) throws IOException {
        InputStream reader = new FileInputStream(first);
        OutputStream writer = new FileOutputStream(second);
        int x;
        while ((x = reader.read()) != -1) {
            writer.write(x);
        }
        writer.close();
    }

    void writer() throws IOException {
        File file = new File("C:\\Users\\Mikhail\\IdeaProjects\\Java31\\task3\\src\\main\\java\\classwork\\1.txt");
        OutputStream os = new FileOutputStream(file);
        for (int i = 0; i < 100000 / 10000; i++) {
            byte [] arr = new byte[10000];
            Arrays.fill(arr, (byte)'0');
            os.write(arr);
        }
        os.close();
        BufferedReader br; //byte [] arr
        BufferedWriter bw;
    }

    public static void main(String[] args) throws IOException {
//        new Reading()
//                .ImgWrite(new File("C:\\Users\\Mikhail\\IdeaProjects\\Java31\\task3\\src\\main\\java\\classwork\\img.png"),
//                        new File("C:\\Users\\Mikhail\\IdeaProjects\\Java31\\task3\\src\\main\\java\\classwork\\1.png"));
        long start = System.currentTimeMillis();
        new Reading().writer();
        long end = System.currentTimeMillis();
        System.out.println(end - start + " ms");
    }

}
