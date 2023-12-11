-- Project Name : noname
-- Date/Time    : 2023/12/06 10:52:09
-- Author       : rina.ozone
-- RDBMS Type   : PostgreSQL
-- Application  : A5:SQL Mk-2

/*
  << 注意！！ >>
  BackupToTempTable, RestoreFromTempTable疑似命令が付加されています。
  これにより、drop table, create table 後もデータが残ります。
  この機能は一時的に $$TableName のような一時テーブルを作成します。
  この機能は A5:SQL Mk-2でのみ有効であることに注意してください。
*/

-- 会社テーブル
-- * BackupToTempTable
drop table if exists companyTable cascade;

-- * RestoreFromTempTable
create table companyTable (
  companyId serial not null
  , company character varying not null
  , constraint companyTable_PKC primary key (companyId)
) ;

-- 個人情報テーブル
-- * BackupToTempTable
drop table if exists personalinfotable cascade;

-- * RestoreFromTempTable
create table personalinfotable (
  businesscardid serial not null
  , createdate date default CURRENT_DATE
  , name character varying(50) not null
  , companyId integer
  , department character varying(50)
  , post character varying(50)
  , address character varying(100)
  , tel character varying(50)
  , mail character varying(50)
  , projecttitle character varying(100) not null
  , remarks character varying(400)
  , constraint personalinfotable_PKC primary key (businesscardid)
) ;

alter table personalinfotable
  add constraint personalinfotable_FK1 foreign key (companyId) references companyTable(companyId);

comment on table companyTable is '会社テーブル';
comment on column companyTable.companyId is '会社ID';
comment on column companyTable.company is '会社名';

comment on table personalinfotable is '個人情報テーブル';
comment on column personalinfotable.businesscardid is '名刺ID';
comment on column personalinfotable.createdate is '登録日';
comment on column personalinfotable.name is '名前';
comment on column personalinfotable.companyId is '会社ID';
comment on column personalinfotable.department is '部署名';
comment on column personalinfotable.post is '役職名';
comment on column personalinfotable.address is '住所';
comment on column personalinfotable.tel is '電話番号';
comment on column personalinfotable.mail is 'メールアドレス';
comment on column personalinfotable.projecttitle is '案件名';
comment on column personalinfotable.remarks is '備考';

