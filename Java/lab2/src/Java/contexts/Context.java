package Java.contexts;

import Java.exceptions.BadVariableException;

import java.util.EmptyStackException;

public interface Context<T> {

    T viewTopElement()throws EmptyStackException;

    T getTopElement() throws EmptyStackException;

    void addElementToTop(T elem);

    void addVariable(String name, T value) throws BadVariableException;

    T getVariableValue(String name) throws  BadVariableException;
}
