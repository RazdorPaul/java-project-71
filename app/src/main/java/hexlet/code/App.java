package hexlet.code;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "Differ", mixinStandardHelpOptions = true, version = "1.0",
        description = "This program for calculate differs between two files")
public class App implements Callable<Integer> {
    public static void main(String[] args) {
        System.exit(new CommandLine(new App()).execute(args));
    }

    @Override
    public Integer call() throws Exception {
        return 0;
    }
}