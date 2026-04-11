package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;

import java.io.IOException;
import java.util.concurrent.Callable;

@Command(name = "Gendiff",
    mixinStandardHelpOptions = true,
    version = "gendiff 1.0",
    description = "Compares two configuration files and shows a difference.")
public final class App implements Callable<Integer> {

    /**
     *Опция для указания формата выходного файла.
     */
    @Option(names = {"-f", "--format"},
            defaultValue = "stylish",
            paramLabel = "format",
            description = "output format [default: ${DEFAULT-VALUE}]")
    private String format;

    /**
     *Обязательный параметр-указание имени первого файла.
     */
    @SuppressWarnings("unused")
    @Parameters(index = "0",
                            paramLabel = "filepath1",
                            description = "path to first file")
    private String filepath1;
    /**
     *Обязательный параметр-указание имени второго файла.
     */
    @SuppressWarnings("unused")
    @Parameters(index = "1",
            paramLabel = "filepath2",
            description = "path to second file")
    private String filepath2;

    /**
     * Точка входа в приложение.
     * @param args
     */
    public static void main(final String[] args) {
        System.exit(new CommandLine(new App()).execute(args));
    }

    @Override
    public Integer call() {
        try {
            Differ gen = new Differ(filepath1, filepath2);
            var mess = gen.generate(format);
            System.out.println(mess);
        } catch (IOException e) {
            System.err.println("При вызове программы указаны "
                    + "неверные пути к файлу/ам: " + e.getMessage());
            return -1;
        }
        return 0;
    }
}
