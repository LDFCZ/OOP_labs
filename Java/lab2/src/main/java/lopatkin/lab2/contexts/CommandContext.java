package lopatkin.lab2.contexts;

import lopatkin.lab2.constspace.ConstSpace;
import lopatkin.lab2.exceptions.BadVariableException;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandContext implements Context<Double> {
    Stack<Double> stack = new Stack<>();
    Map<String, Double> map = new HashMap<>();

    @Override
    public Double viewTopElement() {
        return stack.peek();
    }

    @Override
    public Double getTopElement() throws EmptyStackException {
        return stack.pop();
    }

    @Override
    public void addElementToTop(Double elem) {
        stack.push(elem);
    }

    @Override
    public void addVariable(String name, Double value) throws BadVariableException {
        String regex = ConstSpace.VAR_NAME_FILTER;
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(name);

        if (m.matches()) {
            map.put(name, value);
        }
        else {
            throw new BadVariableException(name + ConstSpace.BAD_NAME_ERROR);
        }
    }

    @Override
    public Double getVariableValue(String name) throws BadVariableException {
        if (map.containsKey(name)) {
            return map.get(name);
        }
        else throw new BadVariableException(name + ConstSpace.NAN_OR_NAV_ERROR);
    }
}
