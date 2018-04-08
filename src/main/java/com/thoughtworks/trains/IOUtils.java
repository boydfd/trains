package com.thoughtworks.trains;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by sijieliu on 3/31/18.
 */
public class IOUtils {
    public static ArrayList<String> readFile(String path) throws IOException, NumberFormatException {
        ArrayList<String> rst = new ArrayList<>();

        try (BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            if (input.ready()) {
                String line = input.readLine();

                if (line != null) {
                    String[] temp = line.split("[,:]");

                    for(int i = 1; i < temp.length; i++) {
                        rst.add(temp[i].trim());
                    }
                } else {
                    throw new RuntimeException(Main.INVALID_INPUT);
                }
            }
        }

        return rst;
    }
}
