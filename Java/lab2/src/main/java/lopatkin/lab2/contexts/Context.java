package lopatkin.lab2.contexts;


import lopatkin.lab2.exceptions.BadVariableException;

import java.util.EmptyStackException;

public interface Context<T> {


    T viewTopElement()throws EmptyStackException;

    T getTopElement() throws EmptyStackException;

    void addElementToTop(T elem);

    void addVariable(String name, T value) throws BadVariableException;

    T getVariableValue(String name) throws  BadVariableException;
}
