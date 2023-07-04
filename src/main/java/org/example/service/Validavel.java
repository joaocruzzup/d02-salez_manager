package org.example.service;

import org.example.model.Usuario;

public interface Validavel {
    public abstract void validarEmail(Usuario usuario);
    public abstract void validarEmailRepetido(Usuario usuario);
    public abstract void validarCpfRepetido(Usuario usuario);

}
