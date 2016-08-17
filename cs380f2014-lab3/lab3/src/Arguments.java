import com.beust.jcommander.Parameter;
import com.beust.jcommander.JCommander;

public class Arguments {
    @Parameter(names = "-debug", description = "Debug mode", arity = 1)
    private boolean debug;

    @Parameter(names = "-pull", required = true, description = "determines what to output")
    private String pull;

    public boolean getOutput() {
        return debug;
    }

    public String getPull() {
        return pull;
    }
}

