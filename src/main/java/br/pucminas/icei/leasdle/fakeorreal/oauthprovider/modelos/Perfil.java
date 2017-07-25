package br.pucminas.icei.leasdle.fakeorreal.oauthprovider.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import br.pucminas.icei.leasdle.fakeorreal.oauthprovider.modelos.enums.RoleEnum;
import br.pucminas.icei.leasdle.fakeorreal.oauthprovider.util.Modelos;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Perfil extends Modelos {
        
        private static final long serialVersionUID = 8765060059417187982L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "perfilGenerator")
	@TableGenerator(name = "perfilGenerator", allocationSize = 1)
	private Long id;
        
        @NotEmpty
        private String nome;
        
        private String descricao;
        
        @JsonIgnore //nao eh bom retornar as permissoes em tela...
        @ManyToMany
        private List<Permissao> permissoes;
        
        //@JsonIgnore
        //@OneToMany(mappedBy = "perfil")
        @Transient        
        private List<Usuario> usuarios;

        public boolean contemRoleOuAdmin(RoleEnum roleConfigurada) {
                if(permissoes!=null && !permissoes.isEmpty()){
                        if (permissoes.stream().anyMatch((perm) -> (perm.getPapel().equals(roleConfigurada) || perm.isAdmin()))) {
                                return true;
                        }
                }
                return false;
        }
        
        @Override
        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getNome() {
                return nome;
        }

        public void setNome(String nome) {
                this.nome = nome;
        }

        public String getDescricao() {
                return descricao;
        }

        public void setDescricao(String descricao) {
                this.descricao = descricao;
        }

        public List<Permissao> getPermissoes() {
                return permissoes;
        }

        public void setPermissoes(List<Permissao> permissoes) {
                this.permissoes = permissoes;
        }

        public List<Usuario> getUsuarios() {
                return usuarios;
        }

        public void setUsuarios(List<Usuario> usuarios) {
                this.usuarios = usuarios;
        }

}
