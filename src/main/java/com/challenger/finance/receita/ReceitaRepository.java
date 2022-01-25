package com.challenger.finance.receita;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
class ReceitaRepository implements JpaRepository<Receita, Long> {

    @Override
    public List<Receita> findAll() {
        return null;
    }

    @Override
    public List<Receita> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Receita> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Receita> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Receita entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Receita> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Receita> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Receita> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Receita> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Receita> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Receita> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Receita> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Receita getOne(Long aLong) {
        return null;
    }

    @Override
    public Receita getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Receita> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Receita> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Receita> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Receita> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Receita> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Receita> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Receita, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
