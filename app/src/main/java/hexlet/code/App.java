package hexlet.code;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import hexlet.code.utils.FileUtils;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
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
        Path path1 = FileUtils.getPath(defaultFolder1);
        Path path2 = FileUtils.get6+Path(defaultFolder2);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map1 = mapper.readValue(path1.toFile(), new TypeReference<>(){});
        Map<String, Object> map2 = mapper.readValue(path2.toFile(), new TypeReference<>(){});
        System.out.println(map1);
        System.out.println(map2);
        return 0;
    }
}
