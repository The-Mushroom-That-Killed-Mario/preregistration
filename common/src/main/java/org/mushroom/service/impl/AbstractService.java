//package org.mushroom.service.impl;
//
//import lombok.Data;
//import org.mushroom.exception.EntityNotFoundException;
//import org.mushroom.model.BaseEntity;
//import org.mushroom.service.BaseService;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Data
//public abstract class AbstractService<
//        E extends BaseEntity,
//        ID extends Number,
//        R extends JpaRepository<E, ID>
//        >
//        implements BaseService<E, ID> {
//
//    private final E entity;
//    private final R repository;
//    private final ID id;
//
//    public AbstractService(E entity, R repository, ID id) {
//        this.entity = entity;
//        this.repository = repository;
//        this.id = id;
//    }
//
//    @Override
//    public Optional<E> findOne(ID id){
//        return repository.findById(id);
//    }
//    @Override
//    public E findById(ID id){
//        return repository.findById(id).orElseThrow(()->new EntityNotFoundException(id,entity.getClass().getSimpleName()));
//
//    }
//    @Override
//    public List<E> findAll(){
//        return null;
//    }
//    @Override
//    public E create(E object){
//        return null;
//    }
//    @Override
//    public E update(E object){
//        return null;
//    }
//    @Override
//    public void delete(ID id){
//
//    }
//    @Override
//    public void softDelete(ID id){
//
//    }
//
//
////    @Override
////    public List<E> findAll() {
////
////        return (List<E>) repository.findAll();
////
////    }
////
////
////    @Override
////    public Optional<E> findById(ID id){
////        existById(id);
////        Optional<E> optionalE = repository.findById(id);
////        T t = mapper.toTransfer(optionalE.get());
////
////        return Optional.of(t);
////    }
////
////    @Override
////    public T findOne(@Valid Long id){
////        return this.findById(id).get();
////    }
////
////    @Override
////    public void hardDelete(@Valid Long id){
////        existById(id);
////
////        repository.deleteById(id);
////    }
////
////    @Override
////    public void softDelete(@Valid Long id){
////        existById(id);
////
////        T t = this.findOne(id);
////        t.setEntityStatus(EntityStatus.DELETED);
////        this.update(t);
////    }
////
////    @Override
////    public T create(@Valid T t){
////        E e = mapper.toEntity(t);
////        e.setChanged(timeManager.currentTime());
////        e.setCreated(timeManager.currentTime());
////
////        T createdT = mapper.toTransfer(repository.save(e));
////
////        return createdT;
////    }
////
////    @Override
////    public T update(@Valid T t){
////        existById(t.getId());
////
////        E e = mapper.toEntity(t);
////        e.setChanged(timeManager.currentTime());
////        T updatedT = mapper.toTransfer(repository.save(e));
////
////        return updatedT;
////    }
////
////    @Override
////    public Boolean existById(@Valid Long id) throws EntityNotFoundException {
////        if (!repository.existsById(id)){
////            throw new EntityNotFoundException("id", id);
////        }
////
////        return true;
////    }
//}
