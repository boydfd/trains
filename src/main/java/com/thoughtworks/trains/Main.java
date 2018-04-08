package com.thoughtworks.trains;
import java.io.*;
import java.util.List;

public class Main {

    private static final int MIN_ARGS_LENGTH = 1;
    public static final String INVALID_INPUT = "Invalid Input.";
    private static final String DEFAULT_FILE = "input.txt";

    public static void main(String[] args) throws FileNotFoundException {
        String path = DEFAULT_FILE;

	    if(args.length >= MIN_ARGS_LENGTH) {
            path = args[0];
        }

        List<String> input;

        try {
            input = IOUtils.readFile(path);
        } catch (IOException e) {
            throw new RuntimeException(INVALID_INPUT);
        }

        ResolverOne resolverOne = new ResolverOne(input);
        resolverOne.resolve();
    }
}
