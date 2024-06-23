create table if not exists INGREDIENT (
  "ID" identity,
  "CODE" CHARACTER(4) NOT NULL,
  "NAME" varchar(100) NOT NULL,
  "TYPE_ORD" TINYINT
);

ALTER TABLE INGREDIENT ADD CONSTRAINT code_UNIQUE UNIQUE(CODE);

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
  created_at timestamp not null,
  name varchar(50) not null,
  taco_order_id bigint not null, 
  taco_order_key bigint not null 
);
 
create table if not exists Ingredient_Ref (
  id identity,
  ingredient bigint not null,
  taco bigint not null,
  taco_key bigint not null
);
 
alter table Taco
    add foreign key (taco_order_id) references Taco_Order(id);
alter table Ingredient_Ref
    add foreign key (ingredient) references Ingredient(id);
alter table Ingredient_Ref
    add foreign key (taco) references Taco(id);