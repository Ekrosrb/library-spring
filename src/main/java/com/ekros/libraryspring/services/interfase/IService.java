package com.ekros.libraryspring.services.interfase;



public interface IService<E, D> {

    D toDto(E e);

    E fromDto(D d);
}
