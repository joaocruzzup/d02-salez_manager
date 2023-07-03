package org.example;

import java.util.List;

public interface Cadastravel {
    public abstract void cadastrar(Object obj);
    public abstract void listar(List<Object> entidade);
}
