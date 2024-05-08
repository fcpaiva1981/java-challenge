create database javachallenge;
use javachallenge;
create table flyway_schema_history(installed_rank int  not null primary key,version  varchar(50)  null,description  varchar(200)  not null,type  varchar(20) not null,script  varchar(1000) not null,checksum int null,installed_by  varchar(100) not null,installed_on  timestamp default CURRENT_TIMESTAMP not null,execution_time int not null,success tinyint(1) not null);
create index flyway_schema_history_s_idx on flyway_schema_history (success);
create table tb_usuarios(id bigint auto_increment primary key,email varchar(255) null,nome  varchar(255) null);
create table tb_permissoes(id  bigint auto_increment primary key,descricao varchar(255) null,fk_usuario_id bigint null, constraint FKd6cblrjynl1gfw4rkt38f7p26 foreign key (fk_usuario_id) references tb_usuarios (id));
