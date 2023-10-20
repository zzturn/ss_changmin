alter table "order"
    add admin_desc varchar;

comment on column "order".admin_desc is '管理员备注';

