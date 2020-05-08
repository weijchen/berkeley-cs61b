package com.company;

import javax.lang.model.element.AnnotationValue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    private static AnnotationValue IOUtils;

    public static void main(String[] args){
        Scanner sc = new Scanner(new File("test.txt"));
        while (sc.hasNextLine())
            System.out.println(sc.nextLine());
    }
}

