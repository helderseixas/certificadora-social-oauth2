package br.pucminas.icei.leasdle.fakeorreal.oauthprovider.servicos.repositorio;

import br.pucminas.icei.leasdle.fakeorreal.oauthprovider.modelos.SegurancaAPI;
import br.pucminas.icei.leasdle.fakeorreal.oauthprovider.modelos.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SegurancaRepository extends CrudRepository<SegurancaAPI, Long> {
        
        @Query("SELECT s FROM SegurancaAPI s WHERE s.token = :token")
        SegurancaAPI findByToken(@Param("token") String token);
        
        //SegurancaAPI findByUsuario(Usuario usuario);
        @Query("SELECT s FROM SegurancaAPI s WHERE s.usuarioCPF = :usuarioCPF")
        SegurancaAPI findByUsuarioCPF(@Param("usuarioCPF") String usuarioCPF);
        
}
