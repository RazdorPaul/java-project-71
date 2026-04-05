package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "gendiff",
                     mixinStandardHelpOptions = true,
                     version = "gendiff 1.0",
                     description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    @Option(names = {"-f", "--format"},
            defaultValue = "stylish",
            paramLabel = "format",
            description = "output format [default: ${DEFAULT-VALUE}]")
    private String format;
    @CommandLine.Parameters(index = "0",
                            paramLabel = "filepath1",
                            description = "path to first file")
    private String filepath1;
    @CommandLine.Parameters(index = "1",
            paramLabel = "filepath2",
            description = "path to second file")
    private String filepath2;

    public static void main(String[] args) {
        System.exit(new CommandLine(new App()).execute(args));
    }

    @Override
    public Integer call() throws Exception {
        Gendiff gen = new Gendiff(filepath1, filepath2, "json");
        System.out.println(gen.getMap(gen.getfirstFile()));
        System.out.println(gen.getMap(gen.getSecondFile()));
        return 0;
    }
}