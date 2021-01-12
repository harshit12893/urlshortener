drop table if exists url_data;

create table url_data (
	id_hash varchar(32) PRIMARY KEY,
	original_url varchar(4000),
	shortened_url varchar(100),
	created_date date,
	expiration_date date
);