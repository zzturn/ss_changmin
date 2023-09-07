CREATE TABLE "public"."access_token"
(
    "id"               int8                                        NOT NULL,
    "create_time_utc"  timestamp(6)                                NOT NULL,
    "update_time_utc"  timestamp(6)                                NOT NULL,
    "creator"          varchar COLLATE "pg_catalog"."default",
    "updater"          varchar COLLATE "pg_catalog"."default",
    "deleted"          bool                                        NOT NULL DEFAULT false,
    "user_id"          varchar COLLATE "pg_catalog"."default"      NOT NULL,
    "user_type"        int2                                        NOT NULL,
    "access_token"     varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
    "refresh_token"    varchar(255) COLLATE "pg_catalog"."default",
    "client_id"        varchar(255) COLLATE "pg_catalog"."default",
    "scopes"           varchar(255) COLLATE "pg_catalog"."default",
    "expires_time_utc" timestamp(6),

    "user_context"     varchar(255) COLLATE "pg_catalog"."default",
    CONSTRAINT "access_token_pkey" PRIMARY KEY ("id")
)
;


CREATE INDEX "access_token_access_token_idx" ON "public"."access_token" USING btree (
    "access_token" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
    );

CREATE INDEX "access_token_user_id_idx" ON "public"."access_token" USING btree (
    "user_id" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
    );

COMMENT
ON COLUMN "public"."access_token"."id" IS '编号';
COMMENT
ON COLUMN "public"."access_token"."user_id" IS '用户编号';
COMMENT
ON COLUMN "public"."access_token"."user_type" IS '用户类型';
COMMENT
ON COLUMN "public"."access_token"."access_token" IS '访问令牌';
COMMENT
ON COLUMN "public"."access_token"."refresh_token" IS '刷新令牌';
COMMENT
ON COLUMN "public"."access_token"."client_id" IS '客户端编号';
COMMENT
ON COLUMN "public"."access_token"."scopes" IS '授权范围';
COMMENT
ON COLUMN "public"."access_token"."expires_time_utc" IS '过期时间';
COMMENT
ON COLUMN "public"."access_token"."creator" IS '创建者';
COMMENT
ON COLUMN "public"."access_token"."create_time_utc" IS '创建时间';
COMMENT
ON COLUMN "public"."access_token"."updater" IS '更新者';
COMMENT
ON COLUMN "public"."access_token"."update_time_utc" IS '更新时间';
COMMENT
ON COLUMN "public"."access_token"."deleted" IS '是否删除';
COMMENT
ON COLUMN "public"."access_token"."user_context" IS '用户信息';
COMMENT
ON TABLE "public"."access_token" IS 'OAuth2 访问令牌';



CREATE TABLE "public"."user"
(
    "id"              varchar COLLATE "pg_catalog"."default"     NOT NULL,
    "username"        varchar(50) COLLATE "pg_catalog"."default",
    "password"        varchar(100) COLLATE "pg_catalog"."default"         DEFAULT '':: character varying,
    "nickname"        varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
    "remark"          varchar(500) COLLATE "pg_catalog"."default",
    "email"           varchar(255) COLLATE "pg_catalog"."default",
    "mobile"          varchar(50) COLLATE "pg_catalog"."default",
    "gender"          varchar(50) COLLATE "pg_catalog"."default",
    "avatar"          varchar(255) COLLATE "pg_catalog"."default",
    "status"          int2                                       NOT NULL DEFAULT 0,
    "login_ip"        varchar(50) COLLATE "pg_catalog"."default",
    "login_date"      timestamp(6),
    "creator"         varchar COLLATE "pg_catalog"."default"     ,
    "create_time_utc" timestamp(6)                               NOT NULL,
    "updater"         varchar COLLATE "pg_catalog"."default"     ,
    "update_time_utc" timestamp(6)                               NOT NULL,
    "deleted"         bool                                       NOT NULL DEFAULT false,
    "user_type"       int2                                       NOT NULL DEFAULT 1,
    "openid"          varchar(255) COLLATE "pg_catalog"."default",
    CONSTRAINT "user_pkey" PRIMARY KEY ("id")
)
;


CREATE INDEX "user_mobile_idx" ON "public"."user" USING btree (
    "mobile" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
    );

CREATE INDEX "user_openid_idx" ON "public"."user" USING btree (
    "openid" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
    );


COMMENT
ON COLUMN "public"."user"."id" IS '用户ID';

COMMENT
ON COLUMN "public"."user"."username" IS '用户账号';

COMMENT
ON COLUMN "public"."user"."password" IS '密码';

COMMENT
ON COLUMN "public"."user"."nickname" IS '用户昵称';

COMMENT
ON COLUMN "public"."user"."remark" IS '备注';

COMMENT
ON COLUMN "public"."user"."email" IS '用户邮箱';

COMMENT
ON COLUMN "public"."user"."mobile" IS '手机号码';

COMMENT
ON COLUMN "public"."user"."gender" IS '用户性别';

COMMENT
ON COLUMN "public"."user"."avatar" IS '头像地址';

COMMENT
ON COLUMN "public"."user"."status" IS '帐号状态（0正常 1停用）';

COMMENT
ON COLUMN "public"."user"."login_ip" IS '最后登录IP';

COMMENT
ON COLUMN "public"."user"."login_date" IS '最后登录时间';

COMMENT
ON COLUMN "public"."user"."creator" IS '创建者';

COMMENT
ON COLUMN "public"."user"."create_time_utc" IS '创建时间';

COMMENT
ON COLUMN "public"."user"."updater" IS '更新者';

COMMENT
ON COLUMN "public"."user"."update_time_utc" IS '更新时间';

COMMENT
ON COLUMN "public"."user"."deleted" IS '是否删除';

COMMENT
ON COLUMN "public"."user"."user_type" IS '用户类型 1前台用户 2后台用户';

COMMENT
ON TABLE "public"."user" IS '用户信息表';


CREATE TABLE "public"."wuxing" (
   "id" varchar COLLATE "pg_catalog"."default" NOT NULL,
   "create_time_utc" timestamp(6) NOT NULL,
   "update_time_utc" timestamp(6) NOT NULL,
   "creator" varchar COLLATE "pg_catalog"."default",
   "updater" varchar COLLATE "pg_catalog"."default",
   "deleted" bool NOT NULL DEFAULT false,
   "data" varchar COLLATE "pg_catalog"."default",
   "area_value" float8,
   "price" int8,
   "yong_tu" varchar COLLATE "pg_catalog"."default",
   "area" varchar COLLATE "pg_catalog"."default",
   "feng_ge" varchar COLLATE "pg_catalog"."default",
   "roof" varchar COLLATE "pg_catalog"."default",
   "room_count" varchar COLLATE "pg_catalog"."default",
   "can_custom" bool,
   "type" varchar COLLATE "pg_catalog"."default",
   "cover_url" varchar COLLATE "pg_catalog"."default",
   "xian_chang_zhao_pian_url" varchar COLLATE "pg_catalog"."default",
   "jian_zhu_tu_mian_url" varchar COLLATE "pg_catalog"."default",
   CONSTRAINT "wuxing_pkey" PRIMARY KEY ("id")
)
;

ALTER TABLE "public"."wuxing"
    OWNER TO "postgres";

COMMENT ON COLUMN "public"."wuxing"."area_value" IS '面积';

COMMENT ON COLUMN "public"."wuxing"."price" IS '价格';

COMMENT ON COLUMN "public"."wuxing"."yong_tu" IS '用途';

COMMENT ON COLUMN "public"."wuxing"."area" IS '面积枚举';

COMMENT ON COLUMN "public"."wuxing"."feng_ge" IS '风格';

COMMENT ON COLUMN "public"."wuxing"."roof" IS '屋顶';

COMMENT ON COLUMN "public"."wuxing"."room_count" IS '房间数类型';

COMMENT ON COLUMN "public"."wuxing"."can_custom" IS '能否定制';

COMMENT ON COLUMN "public"."wuxing"."type" IS '官方or用户保存的';

COMMENT ON COLUMN "public"."wuxing"."cover_url" IS '封面';