package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;

@Command(name = "gendiff",
        mixinStandardHelpOptions = true, version = "Version 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer>{

    @Option(names = {"-f", "--format"},
            paramLabel = "format",
            description = "output format [default: stylish]")
    private String typefile = "stylish";
    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private String defaultFolder1;
    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private String defaultFolder2;

    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
    }

    @Override
    public Integer call() throws Exception {
        GenDiff generator = new GenDiff(defaultFolder1, defaultFolder2);
        System.out.println(generator.generate());
        return 0;
    }
}
