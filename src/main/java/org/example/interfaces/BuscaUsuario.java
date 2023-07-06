package org.example.interfaces;

import org.example.Exceptions.UsuarioNaoCadastradoException;
import org.example.model.Usuario;

public interface BuscaUsuario {
    public abstract Usuario buscaUsuario(String cpf) throws UsuarioNaoCadastradoException;
}
