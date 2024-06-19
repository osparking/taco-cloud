DROP Table INGREDIENT if exists;
CREATE CACHED TABLE "PUBLIC"."INGREDIENT"(
  "SN" smallint GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL,
  "ID" CHARACTER(4) NOT NULL,
  "NAME" CHARACTER VARYING(100) NOT NULL,
  "TYPE_ORD" TINYINT
);

ALTER TABLE "PUBLIC"."INGREDIENT" ADD CONSTRAINT "PUBLIC"."ING_PRI_KEY" PRIMARY KEY("SN");

ALTER TABLE "PUBLIC"."INGREDIENT" ADD CONSTRAINT ID_UNIQUE UNIQUE(ID);

create table if not exists Taco_Order (
  id identity,
  cust_name varchar(50) not null,
  deli_zip varchar(10) not null,
  deli_road_addr varchar(50) not null,
  deli_detail_addr varchar(50) not null,
  cc_number varchar(16) not null,
  cc_cvv varchar(3) not null,
  cc_expr_y_m varchar(5) not null,
  placed_at timestamp not null
);
 
create table if not exists Taco (
  id identity,
  name varchar(50) not null,
  taco_order bigint not null,
  taco_order_key bigint not null,
  created_at timestamp not null
);
 
create table if not exists Ingredient_Ref (
  ing_sn varchar(4) not null,
  taco_id bigint not null,
  taco_key bigint not null
);
 
alter table Taco
    add foreign key (taco_order) references Taco_Order(id);
alter table Ingredient_Ref
    add foreign key (ing_sn) references Ingredient(sn);