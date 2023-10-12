create table public."order"
(
    id              bigint                not null
        primary key,
    create_time_utc timestamp(6)          not null,
    update_time_utc timestamp(6)          not null,
    creator         varchar,
    updater         varchar,
    deleted         boolean default false not null,
    user_id         varchar               not null,
    type            varchar               not null,
    state           varchar               not null,
    wuxing_id       varchar(255),
    data            varchar,
    username        varchar               not null,
    phone           varchar,
    email           varchar,
    description     varchar
);

comment on column public."order".id is 'id';

comment on column public."order".user_id is '用户id';

comment on column public."order".type is '订单类型';

comment on column public."order".state is '订单状态';

comment on column public."order".wuxing_id is '屋型id';

comment on column public."order".data is '冗余数据';

comment on column public."order".username is '用户名';

comment on column public."order".phone is '手机号';

comment on column public."order".email is '邮箱';

comment on column public."order".description is '描述';


create index order_user_id_idx
    on public."order" (user_id);

create index order_wuxing_id_idx
    on public."order" (wuxing_id);

alter table "user"
    rename column mobile to phone;