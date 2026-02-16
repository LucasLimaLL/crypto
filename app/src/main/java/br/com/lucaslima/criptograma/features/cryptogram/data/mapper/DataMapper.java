package br.com.lucaslima.criptograma.features.cryptogram.data.mapper;

public abstract class DataMapper<E, D> {

    public abstract E toEntity(D domain);

    public abstract D toDomain(E entity);

}
