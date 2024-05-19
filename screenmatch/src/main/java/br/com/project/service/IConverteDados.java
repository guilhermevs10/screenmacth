package br.com.project.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);

}
