package Java.constspace;

public class CONSTSPACE {
    public static final String START_MESSAGE = "Application started and constructed!";
    

    public static final String PATH_TO_LOG_FILE = "logs.log";

    public static final String LOGGER_EXCEPTION = "Logger Exception";
    public static final String ARGS_COUNT_EXCEPTION = "Args Count Exception";
    public static final String FILE_EXCEPTION = "Bad Input File Exception";

    public static final int FILE_NAME_ARG = 0;



    public static final String LOG_NAME = "MyLogger";


    public static final String LOG_INFO = "Logger successfully launched!";
    public static final String LOG_PROPERTY = "java.util.logging.SimpleFormatter.format";
    public static final String LOG_FORMAT = "[%1$tF %1$tT] [%4$-7s] %5$s %n";
    public static final String OPEN_BRACKET = "[";
    public static final String CLOSE_BRACKET = "] ";


    public static final String COMMAND_PATH = "resources/command.properties";
    public static final String CONFIG_PATH = "resources/config.properties";

    public static final String COMMAND_CLASS_PATH = "COMMAND_CLASS_PATH";


    public static final String START_MESSAGE_SIS_EXECUTOR = "SISExecutor started and constructed!";
    public static final String START_MESSAGE_FILE_EXECUTOR = "FileExecutor started and constructed!";

    public static final String WELCOME_STRING = "Enter a Commands: ";
    public static final String SPACE = " ";
    public static final Character COMMENT = '#';

    public static final int COMMAND_NAME_ARG = 0;

    public static final String LOG_READ_INFO_1 = "Read ";
    public static final String LOG_READ_INFO_2 = " command";
    public static final String LOG_READ_INFO_3 = "Start to read file";
    public static final String LOG_EXECUTE_INFO_1 = "Start to execute commands";
    public static final String LOG_EXECUTE_INFO_2 = "Start to execute command";

    public static final String LOG_CREATE_SEVERE_1 = "Failed to create command ";
    public static final String LOG_CREATE_SEVERE_2 = ". No such command";

    public static final String ARGS_ERROR = "bad num of args for command ";
    public static final String FILE_ARGS_ERROR = "File reading failed, bad args for command ";

    public static final String OTHER_ERRORS = "Properties file reading or command creating failed";
    public static final String UNKNOWN_ERRORS = "File reading failed, unknown fail";

    public static final String EXECUTION_ERROR = "Failed to execute command ";

    public static final String PROPERTIES_ERROR = "Properties file reading failed";

    public static final String FILE_ERROR = "File reading failed";

    public static final String NO_COMMAND_ERROR = "File reading failed, command not found ";

    public static final String LINE = " line: ";

    public static final String STACK_ERROR = "Stack is empty!";

    public static final int COMMON_COMMANDS_ARGS_COUNT = 1;

    public static final int PUSH_COMMAND_ARGS_COUNT = 2;
    public static final int PUSH_ARG = 1;
    public static final String NAN_OR_NAV_ERROR = " - Not a number or variable";

    public static final String VAR_NAME_FILTER = "^[a-zA-Z_][a-zA-Z0-9_]*$";
    public static final int DEFINE_COMMAND_ARGS_COUNT = 3;
    public static final int VAR_NAME_ARG = 1;
    public static final int NUM_NAME_ARG = 2;
    public static final String NAN_ERROR = " - NAN";
    public static final String BAD_NAME_ERROR = " - Bad variable name";

    public static final String COMMAND_NAME_ERROR_1 = "Command ";
    public static final String COMMAND_NAME_ERROR_2 = " not found";
    public static final String EXIT_COMMAND = "EXIT";

    public static final int MAX_INPUT_ARGS_COUNT = 1;

    public static final String NEGATIVE_NUM_ERROR = "negative number in SQRT";
    public static final String ZERO_DIV_ERROR = "zero division!";
}
