package ngdemo.repositories.impl.mock;

import ngdemo.repositories.contract.Repository;

import java.util.List;

public abstract class GenericMockRepository<T> implements Repository<T> {

    
    public List<T> getAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    
    public T getById(int id) {
        return (T) null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
