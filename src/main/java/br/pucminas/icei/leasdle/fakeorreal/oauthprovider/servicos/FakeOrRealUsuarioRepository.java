package br.pucminas.icei.leasdle.fakeorreal.oauthprovider.servicos;

import org.springframework.stereotype.Repository;

import br.pucminas.icei.leasdle.fakeorreal.oauthprovider.modelos.Usuario;
import br.pucminas.icei.leasdle.fakeorreal.oauthprovider.servicos.repositorio.FakeOrRealDatabase;
import br.pucminas.icei.leasdle.fakeorreal.oauthprovider.servicos.repositorio.UsuarioRepository;

/**
 *
 * @author gustavo
 */
@Repository
public class FakeOrRealUsuarioRepository {
//public class FakeOrRealUsuarioRepository implements UsuarioRepository {

    
    public Usuario findByLogin(String login) {
        return FakeOrRealDatabase.getInstance().getByCpf(login);
    }

    
    public Usuario findByLoginAndSenha(String login, String senha) {
        return findByLogin(login);
    }

    
    public <S extends Usuario> S save(S s) {
        throw new UnsupportedOperationException("FakeOrRealRepository.save(usuario): Not supported yet.");
    }

    
    public <S extends Usuario> Iterable<S> save(Iterable<S> itrbl) {
        throw new UnsupportedOperationException("FakeOrRealRepository.save(usuarios): Not supported yet.");
    }

    
    public Usuario findOne(Long id) {
        return FakeOrRealDatabase.getInstance().getById(id.intValue());
    }

    
    public boolean exists(Long id) {
        return FakeOrRealDatabase.getInstance().getById(id.intValue()) != null;
    }

    
    public Iterable<Usuario> findAll() {
        return FakeOrRealDatabase.getInstance().getAll();
    }

    
    public Iterable<Usuario> findAll(Iterable<Long> itrbl) {
        throw new UnsupportedOperationException("FakeOrRealRepository.findAll(ids): Not supported yet.");
    }

    
    public long count() {
        throw new UnsupportedOperationException("FakeOrRealRepository.count(): Not supported yet.");
    }

    
    public void delete(Long id) {
        throw new UnsupportedOperationException("FakeOrRealRepository.delete(id): Not supported yet.");
    }

    
    public void delete(Usuario t) {
        throw new UnsupportedOperationException("FakeOrRealRepository.delete(usuario): Not supported yet.");
    }

    
    public void delete(Iterable<? extends Usuario> itrbl) {
        throw new UnsupportedOperationException("FakeOrRealRepository.delete(usuarios): Not supported yet.");
    }

    
    public void deleteAll() {
        throw new UnsupportedOperationException("FakeOrRealRepository.deleteAll(): Not supported yet.");
    }
    
}
