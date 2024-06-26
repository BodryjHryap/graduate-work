-- liquibase formatted sql

-- changeset khromchenkodv:1

   CREATE TABLE users (
   	id              int8 GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
   	first_name      varchar(255),
   	last_name       varchar(255),
   	"password"      varchar(255),
   	phone           varchar(255),
   	"role"          varchar(255) NOT NULL,
   	username        varchar(255) UNIQUE NOT NULL,
   	enabled         boolean
   );

   CREATE TABLE ads (
   	id              int8 GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
   	description     varchar(255),
   	price           numeric(19, 2),
   	title           varchar(255),
   	user_id         int8 REFERENCES users(id) ON DELETE CASCADE,
   	image_id        int8
   );

   CREATE TABLE avatars (
   	id              int8 GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
   	image           bytea,
   	user_id         int8 UNIQUE REFERENCES users(id) ON DELETE CASCADE
   );

   CREATE TABLE "comments" (
   	id              int8 GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
   	created_at      timestamp,
   	"text"          varchar(255),
   	ad_id           int8 REFERENCES ads(id) ON DELETE CASCADE,
   	user_id         int8 REFERENCES users(id) ON DELETE CASCADE
   );

   CREATE TABLE images (
   	id              int8 GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
   	"data"          bytea NULL,
   	file_size       int8 NULL,
   	media_type      varchar(255) NULL
   );
