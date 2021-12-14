CREATE TABLE club (
  id_club varchar(255) not null,
  cover_img varchar(255) not null,
  date_cre timestamp,
  desc_club varchar(255) not null,
  logo varchar(255) not null,
  nom_club varchar(255) not null,
  status number(1) not null,
  affiliation_id varchar(255),
  categorie_id varchar(255),
  referent_id varchar(255),
  tresorerie_id varchar(255),
  primary key (id_club),
  foreign key (affiliation_id) REFERENCES club(affiliation_id),
  foreign key (categorie_id) REFERENCES club(categorie_id),
  foreign key (referent_id) REFERENCES club(referent_id),
  foreign key (tresorerie_id) REFERENCES club(tresorerie_id)
);